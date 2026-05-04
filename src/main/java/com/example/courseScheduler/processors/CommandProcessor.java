package com.example.courseScheduler.processors;

import com.example.courseScheduler.constants.IntegerConstants;
import com.example.courseScheduler.services.CourseAllotmentService;
import com.example.courseScheduler.services.CourseOfferingService;
import com.example.courseScheduler.services.CourseRegistrationService;

public class CommandProcessor {

    private final CourseOfferingService courseOfferingService;
    private final CourseRegistrationService courseRegistrationService;
    private final CourseAllotmentService courseAllotmentService;

    public CommandProcessor() {
        this.courseOfferingService = new CourseOfferingService();
        this.courseRegistrationService = new CourseRegistrationService(this.courseOfferingService);
        this.courseAllotmentService = new CourseAllotmentService(this.courseOfferingService, this.courseRegistrationService);
    }

    public CommandProcessor(CourseOfferingService courseOfferingService, CourseRegistrationService courseRegistrationService, CourseAllotmentService courseAllotmentService) {
        this.courseOfferingService = courseOfferingService;
        this.courseRegistrationService = courseRegistrationService;
        this.courseAllotmentService = courseAllotmentService;
    }

    public void processCommand(String command) {
        String[] commandParts = command.split(" ");

        switch(commandParts[IntegerConstants.INDEX_ZERO.ordinal()]) {
            case "ADD-COURSE-OFFERING":
                this.courseOfferingService.addCourseOffering(
                    commandParts.length < IntegerConstants.INDEX_TWO.ordinal() ? null : commandParts[IntegerConstants.INDEX_ONE.ordinal()], 
                    commandParts.length < IntegerConstants.INDEX_THREE.ordinal() ? null : commandParts[IntegerConstants.INDEX_TWO.ordinal()], 
                    commandParts.length < IntegerConstants.INDEX_FOUR.ordinal() ? null : commandParts[IntegerConstants.INDEX_THREE.ordinal()], 
                    commandParts.length < IntegerConstants.INDEX_FIVE.ordinal() ? -1 : Integer.parseInt(commandParts[IntegerConstants.INDEX_FOUR.ordinal()]),
                    commandParts.length < IntegerConstants.INDEX_SIX.ordinal() ? -1 : Integer.parseInt(commandParts[IntegerConstants.INDEX_FIVE.ordinal()])
                );
                break;
            case "REGISTER":
                    this.courseRegistrationService.registerEmployeeToCourse(
                        commandParts.length < IntegerConstants.INDEX_TWO.ordinal() ? null :commandParts[IntegerConstants.INDEX_ONE.ordinal()], 
                        commandParts.length < IntegerConstants.INDEX_THREE.ordinal() ? null : commandParts[IntegerConstants.INDEX_TWO.ordinal()]);    
                break;
            case "CANCEL":
                this.courseRegistrationService.cancelCourseRegistration(
                    commandParts.length < IntegerConstants.INDEX_TWO.ordinal() ? null : commandParts[IntegerConstants.INDEX_ONE.ordinal()]
                );
                break;
            case "ALLOT":
                this.courseAllotmentService.allotCourseOfferings();
                break;
            default:
                System.out.println("Invalid command: " + command);
        }
    }

}
