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
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

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
    private TextView systemtime, systemdate;
    private ImageView settings;
    private PhonecallReceiver call;
    private MainServer mainServer = new MainServer();
    BluetoothManager bluetoothManager;
    BluetoothAdapter bluetoothAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        systemdate = findViewById(R.id.systemdate);
        systemtime = findViewById(R.id.systemtime);
        settings = findViewById(R.id.settings);

        bluetoothManager = getSystemService(BluetoothManager.class);
        bluetoothAdapter = bluetoothManager.getAdapter();

        try{
            mainServer.start();
            } catch (IOException e) {
            throw new RuntimeException(e);
        }

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

//        RestServerManager.initialize(MainActivity.this.getApplication());
//        try {
//            mainServer.start();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
        }


        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                askForPermissions(MainActivity.this);
                return;
            }

            bluetoothAdapter.enable();
            bluetoothAdapter.startDiscovery();
            startActivityForResult(enableBtIntent, 1);
        }




        implementListeners();

    }
    BluetoothDevice[] bluetoothDevices;

    private void implementListeners() {
        // listelemeye tıklayınca neler olacak

            // devices cihazların listelendiği
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
                Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();

                // cihazların değeri kadar al ve diziye ata
                String[] strings = new String[devices.size()];

                // bluetoothDevices size değerini ata
                bluetoothDevices = new BluetoothDevice[devices.size()];

                // index değeri 0 olarak verdik.
                int index = 0;

                // cihazlarının boyutu 0 dan büyükse
                if (devices.size() > 0) {

                    // loop ile cihazları döndür.
                    for (BluetoothDevice device : devices) {

                        // bluetoothDevices index değerine device ata
                        bluetoothDevices[index] = device;

                        // isimleri index değerine koy
                        strings[index] = device.getName();

                        // index değerini arttır
                        index++;
                    }

                    // adapter nesnesi tanımlandı.
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, strings);

                    // listview'e adapteri ata
//                    listView.setAdapter(arrayAdapter);
                    Log.d("aResztdxyftgjhkj", "implementListeners: " + arrayAdapter);
                }
            }


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

        try {
//            Fragment fragment = manager.findFragmentByTag(fragtag);
//
//                if(fragment == null) {
//                    transaction.add(R.id.My_Container_4_ID, frg, fragtag);
//                }else {
                    transaction.replace(R.id.My_Container_4_ID, frg, fragtag);
//                }

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

    private void getdate() {
        Date dt = new Date(String.valueOf(Calendar.getInstance().getTime()));
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd");

        systemdate.setText(sdf2.format(dt));
        systemtime.setText(sdf.format(dt));

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Don't forget to unregister the ACTION_FOUND receiver.
//        unregisterReceiver(receiver);
    }

}