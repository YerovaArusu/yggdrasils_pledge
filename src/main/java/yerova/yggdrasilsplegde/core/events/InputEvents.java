package yerova.yggdrasilsplegde.core.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;
import yerova.yggdrasilsplegde.core.ClientServerCommunication;
import yerova.yggdrasilsplegde.core.abilities.AbilityManager;
import yerova.yggdrasilsplegde.core.abilities.AbilitySkills;
import yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu.OpenGUI;
import yerova.yggdrasilsplegde.core.keybindings.KeyBindsInit;

@Mod.EventBusSubscriber(modid = YggdrasilsPlegde.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class InputEvents{



    @SubscribeEvent
    public static void onKeyPresses(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;

        onInput(mc, event.getKey(), event.getAction());
    }


    @SubscribeEvent
    public static void onMouseClicked(InputEvent.MouseInputEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;
        onInput(mc, event.getButton(), event.getAction());
    }

    private static void onInput(Minecraft mc, int key, int action) {

        if (mc.screen == null) {
            // The 3 set Skills

            if (KeyBindsInit.useFirstSkill.isDown()) {
                ClientServerCommunication.sendToServer(new AbilityManager(AbilitySkills.getFirstSetSkill()));
            }
            if (KeyBindsInit.useSecondSkill.isDown()) {
                ClientServerCommunication.sendToServer(new AbilityManager(AbilitySkills.getSecondSetSkill()));
            }
            if (KeyBindsInit.useThirdSkill.isDown()) {
                ClientServerCommunication.sendToServer(new AbilityManager(AbilitySkills.getThirdSetSkill()));
            }

            //open ability menu
            if (KeyBindsInit.openAbilityMenu.isDown()) {
                Thread thread = new Thread(new OpenGUI());
                thread.start();
            }
        }

        if (KeyBindsInit.useFirstSkill.isDown()) {
            AbilitySkills.setSkillSlot(1);
        }
        if (KeyBindsInit.useSecondSkill.isDown()) {
            AbilitySkills.setSkillSlot(2);
        }
        if (KeyBindsInit.useThirdSkill.isDown()) {
            AbilitySkills.setSkillSlot(3);
        }

    }



}
