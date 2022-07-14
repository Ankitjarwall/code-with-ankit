package com.google.zxing.client.android.camera;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera.Area;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

@TargetApi(15)
public final class CameraConfigurationUtils
{
  private static final int AREA_PER_1000 = 400;
  private static final double MAX_ASPECT_DISTORTION = 0.15D;
  private static final float MAX_EXPOSURE_COMPENSATION = 1.5F;
  private static final int MAX_FPS = 20;
  private static final float MIN_EXPOSURE_COMPENSATION = 0.0F;
  private static final int MIN_FPS = 10;
  private static final int MIN_PREVIEW_PIXELS = 153600;
  private static final Pattern SEMICOLON = Pattern.compile(";");
  private static final String TAG = "CameraConfiguration";
  
  private static List<Camera.Area> buildMiddleArea(int paramInt)
  {
    return Collections.singletonList(new Camera.Area(new Rect(-paramInt, -paramInt, paramInt, paramInt), 1));
  }
  
  public static String collectStats(Camera.Parameters paramParameters)
  {
    return collectStats(paramParameters.flatten());
  }
  
  public static String collectStats(CharSequence paramCharSequence)
  {
    StringBuilder localStringBuilder = new StringBuilder(1000);
    localStringBuilder.append("BOARD=").append(Build.BOARD).append('\n');
    localStringBuilder.append("BRAND=").append(Build.BRAND).append('\n');
    localStringBuilder.append("CPU_ABI=").append(Build.CPU_ABI).append('\n');
    localStringBuilder.append("DEVICE=").append(Build.DEVICE).append('\n');
    localStringBuilder.append("DISPLAY=").append(Build.DISPLAY).append('\n');
    localStringBuilder.append("FINGERPRINT=").append(Build.FINGERPRINT).append('\n');
    localStringBuilder.append("HOST=").append(Build.HOST).append('\n');
    localStringBuilder.append("ID=").append(Build.ID).append('\n');
    localStringBuilder.append("MANUFACTURER=").append(Build.MANUFACTURER).append('\n');
    localStringBuilder.append("MODEL=").append(Build.MODEL).append('\n');
    localStringBuilder.append("PRODUCT=").append(Build.PRODUCT).append('\n');
    localStringBuilder.append("TAGS=").append(Build.TAGS).append('\n');
    localStringBuilder.append("TIME=").append(Build.TIME).append('\n');
    localStringBuilder.append("TYPE=").append(Build.TYPE).append('\n');
    localStringBuilder.append("USER=").append(Build.USER).append('\n');
    localStringBuilder.append("VERSION.CODENAME=").append(Build.VERSION.CODENAME).append('\n');
    localStringBuilder.append("VERSION.INCREMENTAL=").append(Build.VERSION.INCREMENTAL).append('\n');
    localStringBuilder.append("VERSION.RELEASE=").append(Build.VERSION.RELEASE).append('\n');
    localStringBuilder.append("VERSION.SDK_INT=").append(Build.VERSION.SDK_INT).append('\n');
    if (paramCharSequence != null)
    {
      paramCharSequence = SEMICOLON.split(paramCharSequence);
      Arrays.sort(paramCharSequence);
      int j = paramCharSequence.length;
      int i = 0;
      while (i < j)
      {
        localStringBuilder.append(paramCharSequence[i]).append('\n');
        i += 1;
      }
    }
    return localStringBuilder.toString();
  }
  
  public static Point findBestPreviewSizeValue(Camera.Parameters paramParameters, Point paramPoint)
  {
    Object localObject1 = paramParameters.getSupportedPreviewSizes();
    if (localObject1 == null)
    {
      Log.w("CameraConfiguration", "Device returned no supported preview sizes; using default");
      paramParameters = paramParameters.getPreviewSize();
      if (paramParameters == null) {
        throw new IllegalStateException("Parameters contained no preview size!");
      }
      return new Point(paramParameters.width, paramParameters.height);
    }
    localObject1 = new ArrayList((Collection)localObject1);
    Collections.sort((List)localObject1, new Comparator()
    {
      public int compare(Camera.Size paramAnonymousSize1, Camera.Size paramAnonymousSize2)
      {
        int i = paramAnonymousSize1.height * paramAnonymousSize1.width;
        int j = paramAnonymousSize2.height * paramAnonymousSize2.width;
        if (j < i) {
          return -1;
        }
        if (j > i) {
          return 1;
        }
        return 0;
      }
    });
    Object localObject3;
    if (Log.isLoggable("CameraConfiguration", 4))
    {
      localObject2 = new StringBuilder();
      localObject3 = ((List)localObject1).iterator();
      while (((Iterator)localObject3).hasNext())
      {
        Camera.Size localSize = (Camera.Size)((Iterator)localObject3).next();
        ((StringBuilder)localObject2).append(localSize.width).append('x').append(localSize.height).append(' ');
      }
      Log.i("CameraConfiguration", "Supported preview sizes: " + localObject2);
    }
    double d = paramPoint.x / paramPoint.y;
    Object localObject2 = ((List)localObject1).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (Camera.Size)((Iterator)localObject2).next();
      int i = ((Camera.Size)localObject3).width;
      int j = ((Camera.Size)localObject3).height;
      if (i * j < 153600)
      {
        ((Iterator)localObject2).remove();
      }
      else
      {
        int k;
        if (i < j)
        {
          k = 1;
          label272:
          if (paramPoint.x >= paramPoint.y) {
            break label362;
          }
          m = 1;
          label286:
          if (((k == 0) || (m == 0)) && ((k != 0) || (m != 0))) {
            break label368;
          }
          m = 1;
          label309:
          if (m == 0) {
            break label374;
          }
          k = i;
          label318:
          if (m == 0) {
            break label381;
          }
        }
        label362:
        label368:
        label374:
        label381:
        for (int m = j;; m = i)
        {
          if (Math.abs(k / m - d) <= 0.15D) {
            break label388;
          }
          ((Iterator)localObject2).remove();
          break;
          k = 0;
          break label272;
          m = 0;
          break label286;
          m = 0;
          break label309;
          k = j;
          break label318;
        }
        label388:
        if ((k == paramPoint.x) && (m == paramPoint.y))
        {
          paramParameters = new Point(i, j);
          Log.i("CameraConfiguration", "Found preview size exactly matching screen size: " + paramParameters);
          return paramParameters;
        }
      }
    }
    if (!((List)localObject1).isEmpty())
    {
      paramParameters = (Camera.Size)((List)localObject1).get(0);
      paramParameters = new Point(paramParameters.width, paramParameters.height);
      Log.i("CameraConfiguration", "Using largest suitable preview size: " + paramParameters);
      return paramParameters;
    }
    paramParameters = paramParameters.getPreviewSize();
    if (paramParameters == null) {
      throw new IllegalStateException("Parameters contained no preview size!");
    }
    paramParameters = new Point(paramParameters.width, paramParameters.height);
    Log.i("CameraConfiguration", "No suitable preview sizes, using default: " + paramParameters);
    return paramParameters;
  }
  
  private static String findSettableValue(String paramString, Collection<String> paramCollection, String... paramVarArgs)
  {
    Log.i("CameraConfiguration", "Requesting " + paramString + " value from among: " + Arrays.toString(paramVarArgs));
    Log.i("CameraConfiguration", "Supported " + paramString + " values: " + paramCollection);
    if (paramCollection != null)
    {
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        String str = paramVarArgs[i];
        if (paramCollection.contains(str))
        {
          Log.i("CameraConfiguration", "Can set " + paramString + " to: " + str);
          return str;
        }
        i += 1;
      }
    }
    Log.i("CameraConfiguration", "No supported values match");
    return null;
  }
  
  private static Integer indexOfClosestZoom(Camera.Parameters paramParameters, double paramDouble)
  {
    List localList = paramParameters.getZoomRatios();
    Log.i("CameraConfiguration", "Zoom ratios: " + localList);
    int i = paramParameters.getMaxZoom();
    if ((localList == null) || (localList.isEmpty()) || (localList.size() != i + 1))
    {
      Log.w("CameraConfiguration", "Invalid zoom ratios!");
      return null;
    }
    double d1 = Double.POSITIVE_INFINITY;
    int j = 0;
    i = 0;
    while (i < localList.size())
    {
      double d3 = Math.abs(((Integer)localList.get(i)).intValue() - 100.0D * paramDouble);
      double d2 = d1;
      if (d3 < d1)
      {
        d2 = d3;
        j = i;
      }
      i += 1;
      d1 = d2;
    }
    Log.i("CameraConfiguration", "Chose zoom ratio of " + ((Integer)localList.get(j)).intValue() / 100.0D);
    return Integer.valueOf(j);
  }
  
  public static void setBarcodeSceneMode(Camera.Parameters paramParameters)
  {
    if ("barcode".equals(paramParameters.getSceneMode())) {
      Log.i("CameraConfiguration", "Barcode scene mode already set");
    }
    String str;
    do
    {
      return;
      str = findSettableValue("scene mode", paramParameters.getSupportedSceneModes(), new String[] { "barcode" });
    } while (str == null);
    paramParameters.setSceneMode(str);
  }
  
  public static void setBestExposure(Camera.Parameters paramParameters, boolean paramBoolean)
  {
    float f1 = 0.0F;
    int i = paramParameters.getMinExposureCompensation();
    int j = paramParameters.getMaxExposureCompensation();
    float f2 = paramParameters.getExposureCompensationStep();
    if (((i != 0) || (j != 0)) && (f2 > 0.0F))
    {
      if (paramBoolean) {}
      for (;;)
      {
        int k = Math.round(f1 / f2);
        f1 = f2 * k;
        i = Math.max(Math.min(k, j), i);
        if (paramParameters.getExposureCompensation() != i) {
          break;
        }
        Log.i("CameraConfiguration", "Exposure compensation already set to " + i + " / " + f1);
        return;
        f1 = 1.5F;
      }
      Log.i("CameraConfiguration", "Setting exposure compensation to " + i + " / " + f1);
      paramParameters.setExposureCompensation(i);
      return;
    }
    Log.i("CameraConfiguration", "Camera does not support exposure compensation");
  }
  
  public static void setBestPreviewFPS(Camera.Parameters paramParameters)
  {
    setBestPreviewFPS(paramParameters, 10, 20);
  }
  
  public static void setBestPreviewFPS(Camera.Parameters paramParameters, int paramInt1, int paramInt2)
  {
    Object localObject = paramParameters.getSupportedPreviewFpsRange();
    Log.i("CameraConfiguration", "Supported FPS ranges: " + toString((Collection)localObject));
    if ((localObject != null) && (!((List)localObject).isEmpty()))
    {
      arrayOfInt = null;
      Iterator localIterator = ((List)localObject).iterator();
      int i;
      int j;
      do
      {
        localObject = arrayOfInt;
        if (!localIterator.hasNext()) {
          break;
        }
        localObject = (int[])localIterator.next();
        i = localObject[0];
        j = localObject[1];
      } while ((i < paramInt1 * 1000) || (j > paramInt2 * 1000));
      if (localObject == null) {
        Log.i("CameraConfiguration", "No suitable FPS range?");
      }
    }
    else
    {
      return;
    }
    int[] arrayOfInt = new int[2];
    paramParameters.getPreviewFpsRange(arrayOfInt);
    if (Arrays.equals(arrayOfInt, (int[])localObject))
    {
      Log.i("CameraConfiguration", "FPS range already set to " + Arrays.toString((int[])localObject));
      return;
    }
    Log.i("CameraConfiguration", "Setting FPS range to " + Arrays.toString((int[])localObject));
    paramParameters.setPreviewFpsRange(localObject[0], localObject[1]);
  }
  
  public static void setFocus(Camera.Parameters paramParameters, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    List localList = paramParameters.getSupportedFocusModes();
    String str1 = null;
    if (paramBoolean1) {
      if ((!paramBoolean3) && (!paramBoolean2)) {
        break label129;
      }
    }
    String str2;
    label129:
    for (str1 = findSettableValue("focus mode", localList, new String[] { "auto" });; str1 = findSettableValue("focus mode", localList, new String[] { "continuous-picture", "continuous-video", "auto" }))
    {
      str2 = str1;
      if (!paramBoolean3)
      {
        str2 = str1;
        if (str1 == null) {
          str2 = findSettableValue("focus mode", localList, new String[] { "macro", "edof" });
        }
      }
      if (str2 != null)
      {
        if (!str2.equals(paramParameters.getFocusMode())) {
          break;
        }
        Log.i("CameraConfiguration", "Focus mode already set to " + str2);
      }
      return;
    }
    paramParameters.setFocusMode(str2);
  }
  
  public static void setFocusArea(Camera.Parameters paramParameters)
  {
    if (paramParameters.getMaxNumFocusAreas() > 0)
    {
      Log.i("CameraConfiguration", "Old focus areas: " + toString(paramParameters.getFocusAreas()));
      List localList = buildMiddleArea(400);
      Log.i("CameraConfiguration", "Setting focus area to : " + toString(localList));
      paramParameters.setFocusAreas(localList);
      return;
    }
    Log.i("CameraConfiguration", "Device does not support focus areas");
  }
  
  public static void setInvertColor(Camera.Parameters paramParameters)
  {
    if ("negative".equals(paramParameters.getColorEffect())) {
      Log.i("CameraConfiguration", "Negative effect already set");
    }
    String str;
    do
    {
      return;
      str = findSettableValue("color effect", paramParameters.getSupportedColorEffects(), new String[] { "negative" });
    } while (str == null);
    paramParameters.setColorEffect(str);
  }
  
  public static void setMetering(Camera.Parameters paramParameters)
  {
    if (paramParameters.getMaxNumMeteringAreas() > 0)
    {
      Log.i("CameraConfiguration", "Old metering areas: " + paramParameters.getMeteringAreas());
      List localList = buildMiddleArea(400);
      Log.i("CameraConfiguration", "Setting metering area to : " + toString(localList));
      paramParameters.setMeteringAreas(localList);
      return;
    }
    Log.i("CameraConfiguration", "Device does not support metering areas");
  }
  
  public static void setTorch(Camera.Parameters paramParameters, boolean paramBoolean)
  {
    Object localObject = paramParameters.getSupportedFlashModes();
    if (paramBoolean) {}
    for (localObject = findSettableValue("flash mode", (Collection)localObject, new String[] { "torch", "on" });; localObject = findSettableValue("flash mode", (Collection)localObject, new String[] { "off" }))
    {
      if (localObject != null)
      {
        if (!((String)localObject).equals(paramParameters.getFlashMode())) {
          break;
        }
        Log.i("CameraConfiguration", "Flash mode already set to " + (String)localObject);
      }
      return;
    }
    Log.i("CameraConfiguration", "Setting flash mode to " + (String)localObject);
    paramParameters.setFlashMode((String)localObject);
  }
  
  public static void setVideoStabilization(Camera.Parameters paramParameters)
  {
    if (paramParameters.isVideoStabilizationSupported())
    {
      if (paramParameters.getVideoStabilization())
      {
        Log.i("CameraConfiguration", "Video stabilization already enabled");
        return;
      }
      Log.i("CameraConfiguration", "Enabling video stabilization...");
      paramParameters.setVideoStabilization(true);
      return;
    }
    Log.i("CameraConfiguration", "This device does not support video stabilization");
  }
  
  public static void setZoom(Camera.Parameters paramParameters, double paramDouble)
  {
    if (paramParameters.isZoomSupported())
    {
      Integer localInteger = indexOfClosestZoom(paramParameters, paramDouble);
      if (localInteger == null) {
        return;
      }
      if (paramParameters.getZoom() == localInteger.intValue())
      {
        Log.i("CameraConfiguration", "Zoom is already set to " + localInteger);
        return;
      }
      Log.i("CameraConfiguration", "Setting zoom to " + localInteger);
      paramParameters.setZoom(localInteger.intValue());
      return;
    }
    Log.i("CameraConfiguration", "Zoom is not supported");
  }
  
  private static String toString(Iterable<Camera.Area> paramIterable)
  {
    if (paramIterable == null) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      Camera.Area localArea = (Camera.Area)paramIterable.next();
      localStringBuilder.append(localArea.rect).append(':').append(localArea.weight).append(' ');
    }
    return localStringBuilder.toString();
  }
  
  private static String toString(Collection<int[]> paramCollection)
  {
    if ((paramCollection == null) || (paramCollection.isEmpty())) {
      return "[]";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('[');
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      localStringBuilder.append(Arrays.toString((int[])paramCollection.next()));
      if (paramCollection.hasNext()) {
        localStringBuilder.append(", ");
      }
    }
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\camera\CameraConfigurationUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */