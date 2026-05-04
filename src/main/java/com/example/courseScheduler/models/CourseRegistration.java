package com.example.courseScheduler.models;

public class CourseRegistration {

    private final String registrationId;
    private final String employeeEmail;
    private final String courseOfferingId;
    private String cancellationStatus;

    public CourseRegistration(String registrationId, String employeeEmail, String courseOfferingId) {
        this.registrationId = registrationId;
        this.employeeEmail = employeeEmail;
        this.courseOfferingId = courseOfferingId;
    }

    public void setCancellationStatus(String cancellationStatus) {
        this.cancellationStatus = cancellationStatus;
    }

    public String getRegistrationId() {
        return this.registrationId;
    }

    public String getEmployeeEmail() {
        return this.employeeEmail;
    }

    public String getCourseOfferingId() {
        return this.courseOfferingId;
    }

}
