package com.example.daamdekhi;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private ProfileFragment profileFragment;
    private FavoriteFragment favoriteFragment;
    private HomeFragment homeFragment;
    private LoginFragment loginFragment;
    private CategoryFragment categoryFragment;
    private MoreFragment moreFragment;
    private PlaceholderFragment placeholderFragment;

    private LocationManager locationManager;
    public static double longitude, latitude;
    MaterialSearchView searchView;
    public static Context cont;

    public static String[][] products = new String[][]{
            {"Rice in Dhaka", "Small desc", "100", "image.jpg"},
            {"Rice in Chittagong", "Small desc", "100", "image.jpg"},
            {"Rice in Comilla", "Small desc", "100", "image.jpg"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cont = this;

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(location!= null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

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
        loginFragment = new LoginFragment();
        categoryFragment = new CategoryFragment();
        moreFragment = new MoreFragment();
        homeFragment = new HomeFragment();
        placeholderFragment = new PlaceholderFragment();
        


        NavigationView navigationView=findViewById(R.id.naView);
        navigationView.setNavigationItemSelectedListener(this);

        setFragment(homeFragment);

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
            }
            @Override
            public void onSearchViewClosed() {
            }
        });
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.detach(homeFragment);
                Bundle q = new Bundle();
                q.putString("query", query);
                homeFragment.setArguments(q);
                fragmentTransaction.attach(homeFragment).commit();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return true;
            }
        });



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

    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
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

            case R.id.user_login:
                fragment=new LoginFragment();
                break;
            case R.id.share:
                Intent sharingIntent=new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody= "Get More Exciting Product. Visit https://daamdekhi.com/ \n App Download link:";
                String shareSubject="App Download link: ";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);
                sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(sharingIntent,"Share With"));
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
