package qmech.lib.objects.meta

import net.minecraft.item.{ItemStack, Item}
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.util.IIcon
import cpw.mods.fml.relauncher.{SideOnly, Side}
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World
import net.minecraft.client.renderer.texture.IIconRegister
import scala.collection.mutable
import java.util

/**
 * Created by anshuman on 26-05-2014.
 */
abstract class MetaItemBase(intName: String) extends Item {
  setHasSubtypes(true)
  setMaxDamage(0)
  this.setUnlocalizedName(intName)
  GameRegistry.registerItem(this, intName)

  def registerItem(id: Int, item: IMetaSubItem) = metaitems.put(id, item)

  override def registerIcons(register: IIconRegister) {
    for (item <- metaitems.values) {
      item.registerIcons(register, intName)
    }
  }

  override def getIconFromDamage(i: Int): IIcon = {
    val meta: IMetaSubItem = getMeta(i)
    if (meta != null) {
      return meta.getIcon
    }
    null
  }

  override def getUnlocalizedName(stack: ItemStack): String = {
    val meta: IMetaSubItem = getMeta(stack.getItemDamage)
    if (meta != null) {
      return s"item.${intName}_${meta.getUnlocalizedName(stack)}"
    }
    ""
  }

  override def onItemUse(itemStack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z: Int, side: Int, par8: Float, par9: Float, par10: Float): Boolean = {
    val meta: IMetaSubItem = getMeta(itemStack.getItemDamage)
    if (meta != null) {
      return meta.onItemUse(itemStack, player, world, x, y, z, side, par8, par9, par10)
    }
    true
  }

  override def onItemRightClick(itemStack: ItemStack, world: World, player: EntityPlayer): ItemStack = {
    val meta: IMetaSubItem = getMeta(itemStack.getItemDamage)
    if (meta != null) {
      return meta.onItemRightClick(itemStack, player, world)
    }
    itemStack
  }

  override def hitEntity(itemStack: ItemStack, target: EntityLivingBase, player: EntityLivingBase): Boolean = {
    val meta: IMetaSubItem = getMeta(itemStack.getItemDamage)
    if (meta != null) {
      return meta.hitEntity(itemStack, target, player)
    }
    true
  }

  @SideOnly(Side.CLIENT) override def hasEffect(itemStack: ItemStack, pass: Int): Boolean = {
    val meta: IMetaSubItem = getMeta(itemStack.getItemDamage)
    if (meta != null) meta.hasEffect(pass) else false
  }

  @SideOnly(Side.CLIENT)
  override def getSubItems(item: Item, tab: CreativeTabs, subItems: util.List[_]) {
    import scala.collection.JavaConversions._
    for (entry <- metaitems.entrySet) entry.getValue.addToCreativeList(item, entry.getKey, subItems.asInstanceOf[util.List[ItemStack]])
  }

  def getMeta(id: Int): IMetaSubItem = {
    metaitems.get(id).get
  }

  def getMeta(itemStack: ItemStack): IMetaSubItem = {
    getMeta(itemStack.getItemDamage)
  }

  def newItemStack(id: Int): ItemStack = {
    newItemStack(id, 1)
  }

  def newItemStack(id: Int, number: Int): ItemStack = {
    new ItemStack(this, number, id)
  }

  def newItemStack(meta: IMetaSubItem, size: Int): ItemStack = {
    import scala.collection.JavaConversions._
    for (o <- metaitems.entrySet) {
      if (o.getValue == meta) {
        return newItemStack(o.getKey, size)
      }
    }
    null
  }

  protected var metaitems: mutable.HashMap[Integer, IMetaSubItem] = new mutable.HashMap[Integer, IMetaSubItem]
  var prefix: String = null
}
