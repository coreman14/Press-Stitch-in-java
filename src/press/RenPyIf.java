package press;

public class RenPyIf extends RenPyObject{
	public boolean hasExecuted;
	public RenPyIf(int ln, int ind) {
		super(ln, ind);
		this.objType = "If";
		hasExecuted = false;
	}
	@Override
	public String toString() {
		return "if(" + lineNum + "," + indent + ")";
	}
	@Override
	public RenPyObject clone() {
		// TODO Auto-generated method stub
		RenPyIf s3 = new RenPyIf(lineNum, indent);
		s3.done = done;
		s3.hasExecuted = hasExecuted;
		s3.objType = objType;
		return s3;
	}
}
