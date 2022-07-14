package com.google.zxing.client.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import barcodescanner.xservices.nl.barcodescanner.R.id;
import barcodescanner.xservices.nl.barcodescanner.R.layout;

public final class HelpActivity
  extends Activity
{
  private static final String BASE_URL = "file:///android_asset/html-" + LocaleManager.getTranslatedAssetLanguage() + '/';
  private WebView webView;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.help);
    this.webView = ((WebView)findViewById(R.id.help_contents));
    if (paramBundle == null)
    {
      this.webView.loadUrl(BASE_URL + "index.html");
      return;
    }
    this.webView.restoreState(paramBundle);
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (this.webView.canGoBack()))
    {
      this.webView.goBack();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    this.webView.saveState(paramBundle);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\HelpActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */