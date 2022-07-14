package yerova.yggdrasilsplegde.core.gui;

import net.minecraftforge.client.gui.IIngameOverlay;
import yerova.yggdrasilsplegde.core.capabilities.reiryoku.client.ClientReiryoku;

public class ReiryokuOverlay {

    public static final IIngameOverlay HUD_REIRYOKU = (gui, poseStack, partialTicks, width, height) -> {
        String toDisplay = String.valueOf(ClientReiryoku.getReiryoku() + " | " + ClientReiryoku.getMaxReiryoku());

        //TODO: Bind  X, Y, and Color  to a config file

        int x = 100;
        int y = 100;
        if (x >= 0 && y >= 0) {
            gui.getFont().draw(poseStack, toDisplay, x, y, 255);

        }
    };
}
