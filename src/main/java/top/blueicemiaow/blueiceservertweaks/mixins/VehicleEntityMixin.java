package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(VehicleEntity.class)
public abstract class VehicleEntityMixin {
    @Unique
    private static ItemStack dropMinecart(VehicleEntity instance, ServerLevel level, Item item) {
        instance.spawnAtLocation(level, new ItemStack(Items.MINECART));
        ItemStack itemStack = new ItemStack(item);
        itemStack.set(DataComponents.CUSTOM_NAME, instance.getCustomName());
        return itemStack;
    }

    @WrapOperation(
            method = "destroy(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/Item;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/vehicle/VehicleEntity;spawnAtLocation(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/entity/item/ItemEntity;"
            )
    )
    private ItemEntity destroy(VehicleEntity instance, ServerLevel level, ItemStack itemStack, Operation<ItemEntity> original) {
        if (ConfigHelper.get(Config.minecart_drop_fix)) {
            if (itemStack.is(Items.CHEST_MINECART)) {
                return original.call(instance, level, dropMinecart(instance, level, Items.CHEST));
            }
            if (itemStack.is(Items.FURNACE_MINECART)) {
                return original.call(instance, level, dropMinecart(instance, level, Items.FURNACE));
            }
            if (itemStack.is(Items.HOPPER_MINECART)) {
                return original.call(instance, level, dropMinecart(instance, level, Items.HOPPER));
            }
            if (itemStack.is(Items.TNT_MINECART)) {
                return original.call(instance, level, dropMinecart(instance, level, Items.TNT));
            }
            if (itemStack.is(Items.COMMAND_BLOCK_MINECART)) {
                return original.call(instance, level, dropMinecart(instance, level, Items.COMMAND_BLOCK));
            }
        }
        return original.call(instance, level, itemStack);
    }
}
