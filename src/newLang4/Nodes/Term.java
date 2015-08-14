package newLang4.Nodes;

import newLang4.*;

/**
 * SUB Expr
 * LP Expr RP
 * FuncCall
 * Variable
 * NAME
 * INTVAL
 * DOUBLEVAL
 * LITERAL
 */
public class Term extends Node {
	Node next;
	boolean negative = false;
	boolean brackets = false;
	
	public Term(Environment my_env, Node next) { //(expr)
    	type = NodeType.EXPR;
        env = my_env;
        this.next = next;
        this.brackets = true;
    }
	public Term(Environment my_env, Node next, boolean negative) { //-expr
    	type = NodeType.EXPR;
        env = my_env;
        this.next = next;
        this.negative = negative;
    }
    
    static public Node isMatch(Environment env, LexicalUnit u) {
        Node handler;
        
        if (u.getType() == LexicalType.SUB) {
        	LexicalUnit ex = env.getInput().get();
        	handler = Expr.isMatch(env, ex);
        	if (handler != null) {
        			return new Term(env, handler, true);
        	}
        	env.getInput().unget(ex);
        }
        
        if (u.getType() == LexicalType.LP) {
        	LexicalUnit ex = env.getInput().get();
        	handler = Expr.isMatch(env, ex);
        	if (handler != null) {
        		LexicalUnit rp = env.getInput().get();
        		if (rp.getType() == LexicalType.RP) {
        			return new Term(env, handler);
        		}
        		env.getInput().unget(rp);
        	}
        	env.getInput().unget(ex);
        }
        
        Node funcCall = FuncCall.isMatch(env, u);
        if (funcCall != null) {
        	return funcCall;
        }
        
        
        Node var = Variable.isMatch(env, u);
        if (var != null) {
        	return var;
        }
        
        
        if (u.getType() == LexicalType.NAME) 
        	return new Node(NodeType.STRING_CONSTANT, u);
        if (u.getType() == LexicalType.INTVAL) 
        	return new Node(NodeType.INT_CONSTANT, u);
        if (u.getType() == LexicalType.DOUBLEVAL) 
        	return new Node(NodeType.DOUBLE_CONSTANT, u);
        if (u.getType() == LexicalType.LITERAL) 
        	return new Node(NodeType.STRING_CONSTANT, u);

    	return null;
    }
    
    public Value getValue() {
    	Value value = next.getValue();
    	if (negative) return value.negative();
    	else return value;
    }
    
    public boolean parse() {
    	if (!next.parse()) return false;
		return true;
    }
    
    public String toString() {
    	if (brackets == false) {
    		if (negative) return "-" + next;
    		else return next.toString();
    	}
    	else {
    		return "(" + next + ")";
    	}
    }
}
