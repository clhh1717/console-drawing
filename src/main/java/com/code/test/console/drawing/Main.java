package com.code.test.console.drawing;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter command: ");
        boolean running = true;
        while (running) {
            String userInput = scanner.nextLine();
            m.process(userInput);
            System.out.println("enter command: ");
        }
        scanner.close();
    }

    public String process(String iCmd) {
        CommandProcessor.Command cmd = CommandProcessor.Command.getInstance(iCmd);
        String result = null;
        try {
            if (cmd == null) {
                System.out.println("invalid command. please check supported command list in README");
            } else {
                result = cmd.execute();
            }
        } catch (Exception e) {
            System.out.println("**error: " + e.getMessage());
            System.out.println("please try again or enter Q to quit");
        }
        return result;
    }
}
