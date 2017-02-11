package ddev.lightsoff;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Danish Shah on 17/03/2016.
 * This is where the whole game logic goes.
 * Its a simple class which accepts a Button array, integer array, and board size
 */
public class LightsOff {

    Button btn[];
    TextView m;
    Context cxt;
    int a[],n, move;

    public LightsOff(Button btn[], int a[], int n, TextView m, Context cxt) {
        this.n = n;
        this.m = m;
        this.btn = new Button[n*n];
        this.a = new int[n*n];
        this.cxt = cxt;
        move = 0;
        for(int i=0;i<n*n;i++) {            //initializing the arrays
            this.btn[i] = btn[i];
            this.a[i] = a[i];
        }
    }

    public void setMatrix(int c[]) {
        for (int i = 0; i < n * n; i++) {
            a[i] = c[i];
        }
        move=0;
    }

    public int[] getMatrix(){
        return a;
    }

    /** This method initializes all the buttons to form an initial board.
     *  The button are set to ON if the index corresponding to it is 1 in the integer array.
     *  else it is set to OFF.
     */

    public void initBoard() {
        for (int i = 0; i < btn.length; i++) {
            btn[i].setEnabled(true);
            switch (a[i]) {
                case 0:
                    btn[i].setBackgroundResource(R.drawable.light_off);
                    break;
                case 1:
                    btn[i].setBackgroundResource(R.drawable.light_on);
                    break;
            }
        }
    }

    /**
     * Sets listeners to the buttons
     */

    public void initListener() {
        int j;
        for (int i = 0; i < btn.length; i++) {
            j = i;
            final int finalJ = j;
            btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initMoves(finalJ);
                    initBoard();
                    move++;
                    m.setText(move + "");
                    //showMatrix();
                    if (win()) {
                        disableBoard();
                        //showWinDialog();
                    }
                }
            });

        }
    }

    /**
     * This is where the logic of the game is.
     * It takes in j as the index of the button which is to be toggled.
     * It automatically toggles all the adjecents lights too.
     * @param j
     */

    public void initMoves(int j) {
        toggle(j);
        //Log.i("LightsOff", j + "");
        if (j % n != (n - 1) && j + 1 < a.length) {
            toggle(j + 1);
            //Log.i("LightsOff", (j + 1) + "");
        }
        if (j % n != 0 && j - 1 >= 0) {
            toggle(j - 1);
            //Log.i("LightsOff", (j - 1) + "");
        }
        if (j + n < a.length) {
            toggle(j + n);
            //Log.i("LightsOff", (j + 3) + "");
        }
        if (j - n >= 0) {
            toggle(j - n);
            //Log.i("LightsOff", (j - 3) + "");
        }
    }

    public void disableBoard(){
        for(int i =0 ;i<btn.length; i++)
            btn[i].setEnabled(false);
    }

    /**
     * This checks weather the game is over i.e all the lights have been switched OFF.
     * All the toggle made were reflected to the integer array.
     * Checking the integer array after every move makes the task easier.
     */

    public boolean win() {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0)
                count++;
        }
        if (count == a.length)
            return true;
        else
            return false;
    }

    public void showMatrix(){
        for (int i = 0; i < n * n; i++) {
            Log.i("LightsOff!", a[i] + "\t");
            //if (i % 3 == 1)
                //System.out.println();
        }
    }

    /**
     * This method checks for the integer array and toggles the index according to the integer it contains.
     */

    public void toggle(int p) {
        if (a[p] == 0)
            a[p] = 1;
        else
            a[p] = 0;
    }
}
