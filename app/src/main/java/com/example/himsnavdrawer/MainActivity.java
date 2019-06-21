package com.example.himsnavdrawer;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout ;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        NavigationView mDrawer = (NavigationView) findViewById(R.id.nv);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(mDrawer);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true ;
        }

        return super.onOptionsItemSelected(item);

    }



    public void selectItemDrawer(MenuItem menuItem){
        Fragment myFragment = null ;
        Class fragmentClass ;
        switch (menuItem.getItemId()){

            case R.id.dashboard :
                fragmentClass = DashboardFragment.class ;
                break;

            case R.id.event :
                fragmentClass = EventFragment.class ;
                break;

            case R.id.about :
                fragmentClass = AboutFragment.class ;
                break;

            default:
                fragmentClass = DashboardFragment.class ;


        }

        try {

            myFragment = (Fragment) fragmentClass.newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager() ;
        fragmentManager.beginTransaction().replace(R.id.fcontent, myFragment).commit() ;
        menuItem.setChecked(true) ;
        setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();


    }

    private void setupDrawerContent ( NavigationView navigationView) {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectItemDrawer(menuItem);
                return true;
            }
        });
    }



}
