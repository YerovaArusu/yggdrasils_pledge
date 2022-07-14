package yerova.yggdrasilsplegde.core.capabilities.reiryoku.data;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ReiryokuProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {


    public static Capability<Reiryoku> REIRYOKU = CapabilityManager.get(new CapabilityToken<>(){});
    private Reiryoku reiryoku = null;
    private final LazyOptional<Reiryoku> opt = LazyOptional.of(this::createReiryoku);

    @Nonnull
    private Reiryoku createReiryoku() {
        if (reiryoku == null) {
            reiryoku = new Reiryoku();
        }
        return reiryoku;
    }

    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == REIRYOKU) {
            return opt.cast();
        }
        return LazyOptional.empty();
    }
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return getCapability(cap);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createReiryoku().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createReiryoku().loadNBTData(nbt);
    }
}
