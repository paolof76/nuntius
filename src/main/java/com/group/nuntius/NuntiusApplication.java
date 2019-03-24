package com.group.nuntius;

import com.group.nuntius.service.InstitutionRepository;
import com.group.nuntius.service.Setup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@EnableFeignClients
@SpringBootApplication
public class NuntiusApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        System.setProperty("spring.config.location", "target/application.properties");
        ConfigurableApplicationContext context = SpringApplication.run(NuntiusApplication.class, args);

        Setup setup = new Setup(context);
        setup.createExampleInstitutions();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(NuntiusApplication.class);
    }


}



