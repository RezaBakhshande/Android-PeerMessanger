package com.macroid.bluetoothmessenger.di.models;

import android.content.Context;
import com.macroid.bluetoothmessenger.di.scope.MainScope;

import dagger.Module;
import dagger.Provides;

@Module
public class C_ContextModel
{
    private Context context;

    public C_ContextModel(Context context)
    {
        this.context=context;
    }

    @MainScope
    @Provides
    public Context getContext()
    {
        return context;
    }

}
