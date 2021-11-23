package press;
import java.util.LinkedHashMap;

public class RenPyLabelCall {
	String label;
	LinkedHashMap<String, String> vars;
	public RenPyLabelCall(
			String l,
			LinkedHashMap<String, String> v			
			) {
		label = l;
		vars = v;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return label + ", " + vars.toString();
	}
	public boolean equals(RenPyLabelCall o) {
		return (this.vars.equals(o.vars) && this.label.equals(o.label));
	}
}
