package edu.psu.ist311;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class CompetitionBook {
    private final String fileLogName;
    private final List<Competition> competitions;

    public CompetitionBook(String fileName){
        this.fileLogName = fileName;
        this.competitions = new ArrayList<>();
        //use the scanner to read in each line of the file from fileLogName
        try (Scanner scan = new Scanner(new File(fileLogName))){
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                Competition competition = new Competition(line, true);
                competitions.add(competition);
            }
        } catch (FileNotFoundException e){
            System.out.println("Unable to find " + fileLogName);
        } catch (Exception e){
            System.out.println("Something went wrong parsing runlog line ");
        }
    }
    public String getFileLogName(){
        return fileLogName;
    }
    public void addComp(Competition c){
        competitions.add(c);
    }
    public void removeComp(Competition c) {
        competitions.remove(c);
    }
    public boolean isInBook(Competition c){
        return competitions.contains(c);
    }
    public List<Competition> competitions (){
        return this.competitions;
    }

    private int lookupIndex(Competition c){
        return competitions.indexOf(c);
    }

}
