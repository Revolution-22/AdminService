package com.revolution.admin.service.domain.adapter

import com.revolution.admin.service.api.port.OrderService
import com.revolution.admin.service.domain.Constants
import com.revolution.common.command.OrderCommand
import com.revolution.common.response.LineItemResponse
import com.revolution.common.response.OrderResponse

class TestOrderService implements OrderService, Constants {

    @Override
    OrderResponse getOrder(final OrderCommand orderCommand) {
        new OrderResponse(
                1L,
                1L,
                List.of(
                        new LineItemResponse(
                                ITEM_NAME,
                                BigDecimal.ONE
                        )
                )
        )
    }
}
