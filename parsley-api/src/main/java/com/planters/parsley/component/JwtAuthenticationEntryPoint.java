
package com.planters.parsley.component;

import com.planters.parsley.constant.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        setResponse(response, ResponseCode.UNAUTHORIZED_ERROR);
    }

    private void setResponse(HttpServletResponse response, ResponseCode responseCode) throws IOException {

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);


        JSONObject responseJson = new JSONObject();
        responseJson.put("message", responseCode.getMessage());
        responseJson.put("code", responseCode.getCode());

        response.getWriter().print(responseJson);
    }
}