package com.example.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) {
        LOGGER.error("システムエラーが発生しました", e);
        return null;
    }
}
