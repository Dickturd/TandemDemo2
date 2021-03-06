package com.sourcey.NauticalDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.tToolbar);
        if (toolbar != null)
            setSupportActionBar(toolbar);


        mAuth = FirebaseAuth.getInstance();

       ImageView profileActivity = (ImageView) toolbar.findViewById(R.id.action_profile);
       ImageView cnectActivity = (ImageView) toolbar.findViewById(R.id.action_msg);
        Button logout = (Button) findViewById(R.id.logout_btn);


        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity( new Intent(HomeActivity.this, LoginActivity.class));
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }

        });

        profileActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent profileActivity = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(profileActivity);
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }

        });
        cnectActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent knct = new Intent(HomeActivity.this, ConnectionsActivity.class);
                startActivity(knct);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }

        });


    }



/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int res_id = item.getItemId();
        if(res_id == R.id.action_profile)
        {
            Intent profileActivity = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(profileActivity);
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);


        }
        else if(res_id == R.id.action_home)
        {

        }
        else if(res_id == R.id.action_msg)
        {
            Intent cnectActivity = new Intent(HomeActivity.this, ConnectionsActivity.class);
            startActivity(cnectActivity);
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }

        return true;
    }*/
}
