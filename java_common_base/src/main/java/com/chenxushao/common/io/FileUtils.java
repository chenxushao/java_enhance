package com.chenxushao.common.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.chenxushao.common.lang.CharsetUtils;
import com.chenxushao.common.string.StringMatcher;

/**
 * 文件操纵工具类
 * 
 */
public class FileUtils {
	// FileUtil constant
	public static final String[] FILE_SIZE_UNIT = { "Byte", "KB", "MB", "GB",
			"TB", "PB" };

	public static final String FILE_TYPE_FOLDER = "dir";

	public static final String FILE_TYPE_ALL = "*.*";

	public static final String FILE_STATUS_HIDDEN = "H";

	public static final String FILE_STATUS_CAN_READ = "R";

	public static final String FILE_STATUS_CAN_WRITE = "W";

	public static final String FILE_STATUS_IS_FILE = "F";

	public static final String FILE_STATUS_IS_FOLDER = "D";



	private static final long K = 1 << 10;
	private static final long M = 1 << 20;

	public static String fileLengthToString(long length) {
		if (length < K) {
			return Long.toString(length) + " B"; //$NON-NLS-1$
		} else if (length >= K && length < M) {
			double tmp = ((double) length) / (double) K;
			return String.format("%,.2f KB", tmp); //$NON-NLS-1$
		}
		double tmp = ((double) length) / (double) (M);
		return String.format("%,.2f MB", tmp); //$NON-NLS-1$
	}

	public static String[] list(File path) {
		return list(path, null);
	}

	public static String[] list(File path, final FilenameFilter filter) {
		return list(path, false, filter);
	}

	public static String[] list(File path, boolean extend,
								final FilenameFilter filter) {
		String pathName = path.getAbsolutePath();
		if (path.isFile() && path.exists()) {
			if (extend)
				return list(
						new File(path.getParentFile(), path.getName() + "*"), false, filter); //$NON-NLS-1$
			if (filter == null
					|| filter.accept(path.getParentFile(), path.getName()))
				return new String[] { pathName };
		} else if (path.isDirectory() && path.exists()) {
			File[] subFiles = path.listFiles(filter);
			if (subFiles != null) {
				List<String> list = new ArrayList<String>();
				for (File subFile : subFiles) {
					list.add(subFile.getAbsolutePath());
				}
				return list.toArray(new String[0]);
			}
		} else {
			int index = pathName.lastIndexOf(File.separatorChar);
			if (index >= 0) {
				String dirName = pathName.substring(0, index + 1);
				String filePattern = pathName.substring(index + 1, pathName
						.length());
				if (extend && !filePattern.endsWith("*")) //$NON-NLS-1$
					filePattern += "*"; //$NON-NLS-1$
				File dir = new File(dirName);
				if (dir.isDirectory()) {
					final StringMatcher matcher = new StringMatcher(
							filePattern, true, false);
					return list(dir, false, new FilenameFilter() {
						public boolean accept(File dir, String name) {
							return matcher.match(name)
									&& (filter == null || filter.accept(dir,
									name));
						}
					});
				}
			}
		}
		return new String[0];
	}

	/**
	 * Deletes the given file and, if it is a directory, delete all its
	 * sub-directories and sub-files.
	 * 
	 * @param f
	 *            The file or directory to delete
	 * @return Whether the given file or directory is successfully deleted.
	 *         删除文件或文件夹
	 */
	public static boolean delete(File f) {
		if (f.isFile())
			return f.delete();
		else if (f.isDirectory()) {
			boolean b = clearDir(f);
			b &= f.delete();
			return b;
		} else
			return false;
	}

	/**
	 * Deletes all sub-files and sub-directories in the given directory.
	 * 
	 * @param dir
	 *            The directory to clear
	 * @return Whether the given directory is successfully cleared. 清空某文件夹
	 */
	public static boolean clearDir(File dir) {
		if (!dir.isDirectory())
			return false;
		File[] files = dir.listFiles();
		if (files == null || files.length == 0)
			return true;
		boolean cleared = true;
		for (File sub : files) {
			cleared &= delete(sub);
		}
		return cleared;
	}

	/**
	 * Determines corresponding media type of the given path.
	 * 
	 * @param path
	 * @return 返回图片文件的类型文明
	 */
	public static String getMediaType(String path) {
		if (path != null) {
			String ext = getExtension(path);
			if (".jpg".equals(ext) || ".jpeg".equals(ext))
				return "image/jpeg";
			if (".png".equals(ext))
				return "image/png";
			if (".bmp".equals(ext))
				return "image/bmp";
			if (".gif".equals(ext))
				return "image/gif";
		}
		return "";
	}

	private static Pattern FileNamePattern = null;

	/**
	 * s
	 * 
	 * @param fullPath
	 * @return 返回文件夹或文件的名称
	 */
	public static String getFileName(String fullPath) {
		if (FileNamePattern == null)
			FileNamePattern = Pattern.compile("([^/\\\\]*)[/|\\\\]?$");
		Matcher m = FileNamePattern.matcher(fullPath);
		if (m.find())
			return m.group(1);
		return fullPath;
	}

	/**
	 * Returns the extension part of a file path, e.g., <code>.jpg</code>,
	 * <code>.html</code>. If the file does not have an extension, an empty
	 * string is returned.
	 * 
	 * @param fullPath
	 * @return 返回文件扩展名
	 */
	public static String getExtension(String fullPath) {
		String fileName = getFileName(fullPath);
		int i = fileName.lastIndexOf('.');
		if (i >= 0)
			return fileName.substring(i);
		return "";
	}

	/**
	 * 
	 * @param fullPath
	 * @return 返回不包括扩展名的文件名称
	 */
	public static String getNoExtensionFileName(String fullPath) {
		String fileName = getFileName(fullPath);
		int i = fileName.lastIndexOf('.');
		if (i >= 0)
			return fileName.substring(0, i);
		return fileName;
	}
 
