package com.google.zxing.client.android;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultPointCallback;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

final class DecodeThread
  extends Thread
{
  public static final String BARCODE_BITMAP = "barcode_bitmap";
  public static final String BARCODE_SCALED_FACTOR = "barcode_scaled_factor";
  private final CaptureActivity activity;
  private Handler handler;
  private final CountDownLatch handlerInitLatch;
  private final Map<DecodeHintType, Object> hints;
  
  DecodeThread(CaptureActivity paramCaptureActivity, Collection<BarcodeFormat> paramCollection, Map<DecodeHintType, ?> paramMap, String paramString, ResultPointCallback paramResultPointCallback)
  {
    this.activity = paramCaptureActivity;
    this.handlerInitLatch = new CountDownLatch(1);
    this.hints = new EnumMap(DecodeHintType.class);
    if (paramMap != null) {
      this.hints.putAll(paramMap);
    }
    if (paramCollection != null)
    {
      paramMap = paramCollection;
      if (!paramCollection.isEmpty()) {}
    }
    else
    {
      paramCollection = PreferenceManager.getDefaultSharedPreferences(paramCaptureActivity);
      paramCaptureActivity = EnumSet.noneOf(BarcodeFormat.class);
      if (paramCollection.getBoolean("preferences_decode_1D_product", true)) {
        paramCaptureActivity.addAll(DecodeFormatManager.PRODUCT_FORMATS);
      }
      if (paramCollection.getBoolean("preferences_decode_1D_industrial", true)) {
        paramCaptureActivity.addAll(DecodeFormatManager.INDUSTRIAL_FORMATS);
      }
      if (paramCollection.getBoolean("preferences_decode_QR", true)) {
        paramCaptureActivity.addAll(DecodeFormatManager.QR_CODE_FORMATS);
      }
      if (paramCollection.getBoolean("preferences_decode_Data_Matrix", true)) {
        paramCaptureActivity.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
      }
      if (paramCollection.getBoolean("preferences_decode_Aztec", false)) {
        paramCaptureActivity.addAll(DecodeFormatManager.AZTEC_FORMATS);
      }
      paramMap = paramCaptureActivity;
      if (paramCollection.getBoolean("preferences_decode_PDF417", false))
      {
        paramCaptureActivity.addAll(DecodeFormatManager.PDF417_FORMATS);
        paramMap = paramCaptureActivity;
      }
    }
    this.hints.put(DecodeHintType.POSSIBLE_FORMATS, paramMap);
    if (paramString != null) {
      this.hints.put(DecodeHintType.CHARACTER_SET, paramString);
    }
    this.hints.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, paramResultPointCallback);
    Log.i("DecodeThread", "Hints: " + this.hints);
  }
  
  Handler getHandler()
  {
    try
    {
      this.handlerInitLatch.await();
      return this.handler;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
  }
  
  public void run()
  {
    Looper.prepare();
    this.handler = new DecodeHandler(this.activity, this.hints);
    this.handlerInitLatch.countDown();
    Looper.loop();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\DecodeThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */