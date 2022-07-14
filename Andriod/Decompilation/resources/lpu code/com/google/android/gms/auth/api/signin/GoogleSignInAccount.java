package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInAccount
  extends com.google.android.gms.common.internal.safeparcel.zza
  implements ReflectedParcelable
{
  public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new zza();
  public static zze zzajZ = zzi.zzzc();
  private static Comparator<Scope> zzakg = new Comparator()
  {
    public int zza(Scope paramAnonymousScope1, Scope paramAnonymousScope2)
    {
      return paramAnonymousScope1.zzvt().compareTo(paramAnonymousScope2.zzvt());
    }
  };
  final int versionCode;
  private String zzGV;
  List<Scope> zzaiN;
  private String zzajB;
  private String zzajl;
  private String zzajm;
  private String zzaka;
  private String zzakb;
  private Uri zzakc;
  private String zzakd;
  private long zzake;
  private String zzakf;
  
  GoogleSignInAccount(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, Uri paramUri, String paramString5, long paramLong, String paramString6, List<Scope> paramList, String paramString7, String paramString8)
  {
    this.versionCode = paramInt;
    this.zzGV = paramString1;
    this.zzajB = paramString2;
    this.zzaka = paramString3;
    this.zzakb = paramString4;
    this.zzakc = paramUri;
    this.zzakd = paramString5;
    this.zzake = paramLong;
    this.zzakf = paramString6;
    this.zzaiN = paramList;
    this.zzajl = paramString7;
    this.zzajm = paramString8;
  }
  
  public static GoogleSignInAccount zza(@Nullable String paramString1, @Nullable String paramString2, @Nullable String paramString3, @Nullable String paramString4, @Nullable String paramString5, @Nullable String paramString6, @Nullable Uri paramUri, @Nullable Long paramLong, @NonNull String paramString7, @NonNull Set<Scope> paramSet)
  {
    Long localLong = paramLong;
    if (paramLong == null) {
      localLong = Long.valueOf(zzajZ.currentTimeMillis() / 1000L);
    }
    return new GoogleSignInAccount(3, paramString1, paramString2, paramString3, paramString4, paramUri, null, localLong.longValue(), zzac.zzdr(paramString7), new ArrayList((Collection)zzac.zzw(paramSet)), paramString5, paramString6);
  }
  
  @Nullable
  public static GoogleSignInAccount zzcv(@Nullable String paramString)
    throws JSONException
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    JSONObject localJSONObject = new JSONObject(paramString);
    paramString = localJSONObject.optString("photoUrl", null);
    if (!TextUtils.isEmpty(paramString)) {}
    for (paramString = Uri.parse(paramString);; paramString = null)
    {
      long l = Long.parseLong(localJSONObject.getString("expirationTime"));
      HashSet localHashSet = new HashSet();
      JSONArray localJSONArray = localJSONObject.getJSONArray("grantedScopes");
      int j = localJSONArray.length();
      int i = 0;
      while (i < j)
      {
        localHashSet.add(new Scope(localJSONArray.getString(i)));
        i += 1;
      }
      return zza(localJSONObject.optString("id"), localJSONObject.optString("tokenId", null), localJSONObject.optString("email", null), localJSONObject.optString("displayName", null), localJSONObject.optString("givenName", null), localJSONObject.optString("familyName", null), paramString, Long.valueOf(l), localJSONObject.getString("obfuscatedIdentifier"), localHashSet).zzcw(localJSONObject.optString("serverAuthCode", null));
    }
  }
  
  private JSONObject zzri()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      if (getId() != null) {
        localJSONObject.put("id", getId());
      }
      if (getIdToken() != null) {
        localJSONObject.put("tokenId", getIdToken());
      }
      if (getEmail() != null) {
        localJSONObject.put("email", getEmail());
      }
      if (getDisplayName() != null) {
        localJSONObject.put("displayName", getDisplayName());
      }
      if (getGivenName() != null) {
        localJSONObject.put("givenName", getGivenName());
      }
      if (getFamilyName() != null) {
        localJSONObject.put("familyName", getFamilyName());
      }
      if (getPhotoUrl() != null) {
        localJSONObject.put("photoUrl", getPhotoUrl().toString());
      }
      if (getServerAuthCode() != null) {
        localJSONObject.put("serverAuthCode", getServerAuthCode());
      }
      localJSONObject.put("expirationTime", this.zzake);
      localJSONObject.put("obfuscatedIdentifier", zzrf());
      JSONArray localJSONArray = new JSONArray();
      Collections.sort(this.zzaiN, zzakg);
      Iterator localIterator = this.zzaiN.iterator();
      while (localIterator.hasNext()) {
        localJSONArray.put(((Scope)localIterator.next()).zzvt());
      }
      localJSONException.put("grantedScopes", localJSONArray);
    }
    catch (JSONException localJSONException)
    {
      throw new RuntimeException(localJSONException);
    }
    return localJSONException;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof GoogleSignInAccount)) {
      return false;
    }
    return ((GoogleSignInAccount)paramObject).zzrg().equals(zzrg());
  }
  
  @Nullable
  public Account getAccount()
  {
    if (this.zzaka == null) {
      return null;
    }
    return new Account(this.zzaka, "com.google");
  }
  
  @Nullable
  public String getDisplayName()
  {
    return this.zzakb;
  }
  
  @Nullable
  public String getEmail()
  {
    return this.zzaka;
  }
  
  @Nullable
  public String getFamilyName()
  {
    return this.zzajm;
  }
  
  @Nullable
  public String getGivenName()
  {
    return this.zzajl;
  }
  
  @NonNull
  public Set<Scope> getGrantedScopes()
  {
    return new HashSet(this.zzaiN);
  }
  
  @Nullable
  public String getId()
  {
    return this.zzGV;
  }
  
  @Nullable
  public String getIdToken()
  {
    return this.zzajB;
  }
  
  @Nullable
  public Uri getPhotoUrl()
  {
    return this.zzakc;
  }
  
  @Nullable
  public String getServerAuthCode()
  {
    return this.zzakd;
  }
  
  public int hashCode()
  {
    return zzrg().hashCode();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public boolean zza()
  {
    return zzajZ.currentTimeMillis() / 1000L >= this.zzake - 300L;
  }
  
  public GoogleSignInAccount zzcw(String paramString)
  {
    this.zzakd = paramString;
    return this;
  }
  
  public long zzre()
  {
    return this.zzake;
  }
  
  @NonNull
  public String zzrf()
  {
    return this.zzakf;
  }
  
  public String zzrg()
  {
    return zzri().toString();
  }
  
  public String zzrh()
  {
    JSONObject localJSONObject = zzri();
    localJSONObject.remove("serverAuthCode");
    return localJSONObject.toString();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\auth\api\signin\GoogleSignInAccount.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */