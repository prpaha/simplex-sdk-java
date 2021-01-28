package ru.prpaha.simplex;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.prpaha.simplex.api.DefaultApi;
import ru.prpaha.simplex.invoker.ApiClient;
import ru.prpaha.simplex.repository.EventRepository;
import ru.prpaha.simplex.repository.PaymentRepository;
import ru.prpaha.simplex.repository.QuoteRepository;

import java.text.SimpleDateFormat;

/**
 * @author Proskurin Pavel (prpaha@rambler.ru)
 */
@Configuration
@ComponentScan("ru.prpaha.simplex")
public class SimplexSDKConfiguration {

    @Value("${simplex.mainNet}")
    private boolean mainNet;

    @Value("${simplex.apiKey}")
    private String simplexApiKey;

    @Value("${simplex.debugging:false}")
    private boolean debugging;

    @Bean
    public DefaultApi defaultApi() {
        return new DefaultApi(createApiClient());
    }

    @Bean
    public QuoteRepository quoteRepository(DefaultApi defaultApi, Gson gson) {
        return new QuoteRepository(defaultApi, gson);
    }

    @Bean
    public PaymentRepository paymentRepository(DefaultApi defaultApi, Gson gson) {
        return new PaymentRepository(defaultApi, gson);
    }

    @Bean
    public EventRepository eventRepository(DefaultApi defaultApi, Gson gson) {
        return new EventRepository(defaultApi, gson);
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().setDateFormat(SimplexConstants.DATE_TIME_FORMAT).create();
    }

    private ApiClient createApiClient() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(mainNet ? SimplexConstants.MAIN_NET_URL : SimplexConstants.TEST_NET_URL);
        apiClient.setApiKeyPrefix(SimplexConstants.API_KEY_PREFIX);
        apiClient.setApiKey(simplexApiKey);
        apiClient.setDateFormat(new SimpleDateFormat(SimplexConstants.DATE_TIME_FORMAT));
        apiClient.setDebugging(debugging);
        return apiClient;
    }

}
