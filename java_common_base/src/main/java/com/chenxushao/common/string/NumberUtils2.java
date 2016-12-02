/* ******************************************************************************
 * Copyright (c) 2006-2008 XMind Ltd. and others.
 * 
 * This file is a part of XMind 3. XMind releases 3 and
 * above are dual-licensed under the Eclipse Public License (EPL),
 * which is available at http://www.eclipse.org/legal/epl-v10.html
 * and the GNU Lesser General Public License (LGPL), 
 * which is available at http://www.gnu.org/licenses/lgpl.html
 * See http://www.xmind.net/license.html for details.
 * 
 * Contributors:
 *     XMind Ltd. - initial API and implementation
 *******************************************************************************/
package com.chenxushao.common.string;

/**
 * @author briansun
 * 
 */
public class NumberUtils2 {

	/**
	 * @param string
	 * @param defaultReturn
	 * @return
	 */
	public static int safeParseInt(String string, int defaultReturn) {
		if (string != null) {
			try {
				return Integer.parseInt(string);
			} catch (Throwable e) {
			}
		}
		return defaultReturn;
	}

	/**
	 * @param s
	 * @param defaultFloat
	 * @return
	 */
	public static float safeParseFloat(String s, float defaultFloat) {
		if (s != null) {
			try {
				return Float.parseFloat(s);
			} catch (Throwable e) {
			}
		}
		return defaultFloat;
	}

	public static double safeParseDouble(String s, double defaultValue) {
		if (s != null) {
			try {
				return Double.parseDouble(s);
			} catch (Throwable e) {
			}
		}
		return defaultValue;
	}

	/**
	 * @param s
	 * @param defaultLong
	 * @return
	 */
	public static long safeParseLong(String s, long defaultLong) {
		if (s != null) {
			try {
				return Long.parseLong(s);
			} catch (Throwable e) {
			}
		}
		return defaultLong;
	}
}