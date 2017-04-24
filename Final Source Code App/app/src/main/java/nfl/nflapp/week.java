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

public class week extends AppCompatActivity {
    static List<Button> homeArray = new ArrayList<Button>();
    static List<Button> awayArray = new ArrayList<Button>();
    static List<Boolean> homeClick = new ArrayList<>();
    static List<Boolean> awayClick = new ArrayList<>();
    static String byes = "";

    MediaPlayer buttonMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/GraphicBlock.ttf");
        TextView weekView = (TextView)findViewById(R.id.weekTitle);
        TextView vs1View = (TextView)findViewById(R.id.vs1);
        TextView vs2View = (TextView)findViewById(R.id.vs2);
        TextView vs3View = (TextView)findViewById(R.id.vs3);
        TextView vs4View = (TextView)findViewById(R.id.vs4);
        TextView vs5View = (TextView)findViewById(R.id.vs5);
        TextView vs6View = (TextView)findViewById(R.id.vs6);
        TextView vs7View = (TextView)findViewById(R.id.vs7);
        TextView vs8View = (TextView)findViewById(R.id.vs8);
        TextView vs9View = (TextView)findViewById(R.id.vs9);
        TextView vs10View = (TextView)findViewById(R.id.vs10);
        TextView vs11View = (TextView)findViewById(R.id.vs11);
        TextView vs12View = (TextView)findViewById(R.id.vs12);
        TextView vs13View = (TextView)findViewById(R.id.vs13);
        TextView vs14View = (TextView)findViewById(R.id.vs14);
        TextView vs15View = (TextView)findViewById(R.id.vs15);
        TextView vs16View = (TextView)findViewById(R.id.vs16);
        TextView byeTitleView = (TextView)findViewById(R.id.byesTitle);
        Button showBtnView = (Button)findViewById(R.id.showBtn);
        EditText byesText = (EditText) findViewById(R.id.byesFeild);

        homeArray.clear();
        awayArray.clear();
        homeArray.add((Button) findViewById(R.id.ht1));
        homeArray.add((Button) findViewById(R.id.ht2));
        homeArray.add((Button) findViewById(R.id.ht3));
        homeArray.add((Button) findViewById(R.id.ht4));
        homeArray.add((Button) findViewById(R.id.ht5));
        homeArray.add((Button) findViewById(R.id.ht6));
        homeArray.add((Button) findViewById(R.id.ht7));
        homeArray.add((Button) findViewById(R.id.ht8));
        homeArray.add((Button) findViewById(R.id.ht9));
        homeArray.add((Button) findViewById(R.id.ht10));
        homeArray.add((Button) findViewById(R.id.ht11));
        homeArray.add((Button) findViewById(R.id.ht12));
        homeArray.add((Button) findViewById(R.id.ht13));
        homeArray.add((Button) findViewById(R.id.ht14));
        homeArray.add((Button) findViewById(R.id.ht15));
        homeArray.add((Button) findViewById(R.id.ht16));

        awayArray.add((Button) findViewById(R.id.at1));
        awayArray.add((Button) findViewById(R.id.at2));
        awayArray.add((Button) findViewById(R.id.at3));
        awayArray.add((Button) findViewById(R.id.at4));
        awayArray.add((Button) findViewById(R.id.at5));
        awayArray.add((Button) findViewById(R.id.at6));
        awayArray.add((Button) findViewById(R.id.at7));
        awayArray.add((Button) findViewById(R.id.at8));
        awayArray.add((Button) findViewById(R.id.at9));
        awayArray.add((Button) findViewById(R.id.at10));
        awayArray.add((Button) findViewById(R.id.at11));
        awayArray.add((Button) findViewById(R.id.at12));
        awayArray.add((Button) findViewById(R.id.at13));
        awayArray.add((Button) findViewById(R.id.at14));
        awayArray.add((Button) findViewById(R.id.at15));
        awayArray.add((Button) findViewById(R.id.at16));

        weekView.setTypeface(myTypeface);
        weekView.setText("WEEK " + main.weekCount);
        vs1View.setTypeface(myTypeface);
        vs2View.setTypeface(myTypeface);
        vs3View.setTypeface(myTypeface);
        vs4View.setTypeface(myTypeface);
        vs5View.setTypeface(myTypeface);
        vs6View.setTypeface(myTypeface);
        vs7View.setTypeface(myTypeface);
        vs8View.setTypeface(myTypeface);
        vs9View.setTypeface(myTypeface);
        vs10View.setTypeface(myTypeface);
        vs11View.setTypeface(myTypeface);
        vs12View.setTypeface(myTypeface);
        vs13View.setTypeface(myTypeface);
        vs14View.setTypeface(myTypeface);
        vs15View.setTypeface(myTypeface);
        vs16View.setTypeface(myTypeface);
        byeTitleView.setTypeface(myTypeface);
        showBtnView.setTypeface(myTypeface);

        buttonMusic = MediaPlayer.create(this,R.raw.buttonmusic);

        homeClick.clear();
        awayClick.clear();
        for(int i = 0; i <= 15; ++i) {
            homeClick.add(true);
            awayClick.add(false);
        }

        callingFinishView();
        byesText.setText(byes);
    }

    public void callingFinishView(){
        if(main.weekCount > 1) {
            finishedViews();
        }
        else{
            setup.finishedViews();
        }
    }

    public static void finishedViews() {
        int counter = 0;
        for (int j = 1; j <= 256; j++) {
            Game game = setup.db.getGame(j);
            if (game.getWeek() == main.weekCount) {
                getHomeText(counter).setText(game.getTeam1());
                getAwayText(counter).setText(game.getTeam2());
                getHomeText(counter).setBackgroundColor(Color.parseColor("#66b3ff"));
                counter++;
            }
        }

        byes = "";
        for(int k = 0;k< DBHandler.ByeWeekList.size();k++){
            if(DBHandler.ByeWeekList.get(k) == main.weekCount ) {
                byes += DBHandler.gameByeWeekList.get(k).toString()+ "     ";
            }
        }
    }

    public void showMessage(View view)
    {
        Intent intent = new Intent(week.this, winner.class);
        startActivity(intent);
        buttonMusic.start();
    }

    public void teamSelect(View v) {
        switch (v.getId()) {
            case R.id.ht1:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.at1).setBackgroundColor(Color.parseColor("#8d8d8d"));
                homeClick.set(0, true);
                awayClick.set(0, false);
                break;
            case R.id.ht2:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.at2).setBackgroundColor(Color.parseColor("#8d8d8d"));
                homeClick.set(1, true);
                awayClick.set(1, false);
                break;
            case R.id.ht3:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.at3).setBackgroundColor(Color.parseColor("#8d8d8d"));
                homeClick.set(2, true);
                awayClick.set(2, false);
                break;
            case R.id.ht4:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.at4).setBackgroundColor(Color.parseColor("#8d8d8d"));
                homeClick.set(3, true);
                awayClick.set(3, false);
                break;
            case R.id.ht5:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.at5).setBackgroundColor(Color.parseColor("#8d8d8d"));
                homeClick.set(4, true);
                awayClick.set(4, false);
                break;
            case R.id.ht6:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.at6).setBackgroundColor(Color.parseColor("#8d8d8d"));
                homeClick.set(5, true);
                awayClick.set(5, false);
                break;
            case R.id.ht7:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.at7).setBackgroundColor(Color.parseColor("#8d8d8d"));
                homeClick.set(6, true);
                awayClick.set(6, false);
                break;
            case R.id.ht8:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.at8).setBackgroundColor(Color.parseColor("#8d8d8d"));
                homeClick.set(7, true);
                awayClick.set(7, false);
                break;
            case R.id.ht9:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.at9).setBackgroundColor(Color.parseColor("#8d8d8d"));
                homeClick.set(8, true);
                awayClick.set(8, false);
                break;
            case R.id.ht10:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.at10).setBackgroundColor(Color.parseColor("#8d8d8d"));
                homeClick.set(9, true);
                awayClick.set(9, false);
                break;
            case R.id.ht11:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.at11).setBackgroundColor(Color.parseColor("#8d8d8d"));
                homeClick.set(10, true);
                awayClick.set(10, false);
                break;
            case R.id.ht12:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.at12).setBackgroundColor(Color.parseColor("#8d8d8d"));
                homeClick.set(11, true);
                awayClick.set(11, false);
                break;
            case R.id.ht13:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.at13).setBackgroundColor(Color.parseColor("#8d8d8d"));
                homeClick.set(12, true);
                awayClick.set(12, false);
                break;
            case R.id.ht14:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.at14).setBackgroundColor(Color.parseColor("#8d8d8d"));
                homeClick.set(13, true);
                awayClick.set(13, false);
                break;
            case R.id.ht15:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.at15).setBackgroundColor(Color.parseColor("#8d8d8d"));
                homeClick.set(14, true);
                awayClick.set(14, false);
                break;
            case R.id.ht16:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.at16).setBackgroundColor(Color.parseColor("#8d8d8d"));
                homeClick.set(15, true);
                awayClick.set(15, false);
                break;
            case R.id.at1:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.ht1).setBackgroundColor(Color.parseColor("#8d8d8d"));
                awayClick.set(0, true);
                homeClick.set(0, false);
                break;
            case R.id.at2:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.ht2).setBackgroundColor(Color.parseColor("#8d8d8d"));
                awayClick.set(1, true);
                homeClick.set(1, false);
                break;
            case R.id.at3:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.ht3).setBackgroundColor(Color.parseColor("#8d8d8d"));
                awayClick.set(2, true);
                homeClick.set(2, false);
                break;
            case R.id.at4:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.ht4).setBackgroundColor(Color.parseColor("#8d8d8d"));
                awayClick.set(3, true);
                homeClick.set(3, false);
                break;
            case R.id.at5:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.ht5).setBackgroundColor(Color.parseColor("#8d8d8d"));
                awayClick.set(4, true);
                homeClick.set(4, false);
                break;
            case R.id.at6:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.ht6).setBackgroundColor(Color.parseColor("#8d8d8d"));
                awayClick.set(5, true);
                homeClick.set(5, false);
                break;
            case R.id.at7:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.ht7).setBackgroundColor(Color.parseColor("#8d8d8d"));
                awayClick.set(6, true);
                homeClick.set(6, false);
                break;
            case R.id.at8:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.ht8).setBackgroundColor(Color.parseColor("#8d8d8d"));
                awayClick.set(7, true);
                homeClick.set(7, false);
                break;
            case R.id.at9:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.ht9).setBackgroundColor(Color.parseColor("#8d8d8d"));
                awayClick.set(8, true);
                homeClick.set(8, false);
                break;
            case R.id.at10:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.ht10).setBackgroundColor(Color.parseColor("#8d8d8d"));
                awayClick.set(9, true);
                homeClick.set(9, false);
                break;
            case R.id.at11:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.ht11).setBackgroundColor(Color.parseColor("#8d8d8d"));
                awayClick.set(10, true);
                homeClick.set(10, false);
                break;
            case R.id.at12:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.ht12).setBackgroundColor(Color.parseColor("#8d8d8d"));
                awayClick.set(11, true);
                homeClick.set(11, false);
                break;
            case R.id.at13:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.ht13).setBackgroundColor(Color.parseColor("#8d8d8d"));
                awayClick.set(12, true);
                homeClick.set(12, false);
                break;
            case R.id.at14:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.ht14).setBackgroundColor(Color.parseColor("#8d8d8d"));
                awayClick.set(13, true);
                homeClick.set(13, false);
                break;
            case R.id.at15:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.ht15).setBackgroundColor(Color.parseColor("#8d8d8d"));
                awayClick.set(14, true);
                homeClick.set(14, false);
                break;
            case R.id.at16:
                v.setBackgroundColor(Color.parseColor("#66b3ff"));
                findViewById(R.id.ht16).setBackgroundColor(Color.parseColor("#8d8d8d"));
                awayClick.set(15, true);
                homeClick.set(15, false);

        }
    }

    public static Button getHomeText(int i) { return homeArray.get(i); }
    public static Button getAwayText(int i) { return awayArray.get(i); }
}
