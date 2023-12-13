package com.abdullah.cruddemo;

import com.abdullah.cruddemo.dao.AppDao;
import com.abdullah.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


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

            // createInstructorWithCourse(appDao);

            // findInstructorWithCourses(appDao);
            // findCoursesForInstructor(appDao);
            // findInstructorWithCoursesJoinFetch(appDao);
            // updateInstructor(appDao);
            // updateCourse(appDao);
            // deleteInstructor(appDao);
            // deleteCourseById(appDao);

            // many-to-many-uni directional
            // createCourseAndReview(appDao);
            // retrieveCourseAndReviews(appDao);
            // deleteCourseAndReviews(appDao);
            // createCourseAndStudents(appDao);
            findCourseAndStudents(appDao);

        };
    }

    private void findCourseAndStudents(AppDao appDao) {
        int theId = 10;

        Course course = appDao.findCourseAndStudentsByCourseId(theId);

        System.out.println("Load Course: " + course);
        System.out.println("Students: " + course.getStudents());

        System.out.println("Done !!!!");
    }

    private void createCourseAndStudents(AppDao appDao) {
        // create a course
        Course tempCourse = new Course("Pacman - How To Score One Million Points");
        // create the students
        Student tempStudent = new Student("John","Doe", "john@abc.com");
        Student tempStudent2 = new Student("Oliver","Queen", "oliver@abc.com");
        Student tempStudent3 = new Student("Henry","Welson", "henri@abc.com");
        // add students to the course

        tempCourse.addStudent(tempStudent);
        tempCourse.addStudent(tempStudent2);
        tempCourse.addStudent(tempStudent3);

        // save the associated course and students

        System.out.println("Saving the course: " + tempCourse);
        System.out.println("Associated students: " + tempCourse.getStudents());

        appDao.save(tempCourse);

        System.out.println("Done !!!!");
    }

    private void deleteCourseAndReviews(AppDao appDao) {
        int theId = 10;

        System.out.println("Deleting course id: " + theId);
        appDao.deleteCourseById(theId);
        System.out.println("Done !!!");
    }

    private void retrieveCourseAndReviews(AppDao appDao) {
        // get the course and reviews
        int theId = 10;
        Course tempCourse = appDao.findCourseAndReviewsByCourseId(theId);

        // print the course
        System.out.println(tempCourse);

        // print the reviews
        System.out.println(tempCourse.getReviews());
        System.out.println("Done !!!");
    }

    private void createCourseAndReview(AppDao appDao) {
        // create a course
        Course tempCourse = new Course("Pacman - How to Score One Millions Points");
        // add some reviews

        tempCourse.addReview(new Review("Great course ... loved it. !!!"));
        tempCourse.addReview(new Review("Cool course ... job well done.!!!"));
        tempCourse.addReview(new Review("Great course ... loved it!!!"));
        tempCourse.addReview(new Review("What a dump course ..... "));

        // save the course

        System.out.println("Saving the course ");
        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviews());

        appDao.save(tempCourse);

        System.out.println("Done !!!");
    }

    private void deleteCourseById(AppDao appDao) {
        int theId = 10;

        System.out.println("Deleting course id: " + theId);

        appDao.deleteCourseById(theId);

        System.out.println("Done !!!!!");

    }

    private void updateCourse(AppDao appDao) {
      int theId= 10;
        System.out.println("Finding the the Course by Course Id: " + theId);
        Course theCourse = appDao.findCourseById(theId);

        // update the theCourse
        theCourse.setTitle("Updated Course title");
        appDao.updateCourse(theCourse);

        System.out.println("Done !!!!");
    }

    private void updateInstructor(AppDao appDao) {
        int theId = 1;
        // find the instructor
        System.out.println("Finding instructor id: " + theId);
        Instructor tempInstructor = appDao.findInstructorById(theId);

        // update the instructor
        System.out.println("Updating instructor id: " + theId);
        tempInstructor.setLastName("Tester");
        appDao.updateInstructor(tempInstructor);

        System.out.println("Done !!!");
    }

    private void findInstructorWithCoursesJoinFetch(AppDao appDao) {
        int theId = 1;
        // find the instructor
        System.out.println("Finding instructor id: " + theId);
        Instructor tempInstructor = appDao.findInstructorByIdJoinFetch(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated courses: " +tempInstructor.getCourses());

        System.out.println("Done !!!");
    }

    private void findCoursesForInstructor(AppDao appDao) {
        int theId = 1;
        System.out.println("Finding instructor id: " + theId);

        Instructor tempInstructor = appDao.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);

        // find courses for instructor
        System.out.println("Finding courses for instructor id: " + theId);
        List<Course> courses = appDao.findCoursesByInstructorId(theId);

        // associate the object
        tempInstructor.setCourses(courses);
        System.out.println("the associated courses: " + tempInstructor.getCourses());

        System.out.println("Done !!!");
    }

    private void findInstructorWithCourses(AppDao appDao) {
        int theId = 1;
        System.out.println("Finding instructor id: " + theId);

        Instructor tempInstructor = appDao.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());

        System.out.println("Done !!!");
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
        int theId = 1;
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
