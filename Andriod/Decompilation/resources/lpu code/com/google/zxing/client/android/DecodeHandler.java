package com.google.zxing.client.android;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import barcodescanner.xservices.nl.barcodescanner.R.id;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.ReaderException;
import com.google.zxing.client.android.camera.CameraManager;
import com.google.zxing.common.HybridBinarizer;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

final class DecodeHandler
  extends Handler
{
  private static final String TAG = DecodeHandler.class.getSimpleName();
  private final CaptureActivity activity;
  private int frameCount;
  private final MultiFormatReader multiFormatReader = new MultiFormatReader();
  private boolean running = true;
  
  DecodeHandler(CaptureActivity paramCaptureActivity, Map<DecodeHintType, Object> paramMap)
  {
    this.multiFormatReader.setHints(paramMap);
    this.activity = paramCaptureActivity;
  }
  
  private static void YUV_NV21_TO_RGB(int[] paramArrayOfInt, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i5 = paramInt1 * paramInt2;
    int i = 0;
    int i1 = 0;
    int n = 0;
    while (i1 < paramInt2)
    {
      int i3 = 0;
      int i2 = 0;
      if (i3 < paramInt1)
      {
        int k = paramArrayOfByte[(n * paramInt1 + i2)] & 0xFF;
        int m = paramArrayOfByte[((n >> 1) * paramInt1 + i5 + (i2 & 0xFFFFFFFE) + 0)] & 0xFF;
        int i4 = paramArrayOfByte[((n >> 1) * paramInt1 + i5 + (i2 & 0xFFFFFFFE) + 1)] & 0xFF;
        int j = k;
        if (k < 16) {
          j = 16;
        }
        j = (j - 16) * 1192;
        k = j + (m - 128) * 1634 >> 10;
        m = j - (m - 128) * 832 - (i4 - 128) * 400 >> 10;
        i4 = j + (i4 - 128) * 2066 >> 10;
        if (k < 0)
        {
          j = 0;
          label196:
          if (m >= 0) {
            break label273;
          }
          k = 0;
          label204:
          if (i4 >= 0) {
            break label293;
          }
          m = 0;
        }
        for (;;)
        {
          paramArrayOfInt[i] = (j << 16 | 0x0 | k << 8 | m);
          i3 += 1;
          i2 += 1;
          i += 1;
          break;
          j = k;
          if (k <= 255) {
            break label196;
          }
          j = 255;
          break label196;
          label273:
          k = m;
          if (m <= 255) {
            break label204;
          }
          k = 255;
          break label204;
          label293:
          m = i4;
          if (i4 > 255) {
            m = 255;
          }
        }
      }
      i1 += 1;
      n += 1;
    }
  }
  
  private static void bundleThumbnail(PlanarYUVLuminanceSource paramPlanarYUVLuminanceSource, Bundle paramBundle)
  {
    Object localObject = paramPlanarYUVLuminanceSource.renderThumbnail();
    int i = paramPlanarYUVLuminanceSource.getThumbnailWidth();
    localObject = Bitmap.createBitmap((int[])localObject, 0, i, i, paramPlanarYUVLuminanceSource.getThumbnailHeight(), Bitmap.Config.ARGB_8888);
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    ((Bitmap)localObject).compress(Bitmap.CompressFormat.JPEG, 50, localByteArrayOutputStream);
    paramBundle.putByteArray("barcode_bitmap", localByteArrayOutputStream.toByteArray());
    paramBundle.putFloat("barcode_scaled_factor", i / paramPlanarYUVLuminanceSource.getWidth());
  }
  
  private void decode(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    long l1 = System.currentTimeMillis();
    Object localObject1 = null;
    if (this.frameCount == 3)
    {
      this.frameCount = 0;
      localObject2 = new int[paramInt1 * paramInt2];
      YUV_NV21_TO_RGB((int[])localObject2, paramArrayOfByte, paramInt1, paramInt2);
      int i = 0;
      while (i < localObject2.length)
      {
        localObject2[i] = (16777215 - localObject2[i]);
        i += 1;
      }
      encodeYUV420SP(paramArrayOfByte, (int[])localObject2, paramInt1, paramInt2);
    }
    this.frameCount += 1;
    Object localObject2 = this.activity.getCameraManager().buildLuminanceSource(paramArrayOfByte, paramInt1, paramInt2);
    paramArrayOfByte = (byte[])localObject1;
    if (localObject2 != null) {
      paramArrayOfByte = new BinaryBitmap(new HybridBinarizer((LuminanceSource)localObject2));
    }
    try
    {
      paramArrayOfByte = this.multiFormatReader.decodeWithState(paramArrayOfByte);
      this.multiFormatReader.reset();
    }
    catch (ReaderException paramArrayOfByte)
    {
      for (;;)
      {
        paramArrayOfByte = paramArrayOfByte;
        this.multiFormatReader.reset();
        paramArrayOfByte = (byte[])localObject1;
      }
    }
    finally
    {
      paramArrayOfByte = finally;
      this.multiFormatReader.reset();
      throw paramArrayOfByte;
    }
    localObject1 = this.activity.getHandler();
    if (paramArrayOfByte != null)
    {
      l2 = System.currentTimeMillis();
      Log.d(TAG, "Found barcode in " + (l2 - l1) + " ms");
      if (localObject1 != null)
      {
        paramArrayOfByte = Message.obtain((Handler)localObject1, R.id.decode_succeeded, paramArrayOfByte);
        localObject1 = new Bundle();
        bundleThumbnail((PlanarYUVLuminanceSource)localObject2, (Bundle)localObject1);
        paramArrayOfByte.setData((Bundle)localObject1);
        paramArrayOfByte.sendToTarget();
      }
    }
    while (localObject1 == null)
    {
      long l2;
      return;
    }
    Message.obtain((Handler)localObject1, R.id.decode_failed).sendToTarget();
  }
  
  void encodeYUV420SP(byte[] paramArrayOfByte, int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int m = paramInt1 * paramInt2;
    int i = 0;
    int j = m;
    int k = m + (paramArrayOfByte.length - m) / 2;
    System.out.println(paramArrayOfByte.length + " " + m);
    int i1 = 0;
    int n = 0;
    int i2;
    label84:
    int i3;
    int i5;
    int i4;
    if (n < paramInt2)
    {
      i2 = 0;
      m = k;
      k = i;
      i = m;
      if (i2 < paramInt1)
      {
        m = paramArrayOfInt[i1];
        m = (paramArrayOfInt[i1] & 0xFF0000) >> 16;
        i3 = (paramArrayOfInt[i1] & 0xFF00) >> 8;
        int i6 = (paramArrayOfInt[i1] & 0xFF) >> 0;
        i5 = (m * 66 + i3 * 129 + i6 * 25 + 128 >> 8) + 16;
        i4 = (m * -38 - i3 * 74 + i6 * 112 + 128 >> 8) + 128;
        i3 = (m * 112 - i3 * 94 - i6 * 18 + 128 >> 8) + 128;
        if (i5 < 0)
        {
          m = 0;
          label230:
          paramArrayOfByte[k] = ((byte)m);
          if ((n % 2 != 0) || (i1 % 2 != 0)) {
            break label416;
          }
          i5 = j + 1;
          if (i4 >= 0) {
            break label354;
          }
          m = 0;
          label265:
          paramArrayOfByte[j] = ((byte)m);
          m = i + 1;
          if (i3 >= 0) {
            break label374;
          }
          j = 0;
          label286:
          paramArrayOfByte[i] = ((byte)j);
          j = m;
          i = i5;
        }
      }
    }
    for (;;)
    {
      i1 += 1;
      i2 += 1;
      m = j;
      k += 1;
      j = i;
      i = m;
      break label84;
      m = i5;
      if (i5 <= 255) {
        break label230;
      }
      m = 255;
      break label230;
      label354:
      m = i4;
      if (i4 <= 255) {
        break label265;
      }
      m = 255;
      break label265;
      label374:
      j = i3;
      if (i3 <= 255) {
        break label286;
      }
      j = 255;
      break label286;
      n += 1;
      m = k;
      k = i;
      i = m;
      break;
      return;
      label416:
      m = i;
      i = j;
      j = m;
    }
  }
  
  public void handleMessage(Message paramMessage)
  {
    if (!this.running) {}
    do
    {
      return;
      if (paramMessage.what == R.id.decode)
      {
        decode((byte[])paramMessage.obj, paramMessage.arg1, paramMessage.arg2);
        return;
      }
    } while (paramMessage.what != R.id.quit);
    this.running = false;
    Looper.myLooper().quit();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\DecodeHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */