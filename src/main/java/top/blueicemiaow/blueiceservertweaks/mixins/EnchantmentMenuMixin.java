package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChiseledBookShelfBlock;
import net.minecraft.world.level.block.EnchantingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(EnchantmentMenu.class)
public abstract class EnchantmentMenuMixin {
    @WrapOperation(
            method = "lambda$slotsChanged$0",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/EnchantingTableBlock;isValidBookShelf(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;)Z"
            ),
            slice = @Slice(
                    from = @At("HEAD"),
                    to = @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/world/inventory/DataSlot;get()I"
                    )
            )
    )
    private boolean slotsChanged(Level level, BlockPos pos, BlockPos offset, Operation<Boolean> original) {
        return !ConfigHelper.get(Config.chiseled_bookshelf_provide_enchantment_power) && original.call(level, pos, offset);
    }

    @Inject(
            method = "lambda$slotsChanged$0",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/RandomSource;setSeed(J)V"
            )
    )
    private void slotsChangedI(ItemStack itemStack, Level level, BlockPos pos, CallbackInfo ci) {
        if (ConfigHelper.get(Config.chiseled_bookshelf_provide_enchantment_power)) {
            int bookshelfBooks = 0;
            for (BlockPos offset : EnchantingTableBlock.BOOKSHELF_OFFSETS) {
                if (EnchantingTableBlock.isValidBookShelf(level, pos, offset)) {
                    BlockState bookshelf = level.getBlockState(pos.offset(offset));
                    if (bookshelf.is(Blocks.CHISELED_BOOKSHELF)) {
                        if (bookshelf.getValue(ChiseledBookShelfBlock.SLOT_0_OCCUPIED)) {
                            bookshelfBooks += 1;
                        }
                        if (bookshelf.getValue(ChiseledBookShelfBlock.SLOT_1_OCCUPIED)) {
                            bookshelfBooks += 1;
                        }
                        if (bookshelf.getValue(ChiseledBookShelfBlock.SLOT_2_OCCUPIED)) {
                            bookshelfBooks += 1;
                        }
                        if (bookshelf.getValue(ChiseledBookShelfBlock.SLOT_3_OCCUPIED)) {
                            bookshelfBooks += 1;
                        }
                        if (bookshelf.getValue(ChiseledBookShelfBlock.SLOT_4_OCCUPIED)) {
                            bookshelfBooks += 1;
                        }
                        if (bookshelf.getValue(ChiseledBookShelfBlock.SLOT_5_OCCUPIED)) {
                            bookshelfBooks += 1;
                        }
                    } else {
                        bookshelfBooks += 3;
                    }
                }
            }
            BlueIceServerTweaks.bookshelfBooks.put(pos, bookshelfBooks);
        }
    }

    @WrapOperation(
            method = "lambda$slotsChanged$0",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getEnchantmentCost(Lnet/minecraft/util/RandomSource;IILnet/minecraft/world/item/ItemStack;)I"
            )
    )
    private int slotsChanged(RandomSource random, int slot, int bookcases, ItemStack itemStack, Operation<Integer> original, @Local(name = "pos", argsOnly = true) BlockPos pos) {
        return ConfigHelper.get(Config.chiseled_bookshelf_provide_enchantment_power) ? original.call(random, slot, BlueIceServerTweaks.bookshelfBooks.get(pos) / 3, itemStack) : original.call(random, slot, bookcases, itemStack);
    }

    @Inject(method = "lambda$slotsChanged$0", at = @At("TAIL"))
    private void slotsChangedT(ItemStack itemStack, Level level, BlockPos pos, CallbackInfo ci) {
        if (ConfigHelper.get(Config.chiseled_bookshelf_provide_enchantment_power)) {
            BlueIceServerTweaks.bookshelfBooks.remove(pos);
        }
    }
}
