package com.chenxushao.common.string;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.BitSet;

/**
 * @author mhanganu
 * 
 *         This class provides utility methods for encoding URI-s. The class is
 *         a slight modified version for the URLEncoder in Tomcat's source,
 *         whose initial author is Craig McClanahan
 */
public class URIEncoder {
	protected static final char[] hexadecimal = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	// Array containing the safe characters set.
	protected static BitSet safeCharacters = new BitSet(256);

	static {
		for (char i = 'a'; i <= 'z'; i++) {
			addSafeCharacter(i);
		}
		for (char i = 'A'; i <= 'Z'; i++) {
			addSafeCharacter(i);
		}
		for (char i = '0'; i <= '9'; i++) {
			addSafeCharacter(i);
		}
	}

	public static void addSafeCharacter(char c) {
		safeCharacters.set(c);
	}

	public static boolean stringContainsUnsafeCharacters(String str) {
		boolean containsUnsafeCharacters = false;
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (!safeCharacters.get(chars[i])) {
				containsUnsafeCharacters = true;
				break;
			}
		}

		return containsUnsafeCharacters;
	}

	public static String encode(String path) {
		int maxBytesPerChar = 10;
		int caseDiff = ('a' - 'A');
		StringBuffer rewrittenPath = new StringBuffer(path.length());
		ByteArrayOutputStream buf = new ByteArrayOutputStream(maxBytesPerChar);
		OutputStreamWriter writer = null;
		try {
			writer = new OutputStreamWriter(buf, "UTF8");
		} catch (Exception e) {
			e.printStackTrace();
			writer = new OutputStreamWriter(buf);
		}

		for (int i = 0; i < path.length(); i++) {
			int c = (int) path.charAt(i);
			if (safeCharacters.get(c)) {
				rewrittenPath.append((char) c);
			} else {
				// convert to external encoding before hex conversion
				try {
					writer.write(c);
					writer.flush();
				} catch (IOException e) {
					buf.reset();
					continue;
				}
				byte[] ba = buf.toByteArray();
				for (int j = 0; j < ba.length; j++) {
					// Converting each byte in the buffer
					byte toEncode = ba[j];
					rewrittenPath.append('%');
					int low = (int) (toEncode & 0x0f);
					int high = (int) ((toEncode & 0xf0) >> 4);
					rewrittenPath.append(hexadecimal[high]);
					rewrittenPath.append(hexadecimal[low]);
				}
				buf.reset();
			}
		}
		return rewrittenPath.toString();
	}

	public static void main(String[] args) {
		File f = new File("c:/");
		System.out.println(f.list().length);
		System.out.println(f.getParent());
	}

	/**
	 * @param fileName
	 * @param schemes
	 * @param default_scheme
	 */
	public static String processString(String fileName, String[] schemes,
			String defaultScheme) {
		// if fileName contains any of the schemes registered with phileas
		// take the scheme out and encode the rest of the string
		// and then concatenate back the scheme and the encoded string
		// otherwise encode the string
		// and add default "file:/" scheme

		// check for any of the schemes
		boolean schemePresent = false;
		String fileScheme = null;
		for (int i = 0; i < schemes.length; i++) {
			if (fileName.startsWith(schemes[i])) {
				schemePresent = true;
				fileScheme = schemes[i];
				break;
			}
		}

		String processedString;

		// if scheme found, take it out and encode the string
		if (schemePresent) {
			String stringWithNoScheme = fileName.substring(fileScheme.length());

			if (stringContainsUnsafeCharacters(stringWithNoScheme)) {
				processedString = fileScheme + encode(stringWithNoScheme);
			} else {
				processedString = fileName;
			}
		} else {
			if (stringContainsUnsafeCharacters(fileName)) {
				processedString = defaultScheme + encode(fileName);
			} else {
				processedString = defaultScheme + fileName;
			}
		}

		return processedString;
	}

	/**
	 * @param processedString
	 */
	/*
	 * private static String cleanUp(String str) { String ret =
	 * str.replace('\\', '/'); while(ret.startsWith("/") ||
	 * ret.startsWith("\\")) { ret = ret.substring(1); }
	 * 
	 * return ret; }
	 */
}