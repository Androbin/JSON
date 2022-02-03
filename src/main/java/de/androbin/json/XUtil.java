package de.androbin.json;

import de.androbin.io.*;
import de.androbin.io.util.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;
import org.json.simple.parser.*;

public final class XUtil {
  private XUtil() {
  }
  
  public static XValue fetchJSON( final String url ) throws IOException {
    try {
      return FileReaderUtil.fetch( url, XUtil::readJSON ).get();
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
  
  public static Optional<XValue> readJSON( final Reader reader ) {
    try {
      final Object value = new JSONParser().parse( reader );
      return Optional.of( new XValue( value ) );
    } catch ( final IOException | ParseException e ) {
      return Optional.empty();
    }
  }
  
  public static Optional<XValue> readJSON( final Path path ) {
    if ( path == null ) {
      return Optional.empty();
    }
    
    try {
      return FileReaderUtil.readFile( path, XUtil::readJSON );
    } catch ( final IOException e ) {
      return Optional.empty();
    }
  }
  
  public static Optional<XValue> readJSON( final String path ) {
    return readJSON( DynamicClassLoader.getPath( "json/" + path ) );
  }
  
  public static XArray readJSONArray( final String path ) {
    return XUtil.readJSON( path )
        .map( XValue::asArray )
        .orElseGet( XArray::new );
  }
  
  public static XObject readJSONObject( final String path ) {
    return XUtil.readJSON( path )
        .map( XValue::asObject )
        .orElseGet( XObject::new );
  }
}