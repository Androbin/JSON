package de.androbin.json;

import java.util.function.*;
import org.json.simple.*;

public final class XObject {
  private final JSONObject obj;
  
  public XObject() {
    this( new JSONObject() );
  }
  
  public XObject( final JSONObject obj ) {
    this.obj = obj;
  }
  
  @ SuppressWarnings( "unchecked" )
  public void forEach( final BiConsumer<String, XValue> action ) {
    obj.forEach( ( key, value ) -> action.accept( (String) key, new XValue( value ) ) );
  }
  
  public XValue get( final String key ) {
    return new XValue( obj.get( key ) );
  }
}