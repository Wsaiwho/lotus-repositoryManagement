package kf.plt.tas.adminserver.biz;

import kf.plt.service.common.biz.BusinessBiz;
import kf.plt.service.common.mapper.CommonMapper;

/**
 * 可信基础业务层
 * @author wangs
 *
 * @param <M>
 * @param <T>
 */
public abstract class KxBusinessBiz<M extends CommonMapper<T>, T>  extends BusinessBiz<M, T>  {

}
