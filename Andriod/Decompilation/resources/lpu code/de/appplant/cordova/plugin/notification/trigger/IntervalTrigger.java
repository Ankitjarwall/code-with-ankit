package de.appplant.cordova.plugin.notification.trigger;

import java.util.Calendar;
import java.util.Date;

public class IntervalTrigger
  extends DateTrigger
{
  private final int ticks;
  final DateTrigger.Unit unit;
  
  public IntervalTrigger(int paramInt, DateTrigger.Unit paramUnit)
  {
    this.ticks = paramInt;
    this.unit = paramUnit;
  }
  
  void addInterval(Calendar paramCalendar)
  {
    switch (this.unit)
    {
    default: 
      return;
    case ???: 
      paramCalendar.add(13, this.ticks);
      return;
    case ???: 
      paramCalendar.add(12, this.ticks);
      return;
    case ???: 
      paramCalendar.add(11, this.ticks);
      return;
    case ???: 
      paramCalendar.add(6, this.ticks);
      return;
    case ???: 
      paramCalendar.add(3, this.ticks);
      return;
    case ???: 
      paramCalendar.add(2, this.ticks);
      return;
    case ???: 
      paramCalendar.add(2, this.ticks * 3);
      return;
    }
    paramCalendar.add(1, this.ticks);
  }
  
  public Date getNextTriggerDate(Date paramDate)
  {
    paramDate = getCal(paramDate);
    addInterval(paramDate);
    incOccurrence();
    return paramDate.getTime();
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\appplant\cordova\plugin\notification\trigger\IntervalTrigger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */