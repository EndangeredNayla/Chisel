package team.chisel.common.world;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class ReplaceBlockDownwardsFeature extends Feature<ReplaceMultipleBlocksConfig> {

    public ReplaceBlockDownwardsFeature(Codec<ReplaceMultipleBlocksConfig> p_i51444_1_) {
        super(p_i51444_1_);
    }
    
    @Override
    public boolean place(FeaturePlaceContext<ReplaceMultipleBlocksConfig> ctx) {
        boolean ret = false;
        int max = 2;
        Random rand = ctx.random();
        if (rand.nextFloat() < 0.7f) {
            max++;
            if (rand.nextFloat() < 0.2f) {
                max++;
            }
        }
        ReplaceMultipleBlocksConfig config = ctx.config();
        WorldGenLevel world = ctx.level();
        BlockPos pos = ctx.origin();
        for (int i = 0; i < max; i++) {
            if (config.toReplace.contains(world.getBlockState(pos))) {
                world.setBlock(pos, config.result, 2);
                ret = true;
            }
            pos = pos.below();
        }
        return ret;
    }
}
