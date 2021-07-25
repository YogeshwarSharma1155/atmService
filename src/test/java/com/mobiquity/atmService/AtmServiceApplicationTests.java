package com.mobiquity.atmService;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.mobiquity.entity.AtmInfo;
import com.mobiquity.service.AtmService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AtmServiceApplicationTests {

	@Autowired
	AtmService atmService;
	
	
	@LocalServerPort
	private int port;
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	

	@Test
	public void atmListNotEmpty() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/allAtm",
				JSONArray.class)).isNotNull();
		
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/allAtm/Rotterdam",
				JSONArray.class)).isNotNull();
	}

}
