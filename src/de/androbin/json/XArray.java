package de.androbin.json;

import java.util.*;
import org.json.simple.*;

public final class XArray implements Iterable<XValue> {
  private final JSONArray array;
  
  public XArray() {
    this( new JSONArray() );
  }
  
  public XArray( final JSONArray array ) {
    this.array = array;
  }
  
  public XValue get( final int index ) {
    return new XValue( array.get( index ) );
  }
  
  @ Override
  public Iterator<XValue> iterator() {
    final Iterator< ? > iter = array.iterator();
    return new Iterator<XValue>() {
      @ Override
      public XValue next() {
        return new XValue( iter.next() );
      }
      
      @ Override
      public boolean hasNext() {
        return iter.hasNext();
      }
    };
  }
}