package kf.plt.tas.adminserver.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import kf.plt.admin.jwt.core.context.BaseContextHandler;
import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.service.common.util.Query;
import kf.plt.service.common.util.UUIDUtils;
import kf.plt.tas.adminserver.components.utils.ExcelUtil;
import kf.plt.tas.adminserver.entity.dataentity.RzZdj;
import kf.plt.tas.adminserver.entity.dataentity.TcmPk;
import kf.plt.tas.adminserver.entity.dataentity.TcmPkLog;
import kf.plt.tas.adminserver.entity.responentity.PublicKeyManageResponse;
import kf.plt.tas.adminserver.entity.responentity.TcmPkResponse;
import kf.plt.tas.adminserver.mapper.RzZdjMapper;
import kf.plt.tas.adminserver.mapper.TcmPkLogMapper;
import kf.plt.tas.adminserver.mapper.TcmPkMapper;
import tk.mybatis.mapper.entity.Example;

/**
 * 公钥管理业务层
 * 
 * @author wangs
 *
 */
@Service
public class TcmPkBiz extends KxBusinessBiz<TcmPkMapper, TcmPk> {

    @Autowired
    private TcmPkMapper tcmPkMapper;

    @Autowired
    private RzZdjMapper rzZdjMapper;

    @Autowired
    private TcmPkLogMapper tcmPkLogMapper;
    @Autowired
    private RzZdjMapper rZdjMapper;

    /**
     * 读取文件中的数据
     * 
     * @param request
     * @return
     * @throws Exception
     */
    public ObjectRestResponse<List<PublicKeyManageResponse>> saveExcelTcmPk(HttpServletRequest request)
        throws Exception {
        ObjectRestResponse<List<PublicKeyManageResponse>> res = new ObjectRestResponse<>();
        String userId = BaseContextHandler.getUserID();

        List<TcmPk> tcmPkList = new ArrayList<>();
        tcmPkList = getExcl(tcmPkList, request);
        // 判断excel格式是否正确
        if (tcmPkList == null) {
            res.setStatus("30410");
            res.setMessage("上传公钥失败");
            return res;
        }
        // 判断excel是否有数据
        if (tcmPkList.size() == 0) {
            res.setStatus("30411");
            res.setMessage("请检查文件是否有数据！");
            return res;
        }

        // 存放终端表中不存在的终端机编号
        List<PublicKeyManageResponse> tcmPkNoTerminalNo = new ArrayList<>();
        // 存放终端表中存在的终端机编号
        List<TcmPk> tcmPkHasTerminalNo = new ArrayList<>();

        // 判断终端机编号是否存在
        // 查询终端表中所有的终端机
        List<RzZdj> rzZdjs = rzZdjMapper.selectAll();
        // 循环excel中的信息
        for (TcmPk tcmPkExcel : tcmPkList) {
            // excel导入的终端编号是否存在在终端表中 ，默认不存在
            boolean hasTerminalCode = false;
            for (RzZdj rzZdj : rzZdjs) {
                if (rzZdj.getTerminalCode().equals(tcmPkExcel.getTerminalCode())) {
                    hasTerminalCode = true;
                    break;
                }
            }
            if (!hasTerminalCode) {
                PublicKeyManageResponse publicKeyManageResponse = new PublicKeyManageResponse();
                publicKeyManageResponse.setPublicKey(tcmPkExcel.getPublicKey());
                publicKeyManageResponse.setTerminalCode(tcmPkExcel.getTerminalCode());
                publicKeyManageResponse.setReason("终端编号不存在");
                // 添加终端编号不存在的列表
                tcmPkNoTerminalNo.add(publicKeyManageResponse);
                break;
            } else {
                // 添加终端编号存在的列表
                tcmPkHasTerminalNo.add(tcmPkExcel);
            }
        }
        
        // 判断终端编号是否不存在，不存在，则直接返回错误
        if (tcmPkNoTerminalNo != null && tcmPkNoTerminalNo.size() > 0) {
        	res.setStatus("30412");
            res.setMessage("上传公钥失败");
            res.data(tcmPkNoTerminalNo);
            return res;
		}
        
        // 查询库中所有的公钥信息
        List<TcmPk> tcmPks = tcmPkMapper.selectAll();
        // 存放所有有效的公钥信息,公钥为key值
        Map<String, String> publicKeyMap = new HashMap<String, String>();
        // 存放所有有效的公钥信息,终端编号为key值
        Map<String, String> TerminalCodeMap = new HashMap<String, String>();
        // 存放需要更新的信息
        Map<String, String> updateMap = new HashMap<String, String>();

        // 公钥重复的List
        List<PublicKeyManageResponse> publicKeyExist = new ArrayList<>();

        // 更新列表
        List<TcmPk> tcmPkUpdate = new ArrayList<>();
        // 插入列表
        List<TcmPk> tcmPkInsert = new ArrayList<>();

        for (TcmPk tcmPk : tcmPks) {
            publicKeyMap.put(tcmPk.getPublicKey(), tcmPk.getTerminalCode());
            TerminalCodeMap.put(tcmPk.getTerminalCode(), tcmPk.getPublicKey());
        }
        // 循环终端编号存在的列表
        for (TcmPk tcmPk : tcmPkHasTerminalNo) {

            String terminalCode = publicKeyMap.get(tcmPk.getPublicKey());
            // 判断公钥是否重复
            if (terminalCode == null || "".equals(terminalCode)) {
                // 不存在重复的公钥
                // 判断终端编号是否存在有效的公钥信息中
                String publicKey = TerminalCodeMap.get(tcmPk.getTerminalCode());
                if (publicKey != null && !"".equals(publicKey)) {
                    // 存在，更新操作
                    updateMap.put(tcmPk.getTerminalCode(), tcmPk.getPublicKey());
                    // 修改存放所有有效的公钥信息,公钥为key值
                    TerminalCodeMap.put(tcmPk.getTerminalCode(), tcmPk.getPublicKey());
                    // 修改存放所有有效的公钥信息,终端编号为key值
                    publicKeyMap.remove(publicKey);
                    // 把修改的终端编号和公钥添加到map中
                    publicKeyMap.put(tcmPk.getPublicKey(), tcmPk.getTerminalCode());
                } else {
                    // 不存在，新增操作
                    tcmPkInsert.add(tcmPk);
                    // 修改存放所有有效的公钥信息,公钥为key值
                    TerminalCodeMap.put(tcmPk.getTerminalCode(), tcmPk.getPublicKey());
                    // 修改存放所有有效的公钥信息,终端编号为key值
                    publicKeyMap.put(tcmPk.getPublicKey(), tcmPk.getTerminalCode());
                }
            } else {
                PublicKeyManageResponse publicKeyManageResponse = new PublicKeyManageResponse();
                publicKeyManageResponse.setPublicKey(tcmPk.getPublicKey());
                publicKeyManageResponse.setTerminalCode(tcmPk.getTerminalCode());
                publicKeyManageResponse.setReason("公钥已存在");
                // 存在重复的公钥
                publicKeyExist.add(publicKeyManageResponse);
                break;
            }
        }
        
        // 判断公钥是否重复，重复，则直接返回错误
        if (publicKeyExist != null && publicKeyExist.size() > 0 ) {
        	res.setStatus("30413");
            res.setMessage("上传公钥失败");
            res.data(publicKeyExist);
            return res;
		}

        // 迭代器 去除需要更新的信息
        Iterator<Entry<String, String>> it = updateMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, String> entry = it.next();
            TcmPk tcmPk = new TcmPk();
            tcmPk.setTerminalCode(entry.getKey());
            tcmPk.setPublicKey(entry.getValue());
            tcmPkUpdate.add(tcmPk);
        }

        // 批量插入
        if (tcmPkInsert != null && tcmPkInsert.size() > 0) {
            tcmPkMapper.insertTcmPk(tcmPkInsert);
            // 插入公钥日志
            List<TcmPkLog> tcmPkLogList = new ArrayList<>();
            for (TcmPk tcmPk : tcmPkInsert) {
                TcmPkLog tcmPkLog = new TcmPkLog();
                tcmPkLog.setId(UUIDUtils.generateUuid());
                tcmPkLog.setTerminalCode(tcmPk.getTerminalCode());
                tcmPkLog.setNewPublicKey(tcmPk.getPublicKey());
                tcmPkLog.setUserId(userId);
                tcmPkLog.setUpdateTime(new Date());
                tcmPkLogList.add(tcmPkLog);
            }
            tcmPkLogMapper.insertTcmPkLog(tcmPkLogList);
        }
        // 批量更新
        if (tcmPkUpdate != null && tcmPkUpdate.size() > 0) {
            tcmPkMapper.updateTcmPk(tcmPkUpdate);
            // 查询需要更新的终端编号的公钥日志
            List<TcmPkLog> tcmPkLogs = tcmPkLogMapper.selectTcmPkLogByTerminalCode(tcmPkUpdate);

            // 插入公钥日志
            List<TcmPkLog> tcmPkLogList = new ArrayList<>();
            for (TcmPk tcmPk : tcmPkUpdate) {
                // 存储日志里面最新的公钥为旧的公钥
                String oldPublicKey = null;
                for (TcmPkLog tcmPkLog : tcmPkLogs) {
                    // 判断终端编号是否一致，一致则日志里面最新的公钥存为旧的公钥，并跳出该循环
                    if (tcmPkLog.getTerminalCode().equals(tcmPk.getTerminalCode())) {
                        oldPublicKey = tcmPkLog.getNewPublicKey();
                        break;
                    }
                }
                TcmPkLog tcmPkLog = new TcmPkLog();
                tcmPkLog.setId(UUIDUtils.generateUuid());
                tcmPkLog.setTerminalCode(tcmPk.getTerminalCode());
                tcmPkLog.setOldPublicKey(oldPublicKey);
                tcmPkLog.setNewPublicKey(tcmPk.getPublicKey());
                tcmPkLog.setUserId(userId);
                tcmPkLog.setUpdateTime(new Date());
                tcmPkLogList.add(tcmPkLog);
            }
            tcmPkLogMapper.insertTcmPkLog(tcmPkLogList);
            // 删除公钥日志
            if (tcmPkLogs != null && tcmPkLogs.size() > 0) {
                tcmPkLogMapper.deleteTcmPkLog(tcmPkLogs);
            }
        }
        // 返回终端编号不存在以及返回公钥重复的列表
        tcmPkNoTerminalNo.addAll(publicKeyExist);
        res.setMessage("上传公钥成功");
        res.setData(tcmPkNoTerminalNo);
        return res;
    }

    /**
     * 获取excel中的公钥信息
     * 
     * @param tcmPkList
     *            目标公钥信息
     * @param request
     *            请求
     * @return
     * @throws Exception
     */
    public List<TcmPk> getExcl(List<TcmPk> tcmPkList, HttpServletRequest request) throws Exception {

        // 将请求转换成MultipartHttpServletRequest对象
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        // 获取请求内容
        MultipartFile excel = multipartRequest.getFile("file");
        // 获取Excel对象
        Workbook workbook = ExcelUtil.getWorkbook(excel.getInputStream(), excel.getOriginalFilename());
        // 获取sheet对象
        Sheet sheet = workbook.getSheetAt(0);
        // 获取bm所在的index
        int bmIndex = ExcelUtil.getIndexForCell(sheet, "bm");
        // 获取publickey所在的index
        int publicKeyIndex = ExcelUtil.getIndexForCell(sheet, "publickey");

        // 循环获取 从第二行开始（第一行为表头）
        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            // 获取sheet中某行的数据
            Row row = sheet.getRow(i);
            // 如果excel表传来的是numeric类型的数据，转为string类型
            // if (CellType.NUMERIC.equals(row.getCell(bmIndex).getCellType())) {
            // row.getCell(bmIndex).setCellType(CellType.STRING);
            // }
            row.getCell(bmIndex).setCellType(CellType.STRING);
            row.getCell(publicKeyIndex).setCellType(CellType.STRING);
            // 判断值是否存在，存在则保存数据库，不存在则返回错误信息
            if (StringUtils.isEmpty(row.getCell(bmIndex).getStringCellValue())
                || StringUtils.isEmpty(row.getCell(publicKeyIndex).getStringCellValue())) {
                return null;
            }
            // 获取终端机编号
            String bm = row.getCell(bmIndex).getStringCellValue();
            // 获取公钥
            String publicKey = row.getCell(publicKeyIndex).getStringCellValue();
            TcmPk tcmPk = new TcmPk();
            tcmPk.setId(UUIDUtils.generateUuid());
            tcmPk.setTerminalCode(bm);
            tcmPk.setPublicKey(publicKey);
            tcmPkList.add(tcmPk);
        }
        return tcmPkList;
    }

    /**
     * 查询公钥管理信息
     * 
     * @param query
     * @return
     */
    public TableResultResponse<TcmPkResponse> queryTcmPK(Query query) {

        Example example = new Example(TcmPk.class);
        this.splicingCriteria(query, example);
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        // 查询公钥信息
        List<TcmPk> tcmPks = super.selectByExample(example);
        List<TcmPkResponse> tcmPkResponses = new ArrayList<>();
        // 根据终端编号查询终端信息
        if (tcmPks.size() > 0) {
            List<RzZdj> rzZdjs = rZdjMapper.selectRzZdjByTerminalCode(tcmPks);
            for (TcmPk tcmPk : tcmPks) {
                for (RzZdj rzZdj : rzZdjs) {
                    // 如果公钥信息与终端信息的终端编号一致，则将该终端信息和公钥信息合并
                    if (tcmPk.getTerminalCode().equals(rzZdj.getTerminalCode())) {
                        // 公钥管理查询结果
                        TcmPkResponse tcmPkResponse = new TcmPkResponse();
                        tcmPkResponse.setPublicKey(tcmPk.getPublicKey());
                        tcmPkResponse.setTerminalCode(rzZdj.getTerminalCode());
                        tcmPkResponse.setTerminalName(rzZdj.getTerminalName());
                        tcmPkResponse.setMachineType(rzZdj.getMachineType());
                        tcmPkResponses.add(tcmPkResponse);
                        break;
                    }
                }
            }
        }

        return new TableResultResponse<TcmPkResponse>(result.getTotal(), tcmPkResponses);
    }
    
   

    /**
     * 拼接sql
     * 
     * @param query
     * @param example
     */
    public void splicingCriteria(Query query, Example example) {
        Example.Criteria criteria = example.createCriteria();
        if (query.entrySet().size() > 0) {
            for (Map.Entry<String, Object> entry : query.entrySet()) {
                if (!StringUtils.isEmpty(entry.getValue().toString())) {
                    criteria.andLike(entry.getKey(), "%" + entry.getValue().toString() + "%");
                }
            }
        }
    }
    
    /**
     * 查询公钥管理信息
     * 
     * @param query
     * @return
     */
    public List<TcmPk> queryTcmPKByPublicKey(Map<String, Object> params) {
        Example example = new Example(TcmPk.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("publicKey", params.get("publicKey"));
        // 查询公钥信息
        List<TcmPk> tcmPks = super.selectByExample(example);
        return tcmPks;
    }

}
