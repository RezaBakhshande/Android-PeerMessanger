package com.macroid.bluetoothmessenger;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.macroid.bluetoothmessenger.di.components.DaggerIChatActivityComponent;
import com.macroid.bluetoothmessenger.di.models.C_BluetoothModelDagger;
import com.macroid.bluetoothmessenger.di.models.C_PermissionHelperModelDagger;
import com.macroid.bluetoothmessenger.models.C_BluetoothModel;
import com.macroid.bluetoothmessenger.utils.C_PermissionsHelper;
import com.macroid.bluetoothmessenger.utils.I_PermissionHelperListener;

import java.util.Set;

import javax.inject.Inject;

public class C_ChatActivity extends AppCompatActivity
{


    @Inject
    C_BluetoothModel bluetoothModel;

    @Inject
    C_PermissionsHelper permissionsHelper;

    //init bluetooth
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        F_DaggerImplimentation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_mainactivity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menuSearchDevices:

                if (bluetoothAdapter.isEnabled())
                {

                    permissionsHelper.F_RequestPermissions(Manifest.permission.ACCESS_FINE_LOCATION, new I_PermissionHelperListener()
                    {
                        @Override
                        public void F_Accepted()
                        {
                            startActivity(new Intent(getApplicationContext(),C_DeviceListActivity.class));
                        }

                        @Override
                        public void F_Rejected()
                        {
                            Toast.makeText(getApplicationContext(), "Please Accept Location Permission", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Toast.makeText(getApplicationContext(), "Start Searching Devices", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),C_DeviceListActivity.class));
                    //bluetoothModel.F_ParedDeviceList(getApplicationContext());
                } else
                {
                    Toast.makeText(getApplicationContext(), "Please turn on Bluetooth first", Toast.LENGTH_SHORT).show();
                }


                return true;
                case R.id.menuEnableBluetooth:
                if (bluetoothModel.F_IsDeviceSupportBluetooth())
                {
                    bluetoothModel.F_EnableBluetooth(this);
                } else
                {
                    Toast.makeText(this, "Device doesn't support Bluetooth", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //This method for implimentation dagger2
    private void F_DaggerImplimentation()
    {
        DaggerIChatActivityComponent.builder()
                .c_BluetoothModelDagger(new C_BluetoothModelDagger(bluetoothAdapter, pairedDevices))
                .c_PermissionHelperModelDagger(new C_PermissionHelperModelDagger(this))
                .build()
                .Inject(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        C_PermissionsHelper.F_getInstance().F_OnRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}