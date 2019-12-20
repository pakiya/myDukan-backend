package com.universal.search.config;

import com.github.vanroy.springboot.autoconfigure.data.jest.ElasticsearchJestProperties;
import com.google.common.base.Supplier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.universal.constants.CommonConstants;
import com.universal.search.service.halper.DateTimeTypeConverter;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Configuration
@EnableConfigurationProperties({ElasticsearchJestProperties.class, ElasticsearchProperties.class})
@Profile(CommonConstants.DEFAULT_PROFILE)
public class ElasticSearchConfiguration {

    /**
     * Elastic search Jest.
     */
    @Autowired
    ElasticsearchJestProperties properties;

    private static final Supplier<LocalDateTime> CLOCK = () -> LocalDateTime.now(ZoneOffset.UTC);

    /**
     * Gson for Jest cline
     * @return
     */
    Gson gson(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateTimeTypeConverter()).create();
        return gson;
    }

    /**
     * Adding Jest cline in the Beam.
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(JestClient.class)
    @ConditionalOnMissingClass("org.elasticsearch.node.Node")
    public JestClient client() {
        return createJestClient(properties.getUri());
    }

    private JestClient createJestClient(String uri) {

        HttpClientConfig.Builder builder = new HttpClientConfig.Builder(uri)
                .maxTotalConnection(properties.getMaxTotalConnection())
                .defaultMaxTotalConnectionPerRoute(properties.getDefaultMaxTotalConnectionPerRoute())
                .readTimeout(properties.getReadTimeout()).gson(gson())
                .multiThreaded(properties.getMultiThreaded());

        if (StringUtils.hasText(this.properties.getUsername())) {
            builder.defaultCredentials(this.properties.getUsername(), this.properties.getPassword());
        }
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(builder.build());
        return factory.getObject();
    }

}
