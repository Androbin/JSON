package de.androbin.json;

import de.androbin.mixin.iter.*;
import java.util.*;
import org.json.simple.*;

public final class XArray implements Iterable<XValue> {
  private final List<?> array;
  
  public XArray() {
    this( null );
  }
  
  public XArray( final List<?> array ) {
    this.array = array == null ? Collections.emptyList() : array;
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
    return toString( false );
  }
  
  public String toString( final boolean pretty ) {
    return toString( array, pretty );
  }
  
  public static String toString( final List<?> array, final boolean pretty ) {
    final String dirty = JSONArray.toJSONString( array );
    return pretty ? XFormatUtil.format( dirty ) : dirty;
  }
}