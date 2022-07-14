package com.google.zxing.client.android.encode;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import barcodescanner.xservices.nl.barcodescanner.R.id;
import barcodescanner.xservices.nl.barcodescanner.R.layout;
import barcodescanner.xservices.nl.barcodescanner.R.menu;
import barcodescanner.xservices.nl.barcodescanner.R.string;
import com.google.zxing.WriterException;
import com.google.zxing.client.android.FinishListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EncodeActivity
  extends Activity
{
  private static final int MAX_BARCODE_FILENAME_LENGTH = 24;
  private static final Pattern NOT_ALPHANUMERIC = Pattern.compile("[^A-Za-z0-9]");
  private static final String TAG = EncodeActivity.class.getSimpleName();
  private static final String USE_VCARD_KEY = "USE_VCARD";
  private QRCodeEncoder qrCodeEncoder;
  
  private static CharSequence makeBarcodeFileName(CharSequence paramCharSequence)
  {
    String str = NOT_ALPHANUMERIC.matcher(paramCharSequence).replaceAll("_");
    paramCharSequence = str;
    if (str.length() > 24) {
      paramCharSequence = str.substring(0, 24);
    }
    return paramCharSequence;
  }
  
  /* Error */
  private void share()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 73	com/google/zxing/client/android/encode/EncodeActivity:qrCodeEncoder	Lcom/google/zxing/client/android/encode/QRCodeEncoder;
    //   4: astore 5
    //   6: aload 5
    //   8: ifnonnull +13 -> 21
    //   11: getstatic 26	com/google/zxing/client/android/encode/EncodeActivity:TAG	Ljava/lang/String;
    //   14: ldc 75
    //   16: invokestatic 81	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   19: pop
    //   20: return
    //   21: aload 5
    //   23: invokevirtual 86	com/google/zxing/client/android/encode/QRCodeEncoder:getContents	()Ljava/lang/String;
    //   26: astore 6
    //   28: aload 6
    //   30: ifnonnull +13 -> 43
    //   33: getstatic 26	com/google/zxing/client/android/encode/EncodeActivity:TAG	Ljava/lang/String;
    //   36: ldc 75
    //   38: invokestatic 81	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   41: pop
    //   42: return
    //   43: aload 5
    //   45: invokevirtual 90	com/google/zxing/client/android/encode/QRCodeEncoder:encodeAsBitmap	()Landroid/graphics/Bitmap;
    //   48: astore 7
    //   50: aload 7
    //   52: ifnull -32 -> 20
    //   55: new 92	java/io/File
    //   58: dup
    //   59: new 92	java/io/File
    //   62: dup
    //   63: invokestatic 98	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   66: ldc 100
    //   68: invokespecial 103	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   71: ldc 105
    //   73: invokespecial 103	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   76: astore_1
    //   77: aload_1
    //   78: invokevirtual 109	java/io/File:exists	()Z
    //   81: ifne +54 -> 135
    //   84: aload_1
    //   85: invokevirtual 112	java/io/File:mkdirs	()Z
    //   88: ifne +47 -> 135
    //   91: getstatic 26	com/google/zxing/client/android/encode/EncodeActivity:TAG	Ljava/lang/String;
    //   94: new 114	java/lang/StringBuilder
    //   97: dup
    //   98: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   101: ldc 117
    //   103: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: aload_1
    //   107: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   110: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   113: invokestatic 81	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   116: pop
    //   117: aload_0
    //   118: getstatic 132	barcodescanner/xservices/nl/barcodescanner/R$string:msg_unmount_usb	I
    //   121: invokespecial 136	com/google/zxing/client/android/encode/EncodeActivity:showErrorMessage	(I)V
    //   124: return
    //   125: astore_1
    //   126: getstatic 26	com/google/zxing/client/android/encode/EncodeActivity:TAG	Ljava/lang/String;
    //   129: aload_1
    //   130: invokestatic 139	android/util/Log:w	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   133: pop
    //   134: return
    //   135: new 92	java/io/File
    //   138: dup
    //   139: aload_1
    //   140: new 114	java/lang/StringBuilder
    //   143: dup
    //   144: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   147: aload 6
    //   149: invokestatic 141	com/google/zxing/client/android/encode/EncodeActivity:makeBarcodeFileName	(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;
    //   152: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   155: ldc -113
    //   157: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   163: invokespecial 103	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   166: astore 4
    //   168: aload 4
    //   170: invokevirtual 146	java/io/File:delete	()Z
    //   173: ifne +30 -> 203
    //   176: getstatic 26	com/google/zxing/client/android/encode/EncodeActivity:TAG	Ljava/lang/String;
    //   179: new 114	java/lang/StringBuilder
    //   182: dup
    //   183: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   186: ldc -108
    //   188: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: aload 4
    //   193: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   196: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   199: invokestatic 81	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   202: pop
    //   203: aconst_null
    //   204: astore_1
    //   205: aconst_null
    //   206: astore_3
    //   207: new 150	java/io/FileOutputStream
    //   210: dup
    //   211: aload 4
    //   213: invokespecial 153	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   216: astore_2
    //   217: aload 7
    //   219: getstatic 159	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
    //   222: iconst_0
    //   223: aload_2
    //   224: invokevirtual 165	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   227: pop
    //   228: aload_2
    //   229: ifnull +7 -> 236
    //   232: aload_2
    //   233: invokevirtual 168	java/io/FileOutputStream:close	()V
    //   236: new 170	android/content/Intent
    //   239: dup
    //   240: ldc -84
    //   242: ldc -82
    //   244: invokestatic 180	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   247: invokespecial 183	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   250: astore_1
    //   251: aload_1
    //   252: ldc -71
    //   254: new 114	java/lang/StringBuilder
    //   257: dup
    //   258: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   261: aload_0
    //   262: getstatic 188	barcodescanner/xservices/nl/barcodescanner/R$string:app_name	I
    //   265: invokevirtual 192	com/google/zxing/client/android/encode/EncodeActivity:getString	(I)Ljava/lang/String;
    //   268: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: ldc -62
    //   273: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   276: aload 5
    //   278: invokevirtual 197	com/google/zxing/client/android/encode/QRCodeEncoder:getTitle	()Ljava/lang/String;
    //   281: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   284: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   287: invokevirtual 201	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   290: pop
    //   291: aload_1
    //   292: ldc -53
    //   294: aload 6
    //   296: invokevirtual 201	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   299: pop
    //   300: aload_1
    //   301: ldc -51
    //   303: new 114	java/lang/StringBuilder
    //   306: dup
    //   307: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   310: ldc -49
    //   312: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   315: aload 4
    //   317: invokevirtual 210	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   320: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   323: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   326: invokestatic 180	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   329: invokevirtual 213	android/content/Intent:putExtra	(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;
    //   332: pop
    //   333: aload_1
    //   334: ldc -41
    //   336: invokevirtual 219	android/content/Intent:setType	(Ljava/lang/String;)Landroid/content/Intent;
    //   339: pop
    //   340: aload_1
    //   341: ldc -36
    //   343: invokevirtual 224	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   346: pop
    //   347: aload_0
    //   348: aload_1
    //   349: aconst_null
    //   350: invokestatic 228	android/content/Intent:createChooser	(Landroid/content/Intent;Ljava/lang/CharSequence;)Landroid/content/Intent;
    //   353: invokevirtual 232	com/google/zxing/client/android/encode/EncodeActivity:startActivity	(Landroid/content/Intent;)V
    //   356: return
    //   357: astore_1
    //   358: aload_3
    //   359: astore_2
    //   360: aload_1
    //   361: astore_3
    //   362: aload_2
    //   363: astore_1
    //   364: getstatic 26	com/google/zxing/client/android/encode/EncodeActivity:TAG	Ljava/lang/String;
    //   367: new 114	java/lang/StringBuilder
    //   370: dup
    //   371: invokespecial 115	java/lang/StringBuilder:<init>	()V
    //   374: ldc -22
    //   376: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   379: aload 4
    //   381: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   384: ldc -20
    //   386: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   389: aload_3
    //   390: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   393: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   396: invokestatic 81	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   399: pop
    //   400: aload_2
    //   401: astore_1
    //   402: aload_0
    //   403: getstatic 132	barcodescanner/xservices/nl/barcodescanner/R$string:msg_unmount_usb	I
    //   406: invokespecial 136	com/google/zxing/client/android/encode/EncodeActivity:showErrorMessage	(I)V
    //   409: aload_2
    //   410: ifnull -390 -> 20
    //   413: aload_2
    //   414: invokevirtual 168	java/io/FileOutputStream:close	()V
    //   417: return
    //   418: astore_1
    //   419: return
    //   420: astore_2
    //   421: aload_1
    //   422: ifnull +7 -> 429
    //   425: aload_1
    //   426: invokevirtual 168	java/io/FileOutputStream:close	()V
    //   429: aload_2
    //   430: athrow
    //   431: astore_1
    //   432: goto -196 -> 236
    //   435: astore_1
    //   436: goto -7 -> 429
    //   439: astore_3
    //   440: aload_2
    //   441: astore_1
    //   442: aload_3
    //   443: astore_2
    //   444: goto -23 -> 421
    //   447: astore_3
    //   448: goto -86 -> 362
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	451	0	this	EncodeActivity
    //   76	31	1	localFile1	java.io.File
    //   125	15	1	localWriterException	WriterException
    //   204	145	1	localIntent	Intent
    //   357	4	1	localFileNotFoundException1	java.io.FileNotFoundException
    //   363	39	1	localObject1	Object
    //   418	8	1	localIOException1	java.io.IOException
    //   431	1	1	localIOException2	java.io.IOException
    //   435	1	1	localIOException3	java.io.IOException
    //   441	1	1	localObject2	Object
    //   216	198	2	localObject3	Object
    //   420	21	2	localObject4	Object
    //   443	1	2	localObject5	Object
    //   206	184	3	localFileNotFoundException2	java.io.FileNotFoundException
    //   439	4	3	localObject6	Object
    //   447	1	3	localFileNotFoundException3	java.io.FileNotFoundException
    //   166	214	4	localFile2	java.io.File
    //   4	273	5	localQRCodeEncoder	QRCodeEncoder
    //   26	269	6	str	String
    //   48	170	7	localBitmap	Bitmap
    // Exception table:
    //   from	to	target	type
    //   43	50	125	com/google/zxing/WriterException
    //   207	217	357	java/io/FileNotFoundException
    //   413	417	418	java/io/IOException
    //   207	217	420	finally
    //   364	400	420	finally
    //   402	409	420	finally
    //   232	236	431	java/io/IOException
    //   425	429	435	java/io/IOException
    //   217	228	439	finally
    //   217	228	447	java/io/FileNotFoundException
  }
  
  private void showErrorMessage(int paramInt)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage(paramInt);
    localBuilder.setPositiveButton(R.string.button_ok, new FinishListener(this));
    localBuilder.setOnCancelListener(new FinishListener(this));
    localBuilder.show();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent();
    if (paramBundle == null)
    {
      finish();
      return;
    }
    paramBundle = paramBundle.getAction();
    if (("com.google.zxing.client.android.ENCODE".equals(paramBundle)) || ("android.intent.action.SEND".equals(paramBundle)))
    {
      setContentView(R.layout.encode);
      return;
    }
    finish();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(R.menu.encode, paramMenu);
    if ((this.qrCodeEncoder != null) && (this.qrCodeEncoder.isUseVCard()))
    {
      i = 1;
      if (i == 0) {
        break label99;
      }
    }
    label99:
    for (int i = R.string.menu_encode_mecard;; i = R.string.menu_encode_vcard)
    {
      MenuItem localMenuItem = paramMenu.findItem(R.id.menu_encode);
      localMenuItem.setTitle(i);
      Intent localIntent = getIntent();
      if (localIntent != null) {
        localMenuItem.setVisible("CONTACT_TYPE".equals(localIntent.getStringExtra("ENCODE_TYPE")));
      }
      return super.onCreateOptionsMenu(paramMenu);
      i = 0;
      break;
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    boolean bool = false;
    int i = paramMenuItem.getItemId();
    if (i == R.id.menu_share)
    {
      share();
      return true;
    }
    if (i == R.id.menu_encode)
    {
      paramMenuItem = getIntent();
      if (paramMenuItem == null) {
        return false;
      }
      if (!this.qrCodeEncoder.isUseVCard()) {
        bool = true;
      }
      paramMenuItem.putExtra("USE_VCARD", bool);
      paramMenuItem.addFlags(67108864);
      startActivity(paramMenuItem);
      finish();
      return true;
    }
    return false;
  }
  
  protected void onResume()
  {
    super.onResume();
    Object localObject1 = ((WindowManager)getSystemService("window")).getDefaultDisplay();
    Object localObject2 = new Point();
    ((Display)localObject1).getSize((Point)localObject2);
    int i = ((Point)localObject2).x;
    int j = ((Point)localObject2).y;
    if (i < j) {}
    for (;;)
    {
      i = i * 7 / 8;
      localObject1 = getIntent();
      if (localObject1 != null) {
        break;
      }
      return;
      i = j;
    }
    try
    {
      this.qrCodeEncoder = new QRCodeEncoder(this, (Intent)localObject1, i, ((Intent)localObject1).getBooleanExtra("USE_VCARD", false));
      localObject2 = this.qrCodeEncoder.encodeAsBitmap();
      if (localObject2 == null)
      {
        Log.w(TAG, "Could not encode barcode");
        showErrorMessage(R.string.msg_encode_contents_failed);
        this.qrCodeEncoder = null;
        return;
      }
    }
    catch (WriterException localWriterException)
    {
      Log.w(TAG, "Could not encode barcode", localWriterException);
      showErrorMessage(R.string.msg_encode_contents_failed);
      this.qrCodeEncoder = null;
      return;
    }
    ((ImageView)findViewById(R.id.image_view)).setImageBitmap((Bitmap)localObject2);
    localObject2 = (TextView)findViewById(R.id.contents_text_view);
    if (localWriterException.getBooleanExtra("ENCODE_SHOW_CONTENTS", true))
    {
      ((TextView)localObject2).setText(this.qrCodeEncoder.getDisplayContents());
      setTitle(this.qrCodeEncoder.getTitle());
      return;
    }
    ((TextView)localObject2).setText("");
    setTitle("");
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\encode\EncodeActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */