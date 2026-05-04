package com.example.courseScheduler;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.courseScheduler.services.CourseAllotmentService;
import com.example.courseScheduler.services.CourseOfferingService;
import com.example.courseScheduler.services.CourseRegistrationService;

public class CourseAllotmentTest {

    private CourseOfferingService courseOfferingService;
    private CourseRegistrationService courseRegistrationService;
    private CourseAllotmentService courseAllotmentService;

    @BeforeEach
    public void setUp() {
        courseOfferingService = new CourseOfferingService();
        courseRegistrationService = new CourseRegistrationService(courseOfferingService);
        courseAllotmentService = new CourseAllotmentService(courseOfferingService, courseRegistrationService);
    }

    @Test
    void testAllotCourseOfferings() {
        courseOfferingService.addCourseOffering("DATASCIENCE", "BOB", "05062022", 1, 3);
        courseRegistrationService.registerEmployeeToCourse("ALICE@example.com", "OFFERING-DATASCIENCE-BOB");
        courseAllotmentService.allotCourseOfferings();
        
        Boolean isCourseOfferingAllotted = courseOfferingService.getCourseOfferings().get("OFFERING-DATASCIENCE-BOB").getCourseAllotmentStatus().equals("CONFIRMED");
        assertTrue(isCourseOfferingAllotted);
    }

    @Test
    void testSkipAllotmentOfCourseOfferingWithNoRegistrations() {
        courseOfferingService.addCourseOffering("DATASCIENCE", "BOB", "05062022", 1, 3);
        courseAllotmentService.allotCourseOfferings();
        
        Boolean isCourseOfferingAllotted = courseOfferingService.getCourseOfferings().get("OFFERING-DATASCIENCE-BOB").getCourseAllotmentStatus().equals("NOT_INITIATED");
        assertTrue(isCourseOfferingAllotted);
    }
}
