package yerova.yggdrasilsplegde.core.abilities;

import net.minecraft.resources.ResourceLocation;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;

import java.util.ArrayList;

public class AbilityInit {

    public static final ArrayList<Ability> ABILITIES = new ArrayList<Ability>();

    public AbilityInit() {

    }

    public static void register() {
        ABILITIES.add(new Ability("Full Serenity", FullSerenitySkill.getID, new ResourceLocation(YggdrasilsPlegde.MOD_ID, "textures/gui/zetsuen_icon.png")
                , 100, 100, FullSerenitySkill::onAbilitySet));

        ABILITIES.add(new Ability("Berserk", BerserkSkill.getID, new ResourceLocation(YggdrasilsPlegde.MOD_ID, "textures/gui/kabbalah_icon.png"),
                200, 200, BerserkSkill::onAbilitySet));

        ABILITIES.add(new Ability("Fireball", FireballAbility.getID, new ResourceLocation(YggdrasilsPlegde.MOD_ID, "textures/gui/fireball-ability.png"),
                300, 250, FireballAbility::onAbilitySet));
    }
}
