package com.adeco.finddifferences;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.io.IOException;
import java.io.InputStream;

public class GameView extends View {

    private Paint mPaint = new Paint();
    private Bitmap scaledBitmap, scaledBitmap2, img1, img2;
    private int scrwidth, scaledHeight, width, height;
    public GameView(Context context) {
        super(context);

        Resources res = this.getResources();


        AssetManager assetManager = context.getAssets();
        img1 = getBitmapFromAsset(assetManager, "pic_12.jpg" );
        img2 = getBitmapFromAsset(assetManager, "pic_12a.jpg" );
        width = img1.getWidth();
        height = img1.getHeight();


        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        scrwidth = display.getWidth();
        //Log.d("MyTAG", "ScreenW"+ String.valueOf(scrwidth));
        scaledHeight = scrwidth*height/width;
        scaledBitmap = Bitmap.createScaledBitmap(img1, scrwidth, scaledHeight, false);
        scaledBitmap2 = Bitmap.createScaledBitmap(img2, scrwidth, scaledHeight, false);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(scaledBitmap, 0, 0, mPaint);
        canvas.drawBitmap(scaledBitmap2, 0, scaledHeight, mPaint);

    }

    public static Bitmap getBitmapFromAsset(AssetManager mgr, String filePath) {


        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = mgr.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            // handle exception
        }

        return bitmap;
    }
}
