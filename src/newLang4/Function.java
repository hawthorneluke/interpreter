package newLang4;

import newLang4.Nodes.ExprList;

public class Function {
    
	protected String name;
	
    /** Creates a new instance of Function */
    public Function() {
    }
    
    /** ‚±‚ÌFunction‚ÌŒÄ‚Ño‚µ–¼ */
    public String getName() {
    	return name;
    }
    
    /** ‚±‚ÌFunction‚ğÀs‚·‚éê‡ */
    public Value invoke(ExprList arg) {
    	return null;        
    }
    
}
