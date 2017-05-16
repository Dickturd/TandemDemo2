package com.sourcey.TandemDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class ProfileActivity extends AppCompatActivity {


    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = (Toolbar) findViewById(R.id.tToolbar);
        if (toolbar != null)
            setSupportActionBar(toolbar);

        ImageView homeActivity = (ImageView) toolbar.findViewById(R.id.action_home);
        ImageView cnectActivity = (ImageView) toolbar.findViewById(R.id.action_msg);

        homeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileActivity = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(profileActivity);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }

        });
        cnectActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent knct = new Intent(ProfileActivity.this, ConnectionsActivity.class);
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



        }
        else if(res_id == R.id.action_home)
        {
            Intent profileActivity = new Intent(ProfileActivity.this, HomeActivity.class);
            startActivity(profileActivity);
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
        else if(res_id == R.id.action_msg)
        {
            Intent cnectActivity = new Intent(ProfileActivity.this, ConnectionsActivity.class);
            startActivity(cnectActivity);
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }

        return true;
    }*/
}
