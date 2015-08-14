package newLang4;

import java.util.Hashtable;

import newLang4.Functions.*;
import newLang4.Nodes.Variable;
import newLang4.Values.BoolValue;
import newLang4.Values.VoidValue;

public class Environment {
	   LexicalAnalyzer input;
	   Hashtable<String, Function> library;
	   Hashtable<String, Variable> var_table;
	   
	   //使用出来るFunctionのリスト
	   Function[] functions = {new Print(), new Read(), new ToInt(), new ToStr(), new Rand(), new OpenFileRead(), new OpenFileWrite(), new CloseFileRead(), new CloseFileWrite(), new ReadFile(), new WriteFile(), new MapPut(), new MapGet()};
	    
	    public Environment(LexicalAnalyzer my_input) {
	        input = my_input;
	        library = new Hashtable<String, Function>();
	        addFunctions();
	        
	        var_table = new Hashtable<String, Variable>();
	        addVars();
	    }
	    
	    /** libraryにFunctionを追加 */
	    private void addFunctions() {
	    	for(Function func : functions) {
	    		library.put(func.getName().toUpperCase(), func);
	    	}
	    }
	    
	    /** 最初から値が決まっている変数を追加 */
	    private void addVars() {
	    	//boolean true
	    	Variable var = new Variable("true");
	    	var.setValue(new BoolValue(true));
	    	var_table.put("true", var);
	    	var_table.put("TRUE", var);
	    	
	    	//boolean false
	    	var = new Variable("false");
	    	var.setValue(new BoolValue(false));
	    	var_table.put("false", var);
	    	var_table.put("FALSE", var);
	    	
	    	//void/null
	    	var = new Variable("null");
	    	var.setValue(new VoidValue());
	    	var_table.put("null", var);
	    	var_table.put("NULL", var);
	    }
	    
	    public LexicalAnalyzer getInput() {
	        return input;
	    }
	    
	    public Function getFunction(String fname) {
	        return (Function) library.get(fname.toUpperCase());
	    }
	    
	    public Variable getVariable(String vname) {
	        Variable v;
	        v = (Variable) var_table.get(vname);
	        if (v == null) {
	            v = new Variable(vname);
	            var_table.put(vname, v);
	        }
	        return v;
	    }
	    
	    public void parseError(String str) {
	    	System.out.println("Syntax Error: " + str);
	    }
}
