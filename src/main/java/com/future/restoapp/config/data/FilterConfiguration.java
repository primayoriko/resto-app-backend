package com.demo.config.data;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

  @Bean
  public FilterRegistrationBean<CredentialFilter> credentialFilter() {
    CredentialFilter credentialFilter = new CredentialFilter();
    FilterRegistrationBean<CredentialFilter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(credentialFilter);
    filterRegistrationBean.setOrder(1);
    return filterRegistrationBean;
  }

}