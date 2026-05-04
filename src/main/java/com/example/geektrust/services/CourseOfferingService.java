package com.example.geektrust.services;

import java.util.HashMap;
import java.util.Map;

import com.example.geektrust.constants.StringConstants;
import com.example.geektrust.models.Course;

public class CourseOfferingService {
    
    private static final String COURSE_ID_PREFIX = "OFFERING-";
    private Map<String, Course> courseOfferings;

    public CourseOfferingService() {
        this.courseOfferings = new HashMap<>();
    }

    private String generateCourseId(String courseName, String instructor) {
        return COURSE_ID_PREFIX + courseName.toUpperCase() + "-" + instructor.toUpperCase();
    }

    private boolean isCourseAlreadyOffered(String courseId) {
        return this.courseOfferings.containsKey(courseId);
    }

    private boolean isValidCourseOfferingData(String courseName, String instructor, String courseId, String date, int minEmployeesAllowedToRegister, int maxEmployeesAllowedToRegister) {
        boolean isCourseValid = true;

        if (courseName == null || courseName.isEmpty()) {
            isCourseValid = false;
        }

        if (instructor == null || instructor.isEmpty()) {
            isCourseValid = false;
        }

        if (isCourseAlreadyOffered(courseId)) {
            isCourseValid = false;
        }

        if (Integer.parseInt(date) <= 0) {
            isCourseValid = false;
        }

        if (minEmployeesAllowedToRegister < 0 || maxEmployeesAllowedToRegister < 0 || minEmployeesAllowedToRegister > maxEmployeesAllowedToRegister) {
            isCourseValid = false;
        }

        return isCourseValid;
    }

    public void addCourseOffering(String courseName, String instructor, String date, int minEmployeesAllowedToRegister, int maxEmployeesAllowedToRegister) {
        
        String courseId = generateCourseId(courseName, instructor);

        if (!isValidCourseOfferingData(courseName, instructor, courseId, date, minEmployeesAllowedToRegister, maxEmployeesAllowedToRegister)) {
            System.out.println(StringConstants.INPUT_DATA_ERROR.getValue());
            return;
        }

        Course newCourse = new Course(courseId, courseName, instructor, date, minEmployeesAllowedToRegister, maxEmployeesAllowedToRegister);
        this.courseOfferings.put(courseId, newCourse);

        System.out.println(courseId);
    }

    public boolean isCourseOfferingAvailableForRegistration(String courseOfferingId) {
        Course courseOffering = this.courseOfferings.get(courseOfferingId);
        return courseOffering != null && !courseOffering.hasReachedMaximumRegistrationLimit();
    }

    public void incrementNumberOfEmployeesRegisteredForCourseOffering(String courseOfferingId) {
        Course courseOffering = this.courseOfferings.get(courseOfferingId);
        if (courseOffering != null) {
            courseOffering.incrementNumberOfEmployeesRegistered();
        }
    }

    public void decrementNumberOfEmployeesRegisteredForCourseOffering(String courseOfferingId) {
        Course courseOffering = this.courseOfferings.get(courseOfferingId);
        if (courseOffering != null) {
            courseOffering.decrementNumberOfEmployeesRegistered();
        }
    }

    public Map<String, Course> getCourseOfferings() {
        return this.courseOfferings;
    }

    public boolean isCourseOfferingAllotted(String courseOfferingId) {
        Course courseOffering = this.courseOfferings.get(courseOfferingId);
        return courseOffering != null && courseOffering.getCourseAllotmentStatus().equals(StringConstants.COURSE_ALLOTMENT_CONFIRMED.getValue());
    }
    
}
