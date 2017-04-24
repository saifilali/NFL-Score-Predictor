package nfl.nflapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.media.MediaPlayer;

public class main extends AppCompatActivity {

    public static double predict;
    public static int weekCount;
    MediaPlayer bckMusic,buttonMusic;
    static double userWinPercentage = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        bckMusic = MediaPlayer.create(this, R.raw.background);
        bckMusic.setLooping(true);
        bckMusic.start();
        buttonMusic = MediaPlayer.create(this,R.raw.buttonmusic);
    }

    @Override
    protected void onPause(){
        bckMusic.release();
        super.onPause();
        finish();
    }

    public void setUpMessage(View view)
    {
        Intent intent = new Intent(main.this, setup.class);
        startActivity(intent);
        buttonMusic.start();
    }

    public void instructionsMessage(View view)
    {
        Intent intent = new Intent(main.this, instructions.class);
        startActivity(intent);
        buttonMusic.start();
    }
}
