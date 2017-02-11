package ddev.lightsoff;

import android.util.Log;

/**
 * Created by Danish Shah on 17/03/2016.
 * This is the solution generator for a board size of nxn.
 */
public class Solution {

    int a[], b[], n;
    String soln = null;
    public StringBuilder inputstring = new StringBuilder();
    public StringBuilder output = new StringBuilder();

    public Solution(int c[], int n) {
        this.n = n;
        this.a = new int[n * n];
        this.b = new int[n * n];
        for (int i = 0; i < a.length; i++) {
            this.a[i] = c[i];
            b[i] = c[i];
        }

    }

    /**
     * This method creates a string whose combination is to be generated.
     * Here the String generation is a character array starting from A to whatever the board size is.
     */

    public void parseString() {
        inputstring = new StringBuilder();
        for (int i = 0; i < n * n; ++i)
            inputstring.append(Character.toChars(i + 65));
        System.out.println(inputstring);
    }

    /**
     * This is a recursive function which generates all the possible combination for the string which is generated above.
     * Each combination generated is checked.
     */

    public void combine(int start) {
        try {
            for (int i = start; i < inputstring.length() && !win(); ++i) {
                output.append(inputstring.charAt(i));
                check(output + "");
                if (i < inputstring.length())
                    combine(i + 1);
                output.setLength(output.length() - 1);
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This function checks if the combination give you the solution.
     * The combination generated above is broken into single move and toggles the move.
     * @param s
     */

    public void check(String s) {
        String s2;

        //System.out.println(s);

        for (int i = 0; i < n * n; i++)
            b[i] = a[i];

        for (int i = 0; i < s.length(); i++) {
            s2 = ""+(s.charAt(i)-65);
            //System.out.println(s2);
            toggle(Integer.parseInt(s2));
        }

        if (win()) {
            soln = s.toString();
            System.out.println("Soln is " + soln);
            //Log.d("LightsOff!", "Soln is " + soln);
        }
    }

    public void toggle(int j) {

        assign(j);
        if (j % n != (n - 1) && j + 1 < b.length) {
            assign(j + 1);
        }
        if (j % n != 0 && j - 1 >= 0) {
            assign(j - 1);
        }
        if (j + n < b.length) {
            assign(j + n);
        }
        if (j - n >= 0) {
            assign(j - n);
        }

        //showInitial();
    }

    public void assign(int p) {
        if (b[p] == 1) {
            b[p] = 0;
        } else {
            b[p] = 1;
        }
    }

    public boolean win() {
        int count = 0, i;
        for (i = 0; i < n * n; i++) {
            if (b[i] == 0)
                count++;
        }
        if (count == n * n) {
            return true;
        } else
            return false;

    }

    public void showInitial() {
        for (int i = 0; i < n * n; i++) {
            Log.i("LightsOff!", b[i] + "\t");
            //if (i % n == 1)
            //System.out.println();
        }
    }

    public String getSolution() {
        if (soln != null)
            return soln;
        else
            return "No Solution";
    }

}
