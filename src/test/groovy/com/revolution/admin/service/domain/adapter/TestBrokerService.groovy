package com.revolution.admin.service.domain.adapter

import com.revolution.admin.service.api.port.BrokerService
import groovy.util.logging.Slf4j

@Slf4j
class TestBrokerService implements BrokerService {
    @Override
    void publishMessage(final String topic, final Object message) {
        log.info("Sent message {} to topic {}", message, topic)
    }
}
