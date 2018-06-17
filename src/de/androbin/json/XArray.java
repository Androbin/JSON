package de.androbin.json;

import de.androbin.mixin.iter.*;
import java.util.*;
import org.json.simple.*;

public final class XArray implements Iterable<XValue> {
  private final List<?> array;
  
  public XArray() {
    this( Collections.emptyList() );
  }
  
  public XArray( final List<?> array ) {
    this.array = array;
  }
  
  public XValue get( final int index ) {
    return new XValue( array.get( index ) );
  }
  
  @ Override
  public Iterator<XValue> iterator() {
    final Iterator<?> iter = array.iterator();
    return new PipeIterator<>( iter, XValue::new );
  }
  
  public int size() {
    return array.size();
  }
  
  @ Override
  public String toString() {
    return toString( array );
  }
  
  public static String toString( final List<?> array ) {
    return JSONArray.toJSONString( array );
  }
}