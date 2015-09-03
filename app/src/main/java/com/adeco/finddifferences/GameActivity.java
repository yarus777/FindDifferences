package com.adeco.finddifferences;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.TextView;

import com.adeco.finddifferences.game.Game;
import com.adeco.finddifferences.game.interfaces.DifferenceFoundHandler;
import com.adeco.finddifferences.game.interfaces.TimeCounter;
import com.adeco.finddifferences.game.popups.Popups;
import com.adeco.finddifferences.game.states.GameStateHandler;
import com.adeco.finddifferences.game.states.StateController;
import com.adeco.finddifferences.game.statistics.StatisticData;
import com.adeco.finddifferences.game.statistics.StatisticHandler;
import com.adeco.finddifferences.utils.Stopwatch;

import java.io.IOException;

public class GameActivity extends Activity implements StatisticHandler, DifferenceFoundHandler, GameStateHandler, TimeCounter {

    private TextView tries, tries_txt;
    private TextView right_touches, right_touches_txt;
    private Chronometer timer;
    private Stopwatch stopWatch;

    private GameView gameView;
    private Popups popupController;
    protected PowerManager.WakeLock mWakeLock;
    private MediaPlayer mp;
    private long timeWhenStopped = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);

       // final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        //this.mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
        //this.mWakeLock.acquire();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        stopWatch = new Stopwatch();

        timer = (Chronometer) findViewById(R.id.timer);
        tries = (TextView) findViewById(R.id.touches);
        right_touches = (TextView) findViewById(R.id.right_touches);
        tries_txt = (TextView) findViewById(R.id.touches_txt);
        right_touches_txt = (TextView) findViewById(R.id.right_touches_txt);
        Typeface tf = Typeface.createFromAsset(getAssets(), getString(R.string.text_typeface));
        tries_txt.setTypeface(tf);
        right_touches_txt.setTypeface(tf);
        tries.setTypeface(tf);
        right_touches.setTypeface(tf);
        timer.setTypeface(tf);

        popupController = new Popups(this, this, (Game) getApplicationContext(), getIntent());

        gameView = (GameView) findViewById(R.id.canvas);
        gameView.init(this, this, popupController, this);


        if (((Game) getApplicationContext()).getSettings().Music) {
            playMusic();
        }


    }


    public void playMusic() {
        AssetFileDescriptor afd = null;
        mp = new MediaPlayer();
        try {
            afd = getAssets().openFd("sounds/music.mp3");
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.setVolume(0.1f, 0.1f);
        mp.start();
    }

    public void playRightTouchSound() {
        AssetFileDescriptor afd = null;
        mp = new MediaPlayer();
        try {
            afd = getAssets().openFd("sounds/shot.ogg");
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.setVolume(3f, 3f);
        mp.start();
    }

    public void playWrongTouchSound() {
        AssetFileDescriptor afd = null;
        mp = new MediaPlayer();
        try {
            afd = getAssets().openFd("sounds/miss.ogg");
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.setVolume(3f, 3f);
        mp.start();
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

        right_touches.setText(diffs + "/5");
        tries.setText(moves + "/10");
    }

    @Override
    public void onDifferenceFound() {
        if (((Game) getApplicationContext()).getSettings().Vibro) {
            Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
        }
        if (((Game) getApplicationContext()).getSettings().Sound) {
            playRightTouchSound();
        }
    }

    @Override
    public void onWrongTouch() {
        if (((Game) getApplicationContext()).getSettings().Sound) {
            playWrongTouchSound();
        }
    }

    @Override
    protected void onStop() {
        timeWhenStopped = timer.getBase() - SystemClock.elapsedRealtime();
        timer.stop();
        stopWatch.pause();
        super.onStop();
        if (mp!=null)
            mp.pause();
        ((Game) getApplicationContext()).onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mp!=null)
            mp.stop();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        stopWatch.resume();
        timer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
        timer.start();
        super.onResume();
        if (mp!=null)
            mp.start();
    }


    @Override
    public void onGameStateChanged(StateController.GameState state) {

        if (state == StateController.GameState.InProgress) {
            timer.start();
            stopWatch.start();
        }
        else
        {
            stopWatch.stop();
            timer.stop();
            if (mp!=null) {
                mp.pause();
            }
            }
    }

    @Override
    public String getLevelTime() {
        return timer.getText().toString();
    }

    @Override
    public int getTimeSinceStart() {
        int seconds = (int) (stopWatch.getElapsedTimeSecs()+stopWatch.getElapsedTimeMin()*60);
        return seconds;
    }



   /* @Override
    public void onDestroy() {
        this.mWakeLock.release();
        super.onDestroy();
    }*/
}
