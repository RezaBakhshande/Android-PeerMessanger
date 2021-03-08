package com.macroid.bluetoothmessenger.di.models;

import com.macroid.bluetoothmessenger.adapters.C_AvailableDeviceRecyclerViewAdapter;
import com.macroid.bluetoothmessenger.adapters.I_OnItemListener;
import com.macroid.bluetoothmessenger.di.scope.MainScope;

import dagger.Module;
import dagger.Provides;

@Module
public class C_RecyclerViewAvailableDeviceModelDagger
{

    I_OnItemListener onItemListener;

    public C_RecyclerViewAvailableDeviceModelDagger(I_OnItemListener onItemListener)
    {
        this.onItemListener = onItemListener;
    }

    @MainScope
    @Provides
    public C_AvailableDeviceRecyclerViewAdapter F_GetAvailableRecyclerView(I_OnItemListener onItemListener)
    {
        return new C_AvailableDeviceRecyclerViewAdapter(onItemListener);
    }
}
