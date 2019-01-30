package com.cg.api.ZuulAPIBankApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.cg.api.ZuulAPIBankApp.filters.ErrorFilter;
import com.cg.api.ZuulAPIBankApp.filters.PostFilter;
import com.cg.api.ZuulAPIBankApp.filters.PreFilter;
import com.cg.api.ZuulAPIBankApp.filters.RouteFilter;
@EnableZuulProxy
@SpringBootApplication
public class ZuulApiBankAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApiBankAppApplication.class, args);
	}

	@Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }
    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }
    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
}
}

