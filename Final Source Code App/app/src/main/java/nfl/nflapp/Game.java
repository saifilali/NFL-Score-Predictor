package nfl.nflapp;

import java.util.Scanner;

public class Game {
    private int id;
    private int week;
    private String team1;
    private String team2;
    private int margin;
    private boolean byeWeek;

    //Constructors
    public Game()
    {
    }
    public Game(int week, String team1,String team2,int margin, boolean bye)
    {
        this.team1=team1;
        this.team2=team2;
        this.week = week;
        this.margin = margin;
        this.byeWeek = bye;
    }
    public Game(int week, String team1,String team2,int margin)
    {
        this.team1=team1;
        this.team2=team2;
        this.week = week;
        this.margin = margin;
        this.byeWeek = false;
    }

    public boolean byeWeek() { return byeWeek; }

    //Setter Functions
    public void setId(int id) {
        this.id = id;
    }
    public void setTeam1(String team1) {
        this.team1 = team1;
    }
    public void setTeam2(String team2) {
        this.team2 = team2;
    }
    public void setMargin(int margin) {
        this.margin = margin;
    }
    public void setWeek(int week) {
        this.week = week;
    }

    //Getter Functions
    public int getId() {
        return id;
    }
    public String getTeam1() {
        return team1;
    }
    public String getTeam2() {
        return team2;
    }
    public int getMargin() {
        return margin;
    }
    public int getWeek() { return week; }
    public boolean getByeWeek() {return byeWeek; }

    public String toString() {
        return String.format("%d %s %s %d", week, team1, team2, margin);
    }

    public static Game read(Scanner s /* some output param */) {
        String team1 = s.next();
        int week  = s.nextInt();
        String team2 = s.next();
        String m = s.next();
        boolean byeWeek = m.equals("null");
        double marginD = byeWeek ? 0 : Double.parseDouble(m);
        int margin = (int) marginD;

        if(margin != marginD) {
            throw new RuntimeException("Margin was not an int: " + margin);
        }

        return new Game(week, team1, team2, margin, byeWeek);
    }
}
