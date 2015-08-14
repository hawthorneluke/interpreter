package newLang4.Nodes;

import newLang4.Environment;
import newLang4.LexicalType;
import newLang4.LexicalUnit;
import newLang4.Node;
import newLang4.NodeType;
import newLang4.Value;

/**
 * ADD/SUB/MUL/DIV Term
 * ADD/SUB/MUL/DIV Term MoreTerms
 */
public class MoreTerms extends Node {
	Node term, moreTerms;
	public MoreTerms(Environment my_env, LexicalUnit value, Node term, Node moreTerms) {
    	type = NodeType.EXPR;
        env = my_env;
        this.value = value;
        this.term = term;
        this.moreTerms = moreTerms;
    }
    
    static public Node isMatch(Environment env, LexicalUnit u) {
        
        if (u.getType() == LexicalType.ADD ||
        		u.getType() == LexicalType.SUB ||
        		u.getType() == LexicalType.MUL ||
        		u.getType() == LexicalType.DIV 
        		) {
        	LexicalUnit t = env.getInput().get();
        	Node term = Term.isMatch(env, t);
            if (term != null) {
            	LexicalUnit mt = env.getInput().get();
            	Node moreTerms = MoreTerms.isMatch(env, mt); //‚È‚¢‚©‚à‚µ‚ê‚È‚¢‚Ì‚Ånull‚Í‚ ‚è
            	if (moreTerms == null) env.getInput().unget(mt);
            	return new MoreTerms(env, u, term, moreTerms);
            }
            env.getInput().unget(t);
        }
        
    	return null;
    }
    
    public LexicalUnit getUnit() {
    	return value;
    }
    
    public Value getValue() {
    	if (moreTerms == null) {
    		return term.getValue();
    	}
    	else {
    		switch(getUnit().getType()) {
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
