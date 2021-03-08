package com.macroid.bluetoothmessenger.models;

public class C_DeviceModel
{
    private String deviceName;
    private String deviceAddress;

    public C_DeviceModel(String deviceName, String deviceAddress)
    {
        this.deviceName = deviceName;
        this.deviceAddress = deviceAddress;
    }

    public String getDeviceName()
    {
        return deviceName;
    }

    public void setDeviceName(String deviceName)
    {
        this.deviceName = deviceName;
    }

    public String getDeviceAddress()
    {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress)
    {
        this.deviceAddress = deviceAddress;
    }
}
