package com.ikramjamro.tiktiktoegame;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // 0-X
    // 1-0
    int activePlayer = 0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
        //      State meanings:
        //      0-X
        //      1-0
        //      2-Null

    int[][] WinPositions={{0,1,2} , {3,4,5} , {6,7,8} ,
                          {0,3,6} ,{1,4,7} ,  {2,5,8} ,
                            {0,4,8} , {2,4,6}
    };
    boolean gameActive =true;
    public void player_tap(View view) {
        TextView status = findViewById(R.id.status);
        ImageView img = (ImageView) view;
        if (!gameActive) {
            gameReset(view);
            status.setText("Tap To Play!");
            return;
        }
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (gameState[tappedImage] == 2 && gameActive) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.cross);
                activePlayer = 1;
                status.setText("Player 2's Turn , Tap to Play..");
            } else {
                status.setText("Player 1's Turn , Tap to Play..");
                img.setImageResource(R.drawable.zero);
                activePlayer = 0;
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        // Check if any player has won
        for (int[] winPosition : WinPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                // somebody is win
                String WinnerStr;
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    WinnerStr = "Player1 has Won the game , Tap to Restart!";
                } else {
                    WinnerStr = "Player2 has Won the game, , Tap to Restart!";
                }
                // update the status bar
                status.setText(WinnerStr);
            }
        }
        if (gameActive) {
            int count = 0;
            for (int i = 0; i < gameState.length; i++) {
                if (gameState[i] != 2) {
                    ++count;
                }
            }
            if (count == 9) {
                status.setText("Ops! it's a draw , Tap to Restart");
                gameActive = false;
            }
        }
        }
    public void gameReset(View view){
        gameActive=true;
        activePlayer=0;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView10)).setImageResource(0);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);}

    }