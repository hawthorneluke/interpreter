package newLang4.Functions;

import java.util.HashMap;
import java.util.Map;

import newLang4.Function;
import newLang4.Value;
import newLang4.Nodes.ExprList;
import newLang4.Values.BoolValue;

/** HashMap‚É’l‚ð‘}“ü‚·‚é */
public class MapPut extends Function {
	
	public static Map<String, Value> map = new HashMap<String, Value>();

    public MapPut() {
    	name = "mapPut";
    }
    
    public Value invoke(ExprList arg) {
    	String name = arg.get(0).getSValue();
    	Value v = arg.get(1);
    	map.put(name, v);
    	return new BoolValue(true);
    }
    
}
