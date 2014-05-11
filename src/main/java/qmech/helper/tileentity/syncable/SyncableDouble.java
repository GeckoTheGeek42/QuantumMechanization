package qmech.helper.tileentity.syncable;

import net.minecraft.nbt.NBTTagCompound;

public class SyncableDouble extends SyncableObjectBase {

	private double value;

	public SyncableDouble(double value) {
		this.value = value;
	}

	public SyncableDouble() {}

	public void setValue(double newValue) {
		if (newValue != value) {
			value = newValue;
			markDirty();
		}
	}

	public double getValue() {
		return value;
	}

	/*
	@Override
	public void readFromStream(DataInput stream) throws IOException {
		value = stream.readDouble();
	}

	@Override
	public void writeToStream(DataOutput stream, boolean fullData) throws IOException {
		stream.writeDouble(value);
	}
	*/

	@Override
	public void writeToNBT(NBTTagCompound tag, String name) {
		tag.setDouble(name, value);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag, String name) {
		value = tag.getDouble(name);
	}

	public void modify(float by) {
		setValue(value + by);
	}
}
