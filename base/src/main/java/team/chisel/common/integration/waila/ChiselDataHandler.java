package team.chisel.common.integration.waila;

import java.util.List;

import mcp.mobius.waila.api.IBlockAccessor;
import mcp.mobius.waila.api.IBlockComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.TooltipPosition;
import mcp.mobius.waila.api.WailaPlugin;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import team.chisel.Chisel;
import team.chisel.api.block.ICarvable;
import team.chisel.common.block.ItemChiselBlock;

@WailaPlugin(id = Chisel.MOD_ID)
public class ChiselDataHandler implements IWailaPlugin, IBlockComponentProvider {
    
    @Override
    public void register(IRegistrar registrar) {
        registrar.addComponent(this, TooltipPosition.BODY, ICarvable.class);
    }

    @Override
    public void appendBody(List<Component> tooltip, IBlockAccessor accessor, IPluginConfig config) {
        if (accessor.getBlock() instanceof ICarvable) {
            ItemStack stack = accessor.getStack();
            if (stack.getItem() instanceof ItemChiselBlock) {
                ((ItemChiselBlock)stack.getItem()).addTooltips(stack, tooltip);
            } else {
                ICarvable block = (ICarvable) accessor.getBlock();
                ItemChiselBlock.addTooltips(stack, block, tooltip);
            }
        }
    }
}
