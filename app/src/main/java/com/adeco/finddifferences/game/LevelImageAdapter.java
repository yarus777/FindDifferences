package com.adeco.finddifferences.game;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.adeco.finddifferences.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.List;

public class LevelImageAdapter extends BaseAdapter{

    private Context context;
    private List<String> griRowItems;
    private List<Integer> stars_num;

    public LevelImageAdapter(Context context, List<String> griRowItems, List<Integer> stars_num) {
        this.context = context;
        this.griRowItems = griRowItems;
        this.stars_num = stars_num;
    }

    @Override
    public int getCount() {
        return griRowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return griRowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;

        if (convertView == null) {
            grid = new View(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            grid = inflater.inflate(R.layout.cellgrid, parent, false);
        } else {
            grid = (View) convertView;
        }

        ImageView star_1 = (ImageView) grid.findViewById(R.id.star_1);
        ImageView star_2 = (ImageView) grid.findViewById(R.id.star_2);
        ImageView star_3 = (ImageView) grid.findViewById(R.id.star_3);

        if (stars_num.get(position) == 3) {
            star_1.setImageResource(R.drawable.starfull);
            star_2.setImageResource(R.drawable.starfull);
            star_3.setImageResource(R.drawable.starfull);
        }
        else if (stars_num.get(position) == 2) {
            star_1.setImageResource(R.drawable.starfull);
            star_2.setImageResource(R.drawable.starfull);
        }
        else if (stars_num.get(position) == 1)
            star_1.setImageResource(R.drawable.starfull);
        AssetManager assetManager = context.getResources().getAssets();

        ImageView imageView = (ImageView) grid.findViewById(R.id.level_img);
        BufferedInputStream buf = null;
        try {
            String path = griRowItems.get(position);
            buf = new BufferedInputStream((assetManager.open(path)));
            Bitmap bitmap = BitmapFactory.decodeStream(buf);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return grid;
    }

}
