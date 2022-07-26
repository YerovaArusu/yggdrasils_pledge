package yerova.yggdrasilsplegde.core.entities;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITIES, YggdrasilsPlegde.MOD_ID);

    public static final RegistryObject<EntityType<MariaArusuEntity>> MARIA_ARUSU = ENTITY.register("maria_arusu",
            () -> EntityType.Builder.of(MariaArusuEntity::new, MobCategory.CREATURE)
                    .sized(1f, 2f)
                    .build(new ResourceLocation(YggdrasilsPlegde.MOD_ID, "maria_arusu").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY.register(eventBus);
    }
}
