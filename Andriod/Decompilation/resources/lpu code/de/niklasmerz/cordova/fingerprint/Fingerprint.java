package de.niklasmerz.cordova.fingerprint;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.KeyguardManager;
import android.content.res.Resources;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintManager.CryptoObject;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec.Builder;
import android.util.Base64;
import android.util.Log;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(23)
public class Fingerprint
  extends CordovaPlugin
{
  private static final String ANDROID_KEY_STORE = "AndroidKeyStore";
  private static final String DIALOG_FRAGMENT_TAG = "FpAuthDialog";
  public static final String TAG = "Fingerprint";
  public static CallbackContext mCallbackContext;
  public static Cipher mCipher;
  private static String mClientId;
  private static String mClientSecret;
  private static boolean mDisableBackup = false;
  public static KeyGenerator mKeyGenerator;
  public static KeyStore mKeyStore;
  public static PluginResult mPluginResult;
  public static String packageName;
  private FingerprintManager mFingerPrintManager;
  FingerprintAuthenticationDialogFragment mFragment;
  KeyguardManager mKeyguardManager;
  
  public static boolean createKey()
  {
    String str1 = "";
    boolean bool = false;
    try
    {
      mKeyStore.load(null);
      mKeyGenerator.init(new KeyGenParameterSpec.Builder(mClientId, 3).setBlockModes(new String[] { "CBC" }).setUserAuthenticationRequired(true).setEncryptionPaddings(new String[] { "PKCS7Padding" }).build());
      mKeyGenerator.generateKey();
      bool = true;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;)
      {
        String str2 = "Failed to create key: " + "NoSuchAlgorithmException: " + localNoSuchAlgorithmException.toString();
      }
    }
    catch (InvalidAlgorithmParameterException localInvalidAlgorithmParameterException)
    {
      for (;;)
      {
        String str3 = "Failed to create key: " + "InvalidAlgorithmParameterException: " + localInvalidAlgorithmParameterException.toString();
      }
    }
    catch (CertificateException localCertificateException)
    {
      for (;;)
      {
        String str4 = "Failed to create key: " + "CertificateException: " + localCertificateException.toString();
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        String str5 = "Failed to create key: " + "IOException: " + localIOException.toString();
      }
    }
    if (!bool)
    {
      Log.e("Fingerprint", str1);
      setPluginResultError(str1);
    }
    return bool;
  }
  
  private static SecretKey getSecretKey()
  {
    String str1 = "";
    Object localObject = null;
    try
    {
      mKeyStore.load(null);
      SecretKey localSecretKey = (SecretKey)mKeyStore.getKey(mClientId, null);
      localObject = localSecretKey;
    }
    catch (KeyStoreException localKeyStoreException)
    {
      for (;;)
      {
        String str2 = "Failed to get SecretKey from KeyStore: " + "KeyStoreException: " + localKeyStoreException.toString();
      }
    }
    catch (CertificateException localCertificateException)
    {
      for (;;)
      {
        String str3 = "Failed to get SecretKey from KeyStore: " + "CertificateException: " + localCertificateException.toString();
      }
    }
    catch (UnrecoverableKeyException localUnrecoverableKeyException)
    {
      for (;;)
      {
        String str4 = "Failed to get SecretKey from KeyStore: " + "UnrecoverableKeyException: " + localUnrecoverableKeyException.toString();
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        String str5 = "Failed to get SecretKey from KeyStore: " + "IOException: " + localIOException.toString();
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;)
      {
        String str6 = "Failed to get SecretKey from KeyStore: " + "NoSuchAlgorithmException: " + localNoSuchAlgorithmException.toString();
      }
    }
    if (localObject == null) {
      Log.e("Fingerprint", str1);
    }
    return (SecretKey)localObject;
  }
  
  private static boolean initCipher()
  {
    boolean bool = false;
    String str1 = "";
    try
    {
      SecretKey localSecretKey = getSecretKey();
      mCipher.init(1, localSecretKey);
      bool = true;
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      for (;;)
      {
        String str2 = "Failed to init Cipher: " + "InvalidKeyException: " + localInvalidKeyException.toString();
      }
    }
    if (!bool)
    {
      Log.e("Fingerprint", str1);
      createKey();
    }
    return bool;
  }
  
  private boolean isFingerprintAuthAvailable()
  {
    return (this.mFingerPrintManager.isHardwareDetected()) && (this.mFingerPrintManager.hasEnrolledFingerprints());
  }
  
  public static void onAuthenticated(boolean paramBoolean)
  {
    JSONObject localJSONObject = new JSONObject();
    String str1 = "";
    int i = 0;
    if (paramBoolean) {}
    for (;;)
    {
      try
      {
        localJSONObject.put("withFingerprint", Base64.encodeToString(tryEncrypt(), 0));
        i = 1;
      }
      catch (BadPaddingException localBadPaddingException)
      {
        String str2 = "Failed to encrypt the data with the generated key: BadPaddingException:  " + localBadPaddingException.getMessage();
        Log.e("Fingerprint", str2);
        continue;
      }
      catch (IllegalBlockSizeException localIllegalBlockSizeException)
      {
        String str3 = "Failed to encrypt the data with the generated key: IllegalBlockSizeException: " + localIllegalBlockSizeException.getMessage();
        Log.e("Fingerprint", str3);
        continue;
      }
      catch (JSONException localJSONException)
      {
        String str4 = "Failed to set resultJson key value pair: " + localJSONException.getMessage();
        Log.e("Fingerprint", str4);
        continue;
        mCallbackContext.error(str4);
        mPluginResult = new PluginResult(PluginResult.Status.ERROR);
        continue;
      }
      if (i == 0) {
        continue;
      }
      mCallbackContext.success(localJSONObject);
      mPluginResult = new PluginResult(PluginResult.Status.OK);
      mCallbackContext.sendPluginResult(mPluginResult);
      return;
      localJSONObject.put("withPassword", true);
      if (!initCipher()) {
        createKey();
      }
    }
  }
  
  public static void onCancelled()
  {
    mCallbackContext.error("Cancelled");
  }
  
  public static boolean setPluginResultError(String paramString)
  {
    mCallbackContext.error(paramString);
    mPluginResult = new PluginResult(PluginResult.Status.ERROR);
    return false;
  }
  
  private static byte[] tryEncrypt()
    throws BadPaddingException, IllegalBlockSizeException
  {
    return mCipher.doFinal(mClientSecret.getBytes());
  }
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    mCallbackContext = paramCallbackContext;
    Log.v("Fingerprint", "Fingerprint action: " + paramString);
    if (Build.VERSION.SDK_INT < 23)
    {
      Log.e("Fingerprint", "minimum SDK version 23 required");
      mPluginResult = new PluginResult(PluginResult.Status.ERROR);
      mCallbackContext.error("minimum SDK version 23 required");
      mCallbackContext.sendPluginResult(mPluginResult);
      return true;
    }
    paramJSONArray = paramJSONArray.getJSONObject(0);
    if (paramString.equals("authenticate"))
    {
      if ((!paramJSONArray.has("clientId")) || (!paramJSONArray.has("clientSecret")))
      {
        mPluginResult = new PluginResult(PluginResult.Status.ERROR);
        mCallbackContext.error("Missing required parameters");
        mCallbackContext.sendPluginResult(mPluginResult);
        return true;
      }
      mClientId = paramJSONArray.getString("clientId");
      mClientSecret = paramJSONArray.getString("clientSecret");
      if (paramJSONArray.has("disableBackup")) {
        mDisableBackup = paramJSONArray.getBoolean("disableBackup");
      }
      paramString = this.cordova.getActivity().getResources();
      paramJSONArray = paramString.getDisplayMetrics();
      paramString.updateConfiguration(paramString.getConfiguration(), paramJSONArray);
      if (isFingerprintAuthAvailable())
      {
        paramJSONArray = getSecretKey();
        paramString = paramJSONArray;
        if (paramJSONArray == null)
        {
          paramString = paramJSONArray;
          if (createKey()) {
            paramString = getSecretKey();
          }
        }
        if (((paramString == null) || (initCipher())) || (paramString != null))
        {
          this.cordova.getActivity().runOnUiThread(new Runnable()
          {
            public void run()
            {
              Fingerprint.this.mFragment = new FingerprintAuthenticationDialogFragment();
              Object localObject = new Bundle();
              ((Bundle)localObject).putBoolean("disableBackup", Fingerprint.mDisableBackup);
              Fingerprint.this.mFragment.setArguments((Bundle)localObject);
              if (Fingerprint.access$100())
              {
                Fingerprint.this.mFragment.setCancelable(false);
                Fingerprint.this.mFragment.setCryptoObject(new FingerprintManager.CryptoObject(Fingerprint.mCipher));
                localObject = Fingerprint.this.cordova.getActivity().getFragmentManager().beginTransaction();
                ((FragmentTransaction)localObject).add(Fingerprint.this.mFragment, "FpAuthDialog");
                ((FragmentTransaction)localObject).commitAllowingStateLoss();
                return;
              }
              if (!Fingerprint.mDisableBackup)
              {
                Fingerprint.this.mFragment.setCryptoObject(new FingerprintManager.CryptoObject(Fingerprint.mCipher));
                Fingerprint.this.mFragment.setStage(FingerprintAuthenticationDialogFragment.Stage.NEW_FINGERPRINT_ENROLLED);
                localObject = Fingerprint.this.cordova.getActivity().getFragmentManager().beginTransaction();
                ((FragmentTransaction)localObject).add(Fingerprint.this.mFragment, "FpAuthDialog");
                ((FragmentTransaction)localObject).commitAllowingStateLoss();
                return;
              }
              Fingerprint.mCallbackContext.error("Failed to init Cipher and backup disabled.");
              Fingerprint.mPluginResult = new PluginResult(PluginResult.Status.ERROR);
              Fingerprint.mCallbackContext.sendPluginResult(Fingerprint.mPluginResult);
            }
          });
          mPluginResult.setKeepCallback(true);
          return true;
        }
        mCallbackContext.sendPluginResult(mPluginResult);
        return true;
      }
      mPluginResult = new PluginResult(PluginResult.Status.ERROR);
      mCallbackContext.error("Fingerprint authentication not available");
      mCallbackContext.sendPluginResult(mPluginResult);
      return true;
    }
    if (paramString.equals("isAvailable"))
    {
      if ((isFingerprintAuthAvailable()) && (this.mFingerPrintManager.isHardwareDetected()) && (this.mFingerPrintManager.hasEnrolledFingerprints()))
      {
        mPluginResult = new PluginResult(PluginResult.Status.OK, "finger");
        mCallbackContext.success("finger");
      }
      for (;;)
      {
        mCallbackContext.sendPluginResult(mPluginResult);
        return true;
        mPluginResult = new PluginResult(PluginResult.Status.ERROR);
        mCallbackContext.error("Fingerprint authentication not ready");
      }
    }
    return false;
  }
  
  /* Error */
  public void initialize(CordovaInterface paramCordovaInterface, org.apache.cordova.CordovaWebView paramCordovaWebView)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: invokespecial 392	org/apache/cordova/CordovaPlugin:initialize	(Lorg/apache/cordova/CordovaInterface;Lorg/apache/cordova/CordovaWebView;)V
    //   6: ldc 19
    //   8: ldc_w 394
    //   11: invokestatic 291	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   14: pop
    //   15: aload_1
    //   16: invokeinterface 339 1 0
    //   21: invokevirtual 398	android/app/Activity:getApplicationContext	()Landroid/content/Context;
    //   24: invokevirtual 403	android/content/Context:getPackageName	()Ljava/lang/String;
    //   27: putstatic 405	de/niklasmerz/cordova/fingerprint/Fingerprint:packageName	Ljava/lang/String;
    //   30: new 233	org/apache/cordova/PluginResult
    //   33: dup
    //   34: getstatic 408	org/apache/cordova/PluginResult$Status:NO_RESULT	Lorg/apache/cordova/PluginResult$Status;
    //   37: invokespecial 242	org/apache/cordova/PluginResult:<init>	(Lorg/apache/cordova/PluginResult$Status;)V
    //   40: putstatic 244	de/niklasmerz/cordova/fingerprint/Fingerprint:mPluginResult	Lorg/apache/cordova/PluginResult;
    //   43: getstatic 297	android/os/Build$VERSION:SDK_INT	I
    //   46: bipush 23
    //   48: if_icmpge +4 -> 52
    //   51: return
    //   52: aload_0
    //   53: aload_1
    //   54: invokeinterface 339 1 0
    //   59: ldc_w 410
    //   62: invokevirtual 414	android/app/Activity:getSystemService	(Ljava/lang/Class;)Ljava/lang/Object;
    //   65: checkcast 410	android/app/KeyguardManager
    //   68: putfield 416	de/niklasmerz/cordova/fingerprint/Fingerprint:mKeyguardManager	Landroid/app/KeyguardManager;
    //   71: aload_0
    //   72: aload_1
    //   73: invokeinterface 339 1 0
    //   78: invokevirtual 398	android/app/Activity:getApplicationContext	()Landroid/content/Context;
    //   81: ldc -66
    //   83: invokevirtual 417	android/content/Context:getSystemService	(Ljava/lang/Class;)Ljava/lang/Object;
    //   86: checkcast 190	android/hardware/fingerprint/FingerprintManager
    //   89: putfield 188	de/niklasmerz/cordova/fingerprint/Fingerprint:mFingerPrintManager	Landroid/hardware/fingerprint/FingerprintManager;
    //   92: ldc_w 419
    //   95: ldc 13
    //   97: invokestatic 423	javax/crypto/KeyGenerator:getInstance	(Ljava/lang/String;Ljava/lang/String;)Ljavax/crypto/KeyGenerator;
    //   100: putstatic 75	de/niklasmerz/cordova/fingerprint/Fingerprint:mKeyGenerator	Ljavax/crypto/KeyGenerator;
    //   103: ldc 13
    //   105: invokestatic 426	java/security/KeyStore:getInstance	(Ljava/lang/String;)Ljava/security/KeyStore;
    //   108: putstatic 67	de/niklasmerz/cordova/fingerprint/Fingerprint:mKeyStore	Ljava/security/KeyStore;
    //   111: ldc_w 428
    //   114: invokestatic 431	javax/crypto/Cipher:getInstance	(Ljava/lang/String;)Ljavax/crypto/Cipher;
    //   117: putstatic 173	de/niklasmerz/cordova/fingerprint/Fingerprint:mCipher	Ljavax/crypto/Cipher;
    //   120: return
    //   121: astore_1
    //   122: new 433	java/lang/RuntimeException
    //   125: dup
    //   126: ldc_w 435
    //   129: aload_1
    //   130: invokespecial 438	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   133: athrow
    //   134: astore_1
    //   135: new 433	java/lang/RuntimeException
    //   138: dup
    //   139: ldc_w 440
    //   142: aload_1
    //   143: invokespecial 438	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   146: athrow
    //   147: astore_1
    //   148: new 433	java/lang/RuntimeException
    //   151: dup
    //   152: ldc_w 440
    //   155: aload_1
    //   156: invokespecial 438	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   159: athrow
    //   160: astore_1
    //   161: new 433	java/lang/RuntimeException
    //   164: dup
    //   165: ldc_w 442
    //   168: aload_1
    //   169: invokespecial 438	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   172: athrow
    //   173: astore_1
    //   174: new 433	java/lang/RuntimeException
    //   177: dup
    //   178: ldc_w 435
    //   181: aload_1
    //   182: invokespecial 438	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   185: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	186	0	this	Fingerprint
    //   0	186	1	paramCordovaInterface	CordovaInterface
    //   0	186	2	paramCordovaWebView	org.apache.cordova.CordovaWebView
    // Exception table:
    //   from	to	target	type
    //   111	120	121	java/security/NoSuchAlgorithmException
    //   92	111	134	java/security/NoSuchAlgorithmException
    //   92	111	147	java/security/NoSuchProviderException
    //   92	111	160	java/security/KeyStoreException
    //   111	120	173	javax/crypto/NoSuchPaddingException
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\niklasmerz\cordova\fingerprint\Fingerprint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */