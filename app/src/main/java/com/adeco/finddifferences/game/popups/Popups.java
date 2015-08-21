package com.adeco.finddifferences.game.popups;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import com.adeco.finddifferences.GameActivity;
import com.adeco.finddifferences.MainActivity;
import com.adeco.finddifferences.R;
import com.adeco.finddifferences.game.Game;
import com.adeco.finddifferences.game.states.StateController;


public class Popups implements PopupController {

    private Context context;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("You won!")
                .setMessage("Go to next level!")
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                if (Game.getInstance().getLevelStorage().goToNextLevel())
                                //Game.getInstance().startLevel();
                                {
                                    Intent intent = new Intent(context, GameActivity.class);
                                context.startActivity(intent);
                                }
                                else {
                                    Intent intent = new Intent(context, MainActivity.class);
                                    context.startActivity(intent);
                                }
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
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
                                dialog.cancel();
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                String appPackageName = context.getResources().getString(R.string.full_version_package);
                                intent.setData(Uri.parse("market://details?id=" + appPackageName));
                                context.startActivity(intent);

                            }
                        })
                .setNegativeButton("No,thanks",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        })
                .setNeutralButton("Later",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        });
        AlertDialog alert = builder.create();
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
            case InProgress:
                break;
        }

    }

    public Popups(Context context) {
        this.context = context;
    }
}
