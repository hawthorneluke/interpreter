package newLang4.Nodes;

import newLang4.Environment;
import newLang4.LexicalType;
import newLang4.LexicalUnit;
import newLang4.Node;
import newLang4.NodeType;
import newLang4.Value;

/** 
 * ElseIfBlock
 * ElseIfBlock ELSE NL StmtList
 */
public class ElseBlock extends Node {
	Node elseIfBlock, stmtList;
    public ElseBlock(Environment my_env, Node elseIfBlock, Node stmtList) {
    	type = NodeType.IF_BLOCK;
        env = my_env;
        this.elseIfBlock = elseIfBlock;
        this.stmtList = stmtList;
    }
    
    static public Node isMatch(Environment env, LexicalUnit u) {

        Node elseIfBlock = ElseIfBlock.isMatch(env, u);
        if (elseIfBlock == null) {
        	env.getInput().unget(u);
        }
    	LexicalUnit els = env.getInput().get();
    	if (els.getType() == LexicalType.ELSE) {
    		LexicalUnit nl = env.getInput().get();
    		if (nl.getType() == LexicalType.NL) {
    			LexicalUnit sl = env.getInput().get();
    			Node stmtList = StmtList.isMatch(env, sl);
    			if (stmtList != null) {
            		return new ElseBlock(env, elseIfBlock, stmtList);
    			}
    			env.getInput().unget(sl);
    		}
    		env.getInput().unget(nl);
    	}
    	if (elseIfBlock != null) env.getInput().unget(els);
    	return elseIfBlock;
    }
    
    public Value getValue() {
    	if (elseIfBlock != null) {
    		Value value = elseIfBlock.getValue();
    		if (value.getBValue() == false) {
    			return stmtList.getValue();
    		}
    		else return value;
    	}
    	else return stmtList.getValue();
    }
    
    public boolean parse() {
    	if (elseIfBlock != null) {
    		if (!elseIfBlock.parse()) return false;
    	}
    	if (!stmtList.parse()) return false;
    	return true;
    }
    
    public String toString() {
    	if (elseIfBlock != null) return elseIfBlock + "else[" + stmtList + "]";
    	else return "else[" + stmtList + "]";
    }
}
