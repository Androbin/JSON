package de.androbin.json;

import java.util.*;
import java.util.function.*;
import org.json.simple.*;

public final class XObject {
  private final Map<String, ?> obj;
  
  public XObject() {
    this( null );
  }
  
  public XObject( final Map<String, ?> obj ) {
    this.obj = obj == null ? Collections.emptyMap() : obj;
  }
  
  public void forEach( final BiConsumer<String, XValue> action ) {
    obj.forEach( ( key, value ) -> action.accept( key, new XValue( value ) ) );
  }
  
  public XValue get( final String key ) {
    return new XValue( obj.get( key ) );
  }
  
  public Map<String, ?> raw() {
    return obj;
  }
  
  @ Override
  public String toString() {
    return toString( false );
  }
  
  public String toString( final boolean pretty ) {
    return toString( obj, pretty );
  }
  
  public static String toString( final Map<?, ?> obj, final boolean pretty ) {
    final String dirty = JSONObject.toJSONString( obj );
    return pretty ? XFormatUtil.format( dirty ) : dirty;
  }
}