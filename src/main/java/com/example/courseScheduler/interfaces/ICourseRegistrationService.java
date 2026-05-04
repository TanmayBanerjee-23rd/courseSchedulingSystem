package com.example.courseScheduler.interfaces;

public interface ICourseRegistrationService {
    public void registerEmployeeToCourse(String employeeEmail, String courseOfferingId);
    public void cancelCourseRegistration(String courseRegistrationId);
    
}
