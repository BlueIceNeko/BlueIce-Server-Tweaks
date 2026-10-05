package top.blueicemiaow.blueiceservertweaks.gameplay;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EndPortalFrameBlock;
import net.minecraft.world.level.block.state.BlockState;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.api.LoggerWrapper;

public class EndPortal {
    public static final LoggerWrapper DEBUG = new LoggerWrapper(BlueIceServerTweaks.LOGGER, "EnhancedEndPortalDebug");

    public static void log(String log) {
        DEBUG.debug(log);
    }

    public static Direction[] directions(Direction direction) {
        return switch (direction.ordinal()) {
            case 2 -> new Direction[]{Direction.NORTH, Direction.EAST, Direction.WEST, Direction.SOUTH};
            case 3 -> new Direction[]{Direction.SOUTH, Direction.WEST, Direction.EAST, Direction.NORTH};
            case 4 -> new Direction[]{Direction.WEST, Direction.SOUTH, Direction.NORTH, Direction.EAST};
            case 5 -> new Direction[]{Direction.EAST, Direction.NORTH, Direction.SOUTH, Direction.WEST};
            default -> null;
        };
    }

    public static int xOffset(Direction direction) {
        return switch (direction.ordinal()) {
            case 2 -> -1;
            case 3 -> 1;
            default -> 0;
        };
    }

    public static int zOffset(Direction direction) {
        return switch (direction.ordinal()) {
            case 4 -> -1;
            case 5 -> 1;
            default -> 0;
        };
    }

    public static boolean check(BlockState frame, Direction direction) {
        return frame.is(Blocks.END_PORTAL_FRAME) && frame.getValue(EndPortalFrameBlock.HAS_EYE) && frame.getValue(EndPortalFrameBlock.FACING) == direction;
    }
}
