package com.lifedrained.dayextender;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Mod.EventBusSubscriber(modid = DayExtender.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.LongValue DAY_FACTOR = BUILDER.comment(
            "day factor,  extends day by multiplier"
    ).defineInRange("dayFactor", 2L, 1L,  100L);


    static final ForgeConfigSpec SPEC = BUILDER.build();
    private static final Logger log = LoggerFactory.getLogger(Config.class);

    public static long dayFactor;


    @SubscribeEvent
    static void onLoad(final ModConfigEvent.Loading event){
        updateDayFactor();
    }

    @SubscribeEvent
    static void onReload(final ModConfigEvent.Reloading event){
        updateDayFactor();
    }

    private static void updateDayFactor(){
        dayFactor = DAY_FACTOR.get();
        log.debug("Loaded day factor: {}", dayFactor);
    }

}
