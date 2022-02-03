package de.androbin.json;

public final class XFormatUtil {
  private XFormatUtil() {
  }
  
  public static String format( final String dirty ) {
    final StringBuilder builder = new StringBuilder();
    boolean quoted = false;
    int indent = 0;
    
    for ( int i = 0; i < dirty.length(); i++ ) {
      final char ch = dirty.charAt( i );
      
      if ( ch == '"' ) {
        builder.append( ch );
        quoted ^= true;
        continue;
      }
      
      if ( quoted ) {
        builder.append( ch );
        
        if ( ch == '\\' ) {
          builder.append( dirty.charAt( ++i ) );
        }
        
        continue;
      }
      
      if ( Character.isWhitespace( ch ) ) {
        continue;
      }
      
      switch ( ch ) {
        default:
          builder.append( ch );
          break;
        case '{':
        case '[':
          builder.append( ch );
          builder.append( '\n' );
          indent( builder, ++indent );
          break;
        case '}':
        case ']':
          builder.append( '\n' );
          indent( builder, --indent );
          builder.append( ch );
          break;
        case ':':
          builder.append( ": " );
          break;
        case ',':
          builder.append( ",\n" );
          indent( builder, indent );
          break;
      }
    }
    
    return builder.toString();
  }
  
  private static void indent( final StringBuilder builder, final int level ) {
    for ( int i = 0; i < level; i++ ) {
      builder.append( '\t' );
    }
  }
}