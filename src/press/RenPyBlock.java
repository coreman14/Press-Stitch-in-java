package press;

public class RenPyBlock extends RenPyObject{

	public RenPyBlock(int ln, int ind) {
		super(ln, ind);
		this.objType = "Block";
	}
	@Override
	public String toString() {
		return "blk(" + lineNum + "," + indent + ")";
	}

}
