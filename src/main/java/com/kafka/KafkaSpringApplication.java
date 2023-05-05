package com.kafka;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

@SpringBootApplication
public class KafkaSpringApplication implements CommandLineRunner {

	private final static Logger LOG = LoggerFactory.getLogger(KafkaSpringApplication.class);
	
	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;

	
	public static void main(String[] args) {
		SpringApplication.run(KafkaSpringApplication.class, args);
	}

	@KafkaListener(topics ="devs4j-topic", groupId ="consumer")
	public void listen(String message) {
		LOG.info("Received Messasge in group consumer {} ", message);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		
				kafkaTemplate.send("devs4j-topic","Sample message ").get();
	
	}
	}
