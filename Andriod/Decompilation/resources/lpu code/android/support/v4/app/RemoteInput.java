package android.support.v4.app;

import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class RemoteInput
{
  private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";
  public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
  public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
  private static final String TAG = "RemoteInput";
  private final boolean mAllowFreeFormTextInput;
  private final Set<String> mAllowedDataTypes;
  private final CharSequence[] mChoices;
  private final Bundle mExtras;
  private final CharSequence mLabel;
  private final String mResultKey;
  
  RemoteInput(String paramString, CharSequence paramCharSequence, CharSequence[] paramArrayOfCharSequence, boolean paramBoolean, Bundle paramBundle, Set<String> paramSet)
  {
    this.mResultKey = paramString;
    this.mLabel = paramCharSequence;
    this.mChoices = paramArrayOfCharSequence;
    this.mAllowFreeFormTextInput = paramBoolean;
    this.mExtras = paramBundle;
    this.mAllowedDataTypes = paramSet;
  }
  
  public static void addDataResultToIntent(RemoteInput paramRemoteInput, Intent paramIntent, Map<String, Uri> paramMap)
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      android.app.RemoteInput.addDataResultToIntent(fromCompat(paramRemoteInput), paramIntent, paramMap);
      return;
    }
    if (Build.VERSION.SDK_INT >= 16)
    {
      Object localObject2 = getClipDataIntentFromIntent(paramIntent);
      Object localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = new Intent();
      }
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        paramMap = (Map.Entry)localIterator.next();
        String str = (String)paramMap.getKey();
        Uri localUri = (Uri)paramMap.getValue();
        if (str != null)
        {
          localObject2 = ((Intent)localObject1).getBundleExtra(getExtraResultsKeyForData(str));
          paramMap = (Map<String, Uri>)localObject2;
          if (localObject2 == null) {
            paramMap = new Bundle();
          }
          paramMap.putString(paramRemoteInput.getResultKey(), localUri.toString());
          ((Intent)localObject1).putExtra(getExtraResultsKeyForData(str), paramMap);
        }
      }
      paramIntent.setClipData(ClipData.newIntent("android.remoteinput.results", (Intent)localObject1));
      return;
    }
    Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
  }
  
  public static void addResultsToIntent(RemoteInput[] paramArrayOfRemoteInput, Intent paramIntent, Bundle paramBundle)
  {
    int i = 0;
    if (Build.VERSION.SDK_INT >= 26)
    {
      android.app.RemoteInput.addResultsToIntent(fromCompat(paramArrayOfRemoteInput), paramIntent, paramBundle);
      return;
    }
    Object localObject1;
    int j;
    Object localObject2;
    if (Build.VERSION.SDK_INT >= 20)
    {
      localObject1 = getResultsFromIntent(paramIntent);
      if (localObject1 == null) {}
      for (;;)
      {
        j = paramArrayOfRemoteInput.length;
        i = 0;
        while (i < j)
        {
          localObject1 = paramArrayOfRemoteInput[i];
          localObject2 = getDataResultsFromIntent(paramIntent, ((RemoteInput)localObject1).getResultKey());
          android.app.RemoteInput.addResultsToIntent(fromCompat(new RemoteInput[] { localObject1 }), paramIntent, paramBundle);
          if (localObject2 != null) {
            addDataResultToIntent((RemoteInput)localObject1, paramIntent, (Map)localObject2);
          }
          i += 1;
        }
        break;
        ((Bundle)localObject1).putAll(paramBundle);
        paramBundle = (Bundle)localObject1;
      }
    }
    if (Build.VERSION.SDK_INT >= 16)
    {
      localObject2 = getClipDataIntentFromIntent(paramIntent);
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = new Intent();
      }
      Object localObject3 = ((Intent)localObject1).getBundleExtra("android.remoteinput.resultsData");
      localObject2 = localObject3;
      if (localObject3 == null) {
        localObject2 = new Bundle();
      }
      j = paramArrayOfRemoteInput.length;
      while (i < j)
      {
        localObject3 = paramArrayOfRemoteInput[i];
        Object localObject4 = paramBundle.get(((RemoteInput)localObject3).getResultKey());
        if ((localObject4 instanceof CharSequence)) {
          ((Bundle)localObject2).putCharSequence(((RemoteInput)localObject3).getResultKey(), (CharSequence)localObject4);
        }
        i += 1;
      }
      ((Intent)localObject1).putExtra("android.remoteinput.resultsData", (Bundle)localObject2);
      paramIntent.setClipData(ClipData.newIntent("android.remoteinput.results", (Intent)localObject1));
      return;
    }
    Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
  }
  
  @RequiresApi(20)
  static android.app.RemoteInput fromCompat(RemoteInput paramRemoteInput)
  {
    return new android.app.RemoteInput.Builder(paramRemoteInput.getResultKey()).setLabel(paramRemoteInput.getLabel()).setChoices(paramRemoteInput.getChoices()).setAllowFreeFormInput(paramRemoteInput.getAllowFreeFormInput()).addExtras(paramRemoteInput.getExtras()).build();
  }
  
  @RequiresApi(20)
  static android.app.RemoteInput[] fromCompat(RemoteInput[] paramArrayOfRemoteInput)
  {
    Object localObject;
    if (paramArrayOfRemoteInput == null)
    {
      localObject = null;
      return (android.app.RemoteInput[])localObject;
    }
    android.app.RemoteInput[] arrayOfRemoteInput = new android.app.RemoteInput[paramArrayOfRemoteInput.length];
    int i = 0;
    for (;;)
    {
      localObject = arrayOfRemoteInput;
      if (i >= paramArrayOfRemoteInput.length) {
        break;
      }
      arrayOfRemoteInput[i] = fromCompat(paramArrayOfRemoteInput[i]);
      i += 1;
    }
  }
  
  @RequiresApi(16)
  private static Intent getClipDataIntentFromIntent(Intent paramIntent)
  {
    paramIntent = paramIntent.getClipData();
    if (paramIntent == null) {}
    ClipDescription localClipDescription;
    do
    {
      return null;
      localClipDescription = paramIntent.getDescription();
    } while ((!localClipDescription.hasMimeType("text/vnd.android.intent")) || (!localClipDescription.getLabel().equals("android.remoteinput.results")));
    return paramIntent.getItemAt(0).getIntent();
  }
  
  public static Map<String, Uri> getDataResultsFromIntent(Intent paramIntent, String paramString)
  {
    HashMap localHashMap = null;
    if (Build.VERSION.SDK_INT >= 26) {
      paramIntent = android.app.RemoteInput.getDataResultsFromIntent(paramIntent, paramString);
    }
    Intent localIntent;
    do
    {
      return paramIntent;
      if (Build.VERSION.SDK_INT < 16) {
        break;
      }
      localIntent = getClipDataIntentFromIntent(paramIntent);
      paramIntent = localHashMap;
    } while (localIntent == null);
    localHashMap = new HashMap();
    paramIntent = localIntent.getExtras().keySet().iterator();
    while (paramIntent.hasNext())
    {
      String str2 = (String)paramIntent.next();
      if (str2.startsWith("android.remoteinput.dataTypeResultsData"))
      {
        String str1 = str2.substring("android.remoteinput.dataTypeResultsData".length());
        if (!str1.isEmpty())
        {
          str2 = localIntent.getBundleExtra(str2).getString(paramString);
          if ((str2 != null) && (!str2.isEmpty())) {
            localHashMap.put(str1, Uri.parse(str2));
          }
        }
      }
    }
    paramIntent = localHashMap;
    if (localHashMap.isEmpty()) {
      paramIntent = null;
    }
    return paramIntent;
    Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
    return null;
  }
  
  private static String getExtraResultsKeyForData(String paramString)
  {
    return "android.remoteinput.dataTypeResultsData" + paramString;
  }
  
  public static Bundle getResultsFromIntent(Intent paramIntent)
  {
    Object localObject = null;
    if (Build.VERSION.SDK_INT >= 20) {
      paramIntent = android.app.RemoteInput.getResultsFromIntent(paramIntent);
    }
    Intent localIntent;
    do
    {
      return paramIntent;
      if (Build.VERSION.SDK_INT < 16) {
        break;
      }
      localIntent = getClipDataIntentFromIntent(paramIntent);
      paramIntent = (Intent)localObject;
    } while (localIntent == null);
    return (Bundle)localIntent.getExtras().getParcelable("android.remoteinput.resultsData");
    Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
    return null;
  }
  
  public boolean getAllowFreeFormInput()
  {
    return this.mAllowFreeFormTextInput;
  }
  
  public Set<String> getAllowedDataTypes()
  {
    return this.mAllowedDataTypes;
  }
  
  public CharSequence[] getChoices()
  {
    return this.mChoices;
  }
  
  public Bundle getExtras()
  {
    return this.mExtras;
  }
  
  public CharSequence getLabel()
  {
    return this.mLabel;
  }
  
  public String getResultKey()
  {
    return this.mResultKey;
  }
  
  public boolean isDataOnly()
  {
    return (!getAllowFreeFormInput()) && ((getChoices() == null) || (getChoices().length == 0)) && (getAllowedDataTypes() != null) && (!getAllowedDataTypes().isEmpty());
  }
  
  public static final class Builder
  {
    private boolean mAllowFreeFormTextInput = true;
    private final Set<String> mAllowedDataTypes = new HashSet();
    private CharSequence[] mChoices;
    private Bundle mExtras = new Bundle();
    private CharSequence mLabel;
    private final String mResultKey;
    
    public Builder(String paramString)
    {
      if (paramString == null) {
        throw new IllegalArgumentException("Result key can't be null");
      }
      this.mResultKey = paramString;
    }
    
    public Builder addExtras(Bundle paramBundle)
    {
      if (paramBundle != null) {
        this.mExtras.putAll(paramBundle);
      }
      return this;
    }
    
    public RemoteInput build()
    {
      return new RemoteInput(this.mResultKey, this.mLabel, this.mChoices, this.mAllowFreeFormTextInput, this.mExtras, this.mAllowedDataTypes);
    }
    
    public Bundle getExtras()
    {
      return this.mExtras;
    }
    
    public Builder setAllowDataType(String paramString, boolean paramBoolean)
    {
      if (paramBoolean)
      {
        this.mAllowedDataTypes.add(paramString);
        return this;
      }
      this.mAllowedDataTypes.remove(paramString);
      return this;
    }
    
    public Builder setAllowFreeFormInput(boolean paramBoolean)
    {
      this.mAllowFreeFormTextInput = paramBoolean;
      return this;
    }
    
    public Builder setChoices(CharSequence[] paramArrayOfCharSequence)
    {
      this.mChoices = paramArrayOfCharSequence;
      return this;
    }
    
    public Builder setLabel(CharSequence paramCharSequence)
    {
      this.mLabel = paramCharSequence;
      return this;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\app\RemoteInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */