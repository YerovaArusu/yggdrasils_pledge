package yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;
import yerova.yggdrasilsplegde.core.abilities.AbilitySkills;
import yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu.button.UniversalButton;
import yerova.yggdrasilsplegde.core.keybindings.KeyBindsInit;

import java.awt.event.KeyListener;
import java.util.List;

public class AbilityMainMenu extends ModdedScreen {
    public ItemRenderer itemRe;
    public static ModdedScreen screen;

    public static ModdedScreen getScreen() {
        return screen;
    }

    public static List<UniversalButton> currentButtonsList;
    public static TextComponent TITLE = new TextComponent("AbilityMainMenu");

    public AbilityMainMenu() {
        super(AbilityMainMenu.TITLE);
        itemRe = this.itemRenderer;
        screen = this;

    }


    @Override
    public void init() {

        minecraft.keyboardHandler.setSendRepeatsToGui(true);
        bookLeft = width / 2 - FULL_WIDTH / 2;
        bookTop = height / 2 - FULL_HEIGHT / 2;
        bookRight = width / 2 + FULL_WIDTH / 2;
        bookBottom = height / 2 + FULL_HEIGHT / 2;

        //Methode für ein FensterWidget wo ein neues Object ein neues Fenster öffnen kann.
        addRenderableWidget(new UniversalButton(this, bookLeft + FULL_WIDTH / 2, bookTop + FULL_HEIGHT / 2, 64, 64,
                new ResourceLocation(YggdrasilsPlegde.MOD_ID, "textures/gui/yggdrasil_icon.png"), UniversalButton::onYggdrasilOpenClick));


        //TODO: Add Stats from Player and enchance design

        super.init();

    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (scaleFactor != 1) {
            matrixStack.scale(scaleFactor, scaleFactor, scaleFactor);
            mouseX /= scaleFactor;
            mouseY /= scaleFactor;
        }
        drawScreenAfterScale(matrixStack, mouseX, mouseY, partialTicks);
        matrixStack.popPose();
    }

}
