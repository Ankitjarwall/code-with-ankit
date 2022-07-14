package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.zzg;
import com.google.android.gms.auth.api.signin.internal.zzh;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInOptions
  extends zza
  implements Api.ApiOptions.Optional, ReflectedParcelable
{
  public static final Parcelable.Creator<GoogleSignInOptions> CREATOR = new zzb();
  public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN;
  public static final GoogleSignInOptions DEFAULT_SIGN_IN;
  public static final Scope SCOPE_GAMES;
  private static Comparator<Scope> zzakg = new Comparator()
  {
    public int zza(Scope paramAnonymousScope1, Scope paramAnonymousScope2)
    {
      return paramAnonymousScope1.zzvt().compareTo(paramAnonymousScope2.zzvt());
    }
  };
  public static final Scope zzakh = new Scope("profile");
  public static final Scope zzaki = new Scope("email");
  public static final Scope zzakj = new Scope("openid");
  final int versionCode;
  private Account zzahh;
  private boolean zzajv;
  private String zzajw;
  private final ArrayList<Scope> zzakk;
  private final boolean zzakl;
  private final boolean zzakm;
  private String zzakn;
  private ArrayList<zzg> zzako;
  private Map<Integer, zzg> zzakp;
  
  static
  {
    SCOPE_GAMES = new Scope("https://www.googleapis.com/auth/games");
    DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
    DEFAULT_GAMES_SIGN_IN = new Builder().requestScopes(SCOPE_GAMES, new Scope[0]).build();
  }
  
  GoogleSignInOptions(int paramInt, ArrayList<Scope> paramArrayList, Account paramAccount, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString1, String paramString2, ArrayList<zzg> paramArrayList1)
  {
    this(paramInt, paramArrayList, paramAccount, paramBoolean1, paramBoolean2, paramBoolean3, paramString1, paramString2, zzx(paramArrayList1));
  }
  
  private GoogleSignInOptions(int paramInt, ArrayList<Scope> paramArrayList, Account paramAccount, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString1, String paramString2, Map<Integer, zzg> paramMap)
  {
    this.versionCode = paramInt;
    this.zzakk = paramArrayList;
    this.zzahh = paramAccount;
    this.zzajv = paramBoolean1;
    this.zzakl = paramBoolean2;
    this.zzakm = paramBoolean3;
    this.zzajw = paramString1;
    this.zzakn = paramString2;
    this.zzako = new ArrayList(paramMap.values());
    this.zzakp = paramMap;
  }
  
  @Nullable
  public static GoogleSignInOptions zzcx(@Nullable String paramString)
    throws JSONException
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    JSONObject localJSONObject = new JSONObject(paramString);
    HashSet localHashSet = new HashSet();
    paramString = localJSONObject.getJSONArray("scopes");
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      localHashSet.add(new Scope(paramString.getString(i)));
      i += 1;
    }
    paramString = localJSONObject.optString("accountName", null);
    if (!TextUtils.isEmpty(paramString)) {}
    for (paramString = new Account(paramString, "com.google");; paramString = null) {
      return new GoogleSignInOptions(3, new ArrayList(localHashSet), paramString, localJSONObject.getBoolean("idTokenRequested"), localJSONObject.getBoolean("serverAuthRequested"), localJSONObject.getBoolean("forceCodeForRefreshToken"), localJSONObject.optString("serverClientId", null), localJSONObject.optString("hostedDomain", null), new HashMap());
    }
  }
  
  private JSONObject zzri()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      JSONArray localJSONArray = new JSONArray();
      Collections.sort(this.zzakk, zzakg);
      Iterator localIterator = this.zzakk.iterator();
      while (localIterator.hasNext()) {
        localJSONArray.put(((Scope)localIterator.next()).zzvt());
      }
      localJSONException.put("scopes", localJSONArray);
    }
    catch (JSONException localJSONException)
    {
      throw new RuntimeException(localJSONException);
    }
    if (this.zzahh != null) {
      localJSONException.put("accountName", this.zzahh.name);
    }
    localJSONException.put("idTokenRequested", this.zzajv);
    localJSONException.put("forceCodeForRefreshToken", this.zzakm);
    localJSONException.put("serverAuthRequested", this.zzakl);
    if (!TextUtils.isEmpty(this.zzajw)) {
      localJSONException.put("serverClientId", this.zzajw);
    }
    if (!TextUtils.isEmpty(this.zzakn)) {
      localJSONException.put("hostedDomain", this.zzakn);
    }
    return localJSONException;
  }
  
  private static Map<Integer, zzg> zzx(@Nullable List<zzg> paramList)
  {
    HashMap localHashMap = new HashMap();
    if (paramList == null) {
      return localHashMap;
    }
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      zzg localzzg = (zzg)paramList.next();
      localHashMap.put(Integer.valueOf(localzzg.getType()), localzzg);
    }
    return localHashMap;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    for (;;)
    {
      return false;
      try
      {
        paramObject = (GoogleSignInOptions)paramObject;
        if ((this.zzako.size() > 0) || (((GoogleSignInOptions)paramObject).zzako.size() > 0) || (this.zzakk.size() != ((GoogleSignInOptions)paramObject).zzrj().size()) || (!this.zzakk.containsAll(((GoogleSignInOptions)paramObject).zzrj()))) {
          continue;
        }
        if (this.zzahh == null)
        {
          if (((GoogleSignInOptions)paramObject).getAccount() != null) {
            continue;
          }
          label76:
          if (!TextUtils.isEmpty(this.zzajw)) {
            break label148;
          }
          if (!TextUtils.isEmpty(((GoogleSignInOptions)paramObject).getServerClientId())) {
            continue;
          }
        }
        while ((this.zzakm == ((GoogleSignInOptions)paramObject).zzrl()) && (this.zzajv == ((GoogleSignInOptions)paramObject).isIdTokenRequested()) && (this.zzakl == ((GoogleSignInOptions)paramObject).zzrk()))
        {
          return true;
          if (!this.zzahh.equals(((GoogleSignInOptions)paramObject).getAccount())) {
            break;
          }
          break label76;
          label148:
          boolean bool = this.zzajw.equals(((GoogleSignInOptions)paramObject).getServerClientId());
          if (!bool) {
            break;
          }
        }
        return false;
      }
      catch (ClassCastException paramObject) {}
    }
  }
  
  public Account getAccount()
  {
    return this.zzahh;
  }
  
  public Scope[] getScopeArray()
  {
    return (Scope[])this.zzakk.toArray(new Scope[this.zzakk.size()]);
  }
  
  public String getServerClientId()
  {
    return this.zzajw;
  }
  
  public int hashCode()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.zzakk.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((Scope)localIterator.next()).zzvt());
    }
    Collections.sort(localArrayList);
    return new zzh().zzq(localArrayList).zzq(this.zzahh).zzq(this.zzajw).zzad(this.zzakm).zzad(this.zzajv).zzad(this.zzakl).zzru();
  }
  
  public boolean isIdTokenRequested()
  {
    return this.zzajv;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
  
  public String zzrg()
  {
    return zzri().toString();
  }
  
  public ArrayList<Scope> zzrj()
  {
    return new ArrayList(this.zzakk);
  }
  
  public boolean zzrk()
  {
    return this.zzakl;
  }
  
  public boolean zzrl()
  {
    return this.zzakm;
  }
  
  public String zzrm()
  {
    return this.zzakn;
  }
  
  public ArrayList<zzg> zzrn()
  {
    return this.zzako;
  }
  
  public static final class Builder
  {
    private Account zzahh;
    private boolean zzajv;
    private String zzajw;
    private boolean zzakl;
    private boolean zzakm;
    private String zzakn;
    private Set<Scope> zzakq = new HashSet();
    private Map<Integer, zzg> zzakr = new HashMap();
    
    public Builder() {}
    
    public Builder(@NonNull GoogleSignInOptions paramGoogleSignInOptions)
    {
      zzac.zzw(paramGoogleSignInOptions);
      this.zzakq = new HashSet(GoogleSignInOptions.zzb(paramGoogleSignInOptions));
      this.zzakl = GoogleSignInOptions.zzc(paramGoogleSignInOptions);
      this.zzakm = GoogleSignInOptions.zzd(paramGoogleSignInOptions);
      this.zzajv = GoogleSignInOptions.zze(paramGoogleSignInOptions);
      this.zzajw = GoogleSignInOptions.zzf(paramGoogleSignInOptions);
      this.zzahh = GoogleSignInOptions.zzg(paramGoogleSignInOptions);
      this.zzakn = GoogleSignInOptions.zzh(paramGoogleSignInOptions);
      this.zzakr = GoogleSignInOptions.zzy(GoogleSignInOptions.zzi(paramGoogleSignInOptions));
    }
    
    private String zzcy(String paramString)
    {
      zzac.zzdr(paramString);
      if ((this.zzajw == null) || (this.zzajw.equals(paramString))) {}
      for (boolean bool = true;; bool = false)
      {
        zzac.zzb(bool, "two different server client ids provided");
        return paramString;
      }
    }
    
    public Builder addExtension(GoogleSignInOptionsExtension paramGoogleSignInOptionsExtension)
    {
      if (this.zzakr.containsKey(Integer.valueOf(1))) {
        throw new IllegalStateException("Only one extension per type may be added");
      }
      this.zzakr.put(Integer.valueOf(1), new zzg(paramGoogleSignInOptionsExtension));
      return this;
    }
    
    public GoogleSignInOptions build()
    {
      if ((this.zzajv) && ((this.zzahh == null) || (!this.zzakq.isEmpty()))) {
        requestId();
      }
      return new GoogleSignInOptions(3, new ArrayList(this.zzakq), this.zzahh, this.zzajv, this.zzakl, this.zzakm, this.zzajw, this.zzakn, this.zzakr, null);
    }
    
    public Builder requestEmail()
    {
      this.zzakq.add(GoogleSignInOptions.zzaki);
      return this;
    }
    
    public Builder requestId()
    {
      this.zzakq.add(GoogleSignInOptions.zzakj);
      return this;
    }
    
    public Builder requestIdToken(String paramString)
    {
      this.zzajv = true;
      this.zzajw = zzcy(paramString);
      return this;
    }
    
    public Builder requestProfile()
    {
      this.zzakq.add(GoogleSignInOptions.zzakh);
      return this;
    }
    
    public Builder requestScopes(Scope paramScope, Scope... paramVarArgs)
    {
      this.zzakq.add(paramScope);
      this.zzakq.addAll(Arrays.asList(paramVarArgs));
      return this;
    }
    
    public Builder requestServerAuthCode(String paramString)
    {
      return requestServerAuthCode(paramString, false);
    }
    
    public Builder requestServerAuthCode(String paramString, boolean paramBoolean)
    {
      this.zzakl = true;
      this.zzajw = zzcy(paramString);
      this.zzakm = paramBoolean;
      return this;
    }
    
    public Builder setAccountName(String paramString)
    {
      this.zzahh = new Account(zzac.zzdr(paramString), "com.google");
      return this;
    }
    
    public Builder setHostedDomain(String paramString)
    {
      this.zzakn = zzac.zzdr(paramString);
      return this;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\auth\api\signin\GoogleSignInOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */