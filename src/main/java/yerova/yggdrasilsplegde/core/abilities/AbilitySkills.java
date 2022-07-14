package yerova.yggdrasilsplegde.core.abilities;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;

public class AbilitySkills {

    private static int skillSetter = 0;
    private static int firstSetSkill = 0;
    private static int secondSetSkill = 1;
    private static int thirdSetSkill = 1;

    //TODO: Add more abilities
    public static final int BERSERK_SKILL = 0;
    public static final int FULL_SERENITY_SKILL = 1;


    public static int getFirstSetSkill() {
        return firstSetSkill;
    }

    public static int getSecondSetSkill() {
        return secondSetSkill;
    }

    public static int getThirdSetSkill() {
        return thirdSetSkill;
    }



    public static void setSkillSlot(int skilSlot) {
        skillSetter = skilSlot;
        Minecraft.getInstance().player.sendMessage(new TextComponent(skilSlot + ". Skill set"), Minecraft.getInstance().player.getUUID());
    }

    public static void setSkill(int SkillID){
        switch (skillSetter){
            case 1:
                firstSetSkill = SkillID;
                break;
            case 2:
                secondSetSkill = SkillID;
                break;
            case 3:
                thirdSetSkill = SkillID;
                break;
            default:
                break;
        }
    }
}
