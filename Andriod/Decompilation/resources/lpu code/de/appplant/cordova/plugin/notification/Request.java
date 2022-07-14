package de.appplant.cordova.plugin.notification;

import de.appplant.cordova.plugin.notification.trigger.DateTrigger;
import de.appplant.cordova.plugin.notification.trigger.DateTrigger.Unit;
import de.appplant.cordova.plugin.notification.trigger.IntervalTrigger;
import de.appplant.cordova.plugin.notification.trigger.MatchTrigger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;

public final class Request
{
  public static final String EXTRA_LAST = "NOTIFICATION_LAST";
  static final String EXTRA_OCCURRENCE = "NOTIFICATION_OCCURRENCE";
  private final int count;
  private final Options options;
  private final JSONObject spec;
  private final DateTrigger trigger;
  private Date triggerDate;
  
  public Request(Options paramOptions)
  {
    this.options = paramOptions;
    this.spec = paramOptions.getTrigger();
    this.count = Math.max(this.spec.optInt("count"), 1);
    this.trigger = buildTrigger();
    this.triggerDate = this.trigger.getNextTriggerDate(getBaseDate());
  }
  
  private DateTrigger buildTrigger()
  {
    if ((this.spec.opt("every") instanceof JSONObject)) {
      return new MatchTrigger(getMatchingComponents(), getSpecialMatchingComponents());
    }
    DateTrigger.Unit localUnit = getUnit();
    return new IntervalTrigger(getTicks(), localUnit);
  }
  
  private Date getBaseDate()
  {
    if (this.spec.has("at")) {
      return new Date(this.spec.optLong("at", 0L));
    }
    if (this.spec.has("firstAt")) {
      return new Date(this.spec.optLong("firstAt", 0L));
    }
    if (this.spec.has("after")) {
      return new Date(this.spec.optLong("after", 0L));
    }
    return new Date();
  }
  
  private List<Integer> getMatchingComponents()
  {
    JSONObject localJSONObject = this.spec.optJSONObject("every");
    return Arrays.asList(new Integer[] { (Integer)localJSONObject.opt("minute"), (Integer)localJSONObject.opt("hour"), (Integer)localJSONObject.opt("day"), (Integer)localJSONObject.opt("month"), (Integer)localJSONObject.opt("year") });
  }
  
  private Date getNextTriggerDate()
  {
    return this.trigger.getNextTriggerDate(this.triggerDate);
  }
  
  private List<Integer> getSpecialMatchingComponents()
  {
    JSONObject localJSONObject = this.spec.optJSONObject("every");
    return Arrays.asList(new Integer[] { (Integer)localJSONObject.opt("weekday"), (Integer)localJSONObject.opt("weekdayOrdinal"), (Integer)localJSONObject.opt("weekOfMonth"), (Integer)localJSONObject.opt("quarter") });
  }
  
  private int getTicks()
  {
    Object localObject = this.spec.opt("every");
    if (this.spec.has("at")) {}
    do
    {
      return 0;
      if (this.spec.has("in")) {
        return this.spec.optInt("in", 0);
      }
      if ((localObject instanceof String)) {
        return 1;
      }
    } while ((localObject instanceof JSONObject));
    return this.spec.optInt("every", 0);
  }
  
  private DateTrigger.Unit getUnit()
  {
    Object localObject = this.spec.opt("every");
    String str = "SECOND";
    if (this.spec.has("unit")) {
      str = this.spec.optString("unit", "second");
    }
    for (;;)
    {
      return DateTrigger.Unit.valueOf(str.toUpperCase());
      if ((localObject instanceof String)) {
        str = this.spec.optString("every", "second");
      }
    }
  }
  
  private boolean hasNext()
  {
    return (this.triggerDate != null) && (getOccurrence() <= this.count);
  }
  
  String getIdentifier()
  {
    return this.options.getId().toString() + "-" + getOccurrence();
  }
  
  int getOccurrence()
  {
    return this.trigger.getOccurrence();
  }
  
  public Options getOptions()
  {
    return this.options;
  }
  
  Date getTriggerDate()
  {
    Calendar localCalendar = Calendar.getInstance();
    if (this.triggerDate == null) {}
    long l;
    do
    {
      return null;
      l = this.triggerDate.getTime();
    } while ((localCalendar.getTimeInMillis() - l > 60000L) || (l >= this.spec.optLong("before", 1L + l)));
    return this.triggerDate;
  }
  
  boolean moveNext()
  {
    if (hasNext()) {}
    for (this.triggerDate = getNextTriggerDate(); this.triggerDate != null; this.triggerDate = null) {
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\appplant\cordova\plugin\notification\Request.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */