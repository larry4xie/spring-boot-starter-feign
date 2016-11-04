package xyz.lxie.feign.springboot;

import com.netflix.hystrix.HystrixCommand;
import feign.Client;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.httpclient.ApacheHttpClient;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Feign AutoConfiguration
 *
 * @author xieg
 * @since 2016/11/3
 */
@Configuration
@ConditionalOnClass(Feign.class)
public class FeignAutoConfiguration {
    @Configuration
    @ConditionalOnClass({ HystrixCommand.class, HystrixFeign.class })
    protected static class HystrixFeignConfiguration {
        @Bean
        @Scope("prototype")
        @ConditionalOnMissingBean
        @ConditionalOnProperty(name = "feign.hystrix.enabled", matchIfMissing = true)
        public Feign.Builder feignHystrixBuilder() {
            return HystrixFeign.builder();
        }
    }

    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }

    @Configuration
    @ConditionalOnClass({ JacksonDecoder.class, JacksonEncoder.class })
    protected static class JacksonFeignConfiguration {
        @Bean
        @ConditionalOnMissingBean
        public Decoder feignDecoder() {
            return new JacksonDecoder();
        }

        @Bean
        @ConditionalOnMissingBean
        public Encoder feignEncoder() {
            return new JacksonEncoder();
        }
    }

    @Configuration
    @ConditionalOnClass(ApacheHttpClient.class)
    @ConditionalOnProperty(value = "feign.httpclient.enabled", matchIfMissing = true)
    protected static class HttpClientFeignConfiguration {
        @Autowired(required = false)
        private HttpClient httpClient;

        @Bean
        @ConditionalOnMissingBean(Client.class)
        public Client feignClient() {
            if (this.httpClient != null) {
                return new ApacheHttpClient(this.httpClient);
            }
            return new ApacheHttpClient();
        }
    }

    @Configuration
    @ConditionalOnClass(okhttp3.OkHttpClient.class)
    @ConditionalOnProperty(value = "feign.okhttp.enabled", matchIfMissing = true)
    protected static class OkHttpFeignConfiguration {
        @Autowired(required = false)
        private okhttp3.OkHttpClient okHttpClient;

        @Bean
        @ConditionalOnMissingBean(Client.class)
        public Client feignClient() {
            if (this.okHttpClient != null) {
                return new OkHttpClient(this.okHttpClient);
            }
            return new OkHttpClient();
        }
    }
}
