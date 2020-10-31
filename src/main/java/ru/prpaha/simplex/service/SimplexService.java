package ru.prpaha.simplex.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.prpaha.simplex.api.DefaultApi;
import ru.prpaha.simplex.invoker.ApiException;
import ru.prpaha.simplex.model.GetQuoteRequest;
import ru.prpaha.simplex.model.GetQuoteResponse;

/**
 * @author Proskurin Pavel (prpaha@rambler.ru)
 */
@Component
@AllArgsConstructor
public class SimplexService {

    private final DefaultApi defaultApi;

    public GetQuoteResponse createQuote(GetQuoteRequest request) throws ApiException {
        return defaultApi.getQuote(request);
    }

}
