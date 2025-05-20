package com.revolution.admin.service.domain.adapter

import com.revolution.admin.service.api.port.PayoutRepository
import com.revolution.admin.service.api.port.PayoutService
import com.revolution.admin.service.api.port.UserRespository
import com.revolution.admin.service.api.port.UserService
import com.revolution.admin.service.domain.CoreBeanConfiguration

class TestBeanConfiguration {

    private CoreBeanConfiguration configuration = new CoreBeanConfiguration()

    private UserRespository userRepository = new TestUserRepository()
    private PayoutRepository payoutRepository = new TestPayoutRepository(userRepository)

    PayoutService payoutService() {
        configuration.payoutService(payoutRepository, new TestOrderService(), userService(), new TestBrokerService())
    }

    UserService userService() {
        configuration.userService(userRepository)
    }

    def clear() {
        userRepository.database.clear()
        payoutRepository.database.clear()
    }
}
