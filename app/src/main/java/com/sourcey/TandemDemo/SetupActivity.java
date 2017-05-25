package com.sourcey.TandemDemo;

import android.app.ProgressDialog;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sourcey.TandemDemo.R;
import com.sourcey.TandemDemo.model.GettoknowActivity;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.w3c.dom.Text;

import butterknife.Bind;


/**
 * Created by Peter on 5/22/2017.
 */

public class SetupActivity extends AppCompatActivity{

    private ImageButton mSetupImagebtn;
    private EditText mNameField;
    private Button mSubmitBtn;
    private Uri mImageUri = null;
    private FirebaseAuth mAuth;
    private StorageReference mStorageImage;
    private ProgressDialog  mProgress;


    private static final int GALLERY_REQUEST = 1;

    private DatabaseReference mDatabaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        mSetupImagebtn = (ImageButton) findViewById(R.id.profileimagebutton);
        mNameField = (EditText) findViewById(R.id.setupusername);
        mSubmitBtn = (Button) findViewById(R.id.setupSubmit);
        mProgress = new ProgressDialog(this);

        mStorageImage = FirebaseStorage.getInstance().getReference().child("Profile_images");
        mAuth = FirebaseAuth.getInstance();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users");

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSetupAccount();
                startActivity( new Intent(SetupActivity.this, GettoknowActivity.class));
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });

        mSetupImagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });
    }





    private void startSetupAccount() {

        final String name = mNameField.getText().toString().trim();

        FirebaseUser user = mAuth.getCurrentUser();

        final String user_id = mAuth.getCurrentUser().getUid();

        if (!TextUtils.isEmpty(name) && mImageUri != null) {

            //For acutal end of setup
            mProgress.setMessage("Finishing Setup...");
            mProgress.show();
            // ends here
            StorageReference filepath = mStorageImage.child(mImageUri.getLastPathSegment());
            filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    mDatabaseUsers.child(user_id).child("name").setValue(name);
                    mDatabaseUsers.child(user_id).child("image").setValue(downloadUrl.toString());
                }
            });


        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();

            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);


        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                mImageUri = result.getUri();
                mSetupImagebtn.setImageURI(mImageUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }





}
