package ru.prpaha.simplex.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.prpaha.simplex.invoker.ApiException;
import ru.prpaha.simplex.model.Event;
import ru.prpaha.simplex.model.Events;
import ru.prpaha.simplex.model.GetQuoteRequest;
import ru.prpaha.simplex.model.GetQuoteResponse;
import ru.prpaha.simplex.model.PaymentRequest;
import ru.prpaha.simplex.model.PaymentResponse;
import ru.prpaha.simplex.repository.EventRepository;
import ru.prpaha.simplex.repository.PaymentRepository;
import ru.prpaha.simplex.repository.QuoteRepository;

/**
 * @author Proskurin Pavel (prpaha@rambler.ru)
 */
@Component
@RequiredArgsConstructor
public class SimplexService {

    private final QuoteRepository quoteRepository;
    private final PaymentRepository paymentRepository;
    private final FormService formService;
    private final EventRepository eventRepository;

    @Value("${simplex.walletId}")
    private String walletId;

    @Value("${simplex.partnerName}")
    private String partnerName;

    @Value("${simplex.partnerUrl}")
    private String partnerUrl;

    public void deleteEvent(final Event event) throws ApiException {
        eventRepository.deleteEvent(event);
    }

    public Events getEvents() throws ApiException {
        return eventRepository.getEvents();
    }

    public String getPaymentForm(final String paymentId) {
        return formService.getPaymentForm(paymentId);
    }

    public GetQuoteResponse createQuote(GetQuoteRequest request) throws ApiException {
        fillWalletId(request);
        return quoteRepository.getQuote(request);
    }

    public PaymentResponse createPayment(PaymentRequest request) throws ApiException {
        fillPartnerData(request);
        return paymentRepository.createPayment(request);
    }

    private void fillPartnerData(PaymentRequest request) {
        if (request.getAccountDetails() != null &&
                StringUtils.isBlank(request.getAccountDetails().getAppProviderId())) {
            request.getAccountDetails().appProviderId(partnerName);
        }
        if (request.getTransactionDetails() != null &&
                request.getTransactionDetails().getPaymentDetails() != null &&
                StringUtils.isBlank(request.getTransactionDetails().getPaymentDetails().getOriginalHttpRefUrl())) {
            request.getTransactionDetails().getPaymentDetails().setOriginalHttpRefUrl(partnerUrl);
        }
    }

    private void fillWalletId(GetQuoteRequest request) {
        if ("empty".equals(walletId)) {
            return;
        }
        if (StringUtils.isBlank(request.getWalletId())) {
            request.setWalletId(walletId);
        }
    }

}
