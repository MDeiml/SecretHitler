package com.mdeiml.secrethitler;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

public class ViewRolesActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_roles);
        ((TextView)findViewById(R.id.roleName)).setText("TEST_NAME_DO_NOT_READ"); //TODO
        final ImageView img = (ImageView)findViewById(R.id.roleImg);
        img.setOnClickListener(new ImageView.OnClickListener() {
            public void onClick(View v) {
                img.setImageDrawable(getResources().getDrawable(R.drawable.party_hitler));
            }
        });
    }
}
