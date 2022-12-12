package Filter;

import Services.CookieService;
import org.eclipse.jetty.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements HttpFilter {

    @Override
    public void doHttpFilter(HttpServletRequest rq, HttpServletResponse rs, FilterChain filterChain) throws IOException, ServletException {
        CookieService ck = new CookieService(rq, rs);
        if(!ck.isCookiePresent()){
            rs.sendRedirect("/login");
        }
        else filterChain.doFilter(rq, rs);
    }

}
