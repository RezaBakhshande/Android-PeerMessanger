package com.macroid.bluetoothmessenger.repositories;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.IntentFilter;

import androidx.lifecycle.LiveData;

import com.macroid.bluetoothmessenger.models.C_BluetoothModel;
import com.macroid.bluetoothmessenger.models.C_DeviceModel;

import java.util.List;
import java.util.Set;

public class C_ItemsRepository
{
    //This class is acting as repositories
    private static C_ItemsRepository instance;
    private C_BluetoothModel bluetoothModel;

    public static C_ItemsRepository getInstance()
    {
        if (instance == null)
        {
            instance = new C_ItemsRepository();
        }
        return instance;
    }

    private C_ItemsRepository()
    {
        bluetoothModel = C_BluetoothModel.getInstance();
    }

    public LiveData<List<C_DeviceModel>> F_GetItems()
    {
        return bluetoothModel.F_GetItems();
    }

    //2-Calling the method in repository
    public void F_ParedDeviceList(Set<BluetoothDevice> pairedDevices)
    {
        bluetoothModel.F_ParedDeviceList(pairedDevices);
    }

    //2-Calling the method in repository
    public void F_AvailableDeviceList(Context context, IntentFilter intentFilter)
    {
        context.registerReceiver(bluetoothModel.F_FindDevice, intentFilter);
    }

}


