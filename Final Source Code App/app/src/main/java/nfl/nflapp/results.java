package nfl.nflapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class results extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/GraphicBlock.ttf");
        TextView resultsView = (TextView)findViewById(R.id.resultsTitle);
        TextView playView = (TextView)findViewById(R.id.playPercent);
        TextView compView = (TextView)findViewById(R.id.compPercent);
        Button homeView = (Button)findViewById(R.id.homeBtn);
        TextView winnerName = (TextView) findViewById(R.id.playPercent);
        TextView userVal = (TextView) findViewById(R.id.playVal);
        TextView compVal = (TextView) findViewById(R.id.compVal);

        winnerName.setText(setup.userName);
        compVal.setText(Double.toString(main.predict));

        resultsView.setTypeface(myTypeface);
        playView.setTypeface(myTypeface);
        compView.setTypeface(myTypeface);
        homeView.setTypeface(myTypeface);

        userVal.setText(Double.toString(Math.floor (main.userWinPercentage*100)/100));
    }

    public void homeMessage(View view)
    {
        Intent intent = new Intent(results.this, main.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
    }
}
