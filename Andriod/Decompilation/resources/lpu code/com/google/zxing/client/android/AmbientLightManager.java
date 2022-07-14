package com.google.zxing.client.android;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import com.google.zxing.client.android.camera.CameraManager;
import com.google.zxing.client.android.camera.FrontLightMode;

final class AmbientLightManager
  implements SensorEventListener
{
  private static final float BRIGHT_ENOUGH_LUX = 450.0F;
  private static final float TOO_DARK_LUX = 45.0F;
  private CameraManager cameraManager;
  private final Context context;
  private Sensor lightSensor;
  
  AmbientLightManager(Context paramContext)
  {
    this.context = paramContext;
  }
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
  
  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    float f = paramSensorEvent.values[0];
    if (this.cameraManager != null)
    {
      if (f > 45.0F) {
        break label30;
      }
      this.cameraManager.setTorch(true);
    }
    label30:
    while (f < 450.0F) {
      return;
    }
    this.cameraManager.setTorch(false);
  }
  
  void start(CameraManager paramCameraManager)
  {
    this.cameraManager = paramCameraManager;
    if (FrontLightMode.readPref(PreferenceManager.getDefaultSharedPreferences(this.context)) == FrontLightMode.AUTO)
    {
      paramCameraManager = (SensorManager)this.context.getSystemService("sensor");
      this.lightSensor = paramCameraManager.getDefaultSensor(5);
      if (this.lightSensor != null) {
        paramCameraManager.registerListener(this, this.lightSensor, 3);
      }
    }
  }
  
  void stop()
  {
    if (this.lightSensor != null)
    {
      ((SensorManager)this.context.getSystemService("sensor")).unregisterListener(this);
      this.cameraManager = null;
      this.lightSensor = null;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\AmbientLightManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */