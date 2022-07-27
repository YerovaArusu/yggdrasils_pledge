package yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;
import yerova.yggdrasilsplegde.core.abilities.*;
import yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu.button.UniversalButton;

public class YggdrasilMenu extends ModdedScreen {

    public ItemRenderer itemRe;
    public YggdrasilMenu() {
        super(new TextComponent(""));
        itemRe = this.itemRenderer;

    }

    @Override
    public void init() {


        bookLeft = width / 2 - FULL_WIDTH / 2;
        bookTop = height / 2 - FULL_HEIGHT / 2;
        bookRight = width / 2 + FULL_WIDTH / 2;
        bookBottom = height / 2 + FULL_HEIGHT / 2;

/*
        this.addRenderableWidget(new UniversalButton(this, bookLeft + (FULL_WIDTH /4) * 3, bookTop + FULL_HEIGHT / 2, 32, 32,
                new ResourceLocation(YggdrasilsPlegde.MOD_ID, "textures/gui/zetsuen_icon.png"), FullSerenitySkill::onAbilitySet));

        this.addRenderableWidget(new UniversalButton(this, bookLeft + FULL_WIDTH / 4, bookTop + FULL_HEIGHT / 2, 32, 32,
                new ResourceLocation(YggdrasilsPlegde.MOD_ID, "textures/gui/kabbalah_icon.png"), BerserkSkill::onAbilitySet));

        this.addRenderableWidget(new UniversalButton(this, bookLeft + FULL_WIDTH / 4, bookTop + (FULL_HEIGHT / 4) * 3 , 32, 32,
                new ResourceLocation(YggdrasilsPlegde.MOD_ID, "textures/gui/fireball-ability.png"), FireballAbility::onAbilitySet));
*/

        for(Ability ability: AbilityInit.ABILITIES) {
            this.addRenderableWidget(ability.getUniversalButton(this,  bookLeft, bookTop));
        }

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
