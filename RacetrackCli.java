package edu.psu.ist311;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.time.Year;
import java.util.Scanner;

public class RacetrackCli{
    private final Scanner in;
    private final PrintStream out;
    private final CompetitionBook book;

    public RacetrackCli(Scanner in, PrintStream out, CompetitionBook book){
        this.in = in;
        this.out = out;
        this.book = book;
    }
    public void doShowCmdLine(){
        out.println("Welcome to the running competition record system");
        out.println("--------------------------------------------------");
        while(true){
            out.println("Enter A)dd, R)emove,  LY) List by Year, S)ave, L)ist all, or Q)uit");
            try{
                String cmd = in.nextLine().trim().toUpperCase();
                if(cmd.equals("A")){
                    doAdd();
                } else if (cmd.equals("R")){
                    doRemove();
                } else if(cmd.equals("LY")){
                    doListByYear();
                } else if(cmd.equals("S")){
                    doSave();
                } else if(cmd.equals("L")){
                    doListAll();
                } else if(cmd.equals("Q")){
                    out.print("Quitting...");
                    System.exit(0);
                } else{
                    out.println("Invalid comment");
                }
            } catch (Exception e){
                out.println("An error occurred: " + e.getMessage());
            }
        }
    }
    private void doAdd(){
        out.println("Please enter race details: ");
        String raceInfo = in.nextLine();
        Competition c = new Competition(raceInfo, true);
        for(Competition comp : book.competitions()){
            if(comp.equals(c)){
                out.println("Competition already exists");
                return;
            }
        }
        book.addComp(c);
        out.println("Competition added");
    }
    private void doRemove(){
        out.println("Please enter the info for the race you want to remove: ");
        String raceInfo = in.nextLine();
        Competition comp = new Competition(raceInfo, false);
        for(Competition c : book.competitions()){
            if(!c.equals(comp)){
                out.println("Competition does not exist");
                return;
            }
        }
        book.removeComp(comp);
    }
    private void doListAll(){
        for(Competition c : book.competitions()){
            out.println(c.toString());
        }
    }
    private void doListByYear(){
        out.println("Please enter a year: ");
        Year year = Year.parse(in.nextLine());
        String result = "";
        for(Competition c : book.competitions()){
            if(c.getYear().equals(year)){
                result = c.toString();
                out.println(result);
            }
        }
        if(result.equals("")){
            out.println("No competitions available for this year");
        }
    }
    private void doSave() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(book.getFileLogName());
        try {
            for (Competition c : book.competitions()) {
                pw.println(c.renderInPresentationFormat());
            }
        } finally {
            pw.close();
        }
        out.println("All changes saved. ");
    }

}
