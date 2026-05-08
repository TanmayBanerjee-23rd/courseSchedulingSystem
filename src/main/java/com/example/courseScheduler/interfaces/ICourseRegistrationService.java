package com.example.courseScheduler.interfaces;

import java.util.Map;

import com.example.courseScheduler.models.CourseRegistration;

public interface ICourseRegistrationService {
    public void registerEmployeeToCourse(String employeeEmail, String courseOfferingId);
    public void cancelCourseRegistration(String courseRegistrationId);
    public Map<String, CourseRegistration> getCourseRegistrations();
    
}
