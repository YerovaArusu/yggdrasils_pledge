package yerova.yggdrasilsplegde.core.abilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import yerova.yggdrasilsplegde.core.capabilities.reiryoku.data.ReiryokuProvider;
import yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu.button.UniversalButton;


public class BerserkSkill implements Runnable {

    public static final int getID = 2;
    private ServerPlayer player;

    public BerserkSkill(ServerPlayer player) {
        this.player = player;
    }


    @Override
    public void run() {
        if (AbilityManager.continueBerserk) {

            int lvl;
            int villagerKills = player.getStats().getValue(Stats.ENTITY_KILLED, EntityType.VILLAGER);

            player.sendMessage(new TextComponent("Berserk-Skill Activated"), ChatType.GAME_INFO, player.getUUID());

            //TODO: add ManaMastery (lvls 1-10)
            if (villagerKills <= 50) {
                lvl = 1;
            } else if (villagerKills <= 100) {
                lvl = 2;
            } else if (villagerKills <= 200) {
                lvl = 3;
            } else if (villagerKills <= 300) {
                lvl = 4;
            } else if (villagerKills <= 400) {
                lvl = 5;
            } else if (villagerKills <= 500) {
                lvl = 6;
            } else if (villagerKills <= 600) {
                lvl = 7;
            } else if (villagerKills <= 700) {
                lvl = 8;
            } else if (villagerKills <= 800) {
                lvl = 9;
            } else if (villagerKills <= 900) {
                lvl = 10;
            } else if (villagerKills <= 1000) {
                lvl = 11;
            } else if (villagerKills <= 5000) {
                lvl = 12;
            } else {
                lvl = 13;
            }

            player.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2.0 + (3 * lvl));
            player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1 + ((0.1 * 0.2) * lvl));
            player.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(4 * lvl);
            player.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(2 * lvl);

            while (AbilityManager.continueBerserk) {
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, lvl - 1, false, true, true, null));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20, (int) ((lvl - 1) / 4), false, true, true, null));

                player.getCapability(ReiryokuProvider.REIRYOKU).ifPresent(reiryoku -> {
                    reiryoku.decreaseReiryoku(15);
                    if (reiryoku.getReiryoku() - 15 < 15) {
                        AbilityManager.continueFullSerenity = false;
                    }
                });

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            player.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2.0);
            player.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(0);
            player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1);
            player.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(0);
            player.sendMessage(new TextComponent("Berserk-Skill Deactivated"), ChatType.GAME_INFO, player.getUUID());
        }

    }

    public static void onAbilitySet(Button button) {
        if (button instanceof UniversalButton) {
            Minecraft.getInstance().player.sendMessage(new TextComponent("Berserk set."), Minecraft.getInstance().player.getUUID());
            AbilitySkills.setSkill(AbilityInit.ABILITIES.get(1).getID());
        }
    }
}
