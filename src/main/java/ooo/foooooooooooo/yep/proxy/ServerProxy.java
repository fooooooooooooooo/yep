package ooo.foooooooooooo.yep.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import ooo.foooooooooooo.yep.EventListener;
import ooo.foooooooooooo.yep.Yep;

public class ServerProxy implements CommonProxy {
    @Override
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new EventListener());

        Yep.LOGGER.info("Yep is enabled!");
    }
}
