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

    private TextView tries, right_touches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);
        tries = (TextView) findViewById(R.id.touches);
        right_touches = (TextView) findViewById(R.id.right_touches);
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
        int diffs = data.getDifferencesFound();
        int moves = data.getMovesTaken();
        // здесь вывод в текст-вью чего хочешь из data
    }
}
