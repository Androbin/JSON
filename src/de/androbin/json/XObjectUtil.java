package de.androbin.json;

import de.androbin.func.*;
import java.awt.*;
import java.util.List;

public final class XObjectUtil {
  private XObjectUtil() {
  }
  
  public static Dimension toDimension( final List<?> o ) {
    return toTuple( o, Dimension::new );
  }
  
  public static Point toPoint( final List<?> o ) {
    return toTuple( o, Point::new );
  }
  
  private static <T> T toTuple( final List<?> o, final BiIntFunction<T> func ) {
    if ( o == null ) {
      return null;
    }
    
    final XArray array = new XArray( o );
    final int a = array.get( 0 ).asInt();
    final int b = array.get( 1 ).asInt();
    return func.apply( a, b );
  }
}