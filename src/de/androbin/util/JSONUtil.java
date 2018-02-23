package de.androbin.util;

import static de.androbin.collection.util.DoubleCollectionUtil.fillParallel;
import static de.androbin.collection.util.FloatCollectionUtil.fillParallel;
import de.androbin.func.*;
import de.androbin.io.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public final class JSONUtil {
  private JSONUtil() {
  }
  
  public static Optional<Object> parseJSON( final URL res ) {
    if ( res == null ) {
      return Optional.empty();
    }
    
    try ( final InputStreamReader reader = new InputStreamReader( res.openStream() ) ) {
      return Optional.of( new JSONParser().parse( reader ) );
    } catch ( final IOException | ParseException e ) {
      return Optional.empty();
    }
  }
  
  public static Optional<Object> parseJSON( final String path ) {
    return parseJSON( DynamicClassLoader.get().getResource( "json/" + path ) );
  }
  
  public static Object readJSON( final String path ) {
    try {
      return parseJSON( new URL( path ) ).get();
    } catch ( final MalformedURLException e ) {
      e.printStackTrace();
      return null;
    }
  }
  
  @ SuppressWarnings( "unchecked" )
  private static <T> T[] toArray( final Object o, final Class<T> cls ) {
    final JSONArray array = (JSONArray) o;
    return (T[]) array.toArray( (T[]) Array.newInstance( cls, array.size() ) );
  }
  
  public static Dimension toDimension( final Object o ) {
    return toTuple( o, Dimension::new );
  }
  
  public static double[] toDoubleArray( final Object o ) {
    final Number[] array = toArray( o, Number.class );
    return array == null ? null
        : fillParallel( new double[ array.length ], i -> array[ i ].doubleValue() );
  }
  
  public static float[] toFloatArray( final Object o ) {
    final Number[] array = toArray( o, Number.class );
    return array == null ? null
        : fillParallel( new float[ array.length ], i -> array[ i ].floatValue() );
  }
  
  public static int toInt( final Object o ) {
    return ( (Number) o ).intValue();
  }
  
  public static Point toPoint( final Object o ) {
    return toTuple( o, Point::new );
  }
  
  public static String[] toStringArray( final Object o ) {
    return toArray( o, String.class );
  }
  
  private static <T> T toTuple( final Object o, final BiIntFunction<T> func ) {
    if ( o == null ) {
      return null;
    }
    
    final JSONArray array = (JSONArray) o;
    final int a = toInt( array.get( 0 ) );
    final int b = toInt( array.get( 1 ) );
    return func.apply( a, b );
  }
}