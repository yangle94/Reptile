package cn.ylapl.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * resttemplate配置
 * date: 2018/4/29
 * time: 上午11:45
 * author: Angle
 */
@Configuration
public class RestTempConfig {

    @Bean
    public RestTemplateCustomizer restTemplateCustomizer() {

        return restTemplate -> new RestTemplateBuilder()
                .requestFactory(new OkHttp3ClientHttpRequestFactory())
                .setConnectTimeout(100)
                .setReadTimeout(200)
                .configure(restTemplate);
    }
}