package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public final class WebImage
  extends zza
{
  public static final Parcelable.Creator<WebImage> CREATOR = new zzb();
  final int zzaiI;
  private final Uri zzata;
  private final int zzrC;
  private final int zzrD;
  
  WebImage(int paramInt1, Uri paramUri, int paramInt2, int paramInt3)
  {
    this.zzaiI = paramInt1;
    this.zzata = paramUri;
    this.zzrC = paramInt2;
    this.zzrD = paramInt3;
  }
  
  public WebImage(Uri paramUri)
    throws IllegalArgumentException
  {
    this(paramUri, 0, 0);
  }
  
  public WebImage(Uri paramUri, int paramInt1, int paramInt2)
    throws IllegalArgumentException
  {
    this(1, paramUri, paramInt1, paramInt2);
    if (paramUri == null) {
      throw new IllegalArgumentException("url cannot be null");
    }
    if ((paramInt1 < 0) || (paramInt2 < 0)) {
      throw new IllegalArgumentException("width and height must not be negative");
    }
  }
  
  public WebImage(JSONObject paramJSONObject)
    throws IllegalArgumentException
  {
    this(zzs(paramJSONObject), paramJSONObject.optInt("width", 0), paramJSONObject.optInt("height", 0));
  }
  
  private static Uri zzs(JSONObject paramJSONObject)
  {
    Uri localUri = null;
    if (paramJSONObject.has("url")) {}
    try
    {
      localUri = Uri.parse(paramJSONObject.getString("url"));
      return localUri;
    }
    catch (JSONException paramJSONObject) {}
    return null;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof WebImage))) {
        return false;
      }
      paramObject = (WebImage)paramObject;
    } while ((zzaa.equal(this.zzata, ((WebImage)paramObject).zzata)) && (this.zzrC == ((WebImage)paramObject).zzrC) && (this.zzrD == ((WebImage)paramObject).zzrD));
    return false;
  }
  
  public int getHeight()
  {
    return this.zzrD;
  }
  
  public Uri getUrl()
  {
    return this.zzata;
  }
  
  public int getWidth()
  {
    return this.zzrC;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { this.zzata, Integer.valueOf(this.zzrC), Integer.valueOf(this.zzrD) });
  }
  
  public JSONObject toJson()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("url", this.zzata.toString());
      localJSONObject.put("width", this.zzrC);
      localJSONObject.put("height", this.zzrD);
      return localJSONObject;
    }
    catch (JSONException localJSONException) {}
    return localJSONObject;
  }
  
  public String toString()
  {
    return String.format(Locale.US, "Image %dx%d %s", new Object[] { Integer.valueOf(this.zzrC), Integer.valueOf(this.zzrD), this.zzata.toString() });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\images\WebImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */