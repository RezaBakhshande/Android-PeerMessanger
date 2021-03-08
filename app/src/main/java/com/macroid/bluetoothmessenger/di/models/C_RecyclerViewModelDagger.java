package com.macroid.bluetoothmessenger.di.models;


import com.macroid.bluetoothmessenger.adapters.C_PairedDevicesRecyclerViewAdapter;
import com.macroid.bluetoothmessenger.adapters.I_OnItemListener;
import com.macroid.bluetoothmessenger.di.scope.MainScope;

import dagger.Module;
import dagger.Provides;

@Module
public class C_RecyclerViewModelDagger
{
    I_OnItemListener onItemListener;

    public C_RecyclerViewModelDagger(I_OnItemListener onItemListener)
    {
        this.onItemListener = onItemListener;
    }

    @MainScope
    @Provides
    public C_PairedDevicesRecyclerViewAdapter F_GetRecyclerView(I_OnItemListener onItemListener)
    {
        return new C_PairedDevicesRecyclerViewAdapter(onItemListener);
    }

    @MainScope
    @Provides
    public I_OnItemListener F_GetonItemListener()
    {
        return onItemListener;
    }
}
