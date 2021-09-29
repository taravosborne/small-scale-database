package edu.psu.ist311;

import java.util.Scanner;

/**
 * A basic driver/tester class for invoking the comprun app. Note that the
 * program requires the name of a file (runlog.txt) containing existing race
 * data to be supplied to the program via the commandline. See the assignment
 * writeup for information on how to do this in IntelliJ.
 *
 * @author dtwelch
 */
public class Tester {
    //java asg1-taravosborne runlog.txt

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Error no file specified, usage: [filename.txt]");
            return; // no sense in continuing (missing race data)..
        }

        // uncomment these when ready:
        Scanner in = new Scanner(System.in);
        CompetitionBook book = new CompetitionBook(args[0]);
        RacetrackCli u = new RacetrackCli(in, System.out, book);
        u.doShowCmdLine();
    }
}
