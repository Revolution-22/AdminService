package com.revolution.admin.service.infrastructure.configuration;

import com.revolution.admin.service.api.port.BrokerService;
import com.revolution.admin.service.api.port.OrderService;
import com.revolution.admin.service.api.port.PayoutRepository;
import com.revolution.admin.service.api.port.PayoutService;
import com.revolution.admin.service.api.port.UserRespository;
import com.revolution.admin.service.api.port.UserService;
import com.revolution.admin.service.domain.CoreBeanConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.revolution.admin.service.infrastructure.*")
class BeanConfiguration {

    private final CoreBeanConfiguration cnfiguration = new CoreBeanConfiguration();

    @Bean
    PayoutService payoutService(PayoutRepository payoutRepository, OrderService orderService, UserService userService, BrokerService brokerService) {
        return cnfiguration.payoutService(
                payoutRepository,
                orderService,
                userService,
                brokerService
        );
    }

    @Bean
    UserService userService(UserRespository userRespository) {
        return cnfiguration.userService(userRespository);
    }

}
