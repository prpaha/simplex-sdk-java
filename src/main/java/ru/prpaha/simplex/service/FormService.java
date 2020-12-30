package ru.prpaha.simplex.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.prpaha.simplex.SimplexConstants;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
public class FormService {

    private static final String FORM_FILE = "transaction-payment-form-auto-send.html";
    private static final String PARAM_BASE_URL = "#{form_url}";
    private static final String PARAM_PARTNER_NAME = "#{partner_name}";
    private static final String PARAM_SUCCESS_RETURN_URL = "#{success_return_url}";
    private static final String PARAM_FAIL_RETURN_URL = "#{fail_return_url}";
    private static final String PARAM_PAYMENT_ID = "#{payment_id}";
    private static final String PARAM_PLACEHOLDER = "{placeholder}";

    @Value("${simplex.mainNet}")
    private boolean mainNet;

    @Value("${simplex.partnerName}")
    private String partnerName;

    @Value("${simplex.successOperationUrl}")
    private String successOperationUrl;

    @Value("${simplex.failOperationUrl}")
    private String failOperationUrl;

    protected String getPaymentForm(final String paymentId) {
        String htmlPage = getFormAsString();
        htmlPage = htmlPage.replace(PARAM_BASE_URL, mainNet ? SimplexConstants.MAIN_NET_FORM_URL : SimplexConstants.TEST_NET_FORM_URL);
        htmlPage = htmlPage.replace(PARAM_PARTNER_NAME, partnerName);
        htmlPage = htmlPage.replace(PARAM_SUCCESS_RETURN_URL, prepareUrl(successOperationUrl, paymentId));
        htmlPage = htmlPage.replace(PARAM_FAIL_RETURN_URL, prepareUrl(failOperationUrl, paymentId));
        htmlPage = htmlPage.replace(PARAM_PAYMENT_ID, paymentId);
        return htmlPage;
    }

    private String prepareUrl(final String url, final String paymentId) {
        if (url.contains(PARAM_PLACEHOLDER)) {
            return url.replace(PARAM_PLACEHOLDER, paymentId);
        }
        return url;
    }

    private String getFormAsString() {
        InputStream resourceAsStream = Optional.ofNullable(getClass().getClassLoader()
                .getResourceAsStream(FORM_FILE)).orElseThrow();
        try {
            return IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
