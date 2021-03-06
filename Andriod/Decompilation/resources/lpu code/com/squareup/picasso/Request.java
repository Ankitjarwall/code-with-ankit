package com.squareup.picasso;

import android.graphics.Bitmap.Config;
import android.net.Uri;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class Request
{
  private static final long TOO_LONG_LOG = TimeUnit.SECONDS.toNanos(5L);
  public final boolean centerCrop;
  public final boolean centerInside;
  public final Bitmap.Config config;
  public final boolean hasRotationPivot;
  int id;
  int networkPolicy;
  public final boolean onlyScaleDown;
  public final Picasso.Priority priority;
  public final int resourceId;
  public final float rotationDegrees;
  public final float rotationPivotX;
  public final float rotationPivotY;
  public final String stableKey;
  long started;
  public final int targetHeight;
  public final int targetWidth;
  public final List<Transformation> transformations;
  public final Uri uri;
  
  private Request(Uri paramUri, int paramInt1, String paramString, List<Transformation> paramList, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean4, Bitmap.Config paramConfig, Picasso.Priority paramPriority)
  {
    this.uri = paramUri;
    this.resourceId = paramInt1;
    this.stableKey = paramString;
    if (paramList == null) {}
    for (this.transformations = null;; this.transformations = Collections.unmodifiableList(paramList))
    {
      this.targetWidth = paramInt2;
      this.targetHeight = paramInt3;
      this.centerCrop = paramBoolean1;
      this.centerInside = paramBoolean2;
      this.onlyScaleDown = paramBoolean3;
      this.rotationDegrees = paramFloat1;
      this.rotationPivotX = paramFloat2;
      this.rotationPivotY = paramFloat3;
      this.hasRotationPivot = paramBoolean4;
      this.config = paramConfig;
      this.priority = paramPriority;
      return;
    }
  }
  
  public Builder buildUpon()
  {
    return new Builder(this, null);
  }
  
  String getName()
  {
    if (this.uri != null) {
      return String.valueOf(this.uri.getPath());
    }
    return Integer.toHexString(this.resourceId);
  }
  
  boolean hasCustomTransformations()
  {
    return this.transformations != null;
  }
  
  public boolean hasSize()
  {
    return (this.targetWidth != 0) || (this.targetHeight != 0);
  }
  
  String logId()
  {
    long l = System.nanoTime() - this.started;
    if (l > TOO_LONG_LOG) {
      return plainId() + '+' + TimeUnit.NANOSECONDS.toSeconds(l) + 's';
    }
    return plainId() + '+' + TimeUnit.NANOSECONDS.toMillis(l) + "ms";
  }
  
  boolean needsMatrixTransform()
  {
    return (hasSize()) || (this.rotationDegrees != 0.0F);
  }
  
  boolean needsTransformation()
  {
    return (needsMatrixTransform()) || (hasCustomTransformations());
  }
  
  String plainId()
  {
    return "[R" + this.id + ']';
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Request{");
    if (this.resourceId > 0) {
      localStringBuilder.append(this.resourceId);
    }
    while ((this.transformations != null) && (!this.transformations.isEmpty()))
    {
      Iterator localIterator = this.transformations.iterator();
      while (localIterator.hasNext())
      {
        Transformation localTransformation = (Transformation)localIterator.next();
        localStringBuilder.append(' ').append(localTransformation.key());
      }
      localStringBuilder.append(this.uri);
    }
    if (this.stableKey != null) {
      localStringBuilder.append(" stableKey(").append(this.stableKey).append(')');
    }
    if (this.targetWidth > 0) {
      localStringBuilder.append(" resize(").append(this.targetWidth).append(',').append(this.targetHeight).append(')');
    }
    if (this.centerCrop) {
      localStringBuilder.append(" centerCrop");
    }
    if (this.centerInside) {
      localStringBuilder.append(" centerInside");
    }
    if (this.rotationDegrees != 0.0F)
    {
      localStringBuilder.append(" rotation(").append(this.rotationDegrees);
      if (this.hasRotationPivot) {
        localStringBuilder.append(" @ ").append(this.rotationPivotX).append(',').append(this.rotationPivotY);
      }
      localStringBuilder.append(')');
    }
    if (this.config != null) {
      localStringBuilder.append(' ').append(this.config);
    }
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
  
  public static final class Builder
  {
    private boolean centerCrop;
    private boolean centerInside;
    private Bitmap.Config config;
    private boolean hasRotationPivot;
    private boolean onlyScaleDown;
    private Picasso.Priority priority;
    private int resourceId;
    private float rotationDegrees;
    private float rotationPivotX;
    private float rotationPivotY;
    private String stableKey;
    private int targetHeight;
    private int targetWidth;
    private List<Transformation> transformations;
    private Uri uri;
    
    public Builder(int paramInt)
    {
      setResourceId(paramInt);
    }
    
    public Builder(Uri paramUri)
    {
      setUri(paramUri);
    }
    
    Builder(Uri paramUri, int paramInt, Bitmap.Config paramConfig)
    {
      this.uri = paramUri;
      this.resourceId = paramInt;
      this.config = paramConfig;
    }
    
    private Builder(Request paramRequest)
    {
      this.uri = paramRequest.uri;
      this.resourceId = paramRequest.resourceId;
      this.stableKey = paramRequest.stableKey;
      this.targetWidth = paramRequest.targetWidth;
      this.targetHeight = paramRequest.targetHeight;
      this.centerCrop = paramRequest.centerCrop;
      this.centerInside = paramRequest.centerInside;
      this.rotationDegrees = paramRequest.rotationDegrees;
      this.rotationPivotX = paramRequest.rotationPivotX;
      this.rotationPivotY = paramRequest.rotationPivotY;
      this.hasRotationPivot = paramRequest.hasRotationPivot;
      this.onlyScaleDown = paramRequest.onlyScaleDown;
      if (paramRequest.transformations != null) {
        this.transformations = new ArrayList(paramRequest.transformations);
      }
      this.config = paramRequest.config;
      this.priority = paramRequest.priority;
    }
    
    public Request build()
    {
      if ((this.centerInside) && (this.centerCrop)) {
        throw new IllegalStateException("Center crop and center inside can not be used together.");
      }
      if ((this.centerCrop) && (this.targetWidth == 0) && (this.targetHeight == 0)) {
        throw new IllegalStateException("Center crop requires calling resize with positive width and height.");
      }
      if ((this.centerInside) && (this.targetWidth == 0) && (this.targetHeight == 0)) {
        throw new IllegalStateException("Center inside requires calling resize with positive width and height.");
      }
      if (this.priority == null) {
        this.priority = Picasso.Priority.NORMAL;
      }
      return new Request(this.uri, this.resourceId, this.stableKey, this.transformations, this.targetWidth, this.targetHeight, this.centerCrop, this.centerInside, this.onlyScaleDown, this.rotationDegrees, this.rotationPivotX, this.rotationPivotY, this.hasRotationPivot, this.config, this.priority, null);
    }
    
    public Builder centerCrop()
    {
      if (this.centerInside) {
        throw new IllegalStateException("Center crop can not be used after calling centerInside");
      }
      this.centerCrop = true;
      return this;
    }
    
    public Builder centerInside()
    {
      if (this.centerCrop) {
        throw new IllegalStateException("Center inside can not be used after calling centerCrop");
      }
      this.centerInside = true;
      return this;
    }
    
    public Builder clearCenterCrop()
    {
      this.centerCrop = false;
      return this;
    }
    
    public Builder clearCenterInside()
    {
      this.centerInside = false;
      return this;
    }
    
    public Builder clearOnlyScaleDown()
    {
      this.onlyScaleDown = false;
      return this;
    }
    
    public Builder clearResize()
    {
      this.targetWidth = 0;
      this.targetHeight = 0;
      this.centerCrop = false;
      this.centerInside = false;
      return this;
    }
    
    public Builder clearRotation()
    {
      this.rotationDegrees = 0.0F;
      this.rotationPivotX = 0.0F;
      this.rotationPivotY = 0.0F;
      this.hasRotationPivot = false;
      return this;
    }
    
    public Builder config(Bitmap.Config paramConfig)
    {
      this.config = paramConfig;
      return this;
    }
    
    boolean hasImage()
    {
      return (this.uri != null) || (this.resourceId != 0);
    }
    
    boolean hasPriority()
    {
      return this.priority != null;
    }
    
    boolean hasSize()
    {
      return (this.targetWidth != 0) || (this.targetHeight != 0);
    }
    
    public Builder onlyScaleDown()
    {
      if ((this.targetHeight == 0) && (this.targetWidth == 0)) {
        throw new IllegalStateException("onlyScaleDown can not be applied without resize");
      }
      this.onlyScaleDown = true;
      return this;
    }
    
    public Builder priority(Picasso.Priority paramPriority)
    {
      if (paramPriority == null) {
        throw new IllegalArgumentException("Priority invalid.");
      }
      if (this.priority != null) {
        throw new IllegalStateException("Priority already set.");
      }
      this.priority = paramPriority;
      return this;
    }
    
    public Builder resize(int paramInt1, int paramInt2)
    {
      if (paramInt1 < 0) {
        throw new IllegalArgumentException("Width must be positive number or 0.");
      }
      if (paramInt2 < 0) {
        throw new IllegalArgumentException("Height must be positive number or 0.");
      }
      if ((paramInt2 == 0) && (paramInt1 == 0)) {
        throw new IllegalArgumentException("At least one dimension has to be positive number.");
      }
      this.targetWidth = paramInt1;
      this.targetHeight = paramInt2;
      return this;
    }
    
    public Builder rotate(float paramFloat)
    {
      this.rotationDegrees = paramFloat;
      return this;
    }
    
    public Builder rotate(float paramFloat1, float paramFloat2, float paramFloat3)
    {
      this.rotationDegrees = paramFloat1;
      this.rotationPivotX = paramFloat2;
      this.rotationPivotY = paramFloat3;
      this.hasRotationPivot = true;
      return this;
    }
    
    public Builder setResourceId(int paramInt)
    {
      if (paramInt == 0) {
        throw new IllegalArgumentException("Image resource ID may not be 0.");
      }
      this.resourceId = paramInt;
      this.uri = null;
      return this;
    }
    
    public Builder setUri(Uri paramUri)
    {
      if (paramUri == null) {
        throw new IllegalArgumentException("Image URI may not be null.");
      }
      this.uri = paramUri;
      this.resourceId = 0;
      return this;
    }
    
    public Builder stableKey(String paramString)
    {
      this.stableKey = paramString;
      return this;
    }
    
    public Builder transform(Transformation paramTransformation)
    {
      if (paramTransformation == null) {
        throw new IllegalArgumentException("Transformation must not be null.");
      }
      if (paramTransformation.key() == null) {
        throw new IllegalArgumentException("Transformation key must not be null.");
      }
      if (this.transformations == null) {
        this.transformations = new ArrayList(2);
      }
      this.transformations.add(paramTransformation);
      return this;
    }
    
    public Builder transform(List<? extends Transformation> paramList)
    {
      if (paramList == null) {
        throw new IllegalArgumentException("Transformation list must not be null.");
      }
      int i = 0;
      int j = paramList.size();
      while (i < j)
      {
        transform((Transformation)paramList.get(i));
        i += 1;
      }
      return this;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\squareup\picasso\Request.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */