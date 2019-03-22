package io.spring2go.hystrixlab.schoolservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * Common Bean config
 *
 * @author wangqingsong
 */
@Configuration
public class CommonConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
