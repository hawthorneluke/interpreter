package newLang4;

import newLang4.Nodes.ExprList;

public class Function {
    
	protected String name;
	
    /** Creates a new instance of Function */
    public Function() {
    }
    
    /** このFunctionの呼び出し名 */
    public String getName() {
    	return name;
    }
    
    /** このFunctionを実行する場合 */
    public Value invoke(ExprList arg) {
    	return null;        
    }
    
}
