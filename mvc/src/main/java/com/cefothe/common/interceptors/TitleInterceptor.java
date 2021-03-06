package com.cefothe.common.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cefothe on 09.05.17.
 */
@Component
public class TitleInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && modelAndView.getModel() != null) {
            String title = "BGJudge - " + modelAndView.getModelMap().get("title");
            if (modelAndView.getModelMap().get("title") != null) {
                modelAndView.addObject("title", title);
            }
        }
    }
}
