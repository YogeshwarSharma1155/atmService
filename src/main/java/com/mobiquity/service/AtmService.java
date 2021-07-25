package com.mobiquity.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.mobiquity.entity.AtmAddress;
import com.mobiquity.entity.AtmGeoLocation;
import com.mobiquity.entity.AtmHours;
import com.mobiquity.entity.AtmInfo;
import com.mobiquity.entity.AtmOpeningHours;

@Service  
public class AtmService {

	public List<AtmInfo> getAllAtmList() {
		List<AtmInfo> atmList=new ArrayList<>() ;

		try {

			URL url = new URL("https://www.ing.nl/api/locator/atms/");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();

			if (responsecode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			} else {
				String inline = "";
				Scanner scanner = new Scanner(url.openStream());
				while (scanner.hasNext()) {
					inline += scanner.nextLine();
				}

				scanner.close();

				String jsonFileString = inline.substring(inline.indexOf("["),inline.length());

				JSONParser parser = new JSONParser();
				Object obj  = parser.parse(jsonFileString);
				JSONArray arr = (JSONArray) obj;


				for(Object atmObj : arr) {
					
					List<AtmOpeningHours> atmOpeningHoursList = new ArrayList<AtmOpeningHours>();
					
					JSONObject jsonAtmInfoObj = (JSONObject) atmObj;
					AtmInfo atmInfoObj = new AtmInfo();
					AtmAddress atmAddressObj = new AtmAddress();
					AtmGeoLocation atmGeoLocationObj = new AtmGeoLocation();

					
					
					
					//ATM Address
					JSONObject jsonAtmAddressObj = (JSONObject) jsonAtmInfoObj.get("address");
					atmAddressObj.setCity((String)jsonAtmAddressObj.get("city"));
					atmAddressObj.setHouseNumber((String)jsonAtmAddressObj.get("housenumber"));
					atmAddressObj.setPostalcode((String)jsonAtmAddressObj.get("postalcode"));
					atmAddressObj.setStreet((String)jsonAtmAddressObj.get("street"));
					
					//Address Geo Location
					JSONObject jsonatmGeoLocationObj = (JSONObject) jsonAtmAddressObj.get("geoLocation");
					atmGeoLocationObj.setLat((String)jsonatmGeoLocationObj.get("lat"));
					atmGeoLocationObj.setLng((String)jsonatmGeoLocationObj.get("lng"));
					atmAddressObj.setGeoLocation(atmGeoLocationObj);
					
					//ATM Opening Hours
					
					
					JSONArray jsonOpeningHoursObj = (JSONArray) jsonAtmInfoObj.get("openingHours");
					
					for(Object atmOpeningObj : jsonOpeningHoursObj) {
						JSONObject jsonAtmOpeningObj = (JSONObject) atmOpeningObj;
						AtmOpeningHours atmOpeningHoursObj = new AtmOpeningHours();
						AtmHours atmHoursObj = new AtmHours();

						//ATM Opening Hourse
						JSONArray jsonAtmOpeningHoursArr = (JSONArray) jsonAtmOpeningObj.get("hours");
						
						for(Object jsonAtmHoursObj : jsonAtmOpeningHoursArr) {
							JSONObject jsonAtmOpeningHoursObj = (JSONObject) jsonAtmHoursObj;

							atmHoursObj.setHourFrom((String)jsonAtmOpeningHoursObj.get("hourFrom"));
							atmHoursObj.setHourTo((String)jsonAtmOpeningHoursObj.get("hourTo"));
							atmOpeningHoursObj.setHours(atmHoursObj);

						}
						atmOpeningHoursObj.setDayOfWeek((long)jsonAtmOpeningObj.get("dayOfWeek"));
			
						atmOpeningHoursList.add(atmOpeningHoursObj);
						
					}
					

					
					atmInfoObj.setAddress(atmAddressObj);
					atmInfoObj.setDistance((long)jsonAtmInfoObj.get("distance"));
					atmInfoObj.setType((String)jsonAtmInfoObj.get("type"));
					atmInfoObj.setFunctionality((String)jsonAtmInfoObj.get("functionality"));
					atmInfoObj.setOpeningHoursList(atmOpeningHoursList);
					
					atmList.add(atmInfoObj);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}


		return atmList;
	}

	public List<AtmInfo> getAllAtmListByCity(List<AtmInfo> allAtmInfoList, String city) {
		
		List<AtmInfo> atmListByCity =  allAtmInfoList.stream()  
                .filter(x -> city.equals(x.getAddress().getCity()))   // filtering by City  
                .collect(Collectors.toList());  
		
		
		return atmListByCity;
	}

}
