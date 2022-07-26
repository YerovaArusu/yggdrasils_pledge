package yerova.yggdrasilsplegde.core.entities.client;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;
import yerova.yggdrasilsplegde.core.entities.MariaArusuEntity;

public class MariaArusuModel extends AnimatedGeoModel<MariaArusuEntity> {
    @Override
    public ResourceLocation getModelLocation(MariaArusuEntity object) {
        return new ResourceLocation(YggdrasilsPlegde.MOD_ID, "geo/maria_arusu_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MariaArusuEntity object) {
        return new ResourceLocation(YggdrasilsPlegde.MOD_ID, "textures/entity/maria_arusu/maria_arusu.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MariaArusuEntity animatable) {
        return new ResourceLocation(YggdrasilsPlegde.MOD_ID, "animations/maria_arusu_animations.geo.json");
    }
}
