package com.joshuaism;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.joshuaism.response.MarsRoverApiResponse;

public class MarsRoverApiTest {
	
	@Test
	public void Test() {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<MarsRoverApiResponse> response = rt.getForEntity("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=2&api_key=DEMO_KEY", MarsRoverApiResponse.class);
		System.out.println(response.getBody());
	}

}
