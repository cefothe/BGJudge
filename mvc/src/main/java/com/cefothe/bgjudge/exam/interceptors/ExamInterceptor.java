package com.cefothe.bgjudge.exam.interceptors;

import com.cefothe.bgjudge.exam.services.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by cefothe on 29.06.17.
 */
@Component
public class ExamInterceptor extends HandlerInterceptorAdapter {

    private final ParticipantService participantService;

    @Autowired
    public ExamInterceptor(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String examId = (String) pathVariables.get("examId");
        return false;
    }
}
