package kders.app.audioplayer.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import kders.app.audioplayer.R;
import kders.app.audioplayer.fragment.FragmentSmartPlaylist;

public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    public static int navItemIndex = 0;
    private static final String TAG_PLAYLIST = "PLAYLIST";
    private static final String TAG_EQUALIZER = "EQUALIZER";
    private static final String TAG_SLEEPTIME = "SLEEP";
    private static final String TAG_FOLLOW = "FOLLOW";
    private static final String TAG_SETTINGS = "SETTINGS";
    private static final String TAG_RATE = "RATE";
    public static String CURRENT_TAG = TAG_PLAYLIST;
    private String[] activityTitles;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_PLAYLIST;
             loadHomeFragment();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
        if (id == R.id.action_search) {
            return true;
        }
        if (id == R.id.action_sync_cloud) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }

        Fragment fragment = getHomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                FragmentSmartPlaylist homeFragment = new FragmentSmartPlaylist();
                return homeFragment;
            case 1:
                // photos
                //  PhotosFragment photosFragment = new PhotosFragment();
                //  return photosFragment;
                FragmentSmartPlaylist homeFragment1 = new FragmentSmartPlaylist();
                return homeFragment1;
            case 2:
                // movies fragment
                //  MoviesFragment moviesFragment = new MoviesFragment();
                //  return moviesFragment;
                FragmentSmartPlaylist homeFragment2 = new FragmentSmartPlaylist();
                return homeFragment2;
            case 3:
                // notifications fragment
                // NotificationsFragment notificationsFragment = new NotificationsFragment();
                // return notificationsFragment;
                FragmentSmartPlaylist homeFragment3 = new FragmentSmartPlaylist();
                return homeFragment3;

            case 4:
                // settings fragment
                //  SettingsFragment settingsFragment = new SettingsFragment();
                //  return settingsFragment;
                FragmentSmartPlaylist homeFragment4 = new FragmentSmartPlaylist();
                return homeFragment4;
            default:
                return new FragmentSmartPlaylist();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_smart_playlist:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_PLAYLIST;
                        break;
                    case R.id.nav_equalizer:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_EQUALIZER;
                        break;
                    case R.id.nav_sleep:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_SLEEPTIME;
                        break;
                    case R.id.nav_setting:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;
                    case R.id.nav_follow:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_FOLLOW;
                        break;
                    case R.id.nav_rate:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_RATE;
                        return true;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }


}
