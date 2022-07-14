package com.google.zxing.client.android;

import android.app.AlertDialog.Builder;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import barcodescanner.xservices.nl.barcodescanner.R.string;
import barcodescanner.xservices.nl.barcodescanner.R.xml;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;

public final class PreferencesFragment
  extends PreferenceFragment
  implements SharedPreferences.OnSharedPreferenceChangeListener
{
  private CheckBoxPreference[] checkBoxPrefs;
  
  private void disableLastCheckedPref()
  {
    ArrayList localArrayList = new ArrayList(this.checkBoxPrefs.length);
    CheckBoxPreference[] arrayOfCheckBoxPreference = this.checkBoxPrefs;
    int j = arrayOfCheckBoxPreference.length;
    int i = 0;
    CheckBoxPreference localCheckBoxPreference;
    while (i < j)
    {
      localCheckBoxPreference = arrayOfCheckBoxPreference[i];
      if (localCheckBoxPreference.isChecked()) {
        localArrayList.add(localCheckBoxPreference);
      }
      i += 1;
    }
    if (localArrayList.size() <= 1)
    {
      i = 1;
      arrayOfCheckBoxPreference = this.checkBoxPrefs;
      int k = arrayOfCheckBoxPreference.length;
      j = 0;
      label87:
      if (j >= k) {
        return;
      }
      localCheckBoxPreference = arrayOfCheckBoxPreference[j];
      if ((i != 0) && (localArrayList.contains(localCheckBoxPreference))) {
        break label136;
      }
    }
    label136:
    for (boolean bool = true;; bool = false)
    {
      localCheckBoxPreference.setEnabled(bool);
      j += 1;
      break label87;
      i = 0;
      break;
    }
  }
  
  private static CheckBoxPreference[] findDecodePrefs(PreferenceScreen paramPreferenceScreen, String... paramVarArgs)
  {
    CheckBoxPreference[] arrayOfCheckBoxPreference = new CheckBoxPreference[paramVarArgs.length];
    int i = 0;
    while (i < paramVarArgs.length)
    {
      arrayOfCheckBoxPreference[i] = ((CheckBoxPreference)paramPreferenceScreen.findPreference(paramVarArgs[i]));
      i += 1;
    }
    return arrayOfCheckBoxPreference;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    addPreferencesFromResource(R.xml.preferences);
    paramBundle = getPreferenceScreen();
    paramBundle.getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    this.checkBoxPrefs = findDecodePrefs(paramBundle, new String[] { "preferences_decode_1D_product", "preferences_decode_1D_industrial", "preferences_decode_QR", "preferences_decode_Data_Matrix", "preferences_decode_Aztec", "preferences_decode_PDF417" });
    disableLastCheckedPref();
    ((EditTextPreference)paramBundle.findPreference("preferences_custom_product_search")).setOnPreferenceChangeListener(new CustomSearchURLValidator(null));
  }
  
  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    disableLastCheckedPref();
  }
  
  private class CustomSearchURLValidator
    implements Preference.OnPreferenceChangeListener
  {
    private CustomSearchURLValidator() {}
    
    private boolean isValid(Object paramObject)
    {
      if (paramObject == null) {}
      for (;;)
      {
        return true;
        paramObject = paramObject.toString();
        if (!((String)paramObject).isEmpty())
        {
          paramObject = ((String)paramObject).replaceAll("%[st]", "").replaceAll("%f(?![0-9a-f])", "");
          try
          {
            paramObject = new URI((String)paramObject).getScheme();
            if (paramObject == null) {
              return false;
            }
          }
          catch (URISyntaxException paramObject) {}
        }
      }
      return false;
    }
    
    public boolean onPreferenceChange(Preference paramPreference, Object paramObject)
    {
      boolean bool = true;
      if (!isValid(paramObject))
      {
        paramPreference = new AlertDialog.Builder(PreferencesFragment.this.getActivity());
        paramPreference.setTitle(R.string.msg_error);
        paramPreference.setMessage(R.string.msg_invalid_value);
        paramPreference.setCancelable(true);
        paramPreference.show();
        bool = false;
      }
      return bool;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\PreferencesFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */