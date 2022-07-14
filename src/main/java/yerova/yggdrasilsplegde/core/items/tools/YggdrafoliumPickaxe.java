package yerova.yggdrasilsplegde.core.items.tools;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeTier;


public class YggdrafoliumPickaxe extends PickaxeItem {
    public YggdrafoliumPickaxe(ForgeTier yggdrafolium, int i, float v, Item.Properties group) {
        super(yggdrafolium, i, v, group);
    }


    //Todo: implement an actual usefull ability
    // Ability: Vain - Miner

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide) {


                LargeFireball fireball = new LargeFireball(level, player, player.getX(), player.getEyeY(), player.getX(), 5);
                fireball.setNoGravity(true);
                level.addFreshEntity(fireball);

                fireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 2, 20, 0);


        }

        return super.use(level, player, hand);

    }

}
