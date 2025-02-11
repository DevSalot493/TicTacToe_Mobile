package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    boolean gameOn = true;
    boolean interactable = true;
    int currPlayer = 0;
    int[] boxVal = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPatterns = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};


    public void boxTouched(View view)
    {
        ImageView img = (ImageView) view;
        int currBox = Integer.parseInt(img.getTag().toString());
        if(boxVal[currBox] == 2 && interactable)
        {
            boxVal[currBox] = currPlayer;
            img.setTranslationY(-1000f);
            if(currPlayer == 0)
            {
                img.setImageResource(R.drawable.tic_x);
                currPlayer = 1;
                TextView res = findViewById(R.id.res);
                res.setText(" Player 2 ");
            }
            else
            {
                img.setImageResource(R.drawable.tic_o);
                currPlayer = 0;
                TextView res = findViewById(R.id.res);
                res.setText(" Player 1 ");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        for(int[] i: winPatterns)
        {
            if(boxVal[i[0]] == boxVal[i[1]] && boxVal[i[1]] == boxVal[i[2]] && boxVal[i[0]]!=2)
            {
                String win;
                gameOn = false;
                interactable = false;
                if(boxVal[i[0]] == 0)
                {
                    win = " Player 1 Wins!! ";
                }
                else
                {
                    win = " Player 2 Wins!! ";
                }
                TextView res = findViewById(R.id.res);
                res.setText(win);
                return;
            }
        }

        boolean tie = true;
        for(int i = 0; i < boxVal.length; i++)
        {
            if (boxVal[i] == 2)
            {
                tie = false;
                break;
            }
        }
        if(tie)
        {
            TextView res = findViewById(R.id.res);
            res.setText(" Tie!! ");
            gameOn = false;
        }
    }


    public void reset(View view)
    {
        gameOn = true;
        interactable=true;
        currPlayer = 0;
        for(int i = 0; i < boxVal.length; i++)
        {
            boxVal[i] = 2;
        }

        ((ImageView)findViewById(R.id.box0)).setImageResource(0);
        ((ImageView)findViewById(R.id.box1)).setImageResource(0);
        ((ImageView)findViewById(R.id.box2)).setImageResource(0);
        ((ImageView)findViewById(R.id.box3)).setImageResource(0);
        ((ImageView)findViewById(R.id.box4)).setImageResource(0);
        ((ImageView)findViewById(R.id.box5)).setImageResource(0);
        ((ImageView)findViewById(R.id.box6)).setImageResource(0);
        ((ImageView)findViewById(R.id.box7)).setImageResource(0);
        ((ImageView)findViewById(R.id.box8)).setImageResource(0);

        TextView res = findViewById(R.id.res);
        res.setText(" Player 1 ");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
