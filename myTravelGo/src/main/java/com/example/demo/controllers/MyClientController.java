package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyClientController {

	@Autowired
	private RestTemplate template;
	
	@Autowired
	private LoadBalancerClient client;
	
	@GetMapping("/find/{location}")
	public String getHotels(@PathVariable("location") String location) {
		
		
		ServiceInstance serviceInstance = client.choose("hotel-service");
		
		String baseURL = serviceInstance.getUri().toString();
		
		String updated = baseURL+"/find/"+location;
		
		String response = template.getForObject(updated, String.class);
		
		
		return response;
	}
}
