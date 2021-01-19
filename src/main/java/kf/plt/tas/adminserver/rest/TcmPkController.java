package kf.plt.tas.adminserver.rest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.service.common.util.Query;
import kf.plt.tas.adminserver.biz.TcmPkBiz;
import kf.plt.tas.adminserver.components.aop.SystemLog;
import kf.plt.tas.adminserver.entity.dataentity.TcmPk;
import kf.plt.tas.adminserver.entity.responentity.PublicKeyManageResponse;
import kf.plt.tas.adminserver.entity.responentity.TcmPkResponse;

/**
 * 
 * @author wangs
 *
 */
@RequestMapping("tcmPk")
@RestController
public class TcmPkController extends BusinessController<TcmPkBiz, TcmPk> {
	
	/**
     * 解析Excel文件对象数据
     * 
     * @param request
     * @return
     * @throws Exception
     */
	@SystemLog
    @RequestMapping(value = "/excel", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<List<PublicKeyManageResponse>> readExcelFile(HttpServletRequest request) throws Exception {
        return baseBiz.saveExcelTcmPk(request);
    }
    /**
     * 查询公钥管理信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/queryTcmPK", method = RequestMethod.POST)
    @ResponseBody
    public TableResultResponse<TcmPkResponse> queryTcmPK(@RequestBody Map<String, Object> params) {
    	Query query = new Query(params);
        return baseBiz.queryTcmPK(query);
    }
    
    /**
     * 根据公钥查询公钥管理信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/queryTcmPKByPublicKey", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Boolean> queryTcmPKByPublicKey(@RequestBody Map<String, Object> params) {
    	boolean res = false;
    	List<TcmPk> list = baseBiz.queryTcmPKByPublicKey(params);
    	if (list != null && list.size() > 0) {
			res = true;
		}
        return new ObjectRestResponse<>().data(res);
    }

}
