package android.support.v4.view;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

public abstract class PagerAdapter
{
  public static final int POSITION_NONE = -2;
  public static final int POSITION_UNCHANGED = -1;
  private final DataSetObservable mObservable = new DataSetObservable();
  private DataSetObserver mViewPagerObserver;
  
  @Deprecated
  public void destroyItem(@NonNull View paramView, int paramInt, @NonNull Object paramObject)
  {
    throw new UnsupportedOperationException("Required method destroyItem was not overridden");
  }
  
  public void destroyItem(@NonNull ViewGroup paramViewGroup, int paramInt, @NonNull Object paramObject)
  {
    destroyItem(paramViewGroup, paramInt, paramObject);
  }
  
  @Deprecated
  public void finishUpdate(@NonNull View paramView) {}
  
  public void finishUpdate(@NonNull ViewGroup paramViewGroup)
  {
    finishUpdate(paramViewGroup);
  }
  
  public abstract int getCount();
  
  public int getItemPosition(@NonNull Object paramObject)
  {
    return -1;
  }
  
  @Nullable
  public CharSequence getPageTitle(int paramInt)
  {
    return null;
  }
  
  public float getPageWidth(int paramInt)
  {
    return 1.0F;
  }
  
  @Deprecated
  @NonNull
  public Object instantiateItem(@NonNull View paramView, int paramInt)
  {
    throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
  }
  
  @NonNull
  public Object instantiateItem(@NonNull ViewGroup paramViewGroup, int paramInt)
  {
    return instantiateItem(paramViewGroup, paramInt);
  }
  
  public abstract boolean isViewFromObject(@NonNull View paramView, @NonNull Object paramObject);
  
  public void notifyDataSetChanged()
  {
    try
    {
      if (this.mViewPagerObserver != null) {
        this.mViewPagerObserver.onChanged();
      }
      this.mObservable.notifyChanged();
      return;
    }
    finally {}
  }
  
  public void registerDataSetObserver(@NonNull DataSetObserver paramDataSetObserver)
  {
    this.mObservable.registerObserver(paramDataSetObserver);
  }
  
  public void restoreState(@Nullable Parcelable paramParcelable, @Nullable ClassLoader paramClassLoader) {}
  
  @Nullable
  public Parcelable saveState()
  {
    return null;
  }
  
  @Deprecated
  public void setPrimaryItem(@NonNull View paramView, int paramInt, @NonNull Object paramObject) {}
  
  public void setPrimaryItem(@NonNull ViewGroup paramViewGroup, int paramInt, @NonNull Object paramObject)
  {
    setPrimaryItem(paramViewGroup, paramInt, paramObject);
  }
  
  void setViewPagerObserver(DataSetObserver paramDataSetObserver)
  {
    try
    {
      this.mViewPagerObserver = paramDataSetObserver;
      return;
    }
    finally {}
  }
  
  @Deprecated
  public void startUpdate(@NonNull View paramView) {}
  
  public void startUpdate(@NonNull ViewGroup paramViewGroup)
  {
    startUpdate(paramViewGroup);
  }
  
  public void unregisterDataSetObserver(@NonNull DataSetObserver paramDataSetObserver)
  {
    this.mObservable.unregisterObserver(paramDataSetObserver);
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\view\PagerAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */