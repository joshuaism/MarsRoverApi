package com.joshuaism.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.joshuaism.dto.HomeDto;
import com.joshuaism.repository.PreferencesRepository;
import com.joshuaism.response.MarsPhoto;
import com.joshuaism.response.MarsRoverApiResponse;

@Service
public class MarsRoverApiService {
	
	@Value( "${marsroverapi.apikey}")
	private String apiKey;
	
	@Autowired
	private PreferencesRepository preferencesRepo;
	
	private Map<String, List<String>> validCameras = new HashMap<>();

	public MarsRoverApiService() {
		validCameras.put("Opportunity", Arrays.asList("FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"));
		validCameras.put("Curiosity", Arrays.asList("FHAZ", "RHAZ", "MAST", "CHEMCAM", "MAHLI", "MARDI", "NAVCAM"));
		validCameras.put("Spirit", Arrays.asList("FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"));
	}
	
	public Map<String, List<String>> getValidCameras() {
		return validCameras;
	}

	public MarsRoverApiResponse getRoverData(HomeDto homeDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		RestTemplate rt = new RestTemplate();
		if (homeDto == null) {
			return new MarsRoverApiResponse();
		}
		MarsRoverApiResponse response = rt.getForObject("https://api.nasa.gov/mars-photos/api/v1/rovers/" + homeDto.getMarsApiRoverData() + "/photos?sol=" + homeDto.getMarsSol() + "&api_key=" + apiKey, MarsRoverApiResponse.class);
		response.setPhotos(getFilteredPhotos(homeDto, response));
		return response;
	}
	
	public List<MarsPhoto> getFilteredPhotos(HomeDto homeDto, MarsRoverApiResponse response) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<MarsPhoto> photos = new ArrayList<MarsPhoto>();
		
		Method[] methods = homeDto.getClass().getMethods();
		for (Method method : methods) {
			if (method.getReturnType().equals(Boolean.class)) {
				if (Boolean.TRUE.equals(method.invoke(homeDto))) {
					photos.addAll(response.getPhotos().stream().filter(photo -> photo.getCamera().getName().equals(method.getName().split("get")[1].toUpperCase())).collect(Collectors.toList()));
				}
			}
		}
		
		return photos;
	}

	public HomeDto save(HomeDto homeDto) {
		return preferencesRepo.save(homeDto);	
	}

	public HomeDto findByUserId(Long userId) {
		return preferencesRepo.findByUserId(userId);
	}
}
