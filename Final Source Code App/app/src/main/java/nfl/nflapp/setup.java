package nfl.nflapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.io.InputStream;
import java.util.Scanner;

public class setup extends AppCompatActivity {

    MediaPlayer buttonMusic;
    String text;
    Spinner seasonSelect;
    Spinner predictorSelect;
    static DBHandler db;
    static String workingSeason;
    static String userName;
    static EditText userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/GraphicBlock.ttf");
        TextView setupView =(TextView)findViewById(R.id.setupTitle);
        TextView seasonView =(TextView)findViewById(R.id.seasonHead);
        TextView predictView =(TextView)findViewById(R.id.predictorHead);
        TextView nameView =(TextView)findViewById(R.id.nameHead);
        Button startView =(Button)findViewById(R.id.startBtn);
        Button backView1 = (Button)findViewById(R.id.backBtn1);
        userInput = (EditText)findViewById(R.id.nameText);

        // Spinner Drop down elements
        seasonSelect = (Spinner)findViewById(R.id.seasonSpin);
        String[] seasons = new String[]{"2015 Season", "2014 Season", "2013 Season", "2012 Season", "2011 Season", "2010 Season"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, seasons);
        seasonSelect.setAdapter(adapter1);

        predictorSelect = (Spinner)findViewById(R.id.perdictSpin);
        String[] predictors = new String[]{"AI Predictor", "PI Rating", "Power Ranking", "Power Ranking + AI"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, predictors);
        predictorSelect.setAdapter(adapter2);

        setupView.setTypeface(myTypeface);
        seasonView.setTypeface(myTypeface);
        predictView.setTypeface(myTypeface);
        nameView.setTypeface(myTypeface);
        startView.setTypeface(myTypeface);
        backView1.setTypeface(myTypeface);
        buttonMusic = MediaPlayer.create(this,R.raw.buttonmusic);
    }

    public void backMessage(View view)
    {
        Intent intent = new Intent(setup.this, main.class);
        startActivity(intent);
        buttonMusic.start();
    }

    public void startMessage(View view)
    {
        Intent intent = new Intent(setup.this, week.class);
        startActivity(intent);
        main.weekCount = 0;
        main.weekCount++;
        buttonMusic.start();

        userName =userInput.getText().toString();
        getSeasonSelect();
        getComp();
    }

    public static void finishedViews() {
        int counter = 0;
        for(int j = 1; j <= 256; j++){
            Game game = db.getGame(j);
            if(game.getMargin() != 0 && game.getWeek() == main.weekCount){
                week.getHomeText(counter).setText(game.getTeam1());
                week.getAwayText(counter).setText(game.getTeam2());
                week.getHomeText(counter).setBackgroundColor(Color.parseColor("#66b3ff"));
                ++counter;
            }
        }
    }

    public void getSeasonSelect(){

        workingSeason = "";
        text = seasonSelect.getSelectedItem().toString();

        if(text == "2015 Season"){
            workingSeason = "season2015";
        }
        else if(text == "2014 Season"){
            workingSeason = "season2014";
        }
        else if(text == "2013 Season"){
            workingSeason = "season2013";
        }
        else if(text == "2012 Season"){
            workingSeason = "season2012";
        }
        else if(text == "2011 Season"){
            workingSeason = "season2011";
        }
        else if(text == "2010 Season"){
            workingSeason = "season2010";
        }

        getResources().getIdentifier(workingSeason,
                "raw", getPackageName());

        InputStream ins = getResources().openRawResource(
                getResources().getIdentifier(workingSeason,
                        "raw", getPackageName()));

        Scanner dbFile = new Scanner(ins);
        db = new DBHandler(this, dbFile);
        db.readGames();
    }

    public void getComp() {
        main.predict = 0.0;
        text = predictorSelect.getSelectedItem().toString();

        if(text == "PI Ranking"){
            main.predict = 60.39;
        }
        else if(text == "AI Predictor"){
            main.predict = 57.82;
        }
        else if(text == "Power Ranking"){
            main.predict = 53.94 ;
        }
        else if(text == "Power Ranking + AI"){
            main.predict = 59.13;
        }
    }
}
