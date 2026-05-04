package com.example.courseScheduler;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.courseScheduler.services.CourseAllotmentService;
import com.example.courseScheduler.services.CourseOfferingService;
import com.example.courseScheduler.services.CourseRegistrationService;

public class CourseRegistrationTest {

    private CourseOfferingService courseOfferingService;
    private CourseRegistrationService courseRegistrationService;
    private CourseAllotmentService courseAllotmentService;

    @BeforeEach
    void setUp() {
        courseOfferingService = new CourseOfferingService();
        courseRegistrationService = new CourseRegistrationService(courseOfferingService);
        courseAllotmentService = new CourseAllotmentService(courseOfferingService, courseRegistrationService);
    }

    @Test
    void testRegisterEmployeeToCourse() {
        courseOfferingService.addCourseOffering("DATASCIENCE", "BOB", "05062022", 1, 3);
        courseRegistrationService.registerEmployeeToCourse("ALICE@example.com", "OFFERING-DATASCIENCE-BOB");

        assertTrue(courseRegistrationService.getCourseRegistrations().size() == 1);
        assertTrue(courseRegistrationService.getCourseRegistrations().containsKey("REG-COURSE-ALICE-DATASCIENCE"));
    }

    @Test
    void testCancelCourseRegistration() {
        courseOfferingService.addCourseOffering("DATASCIENCE", "BOB", "05062022", 1, 3);
        courseRegistrationService.registerEmployeeToCourse("ALICE@example.com", "OFFERING-DATASCIENCE-BOB");
        courseRegistrationService.cancelCourseRegistration("REG-COURSE-ALICE-DATASCIENCE");

        assertTrue(courseRegistrationService.getCourseRegistrations().size() == 0);
    }

    @Test
    void testSkipRegistrationOfEmployeeToInvalidCourseOffering() {
        courseRegistrationService.registerEmployeeToCourse("ALICE@example.com", "OFFERING-DATASCIENCE-BOB");

        assertTrue(courseRegistrationService.getCourseRegistrations().size() == 0);
    }

    @Test
    void testSkipCancellationPostCourseAllotmentToEmployee() {
        courseOfferingService.addCourseOffering("DATASCIENCE", "BOB", "05062022", 1, 3);
        courseRegistrationService.registerEmployeeToCourse("ALICE@example.com", "OFFERING-DATASCIENCE-BOB");
        courseAllotmentService.allotCourseOfferings();
        courseRegistrationService.cancelCourseRegistration("REG-COURSE-ALICE-DATASCIENCE");

        assertTrue(courseRegistrationService.getCourseRegistrations().size() == 1);
    }

    @Test
    void testSkipRegistrationOfEmployeeToCourseOfferingWithMaxRegistrationLimitReached() {
        courseOfferingService.addCourseOffering("DATASCIENCE", "BOB", "05062022", 1, 1);
        courseRegistrationService.registerEmployeeToCourse("ALICE@example.com", "OFFERING-DATASCIENCE-BOB");
        courseRegistrationService.registerEmployeeToCourse("BOB@example.com", "OFFERING-DATASCIENCE-BOB");

        assertTrue(courseRegistrationService.getCourseRegistrations().size() == 1);
    }
}
