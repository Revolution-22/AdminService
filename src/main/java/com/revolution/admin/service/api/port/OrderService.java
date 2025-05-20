package com.revolution.admin.service.api.port;

import com.revolution.common.command.OrderCommand;
import com.revolution.common.response.OrderResponse;

public interface OrderService {
    OrderResponse getOrder(OrderCommand orderCommand);
}
