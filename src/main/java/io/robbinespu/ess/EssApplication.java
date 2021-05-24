/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.EssApplication
 * Last modified:  5/22/21, 3:40 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

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
