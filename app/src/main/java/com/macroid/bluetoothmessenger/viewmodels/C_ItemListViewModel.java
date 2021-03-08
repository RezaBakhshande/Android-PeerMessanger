package com.macroid.bluetoothmessenger.viewmodels;


import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.IntentFilter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.macroid.bluetoothmessenger.models.C_DeviceModel;
import com.macroid.bluetoothmessenger.repositories.C_ItemsRepository;

import java.util.List;
import java.util.Set;

public class C_ItemListViewModel extends ViewModel
{
    private C_ItemsRepository itemsRepository;

    //Constractor
    public C_ItemListViewModel()
    {
        itemsRepository = C_ItemsRepository.getInstance();
    }

    public LiveData<List<C_DeviceModel>> F_GetItems()
    {
        return itemsRepository.F_GetItems();
    }

    //3-Calling method in view-model
    public void F_ParedDeviceList(Set<BluetoothDevice> pairedDevices)
    {
        itemsRepository.F_ParedDeviceList(pairedDevices);
    }

    //3-Calling method in view-model
    public void F_AvailableDeviceList(Context context, IntentFilter intentFilter)
    {
        itemsRepository.F_AvailableDeviceList(context, intentFilter);
    }
}
