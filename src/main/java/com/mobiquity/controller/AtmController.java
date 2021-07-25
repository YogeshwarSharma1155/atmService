package com.mobiquity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mobiquity.entity.AtmInfo;
import com.mobiquity.service.AtmService;

@RestController  
public class AtmController {

	@Autowired
	AtmService atmService;
	List<AtmInfo> allAtmInfoList;


	@GetMapping("/allAtm")  
	public List<AtmInfo> getAllAtmList()   
	{  
		List<AtmInfo> atmList = atmService.getAllAtmList();
		allAtmInfoList=atmList;

		return atmList; 
	}  


	@GetMapping("/allAtm/{city}")  
	public List<AtmInfo> getAtmByCity(@PathVariable("city") String city)   
	{  
		if(CollectionUtils.isEmpty(allAtmInfoList)) {

			List<AtmInfo> atmList = atmService.getAllAtmList();
			allAtmInfoList=atmList;
		}

		return atmService.getAllAtmListByCity(allAtmInfoList,city);  
	}  


}
