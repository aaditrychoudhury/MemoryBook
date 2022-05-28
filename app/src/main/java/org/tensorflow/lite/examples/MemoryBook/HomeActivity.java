package org.tensorflow.lite.examples.MemoryBook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
//        if (currentUser == null) {
//            startActivity(new Intent(BuyersActivity.this, LoginActivity.class));
//            finish();
//        }
        /*else {
            syncFirebaseData();
        }*/

        //setSupportActionBar(toolbar);
        /*toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.logoutButton) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(BuyersActivity.this, LoginActivity.class));
                finish();
                return true;
            }
            return false;
        });*/

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_draw_open,R.string.navigation_draw_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }
    /*@Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.LogoutActivity:
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.HomeActivity:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.AccountActivity:
                Toast.makeText(this, "My Account to be displayed", Toast.LENGTH_SHORT).show();
                break;
        }
        //return super.onOptionsItemSelected(item);
        return true;
    }
    public void registerFace(View view){
        startActivity(new Intent(this,StoreActivity.class));
    }
    public void detectFace(View view){
        startActivity(new Intent(this,DetectorActivity.class));
    }

}