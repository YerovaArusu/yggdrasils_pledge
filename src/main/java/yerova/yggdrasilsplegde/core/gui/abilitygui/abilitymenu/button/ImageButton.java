package yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu.button;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;
import yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu.AbilityMainMenu;
import yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu.ModdedScreen;

import java.util.ArrayList;
import java.util.List;

public class ImageButton extends Button {

    public ResourceLocation image;
    public String resourceIcon;
    int u, v, image_width, image_height;
    ModdedScreen parent;
    TranslatableComponent toolTip;
    public boolean soundDisabled = false;


    public ImageButton(int x, int y, int u, int v, int w, int h, int image_width, int image_height, String resource_image, Button.OnPress onPress) {
        super(x, y, w, h, new TextComponent(""), onPress);
        this.x = x;
        this.y = y;
        this.resourceIcon = resource_image;

        this.u = u;
        this.v = v;
        this.image_height = image_height;
        this.image_width = image_width;
        //System.out.println(width);
        image = new ResourceLocation(YggdrasilsPlegde.MOD_ID, resource_image);

    }

    public ImageButton withTooltip(AbilityMainMenu parent, TranslatableComponent toolTip) {
        this.parent = parent;
        this.toolTip = toolTip;
        return this;
    }

    @Override
    protected void renderBg(PoseStack p_230441_1_, Minecraft p_230441_2_, int p_230441_3_, int p_230441_4_) {

    }

    @Override
    public void render(PoseStack ms, int parX, int parY, float partialTicks) {
//        super.render(ms, parX, parY, partialTicks);
        if (visible) {
            if (parent != null && parent.isMouseInRelativeRange(parX, parY, x, y, width, height) && toolTip != null) {
                if (!toolTip.toString().isEmpty()) {
                    List<Component> tip = new ArrayList<>();
                    tip.add(toolTip);
                    parent.tooltip = tip;
                }
            }
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            AbilityMainMenu.drawFromTexture(image, x, y, u, v, width, height, image_width, image_height, ms);
        }
    }

    @Override
    public void playDownSound(SoundManager pHandler) {
        if (soundDisabled)
            return;
        super.playDownSound(pHandler);
    }
}
