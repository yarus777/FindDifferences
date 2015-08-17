package com.adeco.finddifferences;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import com.adeco.finddifferences.game.statistics.StatisticData;
import com.adeco.finddifferences.game.statistics.StatisticHandler;

public class GameActivity extends Activity implements StatisticHandler {

    private TextView tries;
    private TextView right_touches;

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);
        tries = (TextView) findViewById(R.id.touches);
        right_touches = (TextView) findViewById(R.id.right_touches);
        gameView = (GameView) findViewById(R.id.canvas);
        gameView.setHandler(this);
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

        // здесь вывод в текст-вью чего хочешь из data, например
        right_touches.setText(diffs);
        tries.setText(moves);
    }
}
