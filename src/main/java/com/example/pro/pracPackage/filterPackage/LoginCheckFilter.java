package com.example.pro.pracPackage.filterPackage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.classify.PatternMatcher;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {
    private static final String[] whiteList = {"/", "/member/add", "/login", "/login/*", "/css/*","/index"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        String requestURI = request1.getRequestURI();

        HttpServletResponse response1 = (HttpServletResponse) response;
        try {
            log.info("필터 시작 체크 {}", requestURI);

            if (isLoginPathCheck(requestURI)) {
                log.info("로그인 여부 체크 시작 {}", requestURI);
                HttpSession session = request1.getSession();
                if (session == null) {
                    log.info("미인증 사용자 요청 {}", requestURI);
                    response1.sendRedirect("/login?redirectURL=" + requestURI);
                    return;
                }
            }
            chain.doFilter(request1, response1);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("인증 체크 로직 종료 {}", requestURI);
        }
        /**
         * 로그인 없이 접근가능한 경로 -> whiteList
         */
    }

    private boolean isLoginPathCheck(String requestUri) {
        return !PatternMatchUtils.simpleMatch(whiteList, requestUri);
    }
}
