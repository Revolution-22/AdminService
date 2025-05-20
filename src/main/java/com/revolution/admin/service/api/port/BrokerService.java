package com.revolution.admin.service.api.port;

public interface BrokerService {

    void publishMessage(String topic, Object message);
}
