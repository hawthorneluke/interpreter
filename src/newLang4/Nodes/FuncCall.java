package newLang4.Nodes;

import newLang4.*;

/**
 * NAME LP ExprList RP
 * NAME LP RP
 */
public class FuncCall extends Node {
	private Value name;
	private ExprList exprList;
	
    public FuncCall(Environment my_env, Value name, ExprList exprList) {
    	type = NodeType.FUNCTION_CALL;
        env = my_env;
        this.name = name;
        this.exprList = exprList;
    }
    
    static public Node isMatch(Environment env, LexicalUnit u) {

        if (u.getType() == LexicalType.NAME) {
        	LexicalUnit lp = env.getInput().get();
        	if (lp.getType() == LexicalType.LP) {
	        	LexicalUnit el = env.getInput().get();
	        	Node exprList = ExprList.isMatch(env, el);
	        	if (exprList == null) {
	        		env.getInput().unget(el);
	        	}
        		LexicalUnit rp = env.getInput().get();
            	if (rp.getType() == LexicalType.RP) {
            		return new FuncCall(env, u.getValue(), exprList == null ? null : (ExprList)exprList);
            	}
            	env.getInput().unget(rp);
        	}
        	env.getInput().unget(lp);
        }
        return null;
    }
    
    public Value getValue() {
    	Function func = env.getFunction(name.getSValue());
		if (func != null) {
			return func.invoke(exprList);
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
    	if (exprList != null) return name.getSValue() + "[" + exprList.toString() + "]";
    	else return name.getSValue();
    }
}
