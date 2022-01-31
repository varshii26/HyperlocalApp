package com.example.hyperlocal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hyperlocal.databinding.ActivityMain2Binding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;






public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private static final int HOME_FRAGMENT = 0;
    private static final int CART_FRAGMENT = 1;
    private static final int ORDERS_FRAGMENT = 2;
    private static final int WISHLIST_FRAGMENT = 3;
    private static final int REWARDS_FRAGMENT = 4;
    private static final int ACCOUNT_FRAGMENT = 5;
    public static boolean showCart = false;
    public static Activity main2Activity;
    public static boolean resetMain2Activity = false;


    private FrameLayout frameLayout;
    private ImageView actionBarLogo;
    private int currentFragment = -1;
    public static NavigationView navigationView;

    private Window window;
    private Toolbar toolbar;
    private Dialog signInDialog;
    private FirebaseUser currentuser;
    private TextView badgeCount;
    private int scrollFlags;
    private AppBarLayout.LayoutParams params;

    private TextView fullname, email;
    private ImageView addProfileIcon;

    public static DrawerLayout drawer;



    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*  toolbar = (Toolbar) findViewById(R.id.toolbar);
         actionBarLogo = findViewById(R.id.actionbar_logo);

         */
        setSupportActionBar(toolbar);
        window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        scrollFlags = params.getScrollFlags();

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);


        View profileView = navigationView.getHeaderView(0).findViewById(R.id.imageView);
        fullname = navigationView.getHeaderView(0).findViewById(R.id.main2_fullname);
        email = navigationView.getHeaderView(0).findViewById(R.id.main2_email);


         /*


         signInDialog = new Dialog(Main2Activity.this);
         signInDialog.setContentView(R.layout.sign_in_dialog);
         signInDialog.setCancelable(true);
         signInDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

         Button dialogSignInBtn = signInDialog.findViewById(R.id.sign_in_btn);
         Button dialogSignUpBtn = signInDialog.findViewById(R.id.sign_up_btn);


             NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);


             NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
             NavigationUI.setupWithNavController(navigationView, navController);

          */


        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        frameLayout = findViewById(R.id.main_framelayout);
        setFragment(new HomeFragment());



    }

    private void setFragment(HomeFragment homeFragment) {
    }


    final Intent registerIntent = new Intent(Main2Activity.this, Register.class);

/*

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         // Inflate the menu; this adds items to the action bar if it is present.
         if (currentFragment == HOME_FRAGMENT) {
             getSupportActionBar().setDisplayShowTitleEnabled(false);
             getMenuInflater().inflate(R.menu.main2, menu);

             MenuItem cartItem = menu.findItem(R.id.main2_cart_icon);
             cartItem.setActionView(R.layout.badge_layout);
             ImageView badgeIcon = cartItem.getActionView().findViewById(R.id.badge_icon);
             badgeIcon.setImageResource(R.mipmap.cart_white);
             badgeCount = cartItem.getActionView().findViewById(R.id.badge_count);
             if (currentuser != null) {
                 if (DBqueries.cartList.size() == 0) {
                     DBqueries.loadCartList(Main2Activity.this, new Dialog(Main2Activity.this), false, badgeCount, new TextView(Main2Activity.this));
                 } else {
                     badgeCount.setVisibility(View.VISIBLE);
                     if (DBqueries.cartList.size() < 99) {
                         badgeCount.setText(String.valueOf(DBqueries.cartList.size()));
                     } else {
                         badgeCount.setText("99");
                     }
                 }
             }

             cartItem.getActionView().setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if (currentuser == null) {
                         signInDialog.show();
                     } else {

                     }
                 }
             });

            MenuItem notifyItem = menu.findItem(R.id.main2_notification_icon);
             notifyItem.setActionView(R.layout.badge_layout);
             ImageView notifyIcon = notifyItem.getActionView().findViewById(R.id.badge_icon);
             notifyIcon.setImageResource(R.mipmap.bell);
             TextView notifyCount = notifyItem.getActionView().findViewById(R.id.badge_count);
             if (currentuser != null){
                 DBqueries.checkNotifications(false,notifyCount);
             }



             notifyItem.getActionView().setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent notificationIntent = new Intent(Main2Activity.this, NotificationActivity.class);
                     startActivity(notificationIntent);
                 }
             });

         }






         return true;
     }


 */


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mainSearchIcon) {
            return true;
        } else if (id == R.id.mainNotificationIcon) {
            return true;
        } else if (id == R.id.mainCartIcon) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    MenuItem menuItem;

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        menuItem = item;

        if (currentuser != null) {
            drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                    int id = menuItem.getItemId();
                    if (id == R.id.App){

                    } else if (id == R.id.Orders) {

                    } else if (id == R.id.Favourites) {

                    } else if (id == R.id.Cart) {

                    } else if (id == R.id.Account) {

                    } else if (id == R.id.SignOut) {
                        FirebaseAuth.getInstance().signOut();
                        DBqueries.clearData();
                        Intent registerIntent = new Intent(Main2Activity.this, Register.class);
                        startActivity(registerIntent);
                        finish();
                    }
                    drawer.removeDrawerListener(this);
                }
            });
            return true;
        } else {
            signInDialog.show();
            return false;
        }
    }

    private void setFragment(Fragment fragment, int fragmentNo) {
        if (fragmentNo != currentFragment) {
            if (fragmentNo == REWARDS_FRAGMENT) {
                window.setStatusBarColor(Color.parseColor("#5B04B1"));
                toolbar.setBackgroundColor(Color.parseColor("#5B04B1"));
            } else {
                window.setStatusBarColor(getResources().getColor(R.color.white));
                toolbar.setBackgroundColor(getResources().getColor(R.color.white));
            }
            currentFragment = fragmentNo;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            ((FragmentTransaction) fragmentTransaction).replace(frameLayout.getId(), fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}
