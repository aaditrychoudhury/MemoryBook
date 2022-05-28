package org.tensorflow.lite.examples.MemoryBook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class splashActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tv = findViewById(R.id.locaro);
        tv.animate().translationYBy(65).setDuration(2000);
        tv.animate().alpha(1f).setDuration(2200);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(splashActivity.this, LoginActivity.class));
                finish();
            }
        }, 2350);
    }
}