package com.abdullah.cruddemo;

import com.abdullah.cruddemo.dao.AppDao;
import com.abdullah.cruddemo.entity.Instructor;
import com.abdullah.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao){
		return runner -> {
			createInstructor(appDao);
		};
	}

	private void createInstructor(AppDao appDao) {
		/*// create the instructor
		Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@abc.com");

		// create instructor details
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.abc.com/youtube",
				"Luv 2 code !!!");*/


		// create the instructor
		Instructor tempInstructor = new Instructor("Chad", "Patel", "chad@abc.com");

		// create instructor details
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.chad.com/youtube",
				"Luv 2 play guitar !!!");

        // associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		//
		// NOTE: this will also save the details object
		// beacuse of CascadeType.ALL
		//
		System.out.println("Saving instructor: " + tempInstructor);

		appDao.save(tempInstructor);

		System.out.println("Done !!");
	}

}
