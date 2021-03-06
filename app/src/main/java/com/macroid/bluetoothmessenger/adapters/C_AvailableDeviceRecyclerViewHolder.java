package com.macroid.bluetoothmessenger.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.macroid.bluetoothmessenger.R;

public class C_AvailableDeviceRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    //Widgets
    TextView textViewAvailableDevices;

    //Click Listener
    I_OnItemListener IOnItemListener;

    public C_AvailableDeviceRecyclerViewHolder(@NonNull View itemView , I_OnItemListener IOnItemListener)
    {
        super(itemView);
        this.IOnItemListener = IOnItemListener;
        textViewAvailableDevices = itemView.findViewById(R.id.textViewAvailableDevices);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        IOnItemListener.onItemClick(getAdapterPosition());
    }

}