	private static final long G = 1 << 30;
 
 
 

	/**
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public static String getFileAsString(String name) throws IOException {
		File file = new File(name);
		return getFileAsString(file);
	}

	/**
	 * @param file
	 * @return
	 * @throws IOException
	 *             返回文本文件内容
	 */
	public static String getFileAsString(File file) throws IOException {
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
	 * @return 返回文件扩展名
	 */
	public static String getFileExtension(String filename) {
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
	 * @return 返回文件扩展名
	 */
	public static String getFileExtension(File file) {
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
	 * @return getFileExtension(File file,boolean keepdot)的重构方法
	 */
	public static String getFileExtension(String filename, boolean keepDot) {
		filename = filename.replace('\\', '/');

		String name;
		int namePos = filename.lastIndexOf('/');
		if (namePos != -1) {
			name = filename.substring(namePos + 1);
		} else {
			// no path info found
			name = filename;
		}

		String ext;
		int extPos = name.lastIndexOf('.');
		if (extPos != -1) {
			if (keepDot)
				ext = name.substring(extPos);
			else
				ext = name.substring(extPos + 1);
		} else {
			// no extension found
			ext = "";
		}

		return ext;
	}

	/**
	 * @param file
	 * @param keepDot
	 * @return 返回文件扩展名,keepDot，是否包含小数点
	 */
	public static String getFileExtension(File file, boolean keepDot) {
		// Strip path first.
		String base = file.getName();

		String extension = "";
		int index = base.lastIndexOf('.');
		if (index != -1) {
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
	 *             适合读取数据量不大的文本文件
	 */
	public static String getInputStreamAsString(InputStream inStream)
			throws IOException {
		// todo: should i wrap inputStream in BufferedInputStream?
		StringBuffer text = new StringBuffer();

		InputStreamReader in = new InputStreamReader(inStream, "Unicode");
		char buffer[] = new char[4096];
		int bytes_read;
		while ((bytes_read = in.read(buffer)) != -1)
			text.append(new String(buffer, 0, bytes_read));

		return text.toString();
	}

	/**
	 * @param file
	 * @return 返回某个抽象路径的最顶层根目录
	 */
	public static File getRoot(File file) {
		File parent = file;
		while (parent.getParentFile() != null)
			parent = parent.getParentFile();

		return parent;
		// return file without parent (aka root)
	}

	/**
	 * The static method that actually performs the file copy. Before copying
	 * the file, however, it performs a lot of tests to make sure everything is
	 * as it should be. 只能拷贝文件
	 */
	public static void copy1(String from_name, String to_name)
			throws IOException {
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
		// and ask before overwriting it. If the destination doesn't
		// exist, make sure the directory exists and is writeable.
		if (to_file.exists()) {
			if (!to_file.canWrite())
				abort("FileCopy: destination file is unwriteable: " + to_name);

		} else {
			// if file doesn't exist, check if directory exists and is
			// writeable.
			// If getParent() returns null, then the directory is the current
			// dir.
			// so look up the user.dir system property to find out what that is.
			String parent = to_file.getParent();
			// Get the destination directory
			if (parent == null)
				parent = System.getProperty("user.dir");

			// or CWD
			File dir = new File(parent);
			// Convert it to a file.
			if (!dir.exists())
				abort("FileCopy: destination directory doesn't exist: "
						+ parent);

			if (dir.isFile())
				abort("FileCopy: destination is not a directory: " + parent);

			if (!dir.canWrite())
				abort("FileCopy: destination directory is unwriteable: "
						+ parent);

		}

		// If we've gotten this far, then everything is okay.
		// So we copy the file, a buffer of bytes at a time.
		FileInputStream from = null;
		// Stream to read from source
		FileOutputStream to = null;
		// Stream to write to destination
		try {
			from = new FileInputStream(from_file);
			// Create input stream
			to = new FileOutputStream(to_file);
			// Create output stream
			byte[] buffer = new byte[4096];
			// A buffer to hold file contents
			int bytes_read;
			// How many bytes in buffer
			// Read a chunk of bytes into the buffer, then write them out,
			// looping until we reach the end of the file (when read() returns
			// -1).
			// Note the combination of assignment and comparison in this while
			// loop. This is a common I/O programming idiom.
			while ((bytes_read = from.read(buffer)) != -1) {
				// Read bytes until EOF
				to.write(buffer, 0, bytes_read);
			}
			// write bytes
		}
		// Always close the streams, even if exceptions were thrown
		finally {
			if (from != null) {
				try {
					from.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (to != null) {
				try {
					to.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @param base
	 * @param ext
	 * @return 根据扩展名查找某目录下所有文件，会递归到深层目录中去找
	 */
	public static File[] findByExt(File base, String ext) {
		// searches recursively in base directory
		// for files that match the extension ext

		Vector result = new Vector();

		findByExtWorker(result, base, ext);

		// make generic array into a type-safe array
		Object objs[] = result.toArray();
		File files[] = new File[objs.length];
		System.arraycopy(objs, 0, files, 0, objs.length);

		return files;
	}

	public static void saveStreamToFile(InputStream in, File outFile)
			throws IOException {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(outFile);
			byte[] buf = new byte[4096];
			int bytes_read;
			while ((bytes_read = in.read(buf)) != -1)
				out.write(buf, 0, bytes_read);
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * A convenience method to throw an exception
	 */
	private static void abort(String msg) throws IOException {
		throw new IOException(msg);
	}

	/**
	 * @param result
	 * @param base
	 * @param ext
	 *            供findByExt方法调用的私有方法
	 */
	private static void findByExtWorker(Vector result, File base, String ext) {
		File files[] = base.listFiles();
		if (files == null) {
			return;
		}

		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (!file.isDirectory()) {
				if (ext.equals("*")) {
					result.add(file);
				} else {
					String currentExt = getFileExtension(file);
					if (currentExt.equalsIgnoreCase(ext)) { // bingo; add to
						// result set
						result.add(file);
					}

				}

			} else { // file is a directory, recurse
				findByExtWorker(result, file, ext);
			}
		}
	}

	// 返回文件或目录大小,字节
	public static long getFileOrDirectorySize(File file) {
		/**
		 * 如果为磁盘，例如windows下的c,d,e,g,g盘　
		 */
		List<String> disks = getSystemDisks();
		String diskPath = file.getAbsolutePath().toUpperCase();
		if (disks.contains(diskPath)) {
			return getDiskSize(file);
		}

		if (file.isFile()) {

			return (file.length());

		} else {

			long res = 0L;

			File[] files = file.listFiles();

			if (files != null) {

				for (int i = 0; i < files.length; i++) {

					res += getFileOrDirectorySize(files[i]) + 0L;
				}
			}

			return (res);
		}
	}

	// public static void main(String[] args) {
	// long startTime = System.currentTimeMillis();
	// long size = getFileOrDirectorySize(new File("c:/"));
	// System.out.println(fileLengthToString(size));
	// System.out.println("耗时: " + (System.currentTimeMillis() -
	// startTime)/1000);
	// }

	private static boolean rcdDone = false;

	private static boolean rcdValue;

	/*
	 * Can rename delete 供rename方法调用的私有方法
	 */
	private static boolean renameCanDelete() throws IOException {
		if (rcdDone)
			return rcdValue;
		File a = null;
		File b = null;
		try {
			a = File.createTempFile("java-rename-check-", "");
			b = File.createTempFile("java-rename-check-", "");
			rcdValue = a.renameTo(b);
			rcdDone = true;
		} finally {
			if (a != null)
				a.delete();
			if (b != null)
				b.delete();
		}
		return rcdValue;
	}

	/**
	 * Reliable rename implementation 较可靠的重命名实现，只能在同一个分区进行重命名操作,这是由renameTo本身决定的
	 * 在同一分区内，它可以代替移动操作move
	 */
	public static void rename(File a, File b) throws IOException {
		if (!a.exists())
			throw new IOException("Rename failed: Source file " + a
					+ " doesn't exist.");

		File parentFolder = b.getParentFile();
		parentFolder.mkdirs();
		if (!b.getParentFile().exists() || !b.getParentFile().isDirectory())
			throw new IOException("Rename failed: Destination directory "
					+ b.getParent() + " doesn't exist.");

		// A stupid OS can't rename a file to an existing one.
		// Therefore the rename operation isn't atomical anymore.
		if (!renameCanDelete() && b.exists()) {
			if (!b.delete())
				throw new IOException(
						"Rename failed: Couldn't delete existing destination file "
								+ b);
		}

		if (!a.renameTo(b))
			throw new IOException("Rename failed: Unknown error (" + a + " -> "
					+ b + ")");
	}

	/**
	 * 
	 * @param file
	 * @throws IOException
	 *             创建深层次目录，较有效
	 */
	public static void mkdir(File file) throws IOException {
		if (file.exists()) {// 文件是否存在
			if (file.isDirectory())// 目录已存在
				return;
			throw new IOException("Couldn't create directory " + file
					+ ": Normal file with this path already exists");
		}

		file.mkdirs();

		if (!file.exists() || !file.isDirectory())
			throw new IOException("Couldn't create directory " + file
					+ ": Unknown reason");

	}

	/**
	 * Mkdirs which throws exceptions 创建深层次目录，父目录不存在时会先创建父目录,较有效
	 */
	public static void mkdirs(File file) throws IOException {
		file = file.getCanonicalFile();

		ArrayList dirList = new ArrayList();
		do {
			dirList.add(file);
			file = file.getParentFile();
		} while (file != null);

		for (int i = dirList.size() - 1; i >= 0; --i) {
			File dir = (File) dirList.get(i);

			if (dir.exists()) {
				if (!dir.isDirectory())
					throw new IOException("Couldn't create directory " + dir
							+ ": Normal file with this path already exists");
			}
			dir.mkdir();
			if (!dir.exists() || !dir.isDirectory())
				throw new IOException("Couldn't create directory " + dir
						+ ": Unknown reason");
		}
	}

	/**
	 * 
	 * @param file
	 * @param format
	 *            the date format like "yyyy-MM-dd"
	 * @return the last modified date of the file 返回文件最后修改时间
	 */
	public static String getFileLastModified(File file, String format) {
		Date date = new Date(file.lastModified());
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date).toString();
	}

	/**
	 * 
	 * @param folder
	 * @return the files' number in folder 返回某文件夹下文件个数，不递归
	 */
	public static int getFileNumberInFolder(File folder) {
		if (folder == null)
			return 0;
		if (folder.isFile())
			return 0;

		File[] files = folder.listFiles();
		if (files == null)
			return 0;
		int count = 0;
		for (File file : files) {
			if (file.isFile()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 
	 * @param folder
	 * @return the folder's number in folder 返回某文件夹下子文件夹的个数,不递归
	 */
	public static int getFolderNumberInFolder(File folder) {
		if (folder == null)
			return 0;
		if (folder.isFile())
			return 0;

		File[] files = folder.listFiles();
		if (files == null)
			return 0;
		int count = 0;
		for (File file : files) {
			if (file.isDirectory()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 
	 * @param file
	 * @return the last modified date of the file with the default format :
	 *         yyyy-MM-dd HH:mm:ss 返回文件最后修改时间的字符串
	 */
	public static String getFileLastModified(File file) {
		return getFileLastModified(file, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * @param file
	 * @return the file type like "exe" 返回给定的抽象路径的类型文件或或某个文件类型
	 */
	public static String getFileType(File file) {
		if (file.isDirectory()) {
			return FILE_TYPE_FOLDER;
		} else {
			String[] strArray = file.getName().split("\\.");

			int length = strArray.length;
			if (length > 1) {
				return strArray[length - 1];

			} else {
				return "unknown";
			}
		}
	}

	/**
	 * 
	 * @param folder
	 * @return the types of file in this folder 返回某文件夹下文件类型数组
	 */
	public static String[] getFileTypesInFolder(File folder) {
		if (folder == null) {
			return new String[0];
		}
		if (folder.isFile()) {
			return new String[0];
		}
		Set<String> fileTypes = new TreeSet<String>();
		fileTypes.add(FILE_TYPE_ALL);
		File[] files = folder.listFiles();
		if (files == null) {
			return null;
		}

		for (File file : files) {
			fileTypes.add(getFileType(file).toLowerCase());
		}
		return convertObjectArrayToStringArray(fileTypes.toArray());
	}

	/**
	 * 
	 * @param objectArray
	 * @return stringArray 对象数组转化为字符串数组
	 */
	private static String[] convertObjectArrayToStringArray(Object[] objectArray) {
		String[] stringArray = new String[objectArray.length];
		int i = 0;
		for (Object o : objectArray) {
			stringArray[i] = o.toString();
			i++;
		}
		// Arrays.sort( stringArray );
		return stringArray;
	}

	/**
	 * 
	 * @param file
	 * @return files Status: H-hidden;R-Read;W-Write 返回文件基本状态，可读，可写，隐藏属性
	 */
	public static String getFileStatus(File file) {
		StringBuffer buffer = new StringBuffer();

		buffer.append(file.isHidden() ? FILE_STATUS_HIDDEN + "\t" : "\t");
		buffer.append(file.canRead() ? FILE_STATUS_CAN_READ + "\t" : "\t");
		buffer.append(file.canWrite() ? FILE_STATUS_CAN_WRITE + "\t" : "\t");

		return buffer.toString();
	}

	/**
	 * 
	 * @param file
	 * @return file's info: full path, lastModified, status, file or folder,
	 *         size 获取文件的基本信息
	 */
	public static String getFileInfo(File file) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(file.getPath());
		buffer.append("\t");
		buffer.append(getFileLastModified(file));
		buffer.append("\t");
		buffer.append(getFileStatus(file));
		buffer.append("\t");
		buffer.append(file.isFile() ? FILE_STATUS_IS_FILE
				: FILE_STATUS_IS_FOLDER);
		buffer.append("\t");
		buffer.append(file.isFile() ? file.length() : "");

		return buffer.toString();
	}

	/**
	 * 
	 * @param files
	 *            File Array
	 * @return the size of files in the file array 返回传入的文件数组文件总大小
	 */
	public static long getFilesSize(File[] files) {
		if (files == null)
			return 0;
		long filesSize = 0;
		for (File file : files) {
			if (file.isFile()) {
				filesSize += ((File) file).length();
			}
		}
		return filesSize;
	}

	/**
	 * 
	 * @param folder
	 *            the folder you want to check
	 * @param type
	 *            the file type you want to filter
	 * @return 返回某文件夹下某种类型文件
	 */
	public static List<File> getFileList(File folder, String type) {
		List<File> fileNameList = new ArrayList<File>();
		File[] files = folder.listFiles();
		for (File file : files) {
			if (getFileType(file).equalsIgnoreCase(type)) {
				fileNameList.add(file);
			}
		}
		return fileNameList;
	}

	/*
	 * 某文件是否位于于某文件下面,不递归至深层次子目录下
	 */
	public static boolean isFileExist(File file, File folder) {
		File[] files = folder.listFiles();
		String fileName = file.getName();
		for (File f : files) {
			if (fileName.equals(f.getName())) {
				return true;
			}
		}

		return false;
	}

	/*
	 * 某文件是否位于于某文件下面,不递归至深层次子目录下
	 */
	public static boolean isFileExist(String fileName, File folder) {
		File[] files = folder.listFiles();
		for (File f : files) {
			if (fileName.equals(f.getName())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * get the content of the file
	 * 
	 * @param file
	 *            target file
	 * @param bufferSize
	 *            the buffer size
	 * @return String the file content
	 * @throws FileNotFoundException
	 *             , IOException
	 */
	public static String getFileContent(File file, int bufferSize)
			throws FileNotFoundException, IOException {

		StringBuffer strBuffer = new StringBuffer();
		BufferedReader in = new BufferedReader(new FileReader(file), bufferSize);
		String tempStr = in.readLine();
		strBuffer.append(tempStr);
		while ((tempStr = in.readLine()) != null) {
			strBuffer.append("\n").append(tempStr);
		}
		in.close();
		return strBuffer.toString();
	}

	/**
	 * get the content of the file with defalut buffer size 1024
	 * 
	 * @param file
	 *            target file
	 * @return String the file content
	 * @throws FileNotFoundException
	 *             , IOException
	 */
	public static String getFileContent(File file)
			throws FileNotFoundException, IOException {
		return getFileContent(file, 1024);
	}

	/**
	 * paste a file to the target folder with the default buffer size 4096
	 * 
	 * @param source_file
	 *            the source file
	 * @param target_folder
	 *            the target folder
	 * @return
	 * @throws FileNotFoundException
	 */
	public static void doPaste(File source_file, File target_folder)
			throws FileNotFoundException, IOException {
		doPaste(source_file, target_folder, 4096);
	}

	/**
	 * paste a file to the target folder
	 * 
	 * @param source_file
	 *            the source file
	 * @param target_folder
	 *            the target folder
	 * @param bufferSize
	 *            the buffer size
	 * @return
	 * @throws FileNotFoundException
	 */
	public static void doPaste(File source_file, File target_folder,
			int bufferSize) throws FileNotFoundException, IOException {
		String path = target_folder.getAbsolutePath();
		String name = source_file.getName();
		File target_file = new File(path + File.separator + name);

		FileChannel in = new FileInputStream(source_file).getChannel();
		FileChannel out = new FileOutputStream(target_file).getChannel();

		// copy source file to target folder directly
		in.transferTo(0, in.size(), out);

		in.close();
		out.close();
	}

	/**
	 * 
	 * @param size
	 * @return the formatted size 返回友好的大小格式
	 */
	private static String getFormatSize(double size) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		int i = 0;
		String[] unit = FILE_SIZE_UNIT;
		for (i = 0; i < unit.length; i++) {
			if ((long) (size / 1024) > 0) {
				size /= 1024;
			} else {
				break;
			}
		}
		return nf.format(size) + unit[i];
	}

	/**
	 * 
	 * @param f
	 * @return
	 * @throws IOException
	 *             读取文本文件内容
	 */
	public static String readTextFile(File f) throws IOException {

		StringBuffer buf = new StringBuffer();

		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(f)/* , "UTF-8" */));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			buf.append(inputLine);
			buf.append('\n');
		}
		in.close();
		return buf.toString();

	}

	public static void toTextFile(File f, String content) throws IOException {
		FileOutputStream out = new FileOutputStream(f);
		out.write(content.getBytes("UTF-8"));
		out.close();
	}

	/**
	 * 拷贝目录至目标目录
	 * 
	 * @param inputDir
	 * @param outputDir
	 * @throws IOException
	 */
	public static void copyDir(File inputDir, File outputDir)
			throws IOException {
		if (outputDir == null) {
			throw new RuntimeException("argruments is null");
		}
		if (!outputDir.exists()) {
			outputDir.mkdirs();
		}
		File[] files = inputDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File destFile = new File(outputDir.getAbsolutePath()
					+ File.separator + files[i].getName());
			if (!destFile.exists()) {
				if (files[i].isDirectory()) {
					destFile.mkdir();
				}
			}
			if (files[i].isDirectory()) {
				copyDir(files[i], new File(outputDir.getAbsolutePath()
						+ File.separator + files[i].getName()));
			} else {
				FileUtils.copyFile(files[i], destFile);
			}
		}
	}

	/*
	 * 拷贝文件或目录至目标文件夹内
	 */
	public static void copyFileOrDirectoryToTargetFolder(File inputFile,
			File outputFile) throws IOException {
		if (inputFile.isFile()) {
			copyFile(inputFile, new File(outputFile.getAbsoluteFile()
					+ File.separator + inputFile.getName()));
		}
		if (inputFile.isDirectory()) {
			copyDir(inputFile, outputFile);
		}
	}

	/**
	 * 拷贝文件 inputFile:源文件 outputFile:目标文件 注意，此方法只支持文件至文件的拷贝
	 */
	public static void copyFile(File inputFile, File outputFile)
			throws IOException {
		if (inputFile == null) {
			throw new RuntimeException("inputFile is null");
		}

		if (!inputFile.isFile()) {
			throw new IOException("inputFile is not a File");
		}

		if (!inputFile.exists()) {
			throw new IOException("inputFile is not exists");
		}

		outputFile.getParentFile().mkdirs();

		BufferedInputStream fr = new BufferedInputStream(new FileInputStream(
				inputFile));
		BufferedOutputStream fw = new BufferedOutputStream(
				new FileOutputStream(outputFile));
		byte[] buf = new byte[8192];
		int n;
		while ((n = fr.read(buf)) >= 0)
			fw.write(buf, 0, n);
		fr.close();
		fw.close();
	}

	/**
	 * 返回硬件所有分区所代表的字符串,如C:/,D:/,E:/,E:/,F:/
	 */
	public static List<String> getSystemDisks() {
		File[] disks = File.listRoots();

		String[] diskPaths = new String[disks.length];
		for (int i = 0; i < diskPaths.length; i++) {
			diskPaths[i] = disks[i].getAbsolutePath();
		}
		return Arrays.asList(diskPaths);
	}

	/**
	 * @param disk
	 * @return 获取每个分区大小,如c,d,e,f,g等
	 */
	public static long getDiskSize(File disk) {
		return disk.getTotalSpace();
	}

	/**
	 * 
	 * @param filename
	 * @return 返回此抽象路径名的规范路径名字符串。
	 *         规范路径名是绝对路径名，并且是惟一的。规范路径名的准确定义与系统有关。如有必要，此方法首先将路径名转换为绝对路径名， 这与调用
	 *         getAbsolutePath() 方法的效果一样，然后用与系统相关的方式将它映射到其惟一路径名。这通常涉及到从路径名中移除多
	 *         余的名称（比如 "." 和 ".."）、解析符号连接（对于 UNIX 平台），以及将驱动器号转换为标准大小写形式（对于
	 *         Microsoft Windows 平台）。
	 *         每个表示现存文件或目录的路径名都有一个惟一的规范形式。每个表示不存在文件或目录的路径名也有一个惟一的规范形式
	 *         。不存在文件或目录路径名的
	 *         规范形式可能不同于创建文件或目录之后同一路径名的规范形式。同样，现存文件或目录路径名的规范形式可能不同于删除文件或目录之后同一路径
	 *         名的规范形式。
	 */
	public static String getCanonicalFileName(String filename) {
		String canonicalFileName = filename;
		try {
			canonicalFileName = new File(filename).getCanonicalPath();
		} catch (IOException ignore) {
		}
		return canonicalFileName;
	}

	/**
	 * 返回规范路径名字符串，它与此抽象路径名表示相同的文件或目录
	 */
	public static File canonise(File file) {
		try {
			return file.getCanonicalFile();
		} catch (IOException ioe) {
			return file;
		}
	}

	/**
	 * appends the passed string to the file identified by the passed name. If
	 * the file does not exist an exception will be thrown.
	 * 
	 * @param fileName
	 * @param data
	 * @throws IOException
	 *             往文件中追加字符串内容
	 */
	public static final void appendsToFile(String fileName, String data)
			throws IOException {
		File file = new File(fileName);
		if (!file.exists()) {
			throw new IOException("file: " + fileName + " does not exist");
		}
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		raf.seek(raf.length());
		// raf.writeChars( data );
		// raf.writeUTF(data);
		raf.write(data.getBytes());// 此种方式最好
		raf.close();
	}

	/**
	 * Purge a directory on disk.
	 * 
	 * @param directory
	 *            The directory to purge.
	 */
	private static void purgeDirectory(File directory) {
		if (directory.exists()) {
			File[] files = directory.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					purgeDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}

			directory.delete();
		}
	}

	// 判断某路径是否为文件
	public static boolean isFile(String fileName) {
		File testFile = new File(fileName);

		if ((testFile.exists()) && (testFile.isFile())) {
			return true;
		} else {
			return false;
		}
	}

	// 判断某路径是否为目录
	public static boolean isDirectory(String fileName) {
		File testFile = new File(fileName);

		if ((testFile.exists()) && (testFile.isDirectory())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Rekursives l?schen von Dateien und Verzeichnissen
	 * 
	 * @param file
	 *            Falls das Argument eine Datei ist, wird diese gel?scht. Ist es
	 *            ein Verzeichnis, werden alle dieses mitsamt aller darin
	 *            liegenden Verzeichnisse und Dateien gel?scht.
	 */
	public static void deleteRecursive(final File file) {
		if (file == null)
			return;

		if (file.isDirectory()) {
			final File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++)
				deleteRecursive(files[i]);
		}

		file.delete();
	}

	/** THE system tmp dir "java.io.tmpdir" */
	public static final File TMP_DIR = new File(System
			.getProperty("java.io.tmpdir"));

	/**
	 * Creates a temp directory in java.io.tmpdir.
	 * 
	 * @param prefix
	 * @return temporary directory
	 * 
	 * @see FileUtilities#createNewTempDir(String, File )
	 */
	public static File createNewTempDir(final String prefix) {
		return createNewTempDir(prefix, TMP_DIR);
	}

	/**
	 * Creates a temp directory inside the given one. It uses
	 * <code>System.currentTimeMillis</code> for naming the new temp dir. This
	 * method can hang a little while in the case the directory it tries to
	 * create already exist.
	 * 
	 * @param prefix
	 * @param parentDir
	 * @return temporary directory
	 */
	public static File createNewTempDir(final String prefix,
			final File parentDir) {
		while (true) {
			final File newDir = new File(parentDir, prefix
					+ System.currentTimeMillis());
			if (newDir.mkdir())
				return newDir;
		}
	}

	// 传入的是一个文件全路径
	public static void createDirs(String filename) {

		int pos = -1;
		// look for the last directory separator in the filename
		for (int i = filename.length() - 1; i >= 0; i--) {

			if (filename.charAt(i) == File.separatorChar) {

				pos = i;
				i = -1;// i=-1时退出for循环
			}
		}
		if (pos != -1) {
			File dir = new File(filename.substring(0, pos));
			dir.mkdirs();
		}
	}

	public static void copyFolder(String srcPath, String destPath,
			boolean overwrite) {
		File srcFolder = new File(srcPath);
		if (srcFolder.exists() && srcFolder.isDirectory()) {
			File destFolder = new File(destPath);
			if (destFolder.exists() && destFolder.isDirectory()) {
				// return ;
			} else {
				try {
					destFolder.mkdirs();
				} catch (Exception e) {
				}
			}
			FileFilter filter = new FileFilter() {
				public boolean accept(File file) {
					if (file.isDirectory()) {
						String name = file.getName();
						if (name.equals("CVS")) {
							return false;
						}
					}
					if (file.getAbsolutePath().endsWith(".swp")) {
						return false;
					}
					return true;
				}
			};
			File[] files = srcFolder.listFiles(filter);
			for (int i = 0; i < files.length; i++) {
				File file = new File(destFolder, files[i].getName());
				if (files[i].isDirectory()) {
					copyFolder(files[i].getAbsolutePath(), file
							.getAbsolutePath(), overwrite);
				} else {
					if (!file.exists() || overwrite) {
						streamCopyFile(files[i], file);
					}
				}
			}
		}
	}

	public static void copyFile(String srcFile, String destFile) {
		streamCopyFile(new File(srcFile), new File(destFile));
	}

	public static void streamCopyFile(File srcFile, File destFile) {
		try {
			FileInputStream fi = new FileInputStream(srcFile);
			FileOutputStream fo = new FileOutputStream(destFile);
			byte[] buf = new byte[1024];
			int readLength = 0;
			while (readLength != -1) {
				readLength = fi.read(buf);
				if (readLength != -1) {
					fo.write(buf, 0, readLength);
				}
			}
			fo.close();
			fi.close();
		} catch (Exception e) {
		}
	}

	/**
	 * writes the the passed string to a file created using the passed file name
	 * using the defined character encoding.
	 * 
	 * @param fileName
	 * @param data
	 * @param encoding
	 * @throws IOException
	 */
	public static final void writeToFile(String fileName, String data,
			String encoding) throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
		OutputStreamWriter osr = new OutputStreamWriter(fos, encoding);
		osr.write(data);
		osr.close();
	}

	// ------------------------------------------------------------------------

	/**
	 *** Copies bytes from one stream to another
	 *** 
	 * @param input
	 *            The InputStream
	 *** @param output
	 *            The OutputStream
	 *** @return The number of bytes copied
	 **/
	public static int copyStreams(InputStream input, OutputStream output)
			throws IOException {
		return copyStreams(input, output, -1);
	}

	/**
	 *** Copies bytes from one stream to another
	 *** 
	 * @param input
	 *            The InputStream
	 *** @param output
	 *            The OutputStream
	 *** @param maxLen
	 *            The maximum number of bytes to copy
	 *** @return The number of bytes copied
	 **/
	public static int copyStreams(InputStream input, OutputStream output,
			int maxLen) throws IOException {

		/* copy nothing? */
		if (maxLen == 0) {
			return 0;
		}

		/* copy bytes */
		int length = 0; // count of bytes copied
		byte tmpBuff[] = new byte[10 * 1024]; // 10K blocks
		while (true) {

			/* read length */
			int readLen;
			if (maxLen >= 0) {
				readLen = maxLen - length;
				if (readLen == 0) {
					break; // done reading
				} else if (readLen > tmpBuff.length) {
					readLen = tmpBuff.length; // max block size
				}
			} else {
				readLen = tmpBuff.length;
			}

			/* read input stream */
			int cnt = input.read(tmpBuff, 0, readLen);

			/* copy to output stream */
			if (cnt < 0) {
				if ((maxLen >= 0) && (length != maxLen)) {
					// Print.logError("Copy size mismatch: " + maxLen + " => " +
					// length);
				}
				break;
			} else if (cnt > 0) {
				output.write(tmpBuff, 0, cnt);
				length += cnt;
				if ((maxLen >= 0) && (length >= maxLen)) {
					break; // per 'maxLen', done copying
				}
			} else {
				// Print.logDebug("Read 0 bytes ... continuing");
			}

		}
		output.flush();

		/* return number of bytes copied */
		return length;
	}

	/**
	 *** Returns an array of bytes read from the specified InputStream
	 *** 
	 * @param input
	 *            The InputStream
	 *** @return The array of bytes read from the InputStream
	 **/
	public static byte[] readStream(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		copyStreams(input, output);
		return output.toByteArray();
	}

	// ------------------------------------------------------------------------

	/**
	 *** Writes a String to the specified OutputStream
	 *** 
	 * @param output
	 *            The OutputStream
	 *** @param dataStr
	 *            The String to write to the OutputStream
	 **/
	public static void writeStream(OutputStream output, String dataStr)
			throws IOException {
		byte data[] = dataStr.getBytes();
		output.write(data, 0, data.length);
	}

	// ------------------------------------------------------------------------

	/**
	 *** Opens the specified file for reading
	 *** 
	 * @param file
	 *            The path of the file to open
	 *** @return The opened InputStream
	 **/
	public static InputStream openInputFile(String file) {
		if ((file != null) && !file.equals("")) {
			return openInputFile(new File(file));
		} else {
			return null;
		}
	}

	/**
	 *** Opens the specified file for reading
	 *** 
	 * @param file
	 *            The file to open
	 *** @return The opened InputStream
	 **/
	public static InputStream openInputFile(File file) {
		try {
			return new FileInputStream(file);
		} catch (IOException ioe) {
			// Print.logError("Unable to open file: " + file + " [" + ioe +
			// "]");
			return null;
		}
	}

	// ------------------------------------------------------------------------

	/**
	 *** Closes the specified InputStream
	 *** 
	 * @param in
	 *            The InputStream to close
	 **/
	public static void closeStream(InputStream in) {
		if (in != null) {
			try {
				in.close();
			} catch (IOException ioe) {
				// Print.logError("Unable to close stream: " + ioe);
			}
		}
	}

	/**
	 *** Closes the specified OutputStream
	 *** 
	 * @param out
	 *            The OutputStream to close
	 **/
	public static void closeStream(OutputStream out) {
		if (out != null) {
			try {
				out.close();
			} catch (IOException ioe) {
				// Print.logError("Unable to close stream: " + ioe);
			}
		}
	}

	/**
	 *** Returns an array of bytes read from the specified file
	 *** 
	 * @param file
	 *            The file path from which the byte array is read
	 *** @return The byte array read from the specified file
	 **/
	public static byte[] readFile(String file) {
		if ((file != null) && !file.equals("")) {
			return readFile(new File(file));
		} else {
			return null;
		}
	}

	/**
	 *** Returns an array of bytes read from the specified file
	 *** 
	 * @param file
	 *            The file from which the byte array is read
	 *** @return The byte array read from the specified file
	 **/
	public static byte[] readFile(File file) {
		if (file == null) {
			return null;
		} else if (!file.exists()) {
			// Print.logError("File does not exist: " + file);
			return null;
		} else {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				return readStream(fis);
			} catch (IOException ioe) {
				// Print.logError("Unable to read file: " + file + " [" + ioe +
				// "]");
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException ioe) {/* ignore */
					}
				}
			}
			return null;
		}
	}

	// ------------------------------------------------------------------------

	/**
	 *** Reads a single line of characters from the specified InputStream,
	 * terminated by either a newline (\n) or carriage-return (\r)
	 *** 
	 * @param input
	 *            The InputStream
	 *** @return The line read from the InputStream
	 **/
	public static String readLine(InputStream input) throws IOException {
		StringBuffer sb = new StringBuffer();
		while (true) {
			int ch = input.read();
			if (ch < 0) { // eof
				throw new EOFException("End of InputStream");
			} else if ((ch == '\r') || (ch == '\n')) {
				return sb.toString();
			}
			sb.append((char) ch);
		}
	}

	/**
	 *** Reads a single line of characters from stdin, terminated by either a
	 * newline (\n) or carriage-return (\r)
	 *** 
	 * @return The line read from stdin
	 **/
	public static String readLine_stdin() throws IOException {
		while (System.in.available() > 0) {
			System.in.read();
		}
		return readLine(System.in);
	}

	/**
	 *** Prints a message, and reads a line of text from stdin
	 *** 
	 * @param msg
	 *            The message to print
	 *** @param dft
	 *            The default String returned, if no text was entered
	 *** @return The line of text read from stdin
	 **/
	public static String readString_stdin(String msg, String dft)
			throws IOException {
		if (msg == null) {
			msg = "";
		}
		// Print.sysPrintln(msg + "    [String: default='" + dft + "'] ");
		for (;;) {
			// Print.sysPrint("?");
			String line = readLine_stdin();
			if (line.equals("")) {
				if (dft != null) {
					return dft;
				} else {
					// if there is no default, a non-empty String is required
					// Print.sysPrint("String required, please re-enter] ");
					continue;
				}
			}
			return line;
		}
	}

	// ------------------------------------------------------------------------

	/**
	 *** Writes a byte array to the specified file
	 *** 
	 * @param data
	 *            The byte array to write to the file
	 *** @param file
	 *            The file to which the byte array is written
	 *** @return True if the bytes were successfully written to the file
	 *** @throws IOException
	 *             if an error occurred.
	 **/
	public static boolean writeFile(byte data[], File file) throws IOException {
		return writeFile(data, file, false);
	}

	/**
	 *** Writes a byte array to the specified file
	 *** 
	 * @param data
	 *            The byte array to write to the file
	 *** @param file
	 *            The file to which the byte array is written
	 *** @param append
	 *            True to append the bytes to the file, false to overwrite.
	 *** @return True if the bytes were successfully written to the file
	 *** @throws IOException
	 *             if an error occurred.
	 **/
	public static boolean writeFile(byte data[], File file, boolean append)
			throws IOException {
		if ((data != null) && (file != null)) {
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file, append);
				fos.write(data, 0, data.length);
				return true;
			} finally {
				try {
					fos.close();
				} catch (Throwable t) {/* ignore */
				}
			}
		}
		return false;
	}

	/**
	 *** Gets the extension characters from the specified file
	 *** 
	 * @param file
	 *            The file
	 *** @return The extension characters
	 **/
	public static String getExtension(File file) {
		if (file != null) {
			String fileName = file.getName();
			int p = fileName.indexOf(".");
			if ((p >= 0) && (p < (fileName.length() - 1))) {
				return fileName.substring(p + 1);
			}
		}
		return "";
	}

	/**
	 *** Returns true if the specified file path has an extension which matches
	 * one of the extensions listed in the specified String array
	 *** 
	 * @param filePath
	 *            The file path/name
	 *** @param extn
	 *            An array of file extensions
	 *** @return True if teh specified file path has a matching exention
	 **/
	public static boolean hasExtension(String filePath, String extn[]) {
		if (filePath != null) {
			return hasExtension(new File(filePath), extn);
		}
		return false;
	}

	/**
	 *** Returns true if the specified file has an extension which matches one of
	 * the extensions listed in the specified String array
	 *** 
	 * @param file
	 *            The file
	 *** @param extn
	 *            An array of file extensions
	 *** @return True if teh specified file has a matching exention
	 **/
	public static boolean hasExtension(File file, String extn[]) {
		if ((file != null) && (extn != null)) {
			String e = getExtension(file);
			for (int i = 0; i < extn.length; i++) {
				if (e.equalsIgnoreCase(extn[i])) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 *** Removes the extension from the specified file path
	 *** 
	 * @param filePath
	 *            The file path from which the extension will be removed
	 *** @return The file path with the extension removed
	 **/
	public static String removeExtension(String filePath) {
		if (filePath != null) {
			return removeExtension(new File(filePath));
		}
		return filePath;
	}

	/**
	 *** Removes the extension from the specified file
	 *** 
	 * @param file
	 *            The file from which the extension will be removed
	 *** @return The file path with the extension removed
	 **/
	public static String removeExtension(File file) {
		if (file != null) {
			String fileName = file.getName();
			int p = fileName.indexOf(".");
			if (p > 0) { // '.' in column 0 not allowed
				file = new File(file.getParentFile(), fileName.substring(0, p));
			}
			return file.getPath();
		} else {
			return null;
		}
	}

	public static boolean recursiveDeleteNoCheck(File f) {
		try {
			if (f.isDirectory()) {
				File[] files = f.listFiles();
				for (int i = 0; i < files.length; i++) {
					if (!recursiveDeleteNoCheck(files[i])) {

						return (false);
					}
				}
				if (!f.delete()) {

					return (false);
				}
			} else {
				if (!f.delete()) {

					return (false);
				}
			}
		} catch (Exception ignore) {/* ignore */
		}

		return (true);
	}

	/**
	 * reads a Text file from its resource. For accessing the resource an
	 * <code>InputStreamReader</code> with encoding read from
	 * <code>CharsetUtils.getSystemCharset()</code>
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static StringBuffer readTextFile(URL url) throws IOException {
		return readTextFile(url.openStream());
	}

	/**
	 * reads a Text file from its resource. For accessing the resource an
	 * <code>InputStreamReader</code> with encoding read from
	 * <code>CharsetUtils.getSystemCharset()</code>
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static StringBuffer readTextFile(InputStream is) throws IOException {
		InputStreamReader isr = new InputStreamReader(is, CharsetUtils
				.getSystemCharset());
		return readTextFile(isr);
	}

	/**
	 * reads a Text file from its resource.
	 * 
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	public static StringBuffer readTextFile(Reader reader) throws IOException {
		StringBuffer sb = new StringBuffer(10000);
		int c = 0;
		while ((c = reader.read()) > -1) {
			sb.append((char) c);
		}
		reader.close();

		return sb;
	}

	/**
	 * Return the contents of the Stream as a String. Note: If the InputStream
	 * represents a null String, the Java implementation will try to read from
	 * the stream for a certain amount of time before timing out.
	 * 
	 * @param is
	 *            the InputStream to transform into a String
	 * @return the String representation of the Stream
	 */
	public static String getStringFromStream(InputStream is) {
		if (null == is)
			return null;
		try {
			InputStreamReader reader = new InputStreamReader(is);
			char[] buffer = new char[1024];
			StringWriter writer = new StringWriter();
			int bytes_read;
			while ((bytes_read = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, bytes_read);
			}
			return (writer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != is)
				try {
					is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return null;
	}

	public static void copy(InputStream in, OutputStream out)
			throws IOException {
		int len;
		byte[] buffer = new byte[0xFFFF];
		while ((len = in.read(buffer)) > 0)
			out.write(buffer, 0, len);
	}
}