package yerova.yggdrasilsplegde.core.items.weapons;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;
import yerova.yggdrasilsplegde.core.ClientServerCommunication;
import yerova.yggdrasilsplegde.core.capabilities.reiryoku.network.ReiryokuManagerToServer;
import yerova.yggdrasilsplegde.core.items.client.YggdZephyrRenderer;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class YggdZephyr extends Item implements IAnimatable {
    private Player player;
    private Multimap<Attribute, AttributeModifier> defaultModifiers;
    private ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
    private UUID boundUUID;
    private ItemStack currentItem;
    private CompoundTag nbtData;


    public AnimationFactory factory = new AnimationFactory(this);

    public YggdZephyr(Properties properties) {
        super(properties);
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier",0,
                AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }


    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {
            private final BlockEntityWithoutLevelRenderer renderer = new YggdZephyrRenderer();


            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return renderer;
            }
        });
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        currentItem = player.getItemInHand(hand);
        this.player = player;
        builder = ImmutableMultimap.builder();

        handleNBT();


        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 23, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag tFlag) {
        if(stack.hasTag()) {
            list.add(new TextComponent("Bound to §b" + level.getPlayerByUUID(stack.getTag().getUUID(YggdrasilsPlegde.MOD_ID + ".bound_player")).getName().getString()));

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
