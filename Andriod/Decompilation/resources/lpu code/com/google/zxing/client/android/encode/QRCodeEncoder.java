package com.google.zxing.client.android.encode;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import barcodescanner.xservices.nl.barcodescanner.R.string;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.android.Contents;
import com.google.zxing.client.result.AddressBookParsedResult;
import com.google.zxing.client.result.ResultParser;
import com.google.zxing.common.BitMatrix;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

final class QRCodeEncoder
{
  private static final int BLACK = -16777216;
  private static final String TAG = QRCodeEncoder.class.getSimpleName();
  private static final int WHITE = -1;
  private final Context activity;
  private String contents;
  private final int dimension;
  private String displayContents;
  private BarcodeFormat format;
  private String title;
  private final boolean useVCard;
  
  QRCodeEncoder(Context paramContext, Intent paramIntent, int paramInt, boolean paramBoolean)
    throws WriterException
  {
    this.activity = paramContext;
    this.dimension = paramInt;
    this.useVCard = paramBoolean;
    paramContext = paramIntent.getAction();
    if ("com.google.zxing.client.android.ENCODE".equals(paramContext)) {
      encodeContentsFromZXingIntent(paramIntent);
    }
    while (!"android.intent.action.SEND".equals(paramContext)) {
      return;
    }
    encodeContentsFromShareIntent(paramIntent);
  }
  
  private void encodeContentsFromShareIntent(Intent paramIntent)
    throws WriterException
  {
    if (paramIntent.hasExtra("android.intent.extra.STREAM"))
    {
      encodeFromStreamExtra(paramIntent);
      return;
    }
    encodeFromTextExtras(paramIntent);
  }
  
  private void encodeContentsFromZXingIntent(Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("ENCODE_FORMAT");
    this.format = null;
    if (str != null) {}
    try
    {
      this.format = BarcodeFormat.valueOf(str);
      if ((this.format == null) || (this.format == BarcodeFormat.QR_CODE))
      {
        str = paramIntent.getStringExtra("ENCODE_TYPE");
        if ((str != null) && (!str.isEmpty()))
        {
          this.format = BarcodeFormat.QR_CODE;
          encodeQRCodeContents(paramIntent, str);
        }
      }
      do
      {
        return;
        paramIntent = paramIntent.getStringExtra("ENCODE_DATA");
      } while ((paramIntent == null) || (paramIntent.isEmpty()));
      this.contents = paramIntent;
      this.displayContents = paramIntent;
      this.title = this.activity.getString(R.string.contents_text);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;) {}
    }
  }
  
  private void encodeFromStreamExtra(Intent paramIntent)
    throws WriterException
  {
    this.format = BarcodeFormat.QR_CODE;
    paramIntent = paramIntent.getExtras();
    if (paramIntent == null) {
      throw new WriterException("No extras");
    }
    Object localObject2 = (Uri)paramIntent.getParcelable("android.intent.extra.STREAM");
    if (localObject2 == null) {
      throw new WriterException("No EXTRA_STREAM");
    }
    Object localObject1 = null;
    paramIntent = null;
    try
    {
      InputStream localInputStream = this.activity.getContentResolver().openInputStream((Uri)localObject2);
      if (localInputStream == null)
      {
        paramIntent = localInputStream;
        localObject1 = localInputStream;
        throw new WriterException("Can't open stream for " + localObject2);
      }
    }
    catch (IOException localIOException2)
    {
      localObject1 = paramIntent;
      throw new WriterException(localIOException2);
    }
    finally
    {
      if (localObject1 != null) {}
      try
      {
        ((InputStream)localObject1).close();
        throw paramIntent;
        paramIntent = localIOException2;
        localObject1 = localIOException2;
        localObject2 = new ByteArrayOutputStream();
        paramIntent = localIOException2;
        localObject1 = localIOException2;
        Object localObject3 = new byte['ࠀ'];
        for (;;)
        {
          paramIntent = localIOException2;
          localObject1 = localIOException2;
          int i = localIOException2.read((byte[])localObject3);
          if (i <= 0) {
            break;
          }
          paramIntent = localIOException2;
          localObject1 = localIOException2;
          ((ByteArrayOutputStream)localObject2).write((byte[])localObject3, 0, i);
        }
        paramIntent = localIOException2;
        localObject1 = localIOException2;
        localObject2 = ((ByteArrayOutputStream)localObject2).toByteArray();
        paramIntent = localIOException2;
        localObject1 = localIOException2;
        localObject3 = new String((byte[])localObject2, 0, localObject2.length, "UTF-8");
        if (localIOException2 != null) {}
        try
        {
          localIOException2.close();
          Log.d(TAG, "Encoding share intent content:");
          Log.d(TAG, (String)localObject3);
          paramIntent = ResultParser.parseResult(new Result((String)localObject3, (byte[])localObject2, null, BarcodeFormat.QR_CODE));
          if (!(paramIntent instanceof AddressBookParsedResult)) {
            throw new WriterException("Result was not an address");
          }
          encodeQRCodeContents((AddressBookParsedResult)paramIntent);
          if ((this.contents == null) || (this.contents.isEmpty())) {
            throw new WriterException("No content to encode");
          }
        }
        catch (IOException paramIntent)
        {
          for (;;) {}
        }
      }
      catch (IOException localIOException1)
      {
        for (;;) {}
      }
    }
  }
  
  private void encodeFromTextExtras(Intent paramIntent)
    throws WriterException
  {
    String str = ContactEncoder.trim(paramIntent.getStringExtra("android.intent.extra.TEXT"));
    Object localObject = str;
    if (str == null)
    {
      str = ContactEncoder.trim(paramIntent.getStringExtra("android.intent.extra.HTML_TEXT"));
      localObject = str;
      if (str == null)
      {
        str = ContactEncoder.trim(paramIntent.getStringExtra("android.intent.extra.SUBJECT"));
        localObject = str;
        if (str == null)
        {
          localObject = paramIntent.getStringArrayExtra("android.intent.extra.EMAIL");
          if (localObject == null) {
            break label87;
          }
        }
      }
    }
    label87:
    for (localObject = ContactEncoder.trim(localObject[0]); (localObject == null) || (((String)localObject).isEmpty()); localObject = "?") {
      throw new WriterException("Empty EXTRA_TEXT");
    }
    this.contents = ((String)localObject);
    this.format = BarcodeFormat.QR_CODE;
    if (paramIntent.hasExtra("android.intent.extra.SUBJECT")) {
      this.displayContents = paramIntent.getStringExtra("android.intent.extra.SUBJECT");
    }
    for (;;)
    {
      this.title = this.activity.getString(R.string.contents_text);
      return;
      if (paramIntent.hasExtra("android.intent.extra.TITLE")) {
        this.displayContents = paramIntent.getStringExtra("android.intent.extra.TITLE");
      } else {
        this.displayContents = this.contents;
      }
    }
  }
  
  private void encodeQRCodeContents(Intent paramIntent, String paramString)
  {
    int i = -1;
    switch (paramString.hashCode())
    {
    default: 
      switch (i)
      {
      }
      break;
    }
    label503:
    label599:
    float f1;
    float f2;
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  return;
                  if (!paramString.equals("TEXT_TYPE")) {
                    break;
                  }
                  i = 0;
                  break;
                  if (!paramString.equals("EMAIL_TYPE")) {
                    break;
                  }
                  i = 1;
                  break;
                  if (!paramString.equals("PHONE_TYPE")) {
                    break;
                  }
                  i = 2;
                  break;
                  if (!paramString.equals("SMS_TYPE")) {
                    break;
                  }
                  i = 3;
                  break;
                  if (!paramString.equals("CONTACT_TYPE")) {
                    break;
                  }
                  i = 4;
                  break;
                  if (!paramString.equals("LOCATION_TYPE")) {
                    break;
                  }
                  i = 5;
                  break;
                  paramIntent = paramIntent.getStringExtra("ENCODE_DATA");
                } while ((paramIntent == null) || (paramIntent.isEmpty()));
                this.contents = paramIntent;
                this.displayContents = paramIntent;
                this.title = this.activity.getString(R.string.contents_text);
                return;
                paramIntent = ContactEncoder.trim(paramIntent.getStringExtra("ENCODE_DATA"));
              } while (paramIntent == null);
              this.contents = ("mailto:" + paramIntent);
              this.displayContents = paramIntent;
              this.title = this.activity.getString(R.string.contents_email);
              return;
              paramIntent = ContactEncoder.trim(paramIntent.getStringExtra("ENCODE_DATA"));
            } while (paramIntent == null);
            this.contents = ("tel:" + paramIntent);
            this.displayContents = PhoneNumberUtils.formatNumber(paramIntent);
            this.title = this.activity.getString(R.string.contents_phone);
            return;
            paramIntent = ContactEncoder.trim(paramIntent.getStringExtra("ENCODE_DATA"));
          } while (paramIntent == null);
          this.contents = ("sms:" + paramIntent);
          this.displayContents = PhoneNumberUtils.formatNumber(paramIntent);
          this.title = this.activity.getString(R.string.contents_sms);
          return;
          paramString = paramIntent.getBundleExtra("ENCODE_DATA");
        } while (paramString == null);
        String str1 = paramString.getString("name");
        String str2 = paramString.getString("company");
        String str3 = paramString.getString("postal");
        List localList1 = getAllBundleValues(paramString, Contents.PHONE_KEYS);
        List localList2 = getAllBundleValues(paramString, Contents.PHONE_TYPE_KEYS);
        List localList3 = getAllBundleValues(paramString, Contents.EMAIL_KEYS);
        paramIntent = paramString.getString("URL_KEY");
        String str4;
        if (paramIntent == null)
        {
          paramIntent = null;
          str4 = paramString.getString("NOTE_KEY");
          if (!this.useVCard) {
            break label599;
          }
        }
        for (paramString = new VCardContactEncoder();; paramString = new MECARDContactEncoder())
        {
          paramIntent = paramString.encode(Collections.singletonList(str1), str2, Collections.singletonList(str3), localList1, localList2, localList3, paramIntent, str4);
          if (paramIntent[1].isEmpty()) {
            break;
          }
          this.contents = paramIntent[0];
          this.displayContents = paramIntent[1];
          this.title = this.activity.getString(R.string.contents_contact);
          return;
          paramIntent = Collections.singletonList(paramIntent);
          break label503;
        }
        paramIntent = paramIntent.getBundleExtra("ENCODE_DATA");
      } while (paramIntent == null);
      f1 = paramIntent.getFloat("LAT", Float.MAX_VALUE);
      f2 = paramIntent.getFloat("LONG", Float.MAX_VALUE);
    } while ((f1 == Float.MAX_VALUE) || (f2 == Float.MAX_VALUE));
    this.contents = ("geo:" + f1 + ',' + f2);
    this.displayContents = (f1 + "," + f2);
    this.title = this.activity.getString(R.string.contents_location);
  }
  
  private void encodeQRCodeContents(AddressBookParsedResult paramAddressBookParsedResult)
  {
    if (this.useVCard) {}
    for (Object localObject = new VCardContactEncoder();; localObject = new MECARDContactEncoder())
    {
      paramAddressBookParsedResult = ((ContactEncoder)localObject).encode(toList(paramAddressBookParsedResult.getNames()), paramAddressBookParsedResult.getOrg(), toList(paramAddressBookParsedResult.getAddresses()), toList(paramAddressBookParsedResult.getPhoneNumbers()), null, toList(paramAddressBookParsedResult.getEmails()), toList(paramAddressBookParsedResult.getURLs()), null);
      if (!paramAddressBookParsedResult[1].isEmpty())
      {
        this.contents = paramAddressBookParsedResult[0];
        this.displayContents = paramAddressBookParsedResult[1];
        this.title = this.activity.getString(R.string.contents_contact);
      }
      return;
    }
  }
  
  private static List<String> getAllBundleValues(Bundle paramBundle, String[] paramArrayOfString)
  {
    ArrayList localArrayList = new ArrayList(paramArrayOfString.length);
    int j = paramArrayOfString.length;
    int i = 0;
    if (i < j)
    {
      Object localObject = paramBundle.get(paramArrayOfString[i]);
      if (localObject == null) {}
      for (localObject = null;; localObject = localObject.toString())
      {
        localArrayList.add(localObject);
        i += 1;
        break;
      }
    }
    return localArrayList;
  }
  
  private static String guessAppropriateEncoding(CharSequence paramCharSequence)
  {
    int i = 0;
    while (i < paramCharSequence.length())
    {
      if (paramCharSequence.charAt(i) > 'ÿ') {
        return "UTF-8";
      }
      i += 1;
    }
    return null;
  }
  
  private static List<String> toList(String[] paramArrayOfString)
  {
    if (paramArrayOfString == null) {
      return null;
    }
    return Arrays.asList(paramArrayOfString);
  }
  
  Bitmap encodeAsBitmap()
    throws WriterException
  {
    Object localObject2 = this.contents;
    if (localObject2 == null) {
      return null;
    }
    Object localObject1 = null;
    String str = guessAppropriateEncoding((CharSequence)localObject2);
    if (str != null)
    {
      localObject1 = new EnumMap(EncodeHintType.class);
      ((Map)localObject1).put(EncodeHintType.CHARACTER_SET, str);
    }
    int m;
    int n;
    for (;;)
    {
      int i;
      try
      {
        localObject2 = new MultiFormatWriter().encode((String)localObject2, this.format, this.dimension, this.dimension, (Map)localObject1);
        m = ((BitMatrix)localObject2).getWidth();
        n = ((BitMatrix)localObject2).getHeight();
        localObject1 = new int[m * n];
        i = 0;
        if (i >= n) {
          break;
        }
        int j = 0;
        if (j >= m) {
          break label159;
        }
        if (((BitMatrix)localObject2).get(j, i))
        {
          k = -16777216;
          localObject1[(i * m + j)] = k;
          j += 1;
          continue;
        }
        int k = -1;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        return null;
      }
      continue;
      label159:
      i += 1;
    }
    localObject2 = Bitmap.createBitmap(m, n, Bitmap.Config.ARGB_8888);
    ((Bitmap)localObject2).setPixels(localIllegalArgumentException, 0, m, 0, 0, m, n);
    return (Bitmap)localObject2;
  }
  
  String getContents()
  {
    return this.contents;
  }
  
  String getDisplayContents()
  {
    return this.displayContents;
  }
  
  String getTitle()
  {
    return this.title;
  }
  
  boolean isUseVCard()
  {
    return this.useVCard;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\encode\QRCodeEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */