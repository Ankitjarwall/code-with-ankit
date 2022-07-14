package org.apache.cordova;

public final class PluginEntry
{
  public final boolean onload;
  public final CordovaPlugin plugin;
  public final String pluginClass;
  public final String service;
  
  public PluginEntry(String paramString1, String paramString2, boolean paramBoolean)
  {
    this(paramString1, paramString2, paramBoolean, null);
  }
  
  private PluginEntry(String paramString1, String paramString2, boolean paramBoolean, CordovaPlugin paramCordovaPlugin)
  {
    this.service = paramString1;
    this.pluginClass = paramString2;
    this.onload = paramBoolean;
    this.plugin = paramCordovaPlugin;
  }
  
  public PluginEntry(String paramString, CordovaPlugin paramCordovaPlugin)
  {
    this(paramString, paramCordovaPlugin.getClass().getName(), true, paramCordovaPlugin);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\PluginEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */