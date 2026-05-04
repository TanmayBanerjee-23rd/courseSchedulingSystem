package com.example.courseScheduler.models;

import com.example.courseScheduler.constants.StringConstants;

public class Course {

    private final String courseId;
    private final String title;
    private final String instructor;
    private final String date;
    private final int minimumEmployeesAllowedToRegister;
    private final int maximumEmployeesAllowedToRegister;
    private int numberOfEmployeesRegistered;
    private String courseAllotmentStatus;

    public Course(String courseId, String title, String instructor, String date, int minimumEmployeesAllowedToRegister, int maximumEmployeesAllowedToRegister) {
        this.courseId = courseId;
        this.title = title;
        this.instructor = instructor;
        this.date = date;
        this.minimumEmployeesAllowedToRegister = minimumEmployeesAllowedToRegister;
        this.maximumEmployeesAllowedToRegister = maximumEmployeesAllowedToRegister;
        this.numberOfEmployeesRegistered = 0;
        this.courseAllotmentStatus = StringConstants.COURSE_ALLOTMENT_NOT_INITIATED.getValue();
    }

    public void incrementNumberOfEmployeesRegistered() {
        this.numberOfEmployeesRegistered++;
    }

    public void decrementNumberOfEmployeesRegistered() {
        this.numberOfEmployeesRegistered--;
    }

    public void setCourseAllotmentStatus(String courseAllotmentStatus) {
        this.courseAllotmentStatus = courseAllotmentStatus;
    }


    public String getCourseId() {
        return this.courseId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getInstructor() {
        return this.instructor;
    }

    public String getDate() {
        return this.date;
    }

    public String getCourseAllotmentStatus() {
        return this.courseAllotmentStatus;
    }

    public boolean hasReachedMaximumRegistrationLimit() {
        return this.numberOfEmployeesRegistered >= this.maximumEmployeesAllowedToRegister;
    }

    public boolean hasMetMinimumRegistrationRequirement() {
        return this.numberOfEmployeesRegistered >= this.minimumEmployeesAllowedToRegister;
    }

}
