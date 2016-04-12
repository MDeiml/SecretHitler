package com.mdeiml.secrethitler;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.content.*;
import android.view.*;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final EditText playerNames = (EditText)findViewById(R.id.playerNames);
        final Button playerNamesOk = (Button)findViewById(R.id.playerNamesOk);
        playerNamesOk.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                String[] names = playerNames.getText().toString().split(",");
                for(int i = 0; i < names.length; i++) {
                    names[i] = names[i].trim();
                }
                Intent intent = new Intent(getApplicationContext(), ViewRolesActivity.class);
                intent.putExtra("com.mdeiml.secrethitler.Game", new Game(names));
                startActivity(intent);
            }
        });
    }
}
