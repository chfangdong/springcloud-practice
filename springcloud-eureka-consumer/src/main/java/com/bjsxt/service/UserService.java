package com.bjsxt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bjsxt.pojo.User;

@Service
public class UserService {
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	public List<User> getUsers() {
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("eureka-provider");
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("http://").append(serviceInstance.getHost()).append(":").append(serviceInstance.getPort()).append("/users");
		
		//springMVC restTemplate
		RestTemplate restTemplate = new RestTemplate();
		ParameterizedTypeReference<List<User>> typeReference = new ParameterizedTypeReference<List<User>>() {};
		
		ResponseEntity<List<User>> response = restTemplate.exchange(stringBuffer.toString(), HttpMethod.GET, null, typeReference);
		List<User> list = response.getBody();
		return list;
	}
}
