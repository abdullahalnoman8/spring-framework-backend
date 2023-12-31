package com.abdullah.aopdemo;

import com.abdullah.aopdemo.dao.AccountDao;
import com.abdullah.aopdemo.dao.MemberShipDao;
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
	public CommandLineRunner commandLineRunner(AccountDao theAccountDao, MemberShipDao theMemberShipDao){
		return runner -> {
			System.out.println("Hello World !!!");

			demoTheBeforeAdvice(theAccountDao, theMemberShipDao);
		};
	}

	private void demoTheBeforeAdvice(AccountDao theAccountDao, MemberShipDao theMemberShipDao) {
		// call the business method

		Account account = new Account();

		theAccountDao.addAccount(account, true);

		System.out.println();

		// call the membership business method
		theMemberShipDao.addAccount();

		System.out.println();
	    theMemberShipDao.addSillyMember();
	}
}
