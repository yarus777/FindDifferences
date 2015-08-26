package com.adeco.finddifferences;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.adeco.finddifferences.game.Game;

public class SettingsActivity extends Activity  {

    private Button fullVersionBtn;
    public CheckBox vibro_cb, music_cb, sound_cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        vibro_cb = (CheckBox) findViewById(R.id.vibro_cb);
        music_cb = (CheckBox) findViewById(R.id.music_cb);
        sound_cb = (CheckBox) findViewById(R.id.sound_cb);
        fullVersionBtn = (Button) findViewById(R.id.full_version_btn);


        View.OnClickListener onClickFullVersionBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String appPackageName = getResources().getString(R.string.full_version_package);
                intent.setData(Uri.parse("market://details?id=" + appPackageName));
                startActivity(intent);
            }
        };

        fullVersionBtn.setOnClickListener(onClickFullVersionBtn);

        vibro_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Game.getInstance().getSettings().Vibro = vibro_cb.isChecked();
            }
        });


        music_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Game.getInstance().getSettings().Music = music_cb.isChecked();
            }
        });


        sound_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Game.getInstance().getSettings().Sound = sound_cb.isChecked();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
    protected void onResume() {
        super.onResume();
        vibro_cb.setChecked(Game.getInstance().getSettings().Vibro);
        music_cb.setChecked(Game.getInstance().getSettings().Music);
        sound_cb.setChecked(Game.getInstance().getSettings().Sound);
    }

}
