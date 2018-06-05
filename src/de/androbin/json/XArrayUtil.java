package de.androbin.json;

import static de.androbin.collection.util.DoubleCollectionUtil.fillParallel;
import static de.androbin.collection.util.FloatCollectionUtil.fillParallel;
import java.lang.reflect.*;
import java.util.*;

public final class XArrayUtil {
  private XArrayUtil() {
  }
  
  @ SuppressWarnings( "unchecked" )
  private static <T> T[] toArray( final List<?> raw, final Class<T> cls ) {
    return raw.toArray( (T[]) Array.newInstance( cls, raw.size() ) );
  }
  
  public static double[] toDoubleArray( final List<?> raw ) {
    final Number[] array = toArray( raw, Number.class );
    return array == null ? null
        : fillParallel( new double[ array.length ], i -> array[ i ].doubleValue() );
  }
  
  public static float[] toFloatArray( final List<?> raw ) {
    final Number[] array = toArray( raw, Number.class );
    return array == null ? null
        : fillParallel( new float[ array.length ], i -> array[ i ].floatValue() );
  }
  
  public static String[] toStringArray( final List<?> raw ) {
    return toArray( raw, String.class );
  }
}