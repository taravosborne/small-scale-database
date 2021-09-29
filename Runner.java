package edu.psu.ist311;

public class Runner implements Comparable<Runner>{
    public int timeInMin;
    public String initials;
    public Runner(int timeInMin, String initials){
        this.timeInMin = timeInMin;
        this.initials = initials;
    }
    @Override
    public int compareTo(Runner o){
        return this.timeInMin - o.timeInMin;
    }
    public String toString(){
        return "(" + initials + ": " + timeInMin + "): ";
    }
    public int timeInMin(){
        return this.timeInMin;
    }
}