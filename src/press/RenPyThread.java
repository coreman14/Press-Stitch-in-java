package press;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;



public class RenPyThread {
	public LinkedHashMap<String, String> vars;
	public ArrayList<RenPyObject> stack;
	public RenPyThread(LinkedHashMap<String, String> v, ArrayList<RenPyObject> s) {
		vars = v;
		stack = s;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof RenPyThread)) {
			return false;
		}
		RenPyThread o = (RenPyThread) obj;
		return (this.vars.equals(o.vars) && this.stack.equals(o.stack));
	}
	public RenPyThread clone() {
		LinkedHashMap<String, String> clonevars = new LinkedHashMap<String, String> ();
		ArrayList<RenPyObject> clonestack = new ArrayList<RenPyObject>();
		for(Entry<String, String> k: vars.entrySet()) {
			clonevars.put(k.getKey(), k.getValue());
		}
		for (RenPyObject s: stack) {
			clonestack.add(s.clone());
				
		}
		RenPyThread clone = new RenPyThread(clonevars, clonestack);
		return clone;
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Thread:\nStack: " + stack.toString() +" \nVars: " +vars.toString();
	}
	
	
}
