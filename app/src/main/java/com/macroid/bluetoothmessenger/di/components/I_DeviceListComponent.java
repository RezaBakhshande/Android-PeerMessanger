package com.macroid.bluetoothmessenger.di.components;

import com.macroid.bluetoothmessenger.C_DeviceListActivity;
import com.macroid.bluetoothmessenger.di.models.C_BluetoothModelDagger;
import com.macroid.bluetoothmessenger.di.models.C_ContextModel;
import com.macroid.bluetoothmessenger.di.models.C_RecyclerViewAvailableDeviceModelDagger;
import com.macroid.bluetoothmessenger.di.models.C_RecyclerViewModelDagger;
import com.macroid.bluetoothmessenger.di.scope.MainScope;

@MainScope
@dagger.Component(modules = {C_RecyclerViewModelDagger.class, C_RecyclerViewAvailableDeviceModelDagger.class, C_BluetoothModelDagger.class, C_ContextModel.class})
public interface I_DeviceListComponent
{

    void Inject(C_DeviceListActivity deviceListActivity);

}