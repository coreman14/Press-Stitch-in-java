package press;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
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
	
	
	public static void extractRPATool(String toolName) {
		if (extractedTool) {
			return;
		}
		String path = System.getProperty("user.dir");
		File file = new File(path+"/" + toolName);
		if (file.exists()) {
			System.out.println(toolName  +" already exsist's. Skipping extract.");
			extractedTool = true;
			return;
		}
		InputStream rpatool = press_stitch_archive.class.getResourceAsStream(toolName);
		try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[8192];
            while (true) {
				assert rpatool != null;
				if ((read = rpatool.read(bytes)) == -1) break;
				outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file.deleteOnExit();
		extractedTool = true;
	}
	
	
	
	public static void runRPAEXE(String rpaFilename,File output) throws IOException, InterruptedException {
		Process p = Runtime.getRuntime().exec("rpatool.exe -x \"" + rpaFilename + "\" -o \"" + output.getAbsolutePath() + "\"" );
		p.waitFor();
	}
	
	private static void runRPAPy(String rpaFilename, File output) throws IOException, InterruptedException { //Run rpatool with python
		try {
			Process p = Runtime.getRuntime().exec("python rpatool.py -x \"" + rpaFilename + "\" -o \"" + output.getAbsolutePath() + "\"" );
			p.waitFor();
		}
		catch (IOException | InterruptedException e) { //If fails try with python3
			Process p = Runtime.getRuntime().exec("python3 rpatool.py -x \"" + rpaFilename + "\" -o \"" + output.getAbsolutePath() + "\"" );
			p.waitFor();
		}

		
	}
	
	public static void extractRPAFile(String rpaFilename,File output) {
		System.out.println("Extracting RPA file " + rpaFilename + " to " + output);
		//Place exe in temp dir to use
		String toolName = "";
		assert output.exists() || !output.mkdirs();
		try {
			String OS = System.getProperty("os.name").toLowerCase();
			if (OS.contains("win")) {
				toolName = "rpatool.exe";
				extractRPATool("rpatool.exe");
				runRPAEXE(rpaFilename, output);
			}
			else if (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0 || OS.contains("mac")) {
				toolName = "rpatool.py";
				extractRPATool("rpatool.py");
				runRPAPy(rpaFilename, output);
			}
		} catch (IOException | InterruptedException e) {
			if (toolName.equals("rpatool.py"))
				System.out.println("Error: Could not run python file. Make sure python is installed. Try's python then python3.");
			else {
				System.out.println("Error: Could not run exe. If environment is not windows, contact developer.");
			}
		}
		
		
	}






	//This runs after file has been extracted
	public static void unpackArchive(String folder) { //Folder is the folder we are checking in extracted
		String folderArchive = Paths.get(cwd, folder, "Game") + "\\";
	    File destinationFolder = new File(Paths.get(folderExtracted, folder).toString());
	    
	    if(destinationFolder.exists()){
	        System.out.println("Extracted data folder " + destinationFolder + " exists, skipping RPA extract");
	        return;
	    }
	    System.out.println("Extracting rpas from " + folder + ", please wait...");
	    extractRPAFile(folderArchive + fileNameArchive, destinationFolder);
	    if (!folder.equals(destinationFolder + ".3b-all")) {
	        extractRPAFile(folderArchive + fileNameScripts, destinationFolder);}
	}

	//Extract all the RPA files. Called both by main and by the press-stitch.py script.
    public static void extractAllRPAFiles() {
    	File folderExtracted2 = new File(folderExtracted);
		assert folderExtracted2.exists() || !folderExtracted2.mkdirs();
	    for (String ver: folderVersion) {
	        File extractFolder = new File(Paths.get(folderExtracted, folderName + ver).toString());
	        File UnZippedGame = new File(Paths.get(folderName + ver).toString());
	        if (!UnZippedGame.exists()){
	        	try {
					if (Files.list(Path.of(cwd)).anyMatch((path) -> path.toString().contains(folderName + ver))) {
						System.out.println("Extracting " + folderName + ver + ".zip to " + folderName + ver);
						UnzipFile.unzipfile(folderName + ver + ".zip");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	        if (!extractFolder.exists()) { 
	            unpackArchive(folderName + ver);
	        }
	        else {
	        	System.out.println("Extracted data folder " + extractFolder + " exists, skipping RPA extract");
	        }
	    }
    }
	public static void main(String[] args) {
	    extractAllRPAFiles();
	}
}
