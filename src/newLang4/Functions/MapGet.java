package newLang4.Functions;

import newLang4.Function;
import newLang4.Value;
import newLang4.Nodes.ExprList;
import newLang4.Values.VoidValue;

/** HashMapから値を取得 */
public class MapGet extends Function {
    
    public MapGet() {
    	name = "mapGet";
    }
    
    public Value invoke(ExprList arg) {
    	String name = arg.getValue().getSValue();
    	Value v = MapPut.map.get(name);
    	if (v == null) return new VoidValue();
    	else return v;
    }
    
}
