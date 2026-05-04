package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.example.geektrust.processors.CommandProcessor;
import com.example.geektrust.services.CourseAllotmentService;
import com.example.geektrust.services.CourseOfferingService;
import com.example.geektrust.services.CourseRegistrationService;

public class CommandProcessorTest {

    private CommandProcessor commandProcessor;
    private CourseOfferingService courseOfferingService;
    private CourseRegistrationService courseRegistrationService;
    private CourseAllotmentService courseAllotmentService;

    @BeforeEach
    void setUp() {
        // commandProcessor = new CommandProcessor();
        courseOfferingService = new CourseOfferingService();
        courseRegistrationService = new CourseRegistrationService(courseOfferingService);
        courseAllotmentService = new CourseAllotmentService(courseOfferingService, courseRegistrationService);
        commandProcessor = new CommandProcessor(courseOfferingService, courseRegistrationService, courseAllotmentService);
    }

    @Test
    void testProcessCommand() {
        commandProcessor.processCommand("ADD-COURSE-OFFERING DATASCIENCE BOB 05062022 1 3");
        commandProcessor.processCommand("REGISTER WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB");
        commandProcessor.processCommand("REGISTER ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB");
        commandProcessor.processCommand("ALLOT OFFERING-DATASCIENCE-BOB");
        commandProcessor.processCommand("CANCEL REG-COURSE-ANDY-DATASCIENCE");

        assertTrue(courseOfferingService.getCourseOfferings().size() == 1);
        assertTrue(courseRegistrationService.getCourseRegistrations().size() == 2);
        String courseOfferingId = courseOfferingService.getCourseOfferings().keySet().stream().findFirst().orElse(null);
        assertEquals("OFFERING-DATASCIENCE-BOB", courseOfferingId);
        Boolean isCourseOfferingAllotted = courseOfferingService.getCourseOfferings().get(courseOfferingId).getCourseAllotmentStatus().equals("CONFIRMED");
        assertTrue(isCourseOfferingAllotted);
    }
}
