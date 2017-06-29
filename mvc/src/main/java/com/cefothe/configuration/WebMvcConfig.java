package com.cefothe.configuration;

import com.cefothe.bgjudge.exam.interceptors.ExamInterceptor;
import com.cefothe.common.interceptors.TitleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by cefothe on 09.05.17.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private final TitleInterceptor titleInterceptor;
    private final ExamInterceptor examInterceptor;

    @Autowired
    public WebMvcConfig(TitleInterceptor titleInterceptor, ExamInterceptor examInterceptor) {
        this.examInterceptor = examInterceptor;
        this.titleInterceptor =titleInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.titleInterceptor).excludePathPatterns("/api/**");
        registry.addInterceptor(this.examInterceptor).addPathPatterns("/");// TODO: defined good url
    }
}