package kf.plt.tas.adminserver.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import kf.plt.service.common.filters.BaseFilter;
import kf.plt.tas.adminserver.utils.BodyReaderHttpServletRequestWrapper;

import java.io.IOException;

@WebFilter(filterName = "bodyReaderFilter", urlPatterns = "/*")
public class BodyReaderFilter extends BaseFilter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 防止流读取一次后就没有了, 所以需要将流继续写出去
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
        filterChain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void destroy() {
    }

}