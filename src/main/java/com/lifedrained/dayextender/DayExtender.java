package com.lifedrained.dayextender;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.lifedrained.dayextender.Config.dayFactor;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DayExtender.MODID)
@OnlyIn(Dist.DEDICATED_SERVER)
public class DayExtender
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "dayextender";


    public DayExtender(FMLJavaModLoadingContext context) {
        context.registerConfig(ModConfig.Type.SERVER, Config.SPEC, "dayextender.toml");
        MinecraftForge.EVENT_BUS.register(this);
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END){
            MinecraftServer server = event.getServer();
            server.getAllLevels().forEach((level) -> {
                if (level.getGameTime() %  dayFactor != 0){
                    if (level.isDay()){
                        long currentTime = level.getDayTime();
                        level.setDayTime(currentTime - 1);
                    }
                }
            });
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
//    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
//    public static class ClientModEvents
//    {

//    }
}
