package com.revolution.admin.service.domain;

import com.revolution.admin.service.api.dto.PayoutDto;
import com.revolution.admin.service.api.exception.PayoutNotFoundException;
import com.revolution.admin.service.api.port.BrokerService;
import com.revolution.admin.service.api.port.OrderService;
import com.revolution.admin.service.api.port.PayoutRepository;
import com.revolution.admin.service.api.port.PayoutService;
import com.revolution.admin.service.api.port.UserService;
import com.revolution.admin.service.api.query.PayoutFilterQuery;
import com.revolution.admin.service.api.response.AdminUserResponse;
import com.revolution.admin.service.api.response.PayoutResponse;
import com.revolution.common.command.OrderCommand;
import com.revolution.common.event.EmailNotifyEvent;
import com.revolution.common.event.PayoutEvent;
import com.revolution.common.event.Topics;
import com.revolution.common.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class CorePayoutService implements PayoutService {

    private static final String ADMIN_EMAIL = "kwolny31@gmail.com";
    private static final String SUBJECT = "Revolution-22 :: Nowe zlecenie wyplaty";

    private static final String MESSAGE = """
            Witaj! <br>
            Użytkownik %s zgłosił prośbę o wypłatę z zamówienia {%s} <br>
            Lista przedmiotów: <br>
            %s
            <br>
            <br>
            Wartość zamówienia: %s <br>
            Numer konta bankowego: %s <br>
            Email: %s <br>
            <br>
            Prosimy o niezwłoczną wypłatę po weryfikacji!
            """;


    private final PayoutRepository payoutRepository;
    private final OrderService orderService;
    private final UserService userService;
    private final BrokerService brokerService;
    private final CoreMapper coreMapper;

    @Override
    public Page<PayoutResponse> getPayouts(final Pageable pageable, final PayoutFilterQuery filterQuery) {
        return payoutRepository.findAllByFilterQuery(pageable, filterQuery)
                .map(mapPayoutFromExternalService());
    }

    @Override
    public PayoutResponse changeStatus(final long orderId, final long receiverId, final boolean status) {
        PayoutDto payoutDto = payoutRepository.findByOrderIdAndReceiverId(orderId, receiverId)
                .orElseThrow(() -> new PayoutNotFoundException(orderId, receiverId));
        PayoutDto changedPayoutDto = coreMapper.changeStatus(payoutDto, status);
        return getPayoutResponse(payoutRepository.save(changedPayoutDto));
    }

    @Override
    public void handlePayout(final PayoutEvent event) {
        PayoutDto payoutDto = payoutRepository.save(
                new PayoutDto(event.bankAccountNumber(), event.orderId(), event.receiverId(), event.amount(), false)
        );

        PayoutResponse response = getPayoutResponse(payoutDto);

        brokerService.publishMessage(Topics.EMAIL_NOTIFICATION_TOPIC, new EmailNotifyEvent(ADMIN_EMAIL, SUBJECT, getMessage(response, payoutDto)));
    }

    private static String getMessage(final PayoutResponse response, final PayoutDto payoutDto) {
        String itemsFormatted = response.orderResponse().items().stream()
                .map(item -> String.format("Nazwa: %s, Cena: %.2f", item.name(), item.price()))
                .collect(Collectors.joining("<br>"));

        return MESSAGE.formatted(
                response.username(),
                payoutDto.orderId(),
                itemsFormatted,
                payoutDto.amount(),
                response.bankAccountNumber(),
                response.email()
        );
    }

    private PayoutResponse getPayoutResponse(final PayoutDto dto) {
        return payoutRepository.findByOrderIdAndReceiverId(dto.orderId(), dto.receiverId())
                .map(mapPayoutFromExternalService())
                .orElseThrow(() -> new PayoutNotFoundException(dto.orderId(), dto.receiverId()));
    }

    private Function<PayoutDto, PayoutResponse> mapPayoutFromExternalService() {
        return payout -> {
            OrderResponse orderResponse = orderService.getOrder(
                    new OrderCommand(payout.orderId(), payout.receiverId())
            );
            AdminUserResponse userResponse = userService.getUserById(payout.receiverId());
            return new PayoutResponse(
                    userResponse.email(),
                    userResponse.username(),
                    payout.bankAccountNumber(),
                    orderResponse);
        };
    }
}
