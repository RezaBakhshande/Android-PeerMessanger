package com.macroid.bluetoothmessenger.di.components;


import com.macroid.bluetoothmessenger.C_ChatActivity;
import com.macroid.bluetoothmessenger.C_DeviceListActivity;
import com.macroid.bluetoothmessenger.di.models.C_BluetoothModelDagger;
import com.macroid.bluetoothmessenger.di.models.C_PermissionHelperModelDagger;
import com.macroid.bluetoothmessenger.di.models.C_RecyclerViewModelDagger;
import com.macroid.bluetoothmessenger.di.models.ContextModel;
import com.macroid.bluetoothmessenger.di.scope.MainScope;

@MainScope
@dagger.Component(modules = {C_BluetoothModelDagger.class, C_PermissionHelperModelDagger.class, ContextModel.class})
public interface I_ChatActivityComponent
{

    void Inject(C_ChatActivity chatActivity);

}
