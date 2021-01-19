package kf.plt.tas.adminserver.rest;

import org.springframework.beans.factory.annotation.Autowired;

import kf.plt.service.common.biz.BaseBiz;
import kf.plt.service.common.rest.BaseController;

public class BusinessController<Biz extends BaseBiz, Entity> extends BaseController{
    @Autowired
	protected Biz baseBiz;
    
    
}
