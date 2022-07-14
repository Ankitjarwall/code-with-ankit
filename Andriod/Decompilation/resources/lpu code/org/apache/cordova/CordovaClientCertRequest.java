package org.apache.cordova;

import android.annotation.SuppressLint;
import android.webkit.ClientCertRequest;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class CordovaClientCertRequest
  implements ICordovaClientCertRequest
{
  private final ClientCertRequest request;
  
  public CordovaClientCertRequest(ClientCertRequest paramClientCertRequest)
  {
    this.request = paramClientCertRequest;
  }
  
  @SuppressLint({"NewApi"})
  public void cancel()
  {
    this.request.cancel();
  }
  
  @SuppressLint({"NewApi"})
  public String getHost()
  {
    return this.request.getHost();
  }
  
  @SuppressLint({"NewApi"})
  public String[] getKeyTypes()
  {
    return this.request.getKeyTypes();
  }
  
  @SuppressLint({"NewApi"})
  public int getPort()
  {
    return this.request.getPort();
  }
  
  @SuppressLint({"NewApi"})
  public Principal[] getPrincipals()
  {
    return this.request.getPrincipals();
  }
  
  @SuppressLint({"NewApi"})
  public void ignore()
  {
    this.request.ignore();
  }
  
  @SuppressLint({"NewApi"})
  public void proceed(PrivateKey paramPrivateKey, X509Certificate[] paramArrayOfX509Certificate)
  {
    this.request.proceed(paramPrivateKey, paramArrayOfX509Certificate);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\CordovaClientCertRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */