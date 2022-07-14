package yerova.yggdrasilsplegde.core.keybindings;


import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;


import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

@OnlyIn(Dist.CLIENT)
public class KeyBindsInit {
    //Mana Controll
    public static KeyMapping gatherMana;
    public static KeyMapping decreaseMana;


    // The 3 set Skills
    public static KeyMapping useFirstSkill;
    public static KeyMapping useSecondSkill;
    public static KeyMapping useThirdSkill;
    public static KeyMapping openAbilityMenu;

    public static void register(final FMLClientSetupEvent event) {

        //Mana Control
        gatherMana = create("gather_mana", KeyEvent.VK_R);
        decreaseMana = create("decrease_mana", KeyEvent.VK_V);

        // The 3 set Skills
        useFirstSkill = create("useFirstSkill", KeyEvent.VK_Y);
        useSecondSkill = create("useSecondSkill", KeyEvent.VK_X);
        useThirdSkill = create("useThirdSkill", KeyEvent.VK_C);

        //Ability Meni
        openAbilityMenu = create("openAbilityMenu", KeyEvent.VK_G);


        //Mana Control
        ClientRegistry.registerKeyBinding(gatherMana);
        ClientRegistry.registerKeyBinding(decreaseMana);

        // The 3 set Skills
        ClientRegistry.registerKeyBinding(useFirstSkill);
        ClientRegistry.registerKeyBinding(useSecondSkill);
        ClientRegistry.registerKeyBinding(useThirdSkill);

        //Ability Menu
        ClientRegistry.registerKeyBinding(openAbilityMenu);

    }

    private static KeyMapping create(String name, int key) {
        return new KeyMapping("key." + YggdrasilsPlegde.MOD_ID + "." + name, key, "key.category." + YggdrasilsPlegde.MOD_ID);
    }
}
