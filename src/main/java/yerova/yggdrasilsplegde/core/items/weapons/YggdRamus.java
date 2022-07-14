package yerova.yggdrasilsplegde.core.items.weapons;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;
import yerova.yggdrasilsplegde.core.ClientServerCommunication;
import yerova.yggdrasilsplegde.core.capabilities.reiryoku.data.ReiryokuManager;
import yerova.yggdrasilsplegde.core.capabilities.reiryoku.network.ReiryokuManagerToServer;

import java.util.List;
import java.util.UUID;

public class YggdRamus extends Item {
    private Player player;
    private Multimap<Attribute, AttributeModifier> defaultModifiers;
    private ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
    private UUID boundUUID;
    private ItemStack currentItem;
    private CompoundTag nbtData;


    public YggdRamus(Properties p) {
        super(p);
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier",0,
                AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        currentItem = player.getItemInHand(hand);
        this.player = player;
        builder = ImmutableMultimap.builder();

        handleNBT();

        if(!nbtData.getUUID(YggdrasilsPlegde.MOD_ID + ".bound_player").equals(player.getUUID())) {
            player.drop(currentItem, true);
            player.getInventory().removeItem(currentItem);
        }

        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier",
                (double)(nbtData.getInt(YggdrasilsPlegde.MOD_ID + ".Reiryoku_charge")/400),
                AttributeModifier.Operation.ADDITION));



        this.defaultModifiers = builder.build();
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag tFlag) {
            if(stack.hasTag()) {
                list.add(new TextComponent("Bound to §b" + level.getPlayerByUUID(stack.getTag().getUUID(YggdrasilsPlegde.MOD_ID + ".bound_player")).getName().getString()));

                list.add(new TextComponent("Charge: §e" + stack.getTag().getInt(YggdrasilsPlegde.MOD_ID + ".Reiryoku_charge") + "/1000000"));
            } else {
                list.add(new TextComponent(("Unbound")));
            }

        super.appendHoverText(stack, level, list, tFlag);
    }

    public void handleNBT(){
        if(!currentItem.hasTag()) {
            nbtData = new CompoundTag();

            nbtData.putUUID(YggdrasilsPlegde.MOD_ID + ".bound_player", player.getUUID());
            nbtData.putInt(YggdrasilsPlegde.MOD_ID + ".Reiryoku_charge", 0 );
            currentItem.setTag(nbtData);
        } else {
            nbtData = currentItem.getTag();

            int charge = nbtData.getInt(YggdrasilsPlegde.MOD_ID + ".Reiryoku_charge");
            nbtData.remove(YggdrasilsPlegde.MOD_ID + ".Reiryoku_charge");
            if (charge < 1000000) {
                ClientServerCommunication.sendToServer(new ReiryokuManagerToServer(1, 8));
                nbtData.putInt(YggdrasilsPlegde.MOD_ID + ".Reiryoku_charge", charge + 8);
            }
            currentItem.setTag(nbtData);
        }
    }
}
