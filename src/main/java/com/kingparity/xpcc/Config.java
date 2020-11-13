package com.kingparity.xpcc;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config
{
    public static class Client
    {
        public final ForgeConfigSpec.IntValue xpBarRed;
        public final ForgeConfigSpec.IntValue xpBarGreen;
        public final ForgeConfigSpec.IntValue xpBarBlue;
        
        Client(ForgeConfigSpec.Builder builder)
        {
            builder.comment("Client configuration settings").push("client");
            {
                builder.comment("Configuration options for XP Bar").push("hud");
                this.xpBarRed = builder.comment("XP Bar Red Configuration").translation(Reference.ID + ".config.client.hud.xp_bar_red").defineInRange("xpBarRed", 128, 0, 255);
                this.xpBarGreen = builder.comment("XP Bar Green Configuration").translation(Reference.ID + ".config.client.hud.xp_bar_green").defineInRange("xpBarGreen", 255, 0, 255);
                this.xpBarBlue = builder.comment("XP Bar Blue Configuration").translation(Reference.ID + ".config.client.hud.xp_bar_blue").defineInRange("xpBarBlue", 32, 0, 255);
                builder.pop();
            }
            builder.pop();
        }
    }
    
    static final ForgeConfigSpec clientSpec;
    public static final Config.Client CLIENT;
    
    static
    {
        final Pair<Client, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(Config.Client::new);
        clientSpec = clientSpecPair.getRight();
        CLIENT = clientSpecPair.getLeft();
    }
}
