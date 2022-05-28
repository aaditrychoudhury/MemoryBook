package org.tensorflow.lite.examples.MemoryBook;

import android.content.Intent;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editTextEmail) EditText emailEditText;
    @BindView(R.id.confirmPasswordEditText) EditText passwordEditText;
    @BindView(R.id.cirLoginButton) Button loginButton;
    @BindView(R.id.registerText) TextView registerText;
//    @BindView(R.id.switchUsers) Switch switchUsers;
    @BindView(R.id.loginProgressBar)
    ProgressBar loginProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon colors
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

//        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        registerText.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            finish();
        });

        loginButton.setOnClickListener(view -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            View focusedView = LoginActivity.this.getCurrentFocus();
            if (focusedView != null) {
                imm.hideSoftInputFromWindow(focusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }

            String email = emailEditText.getText().toString().trim();
            String pass = passwordEditText.getText().toString().trim();

            if (email.isEmpty() || pass.isEmpty())
                Snackbar.make(view, "Please fill all the fields", Snackbar.LENGTH_LONG).show();
          else {
                //database intialization
                //Perform Query
                UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext()) ;
                final UserDao userDao = userDatabase.userDao();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        UserEntity userEntity = userDao.login(email, pass);
                        if(userEntity == null){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"Invalid Credentials",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            String name = userEntity.name;
                            startActivity(new Intent
                                    (LoginActivity.this,HomeActivity.class)
                                    .putExtra("name",name));

                        }

                    }
                }).start();
//                loginButton.setClickable(false);
//                loginProgressBar.setVisibility(View.VISIBLE);
//                mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        if(switchUsers.isChecked()) {
//                            startActivity(new Intent(LoginActivity.this, BuyersActivity.class));
//                            finish();
//                        }
//                        else{
//                            startActivity(new Intent(LoginActivity.this, VendorsActivity.class));
//                            finish();
//                        }
//                        finish();
//                    } else {
//                        Snackbar.make(view, "There was an error. Please try again!", Snackbar.LENGTH_LONG).show();
//                        Log.e("Error", "Login Error : " + task.getException());
//                        loginProgressBar.setVisibility(View.INVISIBLE);
//                        loginButton.setClickable(true);
//                    }
//                });
            }
        });
    }

    public void onLoginClick(View View){
        startActivity(new Intent(this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }
}
