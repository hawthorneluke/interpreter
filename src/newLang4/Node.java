package newLang4;

public class Node {
    protected NodeType type;
    protected Environment env;
    protected LexicalUnit value = null;

    public Node() {
    }
    public Node(NodeType my_type) {
        type = my_type;
    }
    public Node(NodeType my_type, LexicalUnit my_value) {
        type = my_type;
        value = my_value;
    }
    public Node(Environment my_env) {
        env = my_env;
    }
    
    public NodeType getType() {
        return type;
    }
    
    static public Node isMatch(Environment env, LexicalUnit u) {
    	return null;
    }
    
    public boolean parse() {
        return true;
    }
    
    public Value getValue() {
    	if (value != null) return value.getValue();
    	else return null;
    }
 
    @Override
    public String toString() {
    	if (type == NodeType.END) return "END";
    	if (type == NodeType.STRING_CONSTANT) return "\"" + value.getValue().getSValue() + "\"";
    	if (type == NodeType.INT_CONSTANT) return getValue().getSValue();
    	if (type == NodeType.DOUBLE_CONSTANT) return getValue().getSValue();
    	return "Node";        
    }

}
