package com.unionutilities.unionutilities;

import com.unionutilities.unionutilities.client.AppInfoRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnionUtilitiesApplication implements ApplicationRunner {

	@Autowired
	private AppInfoRestClient client;

	public static void main(String[] args) {
		SpringApplication.run(UnionUtilitiesApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(client.getAppInfo("http://localhost:9001/api/v1/info"));
	}
}
