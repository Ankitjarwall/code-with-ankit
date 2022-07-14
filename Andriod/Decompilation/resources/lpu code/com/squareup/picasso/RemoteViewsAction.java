package com.squareup.picasso;

import android.app.Notification;
import android.app.NotificationManager;
import android.appwidget.AppWidgetManager;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

abstract class RemoteViewsAction
  extends Action<RemoteViewsTarget>
{
  final RemoteViews remoteViews;
  private RemoteViewsTarget target;
  final int viewId;
  
  RemoteViewsAction(Picasso paramPicasso, Request paramRequest, RemoteViews paramRemoteViews, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Object paramObject, String paramString)
  {
    super(paramPicasso, null, paramRequest, paramInt3, paramInt4, paramInt2, null, paramString, paramObject, false);
    this.remoteViews = paramRemoteViews;
    this.viewId = paramInt1;
  }
  
  void complete(Bitmap paramBitmap, Picasso.LoadedFrom paramLoadedFrom)
  {
    this.remoteViews.setImageViewBitmap(this.viewId, paramBitmap);
    update();
  }
  
  public void error()
  {
    if (this.errorResId != 0) {
      setImageResource(this.errorResId);
    }
  }
  
  RemoteViewsTarget getTarget()
  {
    if (this.target == null) {
      this.target = new RemoteViewsTarget(this.remoteViews, this.viewId);
    }
    return this.target;
  }
  
  void setImageResource(int paramInt)
  {
    this.remoteViews.setImageViewResource(this.viewId, paramInt);
    update();
  }
  
  abstract void update();
  
  static class AppWidgetAction
    extends RemoteViewsAction
  {
    private final int[] appWidgetIds;
    
    AppWidgetAction(Picasso paramPicasso, Request paramRequest, RemoteViews paramRemoteViews, int paramInt1, int[] paramArrayOfInt, int paramInt2, int paramInt3, String paramString, Object paramObject, int paramInt4)
    {
      super(paramRequest, paramRemoteViews, paramInt1, paramInt4, paramInt2, paramInt3, paramObject, paramString);
      this.appWidgetIds = paramArrayOfInt;
    }
    
    void update()
    {
      AppWidgetManager.getInstance(this.picasso.context).updateAppWidget(this.appWidgetIds, this.remoteViews);
    }
  }
  
  static class NotificationAction
    extends RemoteViewsAction
  {
    private final Notification notification;
    private final int notificationId;
    
    NotificationAction(Picasso paramPicasso, Request paramRequest, RemoteViews paramRemoteViews, int paramInt1, int paramInt2, Notification paramNotification, int paramInt3, int paramInt4, String paramString, Object paramObject, int paramInt5)
    {
      super(paramRequest, paramRemoteViews, paramInt1, paramInt5, paramInt3, paramInt4, paramObject, paramString);
      this.notificationId = paramInt2;
      this.notification = paramNotification;
    }
    
    void update()
    {
      ((NotificationManager)Utils.getService(this.picasso.context, "notification")).notify(this.notificationId, this.notification);
    }
  }
  
  static class RemoteViewsTarget
  {
    final RemoteViews remoteViews;
    final int viewId;
    
    RemoteViewsTarget(RemoteViews paramRemoteViews, int paramInt)
    {
      this.remoteViews = paramRemoteViews;
      this.viewId = paramInt;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if ((paramObject == null) || (getClass() != paramObject.getClass())) {
          return false;
        }
        paramObject = (RemoteViewsTarget)paramObject;
      } while ((this.viewId == ((RemoteViewsTarget)paramObject).viewId) && (this.remoteViews.equals(((RemoteViewsTarget)paramObject).remoteViews)));
      return false;
    }
    
    public int hashCode()
    {
      return this.remoteViews.hashCode() * 31 + this.viewId;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\squareup\picasso\RemoteViewsAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */