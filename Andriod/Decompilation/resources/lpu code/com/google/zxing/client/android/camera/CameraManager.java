package com.google.zxing.client.android.camera;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.client.android.camera.open.OpenCamera;
import com.google.zxing.client.android.camera.open.OpenCameraInterface;
import java.io.IOException;

public final class CameraManager
{
  private static final int MAX_FRAME_HEIGHT = 675;
  private static final int MAX_FRAME_WIDTH = 1200;
  private static final int MIN_FRAME_HEIGHT = 240;
  private static final int MIN_FRAME_WIDTH = 240;
  private static final String TAG = CameraManager.class.getSimpleName();
  private AutoFocusManager autoFocusManager;
  private OpenCamera camera;
  private final CameraConfigurationManager configManager;
  private final Context context;
  private Rect framingRect;
  private Rect framingRectInPreview;
  private boolean initialized;
  private final PreviewCallback previewCallback;
  private boolean previewing;
  private int requestedCameraId = -1;
  private int requestedFramingRectHeight;
  private int requestedFramingRectWidth;
  private boolean torchInitiallyOn;
  private WindowManager windowManager;
  
  public CameraManager(Context paramContext)
  {
    this.context = paramContext.getApplicationContext();
    this.configManager = new CameraConfigurationManager(paramContext);
    this.previewCallback = new PreviewCallback(this.configManager);
    this.windowManager = ((WindowManager)this.context.getSystemService("window"));
  }
  
  private static int findDesiredDimensionInRange(int paramInt1, int paramInt2, int paramInt3)
  {
    paramInt1 = paramInt1 * 5 / 8;
    if (paramInt1 < paramInt2) {
      return paramInt2;
    }
    if (paramInt1 > paramInt3) {
      return paramInt3;
    }
    return paramInt1;
  }
  
  public PlanarYUVLuminanceSource buildLuminanceSource(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = new byte[paramArrayOfByte.length];
    int k = this.context.getApplicationContext().getResources().getConfiguration().orientation;
    int i;
    if (k == 1)
    {
      i = 0;
      while (i < paramInt2)
      {
        int j = 0;
        while (j < paramInt1)
        {
          arrayOfByte[(j * paramInt2 + paramInt2 - i - 1)] = paramArrayOfByte[(i * paramInt1 + j)];
          j += 1;
        }
        i += 1;
      }
      i = paramInt1;
      paramInt1 = paramInt2;
    }
    Rect localRect;
    for (;;)
    {
      localRect = getFramingRectInPreview();
      if (localRect != null) {
        break;
      }
      return null;
      arrayOfByte = null;
      i = paramInt2;
    }
    if (k == 1) {}
    for (;;)
    {
      return new PlanarYUVLuminanceSource(arrayOfByte, paramInt1, i, localRect.left, localRect.top, localRect.width(), localRect.height(), false);
      arrayOfByte = paramArrayOfByte;
    }
  }
  
  public void closeDriver()
  {
    try
    {
      if (this.camera != null)
      {
        this.camera.getCamera().release();
        this.camera = null;
        this.framingRect = null;
        this.framingRectInPreview = null;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public Rect getFramingRect()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: getfield 143	com/google/zxing/client/android/camera/CameraManager:framingRect	Landroid/graphics/Rect;
    //   9: ifnonnull +134 -> 143
    //   12: aload_0
    //   13: getfield 130	com/google/zxing/client/android/camera/CameraManager:camera	Lcom/google/zxing/client/android/camera/open/OpenCamera;
    //   16: astore 6
    //   18: aload 6
    //   20: ifnonnull +8 -> 28
    //   23: aload_0
    //   24: monitorexit
    //   25: aload 5
    //   27: areturn
    //   28: aload_0
    //   29: getfield 67	com/google/zxing/client/android/camera/CameraManager:configManager	Lcom/google/zxing/client/android/camera/CameraConfigurationManager;
    //   32: invokevirtual 150	com/google/zxing/client/android/camera/CameraConfigurationManager:getScreenResolution	()Landroid/graphics/Point;
    //   35: astore 6
    //   37: aload 6
    //   39: ifnull -16 -> 23
    //   42: aload 6
    //   44: getfield 155	android/graphics/Point:x	I
    //   47: sipush 240
    //   50: sipush 1200
    //   53: invokestatic 157	com/google/zxing/client/android/camera/CameraManager:findDesiredDimensionInRange	(III)I
    //   56: istore_1
    //   57: aload 6
    //   59: getfield 160	android/graphics/Point:y	I
    //   62: sipush 240
    //   65: sipush 675
    //   68: invokestatic 157	com/google/zxing/client/android/camera/CameraManager:findDesiredDimensionInRange	(III)I
    //   71: istore_2
    //   72: aload 6
    //   74: getfield 155	android/graphics/Point:x	I
    //   77: iload_1
    //   78: isub
    //   79: iconst_2
    //   80: idiv
    //   81: istore_3
    //   82: aload 6
    //   84: getfield 160	android/graphics/Point:y	I
    //   87: iload_2
    //   88: isub
    //   89: iconst_2
    //   90: idiv
    //   91: istore 4
    //   93: aload_0
    //   94: new 111	android/graphics/Rect
    //   97: dup
    //   98: iload_3
    //   99: iload 4
    //   101: iload_3
    //   102: iload_1
    //   103: iadd
    //   104: iload 4
    //   106: iload_2
    //   107: iadd
    //   108: invokespecial 163	android/graphics/Rect:<init>	(IIII)V
    //   111: putfield 143	com/google/zxing/client/android/camera/CameraManager:framingRect	Landroid/graphics/Rect;
    //   114: getstatic 46	com/google/zxing/client/android/camera/CameraManager:TAG	Ljava/lang/String;
    //   117: new 165	java/lang/StringBuilder
    //   120: dup
    //   121: invokespecial 166	java/lang/StringBuilder:<init>	()V
    //   124: ldc -88
    //   126: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: aload_0
    //   130: getfield 143	com/google/zxing/client/android/camera/CameraManager:framingRect	Landroid/graphics/Rect;
    //   133: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   136: invokevirtual 178	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   139: invokestatic 184	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   142: pop
    //   143: aload_0
    //   144: getfield 143	com/google/zxing/client/android/camera/CameraManager:framingRect	Landroid/graphics/Rect;
    //   147: astore 5
    //   149: goto -126 -> 23
    //   152: astore 5
    //   154: aload_0
    //   155: monitorexit
    //   156: aload 5
    //   158: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	159	0	this	CameraManager
    //   56	48	1	i	int
    //   71	37	2	j	int
    //   81	23	3	k	int
    //   91	17	4	m	int
    //   1	147	5	localRect	Rect
    //   152	5	5	localObject1	Object
    //   16	67	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   5	18	152	finally
    //   28	37	152	finally
    //   42	143	152	finally
    //   143	149	152	finally
  }
  
  /* Error */
  public Rect getFramingRectInPreview()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield 145	com/google/zxing/client/android/camera/CameraManager:framingRectInPreview	Landroid/graphics/Rect;
    //   8: ifnonnull +164 -> 172
    //   11: aload_0
    //   12: invokevirtual 186	com/google/zxing/client/android/camera/CameraManager:getFramingRect	()Landroid/graphics/Rect;
    //   15: astore_1
    //   16: aload_1
    //   17: ifnonnull +9 -> 26
    //   20: aload_2
    //   21: astore_1
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_1
    //   25: areturn
    //   26: new 111	android/graphics/Rect
    //   29: dup
    //   30: aload_1
    //   31: invokespecial 189	android/graphics/Rect:<init>	(Landroid/graphics/Rect;)V
    //   34: astore_3
    //   35: aload_0
    //   36: getfield 67	com/google/zxing/client/android/camera/CameraManager:configManager	Lcom/google/zxing/client/android/camera/CameraConfigurationManager;
    //   39: invokevirtual 192	com/google/zxing/client/android/camera/CameraConfigurationManager:getCameraResolution	()Landroid/graphics/Point;
    //   42: astore 4
    //   44: aload_0
    //   45: getfield 67	com/google/zxing/client/android/camera/CameraManager:configManager	Lcom/google/zxing/client/android/camera/CameraConfigurationManager;
    //   48: invokevirtual 150	com/google/zxing/client/android/camera/CameraConfigurationManager:getScreenResolution	()Landroid/graphics/Point;
    //   51: astore 5
    //   53: aload_2
    //   54: astore_1
    //   55: aload 4
    //   57: ifnull -35 -> 22
    //   60: aload_2
    //   61: astore_1
    //   62: aload 5
    //   64: ifnull -42 -> 22
    //   67: aload_0
    //   68: getfield 61	com/google/zxing/client/android/camera/CameraManager:context	Landroid/content/Context;
    //   71: invokevirtual 59	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   74: invokevirtual 92	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   77: invokevirtual 98	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   80: getfield 103	android/content/res/Configuration:orientation	I
    //   83: iconst_1
    //   84: if_icmpne +96 -> 180
    //   87: aload_3
    //   88: aload_3
    //   89: getfield 114	android/graphics/Rect:left	I
    //   92: aload 4
    //   94: getfield 160	android/graphics/Point:y	I
    //   97: imul
    //   98: aload 5
    //   100: getfield 155	android/graphics/Point:x	I
    //   103: idiv
    //   104: putfield 114	android/graphics/Rect:left	I
    //   107: aload_3
    //   108: aload_3
    //   109: getfield 195	android/graphics/Rect:right	I
    //   112: aload 4
    //   114: getfield 160	android/graphics/Point:y	I
    //   117: imul
    //   118: aload 5
    //   120: getfield 155	android/graphics/Point:x	I
    //   123: idiv
    //   124: putfield 195	android/graphics/Rect:right	I
    //   127: aload_3
    //   128: aload_3
    //   129: getfield 117	android/graphics/Rect:top	I
    //   132: aload 4
    //   134: getfield 155	android/graphics/Point:x	I
    //   137: imul
    //   138: aload 5
    //   140: getfield 160	android/graphics/Point:y	I
    //   143: idiv
    //   144: putfield 117	android/graphics/Rect:top	I
    //   147: aload_3
    //   148: aload_3
    //   149: getfield 198	android/graphics/Rect:bottom	I
    //   152: aload 4
    //   154: getfield 155	android/graphics/Point:x	I
    //   157: imul
    //   158: aload 5
    //   160: getfield 160	android/graphics/Point:y	I
    //   163: idiv
    //   164: putfield 198	android/graphics/Rect:bottom	I
    //   167: aload_0
    //   168: aload_3
    //   169: putfield 145	com/google/zxing/client/android/camera/CameraManager:framingRectInPreview	Landroid/graphics/Rect;
    //   172: aload_0
    //   173: getfield 145	com/google/zxing/client/android/camera/CameraManager:framingRectInPreview	Landroid/graphics/Rect;
    //   176: astore_1
    //   177: goto -155 -> 22
    //   180: aload_3
    //   181: aload_3
    //   182: getfield 114	android/graphics/Rect:left	I
    //   185: aload 4
    //   187: getfield 155	android/graphics/Point:x	I
    //   190: imul
    //   191: aload 5
    //   193: getfield 155	android/graphics/Point:x	I
    //   196: idiv
    //   197: putfield 114	android/graphics/Rect:left	I
    //   200: aload_3
    //   201: aload_3
    //   202: getfield 195	android/graphics/Rect:right	I
    //   205: aload 4
    //   207: getfield 155	android/graphics/Point:x	I
    //   210: imul
    //   211: aload 5
    //   213: getfield 155	android/graphics/Point:x	I
    //   216: idiv
    //   217: putfield 195	android/graphics/Rect:right	I
    //   220: aload_3
    //   221: aload_3
    //   222: getfield 117	android/graphics/Rect:top	I
    //   225: aload 4
    //   227: getfield 160	android/graphics/Point:y	I
    //   230: imul
    //   231: aload 5
    //   233: getfield 160	android/graphics/Point:y	I
    //   236: idiv
    //   237: putfield 117	android/graphics/Rect:top	I
    //   240: aload_3
    //   241: aload_3
    //   242: getfield 198	android/graphics/Rect:bottom	I
    //   245: aload 4
    //   247: getfield 160	android/graphics/Point:y	I
    //   250: imul
    //   251: aload 5
    //   253: getfield 160	android/graphics/Point:y	I
    //   256: idiv
    //   257: putfield 198	android/graphics/Rect:bottom	I
    //   260: goto -93 -> 167
    //   263: astore_1
    //   264: aload_0
    //   265: monitorexit
    //   266: aload_1
    //   267: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	268	0	this	CameraManager
    //   15	162	1	localObject1	Object
    //   263	4	1	localObject2	Object
    //   1	60	2	localObject3	Object
    //   34	208	3	localRect	Rect
    //   42	204	4	localPoint1	android.graphics.Point
    //   51	201	5	localPoint2	android.graphics.Point
    // Exception table:
    //   from	to	target	type
    //   4	16	263	finally
    //   26	53	263	finally
    //   67	167	263	finally
    //   167	172	263	finally
    //   172	177	263	finally
    //   180	260	263	finally
  }
  
  /* Error */
  public boolean isOpen()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 130	com/google/zxing/client/android/camera/CameraManager:camera	Lcom/google/zxing/client/android/camera/open/OpenCamera;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull +9 -> 17
    //   11: iconst_1
    //   12: istore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: iload_1
    //   16: ireturn
    //   17: iconst_0
    //   18: istore_1
    //   19: goto -6 -> 13
    //   22: astore_2
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_2
    //   26: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	27	0	this	CameraManager
    //   12	7	1	bool	boolean
    //   6	2	2	localOpenCamera	OpenCamera
    //   22	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	22	finally
  }
  
