package geek.lawsof.physics.lib.fluid

import geek.lawsof.physics.lib.CreativeTabBase
import geek.lawsof.physics.lib.item.{ItemBase, ItemDescriptor}
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.MovingObjectPosition
import net.minecraft.world.World
import net.minecraftforge.fluids.{Fluid, FluidContainerRegistry, FluidRegistry, FluidStack}

/**
 * Created by anshuman on 17-05-2014.
 */
class FluidContainerBase(intName: String, var fluidVol: Int, ctab: CreativeTabBase) extends ItemBase() {
  this +: EmptyFluidContainerDescriptor
  registerFluid(FluidRegistry.WATER)
  registerFluid(FluidRegistry.LAVA)

  def registerFluid(fluid: Fluid) {
    val descript = new FluidContainerDescriptor(fluid).register(this)
    FluidContainerRegistry.registerFluidContainer(new FluidStack(fluid, fluidVol), newItemStack(descript.fluid.getID))
  }

  def canPlaceFluids = false

  def canPickupFluids = true

  override def onItemRightClick(itemStack: ItemStack, world: World, player: EntityPlayer): ItemStack = {
    if (itemStack.getItemDamage != 0 && !canPlaceFluids) {
      itemStack
    }
    //todo place fluid if canPlaceFluid(==true)

    val mop: MovingObjectPosition = this.getMovingObjectPositionFromPlayer(world, player, true)
    var container: ItemStack = fillContainer(world, mop)

    if (container == null) itemStack
    else player.inventory.addItemStackToInventory(container)
    new ItemStack(itemStack.getItem, itemStack.stackSize - 1)
  }

  def fillContainer(world: World, pos: MovingObjectPosition): ItemStack = {
    var block: Block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ)
    var fluid: Fluid = FluidRegistry.lookupFluidForBlock(block)

    var ID: Int = fluid.getID

    val x: Int = pos.blockX
    val y: Int = pos.blockY
    val z: Int = pos.blockZ

    if (!(items contains ID)) return null

    if (world.getBlockMetadata(x, y, z) == 0) {
      world.setBlockToAir(x, y, z)
      newItemStack(ID)
    }

    null
  }
}

object EmptyFluidContainerDescriptor extends ItemDescriptor("empty")

class FluidContainerDescriptor(val fluid: Fluid) extends ItemDescriptor(fluid.getName) {
  override def +:(reg: ItemBase) = this.register(reg.asInstanceOf[FluidContainerBase])

  def register(item: FluidContainerBase) = {
    item.items += (fluid.getID -> this)
    this.item = item
    this
  }
}