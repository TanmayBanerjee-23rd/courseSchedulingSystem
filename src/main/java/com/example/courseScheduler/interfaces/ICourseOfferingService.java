package com.example.courseScheduler.interfaces;

import java.util.Map;

import com.example.courseScheduler.models.Course;

public interface ICourseOfferingService {
    public void addCourseOffering(String courseName, String instructor, String date, int minEmployeesAllowedToRegister, int maxEmployeesAllowedToRegister);

    public boolean isCourseOfferingAvailableForRegistration(String courseOfferingId);

    public void incrementNumberOfEmployeesRegisteredForCourseOffering(String courseOfferingId);

    public void decrementNumberOfEmployeesRegisteredForCourseOffering(String courseOfferingId);

    public Map<String, Course> getCourseOfferings();

    public boolean isCourseOfferingAllotted(String courseOfferingId);
}
