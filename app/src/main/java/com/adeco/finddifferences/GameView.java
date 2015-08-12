package com.adeco.finddifferences;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.adeco.finddifferences.game.Game;
import java.util.ArrayList;
import java.util.List;

public class GameView extends View  implements View.OnTouchListener {

    List<Point> points = new ArrayList<Point>();
    private Paint mPaint = new Paint();

    private Game game;


    public GameView(Context context) {

        super(context);
      
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
		
		game = new Game(context);
        this.setOnTouchListener(this);

        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        

        if (game != null ) {
           game.draw(canvas); 
        } 
        for(Point p: points){
            canvas.drawCircle(p.x, p.y, 40, mPaint);
        }

    }


    public boolean onTouch(View view, MotionEvent event)
    {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                Point p = new Point();
                p.x = (int)event.getX();
                p.y = (int)event.getY();
                points.add(p);
                invalidate();

            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                break;
            }
        }
        invalidate();
        return true;
    }

}
