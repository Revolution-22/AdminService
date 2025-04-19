package com.revolution.admin.service.api.controller;

import com.revolution.admin.service.api.port.PayoutService;
import com.revolution.admin.service.api.query.PayoutFilterQuery;
import com.revolution.admin.service.api.response.PayoutResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/payouts")
@RequiredArgsConstructor
class PayoutController {

    private final PayoutService payoutService;

    @GetMapping
    Page<PayoutResponse> getPayouts(Pageable pageable, @ModelAttribute  PayoutFilterQuery filterQuery) {
        return payoutService.getPayouts(pageable, filterQuery);
    }

    @PutMapping
    PayoutResponse changeStatus(@RequestParam long orderId, @RequestParam long receiverId, @RequestParam boolean status) {
        return payoutService.changeStatus(orderId, receiverId, status);
    }
}
