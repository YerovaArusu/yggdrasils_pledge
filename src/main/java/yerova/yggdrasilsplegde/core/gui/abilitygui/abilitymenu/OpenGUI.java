package yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu;

import net.minecraft.client.Minecraft;

public class OpenGUI implements Runnable{
    public OpenGUI() {
        if(Minecraft.getInstance() != null) {
            Minecraft.getInstance().setScreen(Menus.ABILITY_MAIN_MENU);
        }

    }

    @Override
    public void run() {

    }
}
