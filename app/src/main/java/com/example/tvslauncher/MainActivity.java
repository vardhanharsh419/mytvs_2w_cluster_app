package com.example.tvslauncher;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tvslauncher.Bluetooth.BleDevices;
import com.example.tvslauncher.Common.PhonecallReceiver;
import com.example.tvslauncher.Components.AlertFragment;
import com.example.tvslauncher.Components.BluetoothFrag;
import com.example.tvslauncher.Components.MediaFragment;
import com.example.tvslauncher.Components.NavigationFragment;
import com.example.tvslauncher.Components.SettingsFragment;
import com.example.tvslauncher.Components.TripsFragment;
import com.example.tvslauncher.Fragments.BottomNav;
import com.example.tvslauncher.Fragments.RightNav;
import com.example.tvslauncher.Fragments.TopNav;
import com.example.tvslauncher.Fragments.LeftNav;
import com.example.tvslauncher.Server.MainServer;
import com.example.tvslauncher.extras.HeartbeartService;
import com.example.tvslauncher.extras.MyBackgroundService;
import com.example.tvslauncher.ui.home.HomeFragment;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import ru.skornei.restserver.RestServerManager;

public class MainActivity extends AppCompatActivity {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private TextClock systemtime;
    private TextView systemdate;
    private ImageView settings;
    private PhonecallReceiver call;
    private MainServer mainServer = new MainServer();
    BluetoothManager bluetoothManager;
    BluetoothAdapter bluetoothAdapter;
    int currentFragment = 1;
    private int HOME_FRAGMENT = 1;
    private int BLUETOOTH_FRAGMENT = 2;
    private int MEDIA_FRAGMENT = 3;
    private int NAVIGATION_FRAGMENT = 4;
    private int ALERT_FRAGMENT = 5;
    private int TRIP_FRAGMENT = 6;
    private int SETTING_FRAGMENT = 7;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        askForPermissions(MainActivity.this);

        systemdate = findViewById(R.id.systemdate);
        systemtime = findViewById(R.id.systemtime);
        settings = findViewById(R.id.settings);

        // Clean fragments (only if the app is recreated (When user disable permission))
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        // Remove previous fragments (case of the app was restarted after changed permission on android 6 and higher)
        List<Fragment> fragmentList = fragmentManager.getFragments();
        for (Fragment fragment : fragmentList) {
            if (fragment != null) {
                fragmentManager.beginTransaction().remove(fragment).commit();
            }
        }

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("BDATA"));

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    replacefrag(7, "Setting_Frag");
                } catch (Exception e) {
                    Log.d("afwfgs", String.valueOf(e));
                }
            }
        });

        LeftNav frg = new LeftNav();
        RightNav frg1 = new RightNav();
        TopNav frg2 = new TopNav();
        HomeFragment frg3 = new HomeFragment();
        BottomNav frg4 = new BottomNav();

        manager = getSupportFragmentManager();

        transaction = manager.beginTransaction();

        transaction.add(R.id.My_Container_1_ID, frg2, "Frag_Top_tag");
        transaction.add(R.id.My_Container_2_ID, frg, "Frag_Left_tag");
        transaction.add(R.id.My_Container_3_ID, frg1, "Frag_Right_tag");
        transaction.add(R.id.My_Container_4_ID, frg3, "Frag_Home_tag");
        transaction.add(R.id.My_Container_5_ID, frg4, "Frag_Bottom_tag");

        setScreenSize(R.id.My_Container_1_ID, 70, 1);
        setScreenSize(R.id.My_Container_2_ID, 80, 2);
        setScreenSize(R.id.My_Container_3_ID, 80, 2);
        setScreenSize(R.id.My_Container_4_ID, 90, 3);
        setScreenSize(R.id.My_Container_5_ID, 75, 1);

        transaction.commit();

        getdate();

//        getabsize();

    }

    public static void askForPermissions(Activity activity) {
        List<String> permissionsToAsk = new ArrayList<>();
        int requestResult = 0;

        if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.BLUETOOTH) !=
                PackageManager.PERMISSION_GRANTED) {
            // Ask for permission
            permissionsToAsk.add(android.Manifest.permission.BLUETOOTH);
        }
        if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.BLUETOOTH_CONNECT) !=
                PackageManager.PERMISSION_GRANTED) {
            // Ask for permission
            permissionsToAsk.add(android.Manifest.permission.BLUETOOTH_CONNECT);
        }
        if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.BLUETOOTH_SCAN) !=
                PackageManager.PERMISSION_GRANTED) {
            // Ask for permission
            permissionsToAsk.add(Manifest.permission.BLUETOOTH_SCAN);
        }

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionsToAsk.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionsToAsk.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (permissionsToAsk.size() > 0) {
            ActivityCompat.requestPermissions(activity, permissionsToAsk.toArray(new String[permissionsToAsk.size()]), requestResult);
        }
    }


    public void setScreenSize(int my_Container_1_ID, int i, int j) {

        WindowManager wm =
                (WindowManager) this.getSystemService(this.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        int deviceWidth, deviceHeigth;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            display.getSize(size);
            deviceWidth = size.x;
            deviceHeigth = size.y;
        } else {
            deviceWidth = display.getWidth();
            deviceHeigth = display.getHeight();

        }
        FrameLayout frame1 = findViewById(my_Container_1_ID);

        if (j == 1) {
            frame1.getLayoutParams().width = deviceWidth / 100 * i;
        } else if (j == 3) {

            frame1.getLayoutParams().height = deviceHeigth / 100 * i;
            frame1.getLayoutParams().width = deviceWidth / 100 * i;

        } else {
            frame1.getLayoutParams().height = deviceHeigth / 100 * i;
        }

        frame1.requestLayout();

        this.getWindow().getDecorView().setSystemUiVisibility(
                SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | SYSTEM_UI_FLAG_FULLSCREEN
                        | SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }

    public void replacefrag(int homeFragmentClass, String fragtag) {

        Fragment frg = null;

        int counter = 0;

        if (homeFragmentClass == 1) {
            frg = new HomeFragment();
            counter = 1;
        } else if (homeFragmentClass == 2) {
            frg = new BluetoothFrag();
        } else if (homeFragmentClass == 3) {
            frg = new MediaFragment();
        } else if (homeFragmentClass == 4) {
            frg = new NavigationFragment();
        } else if (homeFragmentClass == 5) {
            frg = new AlertFragment();
        } else if (homeFragmentClass == 6) {
            frg = new TripsFragment();
        } else if (homeFragmentClass == 7) {
            frg = new SettingsFragment();
        }

        transaction = manager.beginTransaction();

        Log.d("DAddd", String.valueOf(getCurrentFragment()));
        Log.d("DAddd", String.valueOf(homeFragmentClass));
        Log.d("DAddd", String.valueOf(getCurrentFragment() == homeFragmentClass));

        if(getCurrentFragment() != homeFragmentClass) {
            try {
                transaction.replace(R.id.My_Container_4_ID, frg, fragtag);

                if (counter == 1) {
                    setScreenSize(R.id.My_Container_4_ID, 90, 3);
                    counter = 0;
                } else {
                    setScreenSize(R.id.My_Container_4_ID, 80, 3);
                }

                transaction.commit();

            } catch (Exception e) {
                Log.d("afwfgs", String.valueOf(e));
            }
        }

    }

    private void getdate() {

            systemdate.setText(null);
//                systemtime.setText(null);

            Date dt = new Date(String.valueOf(Calendar.getInstance().getTime()));
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
            SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd");

            systemdate.setText(sdf2.format(dt));
//                systemtime.setText(sdf.format(dt));


    }

    public int getCurrentFragment() {
        if (currentFragment != 1) {
            return currentFragment;
        } else {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.My_Container_4_ID);
            if (currentFragment != null) {
                if (currentFragment.getClass().equals(HomeFragment.class)) {
                    return HOME_FRAGMENT;
                }
                if (currentFragment.getClass().equals(BluetoothFrag.class)) {
                    return BLUETOOTH_FRAGMENT;
                }
                if (currentFragment.getClass().equals(MediaFragment.class)) {
                    return MEDIA_FRAGMENT;
                }
                if (currentFragment.getClass().equals(NavigationFragment.class)) {
                    return NAVIGATION_FRAGMENT;
                }
                if (currentFragment.getClass().equals(AlertFragment.class)) {
                    return ALERT_FRAGMENT;
                }
                if (currentFragment.getClass().equals(TripsFragment.class)) {
                    return TRIP_FRAGMENT;
                }
                if (currentFragment.getClass().equals(SettingsFragment.class)) {
                    return SETTING_FRAGMENT;
                }
            }
        }
        return 1;

    }

    private void getabsize() {

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;

        float scaleFactor = metrics.density;
        float widthDp = widthPixels / scaleFactor;
        float heightDp = heightPixels / scaleFactor;

        float widthDpi = metrics.xdpi;
        float heightDpi = metrics.ydpi;

        float widthInches = widthPixels / widthDpi;
        float heightInches = heightPixels / heightDpi;

        double diagonalInches = Math.sqrt(
                (widthInches * widthInches)
                        + (heightInches * heightInches));

        Log.d("tabsize", "getabsize: " + diagonalInches);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
//        unregisterReceiver(receiver);


        // Don't forget to unregister the ACTION_FOUND receiver.
//        unregisterReceiver(receiver);
    }


    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            /* add below code in the Activity/Frag from where you want to send data

//            Intent intent = new Intent("BDATA");
//            intent.putExtra("peerId", "s45269d5df");
//            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

            */
        }
    };


    @SuppressLint("MissingPermission")
    protected void makeDiscoverable(){
        // Make local device discoverable
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 50000);
        startActivityForResult(discoverableIntent, 1);
    }

    public double tabletSize() {

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        int dens=dm.densityDpi;
        double wi=(double)width/(double)dens;
        double hi=(double)height/(double)dens;
        double x = Math.pow(wi,2);
        double y = Math.pow(hi,2);
        double screenInches = Math.sqrt(x+y);

        return screenInches;

    }

    @Override
    public void onResume() {
        super.onResume();

        getdate();
    }

}