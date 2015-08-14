package newLang4.Nodes;

import newLang4.*;

/** StmtList */
public class Program extends Node {
	Node next;
	
    public Program(Environment my_env) {
    	type = NodeType.PROGRAM;
        env = my_env;
        next = StmtList.isMatch(env, env.getInput().get());
    }
    
    public boolean parse() {
    	return next.parse();
    }
    
    public Value getValue() {
    	return next.getValue();
    }
    
    public String toString() {
    	return next.toString();
    }
}
