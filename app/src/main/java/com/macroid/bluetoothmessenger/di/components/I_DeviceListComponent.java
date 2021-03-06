package com.macroid.bluetoothmessenger.di.components;

import com.macroid.bluetoothmessenger.C_DeviceListActivity;
import com.macroid.bluetoothmessenger.di.models.C_BluetoothModelDagger;
import com.macroid.bluetoothmessenger.di.models.C_RecyclerViewModelDagger;
import com.macroid.bluetoothmessenger.di.models.ContextModel;
import com.macroid.bluetoothmessenger.di.scope.MainScope;

@MainScope
@dagger.Component(modules = {C_RecyclerViewModelDagger.class, C_BluetoothModelDagger.class, ContextModel.class})
public interface I_DeviceListComponent
{

    void Inject(C_DeviceListActivity deviceListActivity);

}