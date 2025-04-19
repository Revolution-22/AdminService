package com.revolution.admin.service.infrastructure.broker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revolution.admin.service.api.port.PayoutService;
import com.revolution.admin.service.api.port.UserService;
import com.revolution.common.event.PayoutEvent;
import com.revolution.common.event.RegisterEvent;
import com.revolution.common.event.Topics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
class PayoutListener {

    private static final String GROUP_ID = "admin-service-group";

    private final ObjectMapper objectMapper;
    private final PayoutService payoutService;

    @KafkaListener(topics = Topics.PAYOUT_TOPIC, groupId = GROUP_ID)
    void listen(String payload) throws JsonProcessingException {
        log.info("Received RegisterEvent: {}", payload);
        PayoutEvent event = objectMapper.readValue(payload, PayoutEvent.class);
        payoutService.handlePayout(event);
    }
}
