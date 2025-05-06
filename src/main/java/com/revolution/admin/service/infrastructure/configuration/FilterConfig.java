package com.revolution.admin.service.infrastructure.configuration;

import com.revolution.admin.service.infrastructure.filter.GroupFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<GroupFilter> groupFilter() {
        FilterRegistrationBean<GroupFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new GroupFilter());
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
