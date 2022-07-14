package de.appplant.cordova.plugin.notification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Action.Builder;
import android.support.v4.app.NotificationCompat.BigPictureStyle;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.InboxStyle;
import android.support.v4.app.NotificationCompat.MessagingStyle;
import android.support.v4.app.NotificationCompat.MessagingStyle.Message;
import android.support.v4.media.app.NotificationCompat.MediaStyle;
import android.support.v4.media.session.MediaSessionCompat.Token;
import de.appplant.cordova.plugin.notification.action.Action;
import java.util.List;
import java.util.Random;

public final class Builder
{
  private Class<?> clearReceiver;
  private Class<?> clickActivity;
  private final Context context;
  private Bundle extras;
  private final Options options;
  private final Random random = new Random();
  
  public Builder(Options paramOptions)
  {
    this.context = paramOptions.getContext();
    this.options = paramOptions;
  }
  
  private void applyActions(NotificationCompat.Builder paramBuilder)
  {
    Action[] arrayOfAction = this.options.getActions();
    if ((arrayOfAction == null) || (arrayOfAction.length == 0)) {}
    for (;;)
    {
      return;
      int j = arrayOfAction.length;
      int i = 0;
      while (i < j)
      {
        Action localAction = arrayOfAction[i];
        NotificationCompat.Action.Builder localBuilder = new NotificationCompat.Action.Builder(localAction.getIcon(), localAction.getTitle(), getPendingIntentForAction(localAction));
        if (localAction.isWithInput()) {
          localBuilder.addRemoteInput(localAction.getInput());
        }
        paramBuilder.addAction(localBuilder.build());
        i += 1;
      }
    }
  }
  
  private void applyBigPictureStyle(NotificationCompat.Builder paramBuilder, List<Bitmap> paramList)
  {
    Object localObject = this.options.getSummary();
    String str = this.options.getText();
    NotificationCompat.BigPictureStyle localBigPictureStyle = new NotificationCompat.BigPictureStyle(paramBuilder);
    if (localObject == null) {
      localObject = str;
    }
    for (;;)
    {
      paramBuilder.setStyle(localBigPictureStyle.setSummaryText((CharSequence)localObject).bigPicture((Bitmap)paramList.get(0)));
      return;
    }
  }
  
  private void applyBigTextStyle(NotificationCompat.Builder paramBuilder)
  {
    paramBuilder.setStyle(new NotificationCompat.BigTextStyle(paramBuilder).setSummaryText(this.options.getSummary()).bigText(this.options.getText()));
  }
  
  private void applyContentReceiver(NotificationCompat.Builder paramBuilder)
  {
    if (this.clickActivity == null) {
      return;
    }
    Intent localIntent = new Intent(this.context, this.clickActivity).putExtras(this.extras).putExtra("NOTIFICATION_ID", this.options.getId()).putExtra("NOTIFICATION_ACTION_ID", "click").putExtra("NOTIFICATION_LAUNCH", this.options.isLaunchingApp()).setFlags(1073741824);
    int i = this.random.nextInt();
    paramBuilder.setContentIntent(PendingIntent.getActivity(this.context, i, localIntent, 134217728));
  }
  
  private void applyDeleteReceiver(NotificationCompat.Builder paramBuilder)
  {
    if (this.clearReceiver == null) {
      return;
    }
    Intent localIntent = new Intent(this.context, this.clearReceiver).putExtras(this.extras).setAction(this.options.getIdentifier()).putExtra("NOTIFICATION_ID", this.options.getId());
    int i = this.random.nextInt();
    paramBuilder.setDeleteIntent(PendingIntent.getBroadcast(this.context, i, localIntent, 134217728));
  }
  
  private void applyInboxStyle(NotificationCompat.Builder paramBuilder)
  {
    Object localObject = this.options.getText();
    NotificationCompat.InboxStyle localInboxStyle = new NotificationCompat.InboxStyle(paramBuilder).setSummaryText(this.options.getSummary());
    localObject = ((String)localObject).split("\n");
    int j = localObject.length;
    int i = 0;
    while (i < j)
    {
      localInboxStyle.addLine(localObject[i]);
      i += 1;
    }
    paramBuilder.setStyle(localInboxStyle);
  }
  
  private void applyMediaStyle(NotificationCompat.Builder paramBuilder, MediaSessionCompat.Token paramToken)
  {
    paramBuilder.setStyle(new NotificationCompat.MediaStyle(paramBuilder).setMediaSession(paramToken).setShowActionsInCompactView(new int[] { 1 }));
  }
  
  private void applyMessagingStyle(NotificationCompat.Builder paramBuilder, NotificationCompat.MessagingStyle.Message[] paramArrayOfMessage)
  {
    NotificationCompat.MessagingStyle localMessagingStyle = new NotificationCompat.MessagingStyle("Me").setConversationTitle(this.options.getTitle());
    int j = paramArrayOfMessage.length;
    int i = 0;
    while (i < j)
    {
      localMessagingStyle.addMessage(paramArrayOfMessage[i]);
      i += 1;
    }
    paramBuilder.setStyle(localMessagingStyle);
  }
  
