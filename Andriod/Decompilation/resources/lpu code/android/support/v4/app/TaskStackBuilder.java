package android.support.v4.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

public final class TaskStackBuilder
  implements Iterable<Intent>
{
  private static final TaskStackBuilderBaseImpl IMPL = new TaskStackBuilderBaseImpl();
  private static final String TAG = "TaskStackBuilder";
  private final ArrayList<Intent> mIntents = new ArrayList();
  private final Context mSourceContext;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      IMPL = new TaskStackBuilderApi16Impl();
      return;
    }
  }
  
  private TaskStackBuilder(Context paramContext)
  {
    this.mSourceContext = paramContext;
  }
  
  @NonNull
  public static TaskStackBuilder create(@NonNull Context paramContext)
  {
    return new TaskStackBuilder(paramContext);
  }
  
  @Deprecated
  public static TaskStackBuilder from(Context paramContext)
  {
    return create(paramContext);
  }
  
  @NonNull
  public TaskStackBuilder addNextIntent(@NonNull Intent paramIntent)
  {
    this.mIntents.add(paramIntent);
    return this;
  }
  
  @NonNull
  public TaskStackBuilder addNextIntentWithParentStack(@NonNull Intent paramIntent)
  {
    ComponentName localComponentName2 = paramIntent.getComponent();
    ComponentName localComponentName1 = localComponentName2;
    if (localComponentName2 == null) {
      localComponentName1 = paramIntent.resolveActivity(this.mSourceContext.getPackageManager());
    }
    if (localComponentName1 != null) {
      addParentStack(localComponentName1);
    }
    addNextIntent(paramIntent);
    return this;
  }
  
  @NonNull
  public TaskStackBuilder addParentStack(@NonNull Activity paramActivity)
  {
    Object localObject1 = null;
    if ((paramActivity instanceof SupportParentable)) {
      localObject1 = ((SupportParentable)paramActivity).getSupportParentActivityIntent();
    }
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = NavUtils.getParentActivityIntent(paramActivity);
    }
    if (localObject2 != null)
    {
      localObject1 = ((Intent)localObject2).getComponent();
      paramActivity = (Activity)localObject1;
      if (localObject1 == null) {
        paramActivity = ((Intent)localObject2).resolveActivity(this.mSourceContext.getPackageManager());
      }
      addParentStack(paramActivity);
      addNextIntent((Intent)localObject2);
    }
    return this;
  }
  
  public TaskStackBuilder addParentStack(ComponentName paramComponentName)
  {
    int i = this.mIntents.size();
    try
    {
      for (paramComponentName = NavUtils.getParentActivityIntent(this.mSourceContext, paramComponentName); paramComponentName != null; paramComponentName = NavUtils.getParentActivityIntent(this.mSourceContext, paramComponentName.getComponent())) {
        this.mIntents.add(i, paramComponentName);
      }
      return this;
    }
    catch (PackageManager.NameNotFoundException paramComponentName)
    {
      Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
      throw new IllegalArgumentException(paramComponentName);
    }
  }
  
  @NonNull
  public TaskStackBuilder addParentStack(@NonNull Class<?> paramClass)
  {
    return addParentStack(new ComponentName(this.mSourceContext, paramClass));
  }
  
  @Nullable
  public Intent editIntentAt(int paramInt)
  {
    return (Intent)this.mIntents.get(paramInt);
  }
  
  @Deprecated
  public Intent getIntent(int paramInt)
  {
    return editIntentAt(paramInt);
  }
  
  public int getIntentCount()
  {
    return this.mIntents.size();
  }
  
  @NonNull
  public Intent[] getIntents()
  {
    Intent[] arrayOfIntent = new Intent[this.mIntents.size()];
    if (arrayOfIntent.length == 0) {}
    for (;;)
    {
      return arrayOfIntent;
      arrayOfIntent[0] = new Intent((Intent)this.mIntents.get(0)).addFlags(268484608);
      int i = 1;
      while (i < arrayOfIntent.length)
      {
        arrayOfIntent[i] = new Intent((Intent)this.mIntents.get(i));
        i += 1;
      }
    }
  }
  
  @Nullable
  public PendingIntent getPendingIntent(int paramInt1, int paramInt2)
  {
    return getPendingIntent(paramInt1, paramInt2, null);
  }
  
  @Nullable
  public PendingIntent getPendingIntent(int paramInt1, int paramInt2, @Nullable Bundle paramBundle)
  {
    if (this.mIntents.isEmpty()) {
      throw new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent");
    }
    Intent[] arrayOfIntent = (Intent[])this.mIntents.toArray(new Intent[this.mIntents.size()]);
    arrayOfIntent[0] = new Intent(arrayOfIntent[0]).addFlags(268484608);
    return IMPL.getPendingIntent(this.mSourceContext, arrayOfIntent, paramInt1, paramInt2, paramBundle);
  }
  
  @Deprecated
  public Iterator<Intent> iterator()
  {
    return this.mIntents.iterator();
  }
  
  public void startActivities()
  {
    startActivities(null);
  }
  
  public void startActivities(@Nullable Bundle paramBundle)
  {
    if (this.mIntents.isEmpty()) {
      throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
    }
    Intent[] arrayOfIntent = (Intent[])this.mIntents.toArray(new Intent[this.mIntents.size()]);
    arrayOfIntent[0] = new Intent(arrayOfIntent[0]).addFlags(268484608);
    if (!ContextCompat.startActivities(this.mSourceContext, arrayOfIntent, paramBundle))
    {
      paramBundle = new Intent(arrayOfIntent[(arrayOfIntent.length - 1)]);
      paramBundle.addFlags(268435456);
      this.mSourceContext.startActivity(paramBundle);
    }
  }
  
  public static abstract interface SupportParentable
  {
    @Nullable
    public abstract Intent getSupportParentActivityIntent();
  }
  
  @RequiresApi(16)
  static class TaskStackBuilderApi16Impl
    extends TaskStackBuilder.TaskStackBuilderBaseImpl
  {
    public PendingIntent getPendingIntent(Context paramContext, Intent[] paramArrayOfIntent, int paramInt1, int paramInt2, Bundle paramBundle)
    {
      paramArrayOfIntent[0] = new Intent(paramArrayOfIntent[0]).addFlags(268484608);
      return PendingIntent.getActivities(paramContext, paramInt1, paramArrayOfIntent, paramInt2, paramBundle);
    }
  }
  
  static class TaskStackBuilderBaseImpl
  {
    public PendingIntent getPendingIntent(Context paramContext, Intent[] paramArrayOfIntent, int paramInt1, int paramInt2, Bundle paramBundle)
    {
      paramArrayOfIntent[0] = new Intent(paramArrayOfIntent[0]).addFlags(268484608);
      return PendingIntent.getActivities(paramContext, paramInt1, paramArrayOfIntent, paramInt2);
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\app\TaskStackBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */