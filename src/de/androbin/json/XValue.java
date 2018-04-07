package de.androbin.json;

import static de.androbin.json.JSONArrayUtil.*;
import static de.androbin.json.JSONUtil.*;
import java.awt.*;
import org.json.simple.*;

public final class XValue {
  private final Object obj;
  
  public XValue( final Object obj ) {
    this.obj = obj;
  }
  
  @ SuppressWarnings( "unchecked" )
  private <T> T as() {
    return (T) obj;
  }
  
  private <T> T as( final T value ) {
    return obj == null ? value : as();
  }
  
  public XArray asArray() {
    return new XArray( as() );
  }
  
  public boolean asBoolean() {
    return as();
  }
  
  public boolean asBoolean( final boolean value ) {
    return as( value );
  }
  
  public Color asColor() {
    return Color.decode( asString() );
  }
  
  public Dimension asDimension() {
    return toDimension( as() );
  }
  
  public double asDouble() {
    return asNumber().floatValue();
  }
  
  public float asFloat() {
    return asNumber().floatValue();
  }
  
  public float asFloat( final float value ) {
    return asNumber( value ).floatValue();
  }
  
  public float[] asFloatArray() {
    return toFloatArray( (JSONArray) obj );
  }
  
  public int asInt() {
    return asNumber().intValue();
  }
  
  public int asInt( final int value ) {
    return asNumber( value ).intValue();
  }
  
  public long asLong() {
    return asNumber().longValue();
  }
  
  private Number asNumber() {
    return as();
  }
  
  private Number asNumber( final Number value ) {
    return as( value );
  }
  
  public XObject asObject() {
    return new XObject( as() );
  }
  
  public Point asPoint() {
    return toPoint( as() );
  }
  
  public String asString() {
    return as();
  }
  
  public String asString( final String value ) {
    return as( value );
  }
  
  public String[] asStringArray() {
    return toStringArray( (JSONArray) obj );
  }
}