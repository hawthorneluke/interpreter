package newLang4.Nodes;

import newLang4.*;

/**
 * IfPrefix Stmt NL
 * IfPrefix Stmt ELSE Stmt NL
 * IfPrefix NL StmtList ElseBlock ENDIF NL
 * While Cond NL StmtList WEND NL
 * DO WHILE Cond NL StmtList LOOP NL
 * DO UNTIL Cond NL StmtList LOOP NL
 * DO NL StmtList LOOP WHILE Cond NL
 * DO NL StmtList LOOP UNTIL Cond NL
 */
public class Block extends Node {
	
	enum Type { IF, IFELSE, IFELSEIF, WHILE, DOWHILE, DOUNTIL, DOLOOPWHILE, DOLOOPUNTIL };
	
	Node cond, stmt, elseStmt;
	Type blockType;
    public Block(Environment my_env, Type blockType, Node cond, Node stmt, Node elseStmt) {
    	type = NodeType.BLOCK;
        env = my_env;
        this.blockType = blockType;
        this.cond = cond;
        this.stmt = stmt;
        this.elseStmt = elseStmt;
    }
    
    static public Node isMatch(Environment env, LexicalUnit u) {

    	Node ifPrefix = IfPrefix.isMatch(env, u);
    	if (ifPrefix != null) { //IF
    		LexicalUnit s = env.getInput().get();
    		Node stmt = Stmt.isMatch(env, s);
    		if (stmt != null) {
    			LexicalUnit nl = env.getInput().get();
    			if (nl.getType() == LexicalType.NL) {
    				return new Block(env, Type.IF, ifPrefix, stmt, null);
    			}
    			if (nl.getType() == LexicalType.ELSE) { //IFELSE
    				LexicalUnit s2 = env.getInput().get();
    	    		Node stmt2 = Stmt.isMatch(env, s);
    	    		if (stmt != null) {
    	    			LexicalUnit nl2 = env.getInput().get();
    	    			if (nl2.getType() == LexicalType.NL) {
    	    				return new Block(env, Type.IFELSE, ifPrefix, stmt, stmt2);
    	    			}
    	    			env.getInput().unget(nl2);
    	    		}
    	    		env.getInput().unget(s2);
    			}
    			env.getInput().unget(nl);
    		}
    		if (s.getType() == LexicalType.NL) { //IFELSEIF
    			LexicalUnit sl = env.getInput().get();
    			Node stmtList = StmtList.isMatch(env, sl);
    			if (stmtList != null) {
    				LexicalUnit eb = env.getInput().get();
        			Node elseBlock = ElseBlock.isMatch(env, eb);
        			if (elseBlock == null) {
        				env.getInput().unget(eb);
        			}
    				LexicalUnit endif = env.getInput().get();
        			if (endif.getType() == LexicalType.ENDIF) {
        				LexicalUnit nl3 = env.getInput().get();
            			if (nl3.getType() == LexicalType.NL) {
            				return new Block(env, Type.IFELSEIF, ifPrefix, stmtList, elseBlock);
            			}
            			env.getInput().unget(nl3);
        			}
        			env.getInput().unget(endif); 
        			if (elseBlock != null) env.getInput().unget(eb); 
    			}
    			env.getInput().unget(sl);
    		}
    		env.getInput().unget(s);
    	}
    	
    	if (u.getType() == LexicalType.WHILE) { //WHILE
    		LexicalUnit c = env.getInput().get();
    		Node cond = Cond.isMatch(env, c);
    		if (cond != null) {
    			LexicalUnit nl = env.getInput().get();
    	    	if (nl.getType() == LexicalType.NL) {
    	    		LexicalUnit sl = env.getInput().get();
    	    		Node stmtList = StmtList.isMatch(env, sl);
    	    		if (stmtList != null) {
    	    			LexicalUnit wend = env.getInput().get();
    	    	    	if (wend.getType() == LexicalType.WEND) {
    	    	    		LexicalUnit nl2 = env.getInput().get();
    	        	    	if (nl2.getType() == LexicalType.NL) {
    	        	    		return new Block(env, Type.WHILE, cond, stmtList, null);
    	        	    	}
    	        	    	env.getInput().unget(nl2);
    	    	    	}
    	    	    	env.getInput().unget(wend);
    	    		}
    	    		env.getInput().unget(sl);
    	    	}
    	    	env.getInput().unget(nl);
    		}
    		env.getInput().unget(c);
    	}
    	
    	if (u.getType() == LexicalType.DO) {
    		LexicalUnit wh = env.getInput().get();
    		Type t = null;
	    	if (wh.getType() == LexicalType.WHILE) { //DOWHILE
	    		t = Type.DOWHILE;
	    	}
	    	else if (wh.getType() == LexicalType.UNTIL) { //DOUNTIL
	    		t = Type.DOUNTIL;
	    	}
	    	
	    	if (t != null) {
	    		LexicalUnit c = env.getInput().get();
	    		Node cond = Cond.isMatch(env, c);
	    		if (cond != null) {
	    			LexicalUnit nl = env.getInput().get();
	    	    	if (nl.getType() == LexicalType.NL) {
	    	    		LexicalUnit sl = env.getInput().get();
	    	    		Node stmtList = StmtList.isMatch(env, sl);
	    	    		if (stmtList != null) {
	    	    			LexicalUnit nl2 = env.getInput().get();
    	        	    	if (nl2.getType() == LexicalType.NL) {
		    	    			LexicalUnit loop = env.getInput().get();
		    	    	    	if (loop.getType() == LexicalType.LOOP) {
		    	    	    		LexicalUnit nl3 = env.getInput().get();
		    	        	    	if (nl3.getType() == LexicalType.NL) {
		    	        	    		return new Block(env, t, cond, stmtList, null);
		    	        	    	}
		    	        	    	env.getInput().unget(nl3);
		    	    	    	}
		    	    	    	env.getInput().unget(loop);
    	        	    	}
    	        	    	env.getInput().unget(nl2);
	    	    		}
	    	    		env.getInput().unget(sl);
	    	    	}
	    	    	env.getInput().unget(nl);
	    		}
	    		env.getInput().unget(c);
	    	}
	    	else if (wh.getType() == LexicalType.NL) { //DOLOOPWHILE, DOLOOPUNTIL
    			LexicalUnit sl = env.getInput().get();
 	    		Node stmtList = StmtList.isMatch(env, sl);
 	    		if (stmtList != null) {
 	    			LexicalUnit loop = env.getInput().get();
 	    	    	if (loop.getType() == LexicalType.LOOP) {
 	    	    		LexicalUnit wh2 = env.getInput().get();
 	    	    		if (wh2.getType() == LexicalType.WHILE) { //DOLOOPWHILE
 	    	    			t = Type.DOLOOPWHILE;
 	    	    		}
 	    	    		else if (wh2.getType() == LexicalType.UNTIL) { //DOLOOPUNTIL
 	    	    			t = Type.DOLOOPUNTIL;
 	    	    		}
 	    	    		if (t != null) {
 	    	    			LexicalUnit c = env.getInput().get();
 	    		    		Node cond = Cond.isMatch(env, c);
 	    		    		if (cond != null) {
 	    		    			LexicalUnit nl2 = env.getInput().get();
 	    	        	    	if (nl2.getType() == LexicalType.NL) {
 	    	        	    		return new Block(env, t, cond, stmtList, null);
 	    	        	    	}
 	    	        	    	env.getInput().unget(nl2);
 	    		    		}
 	    		    		env.getInput().unget(c);
 	    	    		}
 	    	    		env.getInput().unget(wh2);
 	    	    	}
 	    	    	env.getInput().unget(loop);
 	    		}
 	    		env.getInput().unget(sl);
	    	}
	    	env.getInput().unget(wh);
    	}
    	
    	return null;
    }
    
