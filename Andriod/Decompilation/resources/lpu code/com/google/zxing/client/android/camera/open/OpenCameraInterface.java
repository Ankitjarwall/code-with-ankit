package com.google.zxing.client.android.camera.open;

import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.util.Log;

public final class OpenCameraInterface
{
  public static final int NO_REQUESTED_CAMERA = -1;
  private static final String TAG = OpenCameraInterface.class.getName();
  
  public static OpenCamera open(int paramInt)
  {
    int j = 1;
    int m = Camera.getNumberOfCameras();
    if (m == 0) {
      Log.w(TAG, "No cameras!");
    }
    label132:
    label188:
    label279:
    for (;;)
    {
      return null;
      if (m == 1) {
        paramInt = 0;
      }
      Camera localCamera;
      int k;
      Object localObject;
      if (paramInt >= 0)
      {
        localCamera = null;
        if (j == 0) {
          break label132;
        }
        k = paramInt;
        localObject = new Camera.CameraInfo();
        Camera.getCameraInfo(k, (Camera.CameraInfo)localObject);
        if (k >= m) {
          break label188;
        }
        Log.i(TAG, "Opening camera #" + k);
        localCamera = Camera.open(k);
      }
      for (;;)
      {
        if (localCamera == null) {
          break label279;
        }
        return new OpenCamera(k, localCamera, CameraFacing.values()[localObject.facing], ((Camera.CameraInfo)localObject).orientation);
        j = 0;
        break;
        int i = 0;
        for (;;)
        {
          k = i;
          localObject = localCamera;
          if (i >= m) {
            break;
          }
          localObject = new Camera.CameraInfo();
          Camera.getCameraInfo(i, (Camera.CameraInfo)localObject);
          if (CameraFacing.values()[localObject.facing] == CameraFacing.BACK)
          {
            k = i;
            break;
          }
          i += 1;
        }
        if (j != 0)
        {
          Log.w(TAG, "Requested camera does not exist: " + paramInt);
          localCamera = null;
        }
        else
        {
          Log.i(TAG, "No camera facing " + CameraFacing.BACK + "; returning camera #0");
          localCamera = Camera.open(0);
          localObject = new Camera.CameraInfo();
          Camera.getCameraInfo(0, (Camera.CameraInfo)localObject);
        }
      }
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\camera\open\OpenCameraInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */