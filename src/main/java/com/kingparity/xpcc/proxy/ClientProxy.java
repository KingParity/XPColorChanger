package com.kingparity.xpcc.proxy;

import com.kingparity.xpcc.client.ClientEvents;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy implements Proxy
{
    @Override
    public void setupClient()
    {
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        MinecraftForge.EVENT_BUS.register(this);
    }
}
