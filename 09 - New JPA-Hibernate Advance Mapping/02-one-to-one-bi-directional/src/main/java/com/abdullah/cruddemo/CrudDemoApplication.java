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
    public CommandLineRunner commandLineRunner(AppDao appDao) {
        return runner -> {
            // createInstructor(appDao);
            // findInstructor(appDao);
            // deleteInstructor(appDao);
            // findInstructorDetail(appDao);
             deleteInstructorDetail(appDao);
        };
    }

    private void deleteInstructorDetail(AppDao appDao) {
        int theId = 8;
        System.out.println("Deleting instructor detail id: " + theId);

        appDao.deleteInstructorDetailById(theId);
        System.out.println("Done !!!");
    }

    private void findInstructorDetail(AppDao appDao) {
        // get the instructor detail object

        int theId = 2;
        InstructorDetail instructorDetail = appDao.findInstructorDetailById(theId);

        // print the instructor detail
        System.out.println("tempInstructorDetail: " + instructorDetail);
        // print the associated instructor

        System.out.println("the associated instructor: " + instructorDetail.getInstructor());

        System.out.println("Done !!!");

    }

    private void deleteInstructor(AppDao appDao) {
        int theId = 4;
        System.out.println("Deleteing instructor id: " + theId);

        appDao.deleteInstructorById(theId);

        System.out.println("Done !!");
    }

    private void findInstructor(AppDao appDao) {
        int theId = 2;
        System.out.println("Finding instructor id :" + theId);

        Instructor tempInstructor = appDao.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());

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
