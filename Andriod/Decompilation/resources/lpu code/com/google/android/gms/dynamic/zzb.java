package com.google.android.gms.dynamic;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

@SuppressLint({"NewApi"})
public final class zzb
  extends zzc.zza
{
  private Fragment zzaRK;
  
  private zzb(Fragment paramFragment)
  {
    this.zzaRK = paramFragment;
  }
  
  public static zzb zza(Fragment paramFragment)
  {
    if (paramFragment != null) {
      return new zzb(paramFragment);
    }
    return null;
  }
  
  public Bundle getArguments()
  {
    return this.zzaRK.getArguments();
  }
  
  public int getId()
  {
    return this.zzaRK.getId();
  }
  
  public boolean getRetainInstance()
  {
    return this.zzaRK.getRetainInstance();
  }
  
  public String getTag()
  {
    return this.zzaRK.getTag();
  }
  
  public int getTargetRequestCode()
  {
    return this.zzaRK.getTargetRequestCode();
  }
  
  public boolean getUserVisibleHint()
  {
    return this.zzaRK.getUserVisibleHint();
  }
  
  public IObjectWrapper getView()
  {
    return zzd.zzA(this.zzaRK.getView());
  }
  
  public boolean isAdded()
  {
    return this.zzaRK.isAdded();
  }
  
  public boolean isDetached()
  {
    return this.zzaRK.isDetached();
  }
  
  public boolean isHidden()
  {
    return this.zzaRK.isHidden();
  }
  
  public boolean isInLayout()
  {
    return this.zzaRK.isInLayout();
  }
  
  public boolean isRemoving()
  {
    return this.zzaRK.isRemoving();
  }
  
  public boolean isResumed()
  {
    return this.zzaRK.isResumed();
  }
  
  public boolean isVisible()
  {
    return this.zzaRK.isVisible();
  }
  
  public void setHasOptionsMenu(boolean paramBoolean)
  {
    this.zzaRK.setHasOptionsMenu(paramBoolean);
  }
  
  public void setMenuVisibility(boolean paramBoolean)
  {
    this.zzaRK.setMenuVisibility(paramBoolean);
  }
  
  public void setRetainInstance(boolean paramBoolean)
  {
    this.zzaRK.setRetainInstance(paramBoolean);
  }
  
  public void setUserVisibleHint(boolean paramBoolean)
  {
    this.zzaRK.setUserVisibleHint(paramBoolean);
  }
  
  public void startActivity(Intent paramIntent)
  {
    this.zzaRK.startActivity(paramIntent);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    this.zzaRK.startActivityForResult(paramIntent, paramInt);
  }
  
  public IObjectWrapper zzBO()
  {
    return zzd.zzA(this.zzaRK.getActivity());
  }
  
  public zzc zzBP()
  {
    return zza(this.zzaRK.getParentFragment());
  }
  
  public IObjectWrapper zzBQ()
  {
    return zzd.zzA(this.zzaRK.getResources());
  }
  
  public zzc zzBR()
  {
    return zza(this.zzaRK.getTargetFragment());
  }
  
  public void zzD(IObjectWrapper paramIObjectWrapper)
  {
    paramIObjectWrapper = (View)zzd.zzF(paramIObjectWrapper);
    this.zzaRK.registerForContextMenu(paramIObjectWrapper);
  }
  
  public void zzE(IObjectWrapper paramIObjectWrapper)
  {
    paramIObjectWrapper = (View)zzd.zzF(paramIObjectWrapper);
    this.zzaRK.unregisterForContextMenu(paramIObjectWrapper);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\dynamic\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */