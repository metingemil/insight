package javaInsight.interfaces;

public class CustomObject implements ICustomObject {
	private int mId;

	public CustomObject(int id)
	{
		mId = id;
	}
	
	@Override
	public int getId() {
		return mId;
	}
}
