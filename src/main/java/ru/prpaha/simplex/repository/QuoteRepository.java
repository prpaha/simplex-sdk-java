package ru.prpaha.simplex.repository;

import com.google.gson.Gson;
import okhttp3.Call;
import ru.prpaha.simplex.api.DefaultApi;
import ru.prpaha.simplex.invoker.ApiException;
import ru.prpaha.simplex.model.GetQuoteRequest;
import ru.prpaha.simplex.model.GetQuoteResponse;

import java.io.IOException;

/**
 * @author Proskurin Pavel (prpaha@rambler.ru)
 */
public class QuoteRepository extends AbstractApiRepository {

    private final DefaultApi defaultApi;

    public QuoteRepository(DefaultApi defaultApi, Gson gson) {
        super(gson);
        this.defaultApi = defaultApi;
    }

    public GetQuoteResponse getQuote(final GetQuoteRequest request) throws ApiException {
        Call quoteCall = defaultApi.getQuoteCall(request, null);
        String respBody;
        try {
            respBody = getStringBody(quoteCall.execute());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApiException(e);
        }

        return parseObject(respBody, GetQuoteResponse.class);
    }

}
