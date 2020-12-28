package ru.prpaha.simplex.repository;

import com.google.gson.Gson;
import okhttp3.Call;
import ru.prpaha.simplex.api.DefaultApi;
import ru.prpaha.simplex.invoker.ApiException;
import ru.prpaha.simplex.model.PaymentRequest;
import ru.prpaha.simplex.model.PaymentResponse;

import java.io.IOException;

/**
 * @author Proskurin Pavel (prpaha@rambler.ru)
 */
public class PaymentRepository extends AbstractApiRepository {

    private final DefaultApi defaultApi;

    public PaymentRepository(DefaultApi defaultApi, Gson gson) {
        super(gson);
        this.defaultApi = defaultApi;
    }

    public PaymentResponse createPayment(PaymentRequest request) throws ApiException {
        Call quoteCall = defaultApi.paymentRequestCall(request, null);
        String respBody;
        try {
            respBody = getStringBody(quoteCall.execute());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApiException(e);
        }

        return parseObject(respBody, PaymentResponse.class);
    }
}
