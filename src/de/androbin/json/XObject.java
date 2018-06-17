package de.androbin.json;

import java.util.*;
import java.util.function.*;
import org.json.simple.*;

public final class XObject {
  private final Map<?, ?> obj;
  
  public XObject() {
    this( Collections.emptyMap() );
  }
  
  public XObject( final Map<?, ?> obj ) {
    this.obj = obj;
  }
  
  public void forEach( final BiConsumer<String, XValue> action ) {
    obj.forEach( ( key, value ) -> action.accept( (String) key, new XValue( value ) ) );
  }
  
  public XValue get( final String key ) {
    return new XValue( obj.get( key ) );
  }
  
  @ Override
  public String toString() {
    return toString( obj );
  }
  
  public static String toString( final Map<?, ?> obj ) {
    return JSONObject.toJSONString( obj );
  }
}