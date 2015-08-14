package newLang4.Nodes;

import newLang4.*;

/**
 * Term
 * Term MoreTerms
 */
public class Expr extends Node {
	Node term, moreTerms;
	public Expr(Environment my_env, Node term, Node moreTerms) {
    	type = NodeType.EXPR;
        env = my_env;
        this.term = term;
        this.moreTerms = moreTerms;
    }
    
    static public Node isMatch(Environment env, LexicalUnit u) {
        
        Node term = Term.isMatch(env, u);
        if (term != null) {
        	LexicalUnit next = env.getInput().get();
        	Node moreTerms = MoreTerms.isMatch(env, next); //‚È‚¢‚©‚à‚µ‚ê‚È‚¢‚Ì‚Ånull‚Í‚ ‚è
        	if (moreTerms == null) env.getInput().unget(next);
        	return new Expr(env, term, moreTerms);
        }

    	return null;
    }
    
    public Value getValue() {
    	if (moreTerms == null) {
    		return term.getValue();
    	}
    	else {
    		switch(((MoreTerms)moreTerms).getUnit().getType()) {
    		case ADD:
    			return term.getValue().add(moreTerms.getValue());
    		case SUB:
    			return term.getValue().sub(moreTerms.getValue());
    		case MUL:
    			return term.getValue().mul(moreTerms.getValue());
    		case DIV:
    			return term.getValue().div(moreTerms.getValue());
			default:
				return term.getValue();
    		}
    	}
    }
    
    public boolean parse() {
    	if (!term.parse()) return false;
    	if (moreTerms != null) {
    		if (!moreTerms.parse()) return false;
    	}
    	return true;
    }
    
    public String toString() {
    	if (moreTerms != null)
    		return ((MoreTerms)moreTerms).getUnit().getValue().getSValue() + "[" + term.toString() + "," + moreTerms.toString() + "]";
    	else return term.toString();
    }
}
