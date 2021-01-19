package kf.plt.tas.adminserver.config;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import kf.plt.service.common.filters.RequestHandlerFilter;
import kf.plt.tas.adminserver.filter.BodyReaderFilter;
import kf.plt.tas.adminserver.interceptors.TokenInterceptor;

/**
 * web 配置类 可以配置拦截器与过滤器 已配置的拦截器与过滤器不能删除
 * 
 * @author wangs 2019年5月27日
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

//    @Bean
//    public TokenInterceptor tokenInterceptor() {
//        return new TokenInterceptor();
//    }
	@Autowired
	private TokenInterceptor tokenInterceptor;

    // 配置日志记录拦截器 不能删除,所有拦截器都需要配置在它后面
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // token拦截器
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns("/v2/api-docs",
            "/user/login", "/user/getcode", "/user/busserverLogin");
    }

//    // 配置请求过滤器,不能删除
//    @Bean
//    public FilterRegistrationBean requestFilter() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        RequestHandlerFilter requestFilter = new RequestHandlerFilter();
//        registration.setFilter(requestFilter);
//        registration.addUrlPatterns("/*");
//        registration.setOrder(1);
//        return registration;
//    }

    @Bean
    public FilterRegistrationBean ReaderFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        BodyReaderFilter bodyReaderFilter = new BodyReaderFilter();
        registration.setFilter(bodyReaderFilter);
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }

    /**
     * 配置拦截器的白名单,接口文档一定配置为白名单
     * 
     * @return
     */
    private ArrayList<String> getExcludeCommonPathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {"/v2/api-docs"};
        Collections.addAll(list, urls);
        return list;
    }
}
