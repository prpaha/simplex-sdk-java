package ru.prpaha.simplex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.prpaha.simplex.api.DefaultApi;
import ru.prpaha.simplex.invoker.ApiClient;

import java.text.SimpleDateFormat;

/**
 * @author Proskurin Pavel (prpaha@rambler.ru)
 */
@Configuration
@ComponentScan("ru.prpaha.simplex")
public class SimplexSDKConfiguration {

    @Value("${simplex.mainNet}")
    private Boolean mainNet;

    @Value("${simplex.apiKey}")
    private String simplexApiKey;

    @Bean
    public DefaultApi defaultApi() {
        return new DefaultApi(createApiClient());
    }

    private ApiClient createApiClient() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(mainNet ? SimplexConstants.MAIN_NET_URL : SimplexConstants.TEST_NET_URL);
        apiClient.setApiKeyPrefix(SimplexConstants.API_KEY_PREFIX);
        apiClient.setApiKey(simplexApiKey);
        apiClient.setDateFormat(new SimpleDateFormat(SimplexConstants.DATE_TIME_FORMAT));
        return apiClient;
    }

}
