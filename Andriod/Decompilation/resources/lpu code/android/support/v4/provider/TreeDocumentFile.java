package android.support.v4.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.util.ArrayList;

@RequiresApi(21)
class TreeDocumentFile
  extends DocumentFile
{
  private Context mContext;
  private Uri mUri;
  
  TreeDocumentFile(DocumentFile paramDocumentFile, Context paramContext, Uri paramUri)
  {
    super(paramDocumentFile);
    this.mContext = paramContext;
    this.mUri = paramUri;
  }
  
  private static void closeQuietly(AutoCloseable paramAutoCloseable)
  {
    if (paramAutoCloseable != null) {}
    try
    {
      paramAutoCloseable.close();
      return;
    }
    catch (RuntimeException paramAutoCloseable)
    {
      throw paramAutoCloseable;
    }
    catch (Exception paramAutoCloseable) {}
  }
  
  private static Uri createFile(Context paramContext, Uri paramUri, String paramString1, String paramString2)
  {
    try
    {
      paramContext = DocumentsContract.createDocument(paramContext.getContentResolver(), paramUri, paramString1, paramString2);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public boolean canRead()
  {
    return DocumentsContractApi19.canRead(this.mContext, this.mUri);
  }
  
  public boolean canWrite()
  {
    return DocumentsContractApi19.canWrite(this.mContext, this.mUri);
  }
  
  public DocumentFile createDirectory(String paramString)
  {
    paramString = createFile(this.mContext, this.mUri, "vnd.android.document/directory", paramString);
    if (paramString != null) {
      return new TreeDocumentFile(this, this.mContext, paramString);
    }
    return null;
  }
  
  public DocumentFile createFile(String paramString1, String paramString2)
  {
    paramString1 = createFile(this.mContext, this.mUri, paramString1, paramString2);
    if (paramString1 != null) {
      return new TreeDocumentFile(this, this.mContext, paramString1);
    }
    return null;
  }
  
  public boolean delete()
  {
    try
    {
      boolean bool = DocumentsContract.deleteDocument(this.mContext.getContentResolver(), this.mUri);
      return bool;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public boolean exists()
  {
    return DocumentsContractApi19.exists(this.mContext, this.mUri);
  }
  
  public String getName()
  {
    return DocumentsContractApi19.getName(this.mContext, this.mUri);
  }
  
  public String getType()
  {
    return DocumentsContractApi19.getType(this.mContext, this.mUri);
  }
  
  public Uri getUri()
  {
    return this.mUri;
  }
  
  public boolean isDirectory()
  {
    return DocumentsContractApi19.isDirectory(this.mContext, this.mUri);
  }
  
  public boolean isFile()
  {
    return DocumentsContractApi19.isFile(this.mContext, this.mUri);
  }
  
  public boolean isVirtual()
  {
    return DocumentsContractApi19.isVirtual(this.mContext, this.mUri);
  }
  
  public long lastModified()
  {
    return DocumentsContractApi19.lastModified(this.mContext, this.mUri);
  }
  
  public long length()
  {
    return DocumentsContractApi19.length(this.mContext, this.mUri);
  }
  
  public DocumentFile[] listFiles()
  {
    Object localObject4 = this.mContext.getContentResolver();
    Object localObject5 = DocumentsContract.buildChildDocumentsUriUsingTree(this.mUri, DocumentsContract.getDocumentId(this.mUri));
    ArrayList localArrayList = new ArrayList();
    localObject3 = null;
    Object localObject1 = null;
    try
    {
      localObject4 = ((ContentResolver)localObject4).query((Uri)localObject5, new String[] { "document_id" }, null, null, null);
      for (;;)
      {
        localObject1 = localObject4;
        localObject3 = localObject4;
        if (!((Cursor)localObject4).moveToNext()) {
          break;
        }
        localObject1 = localObject4;
        localObject3 = localObject4;
        localObject5 = ((Cursor)localObject4).getString(0);
        localObject1 = localObject4;
        localObject3 = localObject4;
        localArrayList.add(DocumentsContract.buildDocumentUriUsingTree(this.mUri, (String)localObject5));
      }
      int i;
      return (DocumentFile[])localObject3;
    }
    catch (Exception localException)
    {
      localObject3 = localObject1;
      Log.w("DocumentFile", "Failed query: " + localException);
      closeQuietly((AutoCloseable)localObject1);
      for (;;)
      {
        localObject1 = (Uri[])localArrayList.toArray(new Uri[localArrayList.size()]);
        localObject3 = new DocumentFile[localObject1.length];
        i = 0;
        while (i < localObject1.length)
        {
          localObject3[i] = new TreeDocumentFile(this, this.mContext, localObject1[i]);
          i += 1;
        }
        closeQuietly(localException);
      }
    }
    finally
    {
      closeQuietly((AutoCloseable)localObject3);
    }
  }
  
  public boolean renameTo(String paramString)
  {
    boolean bool = false;
    try
    {
      paramString = DocumentsContract.renameDocument(this.mContext.getContentResolver(), this.mUri, paramString);
      if (paramString != null)
      {
        this.mUri = paramString;
        bool = true;
      }
      return bool;
    }
    catch (Exception paramString) {}
    return false;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\provider\TreeDocumentFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */