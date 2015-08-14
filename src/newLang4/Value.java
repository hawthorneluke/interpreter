package newLang4;

public interface Value {
	public String getSValue();
		// �X�g�����O�^�Œl�����o���B�K�v������΁A�^�ϊ����s���B
    public int getIValue();
    	// �����^�Œl�����o���B�K�v������΁A�^�ϊ����s���B
    public double getDValue();
    	// �����_�^�Œl�����o���B�K�v������΁A�^�ϊ����s���B
    public boolean getBValue();
    	// �_���^�Œl�����o���B�K�v������΁A�^�ϊ����s���B
    public ValueType getType();
    
    public Value add(Value v); //Value���m�̑����Z
    public Value sub(Value v); //Value���m�̈����Z
    public Value mul(Value v); //Value���m�̊|���Z
    public Value div(Value v); //Value���m�̊���Z
    
    public Value negative(); //����Value�𕉂ɂ���
    
    public boolean eq(Value v); //Value���m��=��r
    public boolean gt(Value v); //Value���m�́���r
    public boolean lt(Value v); //Value���m�́���r
    public boolean ge(Value v); //Value���m�́���r
    public boolean le(Value v); //Value���m�́���r
    public boolean ne(Value v); //Value���m�́���r
}
