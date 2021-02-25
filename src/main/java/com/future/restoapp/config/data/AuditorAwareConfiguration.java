package com.future.restoapp.config.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditorAwareConfiguration {

	@Bean
	public AuditorAwareData auditorAwareData() throws Exception {
		return new AuditorAwareData();
	}

}
