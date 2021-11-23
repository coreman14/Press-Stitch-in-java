package press;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
/**
 * CHANGES FROM PYTHON VERSION
 * 1. extractAllRPAFiles: Removed check and verify if the folders are created beforehand, it will not extract anything from the RPAs.
 * @author rcgam
 *
 */
public class press_stitch_archive {
	public static String folderExtracted = "Extracted";
	public static String folderName = "Press-SwitchV0";
	public static String[] folderVersion = {".5c-pc"};
	public static String fileNameArchive = "archive.rpa";
	public static String fileNameScripts = "scripts.rpa";
	public static String cwd = System.getProperty("user.dir");
	public static boolean extractedTool = false;
	
	
	public static void extractRPATool() {
		if (extractedTool) {
			return;
		}
		String path = System.getProperty("user.dir");
		File file = new File(path+"/rpatool.exe");
		InputStream rpatool = press_stitch_archive.class.getResourceAsStream("rpatool.exe");
		try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[8192];
            while ((read = rpatool.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file.deleteOnExit();
		extractedTool = true;
	}
	public static void extractRPAFile(String rpaFilename,File output) {
		System.out.println("Extracting RPA file " + rpaFilename + " to " + output);
		//Place exe in temp dir to use
		
		if (!output.exists()) {
			output.mkdirs();
		}
		try {
			Process p = Runtime.getRuntime().exec("rpatool.exe -x \"" + rpaFilename + "\" -o \"" + output.getAbsolutePath() + "\"" );
			p.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	//This runs after file has been extracted
	public static void unpackArchive(String folder) { //Folder is the folder we are checking in extracted
		String folderArchive = Paths.get(cwd, folder, "Game").toString() + "\\";
	    File destinationFolder = new File(Paths.get(folderExtracted, folder).toString());
	    
	    if(destinationFolder.exists()){
	        System.out.println("Extracted data folder " + destinationFolder + " exists, skipping RPA extract");
	        return;
	    }
	    System.out.println("Extracting " + folder + ", please wait...");
	    extractRPAFile(folderArchive + fileNameArchive, destinationFolder);
	    if (!folder.equals(destinationFolder + ".3b-all")) {
	        extractRPAFile(folderArchive + fileNameScripts, destinationFolder);}
	}

	//Creates the folders to extract to
	public static void createFolders(File folder) {
	    if (!folder.exists()) {
	    	folder.mkdirs();
	    }
	}
	//Verifies that source folders exist
    public static void verifyFolders(File folder) {
    	if (!folder.exists()) {
    		System.out.println(folderName + folder);
    		System.out.println("Data folders not present aborting");
	        System.exit(1);
    	}
    }
	//Extract all the RPA files. Called both by main and by the press-stitch.py script.
    public static void extractAllRPAFiles() {
    	File folderExtracted2 = new File(folderExtracted);
	    if (!folderExtracted2.exists()) {
	    	folderExtracted2.mkdirs();
	    }
	    for (String ver: folderVersion) {
	        File extractFolder = new File(Paths.get(folderExtracted, folderName + ver).toString());
	        
	        System.out.println(folderName + ver);
	        if (!extractFolder.exists()) { //
	            unpackArchive(folderName + ver);
	        }
	        else {
	        	System.out.println("Extracted data folder " + extractFolder.toString() + " exists, skipping RPA extract");
	        }
	    }
    }
	public static void main(String[] args) {
//		extractRPAFile("", null);
		//Press-SwitchV0.5c-pc
		extractRPATool();
	    extractAllRPAFiles();
	}
}