  private void applyStyle(NotificationCompat.Builder paramBuilder)
  {
    Object localObject = this.options.getMessages();
    String str = this.options.getSummary();
    if (localObject != null) {
      applyMessagingStyle(paramBuilder, (NotificationCompat.MessagingStyle.Message[])localObject);
    }
    do
    {
      return;
      localObject = this.options.getMediaSessionToken();
      if (localObject != null)
      {
        applyMediaStyle(paramBuilder, (MediaSessionCompat.Token)localObject);
        return;
      }
      localObject = this.options.getAttachments();
      if (((List)localObject).size() > 0)
      {
        applyBigPictureStyle(paramBuilder, (List)localObject);
        return;
      }
      localObject = this.options.getText();
      if ((localObject != null) && (((String)localObject).contains("\n")))
      {
        applyInboxStyle(paramBuilder);
        return;
      }
    } while ((localObject == null) || ((str == null) && (((String)localObject).length() < 45)));
    applyBigTextStyle(paramBuilder);
  }
  
  private NotificationCompat.Builder findOrCreateBuilder()
  {
    NotificationCompat.Builder localBuilder2 = Notification.getCachedBuilder(this.options.getId().intValue());
    NotificationCompat.Builder localBuilder1 = localBuilder2;
    if (localBuilder2 == null) {
      localBuilder1 = new NotificationCompat.Builder(this.context, this.options.getChannel());
    }
    return localBuilder1;
  }
  
  private PendingIntent getPendingIntentForAction(Action paramAction)
  {
    paramAction = new Intent(this.context, this.clickActivity).putExtras(this.extras).putExtra("NOTIFICATION_ID", this.options.getId()).putExtra("NOTIFICATION_ACTION_ID", paramAction.getId()).putExtra("NOTIFICATION_LAUNCH", paramAction.isLaunchingApp()).setFlags(1073741824);
    int i = this.random.nextInt();
    return PendingIntent.getActivity(this.context, i, paramAction, 268435456);
  }
  
  private boolean isUpdate()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.extras != null)
    {
      bool1 = bool2;
      if (this.extras.getBoolean("NOTIFICATION_UPDATE", false)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public Notification build()
  {
    if (this.options.isSilent()) {
      return new Notification(this.context, this.options);
    }
    Uri localUri = this.options.getSound();
    Object localObject = new Bundle();
    ((Bundle)localObject).putInt("NOTIFICATION_ID", this.options.getId().intValue());
    ((Bundle)localObject).putString("NOTIFICATION_SOUND", localUri.toString());
    localObject = findOrCreateBuilder().setDefaults(this.options.getDefaults()).setExtras((Bundle)localObject).setOnlyAlertOnce(false).setChannelId(this.options.getChannel()).setContentTitle(this.options.getTitle()).setContentText(this.options.getText()).setTicker(this.options.getText()).setNumber(this.options.getNumber()).setAutoCancel(this.options.isAutoClear().booleanValue()).setOngoing(this.options.isSticky().booleanValue()).setColor(this.options.getColor()).setVisibility(this.options.getVisibility()).setPriority(this.options.getPriority()).setShowWhen(this.options.getShowWhen()).setUsesChronometer(this.options.isWithProgressBar()).setGroup(this.options.getGroup()).setGroupSummary(this.options.getGroupSummary()).setLights(this.options.getLedColor(), this.options.getLedOn(), this.options.getLedOff());
    if ((localUri != Uri.EMPTY) && (!isUpdate())) {
      ((NotificationCompat.Builder)localObject).setSound(localUri);
    }
    if (this.options.isWithProgressBar()) {
      ((NotificationCompat.Builder)localObject).setProgress(this.options.getProgressMaxValue(), this.options.getProgressValue(), this.options.isIndeterminateProgress());
    }
    if (this.options.hasLargeIcon())
    {
      ((NotificationCompat.Builder)localObject).setSmallIcon(this.options.getSmallIcon());
      ((NotificationCompat.Builder)localObject).setLargeIcon(this.options.getLargeIcon());
    }
    for (;;)
    {
      applyStyle((NotificationCompat.Builder)localObject);
      applyActions((NotificationCompat.Builder)localObject);
      applyDeleteReceiver((NotificationCompat.Builder)localObject);
      applyContentReceiver((NotificationCompat.Builder)localObject);
      return new Notification(this.context, this.options, (NotificationCompat.Builder)localObject);
      ((NotificationCompat.Builder)localObject).setSmallIcon(this.options.getSmallIcon());
    }
  }
  
  public Builder setClearReceiver(Class<?> paramClass)
  {
    this.clearReceiver = paramClass;
    return this;
  }
  
  public Builder setClickActivity(Class<?> paramClass)
  {
    this.clickActivity = paramClass;
    return this;
  }
  
  public Builder setExtras(Bundle paramBundle)
  {
    this.extras = paramBundle;
    return this;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\appplant\cordova\plugin\notification\Builder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */