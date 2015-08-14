package newLang4.Nodes;

import newLang4.Environment;
import newLang4.LexicalType;
import newLang4.LexicalUnit;
import newLang4.Node;
import newLang4.NodeType;
import newLang4.Value;

/** 
 * null
 * ELSEIF Cond THEN NL StmtList ElseIfBlock
 */
public class ElseIfBlock extends Node {
	Node cond, stmtList, more;
    public ElseIfBlock(Environment my_env, Node cond, Node stmtList, Node more) {
    	type = NodeType.IF_BLOCK;
        env = my_env;
        this.cond = cond;
        this.stmtList = stmtList;
        this.more = more;
    }
    
    static public Node isMatch(Environment env, LexicalUnit u) {
    	
    	if (u.getType() == LexicalType.ELSEIF) {
    		LexicalUnit c = env.getInput().get();
    		Node cond = Cond.isMatch(env, c);
    		if (cond != null) {
    			LexicalUnit then = env.getInput().get();
    			if (then.getType() == LexicalType.THEN) {
    				LexicalUnit nl = env.getInput().get();
        			if (nl.getType() == LexicalType.NL) {
        				LexicalUnit sl = env.getInput().get();
        				Node stmtList = StmtList.isMatch(env, sl);
        				if (stmtList != null) {
        					LexicalUnit m = env.getInput().get();
        					Node more = ElseIfBlock.isMatch(env, m);
        					if (more == null) env.getInput().unget(m);
        					return new ElseIfBlock(env, cond, stmtList, more);
        				}
        				env.getInput().unget(sl);
        			}
        			env.getInput().unget(nl);
    			}
    			env.getInput().unget(then);
    		}
    		env.getInput().unget(c);
    	}
    	
    	return null;
    }
    
    public Value getValue() {
    	Value value = cond.getValue();
    	if (value.getBValue() == true) {
    		stmtList.getValue();
    		return value;
    	}
    	else if (more != null) {
    		return more.getValue();
    	}
    	
    	return value;
    }
    
    public boolean parse() {
    	if (!cond.parse()) return false;
    	if (!stmtList.parse()) return false;
    	if (more != null) {
    		if (!more.parse()) return false;
    	}
    	return true;
    }
    
    public String toString() {
    	if (more != null) return "elseif[" + cond + "[" + stmtList + "]" + more + "]";
    	else return "elseif[" + cond + "[" + stmtList + "]" + "]";
    }
}
