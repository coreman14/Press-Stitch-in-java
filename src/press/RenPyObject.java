package press;

public class RenPyObject{
	public String objType;
	public boolean done;
	public int lineNum;
	public int indent;
	public RenPyObject(int ln, int ind) {
		lineNum = ln;
		indent = ind;
		objType = "Object";
		done = false;
	}	
    public RenPyObject clone()
    {
		RenPyObject s3 = new RenPyObject(lineNum, indent);
		s3.done = done;
		s3.objType = objType;
		return s3;
    }
}
