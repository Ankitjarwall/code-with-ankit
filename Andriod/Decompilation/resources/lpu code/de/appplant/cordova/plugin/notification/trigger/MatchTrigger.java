package de.appplant.cordova.plugin.notification.trigger;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MatchTrigger
  extends IntervalTrigger
{
  private static DateTrigger.Unit[] INTERVALS = { null, DateTrigger.Unit.MINUTE, DateTrigger.Unit.HOUR, DateTrigger.Unit.DAY, DateTrigger.Unit.MONTH, DateTrigger.Unit.YEAR };
  private static int[] WEEKDAYS = { 0, 2, 3, 4, 5, 6, 7, 1 };
  private static int[] WEEKDAYS_REV = { 0, 7, 1, 2, 3, 4, 5, 6 };
  private final List<Integer> matchers;
  private final List<Integer> specials;
  
  public MatchTrigger(List<Integer> paramList1, List<Integer> paramList2)
  {
    super(1, getUnit(paramList1, paramList2));
    if (paramList2.get(0) != null) {
      paramList2.set(0, Integer.valueOf(WEEKDAYS[((Integer)paramList2.get(0)).intValue()]));
    }
    this.matchers = paramList1;
    this.specials = paramList2;
  }
  
  private void addToDate(Calendar paramCalendar1, Calendar paramCalendar2, int paramInt1, int paramInt2)
  {
    paramCalendar1.set(paramInt1, paramCalendar2.get(paramInt1));
    paramCalendar1.add(paramInt1, paramInt2);
  }
  
  private Date applySpecials(Calendar paramCalendar)
  {
    if ((this.specials.get(2) != null) && (!setWeekOfMonth(paramCalendar))) {}
    while ((this.specials.get(0) != null) && (!setDayOfWeek(paramCalendar))) {
      return null;
    }
    return paramCalendar.getTime();
  }
  
  private Calendar getBaseTriggerDate(Date paramDate)
  {
    paramDate = getCal(paramDate);
    paramDate.set(13, 0);
    if (this.matchers.get(0) != null)
    {
      paramDate.set(12, ((Integer)this.matchers.get(0)).intValue());
      if (this.matchers.get(1) == null) {
        break label199;
      }
      paramDate.set(11, ((Integer)this.matchers.get(1)).intValue());
    }
    for (;;)
    {
      if (this.matchers.get(2) != null) {
        paramDate.set(5, ((Integer)this.matchers.get(2)).intValue());
      }
      if (this.matchers.get(3) != null) {
        paramDate.set(2, ((Integer)this.matchers.get(3)).intValue() - 1);
      }
      if (this.matchers.get(4) != null) {
        paramDate.set(1, ((Integer)this.matchers.get(4)).intValue());
      }
      return paramDate;
      paramDate.set(12, 0);
      break;
      label199:
      paramDate.set(11, 0);
    }
  }
  
  private Date getTriggerDate(Date paramDate)
  {
    Object localObject = null;
    Calendar localCalendar1 = getBaseTriggerDate(paramDate);
    Calendar localCalendar2 = getCal(paramDate);
    if (localCalendar1.compareTo(localCalendar2) >= 0) {
      paramDate = applySpecials(localCalendar1);
    }
    do
    {
      do
      {
        return paramDate;
        paramDate = (Date)localObject;
      } while (this.unit == null);
      paramDate = (Date)localObject;
    } while (localCalendar1.get(1) < localCalendar2.get(1));
    if (localCalendar1.get(2) < localCalendar2.get(2)) {
      switch (this.unit)
      {
      }
    }
    for (;;)
    {
      return applySpecials(localCalendar1);
      paramDate = (Date)localObject;
      if (this.matchers.get(4) != null) {
        break;
      }
      addToDate(localCalendar1, localCalendar2, 1, 1);
      continue;
      addToDate(localCalendar1, localCalendar2, 1, 1);
      continue;
      if (localCalendar1.get(6) < localCalendar2.get(6)) {
        switch (this.unit)
        {
        case ???: 
        case ???: 
        default: 
          break;
        case ???: 
        case ???: 
          if (this.matchers.get(3) == null)
          {
            addToDate(localCalendar1, localCalendar2, 2, 1);
            continue;
          }
          paramDate = (Date)localObject;
          if (this.matchers.get(4) != null) {
            break;
          }
          addToDate(localCalendar1, localCalendar2, 1, 1);
          break;
        case ???: 
          addToDate(localCalendar1, localCalendar2, 2, 1);
          break;
        case ???: 
          addToDate(localCalendar1, localCalendar2, 1, 1);
          break;
        }
      }
      if (localCalendar1.get(11) < localCalendar2.get(11)) {
        switch (this.unit)
        {
        default: 
          break;
        case ???: 
          if (this.matchers.get(2) == null)
          {
            addToDate(localCalendar1, localCalendar2, 6, 1);
            continue;
          }
          paramDate = (Date)localObject;
          if (this.matchers.get(3) != null) {
            break;
          }
          addToDate(localCalendar1, localCalendar2, 2, 1);
          break;
        case ???: 
          addToDate(localCalendar1, localCalendar2, 11, 0);
          break;
        case ???: 
        case ???: 
          addToDate(localCalendar1, localCalendar2, 6, 1);
          break;
        case ???: 
          addToDate(localCalendar1, localCalendar2, 2, 1);
          break;
        case ???: 
          addToDate(localCalendar1, localCalendar2, 1, 1);
          break;
        }
      }
      if (localCalendar1.get(12) < localCalendar2.get(12)) {
        switch (this.unit)
        {
        default: 
          break;
        case ???: 
          addToDate(localCalendar1, localCalendar2, 12, 1);
          break;
        case ???: 
          addToDate(localCalendar1, localCalendar2, 11, 1);
          break;
        case ???: 
        case ???: 
          addToDate(localCalendar1, localCalendar2, 6, 1);
          break;
        case ???: 
          addToDate(localCalendar1, localCalendar2, 2, 1);
          break;
        case ???: 
          addToDate(localCalendar1, localCalendar2, 1, 1);
        }
      }
    }
  }
  
  private static DateTrigger.Unit getUnit(List<Integer> paramList1, List<Integer> paramList2)
  {
    DateTrigger.Unit localUnit = INTERVALS[(paramList1.indexOf(null) + 1)];
    paramList1 = null;
    if (paramList2.get(0) != null) {
      paramList1 = DateTrigger.Unit.WEEK;
    }
    if (paramList1 == null) {
      return localUnit;
    }
    if (localUnit.compareTo(paramList1) < 0) {}
    for (;;)
    {
      return paramList1;
      paramList1 = localUnit;
    }
  }
  
  private boolean setDayOfWeek(Calendar paramCalendar)
  {
    paramCalendar.setFirstDayOfWeek(2);
    int i = WEEKDAYS_REV[paramCalendar.get(7)];
    int j = paramCalendar.get(2);
    int k = paramCalendar.get(1);
    int m = WEEKDAYS_REV[((Integer)this.specials.get(0)).intValue()];
    if (this.matchers.get(2) != null) {
      return false;
    }
    if (i > m)
    {
      if (this.specials.get(2) != null) {
        break label137;
      }
      paramCalendar.add(3, 1);
    }
    for (;;)
    {
      paramCalendar.set(7, ((Integer)this.specials.get(0)).intValue());
      if ((this.matchers.get(3) == null) || (paramCalendar.get(2) == j)) {
        break label183;
      }
      return false;
      label137:
      if (this.matchers.get(3) == null)
      {
        paramCalendar.add(2, 1);
      }
      else
      {
        if (this.matchers.get(4) != null) {
          break;
        }
        paramCalendar.add(1, 1);
      }
    }
    return false;
    label183:
    return (this.matchers.get(4) == null) || (paramCalendar.get(1) == k);
  }
  
  private boolean setWeekOfMonth(Calendar paramCalendar)
  {
    int i = paramCalendar.get(4);
    int k = paramCalendar.get(1);
    int j = ((Integer)this.specials.get(2)).intValue();
    if (i > j)
    {
      if (this.matchers.get(3) == null) {
        paramCalendar.add(2, 1);
      }
      while ((this.matchers.get(4) != null) && (paramCalendar.get(1) != k))
      {
        return false;
        if (this.matchers.get(4) == null) {
          paramCalendar.add(1, 1);
        } else {
          return false;
        }
      }
    }
    k = paramCalendar.get(2);
    paramCalendar.set(4, j);
    if (paramCalendar.get(2) != k)
    {
      paramCalendar.set(5, 1);
      paramCalendar.set(2, k);
    }
    for (;;)
    {
      return true;
      if ((this.matchers.get(2) == null) && (i != j)) {
        paramCalendar.set(7, 2);
      }
    }
  }
  
  public Date getNextTriggerDate(Date paramDate)
  {
    Date localDate = paramDate;
    if (getOccurrence() > 1)
    {
      paramDate = getCal(paramDate);
      addInterval(paramDate);
      localDate = paramDate.getTime();
    }
    incOccurrence();
    return getTriggerDate(localDate);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\de\appplant\cordova\plugin\notification\trigger\MatchTrigger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */