package rajawali.materials.textures;

import java.nio.ByteBuffer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public abstract class AMultiTexture extends ATexture {
	protected Bitmap[] mBitmaps;
	protected ByteBuffer[] mByteBuffers;
	protected int[] mResourceIds;

	protected AMultiTexture() {
		super();
	}

	public AMultiTexture(TextureType textureType, String textureName) {
		super(textureType, textureName);
	}
	
	public AMultiTexture(TextureType textureType, String textureName, int[] resourceIds)
	{
		super(textureType, textureName);
		setResourceIds(resourceIds);
	}
	
	public AMultiTexture(TextureType textureType, String textureName, Bitmap[] bitmaps)
	{
		super(textureType, textureName);
		setBitmaps(bitmaps);
	}

	public AMultiTexture(TextureType textureType, String textureName, ByteBuffer[] byteBuffers)
	{
		super(textureType, textureName);
		setByteBuffers(byteBuffers);
	}

	public AMultiTexture(ATexture other) {
		super(other);
	}
	
	/**
	 * Copies every property from another AMultiTexture object
	 * 
	 * @param other
	 *            another AMultiTexture object to copy from
	 */
	public void setFrom(AMultiTexture other)
	{
		super.setFrom(other);
		setBitmaps(mBitmaps);
		setResourceIds(mResourceIds);
		setByteBuffers(mByteBuffers);
	}
	
	public void setResourceIds(int[] resourceIds)
	{
		mResourceIds = resourceIds;
		int numResources = resourceIds.length;
		mBitmaps = new Bitmap[numResources];
		Context context = TextureManager.getInstance().getContext();

		for(int i=0; i<numResources; i++)
		{
			mBitmaps[i] = BitmapFactory.decodeResource(context.getResources(), resourceIds[i]); 
		}
	}
	
	public int[] getResourceIds()
	{
		return mResourceIds;
	}
	
	public void setBitmaps(Bitmap[] bitmaps)
	{
		mBitmaps = bitmaps;
	}
	
	public Bitmap[] getBitmaps()
	{
		return mBitmaps;
	}
	
	public void setByteBuffers(ByteBuffer[] byteBuffers)
	{
		mByteBuffers = byteBuffers;
	}
	
	public ByteBuffer[] getByteBuffers()
	{
		return mByteBuffers;
	}
	
	void reset() throws TextureException
	{
		if(mBitmaps != null)
		{
			int count = mBitmaps.length;
			for(int i=0; i<count; i++)
			{
				Bitmap bitmap = mBitmaps[i];
				bitmap.recycle();
				bitmap = null;
				mBitmaps[i] = null;
			}
		}
		if(mByteBuffers != null)
		{
			int count = mByteBuffers.length;
			for(int i=0; i<count; i++)
			{
				ByteBuffer byteBuffer = mByteBuffers[i];
				byteBuffer.clear();
				byteBuffer = null;
				mByteBuffers[i] = null;
			}
		}
	}
}
