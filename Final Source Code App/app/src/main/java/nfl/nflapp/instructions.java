package nfl.nflapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class instructions extends AppCompatActivity {
    MediaPlayer buttonMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/GraphicBlock.ttf");
        TextView instView =(TextView)findViewById(R.id.instTitle);
        TextView instView1 =(TextView)findViewById(R.id.instTitle1);
        Button backView =(Button)findViewById(R.id.backBtn);
        instView.setTypeface(myTypeface);
        instView1.setTypeface(myTypeface);
        backView.setTypeface(myTypeface);
        buttonMusic = MediaPlayer.create(this,R.raw.buttonmusic);
    }

    public void backMessage(View view) {
        Intent intent = new Intent(instructions.this, main.class);
        startActivity(intent);
        buttonMusic.start();
    }
}