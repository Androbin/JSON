package de.androbin.util;

import static de.androbin.collection.util.DoubleCollectionUtil.fillParallel;
import static de.androbin.collection.util.FloatCollectionUtil.fillParallel;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public final class JSONUtil
{
	private JSONUtil()
	{
	}
	
	public static Object parseJSON( final String path )
	{
		final InputStream res = ClassLoader.getSystemResourceAsStream( "json/" + path );
		
		if ( res == null )
		{
			return null;
		}
		
		try ( final InputStreamReader reader = new InputStreamReader( res ) )
		{
			return new JSONParser().parse( reader );
		}
		catch ( final ParseException | IOException e )
		{
			return null;
		}
	}
	
	public static double[] toDoubleArray( final Object o )
	{
		return toDoubleArray( toNumberArray( o ) );
	}
	
	public static double[] toDoubleArray( final Number[] n )
	{
		return n == null ? null : fillParallel( new double[ n.length ], i -> n[ i ].doubleValue() );
	}
	
	public static float[] toFloatArray( final Object o )
	{
		return toFloatArray( toNumberArray( o ) );
	}
	
	public static float[] toFloatArray( final Number[] n )
	{
		return n == null ? null : fillParallel( new float[ n.length ], i -> n[ i ].floatValue() );
	}
	
	@ SuppressWarnings( "unchecked" )
	public static Number[] toNumberArray( final Object o )
	{
		final JSONArray array = (JSONArray) o;
		return (Number[]) array.toArray( new Number[ array.size() ] );
	}
	
	@ SuppressWarnings( "unchecked" )
	public static String[] toStringArray( final Object o )
	{
		final JSONArray array = (JSONArray) o;
		return (String[]) array.toArray( new String[ array.size() ] );
	}
}
