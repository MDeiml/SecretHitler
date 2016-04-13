package com.mdeiml.secrethitler;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

public class ElectActivity extends Activity {

    private int currentP;
    private int agree;
    private int disagree;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elect);
        final Game game = (Game)getIntent().getSerializableExtra("com.mdeiml.secrethitler.Game");
        final TextView name = (TextView)findViewById(R.id.electName);
        name.setText(game.getName(0)+" is voting\n"+game.getPresident()+" wants "+game.getChancellor()+" to be chancellor");
        currentP = 0;
        agree = 0;
        disagree = 0;
        ((ImageView)findViewById(R.id.electYes)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                agree++;
                currentP++;
                if(currentP >= game.getNumPlayers()) {
                    endElection();
                }else {
                    if(game.isDead(currentP) || game.getName(currentP).equals(game.getPresident())) {
                        onClick(v);
                    }else {
                        name.setText(game.getName(currentP)+" is voting\n"+game.getPresident()+" wants "+game.getChancellor()+" to be chancellor");
                    }
                }
            }
        });
        ((ImageView)findViewById(R.id.electNo)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                disagree++;
                currentP++;
                if(currentP >= game.getNumPlayers()) {
                    endElection();
                }else {
                    if(game.isDead(currentP) || game.getName(currentP).equals(game.getPresident())) {
                        onClick(v);
                    }else {
                        name.setText(game.getName(currentP)+" is voting\n"+game.getPresident()+" wants "+game.getChancellor()+" to be chancellor");
                    }
                }
            }
        });
    }

    public void endElection() {
        Toast.makeText(getApplicationContext(), agree+" <-> "+disagree,Toast.LENGTH_LONG).show(); //TODO
    }
}
