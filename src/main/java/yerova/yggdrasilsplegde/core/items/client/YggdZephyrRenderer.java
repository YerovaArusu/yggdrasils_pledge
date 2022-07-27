package yerova.yggdrasilsplegde.core.items.client;

import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;
import yerova.yggdrasilsplegde.core.items.weapons.YggdZephyr;

public class YggdZephyrRenderer extends GeoItemRenderer<YggdZephyr> {
    public YggdZephyrRenderer() {
        super(new YggdZephyrModel());
    }
}
