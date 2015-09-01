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
import android.widget.TextView;

import com.adeco.finddifferences.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class LevelImageAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<String> griRowItems;

    public LevelImageAdapter(Context context, ArrayList<String> griRowItems) {
        this.context = context;
        this.griRowItems = griRowItems;
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
            //LayoutInflater inflater = getLayoutInflater();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            grid = inflater.inflate(R.layout.cellgrid, parent, false);
        } else {
            grid = (View) convertView;
        }


        TextView textView = (TextView) grid.findViewById(R.id.textpart);
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

        textView.setText("Картинка " + String.valueOf(position));
        return grid;
    }

   // public Integer[] mThumbIds = {
   //         R.drawable.starempty, R.drawable.starfull, R.drawable.miss };
}
