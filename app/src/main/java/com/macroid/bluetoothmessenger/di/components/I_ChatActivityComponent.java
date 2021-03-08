package com.macroid.bluetoothmessenger.di.components;


import com.macroid.bluetoothmessenger.C_ChatActivity;
import com.macroid.bluetoothmessenger.di.models.C_BluetoothModelDagger;
import com.macroid.bluetoothmessenger.di.models.C_PermissionHelperModelDagger;
import com.macroid.bluetoothmessenger.di.models.C_ContextModel;
import com.macroid.bluetoothmessenger.di.scope.MainScope;

@MainScope
@dagger.Component(modules = {C_BluetoothModelDagger.class, C_PermissionHelperModelDagger.class, C_ContextModel.class})
public interface I_ChatActivityComponent
{

    void Inject(C_ChatActivity chatActivity);

}
