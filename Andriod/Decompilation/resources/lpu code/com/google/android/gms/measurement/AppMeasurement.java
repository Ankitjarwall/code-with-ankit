package com.google.android.gms.measurement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.internal.zzaba;
import com.google.android.gms.internal.zzatb;
import com.google.android.gms.internal.zzati;
import com.google.android.gms.internal.zzatx;
import com.google.android.gms.internal.zzatx.zza;
import com.google.android.gms.internal.zzaue;
import com.google.android.gms.internal.zzauj;
import com.google.android.gms.internal.zzauk;
import com.google.android.gms.internal.zzauq;
import com.google.android.gms.internal.zzaut;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.google.firebase.analytics.FirebaseAnalytics.UserProperty;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Deprecated
@Keep
public class AppMeasurement
{
  private final zzaue zzbqb;
  
  public AppMeasurement(zzaue paramzzaue)
  {
    zzac.zzw(paramzzaue);
    this.zzbqb = paramzzaue;
  }
  
  @Deprecated
  @Keep
  @RequiresPermission(allOf={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WAKE_LOCK"})
  public static AppMeasurement getInstance(Context paramContext)
  {
    return zzaue.zzbM(paramContext).zzMx();
  }
  
  private void zzc(String paramString1, String paramString2, Object paramObject)
  {
    this.zzbqb.zzKa().zzd(paramString1, paramString2, paramObject);
  }
  
  @Keep
  public void beginAdUnitExposure(@NonNull @Size(min=1L) String paramString)
  {
    this.zzbqb.zzJY().beginAdUnitExposure(paramString);
  }
  
  @Keep
  protected void clearConditionalUserProperty(@NonNull @Size(max=24L, min=1L) String paramString1, @Nullable String paramString2, @Nullable Bundle paramBundle)
  {
    this.zzbqb.zzKa().clearConditionalUserProperty(paramString1, paramString2, paramBundle);
  }
  
  @Keep
  protected void clearConditionalUserPropertyAs(@NonNull @Size(min=1L) String paramString1, @NonNull @Size(max=24L, min=1L) String paramString2, @Nullable String paramString3, @Nullable Bundle paramBundle)
  {
    this.zzbqb.zzKa().clearConditionalUserPropertyAs(paramString1, paramString2, paramString3, paramBundle);
  }
  
  @Keep
  public void endAdUnitExposure(@NonNull @Size(min=1L) String paramString)
  {
    this.zzbqb.zzJY().endAdUnitExposure(paramString);
  }
  
  @Keep
  public long generateEventId()
  {
    return this.zzbqb.zzKh().zzNk();
  }
  
  @Keep
  @Nullable
  @WorkerThread
  public String getAppInstanceId()
  {
    return this.zzbqb.zzKa().zzfQ(null);
  }
  
  @Keep
  @WorkerThread
  protected List<ConditionalUserProperty> getConditionalUserProperties(@Nullable String paramString1, @Nullable @Size(max=23L, min=1L) String paramString2)
  {
    return this.zzbqb.zzKa().getConditionalUserProperties(paramString1, paramString2);
  }
  
  @Keep
  @WorkerThread
  protected List<ConditionalUserProperty> getConditionalUserPropertiesAs(@NonNull @Size(min=1L) String paramString1, @Nullable String paramString2, @Nullable @Size(max=23L, min=1L) String paramString3)
  {
    return this.zzbqb.zzKa().getConditionalUserPropertiesAs(paramString1, paramString2, paramString3);
  }
  
  @Keep
  @Nullable
  public String getCurrentScreenClass()
  {
    zzf localzzf = this.zzbqb.zzKe().zzMX();
    if (localzzf != null) {
      return localzzf.zzbqf;
    }
    return null;
  }
  
  @Keep
  @Nullable
  public String getCurrentScreenName()
  {
    zzf localzzf = this.zzbqb.zzKe().zzMX();
    if (localzzf != null) {
      return localzzf.zzbqe;
    }
    return null;
  }
  
  @Keep
  @Nullable
  public String getGmpAppId()
  {
    try
    {
      String str = zzaba.zzwQ();
      return str;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      this.zzbqb.zzKl().zzLZ().zzj("getGoogleAppId failed with exception", localIllegalStateException);
    }
    return null;
  }
  
  @Keep
  @WorkerThread
  protected int getMaxUserProperties(@NonNull @Size(min=1L) String paramString)
  {
    return this.zzbqb.zzKa().getMaxUserProperties(paramString);
  }
  
  @Keep
  @WorkerThread
  protected Map<String, Object> getUserProperties(@Nullable String paramString1, @Nullable @Size(max=24L, min=1L) String paramString2, boolean paramBoolean)
  {
    return this.zzbqb.zzKa().getUserProperties(paramString1, paramString2, paramBoolean);
  }
  
  @Keep
  @WorkerThread
  protected Map<String, Object> getUserPropertiesAs(@NonNull @Size(min=1L) String paramString1, @Nullable String paramString2, @Nullable @Size(max=23L, min=1L) String paramString3, boolean paramBoolean)
  {
    return this.zzbqb.zzKa().getUserPropertiesAs(paramString1, paramString2, paramString3, paramBoolean);
  }
  
  public void logEvent(@NonNull @Size(max=40L, min=1L) String paramString, Bundle paramBundle)
  {
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    this.zzbqb.zzKn().zzLh();
    if (!"_iap".equals(paramString))
    {
      int j = this.zzbqb.zzKh().zzfU(paramString);
      if (j != 0)
      {
        paramBundle = this.zzbqb.zzKh().zza(paramString, this.zzbqb.zzKn().zzKM(), true);
        if (paramString != null) {}
        for (int i = paramString.length();; i = 0)
        {
          this.zzbqb.zzKh().zza(j, "_ev", paramBundle, i);
          return;
        }
      }
    }
    this.zzbqb.zzKa().zza("app", paramString, localBundle, true);
  }
  
  @Keep
  public void logEventInternal(String paramString1, String paramString2, Bundle paramBundle)
  {
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    this.zzbqb.zzKa().zze(paramString1, paramString2, localBundle);
  }
  
  @Keep
  public void registerOnScreenChangeCallback(@NonNull zzd paramzzd)
  {
    this.zzbqb.zzKe().registerOnScreenChangeCallback(paramzzd);
  }
  
  @Keep
  protected void setConditionalUserProperty(@NonNull ConditionalUserProperty paramConditionalUserProperty)
  {
    this.zzbqb.zzKa().setConditionalUserProperty(paramConditionalUserProperty);
  }
  
  @Keep
  protected void setConditionalUserPropertyAs(@NonNull ConditionalUserProperty paramConditionalUserProperty)
  {
    this.zzbqb.zzKa().setConditionalUserPropertyAs(paramConditionalUserProperty);
  }
  
  @Deprecated
  public void setMeasurementEnabled(boolean paramBoolean)
  {
    this.zzbqb.zzKa().setMeasurementEnabled(paramBoolean);
  }
  
  public void setMinimumSessionDuration(long paramLong)
  {
    this.zzbqb.zzKa().setMinimumSessionDuration(paramLong);
  }
  
  public void setSessionTimeoutDuration(long paramLong)
  {
    this.zzbqb.zzKa().setSessionTimeoutDuration(paramLong);
  }
  
  public void setUserId(String paramString)
  {
    zzb("app", "_id", paramString);
  }
  
  public void setUserProperty(@NonNull @Size(max=24L, min=1L) String paramString1, @Nullable @Size(max=36L) String paramString2)
  {
    int j = this.zzbqb.zzKh().zzfW(paramString1);
    if (j != 0)
    {
      paramString2 = this.zzbqb.zzKh().zza(paramString1, this.zzbqb.zzKn().zzKN(), true);
      if (paramString1 != null) {}
      for (int i = paramString1.length();; i = 0)
      {
        this.zzbqb.zzKh().zza(j, "_ev", paramString2, i);
        return;
      }
    }
    zzb("app", paramString1, paramString2);
  }
  
  @Keep
  public void unregisterOnScreenChangeCallback(@NonNull zzd paramzzd)
  {
    this.zzbqb.zzKe().unregisterOnScreenChangeCallback(paramzzd);
  }
  
  @WorkerThread
  public void zza(zzb paramzzb)
  {
    this.zzbqb.zzKa().zza(paramzzb);
  }
  
  public void zza(zzc paramzzc)
  {
    this.zzbqb.zzKa().zza(paramzzc);
  }
  
  public void zza(String paramString1, String paramString2, Bundle paramBundle, long paramLong)
  {
    if (paramBundle == null) {
      paramBundle = new Bundle();
    }
    for (;;)
    {
      this.zzbqb.zzKa().zzd(paramString1, paramString2, paramBundle, paramLong);
      return;
    }
  }
  
  @WorkerThread
  public Map<String, Object> zzaI(boolean paramBoolean)
  {
    Object localObject = this.zzbqb.zzKa().zzaM(paramBoolean);
    ArrayMap localArrayMap = new ArrayMap(((List)localObject).size());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      zzauq localzzauq = (zzauq)((Iterator)localObject).next();
      localArrayMap.put(localzzauq.name, localzzauq.getValue());
    }
    return localArrayMap;
  }
  
  public void zzb(String paramString1, String paramString2, Object paramObject)
  {
    zzc(paramString1, paramString2, paramObject);
  }
  
  public static class ConditionalUserProperty
  {
    @Keep
    public boolean mActive;
    @Keep
    public String mAppId;
    @Keep
    public long mCreationTimestamp;
    @Keep
    public String mExpiredEventName;
    @Keep
    public Bundle mExpiredEventParams;
    @Keep
    public String mName;
    @Keep
    public String mOrigin;
    @Keep
    public long mTimeToLive;
    @Keep
    public String mTimedOutEventName;
    @Keep
    public Bundle mTimedOutEventParams;
    @Keep
    public String mTriggerEventName;
    @Keep
    public long mTriggerTimeout;
    @Keep
    public String mTriggeredEventName;
    @Keep
    public Bundle mTriggeredEventParams;
    @Keep
    public long mTriggeredTimestamp;
    @Keep
    public Object mValue;
    
    public ConditionalUserProperty() {}
    
    public ConditionalUserProperty(ConditionalUserProperty paramConditionalUserProperty)
    {
      zzac.zzw(paramConditionalUserProperty);
      this.mAppId = paramConditionalUserProperty.mAppId;
      this.mOrigin = paramConditionalUserProperty.mOrigin;
      this.mCreationTimestamp = paramConditionalUserProperty.mCreationTimestamp;
      this.mName = paramConditionalUserProperty.mName;
      if (paramConditionalUserProperty.mValue != null)
      {
        this.mValue = zzaut.zzI(paramConditionalUserProperty.mValue);
        if (this.mValue == null) {
          this.mValue = paramConditionalUserProperty.mValue;
        }
      }
      this.mValue = paramConditionalUserProperty.mValue;
      this.mActive = paramConditionalUserProperty.mActive;
      this.mTriggerEventName = paramConditionalUserProperty.mTriggerEventName;
      this.mTriggerTimeout = paramConditionalUserProperty.mTriggerTimeout;
      this.mTimedOutEventName = paramConditionalUserProperty.mTimedOutEventName;
      if (paramConditionalUserProperty.mTimedOutEventParams != null) {
        this.mTimedOutEventParams = new Bundle(paramConditionalUserProperty.mTimedOutEventParams);
      }
      this.mTriggeredEventName = paramConditionalUserProperty.mTriggeredEventName;
      if (paramConditionalUserProperty.mTriggeredEventParams != null) {
        this.mTriggeredEventParams = new Bundle(paramConditionalUserProperty.mTriggeredEventParams);
      }
      this.mTriggeredTimestamp = paramConditionalUserProperty.mTriggeredTimestamp;
      this.mTimeToLive = paramConditionalUserProperty.mTimeToLive;
      this.mExpiredEventName = paramConditionalUserProperty.mExpiredEventName;
      if (paramConditionalUserProperty.mExpiredEventParams != null) {
        this.mExpiredEventParams = new Bundle(paramConditionalUserProperty.mExpiredEventParams);
      }
    }
  }
  
  public static final class zza
    extends FirebaseAnalytics.Event
  {
    public static final Map<String, String> zzbqc = zzf.zzb(new String[] { "app_clear_data", "app_exception", "app_remove", "app_upgrade", "app_install", "app_update", "firebase_campaign", "error", "first_open", "first_visit", "in_app_purchase", "notification_dismiss", "notification_foreground", "notification_open", "notification_receive", "os_update", "session_start", "user_engagement", "firebase_ad_exposure", "firebase_adunit_exposure", "firebase_extra_parameter" }, new String[] { "_cd", "_ae", "_ui", "_in", "_ug", "_au", "_cmp", "_err", "_f", "_v", "_iap", "_nd", "_nf", "_no", "_nr", "_ou", "_s", "_e", "_xa", "_xu", "_ep" });
  }
  
  public static abstract interface zzb
  {
    @WorkerThread
    public abstract void zzb(String paramString1, String paramString2, Bundle paramBundle, long paramLong);
  }
  
  public static abstract interface zzc
  {
    @WorkerThread
    public abstract void zzc(String paramString1, String paramString2, Bundle paramBundle, long paramLong);
  }
  
  public static abstract interface zzd
  {
    @MainThread
    public abstract boolean zza(AppMeasurement.zzf paramzzf1, AppMeasurement.zzf paramzzf2);
  }
  
  public static final class zze
    extends FirebaseAnalytics.Param
  {
    public static final Map<String, String> zzbqd = zzf.zzb(new String[] { "firebase_conversion", "engagement_time_msec", "exposure_time", "ad_event_id", "ad_unit_id", "firebase_error", "firebase_error_value", "firebase_error_length", "debug", "realtime", "firebase_event_origin", "firebase_screen", "firebase_screen_class", "firebase_screen_id", "message_device_time", "message_id", "message_name", "message_time", "previous_app_version", "previous_os_version", "topic", "update_with_analytics", "previous_first_open_count", "system_app", "system_app_update", "previous_install_count", "firebase_event_id", "firebase_extra_params_ct", "firebase_group_name", "firebase_list_length", "firebase_index", "firebase_event_name" }, new String[] { "_c", "_et", "_xt", "_aeid", "_ai", "_err", "_ev", "_el", "_dbg", "_r", "_o", "_sn", "_sc", "_si", "_ndt", "_nmid", "_nmn", "_nmt", "_pv", "_po", "_nt", "_uwa", "_pfo", "_sys", "_sysu", "_pin", "_eid", "_epc", "_gn", "_ll", "_i", "_en" });
  }
  
  public static class zzf
  {
    public String zzbqe;
    public String zzbqf;
    public long zzbqg;
    
    public zzf() {}
    
    public zzf(zzf paramzzf)
    {
      this.zzbqe = paramzzf.zzbqe;
      this.zzbqf = paramzzf.zzbqf;
      this.zzbqg = paramzzf.zzbqg;
    }
  }
  
  public static final class zzg
    extends FirebaseAnalytics.UserProperty
  {
    public static final Map<String, String> zzbqh = zzf.zzb(new String[] { "firebase_last_notification", "first_open_time", "first_visit_time", "last_deep_link_referrer", "user_id" }, new String[] { "_ln", "_fot", "_fvt", "_ldl", "_id" });
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\measurement\AppMeasurement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */