package com.macroid.bluetoothmessenger.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.macroid.bluetoothmessenger.R;


public class C_PairedDeviceRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    //Widgets
    TextView textView;

    //Click Listener
    I_OnItemListener IOnItemListener;

    public C_PairedDeviceRecyclerViewHolder(@NonNull View itemView , I_OnItemListener IOnItemListener)
    {
        super(itemView);
        this.IOnItemListener = IOnItemListener;
        textView = itemView.findViewById(R.id.textViewDeviceName);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        IOnItemListener.onItemClick(getAdapterPosition());
    }
}
