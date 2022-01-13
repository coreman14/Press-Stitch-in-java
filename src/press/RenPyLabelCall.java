package press;
import java.util.HashMap;

public class RenPyLabelCall {
	String label;
	HashMap<String, String> vars;
	public RenPyLabelCall(
			String l,
			HashMap<String, String> v			
			) {
		label = l;
		vars = v;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return label + ", " + vars.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof RenPyLabelCall o)) {
			return false;
		}
		return (this.vars.equals(o.vars) && this.label.equals(o.label));
	}
}
