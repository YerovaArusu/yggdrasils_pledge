package yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu.button;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.resources.ResourceLocation;
import yerova.yggdrasilsplegde.core.abilities.AbilitySkills;
import yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu.AbilityMainMenu;
import yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu.ModdedScreen;
import yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu.YggdrasilMenu;

public class UniversalButton extends ImageButton {

    public static int skillToSet = 0;
    private ResourceLocation IMAGE;

    public UniversalButton(ModdedScreen parent, int x, int y, int width, int height, ResourceLocation imgLocation, Button.OnPress onPress) {
        super(x - (width / 2), y - (height / 2), 0, 0, 32, 32, width, height, imgLocation.getPath(), onPress);
        IMAGE = imgLocation;
        this.parent = parent;
    }

    @Override
    public void render(PoseStack ms, int parX, int parY, float partialTicks) {
        super.render(ms, parX, parY, partialTicks);

        if (visible) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            AbilityMainMenu.drawFromTexture(image, x, y, u, v, width, height, image_width, image_height, ms);
        }
    }

    public static void onYggdrasilOpenClick(Button button) {
        if (button instanceof UniversalButton) {
           Minecraft.getInstance().setScreen(new YggdrasilMenu());
        }
    }

    public static void onAbilitySetClick(Button button) {
        if (button instanceof UniversalButton) {


        }
    }
}
