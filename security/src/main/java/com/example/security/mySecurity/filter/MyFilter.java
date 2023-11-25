package com.example.security.mySecurity.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.rmi.UnknownHostException;
import java.util.Arrays;
import java.util.Base64;

@Slf4j
public class MyFilter implements Filter {
    private static final String loginUrl = "https://www.test.test";
    private static final String userName = "testUser";
    private static final String password = "1q2w3e4r!";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (!loginUrl.equals(request.getRequestURI())) {
            throw new UnknownHostException("url error");
        }
        String tokenValue = request.getHeader("Authorization");
        if (tokenValue.startsWith("Bearer ")) {
            String userInfo = tokenValue.split("Bearer ")[1];
            String[] datas = Arrays.toString(Base64.getDecoder().decode(userInfo)).split(":");
            String requestUserInfo = datas[0];
            String requestUserPassword = datas[1];

            if (requestUserInfo.equals(userName) && requestUserPassword.equals(password)) {
                // TODO: 2023/11/22 ??????
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            log.warn("로그인 실패");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
