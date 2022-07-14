package com.keyes.youtube;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Sample
  extends Activity
{
  protected void onCreate(final Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.sample);
    paramBundle = (TextView)findViewById(R.id.youtubeIdText);
    ((Button)findViewById(R.id.viewVideoButton)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = paramBundle.getText().toString();
        if ((paramAnonymousView == null) || (paramAnonymousView.trim().equals(""))) {
          return;
        }
        paramAnonymousView = new Intent(null, Uri.parse("ytv://" + paramAnonymousView), Sample.this, OpenYouTubePlayerActivity.class);
        Sample.this.startActivity(paramAnonymousView);
      }
    });
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\keyes\youtube\Sample.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */