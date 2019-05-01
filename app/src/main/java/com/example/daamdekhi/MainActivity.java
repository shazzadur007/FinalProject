package com.example.daamdekhi;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private ProfileFragment profileFragment;
    private FavoriteFragment favoriteFragment;
    private HomeFragment homeFragment;
    private LoginFragment loginFragment;
    private CategoryFragment categoryFragment;
    private MoreFragment moreFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("DaamDekhi");
        setSupportActionBar(toolbar);


        bottomNavigationView = findViewById(R.id.bottomnavigationbar);
        frameLayout = findViewById(R.id.main_frame);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        profileFragment = new ProfileFragment();
        favoriteFragment = new FavoriteFragment();
        homeFragment = new HomeFragment();
        loginFragment = new LoginFragment();
        categoryFragment = new CategoryFragment();
        moreFragment = new MoreFragment();

        NavigationView navigationView=findViewById(R.id.naView);
        navigationView.setNavigationItemSelectedListener(this);

        setFragment(homeFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.home:
                        setFragment(homeFragment);
                        return true;

                    case R.id.category:
                        setFragment(categoryFragment);
                        return true;

                    case R.id.more:
                        setFragment(moreFragment);
                        return true;

                        default:
                            return false;
                }
            }
        });

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.END)){
            drawerLayout.closeDrawer(GravityCompat.END);
        }else {
            super.onBackPressed();
        }
    }
    public void displaySelectedListener(int itemID){
        Fragment fragment=null;

        switch (itemID){
            case R.id.user_Profile:
                fragment=new ProfileFragment();
                break;

            case R.id.user_favorite:
                fragment=new FavoriteFragment();
                break;

            case R.id.user_login:
                fragment=new LoginFragment();
                break;
            case R.id.share:
                Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
                break;
        }
        if (fragment!=null){
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_frame,fragment);
            ft.commit();
        }
        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        displaySelectedListener(menuItem.getItemId());
        return true;
    }
}
