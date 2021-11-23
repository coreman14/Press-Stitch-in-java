package press;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;

public class RenPyFile {
	public static String[] trimStringByStringArray(String text) {
		return trimStringByString(text, ":").split("\\W");
	}
	public static String[] trimStringByStringArray(String text, String trimBy) {
		return trimStringByString(text, trimBy).split("\\W");
	}
	public static String trimStringByStringMulti(String text, String... trimBy) {
		ArrayList<String> trims = new ArrayList<String>(Arrays.asList(trimBy));
		for (String x: trims) {
			text = trimStringByString(text, x);
		}
		return text.strip();
	}
	
	public static String trimStringByString(String text) {
		return trimStringByString(text, ":");
	}
	public static String trimStringByString(String text, String trimBy) {//helper function to replace strip
	    int beginIndex = 0;
	    int endIndex = text.length();

	    while (text.substring(beginIndex, endIndex).startsWith(trimBy)) {
	        beginIndex += trimBy.length();
	    } 

	    while (text.substring(beginIndex, endIndex).endsWith(trimBy)) {
	        endIndex -= trimBy.length();
	    }

	    return text.substring(beginIndex, endIndex).strip();
	}
	
	public String type;
	public ArrayList<String> lines;
	public int numLines  = 0;
	public LinkedHashMap<String, Integer> labelList;
	public LinkedHashMap<String, String> backMap;
	public LinkedHashMap<String, LinkedHashMap<String, String>> charMap;
	public LinkedHashMap<String, LinkedHashMap<String, String>> v6Map;
	public ArrayList<String> charFlip;
	public ArrayList<Integer> visLines;
	public ArrayList<Integer> showLines;
	public boolean trackVis;
	public LinkedHashMap<Integer, Boolean> lineModifiedFlags;
	public RenPyFile() {
	    lines = new ArrayList<String>();
	    numLines  = 0;
	    labelList = new LinkedHashMap<String, Integer>();
	    backMap   = new LinkedHashMap<String, String>();
	    charMap   = new LinkedHashMap<String, LinkedHashMap<String, String>>();
	    v6Map     = new LinkedHashMap<String, LinkedHashMap<String, String>>();
	    charFlip  = new ArrayList<String>();
	    visLines  = new ArrayList<Integer>();
	    showLines = new ArrayList<Integer>();
	    trackVis  = false;
	    lineModifiedFlags = new LinkedHashMap<Integer, Boolean>();
	    type = "Z";	
	    }

