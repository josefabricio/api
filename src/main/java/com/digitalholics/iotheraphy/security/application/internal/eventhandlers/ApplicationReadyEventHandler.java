package com.digitalholics.iotheraphy.security.application.internal.eventhandlers;

import com.digitalholics.iotheraphy.security.domain.services.TokenCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;

@Service
public class ApplicationReadyEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

    public ApplicationReadyEventHandler(){
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var name = event.getApplicationContext().getId();
        LOGGER.info("Starting to seed roles for {} at {}", name, new Timestamp(System.currentTimeMillis()));
        LOGGER.info("Roles seeded successfully for {} at {}", name, new Timestamp(System.currentTimeMillis()));
    }


}
