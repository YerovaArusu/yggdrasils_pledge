package yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;

import java.util.List;

public class ModdedScreen extends Screen {


    public final int FULL_WIDTH = 534;
    public final int FULL_HEIGHT = 300;
    public ResourceLocation background = new ResourceLocation(YggdrasilsPlegde.MOD_ID, "textures/gui/ability_screen.png");
    public int bookLeft;
    public int bookTop;
    public int bookRight;
    public int bookBottom;
    public int maxScale;
    public float scaleFactor;
    public List<Component> tooltip;

    public ModdedScreen(Component titleIn) {
        super(titleIn);

    }

    @Override
    public void init() {

        minecraft.keyboardHandler.setSendRepeatsToGui(true);
        this.minecraft.keyboardHandler.tick();
        bookLeft = width / 2 - FULL_WIDTH / 2;
        bookTop = height / 2 - FULL_HEIGHT / 2;
        bookRight = width / 2 + FULL_WIDTH / 2;
        bookBottom = height / 2 + FULL_HEIGHT / 2;


        Window res = getMinecraft().getWindow();
        double oldGuiScale = res.calculateScale(minecraft.options.guiScale, minecraft.isEnforceUnicode());
        maxScale = getMaxAllowedScale();
        int persistentScale = Math.min(0, maxScale);
        double newGuiScale = res.calculateScale(persistentScale, minecraft.isEnforceUnicode());

        if(persistentScale > 0 && newGuiScale != oldGuiScale) {
            scaleFactor = (float) newGuiScale / (float) res.getGuiScale();

            res.setGuiScale(newGuiScale);
            width = res.getGuiScaledWidth();
            height = res.getGuiScaledHeight();
            res.setGuiScale(oldGuiScale);
        } else scaleFactor = 1;

        super.init();
    }

    public boolean isMouseInRelativeRange(int mouseX, int mouseY, int x, int y, int w, int h) {

        return mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h;
    }

    public void drawTooltip(PoseStack stack, int mouseX, int mouseY) {
        if (tooltip != null && !tooltip.isEmpty()) {
            this.renderComponentTooltip(stack, tooltip, mouseX, mouseY, font);
        }
    }


    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY,partialTicks);

    }

    public final void resetTooltip() {
        tooltip = null;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    int getMaxAllowedScale() {
        return getMinecraft().getWindow().calculateScale(0, minecraft.isEnforceUnicode());
    }


    //Rendering


    public void drawBackgroundElements(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        drawFromTexture(background,0, 0, 0, 0, FULL_WIDTH, FULL_HEIGHT, FULL_WIDTH, FULL_HEIGHT, stack);
    }

    public static void drawFromTexture(ResourceLocation resourceLocation, int x, int y, int uOffset, int vOffset, int width, int height, int fileWidth, int fileHeight, PoseStack stack) {
        RenderSystem.setShaderTexture(0, resourceLocation);
        blit(stack,x, y, uOffset, vOffset, width, height, fileWidth, fileHeight);
    }

    public void drawForegroundElements(int mouseX, int mouseY, float partialTicks) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void drawScreenAfterScale(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        resetTooltip();
        renderBackground(stack);
        stack.pushPose();
        stack.translate(bookLeft, bookTop, 0);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        drawBackgroundElements(stack,mouseX, mouseY, partialTicks);
        drawForegroundElements(mouseX, mouseY, partialTicks);
        stack.popPose();
        super.render(stack, mouseX, mouseY, partialTicks);
        drawTooltip(stack, mouseX, mouseY);
    }


}
