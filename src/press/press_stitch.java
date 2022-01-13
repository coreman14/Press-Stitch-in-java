package press;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import org.apache.commons.io.FileUtils;

public class press_stitch {
	public String filename_03 = "Press-SwitchV0.3b-all";
	public String filename_04 = "Press-SwitchV0.4a-pc";
	public String filename_05 = "Press-SwitchV0.5c-pc";
	public String filename_06 = "Press-SwitchV0.6";
	public HashMap<String, String> characterLabelMap = new HashMap<>();
	public HashMap<String, Boolean> characterDoRemap = new HashMap<>();
	public HashMap<String, String> pyVariables = new HashMap<>();
	public HashMap<String, String> personDispVars = new HashMap<>();
	public boolean inlineErrors = false;
	public List<RenPyThread> threads = Collections.synchronizedList(new ArrayList<>());
	public ArrayList<RenPyLabelCall> labelCalls = new ArrayList<>();

	public void fillvars() {
		characterLabelMap.put("alma", "alma");
		characterLabelMap.put("amber", "amber");
		characterLabelMap.put("amberd", "amber");
		characterLabelMap.put("anna", "anna");
		characterLabelMap.put("april", "april");
		characterLabelMap.put("candice", "candice");
		characterLabelMap.put("candiced", "candice");
		characterLabelMap.put("chris", "chris");
		characterLabelMap.put("chrisd", "chris");
		characterLabelMap.put("chrisghost", "chris");
		characterLabelMap.put("ciel", "ciel");
		characterLabelMap.put("cindy", "cindy");
		characterLabelMap.put("donald", "donald");
		characterLabelMap.put("donaldd", "donald");
		characterLabelMap.put("donaldflash", "donald");
		characterLabelMap.put("eliza", "eliza");
		characterLabelMap.put("elizad", "eliza");
		characterLabelMap.put("elizaflash", "eliza");
		characterLabelMap.put("elizaghost", "eliza");
		characterLabelMap.put("erin", "erin");
		characterLabelMap.put("erind", "erin");
		characterLabelMap.put("eringhost", "erin");
		characterLabelMap.put("hillary", "hillary");
		characterLabelMap.put("hillaryd", "hillary");
		characterLabelMap.put("jenna", "jenna");
		characterLabelMap.put("jennifer", "jennifer");
		characterLabelMap.put("jenniferd", "jennifer");
		characterLabelMap.put("jillian", "jillian");
		characterLabelMap.put("jilliand", "jillian");
		characterLabelMap.put("karyn", "karyn");
		characterLabelMap.put("karynd", "karyn");
		characterLabelMap.put("karynflash", "karyn");
		characterLabelMap.put("karynghost", "karyn");
		characterLabelMap.put("kayla", "kayla");
		characterLabelMap.put("kaylad", "kayla");
		characterLabelMap.put("main", "main");
		characterLabelMap.put("maind", "main");
		characterLabelMap.put("mainflash", "main");
		characterLabelMap.put("mainghost", "main");
		characterLabelMap.put("martha", "martha");
		characterLabelMap.put("marthad", "martha");
		characterLabelMap.put("marthaghost", "martha");
		characterLabelMap.put("melina", "melina");
		characterLabelMap.put("michelle", "michelle");
		characterLabelMap.put("michelled", "michelle");
		characterLabelMap.put("michelleghost", "michelle");
		characterLabelMap.put("mika", "mika");
		characterLabelMap.put("mikad", "mika");
		characterLabelMap.put("mother", "mother");
		characterLabelMap.put("nelson", "nelson");
		characterLabelMap.put("nick", "nick");
		characterLabelMap.put("nurse", "nurse");
		characterLabelMap.put("sean", "sean");
		characterLabelMap.put("vanessa", "vanessa");
		characterLabelMap.put("vanessad", "vanessa");
		characterLabelMap.put("waitress", "waitress");
		characterDoRemap.put("alma", false);
		characterDoRemap.put("amber", false);
		characterDoRemap.put("amberd", true);
		characterDoRemap.put("anna", false);
		characterDoRemap.put("april", false);
		characterDoRemap.put("candice", false);
		characterDoRemap.put("candiced", true);
		characterDoRemap.put("chris", false);
		characterDoRemap.put("chrisd", true);
		characterDoRemap.put("chrisghost", false);
		characterDoRemap.put("ciel", false);
		characterDoRemap.put("cindy", false);
		characterDoRemap.put("donald", false);
		characterDoRemap.put("donaldd", true);
		characterDoRemap.put("donaldflash", false);
		characterDoRemap.put("eliza", false);
		characterDoRemap.put("elizad", true);
		characterDoRemap.put("elizaflash", false);
		characterDoRemap.put("elizaghost", false);
		characterDoRemap.put("erin", false);
		characterDoRemap.put("erind", true);
		characterDoRemap.put("eringhost", false);
		characterDoRemap.put("hillary", false);
		characterDoRemap.put("hillaryd", true);
		characterDoRemap.put("jenna", false);
		characterDoRemap.put("jennifer", false);
		characterDoRemap.put("jenniferd", true);
		characterDoRemap.put("jillian", false);
		characterDoRemap.put("jilliand", true);
		characterDoRemap.put("karyn", false);
		characterDoRemap.put("karynd", true);
		characterDoRemap.put("karynflash", false);
		characterDoRemap.put("karynghost", false);
		characterDoRemap.put("kayla", false);
		characterDoRemap.put("kaylad", true);
		characterDoRemap.put("main", false);
		characterDoRemap.put("maind", true);
		characterDoRemap.put("mainflash", false);
		characterDoRemap.put("mainghost", false);
		characterDoRemap.put("martha", false);
		characterDoRemap.put("marthad", true);
		characterDoRemap.put("marthaghost", false);
		characterDoRemap.put("melina", false);
		characterDoRemap.put("michelle", false);
		characterDoRemap.put("michelled", true);
		characterDoRemap.put("michelleghost", false);
		characterDoRemap.put("mika", false);
		characterDoRemap.put("mikad", true);
		characterDoRemap.put("mother", false);
		characterDoRemap.put("nelson", false);
		characterDoRemap.put("nick", false);
		characterDoRemap.put("nurse", false);
		characterDoRemap.put("sean", false);
		characterDoRemap.put("vanessa", false);
		characterDoRemap.put("vanessad", true);
		characterDoRemap.put("waitress", false);
		pyVariables.put("Al.display", "alma");
		pyVariables.put("Am.display", "amber");
		pyVariables.put("Can.display", "candice");
		pyVariables.put("ch.display", "chris");
		pyVariables.put("Do.display", "donald");
		pyVariables.put("e.display", "eliza");
		pyVariables.put("er.display", "erin");
		pyVariables.put("hi.display", "hillary");
		pyVariables.put("je.display", "jennifer");
		pyVariables.put("ji.display", "jillian");
		pyVariables.put("k.display", "karyn");
		pyVariables.put("ka.display", "kayla");
		pyVariables.put("ma.display", "martha");
		pyVariables.put("m.display", "mika");
		pyVariables.put("M.display", "main");
		pyVariables.put("mic.display", "michelle");
		pyVariables.put("Nel.display", "nelson");
		pyVariables.put("nur2.display", "nurse");
		pyVariables.put("Te.display", "teacher");
		pyVariables.put("v.display", "vanessa");
		personDispVars.put("alma", "Al.display");
		personDispVars.put("amber", "Am.display");
		personDispVars.put("candice", "Can.display");
		personDispVars.put("chris", "ch.display");
		personDispVars.put("donald", "Do.display");
		personDispVars.put("eliza", "e.display");
		personDispVars.put("erin", "er.display");
		personDispVars.put("hillary", "hi.display");
		personDispVars.put("jennifer", "je.display");
		personDispVars.put("jillian", "ji.display");
		personDispVars.put("karyn", "k.display");
		personDispVars.put("kayla", "ka.display");
		personDispVars.put("martha", "ma.display");
		personDispVars.put("mika", "m.display");
		personDispVars.put("main", "M.display");
		personDispVars.put("michelle", "mic.display");
		personDispVars.put("nelson", "Nel.display");
		personDispVars.put("nurse", "nur2.display");
		personDispVars.put("teacher", "Te.display");
		personDispVars.put("vanessa", "v.display");
	}

