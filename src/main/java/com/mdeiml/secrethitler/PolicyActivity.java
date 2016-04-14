package com.mdeiml.secrethitler;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;

public class PolicyActivity extends Activity {

    private Game game;
    private TextView message;
    private ImageView p0;
    private ImageView p1;
    private ImageView p2;
    private boolean revealed;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.policy);
        game = (Game)getIntent().getSerializableExtra("com.mdeiml.secrethitler.Game");
        message = (TextView)findViewById(R.id.policyMessage);
        message.setText(game.getPresident()+" is president\nTap to view policies");
    }
}
