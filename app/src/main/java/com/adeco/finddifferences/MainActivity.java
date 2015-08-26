package com.adeco.finddifferences;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

import com.adeco.finddifferences.game.Game;


public class MainActivity extends Activity {

    Button startGameBtn, resumeGameBtn, settingsBtn;
    CheckBox vibroCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGameBtn = (Button) findViewById(R.id.startGame);
        resumeGameBtn = (Button) findViewById(R.id.resumeGame);
        settingsBtn = (Button) findViewById(R.id.settings);
        vibroCheck = (CheckBox) findViewById(R.id.vibro);


        OnClickListener onClickSettingBtn = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        };

        settingsBtn.setOnClickListener(onClickSettingBtn);

        OnClickListener onClickStartBtn = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                Game.getInstance().getSettings().Vibro = vibroCheck.isChecked();
                Game.getInstance().getLevelStorage().resetLevel();
                startActivity(intent);
            }
        };

        startGameBtn.setOnClickListener(onClickStartBtn);
        OnClickListener onClickResumeBtn = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                Game.getInstance().getSettings().Vibro = vibroCheck.isChecked();
                startActivity(intent);
            }
        };
        resumeGameBtn.setOnClickListener(onClickResumeBtn);
        vibroCheck.setChecked(Game.getInstance().getSettings().Vibro);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
