package com.chenxushao.common.lang;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public final class CharsetUtils {

	// private static ILogger LOG = LoggerFactory.getLogger( CharsetUtils.class
	// );

	private static final String DEFAULT_CHARSET = "UTF-8";

	private CharsetUtils() {
	}

	/**
	 * returns the name of the charset that is passed to the JVM as system
	 * property -DCHARSET=... If no charset has been defined UTF-8 will be
	 * returned as default.
	 * 
	 * @return the name of the charset that is passed to the JVM as system
	 *         property -DCHARSET=... If no charset has been defined UTF-8 will
	 *         be returned as default.
	 */
	public static String getSystemCharset() {
		String charset = null;
		try {
			charset = System.getProperty("CHARSET");
		} catch (Exception exc) {
			// LOG.logError( "Error retrieving system property CHARSET", exc );
		}
		if (charset == null) {
			charset = DEFAULT_CHARSET;
		}
		// LOG.logDebug( "Using system charset: " + charset );
		return charset;
	}

	public static String convertToUnicode(String input, String inCharset) {
		// Create the encoder and decoder for inCharset
		Charset charset = Charset.forName(inCharset);
		CharsetEncoder encoder = charset.newEncoder();

		ByteBuffer bbuf = null;
		try {
			// Convert a string to ISO-LATIN-1 bytes in a ByteBuffer
			// The newstate ByteBuffer is ready to be read.
			bbuf = encoder.encode(CharBuffer.wrap(input));

		} catch (CharacterCodingException e) {
			// LOG.logError( e.getMessage(), e );
		}
		return bbuf.toString();
	}

	public static String convertFromUnicode(String input, String targetCharset) {
		// Create the encoder and decoder for inCharset
		Charset charset = Charset.forName(targetCharset);
		CharsetDecoder decoder = charset.newDecoder();

		CharBuffer cbuf = null;
		try {
			// Convert ISO-LATIN-1 bytes in a ByteBuffer to a character
			// ByteBuffer and then to a
			// string.
			// The newstate ByteBuffer is ready to be read.
			cbuf = decoder.decode(ByteBuffer.wrap(input.getBytes()));
		} catch (CharacterCodingException e) {
			// LOG.logError( e.getMessage(), e );
		}
		return cbuf.toString();
	}

}