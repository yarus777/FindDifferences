package com.adeco.finddifferences;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.adeco.finddifferences.game.Game;


public class MainActivity extends Activity {

    Button startGameBtn, resumeGameBtn, settingsBtn, fullVersion;
    SharedPreferences sPref;
    int entries = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGameBtn = (Button) findViewById(R.id.startGame);
        resumeGameBtn = (Button) findViewById(R.id.resumeGame);
        settingsBtn = (Button) findViewById(R.id.settings);
        fullVersion = (Button) findViewById(R.id.full_version);

        if ( ((Game) getApplicationContext()).getEntries() >= 2 ) {
            fullVersion.setVisibility(View.VISIBLE);


            OnClickListener onClickFullVersionBtn = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    String appPackageName = getResources().getString(R.string.full_version_package);
                    intent.setData(Uri.parse("market://details?id=" + appPackageName));
                    startActivity(intent);
                }
            };

            fullVersion.setOnClickListener(onClickFullVersionBtn);
        }


        Log.d("Game_lbl",""+ ((Game) getApplicationContext()).getEntries());




        OnClickListener onClickSettingBtn = new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                //startActivity(intent);

                Dialog alert = new Dialog(MainActivity.this);
                alert.setCanceledOnTouchOutside(false);
                alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alert.setContentView(R.layout.dialog_settings);
                alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                Button fullVersionBtn;
                fullVersionBtn = (Button) alert.findViewById(R.id.full_version_btn);
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

                final CheckBox vibro_cb, music_cb, sound_cb;

                vibro_cb = (CheckBox) alert.findViewById(R.id.vibro_cb);
                music_cb = (CheckBox) alert.findViewById(R.id.music_cb);
                sound_cb = (CheckBox) alert.findViewById(R.id.sound_cb);

                vibro_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        ((Game) getApplicationContext()).getSettings().Vibro = vibro_cb.isChecked();
                    }
                });


                music_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        ((Game) getApplicationContext()).getSettings().Music = music_cb.isChecked();
                    }
                });


                sound_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        ((Game) getApplicationContext()).getSettings().Sound = sound_cb.isChecked();
                    }
                });

                vibro_cb.setChecked(((Game) getApplicationContext()).getSettings().Vibro);
                music_cb.setChecked(((Game) getApplicationContext()).getSettings().Music);
                sound_cb.setChecked(((Game) getApplicationContext()).getSettings().Sound);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(alert.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;

                alert.show();

                alert.getWindow().setAttributes(lp);
            }
        };

        settingsBtn.setOnClickListener(onClickSettingBtn);

        OnClickListener onClickStartBtn = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LevelsActivity.class);
                startActivity(intent);

            }
        };

        startGameBtn.setOnClickListener(onClickStartBtn);
        OnClickListener onClickResumeBtn = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);


            }
        };
        resumeGameBtn.setOnClickListener(onClickResumeBtn);
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
