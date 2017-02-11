package ddev.lightsoff;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Danish on 17/03/2016.
 * A simple guide and a free board for the user to learn the game logic.
 */
public class HowTo extends Fragment {

    Button btn[] = new Button[9];
    int a[] = new int[9];
    int n=3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.how_to, container, false);

        Animation animation_in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        final Animation animation_out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);

        final RelativeLayout guide1 = (RelativeLayout) v.findViewById(R.id.guide_1);
        guide1.setAnimation(animation_in);

        btn[0] = (Button) v.findViewById(R.id.btn_1);
        btn[1] = (Button) v.findViewById(R.id.btn_2);
        btn[2] = (Button) v.findViewById(R.id.btn_3);
        btn[3] = (Button) v.findViewById(R.id.btn_4);
        btn[4] = (Button) v.findViewById(R.id.btn_5);
        btn[5] = (Button) v.findViewById(R.id.btn_6);
        btn[6] = (Button) v.findViewById(R.id.btn_7);
        btn[7] = (Button) v.findViewById(R.id.btn_8);
        btn[8] = (Button) v.findViewById(R.id.btn_9);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "font.ttf");

        TextView guide_text = (TextView) v.findViewById(R.id.guide_text_1);
        guide_text.setTypeface(tf);

        Button got_it = (Button) v.findViewById(R.id.btn_got_it);
        got_it.setTypeface(tf);
        got_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guide1.startAnimation(animation_out);
                guide1.setVisibility(View.GONE);
                enableBoard();
            }
        });

        for(int i=0;i<a.length;i++){
            if(i%2==0)
                a[i]=0;
            else
                a[i]=1;
        }
        a[4] = 1;

        initBoard();
        initListener();
        disableBoard();



        return v;
    }

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
                }
            });

        }
    }

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

    public void enableBoard(){
        for(int i =0 ;i<btn.length; i++)
            btn[i].setEnabled(true);
    }

    public void toggle(int p) {
        if (a[p] == 0)
            a[p] = 1;
        else
            a[p] = 0;
    }
}
