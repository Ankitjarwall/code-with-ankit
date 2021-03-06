package android.support.v4.net;

import android.net.TrafficStats;
import android.os.Build.VERSION;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

public final class TrafficStatsCompat
{
  @Deprecated
  public static void clearThreadStatsTag() {}
  
  @Deprecated
  public static int getThreadStatsTag()
  {
    return TrafficStats.getThreadStatsTag();
  }
  
  @Deprecated
  public static void incrementOperationCount(int paramInt)
  {
    TrafficStats.incrementOperationCount(paramInt);
  }
  
  @Deprecated
  public static void incrementOperationCount(int paramInt1, int paramInt2)
  {
    TrafficStats.incrementOperationCount(paramInt1, paramInt2);
  }
  
  @Deprecated
  public static void setThreadStatsTag(int paramInt)
  {
    TrafficStats.setThreadStatsTag(paramInt);
  }
  
  public static void tagDatagramSocket(@NonNull DatagramSocket paramDatagramSocket)
    throws SocketException
  {
    if (Build.VERSION.SDK_INT >= 24)
    {
      TrafficStats.tagDatagramSocket(paramDatagramSocket);
      return;
    }
    ParcelFileDescriptor localParcelFileDescriptor = ParcelFileDescriptor.fromDatagramSocket(paramDatagramSocket);
    TrafficStats.tagSocket(new DatagramSocketWrapper(paramDatagramSocket, localParcelFileDescriptor.getFileDescriptor()));
    localParcelFileDescriptor.detachFd();
  }
  
  @Deprecated
  public static void tagSocket(Socket paramSocket)
    throws SocketException
  {
    TrafficStats.tagSocket(paramSocket);
  }
  
  public static void untagDatagramSocket(@NonNull DatagramSocket paramDatagramSocket)
    throws SocketException
  {
    if (Build.VERSION.SDK_INT >= 24)
    {
      TrafficStats.untagDatagramSocket(paramDatagramSocket);
      return;
    }
    ParcelFileDescriptor localParcelFileDescriptor = ParcelFileDescriptor.fromDatagramSocket(paramDatagramSocket);
    TrafficStats.untagSocket(new DatagramSocketWrapper(paramDatagramSocket, localParcelFileDescriptor.getFileDescriptor()));
    localParcelFileDescriptor.detachFd();
  }
  
  @Deprecated
  public static void untagSocket(Socket paramSocket)
    throws SocketException
  {
    TrafficStats.untagSocket(paramSocket);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\net\TrafficStatsCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */