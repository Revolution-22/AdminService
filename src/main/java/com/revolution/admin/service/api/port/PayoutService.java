package com.revolution.admin.service.api.port;

import com.revolution.admin.service.api.query.PayoutFilterQuery;
import com.revolution.admin.service.api.response.PayoutResponse;
import com.revolution.common.event.PayoutEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PayoutService {

    Page<PayoutResponse> getPayouts(Pageable pageable, PayoutFilterQuery filterQuery);

    void handlePayout(PayoutEvent event);
}
