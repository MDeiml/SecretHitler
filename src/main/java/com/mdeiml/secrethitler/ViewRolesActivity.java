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
        final TextView name = (TextView)findViewById(R.id.roleName);
        final ImageView img = (ImageView)findViewById(R.id.roleImg);
        final TextView message = (TextView)findViewById(R.id.roleMessage);
        final Game game = (Game)getIntent().getSerializableExtra("com.mdeiml.secrethitler.Game");
        name.setText(game.getName(0));
        currentP = 0;
        open = false;
        img.setOnClickListener(new ImageView.OnClickListener() {
            public void onClick(View v) {
                if(open) {
                    currentP++;
                    if(currentP < game.getNumPlayers()) {
                        img.setImageDrawable(getResources().getDrawable(R.drawable.party_secret));
                        open = false;
                        message.setText("Tap to view identity");
                        name.setText(game.getName(currentP));
                    } //TODO: else
                }else {
                    open = true;
                    switch(game.getRole(currentP)) {
                        case 'l':
                            img.setImageDrawable(getResources().getDrawable(R.drawable.party_liberal));
                            message.setText("Tap to hide identity");
                            break;
                        case 'f':
                            img.setImageDrawable(getResources().getDrawable(R.drawable.party_fascist));
                            String fascists = "";
                            String hitler = "";
                            for(int i = 0; i < game.getNumPlayers(); i++) {
                                if(game.getRole(i) == 'f' && i != currentP) {
                                    fascists += game.getName(i)+", ";
                                }else if(game.getRole(i) == 'h') {
                                    hitler = game.getName(i);
                                }
                            }
                            fascists = fascists.substring(0, fascists.length() - 2);
                            String msg = "The other fascists are: "+fascists+"\n"+hitler+" is Hitler\nTap to hide identity";
                            message.setText(msg);
                            break;
                        case 'h':
                            img.setImageDrawable(getResources().getDrawable(R.drawable.party_hitler));
                            message.setText("Tap to hide identity");
                            break;
                    }
                }
            }
        });
    }
}
