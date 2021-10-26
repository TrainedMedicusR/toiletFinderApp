package com.example.toiletfinderapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement  // <tx:annotation-driven />
@MapperScan("com.example.toiletfinderapp.dao")
public class ToiletFinderAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(ToiletFinderAppApplication.class, args);
	}
}
