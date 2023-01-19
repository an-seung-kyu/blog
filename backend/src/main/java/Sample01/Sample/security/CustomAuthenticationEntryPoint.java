package Sample01.Sample.security;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String exception = (String)request.getAttribute("exception");
        System.out.print(exception);
        /*if (exception == null) {
            setResponse(response, ExceptionCode.UNKNOWN_ERROR);
        } else {
            setResponse(response, ExceptionCode.HANDLE_ACCESS_DENIED);
        }*/
        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
        setResponse(response, ExceptionCode.HANDLE_ACCESS_DENIED);
    }

    public void setResponse(HttpServletResponse response, ExceptionCode exceptionCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject obj = new JSONObject();
        obj.put("result", false);
        obj.put("message", exceptionCode.getMessage());
        obj.put("code", exceptionCode.getCode());
        response.getWriter().print(obj);
    }
}
