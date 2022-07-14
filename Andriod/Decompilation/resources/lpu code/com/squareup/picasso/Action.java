package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

abstract class Action<T>
{
  boolean cancelled;
  final Drawable errorDrawable;
  final int errorResId;
  final String key;
  final int memoryPolicy;
  final int networkPolicy;
  final boolean noFade;
  final Picasso picasso;
  final Request request;
  final Object tag;
  final WeakReference<T> target;
  boolean willReplay;
  
  Action(Picasso paramPicasso, T paramT, Request paramRequest, int paramInt1, int paramInt2, int paramInt3, Drawable paramDrawable, String paramString, Object paramObject, boolean paramBoolean)
  {
    this.picasso = paramPicasso;
    this.request = paramRequest;
    if (paramT == null)
    {
      paramPicasso = null;
      this.target = paramPicasso;
      this.memoryPolicy = paramInt1;
      this.networkPolicy = paramInt2;
      this.noFade = paramBoolean;
      this.errorResId = paramInt3;
      this.errorDrawable = paramDrawable;
      this.key = paramString;
      if (paramObject == null) {
        break label90;
      }
    }
    for (;;)
    {
      this.tag = paramObject;
      return;
      paramPicasso = new RequestWeakReference(this, paramT, paramPicasso.referenceQueue);
      break;
      label90:
      paramObject = this;
    }
  }
  
  void cancel()
  {
    this.cancelled = true;
  }
  
  abstract void complete(Bitmap paramBitmap, Picasso.LoadedFrom paramLoadedFrom);
  
  abstract void error();
  
  String getKey()
  {
    return this.key;
  }
  
  int getMemoryPolicy()
  {
    return this.memoryPolicy;
  }
  
  int getNetworkPolicy()
  {
    return this.networkPolicy;
  }
  
  Picasso getPicasso()
  {
    return this.picasso;
  }
  
  Picasso.Priority getPriority()
  {
    return this.request.priority;
  }
  
  Request getRequest()
  {
    return this.request;
  }
  
  Object getTag()
  {
    return this.tag;
  }
  
  T getTarget()
  {
    if (this.target == null) {
      return null;
    }
    return (T)this.target.get();
  }
  
  boolean isCancelled()
  {
    return this.cancelled;
  }
  
  boolean willReplay()
  {
    return this.willReplay;
  }
  
  static class RequestWeakReference<M>
    extends WeakReference<M>
  {
    final Action action;
    
    public RequestWeakReference(Action paramAction, M paramM, ReferenceQueue<? super M> paramReferenceQueue)
    {
      super(paramReferenceQueue);
      this.action = paramAction;
    }
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\com\squareup\picasso\Action.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */