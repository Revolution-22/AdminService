package com.revolution.admin.service.domain;

import com.revolution.admin.service.api.port.BrokerService;
import com.revolution.admin.service.api.port.OrderService;
import com.revolution.admin.service.api.port.PayoutRepository;
import com.revolution.admin.service.api.port.PayoutService;
import com.revolution.admin.service.api.port.UserRespository;
import com.revolution.admin.service.api.port.UserService;

public class CoreBeanConfiguration {

    private CoreMapper coreMapper = new CoreMapper();

    public PayoutService payoutService(PayoutRepository payoutRepository, OrderService orderService, UserService userService, BrokerService brokerService) {
        return new CorePayoutService(payoutRepository, orderService, userService, brokerService, coreMapper);
    }

    public UserService userService(UserRespository userRespository) {
        return new CoreUserService(userRespository, coreMapper);
    }
}
