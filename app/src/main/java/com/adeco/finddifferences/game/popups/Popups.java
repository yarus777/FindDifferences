package com.adeco.finddifferences.game.popups;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.adeco.finddifferences.MainActivity;
import com.adeco.finddifferences.game.Game;
import com.adeco.finddifferences.game.states.GameStateHandler;
import com.adeco.finddifferences.game.states.StateController;


public class Popups implements PopupController, GameStateHandler {

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
                                //dialog.cancel();
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
        builder.setTitle("You lost!")
                .setMessage("Game over!")
                .setCancelable(false)
                .setPositiveButton("Go to next level",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Game.getInstance().getLevelStorage().goToNextLevel();
                                
                                //dialog.cancel();
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
