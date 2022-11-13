package Services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class CookieService {

    private final String USER_ID = "user_id";
    HttpServletRequest rq;
    HttpServletResponse rs;

    public CookieService(HttpServletRequest rq, HttpServletResponse rs) {
        this.rq = rq;
        this.rs = rs;
    }

    public Cookie getCookie(){
        Cookie result = null;
        Cookie[] cookies = rq.getCookies();
        if (cookies != null) {
            for(Cookie cookie : cookies){
                if(cookie.getName().equalsIgnoreCase(USER_ID)){
                    result = cookie;
                }
            }
        }
        return result;
    }

    public void addCookie(int id){
        rs.addCookie(new Cookie(USER_ID, String.valueOf(id)));
    }

    public void removeCookie(){
        Arrays.stream(rq.getCookies())
                        .filter(cookie -> cookie.getName().equalsIgnoreCase(USER_ID))
                        .map(cookie -> new Cookie(cookie.getName(), cookie.getValue()){{
                            cookie.setMaxAge(0);
                        }}).forEach(cookie -> rs.addCookie(cookie));
    }
}
