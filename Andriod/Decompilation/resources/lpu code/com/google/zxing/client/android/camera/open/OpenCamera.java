package com.google.zxing.client.android.camera.open;

import android.hardware.Camera;

public final class OpenCamera
{
  private final Camera camera;
  private final CameraFacing facing;
  private final int index;
  private final int orientation;
  
  public OpenCamera(int paramInt1, Camera paramCamera, CameraFacing paramCameraFacing, int paramInt2)
  {
    this.index = paramInt1;
    this.camera = paramCamera;
    this.facing = paramCameraFacing;
    this.orientation = paramInt2;
  }
  
  public Camera getCamera()
  {
    return this.camera;
  }
  
  public CameraFacing getFacing()
  {
    return this.facing;
  }
  
  public int getOrientation()
  {
    return this.orientation;
  }
  
  public String toString()
  {
    return "Camera #" + this.index + " : " + this.facing + ',' + this.orientation;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\camera\open\OpenCamera.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */