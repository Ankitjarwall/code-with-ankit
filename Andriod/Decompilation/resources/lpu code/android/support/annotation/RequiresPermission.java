package android.support.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.CONSTRUCTOR, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.PARAMETER})
public @interface RequiresPermission
{
  String[] allOf() default {};
  
  String[] anyOf() default {};
  
  boolean conditional() default false;
  
  String value() default "";
  
  @Target({java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.PARAMETER})
  public static @interface Read
  {
    RequiresPermission value() default @RequiresPermission;
  }
  
  @Target({java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.PARAMETER})
  public static @interface Write
  {
    RequiresPermission value() default @RequiresPermission;
  }
}


/* Location:              C:\Users\ankit\Documents\GitHub\code-with-ankit\Andriod\Decompilation\dex2jar-2.0\classes-dex2jar.jar!\android\support\annotation\RequiresPermission.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */