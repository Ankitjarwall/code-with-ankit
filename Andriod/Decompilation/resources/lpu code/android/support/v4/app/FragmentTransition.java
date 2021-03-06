package android.support.v4.app;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class FragmentTransition
{
  private static final int[] INVERSE_OPS = { 0, 3, 0, 1, 5, 4, 7, 6, 9, 8 };
  private static final FragmentTransitionImpl PLATFORM_IMPL;
  private static final FragmentTransitionImpl SUPPORT_IMPL;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 21) {}
    for (FragmentTransitionCompat21 localFragmentTransitionCompat21 = new FragmentTransitionCompat21();; localFragmentTransitionCompat21 = null)
    {
      PLATFORM_IMPL = localFragmentTransitionCompat21;
      SUPPORT_IMPL = resolveSupportImpl();
      return;
    }
  }
  
  private static void addSharedElementsWithMatchingNames(ArrayList<View> paramArrayList, ArrayMap<String, View> paramArrayMap, Collection<String> paramCollection)
  {
    int i = paramArrayMap.size() - 1;
    while (i >= 0)
    {
      View localView = (View)paramArrayMap.valueAt(i);
      if (paramCollection.contains(ViewCompat.getTransitionName(localView))) {
        paramArrayList.add(localView);
      }
      i -= 1;
    }
  }
  
  private static void addToFirstInLastOut(BackStackRecord paramBackStackRecord, BackStackRecord.Op paramOp, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    Fragment localFragment = paramOp.fragment;
    if (localFragment == null) {}
    int i3;
    do
    {
      return;
      i3 = localFragment.mContainerId;
    } while (i3 == 0);
    int m;
    label38:
    boolean bool2;
    int i1;
    int i2;
    int n;
    boolean bool1;
    int j;
    int k;
    if (paramBoolean1)
    {
      m = INVERSE_OPS[paramOp.cmd];
      bool2 = false;
      i1 = 0;
      i2 = 0;
      n = 0;
      i = i2;
      bool1 = bool2;
      j = n;
      k = i1;
    }
    switch (m)
    {
    default: 
      k = i1;
      j = n;
      bool1 = bool2;
      i = i2;
    case 2: 
    case 5: 
    case 1: 
    case 7: 
      for (;;)
      {
        Object localObject = (FragmentContainerTransition)paramSparseArray.get(i3);
        paramOp = (BackStackRecord.Op)localObject;
        if (bool1)
        {
          paramOp = ensureContainer((FragmentContainerTransition)localObject, paramSparseArray, i3);
          paramOp.lastIn = localFragment;
          paramOp.lastInIsPop = paramBoolean1;
          paramOp.lastInTransaction = paramBackStackRecord;
        }
        if ((!paramBoolean2) && (j != 0))
        {
          if ((paramOp != null) && (paramOp.firstOut == localFragment)) {
            paramOp.firstOut = null;
          }
          localObject = paramBackStackRecord.mManager;
          if ((localFragment.mState < 1) && (((FragmentManagerImpl)localObject).mCurState >= 1) && (!paramBackStackRecord.mReorderingAllowed))
          {
            ((FragmentManagerImpl)localObject).makeActive(localFragment);
            ((FragmentManagerImpl)localObject).moveToState(localFragment, 1, 0, 0, false);
          }
        }
        localObject = paramOp;
        if (i != 0) {
          if (paramOp != null)
          {
            localObject = paramOp;
            if (paramOp.firstOut != null) {}
          }
          else
          {
            localObject = ensureContainer(paramOp, paramSparseArray, i3);
            ((FragmentContainerTransition)localObject).firstOut = localFragment;
            ((FragmentContainerTransition)localObject).firstOutIsPop = paramBoolean1;
            ((FragmentContainerTransition)localObject).firstOutTransaction = paramBackStackRecord;
          }
        }
        if ((paramBoolean2) || (k == 0) || (localObject == null) || (((FragmentContainerTransition)localObject).lastIn != localFragment)) {
          break;
        }
        ((FragmentContainerTransition)localObject).lastIn = null;
        return;
        m = paramOp.cmd;
        break label38;
        if (paramBoolean2) {
          if ((localFragment.mHiddenChanged) && (!localFragment.mHidden) && (localFragment.mAdded)) {
            bool1 = true;
          }
        }
        for (;;)
        {
          j = 1;
          i = i2;
          k = i1;
          break;
          bool1 = false;
          continue;
          bool1 = localFragment.mHidden;
        }
        if (!paramBoolean2) {
          break label428;
        }
        bool1 = localFragment.mIsNewlyAdded;
        j = 1;
        i = i2;
        k = i1;
      }
      if ((!localFragment.mAdded) && (!localFragment.mHidden)) {}
      for (bool1 = true;; bool1 = false) {
        break;
      }
    case 4: 
      label428:
      if (paramBoolean2)
      {
        if ((localFragment.mHiddenChanged) && (localFragment.mAdded) && (localFragment.mHidden)) {}
        for (i = 1;; i = 0)
        {
          k = 1;
          bool1 = bool2;
          j = n;
          break;
        }
      }
      if ((localFragment.mAdded) && (!localFragment.mHidden)) {}
      for (i = 1;; i = 0) {
        break;
      }
    }
    if (paramBoolean2)
    {
      if ((!localFragment.mAdded) && (localFragment.mView != null) && (localFragment.mView.getVisibility() == 0) && (localFragment.mPostponedAlpha >= 0.0F)) {}
      for (i = 1;; i = 0)
      {
        k = 1;
        bool1 = bool2;
        j = n;
        break;
      }
    }
    if ((localFragment.mAdded) && (!localFragment.mHidden)) {}
    for (int i = 1;; i = 0) {
      break;
    }
  }
  
  public static void calculateFragments(BackStackRecord paramBackStackRecord, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean)
  {
    int j = paramBackStackRecord.mOps.size();
    int i = 0;
    while (i < j)
    {
      addToFirstInLastOut(paramBackStackRecord, (BackStackRecord.Op)paramBackStackRecord.mOps.get(i), paramSparseArray, false, paramBoolean);
      i += 1;
    }
  }
  
  private static ArrayMap<String, String> calculateNameOverrides(int paramInt1, ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt2, int paramInt3)
  {
    ArrayMap localArrayMap = new ArrayMap();
    paramInt3 -= 1;
    if (paramInt3 >= paramInt2)
    {
      Object localObject = (BackStackRecord)paramArrayList.get(paramInt3);
      if (!((BackStackRecord)localObject).interactsWith(paramInt1)) {}
      boolean bool;
      do
      {
        paramInt3 -= 1;
        break;
        bool = ((Boolean)paramArrayList1.get(paramInt3)).booleanValue();
      } while (((BackStackRecord)localObject).mSharedElementSourceNames == null);
      int j = ((BackStackRecord)localObject).mSharedElementSourceNames.size();
      ArrayList localArrayList2;
      ArrayList localArrayList1;
      label101:
      int i;
      label104:
      String str1;
      if (bool)
      {
        localArrayList2 = ((BackStackRecord)localObject).mSharedElementSourceNames;
        localArrayList1 = ((BackStackRecord)localObject).mSharedElementTargetNames;
        i = 0;
        if (i < j)
        {
          localObject = (String)localArrayList1.get(i);
          str1 = (String)localArrayList2.get(i);
          String str2 = (String)localArrayMap.remove(str1);
          if (str2 == null) {
            break label188;
          }
          localArrayMap.put(localObject, str2);
        }
      }
      for (;;)
      {
        i += 1;
        break label104;
        break;
        localArrayList1 = ((BackStackRecord)localObject).mSharedElementSourceNames;
        localArrayList2 = ((BackStackRecord)localObject).mSharedElementTargetNames;
        break label101;
        label188:
        localArrayMap.put(localObject, str1);
      }
    }
    return localArrayMap;
  }
  
  public static void calculatePopFragments(BackStackRecord paramBackStackRecord, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean)
  {
    if (!paramBackStackRecord.mManager.mContainer.onHasView()) {}
    for (;;)
    {
      return;
      int i = paramBackStackRecord.mOps.size() - 1;
      while (i >= 0)
      {
        addToFirstInLastOut(paramBackStackRecord, (BackStackRecord.Op)paramBackStackRecord.mOps.get(i), paramSparseArray, true, paramBoolean);
        i -= 1;
      }
    }
  }
  
  private static void callSharedElementStartEnd(Fragment paramFragment1, Fragment paramFragment2, boolean paramBoolean1, ArrayMap<String, View> paramArrayMap, boolean paramBoolean2)
  {
    ArrayList localArrayList;
    if (paramBoolean1)
    {
      paramFragment1 = paramFragment2.getEnterTransitionCallback();
      if (paramFragment1 == null) {
        break label109;
      }
      paramFragment2 = new ArrayList();
      localArrayList = new ArrayList();
      if (paramArrayMap != null) {
        break label87;
      }
    }
    label87:
    for (int i = 0;; i = paramArrayMap.size())
    {
      int j = 0;
      while (j < i)
      {
        localArrayList.add(paramArrayMap.keyAt(j));
        paramFragment2.add(paramArrayMap.valueAt(j));
        j += 1;
      }
      paramFragment1 = paramFragment1.getEnterTransitionCallback();
      break;
    }
    if (paramBoolean2)
    {
      paramFragment1.onSharedElementStart(localArrayList, paramFragment2, null);
      label109:
      return;
    }
    paramFragment1.onSharedElementEnd(localArrayList, paramFragment2, null);
  }
  
  private static boolean canHandleAll(FragmentTransitionImpl paramFragmentTransitionImpl, List<Object> paramList)
  {
    int i = 0;
    int j = paramList.size();
    while (i < j)
    {
      if (!paramFragmentTransitionImpl.canHandle(paramList.get(i))) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  private static ArrayMap<String, View> captureInSharedElements(FragmentTransitionImpl paramFragmentTransitionImpl, ArrayMap<String, String> paramArrayMap, Object paramObject, FragmentContainerTransition paramFragmentContainerTransition)
  {
    Fragment localFragment = paramFragmentContainerTransition.lastIn;
    View localView = localFragment.getView();
    if ((paramArrayMap.isEmpty()) || (paramObject == null) || (localView == null))
    {
      paramArrayMap.clear();
      paramObject = null;
    }
    ArrayMap localArrayMap;
    int i;
    label117:
    do
    {
      return (ArrayMap<String, View>)paramObject;
      localArrayMap = new ArrayMap();
      paramFragmentTransitionImpl.findNamedViews(localArrayMap, localView);
      paramFragmentTransitionImpl = paramFragmentContainerTransition.lastInTransaction;
      if (!paramFragmentContainerTransition.lastInIsPop) {
        break;
      }
      paramObject = localFragment.getExitTransitionCallback();
      paramFragmentTransitionImpl = paramFragmentTransitionImpl.mSharedElementSourceNames;
      if (paramFragmentTransitionImpl != null)
      {
        localArrayMap.retainAll(paramFragmentTransitionImpl);
        localArrayMap.retainAll(paramArrayMap.values());
      }
      if (paramObject == null) {
        break label222;
      }
      ((SharedElementCallback)paramObject).onMapSharedElements(paramFragmentTransitionImpl, localArrayMap);
      i = paramFragmentTransitionImpl.size() - 1;
      paramObject = localArrayMap;
    } while (i < 0);
    paramFragmentContainerTransition = (String)paramFragmentTransitionImpl.get(i);
    paramObject = (View)localArrayMap.get(paramFragmentContainerTransition);
    if (paramObject == null)
    {
      paramObject = findKeyForValue(paramArrayMap, paramFragmentContainerTransition);
      if (paramObject != null) {
        paramArrayMap.remove(paramObject);
      }
    }
    for (;;)
    {
      i -= 1;
      break label117;
      paramObject = localFragment.getEnterTransitionCallback();
      paramFragmentTransitionImpl = paramFragmentTransitionImpl.mSharedElementTargetNames;
      break;
      if (!paramFragmentContainerTransition.equals(ViewCompat.getTransitionName((View)paramObject)))
      {
        paramFragmentContainerTransition = findKeyForValue(paramArrayMap, paramFragmentContainerTransition);
        if (paramFragmentContainerTransition != null) {
          paramArrayMap.put(paramFragmentContainerTransition, ViewCompat.getTransitionName((View)paramObject));
        }
      }
    }
    label222:
    retainValues(paramArrayMap, localArrayMap);
    return localArrayMap;
  }
  
  private static ArrayMap<String, View> captureOutSharedElements(FragmentTransitionImpl paramFragmentTransitionImpl, ArrayMap<String, String> paramArrayMap, Object paramObject, FragmentContainerTransition paramFragmentContainerTransition)
  {
    if ((paramArrayMap.isEmpty()) || (paramObject == null))
    {
      paramArrayMap.clear();
      paramObject = null;
    }
    ArrayMap localArrayMap;
    int i;
    label91:
    do
    {
      return (ArrayMap<String, View>)paramObject;
      paramObject = paramFragmentContainerTransition.firstOut;
      localArrayMap = new ArrayMap();
      paramFragmentTransitionImpl.findNamedViews(localArrayMap, ((Fragment)paramObject).getView());
      paramFragmentTransitionImpl = paramFragmentContainerTransition.firstOutTransaction;
      if (!paramFragmentContainerTransition.firstOutIsPop) {
        break;
      }
      paramObject = ((Fragment)paramObject).getEnterTransitionCallback();
      paramFragmentTransitionImpl = paramFragmentTransitionImpl.mSharedElementTargetNames;
      localArrayMap.retainAll(paramFragmentTransitionImpl);
      if (paramObject == null) {
        break label184;
      }
      ((SharedElementCallback)paramObject).onMapSharedElements(paramFragmentTransitionImpl, localArrayMap);
      i = paramFragmentTransitionImpl.size() - 1;
      paramObject = localArrayMap;
    } while (i < 0);
    paramFragmentContainerTransition = (String)paramFragmentTransitionImpl.get(i);
    paramObject = (View)localArrayMap.get(paramFragmentContainerTransition);
    if (paramObject == null) {
      paramArrayMap.remove(paramFragmentContainerTransition);
    }
    for (;;)
    {
      i -= 1;
      break label91;
      paramObject = ((Fragment)paramObject).getExitTransitionCallback();
      paramFragmentTransitionImpl = paramFragmentTransitionImpl.mSharedElementSourceNames;
      break;
      if (!paramFragmentContainerTransition.equals(ViewCompat.getTransitionName((View)paramObject)))
      {
        paramFragmentContainerTransition = (String)paramArrayMap.remove(paramFragmentContainerTransition);
        paramArrayMap.put(ViewCompat.getTransitionName((View)paramObject), paramFragmentContainerTransition);
      }
    }
    label184:
    paramArrayMap.retainAll(localArrayMap.keySet());
    return localArrayMap;
  }
  
  private static FragmentTransitionImpl chooseImpl(Fragment paramFragment1, Fragment paramFragment2)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramFragment1 != null)
    {
      Object localObject = paramFragment1.getExitTransition();
      if (localObject != null) {
        localArrayList.add(localObject);
      }
      localObject = paramFragment1.getReturnTransition();
      if (localObject != null) {
        localArrayList.add(localObject);
      }
      paramFragment1 = paramFragment1.getSharedElementReturnTransition();
      if (paramFragment1 != null) {
        localArrayList.add(paramFragment1);
      }
    }
    if (paramFragment2 != null)
    {
      paramFragment1 = paramFragment2.getEnterTransition();
      if (paramFragment1 != null) {
        localArrayList.add(paramFragment1);
      }
      paramFragment1 = paramFragment2.getReenterTransition();
      if (paramFragment1 != null) {
        localArrayList.add(paramFragment1);
      }
      paramFragment1 = paramFragment2.getSharedElementEnterTransition();
      if (paramFragment1 != null) {
        localArrayList.add(paramFragment1);
      }
    }
    if (localArrayList.isEmpty()) {}
    do
    {
      return null;
      if ((PLATFORM_IMPL != null) && (canHandleAll(PLATFORM_IMPL, localArrayList))) {
        return PLATFORM_IMPL;
      }
      if ((SUPPORT_IMPL != null) && (canHandleAll(SUPPORT_IMPL, localArrayList))) {
        return SUPPORT_IMPL;
      }
    } while ((PLATFORM_IMPL == null) && (SUPPORT_IMPL == null));
    throw new IllegalArgumentException("Invalid Transition types");
  }
  
  private static ArrayList<View> configureEnteringExitingViews(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject, Fragment paramFragment, ArrayList<View> paramArrayList, View paramView)
  {
    Object localObject = null;
    if (paramObject != null)
    {
      ArrayList localArrayList = new ArrayList();
      paramFragment = paramFragment.getView();
      if (paramFragment != null) {
        paramFragmentTransitionImpl.captureTransitioningViews(localArrayList, paramFragment);
      }
      if (paramArrayList != null) {
        localArrayList.removeAll(paramArrayList);
      }
      localObject = localArrayList;
      if (!localArrayList.isEmpty())
      {
        localArrayList.add(paramView);
        paramFragmentTransitionImpl.addTargets(paramObject, localArrayList);
        localObject = localArrayList;
      }
    }
    return (ArrayList<View>)localObject;
  }
  
  private static Object configureSharedElementsOrdered(FragmentTransitionImpl paramFragmentTransitionImpl, ViewGroup paramViewGroup, final View paramView, final ArrayMap<String, String> paramArrayMap, final FragmentContainerTransition paramFragmentContainerTransition, final ArrayList<View> paramArrayList1, final ArrayList<View> paramArrayList2, final Object paramObject1, final Object paramObject2)
  {
    final Fragment localFragment1 = paramFragmentContainerTransition.lastIn;
    final Fragment localFragment2 = paramFragmentContainerTransition.firstOut;
    if ((localFragment1 == null) || (localFragment2 == null)) {
      return null;
    }
    final boolean bool = paramFragmentContainerTransition.lastInIsPop;
    final Object localObject;
    ArrayMap localArrayMap;
    if (paramArrayMap.isEmpty())
    {
      localObject = null;
      localArrayMap = captureOutSharedElements(paramFragmentTransitionImpl, paramArrayMap, localObject, paramFragmentContainerTransition);
      if (!paramArrayMap.isEmpty()) {
        break label96;
      }
      localObject = null;
    }
    for (;;)
    {
      if ((paramObject1 != null) || (paramObject2 != null) || (localObject != null)) {
        break label110;
      }
      return null;
      localObject = getSharedElementTransition(paramFragmentTransitionImpl, localFragment1, localFragment2, bool);
      break;
      label96:
      paramArrayList1.addAll(localArrayMap.values());
    }
    label110:
    callSharedElementStartEnd(localFragment1, localFragment2, bool, localArrayMap, true);
    Rect localRect;
    if (localObject != null)
    {
      localRect = new Rect();
      paramFragmentTransitionImpl.setSharedElementTargets(localObject, paramView, paramArrayList1);
      setOutEpicenter(paramFragmentTransitionImpl, localObject, paramObject2, localArrayMap, paramFragmentContainerTransition.firstOutIsPop, paramFragmentContainerTransition.firstOutTransaction);
      paramObject2 = localRect;
      if (paramObject1 != null) {
        paramFragmentTransitionImpl.setEpicenter(paramObject1, localRect);
      }
    }
    for (paramObject2 = localRect;; paramObject2 = null)
    {
      OneShotPreDrawListener.add(paramViewGroup, new Runnable()
      {
        public void run()
        {
          Object localObject = FragmentTransition.captureInSharedElements(this.val$impl, paramArrayMap, localObject, paramFragmentContainerTransition);
          if (localObject != null)
          {
            paramArrayList2.addAll(((ArrayMap)localObject).values());
            paramArrayList2.add(paramView);
          }
          FragmentTransition.callSharedElementStartEnd(localFragment1, localFragment2, bool, (ArrayMap)localObject, false);
          if (localObject != null)
          {
            this.val$impl.swapSharedElementTargets(localObject, paramArrayList1, paramArrayList2);
            localObject = FragmentTransition.getInEpicenterView((ArrayMap)localObject, paramFragmentContainerTransition, paramObject1, bool);
            if (localObject != null) {
              this.val$impl.getBoundsOnScreen((View)localObject, paramObject2);
            }
          }
        }
      });
      return localObject;
    }
  }
  
  private static Object configureSharedElementsReordered(final FragmentTransitionImpl paramFragmentTransitionImpl, ViewGroup paramViewGroup, final View paramView, final ArrayMap<String, String> paramArrayMap, FragmentContainerTransition paramFragmentContainerTransition, ArrayList<View> paramArrayList1, ArrayList<View> paramArrayList2, Object paramObject1, Object paramObject2)
  {
    Fragment localFragment1 = paramFragmentContainerTransition.lastIn;
    final Fragment localFragment2 = paramFragmentContainerTransition.firstOut;
    if (localFragment1 != null) {
      localFragment1.getView().setVisibility(0);
    }
    if ((localFragment1 == null) || (localFragment2 == null)) {
      return null;
    }
    final boolean bool = paramFragmentContainerTransition.lastInIsPop;
    Object localObject;
    ArrayMap localArrayMap2;
    final ArrayMap localArrayMap1;
    if (paramArrayMap.isEmpty())
    {
      localObject = null;
      localArrayMap2 = captureOutSharedElements(paramFragmentTransitionImpl, paramArrayMap, localObject, paramFragmentContainerTransition);
      localArrayMap1 = captureInSharedElements(paramFragmentTransitionImpl, paramArrayMap, localObject, paramFragmentContainerTransition);
      if (!paramArrayMap.isEmpty()) {
        break label146;
      }
      paramArrayMap = null;
      if (localArrayMap2 != null) {
        localArrayMap2.clear();
      }
      localObject = paramArrayMap;
      if (localArrayMap1 != null)
      {
        localArrayMap1.clear();
        localObject = paramArrayMap;
      }
    }
    for (;;)
    {
      if ((paramObject1 != null) || (paramObject2 != null) || (localObject != null)) {
        break label171;
      }
      return null;
      localObject = getSharedElementTransition(paramFragmentTransitionImpl, localFragment1, localFragment2, bool);
      break;
      label146:
      addSharedElementsWithMatchingNames(paramArrayList1, localArrayMap2, paramArrayMap.keySet());
      addSharedElementsWithMatchingNames(paramArrayList2, localArrayMap1, paramArrayMap.values());
    }
    label171:
    callSharedElementStartEnd(localFragment1, localFragment2, bool, localArrayMap2, true);
    if (localObject != null)
    {
      paramArrayList2.add(paramView);
      paramFragmentTransitionImpl.setSharedElementTargets(localObject, paramView, paramArrayList1);
      setOutEpicenter(paramFragmentTransitionImpl, localObject, paramObject2, localArrayMap2, paramFragmentContainerTransition.firstOutIsPop, paramFragmentContainerTransition.firstOutTransaction);
      paramArrayList1 = new Rect();
      paramFragmentContainerTransition = getInEpicenterView(localArrayMap1, paramFragmentContainerTransition, paramObject1, bool);
      paramView = paramFragmentContainerTransition;
      paramArrayMap = paramArrayList1;
      if (paramFragmentContainerTransition != null)
      {
        paramFragmentTransitionImpl.setEpicenter(paramObject1, paramArrayList1);
        paramArrayMap = paramArrayList1;
      }
    }
    for (paramView = paramFragmentContainerTransition;; paramView = null)
    {
      OneShotPreDrawListener.add(paramViewGroup, new Runnable()
      {
        public void run()
        {
          FragmentTransition.callSharedElementStartEnd(this.val$inFragment, localFragment2, bool, localArrayMap1, false);
          if (paramView != null) {
            paramFragmentTransitionImpl.getBoundsOnScreen(paramView, paramArrayMap);
          }
        }
      });
      return localObject;
      paramArrayMap = null;
    }
  }
  
  private static void configureTransitionsOrdered(FragmentManagerImpl paramFragmentManagerImpl, int paramInt, FragmentContainerTransition paramFragmentContainerTransition, View paramView, ArrayMap<String, String> paramArrayMap)
  {
    ViewGroup localViewGroup = null;
    if (paramFragmentManagerImpl.mContainer.onHasView()) {
      localViewGroup = (ViewGroup)paramFragmentManagerImpl.mContainer.onFindViewById(paramInt);
    }
    if (localViewGroup == null) {}
    Fragment localFragment;
    Object localObject3;
    FragmentTransitionImpl localFragmentTransitionImpl;
    Object localObject1;
    ArrayList localArrayList1;
    Object localObject2;
    do
    {
      do
      {
        do
        {
          return;
          localFragment = paramFragmentContainerTransition.lastIn;
          localObject3 = paramFragmentContainerTransition.firstOut;
          localFragmentTransitionImpl = chooseImpl((Fragment)localObject3, localFragment);
        } while (localFragmentTransitionImpl == null);
        boolean bool1 = paramFragmentContainerTransition.lastInIsPop;
        boolean bool2 = paramFragmentContainerTransition.firstOutIsPop;
        localObject1 = getEnterTransition(localFragmentTransitionImpl, localFragment, bool1);
        paramFragmentManagerImpl = getExitTransition(localFragmentTransitionImpl, (Fragment)localObject3, bool2);
        localArrayList2 = new ArrayList();
        localArrayList1 = new ArrayList();
        localObject2 = configureSharedElementsOrdered(localFragmentTransitionImpl, localViewGroup, paramView, paramArrayMap, paramFragmentContainerTransition, localArrayList2, localArrayList1, localObject1, paramFragmentManagerImpl);
      } while ((localObject1 == null) && (localObject2 == null) && (paramFragmentManagerImpl == null));
      localObject3 = configureEnteringExitingViews(localFragmentTransitionImpl, paramFragmentManagerImpl, (Fragment)localObject3, localArrayList2, paramView);
      if ((localObject3 == null) || (((ArrayList)localObject3).isEmpty())) {
        paramFragmentManagerImpl = null;
      }
      localFragmentTransitionImpl.addTarget(localObject1, paramView);
      paramFragmentContainerTransition = mergeTransitions(localFragmentTransitionImpl, localObject1, paramFragmentManagerImpl, localObject2, localFragment, paramFragmentContainerTransition.lastInIsPop);
    } while (paramFragmentContainerTransition == null);
    ArrayList localArrayList2 = new ArrayList();
    localFragmentTransitionImpl.scheduleRemoveTargets(paramFragmentContainerTransition, localObject1, localArrayList2, paramFragmentManagerImpl, (ArrayList)localObject3, localObject2, localArrayList1);
    scheduleTargetChange(localFragmentTransitionImpl, localViewGroup, localFragment, paramView, localArrayList1, localObject1, localArrayList2, paramFragmentManagerImpl, (ArrayList)localObject3);
    localFragmentTransitionImpl.setNameOverridesOrdered(localViewGroup, localArrayList1, paramArrayMap);
    localFragmentTransitionImpl.beginDelayedTransition(localViewGroup, paramFragmentContainerTransition);
    localFragmentTransitionImpl.scheduleNameReset(localViewGroup, localArrayList1, paramArrayMap);
  }
  
  private static void configureTransitionsReordered(FragmentManagerImpl paramFragmentManagerImpl, int paramInt, FragmentContainerTransition paramFragmentContainerTransition, View paramView, ArrayMap<String, String> paramArrayMap)
  {
    ViewGroup localViewGroup = null;
    if (paramFragmentManagerImpl.mContainer.onHasView()) {
      localViewGroup = (ViewGroup)paramFragmentManagerImpl.mContainer.onFindViewById(paramInt);
    }
    if (localViewGroup == null) {}
    Object localObject4;
    ArrayList localArrayList1;
    ArrayList localArrayList2;
    Object localObject1;
    Object localObject2;
    ArrayList localArrayList3;
    do
    {
      boolean bool1;
      do
      {
        do
        {
          return;
          localObject4 = paramFragmentContainerTransition.lastIn;
          localObject3 = paramFragmentContainerTransition.firstOut;
          paramFragmentManagerImpl = chooseImpl((Fragment)localObject3, (Fragment)localObject4);
        } while (paramFragmentManagerImpl == null);
        bool1 = paramFragmentContainerTransition.lastInIsPop;
        boolean bool2 = paramFragmentContainerTransition.firstOutIsPop;
        localArrayList1 = new ArrayList();
        localArrayList2 = new ArrayList();
        localObject1 = getEnterTransition(paramFragmentManagerImpl, (Fragment)localObject4, bool1);
        localObject2 = getExitTransition(paramFragmentManagerImpl, (Fragment)localObject3, bool2);
        paramFragmentContainerTransition = configureSharedElementsReordered(paramFragmentManagerImpl, localViewGroup, paramView, paramArrayMap, paramFragmentContainerTransition, localArrayList2, localArrayList1, localObject1, localObject2);
      } while ((localObject1 == null) && (paramFragmentContainerTransition == null) && (localObject2 == null));
      localArrayList3 = configureEnteringExitingViews(paramFragmentManagerImpl, localObject2, (Fragment)localObject3, localArrayList2, paramView);
      paramView = configureEnteringExitingViews(paramFragmentManagerImpl, localObject1, (Fragment)localObject4, localArrayList1, paramView);
      setViewVisibility(paramView, 4);
      localObject4 = mergeTransitions(paramFragmentManagerImpl, localObject1, localObject2, paramFragmentContainerTransition, (Fragment)localObject4, bool1);
    } while (localObject4 == null);
    replaceHide(paramFragmentManagerImpl, localObject2, (Fragment)localObject3, localArrayList3);
    Object localObject3 = paramFragmentManagerImpl.prepareSetNameOverridesReordered(localArrayList1);
    paramFragmentManagerImpl.scheduleRemoveTargets(localObject4, localObject1, paramView, localObject2, localArrayList3, paramFragmentContainerTransition, localArrayList1);
    paramFragmentManagerImpl.beginDelayedTransition(localViewGroup, localObject4);
    paramFragmentManagerImpl.setNameOverridesReordered(localViewGroup, localArrayList2, localArrayList1, (ArrayList)localObject3, paramArrayMap);
    setViewVisibility(paramView, 0);
    paramFragmentManagerImpl.swapSharedElementTargets(paramFragmentContainerTransition, localArrayList2, localArrayList1);
  }
  
  private static FragmentContainerTransition ensureContainer(FragmentContainerTransition paramFragmentContainerTransition, SparseArray<FragmentContainerTransition> paramSparseArray, int paramInt)
  {
    FragmentContainerTransition localFragmentContainerTransition = paramFragmentContainerTransition;
    if (paramFragmentContainerTransition == null)
    {
      localFragmentContainerTransition = new FragmentContainerTransition();
      paramSparseArray.put(paramInt, localFragmentContainerTransition);
    }
    return localFragmentContainerTransition;
  }
  
  private static String findKeyForValue(ArrayMap<String, String> paramArrayMap, String paramString)
  {
    int j = paramArrayMap.size();
    int i = 0;
    while (i < j)
    {
      if (paramString.equals(paramArrayMap.valueAt(i))) {
        return (String)paramArrayMap.keyAt(i);
      }
      i += 1;
    }
    return null;
  }
  
  private static Object getEnterTransition(FragmentTransitionImpl paramFragmentTransitionImpl, Fragment paramFragment, boolean paramBoolean)
  {
    if (paramFragment == null) {
      return null;
    }
    if (paramBoolean) {}
    for (paramFragment = paramFragment.getReenterTransition();; paramFragment = paramFragment.getEnterTransition()) {
      return paramFragmentTransitionImpl.cloneTransition(paramFragment);
    }
  }
  
  private static Object getExitTransition(FragmentTransitionImpl paramFragmentTransitionImpl, Fragment paramFragment, boolean paramBoolean)
  {
    if (paramFragment == null) {
      return null;
    }
    if (paramBoolean) {}
    for (paramFragment = paramFragment.getReturnTransition();; paramFragment = paramFragment.getExitTransition()) {
      return paramFragmentTransitionImpl.cloneTransition(paramFragment);
    }
  }
  
  private static View getInEpicenterView(ArrayMap<String, View> paramArrayMap, FragmentContainerTransition paramFragmentContainerTransition, Object paramObject, boolean paramBoolean)
  {
    paramFragmentContainerTransition = paramFragmentContainerTransition.lastInTransaction;
    if ((paramObject != null) && (paramArrayMap != null) && (paramFragmentContainerTransition.mSharedElementSourceNames != null) && (!paramFragmentContainerTransition.mSharedElementSourceNames.isEmpty()))
    {
      if (paramBoolean) {}
      for (paramFragmentContainerTransition = (String)paramFragmentContainerTransition.mSharedElementSourceNames.get(0);; paramFragmentContainerTransition = (String)paramFragmentContainerTransition.mSharedElementTargetNames.get(0)) {
        return (View)paramArrayMap.get(paramFragmentContainerTransition);
      }
    }
    return null;
  }
  
  private static Object getSharedElementTransition(FragmentTransitionImpl paramFragmentTransitionImpl, Fragment paramFragment1, Fragment paramFragment2, boolean paramBoolean)
  {
    if ((paramFragment1 == null) || (paramFragment2 == null)) {
      return null;
    }
    if (paramBoolean) {}
    for (paramFragment1 = paramFragment2.getSharedElementReturnTransition();; paramFragment1 = paramFragment1.getSharedElementEnterTransition()) {
      return paramFragmentTransitionImpl.wrapTransitionInSet(paramFragmentTransitionImpl.cloneTransition(paramFragment1));
    }
  }
  
  private static Object mergeTransitions(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject1, Object paramObject2, Object paramObject3, Fragment paramFragment, boolean paramBoolean)
  {
    boolean bool2 = true;
    boolean bool1 = bool2;
    if (paramObject1 != null)
    {
      bool1 = bool2;
      if (paramObject2 != null)
      {
        bool1 = bool2;
        if (paramFragment != null) {
          if (!paramBoolean) {
            break label53;
          }
        }
      }
    }
    label53:
    for (bool1 = paramFragment.getAllowReturnTransitionOverlap(); bool1; bool1 = paramFragment.getAllowEnterTransitionOverlap()) {
      return paramFragmentTransitionImpl.mergeTransitionsTogether(paramObject2, paramObject1, paramObject3);
    }
    return paramFragmentTransitionImpl.mergeTransitionsInSequence(paramObject2, paramObject1, paramObject3);
  }
  
  private static void replaceHide(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject, Fragment paramFragment, ArrayList<View> paramArrayList)
  {
    if ((paramFragment != null) && (paramObject != null) && (paramFragment.mAdded) && (paramFragment.mHidden) && (paramFragment.mHiddenChanged))
    {
      paramFragment.setHideReplaced(true);
      paramFragmentTransitionImpl.scheduleHideFragmentView(paramObject, paramFragment.getView(), paramArrayList);
      OneShotPreDrawListener.add(paramFragment.mContainer, new Runnable()
      {
        public void run()
        {
          FragmentTransition.setViewVisibility(this.val$exitingViews, 4);
        }
      });
    }
  }
  
  private static FragmentTransitionImpl resolveSupportImpl()
  {
    try
    {
      FragmentTransitionImpl localFragmentTransitionImpl = (FragmentTransitionImpl)Class.forName("android.support.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
      return localFragmentTransitionImpl;
    }
    catch (Exception localException) {}
    return null;
  }
  
  private static void retainValues(ArrayMap<String, String> paramArrayMap, ArrayMap<String, View> paramArrayMap1)
  {
    int i = paramArrayMap.size() - 1;
    while (i >= 0)
    {
      if (!paramArrayMap1.containsKey((String)paramArrayMap.valueAt(i))) {
        paramArrayMap.removeAt(i);
      }
      i -= 1;
    }
  }
  
  private static void scheduleTargetChange(final FragmentTransitionImpl paramFragmentTransitionImpl, ViewGroup paramViewGroup, final Fragment paramFragment, final View paramView, final ArrayList<View> paramArrayList1, Object paramObject1, final ArrayList<View> paramArrayList2, final Object paramObject2, final ArrayList<View> paramArrayList3)
  {
    OneShotPreDrawListener.add(paramViewGroup, new Runnable()
    {
      public void run()
      {
        ArrayList localArrayList;
        if (this.val$enterTransition != null)
        {
          paramFragmentTransitionImpl.removeTarget(this.val$enterTransition, paramView);
          localArrayList = FragmentTransition.configureEnteringExitingViews(paramFragmentTransitionImpl, this.val$enterTransition, paramFragment, paramArrayList1, paramView);
          paramArrayList2.addAll(localArrayList);
        }
        if (paramArrayList3 != null)
        {
          if (paramObject2 != null)
          {
            localArrayList = new ArrayList();
            localArrayList.add(paramView);
            paramFragmentTransitionImpl.replaceTargets(paramObject2, paramArrayList3, localArrayList);
          }
          paramArrayList3.clear();
          paramArrayList3.add(paramView);
        }
      }
    });
  }
  
  private static void setOutEpicenter(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject1, Object paramObject2, ArrayMap<String, View> paramArrayMap, boolean paramBoolean, BackStackRecord paramBackStackRecord)
  {
    if ((paramBackStackRecord.mSharedElementSourceNames != null) && (!paramBackStackRecord.mSharedElementSourceNames.isEmpty())) {
      if (!paramBoolean) {
        break label65;
      }
    }
    label65:
    for (paramBackStackRecord = (String)paramBackStackRecord.mSharedElementTargetNames.get(0);; paramBackStackRecord = (String)paramBackStackRecord.mSharedElementSourceNames.get(0))
    {
      paramArrayMap = (View)paramArrayMap.get(paramBackStackRecord);
      paramFragmentTransitionImpl.setEpicenter(paramObject1, paramArrayMap);
      if (paramObject2 != null) {
        paramFragmentTransitionImpl.setEpicenter(paramObject2, paramArrayMap);
      }
      return;
    }
  }
  
  private static void setViewVisibility(ArrayList<View> paramArrayList, int paramInt)
  {
    if (paramArrayList == null) {}
    for (;;)
    {
      return;
      int i = paramArrayList.size() - 1;
      while (i >= 0)
      {
        ((View)paramArrayList.get(i)).setVisibility(paramInt);
        i -= 1;
      }
    }
  }
  
  static void startTransitions(FragmentManagerImpl paramFragmentManagerImpl, ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramFragmentManagerImpl.mCurState < 1) {}
    SparseArray localSparseArray;
    do
    {
      return;
      localSparseArray = new SparseArray();
      i = paramInt1;
      if (i < paramInt2)
      {
        localObject = (BackStackRecord)paramArrayList.get(i);
        if (((Boolean)paramArrayList1.get(i)).booleanValue()) {
          calculatePopFragments((BackStackRecord)localObject, localSparseArray, paramBoolean);
        }
        for (;;)
        {
          i += 1;
          break;
          calculateFragments((BackStackRecord)localObject, localSparseArray, paramBoolean);
        }
      }
    } while (localSparseArray.size() == 0);
    Object localObject = new View(paramFragmentManagerImpl.mHost.getContext());
    int j = localSparseArray.size();
    int i = 0;
    label118:
    int k;
    ArrayMap localArrayMap;
    FragmentContainerTransition localFragmentContainerTransition;
    if (i < j)
    {
      k = localSparseArray.keyAt(i);
      localArrayMap = calculateNameOverrides(k, paramArrayList, paramArrayList1, paramInt1, paramInt2);
      localFragmentContainerTransition = (FragmentContainerTransition)localSparseArray.valueAt(i);
      if (!paramBoolean) {
        break label184;
      }
      configureTransitionsReordered(paramFragmentManagerImpl, k, localFragmentContainerTransition, (View)localObject, localArrayMap);
    }
    for (;;)
    {
      i += 1;
      break label118;
      break;
      label184:
      configureTransitionsOrdered(paramFragmentManagerImpl, k, localFragmentContainerTransition, (View)localObject, localArrayMap);
    }
  }
  
  static boolean supportsTransition()
  {
    return (PLATFORM_IMPL != null) || (SUPPORT_IMPL != null);
  }
  
  static class FragmentContainerTransition
  {
    public Fragment firstOut;
    public boolean firstOutIsPop;
    public BackStackRecord firstOutTransaction;
    public Fragment lastIn;
    public boolean lastInIsPop;
    public BackStackRecord lastInTransaction;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\app\FragmentTransition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */