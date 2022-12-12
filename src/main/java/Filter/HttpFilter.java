package Filter;

import Services.CookieService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HttpFilter extends Filter {

    default boolean isHttp(ServletRequest servletRequest,
                          ServletResponse servletResponse){
        return servletRequest instanceof HttpServletRequest
                && servletResponse instanceof HttpServletResponse;
    }

    @Override
    default void init(FilterConfig filterConfig) throws ServletException { }

    void doHttpFilter(HttpServletRequest httpServletRequest,
                      HttpServletResponse httpServletResponse,
                      FilterChain filterChain) throws IOException, ServletException;


    @Override
    default void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(isHttp(servletRequest, servletResponse)){
            HttpServletRequest rq = (HttpServletRequest) servletRequest;
            HttpServletResponse rs = (HttpServletResponse) servletResponse;

            doHttpFilter(rq, rs, filterChain);
        }
        else filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    default void destroy() {}
}
