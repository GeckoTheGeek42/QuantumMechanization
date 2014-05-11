package qmech.helper.tileentity.syncable;

import net.minecraft.nbt.NBTTagCompound;

public class SyncableInt extends SyncableObjectBase {

	protected int value = 0;

	public SyncableInt(int value) {
		this.value = value;
	}

	public SyncableInt() {}

	/*
	@Override
	public void readFromStream(DataInput stream) throws IOException {
		value = stream.readInt();
	}
	*/

	public void modify(int by) {
		setValue(value + by);
	}

	public void setValue(int val) {
		if (val != value) {
			value = val;
			markDirty();
		}
	}

	public int getValue() {
		return value;
	}

	/*
	@Override
	public void writeToStream(DataOutput stream, boolean fullData) throws IOException {
		stream.writeInt(value);
	}
	*/

	@Override
	public void writeToNBT(NBTTagCompound tag, String name) {
		tag.setInteger(name, value);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag, String name) {
		if (tag.hasKey(name)) {
			value = tag.getInteger(name);
		}
	}

}
