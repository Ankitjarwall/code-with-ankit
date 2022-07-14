package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

public final class zzg
  extends zzc.zza
{
  private Fragment zzaRN;
  
  private zzg(Fragment paramFragment)
  {
    this.zzaRN = paramFragment;
  }
  
  public static zzg zza(Fragment paramFragment)
  {
    if (paramFragment != null) {
      return new zzg(paramFragment);
    }
    return null;
  }
  
  public Bundle getArguments()
  {
    return this.zzaRN.getArguments();
  }
  
  public int getId()
  {
    return this.zzaRN.getId();
  }
  
  public boolean getRetainInstance()
  {
    return this.zzaRN.getRetainInstance();
  }
  
  public String getTag()
  {
    return this.zzaRN.getTag();
  }
  
  public int getTargetRequestCode()
  {
    return this.zzaRN.getTargetRequestCode();
  }
  
  public boolean getUserVisibleHint()
  {
    return this.zzaRN.getUserVisibleHint();
  }
  
  public IObjectWrapper getView()
  {
    return zzd.zzA(this.zzaRN.getView());
  }
  
  public boolean isAdded()
  {
    return this.zzaRN.isAdded();
  }
  
  public boolean isDetached()
  {
    return this.zzaRN.isDetached();
  }
  
  public boolean isHidden()
  {
    return this.zzaRN.isHidden();
  }
  
  public boolean isInLayout()
  {
    return this.zzaRN.isInLayout();
  }
  
  public boolean isRemoving()
  {
    return this.zzaRN.isRemoving();
  }
  
  public boolean isResumed()
  {
    return this.zzaRN.isResumed();
  }
  
  public boolean isVisible()
  {
    return this.zzaRN.isVisible();
  }
  
  public void setHasOptionsMenu(boolean paramBoolean)
  {
    this.zzaRN.setHasOptionsMenu(paramBoolean);
  }
  
  public void setMenuVisibility(boolean paramBoolean)
  {
    this.zzaRN.setMenuVisibility(paramBoolean);
  }
  
  public void setRetainInstance(boolean paramBoolean)
  {
    this.zzaRN.setRetainInstance(paramBoolean);
  }
  
  public void setUserVisibleHint(boolean paramBoolean)
  {
    this.zzaRN.setUserVisibleHint(paramBoolean);
  }
  
  public void startActivity(Intent paramIntent)
  {
    this.zzaRN.startActivity(paramIntent);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    this.zzaRN.startActivityForResult(paramIntent, paramInt);
  }
  
  public IObjectWrapper zzBO()
  {
    return zzd.zzA(this.zzaRN.getActivity());
  }
  
  public zzc zzBP()
  {
    return zza(this.zzaRN.getParentFragment());
  }
  
  public IObjectWrapper zzBQ()
  {
    return zzd.zzA(this.zzaRN.getResources());
  }
  
  public zzc zzBR()
  {
    return zza(this.zzaRN.getTargetFragment());
  }
  
  public void zzD(IObjectWrapper paramIObjectWrapper)
  {
    paramIObjectWrapper = (View)zzd.zzF(paramIObjectWrapper);
    this.zzaRN.registerForContextMenu(paramIObjectWrapper);
  }
  
  public void zzE(IObjectWrapper paramIObjectWrapper)
  {
    paramIObjectWrapper = (View)zzd.zzF(paramIObjectWrapper);
    this.zzaRN.unregisterForContextMenu(paramIObjectWrapper);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\dynamic\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */