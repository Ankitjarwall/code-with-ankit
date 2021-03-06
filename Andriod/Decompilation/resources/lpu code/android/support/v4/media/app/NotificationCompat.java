package android.support.v4.media.app;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.Notification.DecoratedMediaCustomViewStyle;
import android.app.Notification.MediaStyle;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.media.session.MediaSession.Token;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.mediacompat.R.color;
import android.support.mediacompat.R.id;
import android.support.mediacompat.R.integer;
import android.support.mediacompat.R.layout;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.Style;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.widget.RemoteViews;
import java.util.ArrayList;

public class NotificationCompat
{
  public static class DecoratedMediaCustomViewStyle
    extends NotificationCompat.MediaStyle
  {
    private void setBackgroundColor(RemoteViews paramRemoteViews)
    {
      if (this.mBuilder.getColor() != 0) {}
      for (int i = this.mBuilder.getColor();; i = this.mBuilder.mContext.getResources().getColor(R.color.notification_material_background_media_default_color))
      {
        paramRemoteViews.setInt(R.id.status_bar_latest_event_content, "setBackgroundColor", i);
        return;
      }
    }
    
    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 24)
      {
        paramNotificationBuilderWithBuilderAccessor.getBuilder().setStyle(fillInMediaStyle(new Notification.DecoratedMediaCustomViewStyle()));
        return;
      }
      super.apply(paramNotificationBuilderWithBuilderAccessor);
    }
    
    int getBigContentViewLayoutResource(int paramInt)
    {
      if (paramInt <= 3) {
        return R.layout.notification_template_big_media_narrow_custom;
      }
      return R.layout.notification_template_big_media_custom;
    }
    
    int getContentViewLayoutResource()
    {
      if (this.mBuilder.getContentView() != null) {
        return R.layout.notification_template_media_custom;
      }
      return super.getContentViewLayoutResource();
    }
    
    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      Object localObject = null;
      if (Build.VERSION.SDK_INT >= 24) {}
      label71:
      for (;;)
      {
        return (RemoteViews)localObject;
        if (this.mBuilder.getBigContentView() != null) {}
        for (paramNotificationBuilderWithBuilderAccessor = this.mBuilder.getBigContentView();; paramNotificationBuilderWithBuilderAccessor = this.mBuilder.getContentView())
        {
          if (paramNotificationBuilderWithBuilderAccessor == null) {
            break label71;
          }
          RemoteViews localRemoteViews = generateBigContentView();
          buildIntoRemoteViews(localRemoteViews, paramNotificationBuilderWithBuilderAccessor);
          localObject = localRemoteViews;
          if (Build.VERSION.SDK_INT < 21) {
            break;
          }
          setBackgroundColor(localRemoteViews);
          return localRemoteViews;
        }
      }
    }
    
    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 24) {
        return null;
      }
      int i;
      if (this.mBuilder.getContentView() != null)
      {
        i = 1;
        if (Build.VERSION.SDK_INT < 21) {
          break label88;
        }
        if ((i == 0) && (this.mBuilder.getBigContentView() == null)) {
          break label83;
        }
      }
      label83:
      for (int j = 1;; j = 0)
      {
        if (j == 0) {
          break label111;
        }
        paramNotificationBuilderWithBuilderAccessor = generateContentView();
        if (i != 0) {
          buildIntoRemoteViews(paramNotificationBuilderWithBuilderAccessor, this.mBuilder.getContentView());
        }
        setBackgroundColor(paramNotificationBuilderWithBuilderAccessor);
        return paramNotificationBuilderWithBuilderAccessor;
        i = 0;
        break;
      }
      label88:
      paramNotificationBuilderWithBuilderAccessor = generateContentView();
      if (i != 0)
      {
        buildIntoRemoteViews(paramNotificationBuilderWithBuilderAccessor, this.mBuilder.getContentView());
        return paramNotificationBuilderWithBuilderAccessor;
      }
      label111:
      return null;
    }
    
    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      Object localObject = null;
      if (Build.VERSION.SDK_INT >= 24) {}
      label71:
      for (;;)
      {
        return (RemoteViews)localObject;
        if (this.mBuilder.getHeadsUpContentView() != null) {}
        for (paramNotificationBuilderWithBuilderAccessor = this.mBuilder.getHeadsUpContentView();; paramNotificationBuilderWithBuilderAccessor = this.mBuilder.getContentView())
        {
          if (paramNotificationBuilderWithBuilderAccessor == null) {
            break label71;
          }
          RemoteViews localRemoteViews = generateBigContentView();
          buildIntoRemoteViews(localRemoteViews, paramNotificationBuilderWithBuilderAccessor);
          localObject = localRemoteViews;
          if (Build.VERSION.SDK_INT < 21) {
            break;
          }
          setBackgroundColor(localRemoteViews);
          return localRemoteViews;
        }
      }
    }
  }
  
  public static class MediaStyle
    extends NotificationCompat.Style
  {
    private static final int MAX_MEDIA_BUTTONS = 5;
    private static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
    int[] mActionsToShowInCompact = null;
    PendingIntent mCancelButtonIntent;
    boolean mShowCancelButton;
    MediaSessionCompat.Token mToken;
    
    public MediaStyle() {}
    
    public MediaStyle(NotificationCompat.Builder paramBuilder)
    {
      setBuilder(paramBuilder);
    }
    
    private RemoteViews generateMediaActionButton(NotificationCompat.Action paramAction)
    {
      if (paramAction.getActionIntent() == null) {}
      for (int i = 1;; i = 0)
      {
        RemoteViews localRemoteViews = new RemoteViews(this.mBuilder.mContext.getPackageName(), R.layout.notification_media_action);
        localRemoteViews.setImageViewResource(R.id.action0, paramAction.getIcon());
        if (i == 0) {
          localRemoteViews.setOnClickPendingIntent(R.id.action0, paramAction.getActionIntent());
        }
        if (Build.VERSION.SDK_INT >= 15) {
          localRemoteViews.setContentDescription(R.id.action0, paramAction.getTitle());
        }
        return localRemoteViews;
      }
    }
    
    public static MediaSessionCompat.Token getMediaSession(Notification paramNotification)
    {
      paramNotification = android.support.v4.app.NotificationCompat.getExtras(paramNotification);
      if (paramNotification != null) {
        if (Build.VERSION.SDK_INT >= 21)
        {
          paramNotification = paramNotification.getParcelable("android.mediaSession");
          if (paramNotification != null) {
            return MediaSessionCompat.Token.fromToken(paramNotification);
          }
        }
        else
        {
          Object localObject = BundleCompat.getBinder(paramNotification, "android.mediaSession");
          if (localObject != null)
          {
            paramNotification = Parcel.obtain();
            paramNotification.writeStrongBinder((IBinder)localObject);
            paramNotification.setDataPosition(0);
            localObject = (MediaSessionCompat.Token)MediaSessionCompat.Token.CREATOR.createFromParcel(paramNotification);
            paramNotification.recycle();
            return (MediaSessionCompat.Token)localObject;
          }
        }
      }
      return null;
    }
    
    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public void apply(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 21) {
        paramNotificationBuilderWithBuilderAccessor.getBuilder().setStyle(fillInMediaStyle(new Notification.MediaStyle()));
      }
      while (!this.mShowCancelButton) {
        return;
      }
      paramNotificationBuilderWithBuilderAccessor.getBuilder().setOngoing(true);
    }
    
    @RequiresApi(21)
    Notification.MediaStyle fillInMediaStyle(Notification.MediaStyle paramMediaStyle)
    {
      if (this.mActionsToShowInCompact != null) {
        paramMediaStyle.setShowActionsInCompactView(this.mActionsToShowInCompact);
      }
      if (this.mToken != null) {
        paramMediaStyle.setMediaSession((MediaSession.Token)this.mToken.getToken());
      }
      return paramMediaStyle;
    }
    
    RemoteViews generateBigContentView()
    {
      int j = Math.min(this.mBuilder.mActions.size(), 5);
      RemoteViews localRemoteViews1 = applyStandardTemplate(false, getBigContentViewLayoutResource(j), false);
      localRemoteViews1.removeAllViews(R.id.media_actions);
      if (j > 0)
      {
        int i = 0;
        while (i < j)
        {
          RemoteViews localRemoteViews2 = generateMediaActionButton((NotificationCompat.Action)this.mBuilder.mActions.get(i));
          localRemoteViews1.addView(R.id.media_actions, localRemoteViews2);
          i += 1;
        }
      }
      if (this.mShowCancelButton)
      {
        localRemoteViews1.setViewVisibility(R.id.cancel_action, 0);
        localRemoteViews1.setInt(R.id.cancel_action, "setAlpha", this.mBuilder.mContext.getResources().getInteger(R.integer.cancel_button_image_alpha));
        localRemoteViews1.setOnClickPendingIntent(R.id.cancel_action, this.mCancelButtonIntent);
        return localRemoteViews1;
      }
      localRemoteViews1.setViewVisibility(R.id.cancel_action, 8);
      return localRemoteViews1;
    }
    
    RemoteViews generateContentView()
    {
      RemoteViews localRemoteViews1 = applyStandardTemplate(false, getContentViewLayoutResource(), true);
      int k = this.mBuilder.mActions.size();
      int i;
      int j;
      if (this.mActionsToShowInCompact == null)
      {
        i = 0;
        localRemoteViews1.removeAllViews(R.id.media_actions);
        if (i > 0) {
          j = 0;
        }
      }
      else
      {
        for (;;)
        {
          if (j >= i) {
            break label145;
          }
          if (j >= k)
          {
            throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", new Object[] { Integer.valueOf(j), Integer.valueOf(k - 1) }));
            i = Math.min(this.mActionsToShowInCompact.length, 3);
            break;
          }
          RemoteViews localRemoteViews2 = generateMediaActionButton((NotificationCompat.Action)this.mBuilder.mActions.get(this.mActionsToShowInCompact[j]));
          localRemoteViews1.addView(R.id.media_actions, localRemoteViews2);
          j += 1;
        }
      }
      label145:
      if (this.mShowCancelButton)
      {
        localRemoteViews1.setViewVisibility(R.id.end_padder, 8);
        localRemoteViews1.setViewVisibility(R.id.cancel_action, 0);
        localRemoteViews1.setOnClickPendingIntent(R.id.cancel_action, this.mCancelButtonIntent);
        localRemoteViews1.setInt(R.id.cancel_action, "setAlpha", this.mBuilder.mContext.getResources().getInteger(R.integer.cancel_button_image_alpha));
        return localRemoteViews1;
      }
      localRemoteViews1.setViewVisibility(R.id.end_padder, 0);
      localRemoteViews1.setViewVisibility(R.id.cancel_action, 8);
      return localRemoteViews1;
    }
    
    int getBigContentViewLayoutResource(int paramInt)
    {
      if (paramInt <= 3) {
        return R.layout.notification_template_big_media_narrow;
      }
      return R.layout.notification_template_big_media;
    }
    
    int getContentViewLayoutResource()
    {
      return R.layout.notification_template_media;
    }
    
    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 21) {
        return null;
      }
      return generateBigContentView();
    }
    
    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      if (Build.VERSION.SDK_INT >= 21) {
        return null;
      }
      return generateContentView();
    }
    
    public MediaStyle setCancelButtonIntent(PendingIntent paramPendingIntent)
    {
      this.mCancelButtonIntent = paramPendingIntent;
      return this;
    }
    
    public MediaStyle setMediaSession(MediaSessionCompat.Token paramToken)
    {
      this.mToken = paramToken;
      return this;
    }
    
    public MediaStyle setShowActionsInCompactView(int... paramVarArgs)
    {
      this.mActionsToShowInCompact = paramVarArgs;
      return this;
    }
    
    public MediaStyle setShowCancelButton(boolean paramBoolean)
    {
      if (Build.VERSION.SDK_INT < 21) {
        this.mShowCancelButton = paramBoolean;
      }
      return this;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\media\app\NotificationCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */