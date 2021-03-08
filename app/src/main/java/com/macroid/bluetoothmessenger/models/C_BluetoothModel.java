package com.macroid.bluetoothmessenger.models;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class C_BluetoothModel
{
    private static final int REQUEST_ENABLE_BT = 1;
    /*BluetoothAdapter bluetoothAdapter;
    Set<BluetoothDevice> pairedDevices;*/
    List<C_DeviceModel> deviceModelList = new ArrayList<>();
    List<C_DeviceModel> deviceModelAvailableList = new ArrayList<>();
    C_DeviceModel deviceModelPaire;
    //C_DeviceModel deviceModelAvailable;

    //Live Data
    private MutableLiveData<List<C_DeviceModel>> mutableLiveData = new MutableLiveData<>();
    private static C_BluetoothModel instance;


    public static C_BluetoothModel getInstance()
    {
        if (instance == null)
        {
            instance = new C_BluetoothModel();
        }
        return instance;
    }

    public C_BluetoothModel()
    {
        mutableLiveData = new MutableLiveData<>();
    }

    public LiveData<List<C_DeviceModel>> F_GetItems()
    {
        return mutableLiveData;
    }

    public boolean F_IsDeviceSupportBluetooth(BluetoothAdapter bluetoothAdapter)
    {
        return bluetoothAdapter.getBondedDevices() != null;
    }


    public void F_EnableBluetooth(Activity activity, BluetoothAdapter bluetoothAdapter)
    {
        if (!bluetoothAdapter.isEnabled())
        {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else
        {
            Toast.makeText(activity, "Bluetooth Already Enabled", Toast.LENGTH_SHORT).show();
        }
    }


    public List<C_DeviceModel> F_ParedDeviceList(Set<BluetoothDevice> pairedDevices)
    {
        if (pairedDevices.size() > 0 && deviceModelList.size() == 0)
        {
            for (BluetoothDevice device : pairedDevices)
            {
                deviceModelPaire = new C_DeviceModel(device.getName(), device.getAddress());
                deviceModelList.add(deviceModelPaire);
                mutableLiveData.setValue(deviceModelList);
            }
            return deviceModelList;

        }
        return deviceModelList;
    }


    public final BroadcastReceiver F_FindDevice = new BroadcastReceiver()
    {
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action))
            {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                assert device != null;
                deviceModelPaire = new C_DeviceModel(device.getName(), device.getAddress());
                deviceModelAvailableList.add(deviceModelPaire);
                mutableLiveData.setValue(deviceModelAvailableList);
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
            {
                if (deviceModelAvailableList.size() == 0)
                {
                    Toast.makeText(context, "No new devices found", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

}
