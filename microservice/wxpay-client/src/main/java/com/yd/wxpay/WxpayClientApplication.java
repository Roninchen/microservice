package com.yd.wxpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@EnableEurekaClient
@SpringBootApplication
@Configuration
public class WxpayClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxpayClientApplication.class, args);
	}
}
