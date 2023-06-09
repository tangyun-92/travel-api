package com.tang.travel;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

@ComponentScan("com.tang")
@SpringBootApplication
@MapperScan("com.tang.travel.model.dao")
public class TravelApplication {

    private static final Logger LOG = LoggerFactory.getLogger(TravelApplication.class);

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(TravelApplication.class);
        ConfigurableEnvironment env = app.run(args).getEnvironment();
        LOG.info("启动成功！！！");
        LOG.info("地址：\thttp://127.0.0.1:{}", env.getProperty("server.port"));

    }

}
