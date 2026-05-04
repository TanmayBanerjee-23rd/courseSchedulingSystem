package com.example.geektrust.services;

import java.util.Map;

import com.example.geektrust.constants.StringConstants;
import com.example.geektrust.models.Course;
import com.example.geektrust.models.CourseRegistration;

public class CourseAllotmentService {
    private final Map<String, Course> courseOfferings;
    private final Map<String, CourseRegistration> courseRegistrations;

    public CourseAllotmentService(CourseOfferingService courseOfferingService, CourseRegistrationService courseRegistrationService) {
        this.courseOfferings = courseOfferingService.getCourseOfferings();
        this.courseRegistrations = courseRegistrationService.getCourseRegistrations();
    }

    public void allotCourseOfferings() {

        for (Course courseOffering : courseOfferings.values()) {
            CourseRegistration[] registrationsForCourseOffering = courseRegistrations.values().stream()
                .filter(courseRegistration -> courseRegistration.getCourseOfferingId().equals(courseOffering.getCourseId()))
                .sorted((courseRegistration1, courseRegistration2) -> courseRegistration1.getRegistrationId().compareTo(courseRegistration2.getRegistrationId()))
                .toArray(CourseRegistration[]::new);

            if (courseOffering.hasMetMinimumRegistrationRequirement()) {

                courseOffering.setCourseAllotmentStatus(StringConstants.COURSE_ALLOTMENT_CONFIRMED.getValue());
                for (CourseRegistration courseRegistration : registrationsForCourseOffering) {
                    System.out.println(courseRegistration.getRegistrationId() + " " + courseRegistration.getEmployeeEmail() + " " + courseOffering.getCourseId() + " " + courseOffering.getTitle() + " " + courseOffering.getInstructor() + " " + courseOffering.getDate() + " " + courseOffering.getCourseAllotmentStatus());
                }
            }
        }

    }
}
