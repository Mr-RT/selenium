package com.neurio.tests.shared;

import org.testng.Assert;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Robert on 5/30/2016.
 * Calculator Class
 */
public class Calculator {

    /**
     * Runs the Calculator Python Script and returns its output as a String array for easier access
     * @param calculator - Calculator script to run
     * @param id - User ID to use
     */
    public static String[] getOutput(StringRef.Calculator calculator, String id){
        String calculatorFile = StringRef.calculatorMapEnumToFile.get(calculator);
        String operatingSystem = Common.getPropertyValue("os", "windows");
        String command;
        switch (operatingSystem){
            case "windows":
                command = "cmd.exe /c python %PROJECT_PATH%\\qa\\"+ calculatorFile + id;
                break;
            case "mac":
                command = "python $PROJECT_PATH\\qa\\"+ calculatorFile + id;
                break;
            case "unix":
                command = "python $PROJECT_PATH\\qa\\"+ calculatorFile + id;
                break;
            default:
                command = "cmd.exe /c python %PROJECT_PATH%\\qa\\"+ calculatorFile + id;
        }

        String s;
        String save = "";

        try {
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec(command);

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            // read the output from the command
            while ((s = stdInput.readLine()) != null) {
                save += (s + "\n");
            }

            // read any errors from the attempted command
            while ((s = stdError.readLine()) != null) {
                save += (s + "\n");
            }
        }
        catch (Exception e) {
            System.out.println("Exception happened - Here's what I know: ");
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        return save.split("\\s+");
    }
}
