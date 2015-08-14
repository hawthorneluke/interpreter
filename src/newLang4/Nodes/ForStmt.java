package newLang4.Nodes;

import newLang4.*;
import newLang4.Values.IntValue;

/**  FOR AssignStmt TO Expr NL StmtList NEXT NAME */
public class ForStmt extends Node {
	Node assignStmt;
	Node toVal;
	Node stmtList;
	Value nextName;

	public ForStmt(Environment my_env, Node assignStmt, Node toVal,
			Node stmtList, Value nextName) {
		type = NodeType.FOR_STMT;
		env = my_env;
		this.assignStmt = assignStmt;
		this.toVal = toVal;
		this.stmtList = stmtList;
		this.nextName = nextName;
	}

	static public Node isMatch(Environment env, LexicalUnit u) {

		if (u.getType() == LexicalType.FOR) {
			LexicalUnit ass = env.getInput().get();
			Node assignStmt = AssignStmt.isMatch(env, ass);
			if (assignStmt != null) {
				LexicalUnit to = env.getInput().get();
				if (to.getType() == LexicalType.TO) {
					LexicalUnit ex = env.getInput().get();
					Node toVal = Expr.isMatch(env, ex);
					if (toVal != null) {
						LexicalUnit nl = env.getInput().get();
						if (nl.getType() == LexicalType.NL) {
							LexicalUnit sl = env.getInput().get();
							Node stmtList = StmtList.isMatch(env, sl);
							if (stmtList != null) {
								LexicalUnit nx = env.getInput().get();
								if (nx.getType() == LexicalType.NEXT) {
									LexicalUnit nm = env.getInput().get();
									if (nm.getType() == LexicalType.NAME) {
										return new ForStmt(env, assignStmt,
												toVal, stmtList, nm.getValue());
									}
									env.getInput().unget(nm);
								}
								env.getInput().unget(nx);
							}
							env.getInput().unget(sl);
						}
						env.getInput().unget(nl);
					}
					env.getInput().unget(ex);
				}
				env.getInput().unget(to);
			}
			env.getInput().unget(ass);
		}

		return null;
	}

	public Value getValue() {
		 //これらは最初だけで評価する。ループするたびにではなくて
		assignStmt.getValue();
		Variable var = ((AssignStmt) assignStmt).getVariable();
		Value toV = toVal.getValue();
		
		Value returnValue = null;
		while (var.getValue().le(toV)) {
			returnValue = stmtList.getValue(); //中身の実行
			var.setValue(var.getValue().add(new IntValue(1))); //i++
		}

		return returnValue;
	}

	public boolean parse() {
		if (!toVal.parse()) {
			return false;
		}
		if (nextName.getSValue().length() <= 0) {
			env.parseError("No NEXT value");
			return false;
		}
		if (!assignStmt.parse())
			return false;
		if (!stmtList.parse())
			return false;
		return true;
	}

	public String toString() {
		return "FOR{" + assignStmt + "TO[" + toVal + "]:" + stmtList + ":"
				+ nextName.getSValue() + "}";
	}
}
