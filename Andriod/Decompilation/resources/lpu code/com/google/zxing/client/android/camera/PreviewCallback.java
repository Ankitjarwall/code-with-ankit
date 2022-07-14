package com.google.zxing.client.android.camera;

import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

final class PreviewCallback
  implements Camera.PreviewCallback
{
  private static final String TAG = PreviewCallback.class.getSimpleName();
  private final CameraConfigurationManager configManager;
  private Handler previewHandler;
  private int previewMessage;
  
  PreviewCallback(CameraConfigurationManager paramCameraConfigurationManager)
  {
    this.configManager = paramCameraConfigurationManager;
  }
  
  public void onPreviewFrame(byte[] paramArrayOfByte, Camera paramCamera)
  {
    paramCamera = this.configManager.getCameraResolution();
    Handler localHandler = this.previewHandler;
    if ((paramCamera != null) && (localHandler != null))
    {
      localHandler.obtainMessage(this.previewMessage, paramCamera.x, paramCamera.y, paramArrayOfByte).sendToTarget();
      this.previewHandler = null;
      return;
    }
    Log.d(TAG, "Got preview callback, but no handler or resolution available");
  }
  
  void setHandler(Handler paramHandler, int paramInt)
  {
    this.previewHandler = paramHandler;
    this.previewMessage = paramInt;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\camera\PreviewCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */