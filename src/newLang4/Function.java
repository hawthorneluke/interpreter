package newLang4;

import newLang4.Nodes.ExprList;

public class Function {
    
	protected String name;
	
    /** Creates a new instance of Function */
    public Function() {
    }
    
    /** ����Function�̌Ăяo���� */
    public String getName() {
    	return name;
    }
    
    /** ����Function�����s����ꍇ */
    public Value invoke(ExprList arg) {
    	return null;        
    }
    
}
