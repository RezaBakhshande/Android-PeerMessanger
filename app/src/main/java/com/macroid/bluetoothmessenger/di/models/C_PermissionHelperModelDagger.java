package com.macroid.bluetoothmessenger.di.models;

import androidx.appcompat.app.AppCompatActivity;

import com.macroid.bluetoothmessenger.di.scope.MainScope;
import com.macroid.bluetoothmessenger.utils.C_PermissionsHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class C_PermissionHelperModelDagger
{
    private final AppCompatActivity appCompatActivity;

    public C_PermissionHelperModelDagger(AppCompatActivity appCompatActivity)
    {
        this.appCompatActivity = appCompatActivity;
    }

    @MainScope
    @Provides
    public C_PermissionsHelper F_GetPermissionsHelper(AppCompatActivity appCompatActivity)
    {
        return new C_PermissionsHelper(appCompatActivity);
    }

    @MainScope
    @Provides
    public AppCompatActivity appCompatActivity()
    {
        return appCompatActivity;
    }
}
