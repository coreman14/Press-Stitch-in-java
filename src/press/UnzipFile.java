package press;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class UnzipFile {
	
	/**
	 * Unzip zip file to given directory
	 * @param fileZip The zip file to extract
	 * @param dest The destination of the files
	 * @throws IOException When fileZip is not found
	 */
	public static void unzipfile(String fileZip, String dest) throws IOException {
		unzipfile(fileZip, dest, false);
	}
	
	/**
	 * Unzip zip file to current directory.
	 * @param fileZip The zip file to extract
	 * @param verbose If true, will print out progress
	 * @throws IOException When fileZip is not found
	 */
	public static void unzipfile(String fileZip, Boolean verbose) throws IOException {
		unzipfile(fileZip, ".", verbose);
	}
	
	/**
	 * Unzip zip file to current directory.
	 * @param fileZip The zip file to extract
	 * @throws IOException When fileZip is not found
	 */
	public static void unzipfile(String fileZip) throws IOException {
		unzipfile(fileZip, ".");
	}
	
	/**
	 * Unzip zip file to given directory
	 * @param fileZip The zip file to extract
	 * @param dest The destination of the files
	 * @param verbose If true, will print out progress
	 * @throws IOException Throws io exception zip_file is not found or a folder cannot be created
	 */
    public static void unzipfile(String fileZip, String dest, Boolean verbose) throws IOException {
        final File destDir = new File(dest);
        final byte[] buffer = new byte[1024];
        
        try (final ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip))) {
        System.out.println();
        int files_in_zip = zipEntriesCount(fileZip);
        int count = 0;
        System.out.println("Number of files in zip: " + files_in_zip);
        
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
        	if (verbose) {
        		count++;
        		System.out.println("Current File: " + zipEntry.getName() + ", #" +count);
        	}
            final File newFile = newFile(destDir, zipEntry);
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }
                final FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        }
    }

    /**
     * @see <a href="https://snyk.io/research/zip-slip-vulnerability">Zip Slip vulnerability</a>
     */
    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
    /**
     * Returns number of files in given zip file
     * @param path Path to zip
     * @return Number of files in zip
     * @throws IOException When file is not found
     */
    public static int zipEntriesCount(String path) throws IOException {
        ZipFile zf = new ZipFile(path);
        int size = zf.size();
        zf.close();
        return size;
   }
    
}