  /* Error */
  public boolean isTorchOn()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 130	com/google/zxing/client/android/camera/CameraManager:camera	Lcom/google/zxing/client/android/camera/open/OpenCamera;
    //   6: ifnull +28 -> 34
    //   9: aload_0
    //   10: getfield 67	com/google/zxing/client/android/camera/CameraManager:configManager	Lcom/google/zxing/client/android/camera/CameraConfigurationManager;
    //   13: aload_0
    //   14: getfield 130	com/google/zxing/client/android/camera/CameraManager:camera	Lcom/google/zxing/client/android/camera/open/OpenCamera;
    //   17: invokevirtual 136	com/google/zxing/client/android/camera/open/OpenCamera:getCamera	()Landroid/hardware/Camera;
    //   20: invokevirtual 205	com/google/zxing/client/android/camera/CameraConfigurationManager:getTorchState	(Landroid/hardware/Camera;)Z
    //   23: istore_1
    //   24: iload_1
    //   25: ifeq +9 -> 34
    //   28: iconst_1
    //   29: istore_1
    //   30: aload_0
    //   31: monitorexit
    //   32: iload_1
    //   33: ireturn
    //   34: iconst_0
    //   35: istore_1
    //   36: goto -6 -> 30
    //   39: astore_2
    //   40: aload_0
    //   41: monitorexit
    //   42: aload_2
    //   43: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	44	0	this	CameraManager
    //   23	13	1	bool	boolean
    //   39	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	24	39	finally
  }
  
  public void openDriver(SurfaceHolder paramSurfaceHolder)
    throws IOException
  {
    Object localObject1;
    try
    {
      localObject2 = this.camera;
      localObject1 = localObject2;
      if (localObject2 != null) {
        break label45;
      }
      localObject1 = OpenCameraInterface.open(this.requestedCameraId);
      if (localObject1 == null) {
        throw new IOException("Camera.open() failed to return object from driver");
      }
    }
    finally {}
    this.camera = ((OpenCamera)localObject1);
    label45:
    if (!this.initialized)
    {
      this.initialized = true;
      this.configManager.initFromCameraParameters((OpenCamera)localObject1);
      if ((this.requestedFramingRectWidth > 0) && (this.requestedFramingRectHeight > 0))
      {
        setManualFramingRect(this.requestedFramingRectWidth, this.requestedFramingRectHeight);
        this.requestedFramingRectWidth = 0;
        this.requestedFramingRectHeight = 0;
      }
    }
    Camera localCamera = ((OpenCamera)localObject1).getCamera();
    Object localObject2 = localCamera.getParameters();
    if (localObject2 == null) {
      localObject2 = null;
    }
    try
    {
      for (;;)
      {
        this.configManager.setDesiredCameraParameters((OpenCamera)localObject1, false);
        localCamera.setPreviewDisplay(paramSurfaceHolder);
        if (this.torchInitiallyOn) {
          setTorch(true);
        }
        return;
        localObject2 = ((Camera.Parameters)localObject2).flatten();
      }
    }
    catch (RuntimeException localRuntimeException2)
    {
      for (;;)
      {
        Log.w(TAG, "Camera rejected parameters. Setting only minimal safe-mode parameters");
        Log.i(TAG, "Resetting to saved camera params: " + (String)localObject2);
        if (localObject2 != null)
        {
          Camera.Parameters localParameters = localCamera.getParameters();
          localParameters.unflatten((String)localObject2);
          try
          {
            localCamera.setParameters(localParameters);
            this.configManager.setDesiredCameraParameters((OpenCamera)localObject1, true);
          }
          catch (RuntimeException localRuntimeException1)
          {
            Log.w(TAG, "Camera rejected even safe-mode parameters! No configuration");
          }
        }
      }
    }
  }
  
  public void requestPreviewFrame(Handler paramHandler, int paramInt)
  {
    try
    {
      OpenCamera localOpenCamera = this.camera;
      if ((localOpenCamera != null) && (this.previewing))
      {
        this.previewCallback.setHandler(paramHandler, paramInt);
        localOpenCamera.getCamera().setOneShotPreviewCallback(this.previewCallback);
      }
      return;
    }
    finally {}
  }
  
  public void setManualCameraId(int paramInt)
  {
    try
    {
      this.requestedCameraId = paramInt;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public void setManualFramingRect(int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 224	com/google/zxing/client/android/camera/CameraManager:initialized	Z
    //   6: ifeq +49 -> 55
    //   9: aload_0
    //   10: aload_0
    //   11: invokevirtual 186	com/google/zxing/client/android/camera/CameraManager:getFramingRect	()Landroid/graphics/Rect;
    //   14: putfield 143	com/google/zxing/client/android/camera/CameraManager:framingRect	Landroid/graphics/Rect;
    //   17: getstatic 46	com/google/zxing/client/android/camera/CameraManager:TAG	Ljava/lang/String;
    //   20: new 165	java/lang/StringBuilder
    //   23: dup
    //   24: invokespecial 166	java/lang/StringBuilder:<init>	()V
    //   27: ldc_w 293
    //   30: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: aload_0
    //   34: getfield 143	com/google/zxing/client/android/camera/CameraManager:framingRect	Landroid/graphics/Rect;
    //   37: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   40: invokevirtual 178	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   43: invokestatic 184	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   46: pop
    //   47: aload_0
    //   48: aconst_null
    //   49: putfield 145	com/google/zxing/client/android/camera/CameraManager:framingRectInPreview	Landroid/graphics/Rect;
    //   52: aload_0
    //   53: monitorexit
    //   54: return
    //   55: aload_0
    //   56: iload_1
    //   57: putfield 230	com/google/zxing/client/android/camera/CameraManager:requestedFramingRectWidth	I
    //   60: aload_0
    //   61: iload_2
    //   62: putfield 232	com/google/zxing/client/android/camera/CameraManager:requestedFramingRectHeight	I
    //   65: goto -13 -> 52
    //   68: astore_3
    //   69: aload_0
    //   70: monitorexit
    //   71: aload_3
    //   72: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	73	0	this	CameraManager
    //   0	73	1	paramInt1	int
    //   0	73	2	paramInt2	int
    //   68	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	52	68	finally
    //   55	65	68	finally
  }
  
  /* Error */
  public void setTorch(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 130	com/google/zxing/client/android/camera/CameraManager:camera	Lcom/google/zxing/client/android/camera/open/OpenCamera;
    //   6: astore_3
    //   7: aload_3
    //   8: ifnull +85 -> 93
    //   11: iload_1
    //   12: aload_0
    //   13: getfield 67	com/google/zxing/client/android/camera/CameraManager:configManager	Lcom/google/zxing/client/android/camera/CameraConfigurationManager;
    //   16: aload_3
    //   17: invokevirtual 136	com/google/zxing/client/android/camera/open/OpenCamera:getCamera	()Landroid/hardware/Camera;
    //   20: invokevirtual 205	com/google/zxing/client/android/camera/CameraConfigurationManager:getTorchState	(Landroid/hardware/Camera;)Z
    //   23: if_icmpeq +70 -> 93
    //   26: aload_0
    //   27: getfield 295	com/google/zxing/client/android/camera/CameraManager:autoFocusManager	Lcom/google/zxing/client/android/camera/AutoFocusManager;
    //   30: ifnull +66 -> 96
    //   33: iconst_1
    //   34: istore_2
    //   35: iload_2
    //   36: ifeq +15 -> 51
    //   39: aload_0
    //   40: getfield 295	com/google/zxing/client/android/camera/CameraManager:autoFocusManager	Lcom/google/zxing/client/android/camera/AutoFocusManager;
    //   43: invokevirtual 300	com/google/zxing/client/android/camera/AutoFocusManager:stop	()V
    //   46: aload_0
    //   47: aconst_null
    //   48: putfield 295	com/google/zxing/client/android/camera/CameraManager:autoFocusManager	Lcom/google/zxing/client/android/camera/AutoFocusManager;
    //   51: aload_0
    //   52: getfield 67	com/google/zxing/client/android/camera/CameraManager:configManager	Lcom/google/zxing/client/android/camera/CameraConfigurationManager;
    //   55: aload_3
    //   56: invokevirtual 136	com/google/zxing/client/android/camera/open/OpenCamera:getCamera	()Landroid/hardware/Camera;
    //   59: iload_1
    //   60: invokevirtual 303	com/google/zxing/client/android/camera/CameraConfigurationManager:setTorch	(Landroid/hardware/Camera;Z)V
    //   63: iload_2
    //   64: ifeq +29 -> 93
    //   67: aload_0
    //   68: new 297	com/google/zxing/client/android/camera/AutoFocusManager
    //   71: dup
    //   72: aload_0
    //   73: getfield 61	com/google/zxing/client/android/camera/CameraManager:context	Landroid/content/Context;
    //   76: aload_3
    //   77: invokevirtual 136	com/google/zxing/client/android/camera/open/OpenCamera:getCamera	()Landroid/hardware/Camera;
    //   80: invokespecial 306	com/google/zxing/client/android/camera/AutoFocusManager:<init>	(Landroid/content/Context;Landroid/hardware/Camera;)V
    //   83: putfield 295	com/google/zxing/client/android/camera/CameraManager:autoFocusManager	Lcom/google/zxing/client/android/camera/AutoFocusManager;
    //   86: aload_0
    //   87: getfield 295	com/google/zxing/client/android/camera/CameraManager:autoFocusManager	Lcom/google/zxing/client/android/camera/AutoFocusManager;
    //   90: invokevirtual 309	com/google/zxing/client/android/camera/AutoFocusManager:start	()V
    //   93: aload_0
    //   94: monitorexit
    //   95: return
    //   96: iconst_0
    //   97: istore_2
    //   98: goto -63 -> 35
    //   101: astore_3
    //   102: aload_0
    //   103: monitorexit
    //   104: aload_3
    //   105: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	106	0	this	CameraManager
    //   0	106	1	paramBoolean	boolean
    //   34	64	2	i	int
    //   6	71	3	localOpenCamera	OpenCamera
    //   101	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	101	finally
    //   11	33	101	finally
    //   39	51	101	finally
    //   51	63	101	finally
    //   67	93	101	finally
  }
  
  public void setTorchInitiallyOn(boolean paramBoolean)
  {
    try
    {
      this.torchInitiallyOn = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void startPreview()
  {
    try
    {
      OpenCamera localOpenCamera = this.camera;
      if ((localOpenCamera != null) && (!this.previewing))
      {
        localOpenCamera.getCamera().startPreview();
        this.previewing = true;
        this.autoFocusManager = new AutoFocusManager(this.context, localOpenCamera.getCamera());
      }
      return;
    }
    finally {}
  }
  
  public void stopPreview()
  {
    try
    {
      if (this.autoFocusManager != null)
      {
        this.autoFocusManager.stop();
        this.autoFocusManager = null;
      }
      if ((this.camera != null) && (this.previewing))
      {
        this.camera.getCamera().stopPreview();
        this.previewCallback.setHandler(null, 0);
        this.previewing = false;
      }
      return;
    }
    finally {}
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\camera\CameraManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */