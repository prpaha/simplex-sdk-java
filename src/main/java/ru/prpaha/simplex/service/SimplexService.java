package ru.prpaha.simplex.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.prpaha.simplex.api.DefaultApi;
import ru.prpaha.simplex.invoker.ApiException;
import ru.prpaha.simplex.model.GetQuoteRequest;
import ru.prpaha.simplex.model.GetQuoteResponse;
import ru.prpaha.simplex.model.PaymentRequest;
import ru.prpaha.simplex.model.PaymentResponse;

/**
 * @author Proskurin Pavel (prpaha@rambler.ru)
 */
@Component
@AllArgsConstructor
public class SimplexService {

    private final DefaultApi defaultApi;

    @Value("${simplex.walletId}")
    private String walletId;

    public GetQuoteResponse createQuote(GetQuoteRequest request) throws ApiException {
        fillWalletId(request);
        return defaultApi.getQuote(request);
    }

    public PaymentResponse createPayment(PaymentRequest request) throws ApiException {
        return defaultApi.paymentRequest(request);
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
