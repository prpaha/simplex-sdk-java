package ru.prpaha.simplex.repository;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import ru.prpaha.simplex.invoker.ApiException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Proskurin Pavel (prpaha@rambler.ru)
 */
@RequiredArgsConstructor
public abstract class AbstractApiRepository {

    private static final String FIELD_ERROR = "error";

    private final Gson gson;

    protected String getStringBody(Response response) throws ApiException {
        String respBody = null;
        try {
            if (response.isSuccessful() && response.body() != null) {
                respBody = response.body().string();
                checkError(respBody, response);
            } else if (response.body() != null) {
                try {
                    respBody = response.body().string();
                } catch (IOException e) {
                    throw new ApiException(response.message(), e, response.code(), response.headers().toMultimap());
                }
            } else {
                throw new ApiException(response.message(), response.code(), response.headers().toMultimap(), respBody);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApiException(e);
        }
        return respBody;
    }

    private void checkError(String respBody, Response response) throws ApiException {
        Map dataMap = gson.fromJson(respBody, HashMap.class);
        if (dataMap.containsKey(FIELD_ERROR)) {
            throw new ApiException(dataMap.get(FIELD_ERROR).toString(), response.code(),
                    response.headers().toMultimap(), respBody);
        }
    }

    protected <I> I parseObject(String body, Class<I> clazz) {
        return gson.fromJson(body, clazz);
    }

}
