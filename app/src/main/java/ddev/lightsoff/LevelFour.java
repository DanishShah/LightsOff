package ddev.lightsoff;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Danish Shah on 20/03/2016.
 * This is a bit difficult and interesting too.
 * For some cases the solution doesn't exist.
 */
public class LevelFour extends Fragment {

    Button btn[] = new Button[16];
    TextView m;
    String solution;
    int move = 0, n = 4;
    int a[] = new int[16];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.level_four, container, false);

        btn[0] = (Button) v.findViewById(R.id.btn_1);
        btn[1] = (Button) v.findViewById(R.id.btn_2);
        btn[2] = (Button) v.findViewById(R.id.btn_3);
        btn[3] = (Button) v.findViewById(R.id.btn_4);
        btn[4] = (Button) v.findViewById(R.id.btn_5);
        btn[5] = (Button) v.findViewById(R.id.btn_6);
        btn[6] = (Button) v.findViewById(R.id.btn_7);
        btn[7] = (Button) v.findViewById(R.id.btn_8);
        btn[8] = (Button) v.findViewById(R.id.btn_9);
        btn[9] = (Button) v.findViewById(R.id.btn_10);
        btn[10] = (Button) v.findViewById(R.id.btn_11);
        btn[11] = (Button) v.findViewById(R.id.btn_12);
        btn[12] = (Button) v.findViewById(R.id.btn_13);
        btn[13] = (Button) v.findViewById(R.id.btn_14);
        btn[14] = (Button) v.findViewById(R.id.btn_15);
        btn[15] = (Button) v.findViewById(R.id.btn_16);

        final ImageView reset = (ImageView) v.findViewById(R.id.btn_reset);
        ImageView soln = (ImageView) v.findViewById(R.id.btn_soln);

        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font.ttf");

        m = (TextView) v.findViewById(R.id.move);
        m.setTypeface(tf);

        randomize();

        final LightsOff lightsOff = new LightsOff(btn, a, 4, m, getActivity());


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomize();
                lightsOff.setMatrix(a);
                lightsOff.initBoard();
            }
        });

        soln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Solution soln = new Solution(lightsOff.getMatrix(), 4);
                soln.parseString();
                soln.showInitial();
                soln.combine(0);
                if (soln.getSolution().equals("No Solution")){
                    Snackbar.make(v, "No Possible Solution. Randomizing!", Snackbar.LENGTH_SHORT).show();
                    randomize();
                    lightsOff.setMatrix(a);
                    lightsOff.initBoard();
                }else {
                    solution = soln.getSolution();
                    showSolution(solution);
                }
            }
        });

        lightsOff.initBoard();
        lightsOff.initListener();
        lightsOff.showMatrix();

        return v;
    }

    public void randomize() {
        Random r = new Random();
        for (int i = 0; i < a.length; i++)
            a[i] = r.nextInt(100) % 2;
        move = 0;
        m.setText(move + "");
        //showMatrix();
    }

    public void showSolution(String solution) {
        if (!solution.equals("No Solution")) {
            for (int i = 0; i < solution.length(); i++) {
                btn[Integer.parseInt(solution.charAt(i)-65+"")].setBackgroundResource(R.drawable.light_soln);
            }
        }
    }
}
