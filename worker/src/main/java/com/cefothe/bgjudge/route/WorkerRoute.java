package com.cefothe.bgjudge.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 6/8/17.
 */
@Component
public class WorkerRoute extends RouteBuilder {

    @Value("${worker.endpoint.input}")
    private String workerEndpoint;

    @Override
    public void configure() throws Exception {
        from(workerEndpoint).log("${body}");
    }
}
