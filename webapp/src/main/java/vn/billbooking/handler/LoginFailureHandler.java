package vn.billbooking.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        String redirectURL = null;
        try {
            String referrer = request.getHeader("referer");
            String username = request.getParameter("username");

            if (referrer.contains("/quan-ly")) {
                redirectURL = "/chuyen-huong/dang-nhap/that-bai?p=AD&username=" + username;
            } else {
                redirectURL = "/chuyen-huong/dang-nhap/that-bai?p=HP&username=" + username;
            }
        } catch (Exception ex) {
            redirectURL = "/dang-nhap";
        }

        super.setDefaultFailureUrl(redirectURL);
        super.onAuthenticationFailure(request, response, exception);
    }

}