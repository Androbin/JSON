package de.androbin.json;

import de.androbin.func.*;
import de.androbin.io.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public final class JSONUtil {
  private JSONUtil() {
  }
  
  public static XValue fetchJSON( final String path ) {
    try {
      return readJSON( new URL( path ) ).get();
    } catch ( final MalformedURLException e ) {
      e.printStackTrace();
      return null;
    }
  }
  
  public static XValue parseJSON( final String text ) {
    try {
      final Object value = new JSONParser().parse( text );
      return new XValue( value );
    } catch ( final ParseException e ) {
      return null;
    }
  }
  
  public static Optional<XValue> readJSON( final DirtySupplier<Reader, IOException> source ) {
    try ( final Reader reader = source.get() ) {
      final Object value = new JSONParser().parse( reader );
      return Optional.of( new XValue( value ) );
    } catch ( final IOException | ParseException e ) {
      return Optional.empty();
    }
  }
  
  public static Optional<XValue> readJSON( final File file ) {
    return readJSON( () -> new FileReader( file ) );
  }
  
  public static Optional<XValue> readJSON( final URL res ) {
    if ( res == null ) {
      return Optional.empty();
    }
    
    return readJSON( () -> new InputStreamReader( res.openStream() ) );
  }
  
  public static Optional<XValue> readJSON( final String path ) {
    return readJSON( DynamicClassLoader.get().getResource( "json/" + path ) );
  }
  
  public static XArray readJSONArray( final String path ) {
    return JSONUtil.readJSON( path )
        .map( XValue::asArray )
        .orElseGet( XArray::new );
  }
  
  public static XObject readJSONObject( final String path ) {
    return JSONUtil.readJSON( path )
        .map( XValue::asObject )
        .orElseGet( XObject::new );
  }
  
  public static Dimension toDimension( final JSONArray o ) {
    return toTuple( o, Dimension::new );
  }
  
  public static Point toPoint( final JSONArray o ) {
    return toTuple( o, Point::new );
  }
  
  private static <T> T toTuple( final JSONArray o, final BiIntFunction<T> func ) {
    if ( o == null ) {
      return null;
    }
    
    final XArray array = new XArray( o );
    final int a = array.get( 0 ).asInt();
    final int b = array.get( 1 ).asInt();
    return func.apply( a, b );
  }
}