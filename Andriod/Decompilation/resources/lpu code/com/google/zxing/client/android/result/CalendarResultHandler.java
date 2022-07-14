package com.google.zxing.client.android.result;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import barcodescanner.xservices.nl.barcodescanner.R.string;
import com.google.zxing.client.result.CalendarParsedResult;
import com.google.zxing.client.result.ParsedResult;
import java.text.DateFormat;
import java.util.Date;

public final class CalendarResultHandler
  extends ResultHandler
{
  private static final String TAG = CalendarResultHandler.class.getSimpleName();
  private static final int[] buttons = { R.string.button_add_calendar };
  
  public CalendarResultHandler(Activity paramActivity, ParsedResult paramParsedResult)
  {
    super(paramActivity, paramParsedResult);
  }
  
  private void addCalendarEvent(String paramString1, Date paramDate1, boolean paramBoolean, Date paramDate2, String paramString2, String paramString3, String[] paramArrayOfString)
  {
    Intent localIntent = new Intent("android.intent.action.INSERT");
    localIntent.setType("vnd.android.cursor.item/event");
    long l = paramDate1.getTime();
    localIntent.putExtra("beginTime", l);
    if (paramBoolean) {
      localIntent.putExtra("allDay", true);
    }
    if (paramDate2 == null) {
      if (paramBoolean) {
        l += 86400000L;
      }
    }
    for (;;)
    {
      localIntent.putExtra("endTime", l);
      localIntent.putExtra("title", paramString1);
      localIntent.putExtra("eventLocation", paramString2);
      localIntent.putExtra("description", paramString3);
      if (paramArrayOfString != null) {
        localIntent.putExtra("android.intent.extra.EMAIL", paramArrayOfString);
      }
      try
      {
        rawLaunchIntent(localIntent);
        return;
      }
      catch (ActivityNotFoundException paramString1)
      {
        Log.w(TAG, "No calendar app available that responds to android.intent.action.INSERT");
        localIntent.setAction("android.intent.action.EDIT");
        launchIntent(localIntent);
      }
      continue;
      l = paramDate2.getTime();
    }
  }
  
  private static String format(boolean paramBoolean, Date paramDate)
  {
    if (paramDate == null) {
      return null;
    }
    if (paramBoolean) {}
    for (DateFormat localDateFormat = DateFormat.getDateInstance(2);; localDateFormat = DateFormat.getDateTimeInstance(2, 2)) {
      return localDateFormat.format(paramDate);
    }
  }
  
  public int getButtonCount()
  {
    return buttons.length;
  }
  
  public int getButtonText(int paramInt)
  {
    return buttons[paramInt];
  }
  
  public CharSequence getDisplayContents()
  {
    CalendarParsedResult localCalendarParsedResult = (CalendarParsedResult)getResult();
    StringBuilder localStringBuilder = new StringBuilder(100);
    ParsedResult.maybeAppend(localCalendarParsedResult.getSummary(), localStringBuilder);
    Date localDate3 = localCalendarParsedResult.getStart();
    ParsedResult.maybeAppend(format(localCalendarParsedResult.isStartAllDay(), localDate3), localStringBuilder);
    Date localDate2 = localCalendarParsedResult.getEnd();
    if (localDate2 != null)
    {
      Date localDate1 = localDate2;
      if (localCalendarParsedResult.isEndAllDay())
      {
        localDate1 = localDate2;
        if (!localDate3.equals(localDate2)) {
          localDate1 = new Date(localDate2.getTime() - 86400000L);
        }
      }
      ParsedResult.maybeAppend(format(localCalendarParsedResult.isEndAllDay(), localDate1), localStringBuilder);
    }
    ParsedResult.maybeAppend(localCalendarParsedResult.getLocation(), localStringBuilder);
    ParsedResult.maybeAppend(localCalendarParsedResult.getOrganizer(), localStringBuilder);
    ParsedResult.maybeAppend(localCalendarParsedResult.getAttendees(), localStringBuilder);
    ParsedResult.maybeAppend(localCalendarParsedResult.getDescription(), localStringBuilder);
    return localStringBuilder.toString();
  }
  
  public int getDisplayTitle()
  {
    return R.string.result_calendar;
  }
  
  public void handleButtonPress(int paramInt)
  {
    CalendarParsedResult localCalendarParsedResult;
    String str2;
    String str3;
    if (paramInt == 0)
    {
      localCalendarParsedResult = (CalendarParsedResult)getResult();
      str2 = localCalendarParsedResult.getDescription();
      str3 = localCalendarParsedResult.getOrganizer();
      str1 = str2;
      if (str3 != null) {
        if (str2 != null) {
          break label76;
        }
      }
    }
    label76:
    for (String str1 = str3;; str1 = str2 + '\n' + str3)
    {
      addCalendarEvent(localCalendarParsedResult.getSummary(), localCalendarParsedResult.getStart(), localCalendarParsedResult.isStartAllDay(), localCalendarParsedResult.getEnd(), localCalendarParsedResult.getLocation(), str1, localCalendarParsedResult.getAttendees());
      return;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\result\CalendarResultHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */