package com.macroid.bluetoothmessenger.di.models;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import com.macroid.bluetoothmessenger.di.scope.MainScope;
import com.macroid.bluetoothmessenger.models.C_BluetoothModel;

import java.util.Set;

import dagger.Module;
import dagger.Provides;

@Module
public class C_BluetoothModelDagger
{
    BluetoothAdapter bluetoothAdapter;
    Set<BluetoothDevice> pairedDevices;

    public C_BluetoothModelDagger(/*BluetoothAdapter bluetoothAdapter, Set<BluetoothDevice> pairedDevices*/)
    {
        /*this.bluetoothAdapter = bluetoothAdapter;
        this.pairedDevices = pairedDevices;*/
    }

    @MainScope
    @Provides
    public C_BluetoothModel F_GetBluetoothModel(/*BluetoothAdapter bluetoothAdapter , Set<BluetoothDevice> pairedDevices*/)
    {
        return new C_BluetoothModel();
    }

   /* @MainScope
    @Provides
    public BluetoothAdapter F_GetBluetoothAdapter()
    {
        return bluetoothAdapter;
    }

    @MainScope
    @Provides
    public Set<BluetoothDevice> F_GetPairedDevices()
    {
        return pairedDevices;
    }*/
}
