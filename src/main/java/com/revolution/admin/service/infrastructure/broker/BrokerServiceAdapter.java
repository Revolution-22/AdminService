package com.revolution.admin.service.infrastructure.broker;

import com.revolution.admin.service.api.port.BrokerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class BrokerServiceAdapter implements BrokerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publishMessage(final String topic, final Object message) {
        kafkaTemplate.send(topic, message);
    }
}
