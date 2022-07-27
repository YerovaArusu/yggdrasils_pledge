package yerova.yggdrasilsplegde.core.abilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.projectile.LargeFireball;
import yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu.button.UniversalButton;


public class FireballAbility implements Runnable {

    public static final int getID = 3;
    private ServerPlayer player;
    public static final int ID = 3;

    public FireballAbility(ServerPlayer player) {
        this.player = player;
    }

    @Override
    public void run() {
        LargeFireball fireball = new LargeFireball(player.getLevel(), player, player.getX(), player.getEyeY(), player.getX(), 5);
        fireball.setNoGravity(true);
        player.getLevel().addFreshEntity(fireball);

        fireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 2, 20, 0);
    }

    public static void onAbilitySet(Button button) {
        if(button instanceof UniversalButton) {
            Minecraft.getInstance().player.sendMessage(new TextComponent("Fireball-Ability set."), Minecraft.getInstance().player.getUUID());
            AbilitySkills.setSkill(ID);
        }
    }


}
