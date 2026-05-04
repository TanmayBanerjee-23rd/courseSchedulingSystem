package com.example.courseScheduler.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.example.courseScheduler.constants.IntegerConstants;
import com.example.courseScheduler.constants.StringConstants;
import com.example.courseScheduler.interfaces.ICourseRegistrationService;
import com.example.courseScheduler.models.CourseRegistration;
import com.example.courseScheduler.models.Course;

public class CourseRegistrationService implements ICourseRegistrationService {

    private static final String REGISTRATION_ID_PREFIX = "REG-COURSE-";

    private final Map<String, CourseRegistration> courseRegistrations;
    private final CourseOfferingService courseOfferingService;

    public CourseRegistrationService(CourseOfferingService courseOfferingService) {
        this.courseOfferingService = courseOfferingService;
        this.courseRegistrations = new HashMap<>();
    }

    private String generateCourseRegistrationId(String employeeName, String courseTitle) {
        return REGISTRATION_ID_PREFIX + employeeName.toUpperCase() + "-" + courseTitle.toUpperCase();
    }

    private boolean isValidCourseRegistrationData(String employeeEmail, String courseOfferingId) {
        boolean isCourseRegistrationDataValid = true;

        if (employeeEmail == null || employeeEmail.isEmpty() || !employeeEmail.contains("@") || employeeEmail.startsWith("@") || employeeEmail.endsWith("@")) {
            isCourseRegistrationDataValid = false;
        }

        if (courseOfferingId == null || courseOfferingId.isEmpty()) {
            isCourseRegistrationDataValid = false;
        }

        return isCourseRegistrationDataValid;
    }

    public void registerEmployeeToCourse(String employeeEmail, String courseOfferingId) {

        if (!isValidCourseRegistrationData(employeeEmail, courseOfferingId)) {
            System.out.println(StringConstants.INPUT_DATA_ERROR.getValue());
            return;
        }

        if (!this.courseOfferingService.isCourseOfferingAvailableForRegistration(courseOfferingId)) {
            System.out.println(StringConstants.COURSE_FULL_ERROR.getValue());
            return;
        }

        String employeeName = employeeEmail.split("@")[IntegerConstants.INDEX_ZERO.ordinal()];
        Course courseOffering = this.courseOfferingService.getCourseOfferings().get(courseOfferingId);
        String courseRegistrationId = generateCourseRegistrationId(employeeName, courseOffering.getTitle());


        CourseRegistration newRegistration = new CourseRegistration(courseRegistrationId, employeeEmail, courseOfferingId);
        this.courseRegistrations.put(courseRegistrationId, newRegistration);
        this.courseOfferingService.incrementNumberOfEmployeesRegisteredForCourseOffering(courseOfferingId);

        System.out.println(courseRegistrationId + " " + StringConstants.COURSE_REGISTRATION_ACCEPTED.getValue());
    }

    public void cancelCourseRegistration(String courseRegistrationId) {
        CourseRegistration courseRegistration = this.courseRegistrations.get(courseRegistrationId);

        if (courseRegistration != null) {

            if (this.courseOfferingService.isCourseOfferingAllotted(courseRegistration.getCourseOfferingId())) {
                courseRegistration.setCancellationStatus(StringConstants.CANCEL_REJECTED.getValue());
                System.out.println(courseRegistrationId + " " + StringConstants.CANCEL_REJECTED.getValue());
                return;
            };

            courseRegistration.setCancellationStatus(StringConstants.CANCEL_ACCEPTED.getValue());
            System.out.println(courseRegistrationId + " " + StringConstants.CANCEL_ACCEPTED.getValue());
            this.courseOfferingService.decrementNumberOfEmployeesRegisteredForCourseOffering(courseRegistration.getCourseOfferingId());
            this.courseRegistrations.remove(courseRegistrationId);
        }
    }

    public Map<String, CourseRegistration> getCourseRegistrations() {
        return Collections.unmodifiableMap(this.courseRegistrations);
    }
}
