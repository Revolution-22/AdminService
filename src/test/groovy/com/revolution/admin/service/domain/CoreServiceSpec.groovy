package com.revolution.admin.service.domain

import com.revolution.admin.service.api.exception.UserNotFoundException
import com.revolution.admin.service.api.port.PayoutService
import com.revolution.admin.service.api.port.UserService
import com.revolution.admin.service.api.query.PayoutFilterQuery
import com.revolution.admin.service.api.response.AdminUserResponse
import com.revolution.admin.service.api.response.PayoutResponse
import com.revolution.admin.service.domain.adapter.TestBeanConfiguration
import com.revolution.common.event.PayoutEvent
import com.revolution.common.event.RegisterEvent
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import spock.lang.Specification
import spock.lang.Subject

class CoreServiceSpec extends Specification implements Constants {

    private TestBeanConfiguration configuration = new TestBeanConfiguration()

    @Subject
    private PayoutService payoutService = configuration.payoutService()

    @Subject
    private UserService userService = configuration.userService()

    def setup() {
        configuration.clear()
    }

    def "should get empty list of payouts because not handle anything before" () {
        when: "Get payouts"
            Page<PayoutResponse> responsePage = payoutService.getPayouts(PageRequest.of(0, 10), new PayoutFilterQuery(null, null))
        then: "Check if page is empty"
            responsePage.isEmpty()
    }

    def "should get unpaid payouts" () {
        given: "Handle create user"
            userService.handleUserRegister(new RegisterEvent(1L, USERNAME, EMAIL))
        and: "Handle create payout"
            payoutService.handlePayout(new PayoutEvent(BANK_ACCOUNT_NUMBER, 1L, 1L, BigDecimal.ONE))
        when: "Find unpaid payouts"
            Page<PayoutResponse> responsePage = payoutService.getPayouts(PageRequest.of(0, 10), new PayoutFilterQuery(null, false))
        then: "Check if page contains elements"
            !responsePage.isEmpty()
    }

    def "should not get payouts because user not found" () {
        when: "Handle create payout"
            payoutService.handlePayout(new PayoutEvent(BANK_ACCOUNT_NUMBER, 1L, 1L, BigDecimal.ONE))
        then: "Check if thrown error"
            thrown(UserNotFoundException)
    }

    def "should get paid payout and dedicated to selected user" () {
        given: "Handle create user"
            userService.handleUserRegister(new RegisterEvent(1L, USERNAME, EMAIL))
        and: "Handle create payout"
            payoutService.handlePayout(new PayoutEvent(BANK_ACCOUNT_NUMBER, 1L, 1L, BigDecimal.ONE))
        and: "Mark payout as paid"
            payoutService.changeStatus(1L, 1l, true)
        when: "Find unpaid payouts"
            Page<PayoutResponse> responsePage = payoutService.getPayouts(PageRequest.of(0, 10), new PayoutFilterQuery(USERNAME, true))
        then: "Check if page contains elements"
            !responsePage.isEmpty()
    }

    def "should change user status" () {
        given: "Handle create user"
            userService.handleUserRegister(new RegisterEvent(1L, USERNAME, EMAIL))
        and: "Block user"
            userService.changeBlockStatus(1L, true)
        when: "Get user"
            AdminUserResponse response = userService.getUserById(1L)
        then: "Check if is blocked"
            response.isBlocked()
    }

    def "should not change user status because user not exists" () {
        when: "Block user"
            userService.getUserById(1L)
        then: "Check if thrown error"
            thrown(UserNotFoundException)
    }

}
