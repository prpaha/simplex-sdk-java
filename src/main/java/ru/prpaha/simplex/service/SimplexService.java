package ru.prpaha.simplex.service;

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
public class SimplexService {

    private final DefaultApi defaultApi;

    @Value("${simplex.walletId}")
    private String walletId;

    @Value("${simplex.partnerName}")
    private String partnerName;

    @Value("${simplex.partnerUrl}")
    private String partnerUrl;

    public SimplexService(DefaultApi defaultApi) {
        this.defaultApi = defaultApi;
    }

    public GetQuoteResponse createQuote(GetQuoteRequest request) throws ApiException {
        fillWalletId(request);
        return defaultApi.getQuote(request);
    }

    public PaymentResponse createPayment(PaymentRequest request) throws ApiException {
        fillPartnerData(request);
        return defaultApi.paymentRequest(request);
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
