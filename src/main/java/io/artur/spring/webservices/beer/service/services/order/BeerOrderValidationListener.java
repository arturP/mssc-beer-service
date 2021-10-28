package io.artur.spring.webservices.beer.service.services.order;

import io.artur.spring.webservices.beer.service.config.JmsConfig;
import io.artur.spring.webservices.brewery.model.events.ValidateOrderRequest;
import io.artur.spring.webservices.brewery.model.events.ValidateOrderResult;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 *
 */
@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private final BeerOrderValidator validator;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateOrderRequest request) {
        Boolean isValid = validator.validate(request.getBeerOrder());

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE,
                ValidateOrderResult.builder()
                        .orderId(request.getBeerOrder().getId())
                        .isValid(isValid)
                        .build());
    }
}
