package com.mdeiml.secrethitler;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;

public class ChooseChancellorActivity extends Activity {

    private Game game;
    private String chancellor;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_chancellor);
        game = (Game)getIntent().getSerializableExtra("com.mdeiml.secrethitler.Game");
        chancellor = "";
        final Spinner chooseChancellor = (Spinner)findViewById(R.id.chooseChancellor);
        final Button ok = (Button)findViewById(R.id.chooseChancellorOk);
        final TextView message = (TextView)findViewById(R.id.chooseChancellorMessage);
        message.setText(game.getPresident()+" is president\nchoose your chancellor");
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(
            getApplicationContext(),
            android.R.layout.simple_spinner_dropdown_item,
            game.getElectableChancellors());
        chooseChancellor.setAdapter(adapter);
        chooseChancellor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                chancellor = parent.getItemAtPosition(pos).toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                chancellor = "";
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(game.setChancellor(chancellor)) {
                    Intent intent = new Intent(getApplicationContext(), ElectActivity.class);
                    intent.putExtra("com.mdeiml.secrethitler.Game",game);
                    startActivity(intent);
                }
            }
        });
    }

}
