package io.robbinespu.ess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EssApplication {

    private static final Logger LOG = LoggerFactory.getLogger(EssApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EssApplication.class, args);
        LOG.debug("<------------------- SYSTEM IS UP! ------------------->");
    }

}
