package qmech.lib.objects.meta;

public enum EnumBlocksBase {
	Generic;

	/*
	public ItemStack newItemStack(MetaItemBase base, int size) {
		return new ItemStack(base, size, ordinal());
	}

	public ItemStack newItemStack(MetaItemBase base) {
		return new ItemStack(base, 1, ordinal());
	}

	public boolean isA(ItemStack stack) {
		return (stack.getItem() instanceof MetaItemBase) && (stack.getItemDamage() == ordinal());
	}

	protected MetaItemBase.MetaItem createMetaItem() {
		return new MetaItemBase.MetaItem(this.intName());
	}

	protected boolean isEnabled() {
		return true;
	}
	*/

	public static void registerItems(MetaBlockBase base) {
		for (EnumBlocksBase block : EnumBlocksBase.values()) {
			base.addSubBlock(block.name());
		}
	}
}