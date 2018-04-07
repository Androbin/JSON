package de.androbin.json;

import org.json.simple.*;

public final class XObject {
  private final JSONObject obj;
  
  public XObject() {
    this( new JSONObject() );
  }
  
  public XObject( final JSONObject obj ) {
    this.obj = obj;
  }
  
  public XValue get( final String key ) {
    return new XValue( obj.get( key ) );
  }
}