package com.joshuaism.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joshuaism.dto.HomeDto;

public interface PreferencesRepository extends JpaRepository<HomeDto, Long> {

	HomeDto findByUserId(Long userId);
	
	// reference for custom query creation
	//@Query("select dto from HomeDto dto where userId = :userId")
	//@Query(value="select * from preferences where user_id = :userId", nativeQuery=true)


}
