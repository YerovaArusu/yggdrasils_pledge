package yerova.yggdrasilsplegde.core.abilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.block.Blocks;
import yerova.yggdrasilsplegde.core.capabilities.reiryoku.data.ReiryokuProvider;
import yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu.button.UniversalButton;

public class FullSerenitySkill implements Runnable {

    private ServerPlayer player;
    public FullSerenitySkill(ServerPlayer player) {
        this.player = player;
    }

    @Override
    public void run() {
        if(AbilityManager.continueFullSerenity) {

            int lvl;
            int stoneDestroyed = player.getStats().getValue(Stats.BLOCK_MINED, Blocks.STONE);

            player.sendMessage(new TextComponent("Full-Serenity-Skill Activated"), player.getUUID());

            //TODO: add ManaMastery (lvls 1-10)
            if(stoneDestroyed <= 50) {lvl =1;
            }else if(stoneDestroyed <= 100) { lvl = 2;
            }else if(stoneDestroyed <= 200) { lvl = 3;
            }else if(stoneDestroyed <= 300) {lvl = 4;
            }else if(stoneDestroyed <= 400) {lvl = 5;
            }else if(stoneDestroyed <= 500) {lvl = 6;
            }else if(stoneDestroyed <= 600) {lvl = 7;
            }else if(stoneDestroyed <= 700) {lvl = 8;
            }else if(stoneDestroyed <= 800) {lvl = 9;
            }else if(stoneDestroyed <= 900) {lvl = 10;
            }else {lvl = 11;}

            //TODO: specialice the Skill for better advantages

            player.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2.0 + (3 * lvl));
            player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1 + ((0.1 *0.2) * lvl));


            while (AbilityManager.continueFullSerenity) {
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, lvl -1, false, true, true, null));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20,  (int) ((lvl -1) /4) , false, true, true, null));


                player.getCapability(ReiryokuProvider.REIRYOKU).ifPresent(reiryoku -> {
                    reiryoku.decreaseReiryoku(15);
                    if(reiryoku.getReiryoku() - 15 < 15) {
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
            player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1);

            player.sendMessage(new TextComponent("Full-Serenity-Skill Deactivated"), player.getUUID());
        }

    }

    public static void onAbilitySet(Button button) {
        if (button instanceof UniversalButton) {
            Minecraft.getInstance().player.sendMessage(new TextComponent("Berserk set."), Minecraft.getInstance().player.getUUID());
            AbilitySkills.setSkill(AbilitySkills.FULL_SERENITY_SKILL);
        }
    }
}
