package com.abdullah.cruddemo;

import com.abdullah.cruddemo.dao.AppDao;
import com.abdullah.cruddemo.entity.Course;
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
            // deleteInstructorDetail(appDao);

            createInstructorWithCourse(appDao);
        };
    }

    private void createInstructorWithCourse(AppDao appDao) {

        Instructor tempInstructor = createInstructorWithInstructorDetail();
        // create some courses
        Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
        Course tempCourse2 = new Course("The Pinball Masterclass");

        // add courses to instructor
        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        // save the instructor
        //
        // Note: This will also save the courses due to the CascadeType.PERSIST

        System.out.println("Saving instructor : " + tempInstructor);
        System.out.println("The courses : " + tempInstructor.getCourses());
        appDao.save(tempInstructor);

        System.out.println("Done !!!");
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


        Instructor tempInstructor = createInstructorWithInstructorDetail();

        // save the instructor
        //
        // NOTE: this will also save the details object
        // beacuse of CascadeType.ALL
        //
        System.out.println("Saving instructor: " + tempInstructor);

        appDao.save(tempInstructor);

        System.out.println("Done !!");
    }

    private static Instructor createInstructorWithInstructorDetail() {
        // create the instructor
        Instructor tempInstructor = new Instructor("Susan", "Public", "susan@abc.com");

        // create instructor details
        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com",
                "Video games !!!");

        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);
        return tempInstructor;
    }

}
