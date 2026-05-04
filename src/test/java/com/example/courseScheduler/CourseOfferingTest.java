package com.example.courseScheduler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.courseScheduler.services.CourseOfferingService;

public class CourseOfferingTest {

    private CourseOfferingService courseOfferingService;

    @BeforeEach
    void setUp() {
        courseOfferingService = new CourseOfferingService();
    }

    @Test
    void testAddCourseOffering() {
        courseOfferingService.addCourseOffering("DATASCIENCE", "BOB", "05062022", 1, 3);
        assertTrue(courseOfferingService.getCourseOfferings().size() == 1);
        String courseOfferingId = courseOfferingService.getCourseOfferings().keySet().stream().findFirst().orElse(null);
        assertEquals("OFFERING-DATASCIENCE-BOB", courseOfferingId);
    }
    
    @Test
    void testSkipAdditionOfInvalidCourseOffering() {
        courseOfferingService.addCourseOffering("DATASCIENCE", "BOB", "05062022", 3, 1);
        assertTrue(courseOfferingService.getCourseOfferings().size() == 0);
    }
}
