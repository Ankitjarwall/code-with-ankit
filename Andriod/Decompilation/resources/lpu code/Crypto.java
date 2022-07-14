import android.util.Base64;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Crypto
{
  private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
  private static String DELIMITER;
  private static int ITERATION_COUNT = 10000;
  private static int KEY_LENGTH = 0;
  public static final String PBKDF2_DERIVATION_ALGORITHM = "PBKDF2WithHmacSHA1";
  private static final int PKCS5_SALT_LENGTH = 8;
  private static final String TAG = Crypto.class.getSimpleName();
  private static SecureRandom random = new SecureRandom();
  
  static
  {
    DELIMITER = "@~@~@";
    KEY_LENGTH = 256;
  }
  
  public static String decrypt(byte[] paramArrayOfByte1, SecretKey paramSecretKey, byte[] paramArrayOfByte2)
    throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException
  {
    Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    localCipher.init(2, paramSecretKey, new IvParameterSpec(paramArrayOfByte2));
    Log.d(TAG, "Cipher IV: " + toHex(localCipher.getIV()));
    return new String(localCipher.doFinal(paramArrayOfByte1), "UTF-8");
  }
  
  public static String decryptPbkdf2(String paramString1, String paramString2)
    throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException
  {
    paramString1 = paramString1.split(DELIMITER);
    if (paramString1.length != 3) {
      throw new IllegalArgumentException("Invalid encypted text format");
    }
    byte[] arrayOfByte1 = fromBase64(paramString1[0]);
    byte[] arrayOfByte2 = fromBase64(paramString1[1]);
    return decrypt(fromBase64(paramString1[2]), deriveKeyPbkdf2(arrayOfByte1, paramString2), arrayOfByte2);
  }
  
  public static SecretKey deriveKeyPbkdf2(byte[] paramArrayOfByte, String paramString)
    throws NoSuchAlgorithmException, InvalidKeySpecException
  {
    long l1 = System.currentTimeMillis();
    paramArrayOfByte = new PBEKeySpec(paramString.toCharArray(), paramArrayOfByte, ITERATION_COUNT, KEY_LENGTH);
    paramArrayOfByte = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(paramArrayOfByte).getEncoded();
    Log.d(TAG, "key bytes: " + toHex(paramArrayOfByte));
    paramArrayOfByte = new SecretKeySpec(paramArrayOfByte, "AES");
    long l2 = System.currentTimeMillis();
    Log.d(TAG, String.format("PBKDF2 key derivation took %d [ms].", new Object[] { Long.valueOf(l2 - l1) }));
    return paramArrayOfByte;
  }
  
  public static String encrypt(String paramString1, String paramString2)
    throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException
  {
    byte[] arrayOfByte1 = generateSalt();
    paramString2 = deriveKeyPbkdf2(arrayOfByte1, paramString2);
    Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    byte[] arrayOfByte2 = generateIv(localCipher.getBlockSize());
    Log.d(TAG, "IV: " + toHex(arrayOfByte2));
    localCipher.init(1, paramString2, new IvParameterSpec(arrayOfByte2));
    String str = TAG;
    StringBuilder localStringBuilder = new StringBuilder().append("Cipher IV: ");
    if (localCipher.getIV() == null) {}
    for (paramString2 = null;; paramString2 = toHex(localCipher.getIV()))
    {
      Log.d(str, paramString2);
      paramString1 = localCipher.doFinal(paramString1.getBytes("UTF-8"));
      if (arrayOfByte1 == null) {
        break;
      }
      return String.format("%s%s%s%s%s", new Object[] { toBase64(arrayOfByte1), DELIMITER, toBase64(arrayOfByte2), DELIMITER, toBase64(paramString1) });
    }
    return String.format("%s%s%s", new Object[] { toBase64(arrayOfByte2), DELIMITER, toBase64(paramString1) });
  }
  
  public static byte[] fromBase64(String paramString)
  {
    return Base64.decode(paramString, 2);
  }
  
  public static byte[] generateIv(int paramInt)
  {
    byte[] arrayOfByte = new byte[paramInt];
    random.nextBytes(arrayOfByte);
    return arrayOfByte;
  }
  
  public static byte[] generateSalt()
  {
    byte[] arrayOfByte = new byte[8];
    random.nextBytes(arrayOfByte);
    return arrayOfByte;
  }
  
  public static String toBase64(byte[] paramArrayOfByte)
  {
    return Base64.encodeToString(paramArrayOfByte, 2);
  }
  
  public static String toHex(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = paramArrayOfByte.length;
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append(String.format("%02X", new Object[] { Byte.valueOf(paramArrayOfByte[i]) }));
      i += 1;
    }
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\Crypto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */