package com.adeco.finddifferences.game.popups;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_win, null);
        builder.setView(view);
        Button next_lvl_btn = (Button) view.findViewById(R.id.next_lvl_btn);
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

        final AlertDialog alert = builder.create();

        next_lvl_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                alert.dismiss();
                Intent intent = new Intent(context, GameActivity.class);
                context.startActivity(intent);
            }
        });
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
                                Game.getInstance().getSettings().ShowFullAppDialog = true;
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
                                Game.getInstance().getSettings().ShowFullAppDialog = false;
                                dialog.cancel();

                            }
                        })
                .setNeutralButton("Later",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Game.getInstance().getSettings().ShowFullAppDialog = true;
                                dialog.cancel();

                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public void showCompletedPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_complete, null);
        builder.setView(view);
        Button close_btn = (Button) view.findViewById(R.id.close_dlg_btn);
       /* builder.setTitle("You won!")
                .setMessage(R.string.last_level_win)
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });*/
        final AlertDialog alert = builder.create();
        close_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
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

    public Popups(Context context) {
        this.context = context;
    }
}
