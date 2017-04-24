package nfl.nflapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class winner extends AppCompatActivity {
    MediaPlayer buttonMusic;
    static List<EditText> winningArray = new ArrayList<EditText>();
    static double correctSelection = 0.0;
    static double totalGames = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        double ans;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winner);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/GraphicBlock.ttf");
        TextView resultsView = (TextView)findViewById(R.id.resultsTitle);
        TextView winnersView = (TextView)findViewById(R.id.winnersTitle);
        Button nextView = (Button)findViewById(R.id.nextBtn);
        buttonMusic = MediaPlayer.create(this,R.raw.buttonmusic);

        resultsView.setTypeface(myTypeface);
        resultsView.setText("WEEK " + main.weekCount);
        winnersView.setTypeface(myTypeface);
        nextView.setTypeface(myTypeface);

        winningArray.clear();

        winningArray.add((EditText) findViewById(R.id.win1));
        winningArray.add((EditText) findViewById(R.id.win2));
        winningArray.add((EditText) findViewById(R.id.win3));
        winningArray.add((EditText) findViewById(R.id.win4));
        winningArray.add((EditText) findViewById(R.id.win5));
        winningArray.add((EditText) findViewById(R.id.win6));
        winningArray.add((EditText) findViewById(R.id.win7));
        winningArray.add((EditText) findViewById(R.id.win8));
        winningArray.add((EditText) findViewById(R.id.win9));
        winningArray.add((EditText) findViewById(R.id.win10));
        winningArray.add((EditText) findViewById(R.id.win11));
        winningArray.add((EditText) findViewById(R.id.win12));
        winningArray.add((EditText) findViewById(R.id.win13));
        winningArray.add((EditText) findViewById(R.id.win14));
        winningArray.add((EditText) findViewById(R.id.win15));
        winningArray.add((EditText) findViewById(R.id.win16));

        finishedViews();
        setCorrect();

        main.userWinPercentage = (correctSelection/totalGames)*100;
    }
    public static void finishedViews() {
        int k = 0;
        for (int j = 1; j <= 256; j++) {
            Game game = setup.db.getGame(j);
            if (game.getWeek() == main.weekCount) {
                if (game.getMargin() > 0) {
                    getWinningText(k).setText(game.getTeam1());
                }
                else {
                    getWinningText(k).setText(game.getTeam2());
                }
                ++k;
            }
        }
    }

    public void nextMessage(View view)
    {
        if(main.weekCount == 17) {
            Intent intent = new Intent(winner.this, results.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(winner.this, week.class);
            startActivity(intent);
            main.weekCount++;
            buttonMusic.start();
        }
        buttonMusic.start();
        finish();
    }

    public void setCorrect() {
        for(int i = 0; i < winningArray.size(); ++i) {
            String hArray = week.homeArray.get(i).getText().toString();
            String aArray = week.awayArray.get(i).getText().toString();
            String winArray = winningArray.get(i).getText().toString();
            totalGames++;

            if(hArray.equals("")) {
                winningArray.get(i).setBackgroundColor(Color.parseColor("#8d8d8d"));
            }
            else if(week.homeClick.get(i) == true && hArray.equals(winArray)) {
                winningArray.get(i).setBackgroundColor(Color.parseColor("#66b3ff"));
                correctSelection++;

            }
            else if(week.awayClick.get(i) == true && aArray.equals(winArray)) {
                winningArray.get(i).setBackgroundColor(Color.parseColor("#66b3ff"));
                correctSelection++;
            }
            else if(week.homeClick.get(i) == true && !hArray.equals(winArray)) {
                winningArray.get(i).setBackgroundColor(Color.parseColor("#8d8d8d"));
            }
            else if(week.awayClick.get(i) == true && !aArray.equals(winArray)) {
                winningArray.get(i).setBackgroundColor(Color.parseColor("#8d8d8d"));
            }
        }
    }

    public static EditText getWinningText(int i) { return winningArray.get(i); }
}
