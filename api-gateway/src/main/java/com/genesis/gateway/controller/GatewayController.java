package com.genesis.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class GatewayController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GatewayController.class);
	private static final String EMPLOYEE_SERVICE_ENDPOINT = "http://employee-service/employee/";
	private static final String FALLBACK_RESPONSE = "Service Error :: Request cannot be processed temporarily";
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/employeeDetails/{employeeId}")
	@HystrixCommand(fallbackMethod = "fallbackMethod")
	public String getStudents(@PathVariable("employeeId") int id) {
		LOGGER.info("fetching employee details for id : {}", id);

		String response = restTemplate.exchange(EMPLOYEE_SERVICE_ENDPOINT + "{employeeId}", HttpMethod.GET, null,
				new ParameterizedTypeReference<String>() {
		}, id).getBody();
		LOGGER.info("Response Body :: {} ", response);
		return "{Employee-id : " + response + "}";
	}

	public String fallbackMethod() {
		return FALLBACK_RESPONSE;
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
