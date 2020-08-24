package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan(basePackages = {"com.example.demo"})
class KafkaDemoApplicationTests {
	@Test
	void contextLoads() {
	}
}
