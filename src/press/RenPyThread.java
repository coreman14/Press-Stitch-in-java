package press;

import java.util.ArrayList;
import java.util.LinkedHashMap;



public class RenPyThread {
	public LinkedHashMap<String, String> vars;
	public ArrayList<RenPyObject> stack;
	public RenPyThread(LinkedHashMap<String, String> v, ArrayList<RenPyObject> s) {
		vars = v;
		stack = s;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof RenPyThread o)) {
			return false;
		}
		return (this.vars.equals(o.vars) && this.stack.equals(o.stack));
	}
	public RenPyThread clone() {
		ArrayList<RenPyObject> clonestack = new ArrayList<>();
		LinkedHashMap<String, String> clonevars = new LinkedHashMap<>(vars);
		for (RenPyObject s: stack) {
			clonestack.add(s.clone());
				
		}
		return new RenPyThread(clonevars, clonestack);
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Thread:\nStack: " + stack.toString() +" \nVars: " +vars.toString();
	}
	
	
}
