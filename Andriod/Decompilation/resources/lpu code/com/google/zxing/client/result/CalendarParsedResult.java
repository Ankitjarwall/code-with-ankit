package com.google.zxing.client.result;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CalendarParsedResult
  extends ParsedResult
{
  private static final Pattern DATE_TIME = Pattern.compile("[0-9]{8}(T[0-9]{6}Z?)?");
  private static final Pattern RFC2445_DURATION = Pattern.compile("P(?:(\\d+)W)?(?:(\\d+)D)?(?:T(?:(\\d+)H)?(?:(\\d+)M)?(?:(\\d+)S)?)?");
  private static final long[] RFC2445_DURATION_FIELD_UNITS = { 604800000L, 86400000L, 3600000L, 60000L, 1000L };
  private final String[] attendees;
  private final String description;
  private final Date end;
  private final boolean endAllDay;
  private final double latitude;
  private final String location;
  private final double longitude;
  private final String organizer;
  private final Date start;
  private final boolean startAllDay;
  private final String summary;
  
  public CalendarParsedResult(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String[] paramArrayOfString, String paramString7, double paramDouble1, double paramDouble2)
  {
    super(ParsedResultType.CALENDAR);
    this.summary = paramString1;
    for (;;)
    {
      long l;
      try
      {
        this.start = parseDate(paramString2);
        if (paramString3 != null) {
          break label156;
        }
        l = parseDurationMS(paramString4);
        if (l < 0L)
        {
          paramString1 = null;
          this.end = paramString1;
          if (paramString2.length() != 8) {
            break label180;
          }
          bool = true;
          this.startAllDay = bool;
          if ((paramString3 == null) || (paramString3.length() != 8)) {
            break label186;
          }
          bool = true;
          this.endAllDay = bool;
          this.location = paramString5;
          this.organizer = paramString6;
          this.attendees = paramArrayOfString;
          this.description = paramString7;
          this.latitude = paramDouble1;
          this.longitude = paramDouble2;
          return;
        }
      }
      catch (ParseException paramString1)
      {
        throw new IllegalArgumentException(paramString1.toString());
      }
      paramString1 = new Date(this.start.getTime() + l);
      continue;
      try
      {
        label156:
        this.end = parseDate(paramString3);
      }
      catch (ParseException paramString1)
      {
        throw new IllegalArgumentException(paramString1.toString());
      }
      label180:
      boolean bool = false;
      continue;
      label186:
      bool = false;
    }
  }
  
  private static DateFormat buildDateFormat()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    return localSimpleDateFormat;
  }
  
  private static DateFormat buildDateTimeFormat()
  {
    return new SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.ENGLISH);
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
  
  private static Date parseDate(String paramString)
    throws ParseException
  {
    if (!DATE_TIME.matcher(paramString).matches()) {
      throw new ParseException(paramString, 0);
    }
    if (paramString.length() == 8) {
      return buildDateFormat().parse(paramString);
    }
    if ((paramString.length() == 16) && (paramString.charAt(15) == 'Z'))
    {
      paramString = buildDateTimeFormat().parse(paramString.substring(0, 15));
      GregorianCalendar localGregorianCalendar = new GregorianCalendar();
      long l = paramString.getTime() + localGregorianCalendar.get(15);
      localGregorianCalendar.setTime(new Date(l));
      return new Date(l + localGregorianCalendar.get(16));
    }
    return buildDateTimeFormat().parse(paramString);
  }
  
  private static long parseDurationMS(CharSequence paramCharSequence)
  {
    long l2 = -1L;
    if (paramCharSequence == null) {}
    do
    {
      return l2;
      paramCharSequence = RFC2445_DURATION.matcher(paramCharSequence);
    } while (!paramCharSequence.matches());
    long l1 = 0L;
    int i = 0;
    for (;;)
    {
      l2 = l1;
      if (i >= RFC2445_DURATION_FIELD_UNITS.length) {
        break;
      }
      String str = paramCharSequence.group(i + 1);
      l2 = l1;
      if (str != null) {
        l2 = l1 + RFC2445_DURATION_FIELD_UNITS[i] * Integer.parseInt(str);
      }
      i += 1;
      l1 = l2;
    }
  }
  
  public String[] getAttendees()
  {
    return this.attendees;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public String getDisplayResult()
  {
    StringBuilder localStringBuilder = new StringBuilder(100);
    maybeAppend(this.summary, localStringBuilder);
    maybeAppend(format(this.startAllDay, this.start), localStringBuilder);
    maybeAppend(format(this.endAllDay, this.end), localStringBuilder);
    maybeAppend(this.location, localStringBuilder);
    maybeAppend(this.organizer, localStringBuilder);
    maybeAppend(this.attendees, localStringBuilder);
    maybeAppend(this.description, localStringBuilder);
    return localStringBuilder.toString();
  }
  
  public Date getEnd()
  {
    return this.end;
  }
  
  public double getLatitude()
  {
    return this.latitude;
  }
  
  public String getLocation()
  {
    return this.location;
  }
  
  public double getLongitude()
  {
    return this.longitude;
  }
  
  public String getOrganizer()
  {
    return this.organizer;
  }
  
  public Date getStart()
  {
    return this.start;
  }
  
  public String getSummary()
  {
    return this.summary;
  }
  
  public boolean isEndAllDay()
  {
    return this.endAllDay;
  }
  
  public boolean isStartAllDay()
  {
    return this.startAllDay;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\CalendarParsedResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */