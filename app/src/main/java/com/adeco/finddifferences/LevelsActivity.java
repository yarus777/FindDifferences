package com.adeco.finddifferences;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.adeco.finddifferences.game.Game;
import com.adeco.finddifferences.game.LevelImageAdapter;
import com.adeco.finddifferences.game.levels.Level;


import java.util.ArrayList;
import java.util.List;

public class LevelsActivity extends Activity {
    List<String> listPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        listPath = new ArrayList<String>();
        List<Integer> stars = new ArrayList<Integer>();
        for (Level level: ((Game) getApplicationContext()).getLevelStorage().levels) {
            listPath.add(level.getImg1());
            stars.add(level.getStarsNum());
        }





        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new LevelImageAdapter(this, listPath, stars));

        gridview.setOnItemClickListener(gridviewOnItemClickListener);

    }


    private GridView.OnItemClickListener gridviewOnItemClickListener = new GridView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position,
                                long id) {
            Log.d("MY_TAG",""+position);
            ((Game) getApplicationContext()).getLevelStorage().setCurrentLevel(position);
            Intent i = new Intent(getApplicationContext(), GameActivity.class);
            startActivity(i);
        }
    };

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
