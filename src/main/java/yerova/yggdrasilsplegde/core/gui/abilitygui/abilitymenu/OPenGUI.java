package yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu;

import net.minecraft.client.Minecraft;

public class OPenGUI implements Runnable{
    public OPenGUI() {
        if(Minecraft.getInstance() != null) {
            Minecraft.getInstance().setScreen(new AbilityMainMenu());
        }

    }

    @Override
    public void run() {

    }
}
