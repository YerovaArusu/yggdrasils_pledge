package yerova.yggdrasilsplegde.core.abilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;
import yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu.Menus;
import yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu.ModdedScreen;
import yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu.YggdrasilMenu;
import yerova.yggdrasilsplegde.core.gui.abilitygui.abilitymenu.button.UniversalButton;

import java.util.ArrayList;

public class Ability {

    private int ID = 0;
    private String name = null;
    private ResourceLocation IconLocation = null;
    private Button.OnPress onPress= null;
    private int x = 0;
    private int y = 0;

    //private ArrayList<Ability> abilityList= new ArrayList<Ability>();
    public Ability(String name, int id, ResourceLocation IconLoc,int x, int y, Button.OnPress onPress) {
        this.ID = id;
        this.name = name;
        this.IconLocation = IconLoc;
        this.onPress = onPress;
        this.x = x;
        this.y = y;
    }

    public int getID(){
        return ID;
    }

    public String getName(){
        return name;
    }


    public UniversalButton getUniversalButton(ModdedScreen screen, int bookLeft, int bookTop){
        return new UniversalButton(screen, bookLeft + this.x, bookTop + y, 32, 32, IconLocation, onPress);
    }
}
