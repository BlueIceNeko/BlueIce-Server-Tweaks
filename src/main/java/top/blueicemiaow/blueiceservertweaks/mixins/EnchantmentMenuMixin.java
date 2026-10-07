package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EnchantingTableBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

import java.util.HashMap;
import java.util.List;

@Mixin(EnchantmentMenu.class)
public abstract class EnchantmentMenuMixin {
    @Unique
    private static final HashMap<BlockPos, Integer> bookCounts = new HashMap<>();

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
            int bookCount = 0;
            for (BlockPos offset : EnchantingTableBlock.BOOKSHELF_OFFSETS) {
                if (EnchantingTableBlock.isValidBookShelf(level, pos, offset)) {
                    BlockEntity bookshelf = level.getBlockEntity(pos.offset(offset));
                    if (bookshelf instanceof ChiseledBookShelfBlockEntity chiseledBookshelf) {
                        List<ItemStack> books = chiseledBookshelf.getItems();
                        for (ItemStack book : books) {
                            if (book.is(ItemTags.BOOKSHELF_BOOKS)) {
                                bookCount++;
                            }
                        }
                    } else {
                        bookCount += 3;
                    }
                }
            }
            bookCounts.put(pos, bookCount);
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
        return ConfigHelper.get(Config.chiseled_bookshelf_provide_enchantment_power) ? original.call(random, slot, bookCounts.get(pos) / 3, itemStack) : original.call(random, slot, bookcases, itemStack);
    }

    @Inject(method = "lambda$slotsChanged$0", at = @At("TAIL"))
    private void slotsChangedT(ItemStack itemStack, Level level, BlockPos pos, CallbackInfo ci) {
        if (ConfigHelper.get(Config.chiseled_bookshelf_provide_enchantment_power)) {
            bookCounts.remove(pos);
        }
    }
}
