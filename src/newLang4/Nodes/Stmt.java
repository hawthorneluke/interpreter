package newLang4.Nodes;

import newLang4.*;

/**
 * END
 * AssignStmt
 * FuncCall
 * SubCall
 * ForStmt
 */
public class Stmt extends Node {
	Node next;
	
    public Stmt(Environment my_env) {
    	type = NodeType.STMT;
        env = my_env;
        next = null;
    }
    
    static public Node isMatch(Environment env, LexicalUnit u) {
        Node handler;

        if (u.getType() == LexicalType.END) 
        	return new Node(NodeType.END);
        
    	handler = AssignStmt.isMatch(env, u);
    	if (handler != null) return handler;
    	handler = FuncCall.isMatch(env, u);
    	if (handler != null) return handler;
    	handler = SubCall.isMatch(env, u);
    	if (handler != null) return handler;
		handler = ForStmt.isMatch(env, u);
    	return handler;
    }
}
