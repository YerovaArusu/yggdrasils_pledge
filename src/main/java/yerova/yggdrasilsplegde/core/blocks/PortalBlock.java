package yerova.yggdrasilsplegde.core.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import yerova.yggdrasilsplegde.core.varia.Tools;
import yerova.yggdrasilsplegde.core.worldgen.dimension.DimensionInit;

public class PortalBlock extends Block {
    private static final VoxelShape SHAPE = Shapes.box(0, 0, 0, 1, .8, 1);

    public PortalBlock() {
        super(Properties.of(Material.METAL)
                .sound(SoundType.METAL)
                .strength(-1.0F, 3600000.0F)
                .noDrops());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    // This works because our block isn't a full block
    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof ServerPlayer player) {
            if (level.dimension().equals(DimensionInit.YGGDRASILS_INNER)) {
                teleportTo(player, pos.north(), Level.OVERWORLD);
            } else {
                teleportTo(player, pos.north(), DimensionInit.YGGDRASILS_INNER);
            }
        }
    }

    private void teleportTo(ServerPlayer player, BlockPos pos, ResourceKey<Level> id) {
        ServerLevel newWorld = player.getServer().getLevel(id);



        if(newWorld != null) {
            Tools.teleport(player, newWorld, new BlockPos(pos.getX(), pos.getY(), pos.getZ()), true);
        }

    }

}
