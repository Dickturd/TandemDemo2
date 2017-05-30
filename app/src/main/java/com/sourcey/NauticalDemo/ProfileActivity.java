package com.sourcey.NauticalDemo;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class ProfileActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private FirebaseAuth mAuth;
    private Uri mImageUri = null;
    private DatabaseReference mDatabaseUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = (Toolbar) findViewById(R.id.tToolbar);
        if (toolbar != null)
            setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();

        ImageView homeActivity = (ImageView) toolbar.findViewById(R.id.action_home);
        ImageView cnectActivity = (ImageView) toolbar.findViewById(R.id.action_msg);
        final ImageView profPic = (ImageView) toolbar.findViewById(R.id.profileimagebutton2);
        Button logout = (Button) findViewById(R.id.logout_btn2);

        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users");

      /*  Query query = mDatabase.orderByChild("image").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {




                profPic.setImageURI();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/












        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity( new Intent(ProfileActivity.this, LoginActivity.class));
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }

        });

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
