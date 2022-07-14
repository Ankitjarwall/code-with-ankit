package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.R.string;

public class zzam
{
  private final Resources zzaGK;
  private final String zzaGL;
  
  public zzam(Context paramContext)
  {
    zzac.zzw(paramContext);
    this.zzaGK = paramContext.getResources();
    this.zzaGL = this.zzaGK.getResourcePackageName(R.string.common_google_play_services_unknown_issue);
  }
  
  public String getString(String paramString)
  {
    int i = this.zzaGK.getIdentifier(paramString, "string", this.zzaGL);
    if (i == 0) {
      return null;
    }
    return this.zzaGK.getString(i);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\internal\zzam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */