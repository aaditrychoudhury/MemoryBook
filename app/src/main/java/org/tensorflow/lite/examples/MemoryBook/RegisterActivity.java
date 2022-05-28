package org.tensorflow.lite.examples.MemoryBook;

import android.content.Intent;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RegisterActivity extends AppCompatActivity {

//    FirebaseAuth firebaseAuth;
//    FirebaseFirestore db;
    String userID;

    @BindView(R.id.editTextName) EditText editTextName;
    @BindView(R.id.editTextEmail) EditText emailEditText;
    @BindView(R.id.passwordREditText) EditText passwordREditText;
    @BindView(R.id.confirmPasswordEditText) EditText confirmPasswordEditText;
    @BindView(R.id.cirRegisterButton) Button registerBtn;
    @BindView(R.id.loginText) TextView loginText;
    @BindView(R.id.regProgressBar)
    ProgressBar regProgressBar;

    public static final String keyName = "name", keyEmail = "email";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();

        ButterKnife.bind(this);

        loginText.setOnClickListener(view -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });

        registerBtn.setOnClickListener(v -> {

            String email = emailEditText.getText().toString();
            String password = passwordREditText.getText().toString();
            String confPassword = confirmPasswordEditText.getText().toString();
            String name = editTextName.getText().toString();

            if (email.isEmpty() || password.isEmpty() || confPassword.isEmpty())
                Snackbar.make(v, "Please fill all the fields", Snackbar.LENGTH_LONG).show();
            else if (password.length() < 8)
                Snackbar.make(v, "Password should contain at least 8 characters", Snackbar.LENGTH_LONG).show();
            else if (!confPassword.equals(password))
                Snackbar.make(v, "Passwords don't match", Snackbar.LENGTH_LONG).show();
            else {
                UserEntity userEntity = new UserEntity();
                userEntity.setUserId(email);
                userEntity.setPassword(password);
                userEntity.setName(name);
                if(validateInput(userEntity)){
                    //initialize user database
                    //do insert operation
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    //initialize user dao
                    final UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //Register User
                            userDao.registerUser(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"User Registered !",Toast.LENGTH_SHORT).show();
                                }
                            });


                        }
                    }).start();

                }else{
                    Toast.makeText(getApplicationContext(),"Fill all fields!",Toast.LENGTH_SHORT).show();

                }

//                firebaseAuth = FirebaseAuth.getInstance();
//                db = FirebaseFirestore.getInstance();
//                registerBtn.setClickable(false);
//                regProgressBar.setVisibility(View.VISIBLE);
//                firebaseAuth.createUserWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(RegisterActivity.this, task -> {
//                            if (task.isSuccessful()) {
//                                //saving user details
//                                userID = firebaseAuth.getCurrentUser().getUid();
//                                DocumentReference documentReference = db.collection("users").document(userID);
//                                Map<String,Object> user = new HashMap<>();
//                                user.put("name",name);
//                                user.put("email",email);
//                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void aVoid) {
//                                        Log.i("Information","Successfully added data to firestore");
//                                    }
//                                }).addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        Log.i("Information","Error Occurred");
//                                    }
//                                });
//
//                                Toast.makeText(RegisterActivity.this, "Registration successful. Logging in", Toast.LENGTH_LONG).show();
//                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//                                finish();
//                            } else {
//                                regProgressBar.setVisibility(View.INVISIBLE);
//                                Toast.makeText(RegisterActivity.this, "Registration failed. Try again", Toast.LENGTH_LONG).show();
//                                Log.e("Info", task.getException().getMessage());
//                                registerBtn.setClickable(true);
//                            }
//                        });
            }
        });



    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }
    private Boolean validateInput(UserEntity userEntity){
        //here i have changed from get?Name -> getUserId
        if(userEntity.getName().isEmpty() || userEntity.getPassword().isEmpty() ||
                userEntity.getUserId().isEmpty()){
            return false;
        }
        return true;
    }


}
