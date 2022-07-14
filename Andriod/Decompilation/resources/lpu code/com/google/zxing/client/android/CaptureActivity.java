package com.google.zxing.client.android;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import barcodescanner.xservices.nl.barcodescanner.R.color;
import barcodescanner.xservices.nl.barcodescanner.R.drawable;
import barcodescanner.xservices.nl.barcodescanner.R.id;
import barcodescanner.xservices.nl.barcodescanner.R.layout;
import barcodescanner.xservices.nl.barcodescanner.R.string;
import barcodescanner.xservices.nl.barcodescanner.R.xml;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.camera.CameraManager;
import com.google.zxing.client.android.clipboard.ClipboardInterface;
import com.google.zxing.client.android.history.HistoryActivity;
import com.google.zxing.client.android.history.HistoryItem;
import com.google.zxing.client.android.history.HistoryManager;
import com.google.zxing.client.android.result.ResultButtonListener;
import com.google.zxing.client.android.result.ResultHandler;
import com.google.zxing.client.android.result.ResultHandlerFactory;
import com.google.zxing.client.android.result.supplement.SupplementalInfoRetriever;
import com.google.zxing.client.result.ParsedResultType;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class CaptureActivity
  extends Activity
  implements SurfaceHolder.Callback
{
  private static final long BULK_MODE_SCAN_DELAY_MS = 1000L;
  private static final long DEFAULT_INTENT_RESULT_DURATION_MS = 1500L;
  private static final Collection<ResultMetadataType> DISPLAYABLE_METADATA_TYPES = EnumSet.of(ResultMetadataType.ISSUE_NUMBER, ResultMetadataType.SUGGESTED_PRICE, ResultMetadataType.ERROR_CORRECTION_LEVEL, ResultMetadataType.POSSIBLE_COUNTRY);
  private static final int HISTORY_REQUEST_CODE = 47820;
  private static final String TAG = CaptureActivity.class.getSimpleName();
  private static final String[] ZXING_URLS = { "http://zxing.appspot.com/scan", "zxing://scan/" };
  private AmbientLightManager ambientLightManager;
  private BeepManager beepManager;
  private boolean beepOnScan;
  private CameraManager cameraManager;
  private String characterSet;
  private boolean copyToClipboard;
  private Collection<BarcodeFormat> decodeFormats;
  private Map<DecodeHintType, ?> decodeHints;
  private Button flipButton;
  private CaptureActivityHandler handler;
  private boolean hasSurface;
  private HistoryManager historyManager;
  private InactivityTimer inactivityTimer;
  private Result lastResult;
  private View resultView;
  private Result savedResultToShow;
  private ScanFromWebPageManager scanFromWebPageManager;
  private IntentSource source;
  private String sourceUrl;
  private TextView statusView;
  BroadcastReceiver stopReceiver;
  private Button torchButton;
  private ViewfinderView viewfinderView;
  
  private void decodeOrStoreSavedBitmap(Bitmap paramBitmap, Result paramResult)
  {
    if (this.handler == null)
    {
      this.savedResultToShow = paramResult;
      return;
    }
    if (paramResult != null) {
      this.savedResultToShow = paramResult;
    }
    if (this.savedResultToShow != null)
    {
      paramBitmap = Message.obtain(this.handler, R.id.decode_succeeded, this.savedResultToShow);
      this.handler.sendMessage(paramBitmap);
    }
    this.savedResultToShow = null;
  }
  
  private void displayFrameworkBugMessageAndExit()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(getString(R.string.app_name));
    localBuilder.setMessage(getString(R.string.msg_camera_framework_bug));
    localBuilder.setPositiveButton(R.string.button_ok, new FinishListener(this));
    localBuilder.setOnCancelListener(new FinishListener(this));
    localBuilder.show();
  }
  
  private static void drawLine(Canvas paramCanvas, Paint paramPaint, ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, float paramFloat)
  {
    if ((paramResultPoint1 != null) && (paramResultPoint2 != null)) {
      paramCanvas.drawLine(paramFloat * paramResultPoint1.getX(), paramFloat * paramResultPoint1.getY(), paramFloat * paramResultPoint2.getX(), paramFloat * paramResultPoint2.getY(), paramPaint);
    }
  }
  
  private void drawResultPoints(Bitmap paramBitmap, float paramFloat, Result paramResult)
  {
    int i = 0;
    ResultPoint[] arrayOfResultPoint = paramResult.getResultPoints();
    Paint localPaint;
    if ((arrayOfResultPoint != null) && (arrayOfResultPoint.length > 0))
    {
      paramBitmap = new Canvas(paramBitmap);
      localPaint = new Paint();
      localPaint.setColor(getResources().getColor(R.color.result_points));
      if (arrayOfResultPoint.length != 2) {
        break label83;
      }
      localPaint.setStrokeWidth(4.0F);
      drawLine(paramBitmap, localPaint, arrayOfResultPoint[0], arrayOfResultPoint[1], paramFloat);
    }
    for (;;)
    {
      return;
      label83:
      if ((arrayOfResultPoint.length == 4) && ((paramResult.getBarcodeFormat() == BarcodeFormat.UPC_A) || (paramResult.getBarcodeFormat() == BarcodeFormat.EAN_13)))
      {
        drawLine(paramBitmap, localPaint, arrayOfResultPoint[0], arrayOfResultPoint[1], paramFloat);
        drawLine(paramBitmap, localPaint, arrayOfResultPoint[2], arrayOfResultPoint[3], paramFloat);
        return;
      }
      localPaint.setStrokeWidth(10.0F);
      int j = arrayOfResultPoint.length;
      while (i < j)
      {
        paramResult = arrayOfResultPoint[i];
        if (paramResult != null) {
          paramBitmap.drawPoint(paramResult.getX() * paramFloat, paramResult.getY() * paramFloat, localPaint);
        }
        i += 1;
      }
    }
  }
  
  private int getCurrentOrientation()
  {
    int i = getWindowManager().getDefaultDisplay().getRotation();
    if (getResources().getConfiguration().orientation == 2)
    {
      switch (i)
      {
      default: 
        return 8;
      }
      return 0;
    }
    switch (i)
    {
    case 1: 
    case 2: 
    default: 
      return 9;
    }
    return 1;
  }
  
  private void handleDecodeExternally(Result paramResult, ResultHandler paramResultHandler, Bitmap paramBitmap)
  {
    if (paramBitmap != null) {
      this.viewfinderView.drawResultBitmap(paramBitmap);
    }
    long l2 = 1500L;
    long l1 = l2;
    if (getIntent() != null)
    {
      l1 = l2;
      if (getIntent().hasExtra("RESULT_DISPLAY_DURATION_MS"))
      {
        paramBitmap = getIntent().getStringExtra("RESULT_DISPLAY_DURATION_MS");
        l1 = l2;
        if (paramBitmap == null) {}
      }
    }
    do
    {
      int i;
      try
      {
        l1 = Long.parseLong(paramBitmap);
        if (l1 > 0L)
        {
          String str = String.valueOf(paramResult);
          paramBitmap = str;
          if (str.length() > 32) {
            paramBitmap = str.substring(0, 32) + " ...";
          }
          this.statusView.setText(getString(paramResultHandler.getDisplayTitle()) + " : " + paramBitmap);
        }
        if ((this.copyToClipboard) && (!paramResultHandler.areContentsSecure())) {
          ClipboardInterface.setText(paramResultHandler.getDisplayContents(), this);
        }
        if (this.source == IntentSource.NATIVE_APP_INTENT)
        {
          paramResultHandler = new Intent(getIntent().getAction());
          paramResultHandler.addFlags(524288);
          paramResultHandler.putExtra("SCAN_RESULT", paramResult.toString());
          paramResultHandler.putExtra("SCAN_RESULT_FORMAT", paramResult.getBarcodeFormat().toString());
          paramBitmap = paramResult.getRawBytes();
          if ((paramBitmap != null) && (paramBitmap.length > 0)) {
            paramResultHandler.putExtra("SCAN_RESULT_BYTES", paramBitmap);
          }
          paramResult = paramResult.getResultMetadata();
          if (paramResult != null)
          {
            if (paramResult.containsKey(ResultMetadataType.UPC_EAN_EXTENSION)) {
              paramResultHandler.putExtra("SCAN_RESULT_UPC_EAN_EXTENSION", paramResult.get(ResultMetadataType.UPC_EAN_EXTENSION).toString());
            }
            paramBitmap = (Number)paramResult.get(ResultMetadataType.ORIENTATION);
            if (paramBitmap != null) {
              paramResultHandler.putExtra("SCAN_RESULT_ORIENTATION", paramBitmap.intValue());
            }
            paramBitmap = (String)paramResult.get(ResultMetadataType.ERROR_CORRECTION_LEVEL);
            if (paramBitmap != null) {
              paramResultHandler.putExtra("SCAN_RESULT_ERROR_CORRECTION_LEVEL", paramBitmap);
            }
            paramResult = (Iterable)paramResult.get(ResultMetadataType.BYTE_SEGMENTS);
            if (paramResult != null)
            {
              i = 0;
              paramResult = paramResult.iterator();
              while (paramResult.hasNext())
              {
                paramBitmap = (byte[])paramResult.next();
                paramResultHandler.putExtra("SCAN_RESULT_BYTE_SEGMENTS_" + i, paramBitmap);
                i += 1;
              }
            }
          }
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        for (;;)
        {
          Log.e(TAG, "Could not parse " + paramBitmap + " to Long", localNumberFormatException);
          l1 = l2;
        }
        sendReplyMessage(R.id.return_scan_result, paramResultHandler, l1);
        return;
      }
      if (this.source == IntentSource.PRODUCT_SEARCH_LINK)
      {
        i = this.sourceUrl.lastIndexOf("/scan");
        paramResult = this.sourceUrl.substring(0, i) + "?q=" + paramResultHandler.getDisplayContents() + "&source=zxing";
        sendReplyMessage(R.id.launch_product_query, paramResult, l1);
        return;
      }
    } while ((this.source != IntentSource.ZXING_LINK) || (this.scanFromWebPageManager == null) || (!this.scanFromWebPageManager.isScanFromWebPage()));
    paramResult = this.scanFromWebPageManager.buildReplyURL(paramResult, paramResultHandler);
    this.scanFromWebPageManager = null;
    sendReplyMessage(R.id.launch_product_query, paramResult, l1);
  }
  
  private void handleDecodeInternally(Result paramResult, ResultHandler paramResultHandler, Bitmap paramBitmap)
  {
    CharSequence localCharSequence = paramResultHandler.getDisplayContents();
    if ((this.copyToClipboard) && (!paramResultHandler.areContentsSecure())) {
      ClipboardInterface.setText(localCharSequence, this);
    }
    Object localObject1 = PreferenceManager.getDefaultSharedPreferences(this);
    if ((paramResultHandler.getDefaultButtonID() != null) && (((SharedPreferences)localObject1).getBoolean("preferences_auto_open_web", false)))
    {
      paramResultHandler.handleButtonPress(paramResultHandler.getDefaultButtonID().intValue());
      return;
    }
    this.statusView.setVisibility(8);
    this.viewfinderView.setVisibility(8);
    this.resultView.setVisibility(0);
    localObject1 = (ImageView)findViewById(R.id.barcode_image_view);
    if (paramBitmap == null) {
      ((ImageView)localObject1).setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.launcher_icon));
    }
    for (;;)
    {
      ((TextView)findViewById(R.id.format_text_view)).setText(paramResult.getBarcodeFormat().toString());
      ((TextView)findViewById(R.id.type_text_view)).setText(paramResultHandler.getType().toString());
      paramBitmap = DateFormat.getDateTimeInstance(3, 3);
      ((TextView)findViewById(R.id.time_text_view)).setText(paramBitmap.format(new Date(paramResult.getTimestamp())));
      paramBitmap = (TextView)findViewById(R.id.meta_text_view);
      localObject1 = findViewById(R.id.meta_text_view_label);
      paramBitmap.setVisibility(8);
      ((View)localObject1).setVisibility(8);
      Object localObject2 = paramResult.getResultMetadata();
      if (localObject2 == null) {
        break label366;
      }
      paramResult = new StringBuilder(20);
      localObject2 = ((Map)localObject2).entrySet().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        Map.Entry localEntry = (Map.Entry)((Iterator)localObject2).next();
        if (DISPLAYABLE_METADATA_TYPES.contains(localEntry.getKey())) {
          paramResult.append(localEntry.getValue()).append('\n');
        }
      }
      ((ImageView)localObject1).setImageBitmap(paramBitmap);
    }
    if (paramResult.length() > 0)
    {
      paramResult.setLength(paramResult.length() - 1);
      paramBitmap.setText(paramResult);
      paramBitmap.setVisibility(0);
      ((View)localObject1).setVisibility(0);
    }
    label366:
    paramResult = (TextView)findViewById(R.id.contents_text_view);
    paramResult.setText(localCharSequence);
    paramResult.setTextSize(2, Math.max(22, 32 - localCharSequence.length() / 4));
    paramResult = (TextView)findViewById(R.id.contents_supplement_text_view);
    paramResult.setText("");
    paramResult.setOnClickListener(null);
    if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("preferences_supplemental", true)) {
      SupplementalInfoRetriever.maybeInvokeRetrieval(paramResult, paramResultHandler.getResult(), this.historyManager, this);
    }
    int j = paramResultHandler.getButtonCount();
    paramResult = (ViewGroup)findViewById(R.id.result_button_view);
    paramResult.requestFocus();
    int i = 0;
    label483:
    if (i < 4)
    {
      paramBitmap = (TextView)paramResult.getChildAt(i);
      if (i >= j) {
        break label544;
      }
      paramBitmap.setVisibility(0);
      paramBitmap.setText(paramResultHandler.getButtonText(i));
      paramBitmap.setOnClickListener(new ResultButtonListener(paramResultHandler, i));
    }
    for (;;)
    {
      i += 1;
      break label483;
      break;
      label544:
      paramBitmap.setVisibility(8);
    }
  }
  
  private void initCamera(SurfaceHolder paramSurfaceHolder)
  {
    if (paramSurfaceHolder == null) {
      throw new IllegalStateException("No SurfaceHolder provided");
    }
    if (this.cameraManager.isOpen())
    {
      Log.w(TAG, "initCamera() while already open -- late SurfaceView callback?");
      return;
    }
    try
    {
      this.cameraManager.openDriver(paramSurfaceHolder);
      if (this.handler == null) {
        this.handler = new CaptureActivityHandler(this, this.decodeFormats, this.decodeHints, this.characterSet, this.cameraManager);
      }
      decodeOrStoreSavedBitmap(null, null);
      return;
    }
    catch (IOException paramSurfaceHolder)
    {
      Log.w(TAG, paramSurfaceHolder);
      displayFrameworkBugMessageAndExit();
      return;
    }
    catch (RuntimeException paramSurfaceHolder)
    {
      Log.w(TAG, "Unexpected error initializing camera", paramSurfaceHolder);
      displayFrameworkBugMessageAndExit();
    }
  }
  
  private static boolean isZXingURL(String paramString)
  {
    if (paramString == null) {}
    for (;;)
    {
      return false;
      String[] arrayOfString = ZXING_URLS;
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        if (paramString.startsWith(arrayOfString[i])) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  private void resetStatusView()
  {
    this.resultView.setVisibility(8);
    this.statusView.setText(R.string.msg_default_status);
    this.statusView.setVisibility(0);
    this.viewfinderView.setVisibility(0);
    this.lastResult = null;
    if ((getIntent().getBooleanExtra("SHOW_FLIP_CAMERA_BUTTON", false)) && (Camera.getNumberOfCameras() > 1))
    {
      this.flipButton.setVisibility(0);
      this.flipButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          int i = CaptureActivity.this.getIntent().getIntExtra("SCAN_CAMERA_ID", -1);
          paramAnonymousView = CaptureActivity.this.getIntent();
          if (i == 1) {}
          for (i = 0;; i = 1)
          {
            paramAnonymousView.putExtra("SCAN_CAMERA_ID", i);
            CaptureActivity.this.getIntent().putExtra("SHOW_FLIP_CAMERA_BUTTON", true);
            CaptureActivity.this.recreate();
            return;
          }
        }
      });
    }
    FeatureInfo[] arrayOfFeatureInfo;
    int j;
    int i;
    if ((getIntent().getBooleanExtra("SHOW_TORCH_BUTTON", false)) && (getIntent().getIntExtra("SCAN_CAMERA_ID", -1) != 1))
    {
      arrayOfFeatureInfo = getPackageManager().getSystemAvailableFeatures();
      j = arrayOfFeatureInfo.length;
      i = 0;
    }
    for (;;)
    {
      if (i < j)
      {
        if ("android.hardware.camera.flash".equalsIgnoreCase(arrayOfFeatureInfo[i].name))
        {
          this.torchButton.setVisibility(0);
          this.torchButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              paramAnonymousView = CaptureActivity.this.cameraManager;
              if (!CaptureActivity.this.cameraManager.isTorchOn()) {}
              for (boolean bool = true;; bool = false)
              {
                paramAnonymousView.setTorch(bool);
                return;
              }
            }
          });
        }
      }
      else {
        return;
      }
      i += 1;
    }
  }
  
  private void sendReplyMessage(int paramInt, Object paramObject, long paramLong)
  {
    if (this.handler != null)
    {
      paramObject = Message.obtain(this.handler, paramInt, paramObject);
      if (paramLong > 0L) {
        this.handler.sendMessageDelayed((Message)paramObject, paramLong);
      }
    }
    else
    {
      return;
    }
    this.handler.sendMessage((Message)paramObject);
  }
  
  public void drawViewfinder()
  {
    this.viewfinderView.drawViewfinder();
  }
  
  CameraManager getCameraManager()
  {
    return this.cameraManager;
  }
  
  public Handler getHandler()
  {
    return this.handler;
  }
  
  ViewfinderView getViewfinderView()
  {
    return this.viewfinderView;
  }
  
  public void handleDecode(Result paramResult, Bitmap paramBitmap, float paramFloat)
  {
    this.inactivityTimer.onActivity();
    this.lastResult = paramResult;
    ResultHandler localResultHandler = ResultHandlerFactory.makeResultHandler(this, paramResult);
    if (paramBitmap != null) {}
    for (int i = 1;; i = 0)
    {
      if (i != 0)
      {
        this.historyManager.addHistoryItem(paramResult, localResultHandler);
        if (this.beepOnScan) {
          this.beepManager.playBeepSoundAndVibrate();
        }
        drawResultPoints(paramBitmap, paramFloat, paramResult);
      }
      switch (this.source)
      {
      default: 
        return;
      }
    }
    if ((i != 0) && (getIntent().getBooleanExtra("BULK_SCAN", false)))
    {
      paramBitmap = new Intent("bulk-barcode-result");
      paramBitmap.putExtra("SCAN_RESULT", paramResult.toString());
      paramBitmap.putExtra("SCAN_RESULT_FORMAT", paramResult.getBarcodeFormat().toString());
      LocalBroadcastManager.getInstance(this).sendBroadcast(paramBitmap);
      restartPreviewAfterDelay(1000L);
      return;
    }
    handleDecodeExternally(paramResult, localResultHandler, paramBitmap);
    return;
    if ((this.scanFromWebPageManager == null) || (!this.scanFromWebPageManager.isScanFromWebPage()))
    {
      handleDecodeInternally(paramResult, localResultHandler, paramBitmap);
      return;
    }
    handleDecodeExternally(paramResult, localResultHandler, paramBitmap);
    return;
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    if ((i != 0) && (localSharedPreferences.getBoolean("preferences_bulk_mode", false)))
    {
      Toast.makeText(getApplicationContext(), getResources().getString(R.string.msg_bulk_mode_scanned) + " (" + paramResult.getText() + ')', 0).show();
      restartPreviewAfterDelay(1000L);
      return;
    }
    handleDecodeInternally(paramResult, localResultHandler, paramBitmap);
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt2 == -1) && (paramInt1 == 47820) && (this.historyManager != null))
    {
      paramInt1 = paramIntent.getIntExtra("ITEM_NUMBER", -1);
      if (paramInt1 >= 0) {
        decodeOrStoreSavedBitmap(null, this.historyManager.buildHistoryItem(paramInt1).getResult());
      }
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    recreate();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getWindow().addFlags(128);
    setContentView(R.layout.capture);
    this.hasSurface = false;
    this.inactivityTimer = new InactivityTimer(this);
    this.beepManager = new BeepManager(this);
    this.ambientLightManager = new AmbientLightManager(this);
    PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    this.stopReceiver = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        CaptureActivity.this.finish();
      }
    };
    LocalBroadcastManager.getInstance(this).registerReceiver(this.stopReceiver, new IntentFilter("barcode-scanner-stop"));
  }
  
  protected void onDestroy()
  {
    this.inactivityTimer.shutdown();
    LocalBroadcastManager.getInstance(this).unregisterReceiver(this.stopReceiver);
    super.onDestroy();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = true;
    switch (paramInt)
    {
    default: 
    case 27: 
    case 80: 
    case 4: 
      do
      {
        bool = super.onKeyDown(paramInt, paramKeyEvent);
        return bool;
        if (this.source == IntentSource.NATIVE_APP_INTENT)
        {
          setResult(0);
          finish();
          return true;
        }
      } while (((this.source != IntentSource.NONE) && (this.source != IntentSource.ZXING_LINK)) || (this.lastResult == null));
      restartPreviewAfterDelay(0L);
      return true;
    case 25: 
      this.cameraManager.setTorch(false);
      return true;
    }
    this.cameraManager.setTorch(true);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.addFlags(524288);
    int i = paramMenuItem.getItemId();
    if (i == R.id.menu_history)
    {
      localIntent.setClassName(this, HistoryActivity.class.getName());
      startActivityForResult(localIntent, 47820);
    }
    for (;;)
    {
      return true;
      if (i == R.id.menu_settings)
      {
        localIntent.setClassName(this, PreferencesActivity.class.getName());
        startActivity(localIntent);
      }
      else
      {
        if (i != R.id.menu_help) {
          break;
        }
        localIntent.setClassName(this, HelpActivity.class.getName());
        startActivity(localIntent);
      }
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onPause()
  {
    if (this.handler != null)
    {
      this.handler.quitSynchronously();
      this.handler = null;
    }
    this.inactivityTimer.onPause();
    this.ambientLightManager.stop();
    this.beepManager.close();
    this.cameraManager.closeDriver();
    if (!this.hasSurface) {
      ((SurfaceView)findViewById(R.id.preview_view)).getHolder().removeCallback(this);
    }
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    this.historyManager = new HistoryManager(this);
    this.historyManager.trimHistory();
    this.cameraManager = new CameraManager(getApplication());
    this.viewfinderView = ((ViewfinderView)findViewById(R.id.viewfinder_view));
    this.viewfinderView.setCameraManager(this.cameraManager);
    this.resultView = findViewById(R.id.result_view);
    this.statusView = ((TextView)findViewById(R.id.status_view));
    this.flipButton = ((Button)findViewById(R.id.flip_button));
    this.torchButton = ((Button)findViewById(R.id.torch_button));
    this.handler = null;
    this.lastResult = null;
    Object localObject2 = PreferenceManager.getDefaultSharedPreferences(this);
    Object localObject1 = getIntent().getStringExtra("ORIENTATION_LOCK");
    boolean bool;
    label228:
    label252:
    String str;
    if ("landscape".equalsIgnoreCase((String)localObject1))
    {
      setRequestedOrientation(0);
      resetStatusView();
      this.beepManager.updatePrefs();
      this.ambientLightManager.start(this.cameraManager);
      this.inactivityTimer.onResume();
      localObject1 = getIntent();
      if ((!((SharedPreferences)localObject2).getBoolean("preferences_copy_to_clipboard", true)) || ((localObject1 != null) && (!((Intent)localObject1).getBooleanExtra("SAVE_HISTORY", true)))) {
        break label535;
      }
      bool = true;
      this.copyToClipboard = bool;
      if ((localObject1 != null) && (!((Intent)localObject1).getBooleanExtra("BEEP_ON_SCAN", true))) {
        break label540;
      }
      bool = true;
      this.beepOnScan = bool;
      this.source = IntentSource.NONE;
      this.sourceUrl = null;
      this.scanFromWebPageManager = null;
      this.decodeFormats = null;
      this.characterSet = null;
      if (localObject1 != null)
      {
        localObject2 = ((Intent)localObject1).getAction();
        str = ((Intent)localObject1).getDataString();
        if (!"com.google.zxing.client.android.SCAN".equals(localObject2)) {
          break label545;
        }
        this.source = IntentSource.NATIVE_APP_INTENT;
        this.decodeFormats = DecodeFormatManager.parseDecodeFormats((Intent)localObject1);
        this.decodeHints = DecodeHintManager.parseDecodeHints((Intent)localObject1);
        int i;
        if ((((Intent)localObject1).hasExtra("SCAN_WIDTH")) && (((Intent)localObject1).hasExtra("SCAN_HEIGHT")))
        {
          i = ((Intent)localObject1).getIntExtra("SCAN_WIDTH", 0);
          int j = ((Intent)localObject1).getIntExtra("SCAN_HEIGHT", 0);
          if ((i > 0) && (j > 0)) {
            this.cameraManager.setManualFramingRect(i, j);
          }
        }
        if (((Intent)localObject1).hasExtra("SCAN_CAMERA_ID"))
        {
          i = ((Intent)localObject1).getIntExtra("SCAN_CAMERA_ID", -1);
          if (i >= 0) {
            this.cameraManager.setManualCameraId(i);
          }
        }
        if (((Intent)localObject1).getBooleanExtra("TORCH_ON", false)) {
          this.cameraManager.setTorchInitiallyOn(true);
        }
        localObject2 = ((Intent)localObject1).getStringExtra("PROMPT_MESSAGE");
        if (localObject2 != null) {
          this.statusView.setText((CharSequence)localObject2);
        }
      }
    }
    for (;;)
    {
      this.characterSet = ((Intent)localObject1).getStringExtra("CHARACTER_SET");
      localObject1 = ((SurfaceView)findViewById(R.id.preview_view)).getHolder();
      if (!this.hasSurface) {
        break label657;
      }
      initCamera((SurfaceHolder)localObject1);
      return;
      if (!"portrait".equalsIgnoreCase((String)localObject1)) {
        break;
      }
      setRequestedOrientation(1);
      break;
      label535:
      bool = false;
      break label228;
      label540:
      bool = false;
      break label252;
      label545:
      if ((str != null) && (str.contains("http://www.google")) && (str.contains("/m/products/scan")))
      {
        this.source = IntentSource.PRODUCT_SEARCH_LINK;
        this.sourceUrl = str;
        this.decodeFormats = DecodeFormatManager.PRODUCT_FORMATS;
      }
      else if (isZXingURL(str))
      {
        this.source = IntentSource.ZXING_LINK;
        this.sourceUrl = str;
        localObject2 = Uri.parse(str);
        this.scanFromWebPageManager = new ScanFromWebPageManager((Uri)localObject2);
        this.decodeFormats = DecodeFormatManager.parseDecodeFormats((Uri)localObject2);
        this.decodeHints = DecodeHintManager.parseDecodeHints((Uri)localObject2);
      }
    }
    label657:
    ((SurfaceHolder)localObject1).addCallback(this);
  }
  
  public void restartPreviewAfterDelay(long paramLong)
  {
    if (this.handler != null) {
      this.handler.sendEmptyMessageDelayed(R.id.restart_preview, paramLong);
    }
    resetStatusView();
  }
  
  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    if (paramSurfaceHolder == null) {
      Log.e(TAG, "*** WARNING *** surfaceCreated() gave us a null surface!");
    }
    if (!this.hasSurface)
    {
      this.hasSurface = true;
      initCamera(paramSurfaceHolder);
    }
  }
  
  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
    this.hasSurface = false;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\CaptureActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */