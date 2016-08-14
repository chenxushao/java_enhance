package com.chenxushao.common.io;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;
public class FileUtil
{

	// Deletes all files and subdirectories under dir.
	// Returns true if all deletions were successful.
	// If a deletion fails, the method stops attempting to delete and returns false.
	/**
	 * @param dir
	 * @return
	 */
	public static boolean deleteDir(File dir)
	{
		if (dir.isDirectory())
		{
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++)
			{
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success)
				{
					return false;
				}
			}
		}

		// The directory is now empty so delete it
		return dir.delete();
	}

	/**
	 *  Strips path and extension from a filename example: lib/venus.jnlp ->
	 *  venus
	 */
	public static String getBaseName(String name)
	{
		// 1) Strip path.
		String base = new File(name).getName();
		// 2) Strip possible extension.
		int index = base.lastIndexOf('.');
		if (index != -1)
			base = base.substring(0, index);

		return base;
	}

	/**
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public static String getFileAsString(String name) throws IOException
	{
		File file = new File(name);
		return getFileAsString(file);
	}

	/**
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getFileAsString(File file) throws IOException
	{
		StringBuffer text = new StringBuffer();

		FileReader in = new FileReader(file);
		char buffer[] = new char[4096];
		int bytes_read;
		while ((bytes_read = in.read(buffer)) != -1)
			text.append(new String(buffer, 0, bytes_read));

		return text.toString();
	}

	/**
	 * @param filename
	 * @return
	 */
	public static String getFileExtension(String filename)
	{
		// returns extension including .
		// Now strip of possible extension.

		String extension = "";
		int index = filename.lastIndexOf('.');
		if (index != -1)
			extension = filename.substring(index);

		return extension;
	}

	/**
	 * @param file
	 * @return
	 */
	public static String getFileExtension(File file)
	{
		// returns extension including .

		// Strip of path first.
		String base = file.getName();
		// Now strip of possible extension.

		String extension = "";
		int index = base.lastIndexOf('.');
		if (index != -1)
			extension = base.substring(index);

		return extension;
	}

	/**
	 * @param filename
	 * @param keepDot
	 * @return
	 */
	public static String getFileExtension(String filename, boolean keepDot)
	{
		filename = filename.replace('\\', '/');

		String name;
		int namePos = filename.lastIndexOf('/');
		if (namePos != -1)
		{
			name = filename.substring(namePos + 1);
		}
		else
		{
			// no path info found
			name = filename;
		}

		String ext;
		int extPos = name.lastIndexOf('.');
		if (extPos != -1)
		{
			if (keepDot)
				ext = name.substring(extPos);
			else
				ext = name.substring(extPos + 1);
		}
		else
		{
			// no extension found
			ext = "";
		}

		return ext;
	}

	/**
	 * @param file
	 * @param keepDot
	 * @return
	 */
	public static String getFileExtension(File file, boolean keepDot)
	{
		// Strip path first.
		String base = file.getName();

		String extension = "";
		int index = base.lastIndexOf('.');
		if (index != -1)
		{
			if (keepDot)
				extension = base.substring(index);
			else
				extension = base.substring(index + 1);
		}
		return extension;
	}

	/**
	 * @param inStream
	 * @return
	 * @throws IOException
	 */
	public static String getInputStreamAsString(InputStream inStream) throws IOException
	{
		// todo: should i wrap inputStream in BufferedInputStream?
		StringBuffer text = new StringBuffer();

		InputStreamReader in = new InputStreamReader(inStream);
		char buffer[] = new char[4096];
		int bytes_read;
		while ((bytes_read = in.read(buffer)) != -1)
			text.append(new String(buffer, 0, bytes_read));

		return text.toString();
	}

	/**
	 * @param file
	 * @return
	 */
	public static File getRoot(File file)
	{
		File parent = file;
		while (parent.getParentFile() != null)
			parent = parent.getParentFile();

		return parent;
		// return file without parent (aka root)
	}

	/**
	 * @param from_file
	 * @param to_file
	 * @throws IOException
	 */
	public static void copy(File from_file, File to_file) throws IOException
	{
		// First make sure the source file exists, is a file, and is readable.
		if (!from_file.exists())
			abort("FileCopy: no such source file: " + from_file.getName());

		if (!from_file.isFile())
			abort("FileCopy: can't copy directory: " + from_file.getName());

		if (!from_file.canRead())
			abort("FileCopy: source file is unreadable: " + from_file.getName());

		// If the destination is a directory, use the source file name
		// as the destination file name
		if (to_file.isDirectory())
			to_file = new File(to_file, from_file.getName());

		// If the destination exists, make sure it is a writeable file
		// and ask before overwriting it.  If the destination doesn't
		// exist, make sure the directory exists and is writeable.
		if (to_file.exists())
		{
			if (!to_file.canWrite())
				abort("FileCopy: destination file is unwriteable: " + to_file.getName());
		}
		else
		{
			// if file doesn't exist, check if directory exists and is writeable.
			// If getParent() returns null, then the directory is the current dir.
			// so look up the user.dir system property to find out what that is.
			String parent = to_file.getParent();
			// Get the destination directory
			if (parent == null)
				parent = System.getProperty("user.dir");

			// or CWD
			File dir = new File(parent);
			// Convert it to a file.
			if (!dir.exists())
				abort("FileCopy: destination directory doesn't exist: " + parent);

			if (dir.isFile())
				abort("FileCopy: destination is not a directory: " + parent);

			if (!dir.canWrite())
				abort("FileCopy: destination directory is unwriteable: " + parent);

		}

		// If we've gotten this far, then everything is okay.
		// So we copy the file, a buffer of bytes at a time.
		FileInputStream from = null;
		// Stream to read from source
		FileOutputStream to = null;
		// Stream to write to destination
		try
		{
			from = new FileInputStream(from_file);
			// Create input stream
			to = new FileOutputStream(to_file);
			// Create output stream
			byte[] buffer = new byte[4096];
			// A buffer to hold file contents
			int bytes_read;
			// How many bytes in buffer
			// Read a chunk of bytes into the buffer, then write them out,
			// looping until we reach the end of the file (when read() returns -1).
			// Note the combination of assignment and comparison in this while
			// loop.  This is a common I/O programming idiom.
			while ((bytes_read = from.read(buffer)) != -1)
			{
				// Read bytes until EOF
				to.write(buffer, 0, bytes_read);
			}
			//   write bytes
		}
		// Always close the streams, even if exceptions were thrown
		finally
		{
			if (from != null)
			{
				try
				{
					from.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (to != null)
			{
				try
				{
					to.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 *  The static method that actually performs the file copy. Before copying
	 *  the file, however, it performs a lot of tests to make sure everything is
	 *  as it should be.
	 */
	public static void copy(String from_name, String to_name) throws IOException
	{
		File from_file = new File(from_name);
		// Get File objects from Strings
		File to_file = new File(to_name);

		// First make sure the source file exists, is a file, and is readable.
		if (!from_file.exists())
			abort("FileCopy: no such source file: " + from_name);

		if (!from_file.isFile())
			abort("FileCopy: can't copy directory: " + from_name);

		if (!from_file.canRead())
			abort("FileCopy: source file is unreadable: " + from_name);

		// If the destination is a directory, use the source file name
		// as the destination file name
		if (to_file.isDirectory())
			to_file = new File(to_file, from_file.getName());

		// If the destination exists, make sure it is a writeable file
		// and ask before overwriting it.  If the destination doesn't
		// exist, make sure the directory exists and is writeable.
		if (to_file.exists())
		{
			if (!to_file.canWrite())
				abort("FileCopy: destination file is unwriteable: " + to_name);

		}
		else
		{
			// if file doesn't exist, check if directory exists and is writeable.
			// If getParent() returns null, then the directory is the current dir.
			// so look up the user.dir system property to find out what that is.
			String parent = to_file.getParent();
			// Get the destination directory
			if (parent == null)
				parent = System.getProperty("user.dir");

			// or CWD
			File dir = new File(parent);
			// Convert it to a file.
			if (!dir.exists())
				abort("FileCopy: destination directory doesn't exist: " + parent);

			if (dir.isFile())
				abort("FileCopy: destination is not a directory: " + parent);

			if (!dir.canWrite())
				abort("FileCopy: destination directory is unwriteable: " + parent);

		}

		// If we've gotten this far, then everything is okay.
		// So we copy the file, a buffer of bytes at a time.
		FileInputStream from = null;
		// Stream to read from source
		FileOutputStream to = null;
		// Stream to write to destination
		try
		{
			from = new FileInputStream(from_file);
			// Create input stream
			to = new FileOutputStream(to_file);
			// Create output stream
			byte[] buffer = new byte[4096];
			// A buffer to hold file contents
			int bytes_read;
			// How many bytes in buffer
			// Read a chunk of bytes into the buffer, then write them out,
			// looping until we reach the end of the file (when read() returns -1).
			// Note the combination of assignment and comparison in this while
			// loop.  This is a common I/O programming idiom.
			while ((bytes_read = from.read(buffer)) != -1)
			{
				// Read bytes until EOF
				to.write(buffer, 0, bytes_read);
			}
			//   write bytes
		}
		// Always close the streams, even if exceptions were thrown
		finally
		{
			if (from != null)
			{
				try
				{
					from.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (to != null)
			{
				try
				{
					to.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @param base
	 * @param ext
	 * @return
	 */
	public static File[] findByExt(File base, String ext)
	{
		// searches recursively in base directory
		//   for files that match the extension ext

		Vector result = new Vector();

		findByExtWorker(result, base, ext);

		// make generic array into a type-safe array
		Object objs[] = result.toArray();
		File files[] = new File[objs.length];
		System.arraycopy(objs, 0, files, 0, objs.length);

		return files;
	}
	/**
	 * @param file
	 * @return
	 */
	public static String prettyPrintFileSize(File file)
	{
		long size = file.length();

		// use kilobytes (1028 bytes) for now
		if (size < 1028)
			return "1 k";

		size = size / 1028;

		return size + " k";
	}

	public static void saveStreamToFile(InputStream in, File outFile) throws IOException
	{
		FileOutputStream out = null;
		try
		{
			out = new FileOutputStream(outFile);
			byte[] buf = new byte[4096];
			int bytes_read;
			while ((bytes_read = in.read(buf)) != -1)
				out.write(buf, 0, bytes_read);
		}
		finally
		{
			if (in != null)
				try
				{
					in.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}

			if (out != null)
				try
				{
					out.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
		}
	}

	/**
	 *  A convenience method to throw an exception
	 */
	private static void abort(String msg) throws IOException
	{
		throw new IOException(msg);
	}

	/**
	 * @param result
	 * @param base
	 * @param ext
	 */
	private static void findByExtWorker(Vector result, File base, String ext)
	{
		File files[] = base.listFiles();
		if (files == null)
		{
			return;
		}

		for (int i = 0; i < files.length; i++)
		{
			File file = files[i];
			if (!file.isDirectory())
			{
				if (ext.equals("*"))
				{
					result.add(file);
				}
				else
				{
					String currentExt = getFileExtension(file);
					if (currentExt.equalsIgnoreCase(ext))
					{ // bingo; add to result set
						result.add(file);
					}

				}

			}
			else
			{ // file is a directory, recurse
				findByExtWorker(result, file, ext);
			}
		}
	}

} // end of class
