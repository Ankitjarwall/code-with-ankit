package com.google.firebase.messaging;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class RemoteMessage
  extends com.google.android.gms.common.internal.safeparcel.zza
{
  public static final Parcelable.Creator<RemoteMessage> CREATOR = new zze();
  private Map<String, String> zzacc;
  Bundle zzaic;
  private Notification zzclO;
  
  RemoteMessage(Bundle paramBundle)
  {
    this.zzaic = paramBundle;
  }
  
  public String getCollapseKey()
  {
    return this.zzaic.getString("collapse_key");
  }
  
  public Map<String, String> getData()
  {
    if (this.zzacc == null)
    {
      this.zzacc = new ArrayMap();
      Iterator localIterator = this.zzaic.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        Object localObject = this.zzaic.get(str);
        if ((localObject instanceof String))
        {
          localObject = (String)localObject;
          if ((!str.startsWith("google.")) && (!str.startsWith("gcm.")) && (!str.equals("from")) && (!str.equals("message_type")) && (!str.equals("collapse_key"))) {
            this.zzacc.put(str, localObject);
          }
        }
      }
    }
    return this.zzacc;
  }
  
  public String getFrom()
  {
    return this.zzaic.getString("from");
  }
  
  public String getMessageId()
  {
    String str2 = this.zzaic.getString("google.message_id");
    String str1 = str2;
    if (str2 == null) {
      str1 = this.zzaic.getString("message_id");
    }
    return str1;
  }
  
  public String getMessageType()
  {
    return this.zzaic.getString("message_type");
  }
  
  public Notification getNotification()
  {
    if ((this.zzclO == null) && (zza.zzE(this.zzaic))) {
      this.zzclO = new Notification(this.zzaic, null);
    }
    return this.zzclO;
  }
  
  public long getSentTime()
  {
    Object localObject = this.zzaic.get("google.sent_time");
    if ((localObject instanceof Long)) {
      return ((Long)localObject).longValue();
    }
    if ((localObject instanceof String)) {
      try
      {
        long l = Long.parseLong((String)localObject);
        return l;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        localObject = String.valueOf(localObject);
        Log.w("FirebaseMessaging", String.valueOf(localObject).length() + 19 + "Invalid sent time: " + (String)localObject);
      }
    }
    return 0L;
  }
  
  public String getTo()
  {
    return this.zzaic.getString("google.to");
  }
  
  public int getTtl()
  {
    Object localObject = this.zzaic.get("google.ttl");
    if ((localObject instanceof Integer)) {
      return ((Integer)localObject).intValue();
    }
    if ((localObject instanceof String)) {
      try
      {
        int i = Integer.parseInt((String)localObject);
        return i;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        localObject = String.valueOf(localObject);
        Log.w("FirebaseMessaging", String.valueOf(localObject).length() + 13 + "Invalid TTL: " + (String)localObject);
      }
    }
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zze.zza(this, paramParcel, paramInt);
  }
  
  void zzK(Intent paramIntent)
  {
    paramIntent.putExtras(this.zzaic);
  }
  
  public static class Builder
  {
    private final Map<String, String> zzacc = new ArrayMap();
    private final Bundle zzaic = new Bundle();
    
    public Builder(String paramString)
    {
      if (TextUtils.isEmpty(paramString))
      {
        paramString = String.valueOf(paramString);
        if (paramString.length() != 0) {}
        for (paramString = "Invalid to: ".concat(paramString);; paramString = new String("Invalid to: ")) {
          throw new IllegalArgumentException(paramString);
        }
      }
      this.zzaic.putString("google.to", paramString);
    }
    
    public Builder addData(String paramString1, String paramString2)
    {
      this.zzacc.put(paramString1, paramString2);
      return this;
    }
    
    public RemoteMessage build()
    {
      Bundle localBundle = new Bundle();
      Object localObject = this.zzacc.entrySet().iterator();
      while (((Iterator)localObject).hasNext())
      {
        Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
        localBundle.putString((String)localEntry.getKey(), (String)localEntry.getValue());
      }
      localBundle.putAll(this.zzaic);
      localObject = FirebaseInstanceId.getInstance().getToken();
      if (localObject != null) {
        this.zzaic.putString("from", (String)localObject);
      }
      for (;;)
      {
        return new RemoteMessage(localBundle);
        this.zzaic.remove("from");
      }
    }
    
    public Builder clearData()
    {
      this.zzacc.clear();
      return this;
    }
    
    public Builder setCollapseKey(String paramString)
    {
      this.zzaic.putString("collapse_key", paramString);
      return this;
    }
    
    public Builder setData(Map<String, String> paramMap)
    {
      this.zzacc.clear();
      this.zzacc.putAll(paramMap);
      return this;
    }
    
    public Builder setMessageId(String paramString)
    {
      this.zzaic.putString("google.message_id", paramString);
      return this;
    }
    
    public Builder setMessageType(String paramString)
    {
      this.zzaic.putString("message_type", paramString);
      return this;
    }
    
    public Builder setTtl(int paramInt)
    {
      this.zzaic.putString("google.ttl", String.valueOf(paramInt));
      return this;
    }
  }
  
  public static class Notification
  {
    private final String mTag;
    private final String zzGr;
    private final String zzaQM;
    private final String zzamJ;
    private final String zzclP;
    private final String[] zzclQ;
    private final String zzclR;
    private final String[] zzclS;
    private final String zzclT;
    private final String zzclU;
    private final String zzclV;
    private final Uri zzclW;
    
    private Notification(Bundle paramBundle)
    {
      this.zzamJ = zza.zzf(paramBundle, "gcm.n.title");
      this.zzclP = zza.zzi(paramBundle, "gcm.n.title");
      this.zzclQ = zzl(paramBundle, "gcm.n.title");
      this.zzGr = zza.zzf(paramBundle, "gcm.n.body");
      this.zzclR = zza.zzi(paramBundle, "gcm.n.body");
      this.zzclS = zzl(paramBundle, "gcm.n.body");
      this.zzclT = zza.zzf(paramBundle, "gcm.n.icon");
      this.zzclU = zza.zzW(paramBundle);
      this.mTag = zza.zzf(paramBundle, "gcm.n.tag");
      this.zzaQM = zza.zzf(paramBundle, "gcm.n.color");
      this.zzclV = zza.zzf(paramBundle, "gcm.n.click_action");
      this.zzclW = zza.zzV(paramBundle);
    }
    
    private String[] zzl(Bundle paramBundle, String paramString)
    {
      paramBundle = zza.zzj(paramBundle, paramString);
      if (paramBundle == null) {
        return null;
      }
      paramString = new String[paramBundle.length];
      int i = 0;
      while (i < paramBundle.length)
      {
        paramString[i] = String.valueOf(paramBundle[i]);
        i += 1;
      }
      return paramString;
    }
    
    @Nullable
    public String getBody()
    {
      return this.zzGr;
    }
    
    @Nullable
    public String[] getBodyLocalizationArgs()
    {
      return this.zzclS;
    }
    
    @Nullable
    public String getBodyLocalizationKey()
    {
      return this.zzclR;
    }
    
    @Nullable
    public String getClickAction()
    {
      return this.zzclV;
    }
    
    @Nullable
    public String getColor()
    {
      return this.zzaQM;
    }
    
    @Nullable
    public String getIcon()
    {
      return this.zzclT;
    }
    
    @Nullable
    public Uri getLink()
    {
      return this.zzclW;
    }
    
    @Nullable
    public String getSound()
    {
      return this.zzclU;
    }
    
    @Nullable
    public String getTag()
    {
      return this.mTag;
    }
    
    @Nullable
    public String getTitle()
    {
      return this.zzamJ;
    }
    
    @Nullable
    public String[] getTitleLocalizationArgs()
    {
      return this.zzclQ;
    }
    
    @Nullable
    public String getTitleLocalizationKey()
    {
      return this.zzclP;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\firebase\messaging\RemoteMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */