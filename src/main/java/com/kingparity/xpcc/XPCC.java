package com.kingparity.xpcc;

import com.kingparity.xpcc.proxy.ClientProxy;
import com.kingparity.xpcc.proxy.Proxy;
import com.kingparity.xpcc.proxy.ServerProxy;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.ID)
public class XPCC
{
    public static final Proxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);
    public static final Logger LOGGER = LogManager.getLogger(Reference.ID);
    
    public XPCC()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.clientSpec);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        ForgeIngameGui.renderExperiance = false;
    }
    
    private void onClientSetup(FMLClientSetupEvent event)
    {
        PROXY.setupClient();
    }
}