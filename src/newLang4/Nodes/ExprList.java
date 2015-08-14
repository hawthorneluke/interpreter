package newLang4.Nodes;

import newLang4.Environment;
import newLang4.LexicalType;
import newLang4.LexicalUnit;
import newLang4.Node;
import newLang4.NodeType;
import newLang4.Value;
import newLang4.Values.VoidValue;

/**
 * Expr
 * Expr COMMA ExprList
 */
public class ExprList extends Node {
	Node next;
	Node moreNext;
    public ExprList(Environment my_env, Node next, Node moreNext) {
    	type = NodeType.EXPR_LIST;
        env = my_env;
        this.next = next;
        this.moreNext = moreNext;
    }
    
    static public Node isMatch(Environment env, LexicalUnit u) {
        Node handler;

        handler = Expr.isMatch(env, u);
        if (handler != null) {
        	LexicalUnit comma = env.getInput().get();
        	if (comma.getType() == LexicalType.COMMA) {
        		LexicalUnit next = env.getInput().get();
        		Node moreNext = ExprList.isMatch(env, next);
        		if (moreNext != null) return new ExprList(env, handler, moreNext);
        		env.getInput().unget(next);
        		return null;
        	}
        	env.getInput().unget(comma);
        	return  new ExprList(env, handler, null);
        }

    	return null;
    }
    
    public Value getValue() {
    	Value v = next.getValue();
    	if (moreNext != null) moreNext.getValue();
    	return v;
    }
    
    public boolean parse() {
    	if (!next.parse()) return false;
    	if (moreNext != null) {
    		if (!moreNext.parse()) return false;
    	}
    	return true;
    }
    
    public String toString() {
    	if (moreNext != null) return next.toString() + "," + moreNext.toString();
    	else return next.toString();
    }
    
    public Value get(int i) {
    	if (i == 0) return next.getValue();
    	else {
    		if (moreNext == null) return new VoidValue();
    		else return ((ExprList)moreNext).get(i-1);
    	}
    }
}
