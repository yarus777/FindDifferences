package com.adeco.finddifferences;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import com.adeco.finddifferences.game.DifferenceFoundHandler;
import com.adeco.finddifferences.game.statistics.StatisticData;
import com.adeco.finddifferences.game.statistics.StatisticHandler;

public class GameActivity extends Activity implements StatisticHandler, DifferenceFoundHandler {

    private TextView tries;
    private TextView right_touches;

    private GameView gameView;
    private boolean vibroState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);
        tries = (TextView) findViewById(R.id.touches);
        right_touches = (TextView) findViewById(R.id.right_touches);

        Intent intent = getIntent();
        vibroState = intent.getExtras().getBoolean("vibroState");

        gameView = (GameView) findViewById(R.id.canvas);
        gameView.setHandler(this, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void handleStatistics(StatisticData data) {
        String diffs = String.valueOf(data.getDifferencesFound());
        String moves = String.valueOf(data.getMovesTaken());

        right_touches.setText(diffs);
        tries.setText(moves);
    }

    @Override
    public void onDifferenceFound() {
        if (vibroState) {
            Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
        }
    }
}
