package newLang4.Nodes;

import newLang4.Environment;
import newLang4.LexicalType;
import newLang4.LexicalUnit;
import newLang4.Node;
import newLang4.NodeType;
import newLang4.Value;

/** 
 * Stmt
 * Stmt NL StmtList
 * Block
 * Block StmtList
 */
public class StmtList extends Node {
	Node next;
	Node more;
	
    public StmtList(Environment my_env, Node next, Node more) {
    	type = NodeType.STMT_LIST;
        env = my_env;
        this.next = next;
        this.more = more;
    }
    
    static public Node isMatch(Environment env, LexicalUnit u) {

        Node stmt = Stmt.isMatch(env, u);
        if (stmt != null) {
        	LexicalUnit nl = env.getInput().get();
        	if (nl.getType() == LexicalType.NL) {
        		LexicalUnit sl = env.getInput().get();
        		Node stmtList = StmtList.isMatch(env, sl);
        		if (stmtList != null) {
        			return new StmtList(env, stmt, stmtList);
        		}
        		env.getInput().unget(sl);
        	}
        	env.getInput().unget(nl);
        	return stmt;
        }
        
        
        Node block = Block.isMatch(env, u);
        if (block != null) {
        	LexicalUnit sl = env.getInput().get();
        	Node stmtList = StmtList.isMatch(env, sl);
        	if (stmtList != null) {
        		return new StmtList(env, block, stmtList);
        	}
        	env.getInput().unget(sl);
        	return block;
        }

    	return null;
    }
    
    public boolean parse() {
    	if (!next.parse()) return false;
    	if (!more.parse()) return false;
		return true;
    }
    
    public Value getValue() {
    	Value v = next.getValue();
    	more.getValue();
    	return v;
    }
    
    public String toString() {
    	return next.toString() + ";\n" + more.toString();
    }
}
