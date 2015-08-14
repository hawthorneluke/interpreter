package newLang4.Nodes;

import newLang4.*;

/** IF Cond THEN */
public class IfPrefix extends Node {
	Node next;
	
    public IfPrefix(Environment my_env, Node next) {
    	type = NodeType.IF_BLOCK;
        env = my_env;
        this.next = next;
    }
    
    static public Node isMatch(Environment env, LexicalUnit u) {
        Node handler;

        if (u.getType() == LexicalType.IF) {
        	LexicalUnit con = env.getInput().get();
        	handler = Cond.isMatch(env, con);
        	if (handler != null) {
        		LexicalUnit then = env.getInput().get();
        		if (then.getType() == LexicalType.THEN) {
        			return new IfPrefix(env, handler);
        		}
        		env.getInput().unget(then);
        	}
        	env.getInput().unget(con);
        }
        
    	return null;
    }
    
    public Value getValue() {
    	return next.getValue();
    }
    
    public boolean parse() {
		if (!next.parse()) return false;
		return true;
    }
    
    public String toString() {
    	return "if[" + next + "]";
    }
}
