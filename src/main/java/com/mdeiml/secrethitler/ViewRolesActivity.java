package com.mdeiml.secrethitler;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

public class ViewRolesActivity extends Activity {
    
    private int currentP;
    private boolean open;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_roles);
        ((TextView)findViewById(R.id.roleName)).setText("TEST_NAME_DO_NOT_READ"); //TODO
        final ImageView img = (ImageView)findViewById(R.id.roleImg);
        final TextView message = (TextView)findViewById(R.id.roleMessage);
        final Game game = (Game)getIntent().getSerializableExtra("com.mdeiml.secrethitler.Game");
        currentP = 0;
        open = false;
        img.setOnClickListener(new ImageView.OnClickListener() {
            public void onClick(View v) {
                if(open) {
                    currentP++;
                    if(currentP < game.getNumPlayers()) {
                        img.setImageDrawable(getResources().getDrawable(R.drawable.party_secret));
                        open = false;
                    }
                }else {
                    open = true;
                    switch(game.getRole(currentP)) {
                        case 'l':
                            img.setImageDrawable(getResources().getDrawable(R.drawable.party_liberal));
                            break;
                        case 'f':
                            img.setImageDrawable(getResources().getDrawable(R.drawable.party_fascist));
                            break;
                        case 'h':
                            img.setImageDrawable(getResources().getDrawable(R.drawable.party_hitler));
                            break;
                    }
                }
            }
        });
    }
}
