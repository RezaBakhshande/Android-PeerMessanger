package com.macroid.bluetoothmessenger.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.macroid.bluetoothmessenger.R;
import com.macroid.bluetoothmessenger.models.C_DeviceModel;

import java.util.List;

public class C_PairedDevicesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List<C_DeviceModel> deviceModels;
    private I_OnItemListener onItemListener;

    public C_PairedDevicesRecyclerViewAdapter(I_OnItemListener onItemListener)
    {
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,
                parent, false);
        return new C_PairedDeviceRecyclerViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        //
        ((C_PairedDeviceRecyclerViewHolder) holder).textView.setText(deviceModels.get(position).getDeviceName());


    }

    @Override
    public int getItemCount()
    {
        if (deviceModels != null)
        {
            return deviceModels.size();
        }
        return 0;
    }

    public void F_SetDeviceModels(List<C_DeviceModel> deviceModels)
    {
        this.deviceModels = deviceModels;
    }


}
