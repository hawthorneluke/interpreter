package newLang4.Nodes;

import newLang4.*;

/** NAME ExprList */
public class SubCall extends Node {
	private Value name;
	private Node exprList;
	
    public SubCall(Environment my_env, Value name, Node exprList) {
    	type = NodeType.FUNCTION_CALL;
        env = my_env;
        this.name = name;
        this.exprList = exprList;
    }
    
    static public Node isMatch(Environment env, LexicalUnit u) {

        if (u.getType() == LexicalType.NAME) {
        	LexicalUnit el = env.getInput().get();
        	Node exprList = ExprList.isMatch(env, el);
        	if (exprList == null) {
        		env.getInput().unget(el);
        	}
        	return new SubCall(env, u.getValue(), exprList);        	
        }
        return null;
    }
    
    public Value getValue() {
    	Function func = env.getFunction(name.getSValue());
		if (func != null) {
			return func.invoke((ExprList)exprList);
		}
		
		return null;
    }
    
    public boolean parse() {
    	if (env.getFunction(name.getSValue()) == null) {
    		env.parseError("No function of name: " + name.getSValue());
    		return false;
    	}
    	
		if (exprList != null) {
			if (!exprList.parse()) return false;
		}
		
		return true;
    }
    
    public String toString() {
    	return name.getSValue().toUpperCase() + "[" + exprList.toString() + "]";
    }
}
