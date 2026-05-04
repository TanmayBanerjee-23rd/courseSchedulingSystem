package com.example.courseScheduler;

import java.io.FileInputStream;
import java.util.Scanner;

import com.example.courseScheduler.processors.CommandProcessor;

public class CourseSchedulingSystem {
    public static void main(String[] args)  {
        
        try {
            CommandProcessor commandProcessor = new CommandProcessor();

            FileInputStream fs = new FileInputStream(args[0]);

            Scanner scanner = new Scanner(fs);

            while (scanner.hasNextLine()) {
               String command = scanner.nextLine();
               
               commandProcessor.processCommand(command);
            }
            
            scanner.close(); 
        } catch (Exception e) {
            System.out.println("Error reading input file: " + e.getMessage());
        }
	}
}
