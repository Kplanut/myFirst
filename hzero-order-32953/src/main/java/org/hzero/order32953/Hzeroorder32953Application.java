package org.hzero.order32953;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.choerodon.resource.annoation.EnableChoerodonResourceServer;

@EnableChoerodonResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class Hzeroorder32953Application {

    public static void main(String[] args) {
        SpringApplication.run(Hzeroorder32953Application.class, args);
    }
}