    public Value getValue() {
    	Value condValue;
    	switch(blockType) {
    	case IF:
    		condValue = cond.getValue();
    		if (condValue.getBValue() == true) {
    			return stmt.getValue();
    		}
    		else return condValue;
    	case IFELSE:
    		condValue = cond.getValue();
    		if (condValue.getBValue() == true) {
    			return stmt.getValue();
    		}
    		else return elseStmt.getValue();
    	case IFELSEIF:
    		condValue = cond.getValue();
    		if (condValue.getBValue() == true) {
    			return stmt.getValue();
    		}
    		else if (elseStmt != null) return elseStmt.getValue();
    		else return condValue;
    	case WHILE:
    	case DOWHILE:
    		condValue = cond.getValue();
    		while (condValue.getBValue() == true) {
    			stmt.getValue();
    			condValue = cond.getValue();
    		}
    		return condValue;
    	case DOUNTIL:
    		condValue = cond.getValue();
    		while (condValue.getBValue() == false) {
    			stmt.getValue();
    			condValue = cond.getValue();
    		}
    		return condValue;
    	case DOLOOPWHILE:
    		do {
    			stmt.getValue();
    			condValue = cond.getValue();
    		}
    		while (condValue.getBValue() == true);
    		return condValue;
    	case DOLOOPUNTIL:
    		do {
    			stmt.getValue();
    			condValue = cond.getValue();
    		}
    		while (condValue.getBValue() == false);
    		return condValue;
    	}
		return null;    	
    }
    
    public boolean parse() {
    	if (!cond.parse()) return false;
    	if (!stmt.parse()) return false;
    	if (elseStmt != null) {
    		if (!elseStmt.parse()) return false;
    	}
    	return true;
    }

    public String toString() {
    	switch(blockType) {
    	case IF:
    		return cond + ":{" + stmt + "}";
    	case IFELSE:
    		return cond + ":{" + stmt + "}:" + "ELSE{" + elseStmt + "}";
    	case IFELSEIF:
    		if (elseStmt != null) return cond + ":{" + stmt + "}:" + elseStmt;
    		else return cond + ":{" + stmt + "}";
    	case WHILE:
    		return "WHILE:" + cond + ":{" + stmt + "}";
    	case DOWHILE:
    		return "DOWHILE:" + cond + ":{" + stmt + "}";
    	case DOUNTIL:
    		return "DOUNTIL:" + cond + ":{" + stmt + "}";
    	case DOLOOPWHILE:
    		return "DOLOOPWHILE:" + cond + ":{" + stmt + "}";
    	case DOLOOPUNTIL:
    		return "DOLOOPUNTIL:" + cond + ":{" + stmt + "}";
    	}
    	
    	return "error";
    }
}
