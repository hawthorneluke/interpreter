package newLang4;

public interface Value {
	public String getSValue();
		// ストリング型で値を取り出す。必要があれば、型変換を行う。
    public int getIValue();
    	// 整数型で値を取り出す。必要があれば、型変換を行う。
    public double getDValue();
    	// 小数点型で値を取り出す。必要があれば、型変換を行う。
    public boolean getBValue();
    	// 論理型で値を取り出す。必要があれば、型変換を行う。
    public ValueType getType();
    
    public Value add(Value v); //Value同士の足し算
    public Value sub(Value v); //Value同士の引き算
    public Value mul(Value v); //Value同士の掛け算
    public Value div(Value v); //Value同士の割り算
    
    public Value negative(); //このValueを負にする
    
    public boolean eq(Value v); //Value同士の=比較
    public boolean gt(Value v); //Value同士の＞比較
    public boolean lt(Value v); //Value同士の＜比較
    public boolean ge(Value v); //Value同士の≧比較
    public boolean le(Value v); //Value同士の≦比較
    public boolean ne(Value v); //Value同士の≠比較
}
