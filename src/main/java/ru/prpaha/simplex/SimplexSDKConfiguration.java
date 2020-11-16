package ru.prpaha.simplex;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import ru.prpaha.simplex.api.DefaultApi;
import ru.prpaha.simplex.invoker.ApiClient;

import java.text.SimpleDateFormat;

/**
 * @author Proskurin Pavel (prpaha@rambler.ru)
 */
@Configuration
@ComponentScan("ru.prpaha.simplex")
@PropertySource("classpath:simplex-sdk.yml")
@AllArgsConstructor
public class SimplexSDKConfiguration {

    private final Environment env;

    @Bean
    public DefaultApi defaultApi() {
        return new DefaultApi(createApiClient());
    }

    private ApiClient createApiClient() {
        boolean mainNet = Boolean.getBoolean(env.getProperty("simplex.mainNet"));
        String simplexApiKey = env.getProperty("simplex.apiKey");

        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(mainNet ? SimplexConstants.MAIN_NET_URL : SimplexConstants.TEST_NET_URL);
        apiClient.setApiKeyPrefix(SimplexConstants.API_KEY_PREFIX);
        apiClient.setApiKey(simplexApiKey);
        apiClient.setDateFormat(new SimpleDateFormat(SimplexConstants.DATE_TIME_FORMAT));
        return apiClient;
    }

}
