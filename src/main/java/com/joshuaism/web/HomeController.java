package com.joshuaism.web;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joshuaism.dto.HomeDto;
import com.joshuaism.response.MarsRoverApiResponse;
import com.joshuaism.service.MarsRoverApiService;

@Controller
public class HomeController {
	
	@Autowired
	private MarsRoverApiService roverService;
	
	@GetMapping("/")
	public String getHomeView(ModelMap model, Long userId) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		HomeDto homeDto = roverService.findByUserId(userId);
		MarsRoverApiResponse roverData = roverService.getRoverData(homeDto);
		model.put("roverData", roverData);

		if (homeDto == null) {
			homeDto = new HomeDto();
		}
		model.put("homeDto", homeDto);
		model.put("validCameras", roverService.getValidCameras().get(homeDto.getMarsApiRoverData()));
		return "index";
	}
	
	@GetMapping("/savedPreferences")
	@ResponseBody
	public HomeDto getSavedPreferences(Long userId) {
		if (userId != null) {
			return roverService.findByUserId(userId);
		}
		return new HomeDto();
	}
	
	@PostMapping("/")
	public String postHomeView(HomeDto homeDto) {
		homeDto = roverService.save(homeDto);
		return "redirect:/?userId="+homeDto.getUserId();
	}

}
