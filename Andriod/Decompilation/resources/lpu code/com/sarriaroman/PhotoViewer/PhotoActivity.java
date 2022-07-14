package com.sarriaroman.PhotoViewer;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.Builder;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.UrlConnectionDownloader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoActivity
  extends Activity
{
  public static JSONArray mArgs = null;
  private ImageButton closeBtn;
  private ProgressBar loadingBar;
  private PhotoViewAttacher mAttacher;
  private JSONObject mHeaders;
  private String mImage;
  private boolean mShare;
  private File mTempImage;
  private String mTitle;
  private JSONObject pOptions;
  private ImageView photo;
  private ImageButton shareBtn;
  private int shareBtnVisibility;
  private TextView titleTxt;
  
  private void findViews()
  {
    this.closeBtn = ((ImageButton)findViewById(getApplication().getResources().getIdentifier("closeBtn", "id", getApplication().getPackageName())));
    this.shareBtn = ((ImageButton)findViewById(getApplication().getResources().getIdentifier("shareBtn", "id", getApplication().getPackageName())));
    this.loadingBar = ((ProgressBar)findViewById(getApplication().getResources().getIdentifier("loadingBar", "id", getApplication().getPackageName())));
    this.photo = ((ImageView)findViewById(getApplication().getResources().getIdentifier("photoView", "id", getApplication().getPackageName())));
    this.mAttacher = new PhotoViewAttacher(this.photo);
    this.titleTxt = ((TextView)findViewById(getApplication().getResources().getIdentifier("titleTxt", "id", getApplication().getPackageName())));
  }
  
  private Activity getActivity()
  {
    return this;
  }
  
  private Picasso getImageLoader(Context paramContext)
  {
    Picasso.Builder localBuilder = new Picasso.Builder(paramContext);
    localBuilder.downloader(new UrlConnectionDownloader(paramContext)
    {
      protected HttpURLConnection openConnection(Uri paramAnonymousUri)
        throws IOException
      {
        paramAnonymousUri = super.openConnection(paramAnonymousUri);
        Iterator localIterator = PhotoActivity.this.mHeaders.keys();
        try
        {
          while (localIterator.hasNext())
          {
            String str = (String)localIterator.next();
            paramAnonymousUri.setRequestProperty(str, PhotoActivity.this.mHeaders.getString(str));
          }
          return paramAnonymousUri;
        }
        catch (JSONException localJSONException)
        {
          ThrowableExtension.printStackTrace(localJSONException);
        }
      }
    });
    return localBuilder.build();
  }
  
  private void hideLoadingAndUpdate()
  {
    this.photo.setVisibility(0);
    this.loadingBar.setVisibility(4);
    this.shareBtn.setVisibility(this.shareBtnVisibility);
    this.mAttacher.update();
  }
  
  private void loadImage()
    throws JSONException
  {
    if ((this.mImage.startsWith("http")) || (this.mImage.startsWith("file")))
    {
      if (this.mHeaders == null) {}
      for (Picasso localPicasso = Picasso.with(this);; localPicasso = getImageLoader(this))
      {
        setOptions(localPicasso.load(this.mImage)).into(this.photo, new Callback()
        {
          public void onError()
          {
            Toast.makeText(PhotoActivity.this.getActivity(), "Error loading image.", 1).show();
            PhotoActivity.this.finish();
          }
          
          public void onSuccess()
          {
            PhotoActivity.this.hideLoadingAndUpdate();
          }
        });
        return;
      }
    }
    if (this.mImage.startsWith("data:image"))
    {
      new AsyncTask()
      {
        protected File doInBackground(Void... paramAnonymousVarArgs)
        {
          paramAnonymousVarArgs = PhotoActivity.this.mImage.substring(PhotoActivity.this.mImage.indexOf(",") + 1);
          return PhotoActivity.this.getLocalBitmapFileFromString(paramAnonymousVarArgs);
        }
        
        protected void onPostExecute(File paramAnonymousFile)
        {
          PhotoActivity.access$002(PhotoActivity.this, paramAnonymousFile);
          paramAnonymousFile = Picasso.with(PhotoActivity.this);
          try
          {
            PhotoActivity.this.setOptions(paramAnonymousFile.load(PhotoActivity.this.mTempImage)).into(PhotoActivity.this.photo, new Callback()
            {
              public void onError()
              {
                Toast.makeText(PhotoActivity.this.getActivity(), "Error loading image.", 1).show();
                PhotoActivity.this.finish();
              }
              
              public void onSuccess()
              {
                PhotoActivity.this.hideLoadingAndUpdate();
              }
            });
            return;
          }
          catch (JSONException paramAnonymousFile)
          {
            ThrowableExtension.printStackTrace(paramAnonymousFile);
          }
        }
      }.execute(new Void[0]);
      return;
    }
    this.photo.setImageURI(Uri.parse(this.mImage));
    hideLoadingAndUpdate();
  }
  
  private JSONObject parseHeaders(String paramString)
  {
    Object localObject = null;
    if ((paramString == null) || (paramString.length() == 0)) {
      return null;
    }
    try
    {
      paramString = new JSONObject(paramString);
      return paramString;
    }
    catch (JSONException paramString)
    {
      for (;;)
      {
        ThrowableExtension.printStackTrace(paramString);
        paramString = (String)localObject;
      }
    }
  }
  
  private RequestCreator setOptions(RequestCreator paramRequestCreator)
    throws JSONException
  {
    if ((this.pOptions.has("fit")) && (this.pOptions.optBoolean("fit"))) {
      paramRequestCreator.fit();
    }
    if ((this.pOptions.has("centerInside")) && (this.pOptions.optBoolean("centerInside"))) {
      paramRequestCreator.centerInside();
    }
    if ((this.pOptions.has("centerCrop")) && (this.pOptions.optBoolean("centerCrop"))) {
      paramRequestCreator.centerCrop();
    }
    return paramRequestCreator;
  }
  
  public File getLocalBitmapFileFromString(String paramString)
  {
    try
    {
      File localFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
      localFile.getParentFile().mkdirs();
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
      localFileOutputStream.write(Base64.decode(paramString, 0));
      localFileOutputStream.close();
      return localFile;
    }
    catch (IOException paramString)
    {
      ThrowableExtension.printStackTrace(paramString);
    }
    return null;
  }
  
  public File getLocalBitmapFileFromView(ImageView paramImageView)
  {
    if ((paramImageView.getDrawable() instanceof BitmapDrawable)) {
      paramImageView = ((BitmapDrawable)paramImageView.getDrawable()).getBitmap();
    }
    try
    {
      File localFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
      localFile.getParentFile().mkdirs();
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
      paramImageView.compress(Bitmap.CompressFormat.PNG, 90, localFileOutputStream);
      localFileOutputStream.close();
      return localFile;
    }
    catch (IOException paramImageView)
    {
      ThrowableExtension.printStackTrace(paramImageView);
    }
    return null;
    return null;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    i = 0;
    super.onCreate(paramBundle);
    setContentView(getApplication().getResources().getIdentifier("activity_photo", "layout", getApplication().getPackageName()));
    findViews();
    try
    {
      this.mImage = mArgs.getString(0);
      this.mTitle = mArgs.getString(1);
      this.mShare = mArgs.getBoolean(2);
      this.mHeaders = parseHeaders(mArgs.optString(5));
      this.pOptions = mArgs.optJSONObject(6);
      if (this.pOptions == null)
      {
        this.pOptions = new JSONObject();
        this.pOptions.put("fit", true);
        this.pOptions.put("centerInside", true);
        this.pOptions.put("centerCrop", false);
      }
      if (!this.mShare) {
        break label232;
      }
    }
    catch (JSONException paramBundle)
    {
      try
      {
        for (;;)
        {
          loadImage();
          this.closeBtn.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              PhotoActivity.this.finish();
            }
          });
          this.shareBtn.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              if (Build.VERSION.SDK_INT >= 24) {}
              try
              {
                StrictMode.class.getMethod("disableDeathOnFileUriExposure", new Class[0]).invoke(null, new Object[0]);
                if (PhotoActivity.this.mTempImage == null) {
                  PhotoActivity.access$002(PhotoActivity.this, PhotoActivity.this.getLocalBitmapFileFromView(PhotoActivity.this.photo));
                }
                paramAnonymousView = Uri.fromFile(PhotoActivity.this.mTempImage);
                if (paramAnonymousView != null)
                {
                  Intent localIntent = new Intent("android.intent.action.SEND");
                  localIntent.setType("image/*");
                  localIntent.putExtra("android.intent.extra.STREAM", paramAnonymousView);
                  PhotoActivity.this.startActivity(Intent.createChooser(localIntent, "Share"));
                }
                return;
              }
              catch (Exception paramAnonymousView)
              {
                for (;;)
                {
                  ThrowableExtension.printStackTrace(paramAnonymousView);
                }
              }
            }
          });
          return;
          i = 4;
        }
        paramBundle = paramBundle;
        this.shareBtnVisibility = 4;
      }
      catch (JSONException paramBundle)
      {
        for (;;)
        {
          ThrowableExtension.printStackTrace(paramBundle);
        }
      }
    }
    this.shareBtnVisibility = i;
    this.shareBtn.setVisibility(this.shareBtnVisibility);
    if (!this.mTitle.equals("")) {
      this.titleTxt.setText(this.mTitle);
    }
  }
  
  public void onDestroy()
  {
    if (this.mTempImage != null) {
      this.mTempImage.delete();
    }
    super.onDestroy();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\sarriaroman\PhotoViewer\PhotoActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */