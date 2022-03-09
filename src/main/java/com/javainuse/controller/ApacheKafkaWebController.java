package com.javainuse.controller;

import com.javainuse.model.User;
import com.javainuse.service.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/javainuse-kafka/")
public class ApacheKafkaWebController {

	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate2;

	private static final String TOPIC = "JsonTopic";

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("message") String message) {
		kafkaTemplate2.send(TOPIC, message);

		return "Message sent to the Kafka Topic java_in_use_topic Successfully";
	}




	@GetMapping("/publish/{name}")
	public String post(@PathVariable("name") final String name) {

		kafkaTemplate.send(TOPIC, new User(name, "Technology"));

		return "Published successfully";
	}

}

