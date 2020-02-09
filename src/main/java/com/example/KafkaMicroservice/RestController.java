package com.example.KafkaMicroservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/kafkaApp")
public class RestController {
	

	
   /* @Autowired
	private KafkaTemplate<String, CSVRecord> template;
    */
    @Autowired
	private KafkaTemplate<Object, Object> template;
    @Autowired
    FileReader reader;

	@GetMapping(path = "/send")
	public void sendFoo() {
		System.out.println("inside caller");
		List<CSVRecord> recordList = reader.loadFileIntoBean();
		
		for (int index = 1; index < recordList.size(); index++) {
			CSVRecord c = new CSVRecord();
			c = recordList.get(index);
			
			this.template.send(CountryEnum.getCountry(c.getCountry()).topicName, c);
			}
		
	
	}
}