	public RenPyThread getThread() {
		synchronized (threads) {
			if (threads.size() > 0)
				return threads.remove(threads.size() -1);
			return null;
		}
		
	}
	
	public static void print(String s) {
		System.out.println(s);
	}

	// -----------------------------------------------------------------------------
	public static void printRed(String s) {
		print("\033[1;31m" + s + "\033[0m");
	}

	// -----------------------------------------------------------------------------
	public static void showError(String txt) {
		printRed("Error: " + txt);
	}

	// -----------------------------------------------------------------------------
	public String flagError(RenPyFile rpFile, int lineNum, String txt) {
		showError("Line " + lineNum + ": " + txt);
		if (inlineErrors) {
			return RenPyFile.trimStringByString(rpFile.lines.get(lineNum), "\n") + "  # ERROR: " + txt + "\n";
		}

		System.exit(1);
		return txt;
	}

	// -----------------------------------------------------------------------------
	public String md5(String fname) {
		final int byteRead = 4096;
		MessageDigest hash;
		StringBuilder hd = null;
		try (InputStream inputStream = new FileInputStream(fname)) {
			hash = MessageDigest.getInstance("MD5");

			byte[] chunk;
			while (inputStream.available() != 0) {
				chunk = inputStream.readNBytes(byteRead);
				hash.update(chunk);
			}
			BigInteger hash2 = new BigInteger(1, hash.digest());
			hd = new StringBuilder(hash2.toString(16)); // BigInteger strips leading 0's
			while (hd.length() < 32) {
				hd.insert(0, "0");
			} // pad with leading 0's
		} catch (NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assert hd != null;
		return hd.toString();

	}

	// -----------------------------------------------------------------------------
	public boolean verifySingleFile(String filename, String desiredHash) {
		print("Verifying " + filename + "...");
		File filePath = new File(Paths.get(filename).toString());
		if (!filePath.exists()) {
			showError("File does not exist!");
			return false;
		}
		String actualHash = md5(filename);
		if (!actualHash.equals(desiredHash)) {
			showError("Checksum is not correct, please download the file again");
			print("Desired MD5: " + desiredHash);
			print("Actual MD5 : " + actualHash);
			return false;
		}
		print("Succeeded");
		return true;
	}

	// -----------------------------------------------------------------------------
	public void unzipFile(String filename, boolean verbose) {
		print("Unzipping file " + filename + "...");
		try {
			UnzipFile.unzipfile(filename, verbose);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		print("Finished extracting " + filename);
	}

	// -----------------------------------------------------------------------------
	public void removeDir(String filename) {
		File checkDir = new File(filename);
		if (checkDir.isDirectory()) {
			print("Removing directory " + filename + "...");
			try {
				FileUtils.deleteDirectory(checkDir);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// -----------------------------------------------------------------------------
	public boolean checkForBadFile(String dirname, String checksum, boolean verbose) {
		File checkDir = new File(dirname);
		if (checkDir.isDirectory()) {
			print("Directory " + dirname + " exists, ZIP extract skipped");
			return false;
		}
		String filename = dirname + ".zip";
		if (!verifySingleFile(filename, checksum)) {
			return true;
		}
		unzipFile(filename, verbose);
		return false;
	}

	// -----------------------------------------------------------------------------
	public boolean isNumberField(String s) {
		for (char c : s.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	// -----------------------------------------------------------------------------
	public String expandNumberField(String s) {
		if (!isNumberField(s)) {
			return s;
		}
		return "0".repeat(3 - s.length()) + s;
	}

	// -----------------------------------------------------------------------------
	public int getIndentOf(String line) {
		int indent = 0;
		int lineLen = line.length();

		while ((indent < lineLen) && (line.charAt(indent) == ' ')) {
			indent = indent + 1;
		}
		return indent;
	}

	// -----------------------------------------------------------------------------
	public void processCommand(RenPyFile rpFile, RenPyThread thread, int lineNum, String line) {
		// Checking to see if command had more than 1 option on it
		String[] fields = line.split(" (?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))");

		if (fields.length < 2) {
			return;
		}

		// Try for a UI timer jump
		if (fields[0].startsWith("ui.timer(") && fields[1].startsWith("ui.jumps(")) {
			String jumpLabel = fields[1].split("\"")[1];
			addLabelCall(rpFile, jumpLabel, thread);
			return;
		}

		// Try for a variable assignment
		if (fields.length < 3) {
			return;
		}

		String pyVar = fields[0].strip();
		String pyVal = RenPyFile.trimStringByStringMulti(fields[2].strip(), "\"", "'").strip();
		switch (fields[1]) {
			case "=" -> thread.vars.put(pyVar, pyVal);

//	    print("Variable '" + pyVar + "' becomes '" + pyVal + "'");
			case "+=" -> {
				if (!thread.vars.containsKey(pyVar)) {
					flagError(rpFile, lineNum, "Variable '" + pyVar + "' not found in thread");
				}
				int ans = Integer.parseInt(thread.vars.get(pyVar)) + Integer.parseInt(pyVal);
				thread.vars.put(pyVar, String.valueOf(ans));
			}
			case "-=" -> {
				if (!thread.vars.containsKey(pyVar)) {
					flagError(rpFile, lineNum, "Variable '" + pyVar + "' not found in thread");
				}
				int ans = Integer.parseInt(thread.vars.get(pyVar)) - Integer.parseInt(pyVal);
				thread.vars.put(pyVar, String.valueOf(ans));
			}
			default -> flagError(rpFile, lineNum, "Unsupported operator '" + fields[1] + "', line is: " + line);
		}
	}

	// -----------------------------------------------------------------------------
	public boolean calculateCondition(RenPyThread thread, int lineNum, String[] fields) {
		int offset = 1;
		String varname, condition, value;
		boolean cont;
		while (offset < fields.length) {
			varname = fields[offset];
			condition = fields[offset + 1];
			value = fields[offset + 2];
			if (!thread.vars.containsKey(varname)) {
				return false;
			}
			if (condition.equals("==")) {
				cont = false;
				if (value.charAt(value.length() - 1) == ',') {
					cont = true;
					value = RenPyFile.trimStringByString(value, ",");
				}
				if (thread.vars.get(varname).equals(RenPyFile.trimStringByStringMulti(value, "\"", "'"))) {
					return true;
				}
				if (cont) {
					offset = offset + 1;
					value = fields[offset + 2];
					if (thread.vars.get(varname).equals(RenPyFile.trimStringByStringMulti(value, "\"", "'"))) {
						return true;
					}
				}
			} else if (condition.equals(">=")) {
				if (Integer.parseInt(thread.vars.get(varname)) >= Integer
						.parseInt(RenPyFile.trimStringByStringMulti(value, "\"", "'"))) {
					return true;
				}
			} else {
				showError("Condition " + condition + " not supported");
				System.exit(0);
			}
			offset = offset + 3;
			if ((offset < fields.length && !fields[offset].equals("or"))) {
				showError(lineNum + ": Boolean operator " + fields[offset] + " not supported, fields are ["
						+ String.join(",", fields) + "]");
				System.exit(1);
			}
			offset = offset + 1;
		}
		return false;
	}
	// -----------------------------------------------------------------------------

	public void processIfStep(RenPyFile rpFile, RenPyThread thread) {
		int end = thread.stack.size() - 1;
		RenPyObject obj = thread.stack.get(end);
		String line = rpFile.lines.get(obj.lineNum).split(":")[0];
		String[] fields = line.trim().split("\\s+");

		// Are we still in the block?
		if ((!rpFile.indentIsGood(obj.lineNum, obj.indent))) {
			thread.stack.remove(end); // Kill the IF
			return;
		}

		// Call the "if" hook to see if the file has special processing
		rpFile.hookIf(thread);

		if ((fields[0].equals("if")) || (fields[0].equals("elif"))) {
			boolean condition = calculateCondition(thread, obj.lineNum, fields);
			RenPyIf obj2 = (RenPyIf) obj;
			if (condition && !obj2.hasExecuted) {
				obj2.hasExecuted = true;
				thread.stack.add(new RenPyBlock(obj.lineNum + 1, obj.indent + 4));
			}

			obj.lineNum = rpFile.blockEndLine(obj.lineNum + 1, obj.indent + 4);
		} else if (fields[0].equals("else")) {
			RenPyIf obj2 = (RenPyIf) obj;
			if (!obj2.hasExecuted) {
				thread.stack.add(new RenPyBlock(obj.lineNum + 1, obj.indent + 4));
				obj2.lineNum = rpFile.blockEndLine(obj.lineNum + 1, obj.indent + 4);
				return;
			}
			thread.stack.remove(end);
		} else {// Must have finished the block
			thread.stack.remove(end);
		}
	}
	// -----------------------------------------------------------------------------

	public void processBlockStep(RenPyFile rpFile, RenPyThread thread) {
		int end = thread.stack.size() - 1;
		RenPyObject blk = thread.stack.get(end);
		int i = blk.lineNum;
		int indent = blk.indent;

		if (!(rpFile.indentIsGood(i, indent))) {
			thread.stack.remove(end);
			return;
		}

		String strippedLine = rpFile.lines.get(i).strip();
		if (strippedLine.startsWith("menu:")) {
			// Shift the block processor to the end of the menu, so that when the
			// thread gets cloned it resumes from the right place
			blk.lineNum = rpFile.blockEndLine(i + 1, indent + 4);
			processMenuStep(rpFile, thread, i);
			return;
		} else if (strippedLine.startsWith("return")) {
			thread.stack.clear(); // Kill the thread
			return;
		} else if (strippedLine.startsWith("if ")) {
			// Add an IF processor to the stack
			thread.stack.add(new RenPyIf(i, indent));
			i = rpFile.blockEndLine(i + 1, indent + 4);
		} else if (strippedLine.startsWith("elif ") || strippedLine.startsWith("else:")) {
			i = rpFile.blockEndLine(i + 1, indent + 4); // Flush it
		} else if (strippedLine.startsWith("label goopy")) {
			// We hit the goopy path, no need to process this
			thread.stack.clear(); // Kill the thread
			return;
		} else if (strippedLine.startsWith("hide")) {
			String person = strippedLine.trim().split("\\s+")[1];
			if (rpFile.trackVis && (!person.equals("bg"))) {
				thread.vars.put("_visible_" + person, "0");
			}
			i += 1;
		} else if (strippedLine.startsWith("jump")) {
			String label = strippedLine.trim().split("\\s+")[1];
			if (!rpFile.labelList.containsKey(label)) {
				print("External jump: " + label);
				thread.stack.clear(); // Kill this thread, it jumped out of the file
				return;
			}
			int jumpDest = rpFile.labelList.get(label);
			if (!(label.equals("kpathendroundup2") || label.startsWith("endingclone")) || jumpDest > blk.lineNum) {
				addLabelCall(rpFile, label, thread);
				thread.stack.clear();// Kill this thread, it jumped
				return;
			} else {
				i = i + 1;
			}
		} else if (strippedLine.startsWith("show") || strippedLine.startsWith("scene")) {
			if (rpFile.trackVis && strippedLine.startsWith("scene")) {
				for (String varName : thread.vars.keySet()) {
					if (varName.startsWith("_visible_")) {
						thread.vars.put(varName, "0");
					}
				}
			}
			synchronized (rpFile.lineModifiedFlags) {
				if (!rpFile.lineModifiedFlags.get(i)) {
					rpFile.lines.set(i, processShow(rpFile, thread, i));
					rpFile.lineModifiedFlags.put(i, true);
				}
			}

			i = i + 1;
		} else if (strippedLine.startsWith("$")) {
			processCommand(rpFile, thread, i, RenPyFile.trimStringByString(strippedLine, "$").strip());
			i = i + 1;
		} else {
			i = i + 1;
		}

		blk.lineNum = i;
	}
	// -----------------------------------------------------------------------------
	// On entry, lineNum points to the menu: line

	public void processMenuStep(RenPyFile rpFile, RenPyThread thread, int lineNum) {

		int indent = getIndentOf(rpFile.lines.get(lineNum)) + 4;
		lineNum = lineNum + 1;

		// Iterate the whole menu and fork threads from the current one for each
		// menu option
		String line = rpFile.lines.get(lineNum);

		while ((lineNum < rpFile.numLines) && rpFile.indentIsGood(lineNum, indent)) {
			if (getIndentOf(line) == indent) {
				String menuItem = RenPyFile.trimStringByStringMulti(line.strip(), "\n", "\r").strip();
				if (!((menuItem.charAt(0) == '#') || menuItem.startsWith("\"{s}"))) {
					int endQuote = menuItem.indexOf("\"", 1);
					String condition = ":";
					if (endQuote > 0) {
						condition = menuItem.substring(endQuote + 1).strip();
					}
					boolean res = true;
					if (!(condition.equals(":"))) {
						// Menu has a condition on it
						condition = RenPyFile.trimStringByString(condition, ":");
						res = calculateCondition(thread, lineNum, condition.trim().split("\\s+"));
					}
					if (res) {

						RenPyThread newThread = thread.clone();
						newThread.stack.add(new RenPyBlock(lineNum + 1, indent + 4));
						threads.add(newThread);
					}

					lineNum = rpFile.blockEndLine(lineNum + 1, indent + 4);
				} else {
					lineNum = lineNum + 1;
				}
			} else {
				lineNum = lineNum + 1;
			}

			line = rpFile.lines.get(lineNum);
		}
		// Kill the current thread. Because it's been used as the parent thread for
		// all the menu options, it's not needed any more as each menu option will
		// continue from here.
		thread.stack.clear();
	}
	// -----------------------------------------------------------------------------

	public String processShow(RenPyFile rpFile, RenPyThread thread, int lineNum) {
		String line = rpFile.lines.get(lineNum);
		String[] fields = RenPyFile.trimStringByString(line.strip(), ":").strip().trim().split("\\s+");

		// At this point, 'fields' looks like this:
		// ['show', 'maind', '17', 'with', 'dissolve']

		// Check for backgrounds
		boolean b = line.strip().charAt(line.strip().length() - 1) == ':';
		if (fields[1].equals("bg")) {
			if (fields.length < 3) {
				return line;
			}
			if (!rpFile.backMap.containsKey(fields[2])) {
				// return flagError(rpFile, lineNum, "Background " + fields[2] + " has no
				// mapping table entry");
				return line;
			}

			StringBuilder newLine = new StringBuilder();
			int indent = 0;
			while (line.charAt(indent) == ' ') {
				newLine.append(" ");
				indent = indent + 1;
			}
			String newbg = rpFile.backMap.get(fields[2]);
			if (newbg.equals("")) {
				return flagError(rpFile, lineNum, "Background '" + fields[2] + "' exists but has no mapping");
			}

			newLine.append(fields[0]).append(" bg ").append(newbg);

			int i = 3;
			while (i < fields.length) {
				newLine.append(" ").append(fields[i]);
				i = i + 1;
			}
			if (b) {
				newLine.append(":");
			}
			newLine.append("\n");
			return newLine.toString();
		}
		// Check for 0.3 style "show cg" statements
		if (fields[0].equals("show") && fields[1].equals("cg")) {
			return rpFile.processCG(line);
		}

		// Try for a character
		// Character label is fields[1], get character name
		if (!fields[0].equals("show")) {
			return line;
		}
		if (!characterLabelMap.containsKey(fields[1])) {
			return line;
		}
		if (!rpFile.showLines.contains(lineNum)) {
			rpFile.showLines.add(lineNum);
		}
		if (rpFile.trackVis) {
			String varName = "_visible_" + fields[1];
			if (!thread.vars.containsKey(varName) || thread.vars.get(varName).equals("0")) {
				// Person has become visible
				if (rpFile.charFlip.contains(fields[1]) && !rpFile.visLines.contains(lineNum)) {
					rpFile.visLines.add(lineNum);
				}
			}
			thread.vars.put(varName, "1");
		}
		// If it's got no parameters, like "show michelled:", then just return it
		// as there's no mapping to do
		if (fields.length < 3) {
			return line;
		}

		String charName = characterLabelMap.get(fields[1]);
		String swappedCharName = charName;
		if (characterDoRemap.getOrDefault(fields[1], false)) {
			// Character is not a ghost, do the remap
			if (personDispVars.containsKey(charName)) {
				swappedCharName = thread.vars.get(personDispVars.get(charName));
			}
			String[] swappedFields = swappedCharName.trim().split("\\s+");
			swappedCharName = swappedFields[0];
		}

		// i = 1;
		// while i < len(swappedFields):

		boolean filenameMode = true;
		boolean baseMode = true;
		StringBuilder exFile = new StringBuilder(swappedCharName + "_ex");
		StringBuilder modifiers = new StringBuilder();
		StringBuilder base = new StringBuilder();
		int i = 2;
		ArrayList<String> placement = new ArrayList<>(
				Arrays.asList("as", "at", "behind", "with", "zorder"));
		while (i < fields.length) {
			if (placement.contains(fields[i])) {
				filenameMode = false;
			}
			if (filenameMode) {
				String field = expandNumberField(fields[i]);
				if (field.equals("full")) {
					exFile.append("_full");
				} else if (isNumberField(field)) {
					baseMode = false;
				}
				if (baseMode) {
					if (!(field.equals("full")) && !((charName.equals("hillary")) && (fields[i].equals("school")))) {
						base.append(" ").append(fields[i]);
					}
				} else {
					exFile.append("_").append(field);
				}
			} else {
				modifiers.append(" ").append(fields[i]);
			}
			i = i + 1;
		}
		if (exFile.toString().equals(swappedCharName + "_ex")) {
			// It's something like "show candice with dissolve", with no fields so nothing
			// to do
			return line;
		}

		String mappedFile = "";
		boolean hasMapped = false;
		if (swappedCharName != null && rpFile.charMap.containsKey(swappedCharName)) {
			if (rpFile.charMap.get(swappedCharName).containsKey(exFile.toString())) {
				mappedFile = rpFile.charMap.get(swappedCharName).get(exFile.toString());
				hasMapped = true;
			} else if (rpFile.charMap.get(swappedCharName).containsKey(exFile + "_001")) {
				mappedFile = rpFile.charMap.get(swappedCharName).get(exFile + "_001");
				hasMapped = true;
			} else if (rpFile.charMap.get(swappedCharName).containsKey(exFile + "_002")) {
				mappedFile = rpFile.charMap.get(swappedCharName).get(exFile + "_002");
				hasMapped = true;
			} else if (rpFile.charMap.get(swappedCharName).containsKey(exFile + "_003")) {
				mappedFile = rpFile.charMap.get(swappedCharName).get(exFile + "_003");
				hasMapped = true;
			}
		} else {
			// We're not doing a V3 or V4 mapping for this character, fake that we've done
			// one
			mappedFile = exFile.toString();
			hasMapped = true;
		}

		if (!hasMapped) {
			// The .rpy file is referencing a graphic that doesn't seem to exist in the 0.4
			// graphics directory.
			print("DBG: Vars are: " + thread.vars.toString());
			return (flagError(rpFile, lineNum, "Mapping failed, source file '" + exFile
					+ "' not found. Line being processed is: " + String.join(",", fields)));
		}
		if (mappedFile.equals("")) {
			return (flagError(rpFile, lineNum, "Mapping failed, source file '" + exFile
					+ "' exists but has no mapping. Line being processed is: " + String.join(",", fields)));
		}
		// Map V6 if present
		if (swappedCharName != null && rpFile.v6Map.containsKey(swappedCharName)) {
			hasMapped = false;
			String v6File = "";
			if (rpFile.v6Map.get(swappedCharName).containsKey(mappedFile)) {
				v6File = rpFile.v6Map.get(swappedCharName).get(mappedFile);
				hasMapped = true;
			} else if (rpFile.v6Map.get(swappedCharName).containsKey(mappedFile + "_001")) {
				v6File = rpFile.v6Map.get(swappedCharName).get(mappedFile + "_001");
				hasMapped = true;
			} else if (rpFile.v6Map.get(swappedCharName).containsKey(mappedFile + "_002")) {
				v6File = rpFile.v6Map.get(swappedCharName).get(mappedFile + "_002");
				hasMapped = true;
			} else if (rpFile.v6Map.get(swappedCharName).containsKey(mappedFile + "_003")) {
				v6File = rpFile.v6Map.get(swappedCharName).get(mappedFile + "_003");
				hasMapped = true;
			}

			if (!hasMapped) {
				return (flagError(rpFile, lineNum, "No V6 mapping for V5 file '" + mappedFile + "', source file '"
						+ exFile + "', char name " + swappedCharName + ", original char " + charName));
			}
			// print("Mapped V5 " + mappedFile + " to V6 " + v6File);
			mappedFile = v6File;
		}
		String[] mappedFields = mappedFile.split("_");

		if (mappedFields.length < 2) {
			return (flagError(rpFile, lineNum,
					"Invalid mapping! Source is '" + exFile + "', map is '" + mappedFile + "'"));
		}
		if (swappedCharName != null && !mappedFields[0].equals(swappedCharName)) {
			return (flagError(rpFile, lineNum,
					"Mapped to a different character! Source is '" + exFile + "', map is '" + mappedFile + "'"));
		}
		if (!mappedFields[1].equals("ex")) {
			return (flagError(rpFile, lineNum, "Mapping is not to an expression graphic! Source is '" + exFile
					+ "', map is '" + mappedFile + "'"));
		}
		StringBuilder newLine = new StringBuilder();
		int indent = 0;
		while (line.charAt(indent) == ' ') {
			newLine.append(" ");
			indent = indent + 1;
		}

		newLine.append("show ").append(fields[1]).append(base);

		i = 2;
		while (i < mappedFields.length - 1) {
			if (isNumberField(mappedFields[i])) {
				newLine.append(" ").append(Integer.parseInt(mappedFields[i]));
			} else {
				newLine.append(" ").append(mappedFields[i]);
			}
			i = i + 1;
		}
		newLine.append(modifiers);
		if (b) {
			newLine.append(":");
		}

		newLine.append("\n");
		return newLine.toString();
	}
	// -----------------------------------------------------------------------------

	public void processNextThread(RenPyFile rpFile, RenPyThread thread) {
		while (thread != null && thread.stack.size() > 0) {
			RenPyObject obj = thread.stack.get(thread.stack.size() - 1);
			if (obj.objType.equals("Block")) {
				processBlockStep(rpFile, thread);
			} else if (obj.objType.equals("If")) {
				processIfStep(rpFile, thread);
			} else {
				print("Unhandled object type: " + obj.objType);
				System.exit(1);
			}
		}
	}

	// Culprit for depth 7 on is not in below this
	@SuppressWarnings("unchecked")
	public void addLabelCall(RenPyFile rpFile, String l, RenPyThread thread) {
		if (!rpFile.labelIsAcceptable(l)) {
			return;
		}
		HashMap<String, String> k = (HashMap<String, String>) thread.vars.clone();
		labelCalls.add(new RenPyLabelCall(l, k));
	}

//	//-----------------------------------------------------------------------------
	public void processLabelCall(RenPyFile rpFile, String l, HashMap<String, String> v) {

		int lineNum = rpFile.labelList.get(l) + 1;
		String line = rpFile.lines.get(lineNum);
		int indent = getIndentOf(line);

		RenPyBlock blk = new RenPyBlock(lineNum, indent);
		ArrayList<RenPyObject> b = new ArrayList<>();
		b.add(blk);
		RenPyThread thread = new RenPyThread(v, b);
		threads.add(thread);
	}

	// -----------------------------------------------------------------------------
	public void iterateLabelCalls(RenPyFile rpFile) {
		int iterations = 1;
		int duplicates = 0;
		int numThreads = 0;
		final int max_threads = Runtime.getRuntime().availableProcessors()/2;
		while (labelCalls.size() > 0 || (threads.size() > 0)) {
			print("---------- Depth " + iterations + " ----------");
			print("Label calls: " + labelCalls.size());
			// Process label calls
			while (labelCalls.size() > 0) {
				RenPyLabelCall labelCall = labelCalls.remove(labelCalls.size() - 1);
				if (!labelCalls.contains(labelCall)) { //Turns out if your write the equals correctly it works
					processLabelCall(rpFile, labelCall.label, labelCall.vars);
				} else {
					print("Ignoring duplicate call");
					duplicates += 1;
				}
			}
			// Process threads
			print("Paths: " + threads.size());
			ThreadGroup t = new ThreadGroup("Group 1");
			while (threads.size() > 0) {
				Thread t1 = new Thread(t, () -> processNextThread(rpFile, getThread()));
				t1.start();
				numThreads += 1;
				if ((threads.size() % 10) == 0) {
					print("[Depth " + iterations + "] Paths: " + duplicates + " dupe, " + numThreads + " total, "
							+ threads.size() + " left this depth");
				}
				while(t.activeCount() >= max_threads) {try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
			}
			while(t.activeCount() != 0) {}
			iterations += 1;
		}
	}

	// -----------------------------------------------------------------------------
	// Main program
	public press_stitch() {
		fillvars();
		backgrounds_map.fill_backgrounds_map();
		characterImages.fill_characterImages();
	}

	@SuppressWarnings("unchecked")
	public void main(String[] args) {
		long startTime = System.currentTimeMillis();
//		press_stitch_archive.extractRPATool(); // Decouple
		boolean doClean = false;
		boolean doEliza = true;
		boolean doCiel = true;
		boolean doGoopy = true;
		boolean doScan = true;
		boolean doV6 = false;
		boolean verbose = false;
		boolean threadClean = false;

		if (args.length > 0) {
			for (String arg : args) {
				switch (arg) {
					case "--clean" -> doClean = true;
					case "--inlineerrors" -> inlineErrors = true;
					case "--nociel" -> doCiel = false;
					case "--noeliza" -> doEliza = false;
					case "--nogoopy" -> doGoopy = false;
					case "--noscan" -> doScan = false;
					case "--verbose", "-v" -> verbose = true;
					case "--threadclean" -> threadClean = true;
					case "--v6" -> {
						doV6 = true;
						doCiel = false; // Cielpath disabled for 0.6
					}
					default -> {
						showError("Usage is: press-stitch.py [--clean] [--verbose|-v]");
						System.exit(1);
					}
				}
			}
		}
		if (doClean) {
			removeDir(filename_03);
			removeDir(filename_04);
			removeDir(filename_05);
			removeDir("Extracted");
			System.exit(0);
		}
		if (threadClean) {
			removeDir(Paths.get(filename_05, "Game", "Story").toString());
			new File(Paths.get(filename_05, "Game", "Story").toString()).mkdirs();
		}
		
		// Normal run
		boolean have3 = false;
		boolean have4 = false;
		if (new File(filename_03 + ".zip").exists()) {
			if (checkForBadFile(filename_03, "e01bfc54520e8251bc73c7ee128836e2", verbose)) {
				System.exit(1);
			}
			have3 = true;
			press_stitch_archive.unpackArchive(filename_03);
		}
		if (new File(filename_04 + ".zip").exists()) {
			if (checkForBadFile(filename_03, "ca7ee44f40f802009a6d49659c8a760d", verbose)) {
				System.exit(1);
			}
			have4 = true;
			press_stitch_archive.unpackArchive(filename_04);
		}
		if (checkForBadFile(filename_05, "6a4f9dac386e2fae1bce00e0157ee8b1", verbose)) {
			System.exit(1);
		}
		if (have4 || have3) {
			System.out.println("Has 4? " + have4);
			System.out.println("Has 3? " + have3);
			System.out.println("Currently these do nothing");
		}
		press_stitch_archive.unpackArchive(filename_05);
		String extPath5 = Paths.get("Extracted", filename_05).toString();
		String dstPath = Paths.get(filename_05, "game").toString();
		HashMap<String, HashMap<String, String>> v6map = new HashMap<>();

		if (doV6) {
			v6map = characterImages.characterImageMap56;
			dstPath = Paths.get(filename_06, "game").toString();
		}
		// Day-0.rpy
		print("Patching Day-0.rpy...");
		RenPyFile dayzero = new RenPyFile();
		dayzero.readFile(Paths.get(extPath5, "Story", "Day-0.rpy").toString());
		dayzero.lines.add(2848, " ".repeat(28) + "\"Maybe I was too quick to reject Eliza...\":\n");
		dayzero.lines.add(2849, " ".repeat(32) + "jump eliza\n");
		if (doCiel) {
			dayzero.lines.set(2851, " ".repeat(20) + "\"Hide it until tomorrow.\":\n");
			dayzero.lines.set(2863, " ".repeat(20) + "\"Leave it on my desk and sleep.\":\n");
			dayzero.lines.set(2864, " ".repeat(24) + "jump leftit\n");
		}
		if (doV6) {
			dayzero.v6Map = v6map;
			dayzero.findLabels();
			dayzero.findShows();
			addLabelCall(dayzero, "GameStart",
					new RenPyThread((HashMap<String, String>) pyVariables.clone(), new ArrayList<>()));
			iterateLabelCalls(dayzero);
		}

		dayzero.writeFile(Paths.get(dstPath, "Story", "Day-0.rpy").toString());

		if (doCiel) {
			// Read Cielpath.rpy into memory
			print("Patching Cielpath.rpy...");
			RenPyFile cielPath = new RenPyFile("C", backgrounds_map.backgroundMap35,
					characterImages.characterImageMap35, v6map);
			cielPath.readFile(Paths.get(extPath5, "Story", "Cielpath.rpy").toString());
			// Search for labels
			cielPath.findLabels();

			// Search for "show" statements
			cielPath.findShows();

			// Process the 'leftit' label, it's the toplevel.
			addLabelCall(cielPath, "leftit",
					new RenPyThread(new HashMap<>(), new ArrayList<>()));
			iterateLabelCalls(cielPath);

			// Flip the affected V3 characters
			cielPath.doFlips();

			// Write the updated Cielpath.rpy back out
			cielPath.writeFile(Paths.get(dstPath, "Story", "Cielpath.rpy").toString());
		}

		if (doEliza) {
			// Read ElizaPath.rpy into memory
			print("Patching ElizaPath.rpy... (0.4 content)");
			RenPyFile elizaPath = new RenPyFile("E", backgrounds_map.backgroundMap45,
					characterImages.characterImageMap45, v6map);
			elizaPath.readFile(Paths.get(extPath5, "Story", "ElizaPath.rpy").toString());

			// Search for labels
			elizaPath.findLabels();

			// Search for "show" statements
			elizaPath.findShows();

			if (doScan) {
				// Process the 'eliza' label, it's the toplevel.
				// We need two calls, one for the timer < 34 and one for > 30
				pyVariables.put("timer_value", "0"); // Less than 30
				addLabelCall(elizaPath, "eliza", new RenPyThread((HashMap<String, String>) pyVariables.clone(),
						new ArrayList<>()));
				pyVariables.put("timer_value", "60"); // Greater than 30
				addLabelCall(elizaPath, "eliza", new RenPyThread((HashMap<String, String>) pyVariables.clone(),
						new ArrayList<>()));
				iterateLabelCalls(elizaPath);
			}
			// Write the updated ElizaPath.rpy back out
			elizaPath.writeFile(Paths.get(dstPath, "Story", "ElizaPath.rpy").toString());
		}
		if (doGoopy) {
			// By default we're processing the file we just made for the 0.4 Eliza
			// content...
			String srcFile = Paths.get(dstPath, "Story", "ElizaPath.rpy").toString();
			if (!doEliza) {
				// ...but if we've not done Eliza this time, we'll take a saved copy
				srcFile = "ElizaPath-NoGoopy.rpy";
			}
			// Read ElizaPath.rpy into memory. GoopyPath is ElizaPath but with v0.3 mappings
			print("Patching ElizaPath.rpy... (Goopy path)");
			RenPyFile goopyPath = new RenPyFile("G", backgrounds_map.backgroundMap35,
					characterImages.characterImageMap35, v6map);
			goopyPath.readFile(srcFile);

			// Search for labels
			goopyPath.findLabels();

			// Search for "show" statements
			goopyPath.findShows();

			// Process the path
			addLabelCall(goopyPath, "elizagoopypath",
					new RenPyThread(new HashMap<>(), new ArrayList<>()));
			iterateLabelCalls(goopyPath);

			// Flip the affected V3 characters
			// goopyPath.doFlips();

			// Write the updated ElizaPath.rpy back out
			goopyPath.writeFile(Paths.get(dstPath, "Story", "ElizaPath.rpy").toString());
		}
		// Read effects.rpy into memory
		print("Patching effects.rpy...");
		RenPyFile effectsFile = new RenPyFile();
		effectsFile.readFile(Paths.get(extPath5, "effects.rpy").toString());

		// Patch the timer
		effectsFile.lines.set(492, "default timer_value = 0\n");
		effectsFile.lines.set(495, "    timer 1 repeat True action SetVariable(\"timer_value\", timer_value + 1)\n");

		// Write the updated effects.rpy back out
		effectsFile.writeFile(Paths.get(dstPath, "effects.rpy").toString());

		// -----------------------------------------------------------------------------
		// Hook to call main
		System.out.println((System.currentTimeMillis() - startTime)/1000.00);

		System.out.print("Completed Stitching, Press any key to exit: ");
		try {
			System.in.readNBytes(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
