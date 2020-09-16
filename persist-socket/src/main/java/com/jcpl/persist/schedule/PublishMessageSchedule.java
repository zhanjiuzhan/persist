package com.jcpl.persist.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public class PublishMessageSchedule {

    private final static Logger logger = LoggerFactory.getLogger(PublishMessageSchedule.class);

    @Scheduled(fixedDelay = 100)
    public void execute() {
        // TODO 暂时无用
    }
}
