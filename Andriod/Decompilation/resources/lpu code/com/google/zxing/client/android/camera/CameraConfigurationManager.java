package com.google.zxing.client.android.camera;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.google.zxing.client.android.camera.open.CameraFacing;
import com.google.zxing.client.android.camera.open.OpenCamera;

final class CameraConfigurationManager
{
  private static final String TAG = "CameraConfiguration";
  private Point bestPreviewSize;
  private Point cameraResolution;
  private final Context context;
  private int cwNeededRotation;
  private int cwRotationFromDisplayToCamera;
  private Point previewSizeOnScreen;
  private Point screenResolution;
  
  CameraConfigurationManager(Context paramContext)
  {
    this.context = paramContext.getApplicationContext();
  }
  
  private void doSetTorch(Camera.Parameters paramParameters, boolean paramBoolean1, boolean paramBoolean2)
  {
    CameraConfigurationUtils.setTorch(paramParameters, paramBoolean1);
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
    if ((!paramBoolean2) && (!localSharedPreferences.getBoolean("preferences_disable_exposure", true))) {
      CameraConfigurationUtils.setBestExposure(paramParameters, paramBoolean1);
    }
  }
  
  private void initializeTorch(Camera.Parameters paramParameters, SharedPreferences paramSharedPreferences, boolean paramBoolean)
  {
    if (FrontLightMode.readPref(paramSharedPreferences) == FrontLightMode.ON) {}
    for (boolean bool = true;; bool = false)
    {
      doSetTorch(paramParameters, bool, paramBoolean);
      return;
    }
  }
  
  Point getBestPreviewSize()
  {
    return this.bestPreviewSize;
  }
  
  int getCWNeededRotation()
  {
    return this.cwNeededRotation;
  }
  
  Point getCameraResolution()
  {
    return this.cameraResolution;
  }
  
  Point getPreviewSizeOnScreen()
  {
    return this.previewSizeOnScreen;
  }
  
  Point getScreenResolution()
  {
    return this.screenResolution;
  }
  
  boolean getTorchState(Camera paramCamera)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramCamera != null)
    {
      paramCamera = paramCamera.getParameters();
      bool1 = bool2;
      if (paramCamera != null)
      {
        paramCamera = paramCamera.getFlashMode();
        bool1 = bool2;
        if (paramCamera != null) {
          if (!"on".equals(paramCamera))
          {
            bool1 = bool2;
            if (!"torch".equals(paramCamera)) {}
          }
          else
          {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
  }
  
  void initFromCameraParameters(OpenCamera paramOpenCamera)
  {
    Camera.Parameters localParameters = paramOpenCamera.getCamera().getParameters();
    Display localDisplay = ((WindowManager)this.context.getSystemService("window")).getDefaultDisplay();
    int i = localDisplay.getRotation();
    int j;
    switch (i)
    {
    default: 
      if (i % 90 == 0)
      {
        i = (i + 360) % 360;
        Log.i("CameraConfiguration", "Display at: " + i);
        int k = paramOpenCamera.getOrientation();
        Log.i("CameraConfiguration", "Camera at: " + k);
        j = k;
        if (paramOpenCamera.getFacing() == CameraFacing.FRONT)
        {
          j = (360 - k) % 360;
          Log.i("CameraConfiguration", "Front camera overriden to: " + j);
        }
        this.cwRotationFromDisplayToCamera = ((j + 360 - i) % 360);
        Log.i("CameraConfiguration", "Final display orientation: " + this.cwRotationFromDisplayToCamera);
        if (paramOpenCamera.getFacing() != CameraFacing.FRONT) {
          break label553;
        }
        Log.i("CameraConfiguration", "Compensating rotation for front camera");
        this.cwNeededRotation = ((360 - this.cwRotationFromDisplayToCamera) % 360);
        label264:
        Log.i("CameraConfiguration", "Clockwise rotation from display to camera: " + this.cwNeededRotation);
        paramOpenCamera = new Point();
        localDisplay.getSize(paramOpenCamera);
        this.screenResolution = paramOpenCamera;
        Log.i("CameraConfiguration", "Screen resolution in current orientation: " + this.screenResolution);
        this.cameraResolution = CameraConfigurationUtils.findBestPreviewSizeValue(localParameters, this.screenResolution);
        Log.i("CameraConfiguration", "Camera resolution: " + this.cameraResolution);
        this.bestPreviewSize = CameraConfigurationUtils.findBestPreviewSizeValue(localParameters, this.screenResolution);
        Log.i("CameraConfiguration", "Best available preview size: " + this.bestPreviewSize);
        if (this.screenResolution.x >= this.screenResolution.y) {
          break label564;
        }
        i = 1;
        label440:
        if (this.bestPreviewSize.x >= this.bestPreviewSize.y) {
          break label569;
        }
        j = 1;
        label459:
        if (i != j) {
          break label574;
        }
      }
      break;
    }
    label553:
    label564:
    label569:
    label574:
    for (this.previewSizeOnScreen = this.bestPreviewSize;; this.previewSizeOnScreen = new Point(this.bestPreviewSize.y, this.bestPreviewSize.x))
    {
      Log.i("CameraConfiguration", "Preview size on screen: " + this.previewSizeOnScreen);
      return;
      i = 0;
      break;
      i = 90;
      break;
      i = 180;
      break;
      i = 270;
      break;
      throw new IllegalArgumentException("Bad rotation: " + i);
      this.cwNeededRotation = this.cwRotationFromDisplayToCamera;
      break label264;
      i = 0;
      break label440;
      j = 0;
      break label459;
    }
  }
  
  void setDesiredCameraParameters(OpenCamera paramOpenCamera, boolean paramBoolean)
  {
    paramOpenCamera = paramOpenCamera.getCamera();
    int i = this.context.getApplicationContext().getResources().getConfiguration().orientation;
    int j = ((WindowManager)this.context.getSystemService("window")).getDefaultDisplay().getRotation();
    Camera.Parameters localParameters;
    if (i == 1) {
      if ((j == 0) || (j == 1))
      {
        paramOpenCamera.setDisplayOrientation(90);
        localParameters = paramOpenCamera.getParameters();
        if (localParameters != null) {
          break label118;
        }
        Log.w("CameraConfiguration", "Device error: no camera parameters are available. Proceeding without configuration.");
      }
    }
    label118:
    do
    {
      return;
      paramOpenCamera.setDisplayOrientation(270);
      break;
      if ((j != 2) && (j != 3)) {
        break;
      }
      paramOpenCamera.setDisplayOrientation(180);
      break;
      Log.i("CameraConfiguration", "Initial camera parameters: " + localParameters.flatten());
      if (paramBoolean) {
        Log.w("CameraConfiguration", "In camera config safe mode -- most settings will not be honored");
      }
      SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
      initializeTorch(localParameters, localSharedPreferences, paramBoolean);
      CameraConfigurationUtils.setFocus(localParameters, localSharedPreferences.getBoolean("preferences_auto_focus", true), localSharedPreferences.getBoolean("preferences_disable_continuous_focus", true), paramBoolean);
      if (!paramBoolean)
      {
        if (localSharedPreferences.getBoolean("preferences_invert_scan", false)) {
          CameraConfigurationUtils.setInvertColor(localParameters);
        }
        if (!localSharedPreferences.getBoolean("preferences_disable_barcode_scene_mode", true)) {
          CameraConfigurationUtils.setBarcodeSceneMode(localParameters);
        }
        if (!localSharedPreferences.getBoolean("preferences_disable_metering", true))
        {
          CameraConfigurationUtils.setVideoStabilization(localParameters);
          CameraConfigurationUtils.setFocusArea(localParameters);
          CameraConfigurationUtils.setMetering(localParameters);
        }
      }
      localParameters.setPreviewSize(this.bestPreviewSize.x, this.bestPreviewSize.y);
      paramOpenCamera.setParameters(localParameters);
      paramOpenCamera.setDisplayOrientation(this.cwRotationFromDisplayToCamera);
      paramOpenCamera = paramOpenCamera.getParameters().getPreviewSize();
    } while ((paramOpenCamera == null) || ((this.bestPreviewSize.x == paramOpenCamera.width) && (this.bestPreviewSize.y == paramOpenCamera.height)));
    Log.w("CameraConfiguration", "Camera said it supported preview size " + this.bestPreviewSize.x + 'x' + this.bestPreviewSize.y + ", but after setting it, preview size is " + paramOpenCamera.width + 'x' + paramOpenCamera.height);
    this.bestPreviewSize.x = paramOpenCamera.width;
    this.bestPreviewSize.y = paramOpenCamera.height;
  }
  
  void setTorch(Camera paramCamera, boolean paramBoolean)
  {
    Camera.Parameters localParameters = paramCamera.getParameters();
    doSetTorch(localParameters, paramBoolean, false);
    paramCamera.setParameters(localParameters);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\camera\CameraConfigurationManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */