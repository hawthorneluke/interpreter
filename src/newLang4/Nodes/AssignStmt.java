package newLang4.Nodes;

import newLang4.*;

/** Variable = Expr */
public class AssignStmt extends Node {
	private Node expr;
	private Variable var;
	
	public AssignStmt(Environment my_env, Variable var, Node expr) {
    	type = NodeType.ASSIGN_STMT;
        env = my_env;
        this.var = var;
        this.expr = expr;
    }
    
    static public Node isMatch(Environment env, LexicalUnit u) {

    	Node var = Variable.isMatch(env, u);
    	if (var != null) {
        	LexicalUnit eq = env.getInput().get();
        	if (eq.getType() == LexicalType.EQ) {
        		LexicalUnit ex = env.getInput().get();
        		Node expr = Expr.isMatch(env, ex);
        		if (expr != null) {
        			return new AssignStmt(env, (Variable)var, expr);
        		}
        		env.getInput().unget(ex);
        	}
        	env.getInput().unget(eq);
        }

    	return null;
    }
    
    public Value getValue() {
    	var.setValue(expr.getValue());
    	return var.getValue();
    }
    
    public boolean parse() {
    	if (!var.parse()) return false;
    	if (!expr.parse()) return false;
    	return true;
    }
    
    public Variable getVariable() {
    	return var;
    }
    
    public String toString() {
    	return var + "(" + expr + ")";
    }
}