	public RenPyFile( String type,
			LinkedHashMap<String, String> b,
			LinkedHashMap<String, LinkedHashMap<String, String>> c,
			LinkedHashMap<String, LinkedHashMap<String, String>> v6
			) {
		this();
		if (type.equals("C") || type.equals("G")) {
			this.type = type;
			charFlip.add("main");
			charFlip.add("mother");
			charFlip.add("nick");
			trackVis = true;
		}
		else if (type.equals("E")) {
			this.type = "E";
		}
		backMap = b;
		charMap = c;
		v6Map = v6;

	}
	  public void readFile(String fn) {
		String input = "";
		try (BufferedReader text_file = new BufferedReader(new FileReader(fn, StandardCharsets.UTF_8))){
			while((input = text_file.readLine()) != null) {
				lines.add(input+"\n");
			}
			text_file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		numLines = lines.size();
		additionalReadLine();
	  }
	  public void writeFile(String fn) {
			try (BufferedWriter text_file = new BufferedWriter(new FileWriter(fn, StandardCharsets.UTF_8));){
				for(String input:lines) {
					if (input.contains("\n")) {
						text_file.write(input);
					}
					else {
						text_file.write(input+"\n");
					}
					
				}
				text_file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	  }
	  public void findLabels() {
	    int i = 0;
	    String strippedLine = "";
	    while( i < numLines) {
	    	strippedLine = lines.get(i).strip();
	    	if (strippedLine.startsWith("label")){
	    		String[] fields = trimStringByStringArray(strippedLine);
	        	if (fields[0].equals("label")) {
	        		String label = fields[1];
	        		labelList.put(label, i);
	        	}
	    	}
    		i = i + 1;
	  	}
	  }
	  public boolean indentIsGood(int lineNum,int indent) {
	    int i = 0;
	    String line = lines.get(lineNum);
	    int lineLen = line.length();
	    while(i < lineLen) {
	    	if (i == indent) {return true;}
	      	if ((line.charAt(i) != ' ') && (line.charAt(i) != '\r') && (line.charAt(i) != '\n')) {return false;}
	      	i = i + 1;
	    }
	    //Line is shorter than indent with no characters, this is fine
	    return true;
	  }
	  public int blockEndLine(int lineNum, int indent) {
		  int i = lineNum;
		  while(i < numLines) {
			  if (!indentIsGood(i, indent)) {return i;}
			  i = i + 1;
		  }
	    return i;
	  }
	  public int getIndentOf(String line) {
	    int indent = 0;
	    int lineLen = line.length();

	    while((indent < lineLen) && (line.charAt(indent) == ' ')) {indent = indent + 1;}
	    return indent;
	  }
	  // Ensures a 'show' line has an 'xzoom' instruction
	  // Existing xzoom isn't changed, missing gets xzoom 1
	  public void addXZoom(int lineNum) {
	    String line = lines.get(lineNum);
	    String[] fields = trimStringByStringArray(line.strip());
	    if (fields[1].equals("bg")) {return;}
	    
	    if (!charFlip.contains(fields[1])) {return;}

	    int indent = getIndentOf(line) + 4;
	    if (line.strip().charAt(line.strip().length()-1) != ':') {
	    	lines.set(lineNum, trimStringByStringMulti(line, "\n", "\r") + ":\n");
		    lines.add(lineNum + 1, " ".repeat(indent) + "xzoom 1\n");
		    return;
	    }
	    //The character has following lines
	    lineNum += 1;
	    int insertLineNum = lineNum;
	    line = lines.get(lineNum);
	    while ((lineNum <numLines) && (getIndentOf(line) == indent)) {
		    if (line.strip().startsWith("xzoom ")) {return;}
		    lineNum += 1;
		    line = lines.get(lineNum);
	    }

	    // Need to insert
	    lines.add(insertLineNum, " ".repeat(indent) + "xzoom 1\n");
	  }
	  // Flip all V3 affected characters left-to-right
	  public void doFlips() {
		  
		Collections.sort(visLines, Collections.reverseOrder());
	    for (int lineNum: visLines) {
	        addXZoom(lineNum);
	    }
	    numLines = lines.size();

	    // Reverse all xzoom calls for affected characters
	    
	    for (int i: showLines) {
	    	String strippedLine = lines.get(i).strip();
	    	if (strippedLine.startsWith("show")) {reverseXZoom(i);}
	    	i = i + 1;
	    }
	  }
	  // Reverses an xzoom line in a 'show' statement
	  public void reverseXZoom(int lineNum) {
	    String line = lines.get(lineNum);
	    String[] fields = trimStringByStringArray(line.strip());
	    if (fields[1].equals("bg")) {return;}
	    if (!charFlip.contains(fields[1])) {return;}
	    if (line.strip().charAt(line.strip().length()-1) != ':') {return;}

	    // The character has following lines
	    int indent = getIndentOf(line) + 4;
	    lineNum += 1;
	    line = lines.get(lineNum);
	    while ((lineNum < numLines) && (getIndentOf(line) == indent)) {
	    	if (line.strip().startsWith("xzoom -1")) {
		        lines.set(lineNum," ".repeat(indent) + "xzoom 1\n");
		        return;
	    	}
		    if (line.strip().startsWith("xzoom 1")) {

		    	lines.set(lineNum," ".repeat(indent) + "xzoom -1\n");
		        return;
		    }
	      lineNum += 1;
	      line = lines.get(lineNum);
	    }
	  }
	  public void findShows() {
	    int i = 0;
	    lineModifiedFlags.clear();
	    while (i < numLines) {
	    	String strippedLine = lines.get(i).strip();
	    	if (strippedLine.startsWith("show") || strippedLine.startsWith("scene")) {
	    		lineModifiedFlags.put(i, false);
	    	}
	    	i = i + 1;
	    }
	  }
	  public boolean labelIsAcceptable(String label) {
		  if (type.equals("E")) {
			  return !label.contains("goopy");
		  }
		  return true;} // Place holders for inheritences

	  public void hookIf(RenPyThread thread) {
		  if (type.equals("E")) {
				RenPyObject obj = thread.stack.get(thread.stack.size()-1);
				if (obj.lineNum == 10181) {
					thread.vars.put("JokeEnding", "5");
				}			  
		  }
	  }

	  public String processCG(String line) {
		  if (type.equals("G")) {
			    String[] fields = trimStringByStringArray(line.strip());
			    fields[1] = "cgex";
			    String rv = " ".repeat(getIndentOf(line)) + String.join(" ",fields);
			    if (line.contains(":")){rv += ":";}
			    return rv + "\n";
		  }
		  
		  return line;} // Place holders for inheritences
	  
	   private void additionalReadLine(){
			if (type.equals("C")) {
				lines.set(5, "    hide maind\n");
			}
			else if (type.equals("G")) {
				// Patch the menu to enable the Goopy path
				lines.set(85, "        \"Tried to become a clone of her.\":\n");
				lines.set(86, "\n");
				lines.set(87, "\n");
				lines.set(89, "\n");

				// Make sure 'maind' displayable is hidden on entry
				lines.add(69381, "        hide maind\n");
				numLines = lines.size();

				// Alter the Eliza bed CG
				lines.set(69698, "                scene cgbase eliza sleep 1\n");
				lines.set(69699, "                show cgex eliza sleep 1\n");

				// The Eliza H pic is missing in 0.5, patch it out
				lines.set(69774, "                        show cgex eliza sleep 6\n");
				lines.set(69775, "\n");			
			}
			else if (type.equals("E")) {
				lines.set(6396, " ".repeat(20) + "if timer_value >= 30:\n");
				lines.set(6552, " ".repeat(20) + "if timer_value >= 30:\n");
				lines.set(6713, " ".repeat(20) + "if timer_value >= 30:\n");
				//Patch the "Karyn" if switched menu option in kpathendroundup
				lines.set(68980, "            jump kpathendroundup2\n");

				// Patch Michelle's age
				lines.set(587, lines.get(587).replace("14", "15"));
				lines.set(589, lines.get(589).replace("14", "15"));
			}
		  }
	   
}

