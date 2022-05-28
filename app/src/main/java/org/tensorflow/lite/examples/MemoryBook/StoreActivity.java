package org.tensorflow.lite.examples.MemoryBook;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StoreActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    ImageView imageView;
    Button saveUsers,showUsers;
    Bitmap bmpImage;
    EditText fName,uName,desc,dob;
    UserD userD;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        bmpImage = null;
        userD = UserDB.getDBInstance(this).userD();
        //taking picture
        this.imageView = (ImageView)this.findViewById(R.id.userImage);
        Button photoButton = (Button) this.findViewById(R.id.takePicture);
        photoButton.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v)
            {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
        //saving users data
        fName=findViewById(R.id.fullName);
        uName=findViewById(R.id.userName);
        desc=findViewById(R.id.userDescription);
        dob=findViewById(R.id.userDob);
        saveUsers = (Button) this.findViewById(R.id.saveUsers);
        saveUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(fName.getText().toString().isEmpty() || uName.getText().toString().isEmpty() ||
                        desc.getText().toString().isEmpty() || dob.getText().toString().isEmpty() ){
                    //Toast.makeText(this, "user data missing", Toast.LENGTH_LONG).show();
                }else{
                    User user = new User();
                    user.setFullName(fName.getText().toString());
                    user.setUserName(uName.getText().toString());
                    user.setDescription(desc.getText().toString());
                    user.setImage(DataConverter.convertImage2ByteArray(bmpImage));
                    try {
                        user.setDob(new SimpleDateFormat("dd/mm/yy")
                                .parse(dob.getText().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    userD.insertUser(user);
                    showToast();



                }



            }
        });
        //showing users data
        showUsers = (Button) this.findViewById(R.id.showUsers);
        showUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();

            }
        });

    }
    private void showToast() {
        Toast.makeText(this,
                "Insertion successful",
                Toast.LENGTH_SHORT).show();
    }
    private void show() {
        Intent intent = new Intent(this,ShowUsersActivity.class);
        startActivity(intent);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

            bmpImage = (Bitmap) data.getExtras().get("data");
            if(bmpImage != null) {
                imageView.setImageBitmap(bmpImage);
            }
        }
    }
}