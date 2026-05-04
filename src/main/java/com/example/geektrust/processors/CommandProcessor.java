package com.example.geektrust.processors;

import com.example.geektrust.constants.IntegerConstants;
import com.example.geektrust.services.CourseAllotmentService;
import com.example.geektrust.services.CourseOfferingService;
import com.example.geektrust.services.CourseRegistrationService;

public class CommandProcessor {

    private final CourseOfferingService courseOfferingService;
    private final CourseRegistrationService courseRegistrationService;
    private final CourseAllotmentService courseAllotmentService;

    public CommandProcessor() {
        this.courseOfferingService = new CourseOfferingService();
        this.courseRegistrationService = new CourseRegistrationService(this.courseOfferingService);
        this.courseAllotmentService = new CourseAllotmentService(this.courseOfferingService, this.courseRegistrationService);
    }

    public void processCommand(String command) {
        String[] commandParts = command.split(" ");

        switch(commandParts[IntegerConstants.INDEX_ZERO.ordinal()]) {
            case "ADD-COURSE-OFFERING":
                this.courseOfferingService.addCourseOffering(
                    commandParts[IntegerConstants.INDEX_ONE.ordinal()], 
                    commandParts[IntegerConstants.INDEX_TWO.ordinal()], 
                    commandParts[IntegerConstants.INDEX_THREE.ordinal()], 
                    (Integer.parseInt(commandParts[IntegerConstants.INDEX_FOUR.ordinal()])),
                    (Integer.parseInt(commandParts[IntegerConstants.INDEX_FIVE.ordinal()]))
                ); 
                break;
            case "REGISTER":
                    this.courseRegistrationService.registerEmployeeToCourse(commandParts[IntegerConstants.INDEX_ONE.ordinal()], commandParts[IntegerConstants.INDEX_TWO.ordinal()]);    
                break;
            case "CANCEL":
                this.courseRegistrationService.cancelCourseRegistration(commandParts[IntegerConstants.INDEX_ONE.ordinal()]);
                break;
            case "ALLOT":
                this.courseAllotmentService.allotCourseOfferings();
                break;
            default:
                System.out.println("Invalid command: " + command);
        }
    }

}
