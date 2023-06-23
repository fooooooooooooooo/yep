package ooo.foooooooooooo.yep.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import ooo.foooooooooooo.yep.Yep;

public class ClientProxy implements CommonProxy {
    @Override
    public void init(FMLInitializationEvent event) {
        Yep.LOGGER.warn("Client environment detected - Yep is disabled");
    }
}
