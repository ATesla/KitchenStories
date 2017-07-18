package com.shide56.kitchenstories;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.shide56.kitchenstories.main.HomeFragment;
import com.shide56.kitchenstories.main.LookupFragment;
import com.shide56.kitchenstories.main.MyHomeFragment;
import com.shide56.kitchenstories.main.ShoppingListFragment;
import com.shide56.kitchenstories.main.TipsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//    LocalService mService;
//    boolean mBound = false;
//    private ServiceConnection mConnection = new ServiceConnection() {
//
//
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
//            mService = binder.getService();
//            mBound = true;
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            mBound = false;
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ViewPager no_flip_view_pager = (ViewPager) findViewById(R.id.no_flip_view_pager);
        TabLayout bottom_bar = (TabLayout) findViewById(R.id.bottom_bar);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        no_flip_view_pager.setAdapter(pagerAdapter);
        bottom_bar.setupWithViewPager(no_flip_view_pager);

        String[] names = {
                HomeFragment.class.getName(),
                TipsFragment.class.getName(),
                LookupFragment.class.getName(),
                ShoppingListFragment.class.getName(),
                MyHomeFragment.class.getName()
        };
        SparseArray<Fragment> items = pagerAdapter.items;
        for (int i = 0; i < names.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            items.append(i, Fragment.instantiate(this, names[i], bundle));
        }
        pagerAdapter.notifyDataSetChanged();

        String[] titles = {"主页", "小贴士", "查找", "购物单", "我的主页"};
        int[] icons = {
                R.drawable.ic_mood_bad_black_24dp,
                R.drawable.ic_mood_black_24dp,
                R.drawable.ic_sentiment_neutral_black_24dp,
                R.drawable.ic_sentiment_very_dissatisfied_black_24dp,
                R.drawable.ic_person_black_24dp
        };
        for (int i = 0; i < bottom_bar.getTabCount(); i++) {
            TabLayout.Tab tab = bottom_bar.getTabAt(i);
            if (tab != null) {
                tab.setText(titles[i]);
                Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), icons[i]);
                DrawableCompat.setTint(drawable, Color.GRAY);
                tab.setIcon(drawable);
            }
        }

        bottom_bar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                Drawable drawable = tab.getIcon();
//                int[][] states = {{android.R.attr.state_selected}, {}};
//                int[] colors = {Color.BLUE, Color.BLACK};
//                ColorStateList colorStateList = new ColorStateList(states, colors);
//                StateListDrawable stateListDrawable = new StateListDrawable();
//                stateListDrawable.addState(states[0], drawable);
//                stateListDrawable.addState(states[1], drawable);
//                Drawable.ConstantState constantState = stateListDrawable.getConstantState();
//                Drawable wrap = DrawableCompat.wrap(constantState == null ? stateListDrawable : constantState.newDrawable());
//                DrawableCompat.setTintList(wrap, colorStateList);
                DrawableCompat.setTint(tab.getIcon(), Color.BLACK);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                DrawableCompat.setTint(tab.getIcon(), Color.GRAY);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Intent intent = new Intent(this, LocalService.class);
//        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        if (mBound) {
//            unbindService(mConnection);
//            mBound = false;
//        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private final class PagerAdapter extends FragmentPagerAdapter {
        final SparseArray<Fragment> items = new SparseArray<>();

        PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }
}
