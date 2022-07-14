package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.zzh;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class zza<T extends LifecycleDelegate>
{
  private T zzaRA;
  private Bundle zzaRB;
  private LinkedList<zza> zzaRC;
  private final zze<T> zzaRD = new zze()
  {
    public void zza(T paramAnonymousT)
    {
      zza.zza(zza.this, paramAnonymousT);
      paramAnonymousT = zza.zza(zza.this).iterator();
      while (paramAnonymousT.hasNext()) {
        ((zza.zza)paramAnonymousT.next()).zzb(zza.zzb(zza.this));
      }
      zza.zza(zza.this).clear();
      zza.zza(zza.this, null);
    }
  };
  
  private void zza(Bundle paramBundle, zza paramzza)
  {
    if (this.zzaRA != null)
    {
      paramzza.zzb(this.zzaRA);
      return;
    }
    if (this.zzaRC == null) {
      this.zzaRC = new LinkedList();
    }
    this.zzaRC.add(paramzza);
    if (paramBundle != null)
    {
      if (this.zzaRB != null) {
        break label76;
      }
      this.zzaRB = ((Bundle)paramBundle.clone());
    }
    for (;;)
    {
      zza(this.zzaRD);
      return;
      label76:
      this.zzaRB.putAll(paramBundle);
    }
  }
  
  @VisibleForTesting
  static void zza(final FrameLayout paramFrameLayout, GoogleApiAvailability paramGoogleApiAvailability)
  {
    Context localContext = paramFrameLayout.getContext();
    int i = paramGoogleApiAvailability.isGooglePlayServicesAvailable(localContext);
    String str2 = zzh.zzi(localContext, i);
    String str1 = zzh.zzk(localContext, i);
    LinearLayout localLinearLayout = new LinearLayout(paramFrameLayout.getContext());
    localLinearLayout.setOrientation(1);
    localLinearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
    paramFrameLayout.addView(localLinearLayout);
    paramFrameLayout = new TextView(paramFrameLayout.getContext());
    paramFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
    paramFrameLayout.setText(str2);
    localLinearLayout.addView(paramFrameLayout);
    paramFrameLayout = paramGoogleApiAvailability.zzb(localContext, i, null);
    if (paramFrameLayout != null)
    {
      paramGoogleApiAvailability = new Button(localContext);
      paramGoogleApiAvailability.setId(16908313);
      paramGoogleApiAvailability.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
      paramGoogleApiAvailability.setText(str1);
      localLinearLayout.addView(paramGoogleApiAvailability);
      paramGoogleApiAvailability.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          try
          {
            zza.this.startActivity(paramFrameLayout);
            return;
          }
          catch (ActivityNotFoundException paramAnonymousView)
          {
            Log.e("DeferredLifecycleHelper", "Failed to start resolution intent", paramAnonymousView);
          }
        }
      });
    }
  }
  
  public static void zzb(FrameLayout paramFrameLayout)
  {
    zza(paramFrameLayout, GoogleApiAvailability.getInstance());
  }
  
  private void zzgt(int paramInt)
  {
    while ((!this.zzaRC.isEmpty()) && (((zza)this.zzaRC.getLast()).getState() >= paramInt)) {
      this.zzaRC.removeLast();
    }
  }
  
  public void onCreate(final Bundle paramBundle)
  {
    zza(paramBundle, new zza()
    {
      public int getState()
      {
        return 1;
      }
      
      public void zzb(LifecycleDelegate paramAnonymousLifecycleDelegate)
      {
        zza.zzb(zza.this).onCreate(paramBundle);
      }
    });
  }
  
  public View onCreateView(final LayoutInflater paramLayoutInflater, final ViewGroup paramViewGroup, final Bundle paramBundle)
  {
    final FrameLayout localFrameLayout = new FrameLayout(paramLayoutInflater.getContext());
    zza(paramBundle, new zza()
    {
      public int getState()
      {
        return 2;
      }
      
      public void zzb(LifecycleDelegate paramAnonymousLifecycleDelegate)
      {
        localFrameLayout.removeAllViews();
        localFrameLayout.addView(zza.zzb(zza.this).onCreateView(paramLayoutInflater, paramViewGroup, paramBundle));
      }
    });
    if (this.zzaRA == null) {
      zza(localFrameLayout);
    }
    return localFrameLayout;
  }
  
  public void onDestroy()
  {
    if (this.zzaRA != null)
    {
      this.zzaRA.onDestroy();
      return;
    }
    zzgt(1);
  }
  
  public void onDestroyView()
  {
    if (this.zzaRA != null)
    {
      this.zzaRA.onDestroyView();
      return;
    }
    zzgt(2);
  }
  
  public void onInflate(final Activity paramActivity, final Bundle paramBundle1, final Bundle paramBundle2)
  {
    zza(paramBundle2, new zza()
    {
      public int getState()
      {
        return 0;
      }
      
      public void zzb(LifecycleDelegate paramAnonymousLifecycleDelegate)
      {
        zza.zzb(zza.this).onInflate(paramActivity, paramBundle1, paramBundle2);
      }
    });
  }
  
  public void onLowMemory()
  {
    if (this.zzaRA != null) {
      this.zzaRA.onLowMemory();
    }
  }
  
  public void onPause()
  {
    if (this.zzaRA != null)
    {
      this.zzaRA.onPause();
      return;
    }
    zzgt(5);
  }
  
  public void onResume()
  {
    zza(null, new zza()
    {
      public int getState()
      {
        return 5;
      }
      
      public void zzb(LifecycleDelegate paramAnonymousLifecycleDelegate)
      {
        zza.zzb(zza.this).onResume();
      }
    });
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    if (this.zzaRA != null) {
      this.zzaRA.onSaveInstanceState(paramBundle);
    }
    while (this.zzaRB == null) {
      return;
    }
    paramBundle.putAll(this.zzaRB);
  }
  
  public void onStart()
  {
    zza(null, new zza()
    {
      public int getState()
      {
        return 4;
      }
      
      public void zzb(LifecycleDelegate paramAnonymousLifecycleDelegate)
      {
        zza.zzb(zza.this).onStart();
      }
    });
  }
  
  public void onStop()
  {
    if (this.zzaRA != null)
    {
      this.zzaRA.onStop();
      return;
    }
    zzgt(4);
  }
  
  public T zzBN()
  {
    return this.zzaRA;
  }
  
  protected void zza(FrameLayout paramFrameLayout)
  {
    zzb(paramFrameLayout);
  }
  
  protected abstract void zza(zze<T> paramzze);
  
  private static abstract interface zza
  {
    public abstract int getState();
    
    public abstract void zzb(LifecycleDelegate paramLifecycleDelegate);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\dynamic\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */