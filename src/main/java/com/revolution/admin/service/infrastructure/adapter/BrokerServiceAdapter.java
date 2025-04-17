package com.revolution.admin.service.infrastructure.adapter;

import com.revolution.admin.service.api.port.BrokerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrokerServiceAdapter implements BrokerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publishMessage(final String topic, final Object message) {
        kafkaTemplate.send(topic, message);
    }
}
