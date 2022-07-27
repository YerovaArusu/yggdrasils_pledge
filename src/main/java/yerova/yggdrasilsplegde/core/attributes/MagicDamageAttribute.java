package yerova.yggdrasilsplegde.core.attributes;

import net.minecraft.world.item.ItemStack;
import yerova.yggdrasilsplegde.core.abilities.AbilityInit;

import java.util.ArrayList;

public class MagicDamageAttribute {

    public static ArrayList<MagicDamageAttribute> ATTRIBUTE_LIST = new ArrayList<>();
    private static int value;
    private ItemStack stack;

    public MagicDamageAttribute(ItemStack stack, int value) {
        this.value = value;
        this.stack = stack;

        ATTRIBUTE_LIST.add(this);
    }
}
