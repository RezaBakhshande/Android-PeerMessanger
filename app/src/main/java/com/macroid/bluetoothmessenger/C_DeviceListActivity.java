package com.macroid.bluetoothmessenger;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.macroid.bluetoothmessenger.adapters.C_PairedDevicesRecyclerViewAdapter;
import com.macroid.bluetoothmessenger.adapters.I_OnItemListener;
import com.macroid.bluetoothmessenger.di.components.DaggerI_DeviceListComponent;
import com.macroid.bluetoothmessenger.di.models.C_BluetoothModelDagger;
import com.macroid.bluetoothmessenger.di.models.C_RecyclerViewModelDagger;
import com.macroid.bluetoothmessenger.models.C_BluetoothModel;
import com.macroid.bluetoothmessenger.models.C_DeviceModel;

import java.util.Objects;
import java.util.Set;

import javax.inject.Inject;

public class C_DeviceListActivity extends AppCompatActivity
{

    @Inject
    C_BluetoothModel bluetoothModel;

    @Inject
    C_PairedDevicesRecyclerViewAdapter pairedDevicesRecyclerViewAdapter;

    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

    private RecyclerView recyclerView;
    private TextView textViewMyDeviceName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c__device_list);
        //Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        F_FindViews();
        textViewMyDeviceName.setText(bluetoothAdapter.getName());
        F_DaggerImplimentation();
        pairedDevicesRecyclerViewAdapter.F_SetDeviceModels(bluetoothModel.F_ParedDeviceList(getApplicationContext()));
        //RecyclerView
        F_ConfigureRecyclerView();
    }


    //5-Intializing recyclerview & adding data to it
    private void F_ConfigureRecyclerView()
    {
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setAdapter(pairedDevicesRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
    }

    //This method for implimentation dagger2
    private void F_DaggerImplimentation()
    {
        DaggerI_DeviceListComponent.builder()
                .c_BluetoothModelDagger(new C_BluetoothModelDagger(bluetoothAdapter, pairedDevices))
                .c_RecyclerViewModelDagger(new C_RecyclerViewModelDagger(new I_OnItemListener()
                {
                    @Override
                    public void onItemClick(int position)
                    {
                        Toast.makeText(getApplicationContext() , String.valueOf(position),Toast.LENGTH_SHORT).show();
                    }
                }))
                .build()
                .Inject(this);
    }

    private void F_FindViews()
    {
        recyclerView = findViewById(R.id.recyclerView);
        textViewMyDeviceName = findViewById(R.id.textViewMyDeviceName);
    }
}