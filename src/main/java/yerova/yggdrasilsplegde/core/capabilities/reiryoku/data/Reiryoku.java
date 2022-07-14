package yerova.yggdrasilsplegde.core.capabilities.reiryoku.data;

import net.minecraft.nbt.CompoundTag;

import java.util.Random;


public class Reiryoku {

    Random random = new Random();
    private int reiryoku;
    private int maxReiryoku = random.nextInt((4000 - 400) +1) + 400;

    public int getReiryoku() {
        return reiryoku;
    }

    public void setReiryoku(int reiryoku) {
        this.reiryoku = reiryoku;
    }

    public int getMaxReiryoku() {
        return maxReiryoku;
    }

    public void setMaxReiryoku(int maxReiryoku) {
        this.maxReiryoku = maxReiryoku;
    }

    public void decreaseReiryoku(int toReduce) {
        reiryoku -= toReduce;
    }

    public void increaseReiryoku(int toIncrease) {
        reiryoku += toIncrease;
    }

    public void copyFrom(Reiryoku source) {
        reiryoku = source.reiryoku;
        maxReiryoku = source.maxReiryoku;
    }


    public void saveNBTData(CompoundTag compound) {
        compound.putInt("current", reiryoku);
        compound.putInt("max", maxReiryoku);
    }

    public void loadNBTData(CompoundTag compound) {
        reiryoku = compound.getInt("current");
        maxReiryoku = compound.getInt("max");
    }

}
