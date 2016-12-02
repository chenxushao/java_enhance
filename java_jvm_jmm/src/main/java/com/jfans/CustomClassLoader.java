// ============================================================================
//
// Copyright (C) 2006-2007 Dengues
//
// Google Group: http://groups.google.com/group/dengues
// QQ Group: 24885404
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package com.jfans;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * �Զ������������ʵ��
 */
public class CustomClassLoader extends ClassLoader {

	/** scanned class path */
	private Vector fPathItems;

	private ClassLoader delegate;// �����������

	public CustomClassLoader() {
	}

	public CustomClassLoader(String classPath) {
		scanPath(classPath);
	}

	public CustomClassLoader(String classPath, ClassLoader delegate) {
		this(classPath);
		this.delegate = delegate;
	}

	// ������System.getProperty("path.separator")
	protected void scanPath(String classPath) {
		String separator = System.getProperty("path.separator"); // ·���ָ�����windowsƽ̨��Ϊ";",linuxƽ̨��Ϊ":"
		fPathItems = new Vector(10);
		StringTokenizer st = new StringTokenizer(classPath, separator);
		while (st.hasMoreTokens()) {
			fPathItems.addElement(st.nextToken());
		}
	}

	@Override
	public URL getResource(String name) {
		return ClassLoader.getSystemResource(name);
	}

	@Override
	public InputStream getResourceAsStream(String name) {
		return ClassLoader.getSystemResourceAsStream(name);
	}

	// ��дloadClass�����������ƻ�˫��ί��ģ�ͣ���Ȼ�ڴ˲�û�ж�������ƻ�����osgi�У�DefaultClassLoader��Դ˷��������˴�����д
	@Override
	public synchronized Class loadClass(String name, boolean resolve)
			throws ClassNotFoundException {

		Class c = findLoadedClass(name);// �ȴӻ����в���
		if (delegate != null) {
			c = delegate.loadClass(name);// ���������������Ϊ�գ���ʹ�ô��������������
		}
		if (c != null)
			return c;
		//
		// Delegate the loading of excluded classes to the
		// standard class loader.
		//
		try {
			c = findSystemClass(name);// ʹ��ϵͳ�������ȥ����
			return c;
		} catch (ClassNotFoundException e) {
			// keep searching
			c = null;
		}
		if (c == null) {
			byte[] data = lookupClassData(name);// ���Լ����õ�cananpathȥɨ����
			if (data == null)
				throw new ClassNotFoundException();
			c = defineClass(name, data, 0, data.length);// �˷����ǲ������ǵģ���JDKʵ�֣����ٵ����յ��ã�����һ��native����
		}
		if (resolve)
			resolveClass(c);// ����������
		return c;
	}

	private byte[] lookupClassData(String className)
			throws ClassNotFoundException {
		byte[] data = null;
		for (int i = 0; i < fPathItems.size(); i++) {
			String path = (String) fPathItems.elementAt(i);
			String fileName = className.replace('.', '/') + ".class"; //$NON-NLS-1$
			if (isJar(path)) {
				data = loadJarData(path, fileName);// ��jar����ȥ����
			} else {
				data = loadFileData(path, fileName);// ���ļ�����ȥ����
			}
			if (data != null)
				return data;
		}
		throw new ClassNotFoundException(className);
	}

	boolean isJar(String pathEntry) {
		return pathEntry.endsWith(".jar") || pathEntry.endsWith(".zip"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private byte[] loadFileData(String path, String fileName) {
		File file = new File(path, fileName);
		if (file.exists()) {
			return getClassData(file);
		}
		return null;
	}

	private byte[] getClassData(File f) {
		try {
			FileInputStream stream = new FileInputStream(f);
			ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = stream.read(b)) != -1)
				out.write(b, 0, n);
			stream.close();
			out.close();
			return out.toByteArray();

		} catch (IOException e) {
		}
		return null;
	}

	private byte[] loadJarData(String path, String fileName) {
		ZipFile zipFile = null;
		InputStream stream = null;
		File archive = new File(path);
		if (!archive.exists())
			return null;
		try {
			zipFile = new ZipFile(archive);
		} catch (IOException io) {
			return null;
		}
		ZipEntry entry = zipFile.getEntry(fileName);
		if (entry == null)
			return null;
		int size = (int) entry.getSize();
		try {
			stream = zipFile.getInputStream(entry);
			byte[] data = new byte[size];
			int pos = 0;
			while (pos < size) {
				int n = stream.read(data, pos, data.length - pos);
				pos += n;
			}
			zipFile.close();
			return data;
		} catch (IOException e) {
		} finally {
			try {
				if (stream != null)
					stream.close();
			} catch (IOException e) {
			}
		}
		return null;
	}
}
