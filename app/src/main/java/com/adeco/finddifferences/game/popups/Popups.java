package com.adeco.finddifferences.game.popups;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.adeco.finddifferences.MainActivity;
import com.adeco.finddifferences.R;
import com.adeco.finddifferences.game.Game;
import com.adeco.finddifferences.game.interfaces.TimeCounter;
import com.adeco.finddifferences.game.states.StateController;


public class Popups implements PopupController {

    private final Intent intent;
    private Context context;
    private TimeCounter timeCounter;
    private Game game;

    @Override
    public void showLosePopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("You lost!")
                .setMessage("Game over!")
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Intent intent = new Intent(context, MainActivity.class);
                                context.startActivity(intent);
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public void showWinPopup() {
        final Dialog alert = new Dialog(context);
        alert.setCanceledOnTouchOutside(false);
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alert.setContentView(R.layout.dialog_win);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button next_lvl_btn = (Button) alert.findViewById(R.id.next_lvl_btn);
        Button menu_go_btn = (Button) alert.findViewById(R.id.menu_go_btn);
        Button restart_lvl_btn = (Button) alert.findViewById(R.id.restart_lvl);
        TextView time_txt = (TextView) alert.findViewById(R.id.lvl_time);

        ImageView star2 = (ImageView) alert.findViewById(R.id.star2);
        ImageView star3 = (ImageView) alert.findViewById(R.id.star3);
        int starsCount = game.getScoreController().getStarsCount();
        if (starsCount == 1)
        {
            star2.setImageResource(R.drawable.starempty);
            star3.setImageResource(R.drawable.starempty);
        }
        else if (starsCount == 2) {
            star3.setImageResource(R.drawable.starempty);

        }

        time_txt.setText(timeCounter.getLevelTime());

        next_lvl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                game.getLevelStorage().goToNextLevel();
                //Intent intent = new Intent(context, GameActivity.class);
                context.startActivity(intent);
            }
        });

        menu_go_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });

        restart_lvl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                //Intent intent = new Intent(context, GameActivity.class);
                context.startActivity(intent);
            }
        });



        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alert.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;


        alert.show();

        alert.getWindow().setAttributes(lp);
        /*builder.setTitle("You won!")
                .setMessage("Go to next level!")
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                    Intent intent = new Intent(context, GameActivity.class);
                                    context.startActivity(intent);
                            }
                        });*/


    }

    @Override
    public void showFullVersionPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Full version")
                .setMessage("Do you want install full version?")
                .setCancelable(false)
                .setPositiveButton("Install",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                game.getSettings().ShowFullAppDialog = true;
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                String appPackageName = context.getResources().getString(R.string.full_version_package);
                                intent.setData(Uri.parse("market://details?id=" + appPackageName));
                                context.startActivity(intent);
                                dialog.cancel();

                            }
                        })
                .setNegativeButton("No, thanks",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                game.getSettings().ShowFullAppDialog = false;
                                dialog.cancel();

                            }
                        })
                .setNeutralButton("Later",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                game.getSettings().ShowFullAppDialog = true;
                                dialog.cancel();

                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public void showCompletedPopup() {
       /* builder.setTitle("You won!")
                .setMessage(R.string.last_level_win)
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });*/

        final Dialog alert = new Dialog(context);
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alert.setContentView(R.layout.dialog_complete);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button close_btn = (Button) alert.findViewById(R.id.close_dlg_btn);

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String appPackageName = context.getResources().getString(R.string.full_version_package);
                intent.setData(Uri.parse("market://details?id=" + appPackageName));
                context.startActivity(intent);
                alert.dismiss();
            }
        });
        alert.show();
    }

    @Override
    public void onGameStateChanged(StateController.GameState state) {
        switch (state) {
            case Win:
                showWinPopup();
                break;
            case Lose:
                showLosePopup();
                break;
            case Completed:
                showCompletedPopup();
                break;
        }

    }

    public Popups(Context context, TimeCounter timeCounter, Game applicationContext, Intent intent) {
        this.context = context;
        this.timeCounter = timeCounter;
        this.game = applicationContext;
        this.intent = intent;
    }
}
