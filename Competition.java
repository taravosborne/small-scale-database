package edu.psu.ist311;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Competition {
    public String competitionName;
    public int numRunners;
    public Year year;
    private List<Runner> runners = new ArrayList<>();
    private String distance;

    public Competition(String raceInfoLine, boolean infoIncludesFinishers){
        StringTokenizer t = new StringTokenizer(raceInfoLine);
        String tempTitle = "";
        int numOfWordsInTitle = 0;
        if(infoIncludesFinishers){
            numOfWordsInTitle = t.countTokens() - 6;
        } else {
            numOfWordsInTitle = t.countTokens() - 2;
        }

        for(int i = 0; i < numOfWordsInTitle; i++){
            tempTitle = tempTitle + t.nextToken() + " ";
        }
        this.competitionName = tempTitle.trim();
        String tempDistance = t.nextToken();
        if(tempDistance.equals("5k") || tempDistance.equals("10k") || tempDistance.equals("15k")){
            this.distance = tempDistance;
        } else {
            throw new IllegalArgumentException("Invalid distance");
        }

        String strYear = t.nextToken();
        strYear = strYear.substring(1,5);
        this.year = Year.parse(strYear);

        if(infoIncludesFinishers){
            String strNumRunners = t.nextToken();
            this.numRunners = Integer.parseInt(strNumRunners);
            while(t.hasMoreTokens()){
                String strFirstRunner = t.nextToken();
                int colonIndex = strFirstRunner.indexOf(":");

                String initials = strFirstRunner.substring(0, colonIndex + 1);
                String raceFinish = strFirstRunner.substring(colonIndex + 1, strFirstRunner.length() - 1);
                int runnerTime = Integer.parseInt(raceFinish);
                Runner r = new Runner (runnerTime, initials);
                runners.add(r);
            }
        }
    }
    public String renderInPresentationFormat(){
        String result = competitionName + " " + distance + " (" + year + ") " + numRunners + " ";
        for(Runner r : runners){
            result = result +  r + " ";
        }
        return result;
    }

    @Override
    public String toString(){
        String result = competitionName + " " + distance + " (" + year + ") ";
        if(!runners.isEmpty()) {
            result = result + "runners: " + numRunners + " top time: " + runners.get(0).timeInMin() + " mins";
        }
        return result;
    }

    @Override
    public boolean equals(Object o){
        boolean result = o instanceof Competition;
        if(result){
            result = competitionName.equals(((Competition) o).competitionName) &&
                    year.equals(((Competition) o).year) &&
                    distance.equals(((Competition) o).distance);
        }
        return result;
    }
    public Year getYear(){
        return year;
    }
}
