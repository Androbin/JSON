package de.androbin.util;

import static de.androbin.collection.util.DoubleCollectionUtil.fillParallel;
import static de.androbin.collection.util.FloatCollectionUtil.fillParallel;
import java.io.*;
import java.net.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public final class JSONUtil {
  private JSONUtil() {
  }
  
  public static Optional<Object> parseJSON( final String path ) {
    final URL res = ClassLoader.getSystemResource( "json/" + path );
    
    if ( res == null ) {
      return Optional.empty();
    }
    
    try ( final InputStreamReader reader = new InputStreamReader( res.openStream() ) ) {
      return Optional.of( new JSONParser().parse( reader ) );
    } catch ( final ParseException | IOException e ) {
      return Optional.empty();
    }
  }
  
  public static double[] toDoubleArray( final Object o ) {
    return toDoubleArray( toNumberArray( o ) );
  }
  
  private static double[] toDoubleArray( final Number[] n ) {
    return n == null ? null : fillParallel( new double[ n.length ], i -> n[ i ].doubleValue() );
  }
  
  public static float[] toFloatArray( final Object o ) {
    return toFloatArray( toNumberArray( o ) );
  }
  
  private static float[] toFloatArray( final Number[] n ) {
    return n == null ? null : fillParallel( new float[ n.length ], i -> n[ i ].floatValue() );
  }
  
  @ SuppressWarnings( "unchecked" )
  private static Number[] toNumberArray( final Object o ) {
    final JSONArray array = (JSONArray) o;
    return (Number[]) array.toArray( new Number[ array.size() ] );
  }
  
  @ SuppressWarnings( "unchecked" )
  public static String[] toStringArray( final Object o ) {
    final JSONArray array = (JSONArray) o;
    return (String[]) array.toArray( new String[ array.size() ] );
  }
}
