package org.apache.cordova;

import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public abstract interface ICordovaClientCertRequest
{
  public abstract void cancel();
  
  public abstract String getHost();
  
  public abstract String[] getKeyTypes();
  
  public abstract int getPort();
  
  public abstract Principal[] getPrincipals();
  
  public abstract void ignore();
  
  public abstract void proceed(PrivateKey paramPrivateKey, X509Certificate[] paramArrayOfX509Certificate);
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\ICordovaClientCertRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */