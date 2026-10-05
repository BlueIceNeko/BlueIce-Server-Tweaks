package top.blueicemiaow.blueiceservertweaks.mixins;

import net.minecraft.world.entity.ai.gossip.GossipType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GossipType.class)
public interface GossipTypeMixin {
    @Accessor("max")
    @Mutable
    void setMax(int max);

    @Accessor("decayPerTransfer")
    @Mutable
    void setDecayPerTransfer(int decayPerTransfer);
}
