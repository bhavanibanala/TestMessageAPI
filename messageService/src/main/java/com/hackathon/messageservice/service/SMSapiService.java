package com.hackathon.messageservice.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.hackathon.messageservice.data.SMSRequest;

@Component
public class SMSapiService {

	public String processRequest() {
		return "success";
	}
	
	@Autowired
	RestTemplate restTemplate;
	public Map loginInfo() throws Exception{
		String oauthURL= "https://tapi.telstra.com/v2/oauth/token";
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(oauthURL);
		HttpHeaders headers= new HttpHeaders();
		headers.add("content_type", "application/x-www-form-urlencoded");
		
		MultiValueMap<String, String> requestBody= new LinkedMultiValueMap<String, String>();
		
		requestBody.add("client_id", "Am4smBkEUm6mVcWGG7DuAdOU9U2XdQQc");
		requestBody.add("client_secret", "58sn42VBFVOxVfBy");
		requestBody.add("grant_type", "client_credentials");
		requestBody.add("scope", "NSMS");
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);
		ResponseEntity<Map> response= restTemplate.exchange(uriBuilder.toUriString(),HttpMethod.POST , entity, Map.class);
		//System.out.println("MessagingApiService.loginInfo() response===="+response.getBody());
		return response.getBody();
	}
	
	public Map sendSMSservice(SMSRequest smsRequest) throws Exception {
		 Map<?, ?> loginData = loginInfo();
		 
		// System.out.println("MessagingApiService.processRequest() ====loginData===="+loginData.get("access_token").toString());
		
		 String token = (String) loginData.get("access_token");
		 String tokenType = (String) loginData.get("token_type");
		 String smsURL= "https://tapi.telstra.com/v2/messages/sms";
			
		 UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(smsURL);
		 HttpHeaders headers= new HttpHeaders();
		 headers.add("content_type", MediaType.APPLICATION_JSON_VALUE);
		 headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		 headers.add("Authorization", tokenType+" "+token);
		 HttpEntity<Object> entity = new HttpEntity<Object>(smsRequest, headers);
		 ResponseEntity<Map> response= restTemplate.exchange(uriBuilder.toUriString(),HttpMethod.POST , entity, Map.class);
		System.out.println("SMSapiService.sendSMSservice()===="+response.getBody());	
		 
		 return response.getBody();
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}
