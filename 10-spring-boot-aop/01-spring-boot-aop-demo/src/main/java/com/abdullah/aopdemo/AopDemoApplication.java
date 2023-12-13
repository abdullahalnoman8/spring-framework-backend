package com.abdullah.aopdemo;

import com.abdullah.aopdemo.dao.AccountDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDao theAccountDao){
		return runner -> {
			System.out.println("Hello World !!!");

			demoTheBeforeAdvice(theAccountDao);
		};
	}

	private void demoTheBeforeAdvice(AccountDao theAccountDao) {
		// call the business method

		theAccountDao.addAccount();
	}
}
