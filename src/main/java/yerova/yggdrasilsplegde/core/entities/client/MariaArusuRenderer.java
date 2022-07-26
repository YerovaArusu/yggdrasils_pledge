package yerova.yggdrasilsplegde.core.entities.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;
import yerova.yggdrasilsplegde.core.entities.MariaArusuEntity;

public class MariaArusuRenderer extends GeoEntityRenderer<MariaArusuEntity> {
    public MariaArusuRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MariaArusuModel());

        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(MariaArusuEntity instance) {
        return new ResourceLocation(YggdrasilsPlegde.MOD_ID, "textures/entity/maria_arusu/maria_arusu.png");
    }

    @Override
    public RenderType getRenderType(MariaArusuEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1F, 1F, 1F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
