package com.adeco.finddifferences;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.adeco.finddifferences.game.LevelImageAdapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LevelsActivity extends Activity {
    ArrayList<String> listPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);


        AssetManager assetManager = getResources().getAssets();
        try {
            String[] files = assetManager.list("lvl_images");
            listPath = new ArrayList<String>();
            for (String strImageName : files) {
                String pathAssets = "lvl_images" + File.separator
                        + strImageName;
                listPath.add(pathAssets);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }




        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new LevelImageAdapter(this, listPath));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_levels, menu);
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
}
