package newLang4.Nodes;

import newLang4.Environment;
import newLang4.LexicalType;
import newLang4.LexicalUnit;
import newLang4.Node;
import newLang4.Value;

/** NAME */
public class Variable extends Node {
	    String var_name;
	    Value v;
	    
	    /** Creates a new instance of variable */
	    public Variable(String name) {
	        var_name = name;
	    }
	    public Variable(LexicalUnit u) {
	        var_name = u.getValue().getSValue();
	    }
	    
	    public static Node isMatch(Environment my_env, LexicalUnit first) {
	        if (first.getType() == LexicalType.NAME) {
	            Variable v;

	            String s = first.getValue().getSValue();
	            v = my_env.getVariable(s); //var_tableに登録が必要なのでここでインスタンス作らないでenvに任せる
	            return v;
	        }
	        return null;
	    }
	    
	    public void setValue(Value my_v) {
	        v = my_v;
	    }
	    
	    public Value getValue() {
	    	if (v == null) {
	    		System.out.println("No value for variable: " + var_name);
	    	}
	        return v;
	    }
	    
	    public boolean parse() {
			return true;
	    }
	    
	    public String toString() {
	    	return var_name;
	    }
}
