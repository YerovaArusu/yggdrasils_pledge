package yerova.yggdrasilsplegde.core.items.client;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;
import yerova.yggdrasilsplegde.core.items.weapons.YggdZephyr;

public class YggdZephyrModel extends AnimatedGeoModel<YggdZephyr> {
    @Override
    public ResourceLocation getModelLocation(YggdZephyr object) {
        return new ResourceLocation(YggdrasilsPlegde.MOD_ID, "geo/yggd_zephyr.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(YggdZephyr object) {
        return new ResourceLocation(YggdrasilsPlegde.MOD_ID, "textures/items/yggd_zephyr.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(YggdZephyr animatable) {
        return new ResourceLocation(YggdrasilsPlegde.MOD_ID, "animations/yggd_zephyr_animations.json");
    }
}
