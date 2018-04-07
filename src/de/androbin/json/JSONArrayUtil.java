package de.androbin.json;

import static de.androbin.collection.util.DoubleCollectionUtil.fillParallel;
import static de.androbin.collection.util.FloatCollectionUtil.fillParallel;
import java.lang.reflect.*;
import org.json.simple.*;

public final class JSONArrayUtil {
  private JSONArrayUtil() {
  }
  
  @ SuppressWarnings( "unchecked" )
  private static <T> T[] toArray( final JSONArray raw, final Class<T> cls ) {
    return (T[]) raw.toArray( (T[]) Array.newInstance( cls, raw.size() ) );
  }
  
  public static double[] toDoubleArray( final JSONArray raw ) {
    final Number[] array = toArray( raw, Number.class );
    return array == null ? null
        : fillParallel( new double[ array.length ], i -> array[ i ].doubleValue() );
  }
  
  public static float[] toFloatArray( final JSONArray raw ) {
    final Number[] array = toArray( raw, Number.class );
    return array == null ? null
        : fillParallel( new float[ array.length ], i -> array[ i ].floatValue() );
  }
  
  public static String[] toStringArray( final JSONArray raw ) {
    return toArray( raw, String.class );
  }
}