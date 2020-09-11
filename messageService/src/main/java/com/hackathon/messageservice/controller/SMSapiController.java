package com.hackathon.messageservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.messageservice.data.SMSRequest;
import com.hackathon.messageservice.service.SMSapiService;

@RestController
public class SMSapiController {
	
	@Autowired
	private SMSapiService smsapiService;
	
	@Autowired
	public SMSRequest smsRequest;
	
	@GetMapping("/starsval")
	public String starsRepo(){
		try {
			smsapiService.loginInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return smsapiService.processRequest();
	}
	
	 @ResponseBody
	    @PostMapping(value = "/sms", headers = {
	            "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String sendSmsRequest(@RequestBody SMSRequest smsRequest1) {
		try {
			System.out.println("SMSapiController.sendSmsRequest()=====before====");
			smsapiService.sendSMSservice(smsRequest1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	
	@Bean
	public SMSRequest smsRequestMethod() {
		return new SMSRequest();
	}
	
	

}
