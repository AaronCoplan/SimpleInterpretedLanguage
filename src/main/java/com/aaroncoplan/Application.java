package com.aaroncoplan;

import com.aaroncoplan.phase1.SimpleInterpreter1;
import com.aaroncoplan.phase2.SimpleInterpreter2;
import com.aaroncoplan.phase3.SimpleInterpreter3;
import com.aaroncoplan.phase4.SimpleInterpreter4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        SimpleInterpreter simpleInterpreter = null;

        if(args.length == 0) {
            System.out.println("[ERROR] Missing filename for code to run!");
            System.exit(1);
        }

        String fileName = args[0];
        File file = new File(fileName);
        StringBuilder code = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                code.append(scanner.nextLine() + "\n");
            }
            scanner.close();
        } catch(FileNotFoundException e) {
            System.out.format("[ERROR] File '%s' not found!", fileName).println();
            System.exit(1);
        }


        if(args.length > 1) {
            String phaseNumberString = args[1];

            if("1".equals(phaseNumberString)) {
                System.out.println("[Running Using Phase 1 Interpreter]");
                simpleInterpreter = new SimpleInterpreter1();
            } else if("2".equals(phaseNumberString)) {
                System.out.println("[Running Using Phase 2 Interpreter]");
                simpleInterpreter = new SimpleInterpreter2();
            } else if("3".equals(phaseNumberString)) {
                System.out.println("[Running Using Phase 3 Interpreter]");
                simpleInterpreter = new SimpleInterpreter3();
            } else if("4".equals(phaseNumberString)) {
                System.out.println("[Running Using Phase 4 Interpreter]");
                simpleInterpreter = new SimpleInterpreter4();
            } else {
                System.out.println("[ERROR] Invalid phase number!  Please choose a value from 1-4");
                System.exit(1);
            }
        } else {
            System.out.println("[Running Using Phase 4 Interpreter]");
            simpleInterpreter = new SimpleInterpreter4();
        }

        simpleInterpreter.run(code.toString().trim());
    }
}