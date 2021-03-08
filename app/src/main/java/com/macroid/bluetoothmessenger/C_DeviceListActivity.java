package com.macroid.bluetoothmessenger;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.macroid.bluetoothmessenger.adapters.C_AvailableDeviceRecyclerViewAdapter;
import com.macroid.bluetoothmessenger.adapters.C_PairedDevicesRecyclerViewAdapter;
import com.macroid.bluetoothmessenger.adapters.I_OnItemListener;
import com.macroid.bluetoothmessenger.di.components.DaggerI_DeviceListComponent;
import com.macroid.bluetoothmessenger.di.models.C_BluetoothModelDagger;
import com.macroid.bluetoothmessenger.di.models.C_RecyclerViewAvailableDeviceModelDagger;
import com.macroid.bluetoothmessenger.di.models.C_RecyclerViewModelDagger;
import com.macroid.bluetoothmessenger.models.C_BluetoothModel;
import com.macroid.bluetoothmessenger.models.C_DeviceModel;
import com.macroid.bluetoothmessenger.viewmodels.C_ItemListViewModel;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

public class C_DeviceListActivity extends AppCompatActivity
{

    @Inject
    C_BluetoothModel bluetoothModel;

    @Inject
    C_PairedDevicesRecyclerViewAdapter pairedDevicesRecyclerViewAdapter;

    @Inject
    C_AvailableDeviceRecyclerViewAdapter availableDeviceRecyclerViewAdapter;

    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

    //ViewModel
    private C_ItemListViewModel itemListViewModel;


    private RecyclerView recyclerViewPairedDevices;
    private RecyclerView recyclerViewAvailableDevices;
    private TextView textViewMyDeviceName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c__device_list);
        //Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        itemListViewModel = new ViewModelProvider(this).get(C_ItemListViewModel.class);
        F_FindViews();
        textViewMyDeviceName.setText(bluetoothAdapter.getName());

        F_DaggerImplimentation();


        //availableDeviceRecyclerViewAdapter.F_SetAvailableDeviceModels(bluetoothModel.F_FindDevice(getApplicationContext()));

        F_ObservAnyChange();

        itemListViewModel.F_ParedDeviceList(pairedDevices);


        // Register for broadcasts when a device is discovered.
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        itemListViewModel.F_AvailableDeviceList(getApplicationContext(), filter);



        //RecyclerView
        F_ConfigureRecyclerView();
    }


    //5-Intializing recyclerview & adding data to it
    private void F_ConfigureRecyclerView()
    {
        recyclerViewPairedDevices.setHasFixedSize(true);
        recyclerViewPairedDevices.setItemViewCacheSize(20);
        recyclerViewPairedDevices.setDrawingCacheEnabled(true);
        recyclerViewPairedDevices.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerViewPairedDevices.setAdapter(pairedDevicesRecyclerViewAdapter);
        recyclerViewPairedDevices.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        recyclerViewAvailableDevices.setHasFixedSize(true);
        recyclerViewAvailableDevices.setItemViewCacheSize(20);
        recyclerViewAvailableDevices.setDrawingCacheEnabled(true);
        recyclerViewAvailableDevices.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerViewAvailableDevices.setAdapter(availableDeviceRecyclerViewAdapter);
        recyclerViewAvailableDevices.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
    }

    //This method for implimentation dagger2
    private void F_DaggerImplimentation()
    {
        DaggerI_DeviceListComponent.builder()
                .c_BluetoothModelDagger(new C_BluetoothModelDagger())
                .c_RecyclerViewModelDagger(new C_RecyclerViewModelDagger(new I_OnItemListener()
                {
                    @Override
                    public void onItemClick(int position)
                    {
                        Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                    }
                }))
                .c_RecyclerViewAvailableDeviceModelDagger(new C_RecyclerViewAvailableDeviceModelDagger(new I_OnItemListener()
                {
                    @Override
                    public void onItemClick(int position)
                    {

                    }
                }))
                .build()
                .Inject(this);
    }

    //Observing any data change
    private void F_ObservAnyChange()
    {
        itemListViewModel.F_GetItems().observe(this, new Observer<List<C_DeviceModel>>()
        {
            @Override
            public void onChanged(List<C_DeviceModel> deviceModels)
            {
                //Observing for any data change
                if (deviceModels != null)
                {
                    pairedDevicesRecyclerViewAdapter.F_SetDeviceModels(deviceModels);
                    pairedDevicesRecyclerViewAdapter.notifyDataSetChanged();

                    availableDeviceRecyclerViewAdapter.F_SetAvailableDeviceModels(deviceModels);
                    availableDeviceRecyclerViewAdapter.notifyDataSetChanged();
                }

            }
        });
    }

    private void F_FindViews()
    {
        recyclerViewPairedDevices = findViewById(R.id.recyclerView);
        recyclerViewAvailableDevices = findViewById(R.id.recyclerViewAvailableDevice);
        textViewMyDeviceName = findViewById(R.id.textViewMyDeviceName);
    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(this, C_ChatActivity.class));
        finish();
        super.onBackPressed();
    }
}