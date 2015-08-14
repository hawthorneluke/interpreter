package newLang4.Values;

import newLang4.Value;
import newLang4.ValueType;

public class IntValue implements Value {

	private Integer value;
	private ValueType type = ValueType.INTEGER;

	public IntValue(int i)
	{
		this.value = i;
	}

	@Override
	public String getSValue() {
		return value.toString();
	}

	@Override
	public int getIValue() {
		return value.intValue();
	}

	@Override
	public double getDValue() {
		return value.doubleValue();
	}

	@Override
	public boolean getBValue() {
		return value > 0;
	}

	@Override
	public ValueType getType() {
		return this.type;
	}
	
	@Override
	public Value add(Value v) {
		return new IntValue(getIValue() + v.getIValue());
	}
	
	@Override
	public Value sub(Value v) {
		return new IntValue(getIValue() - v.getIValue());
	}
	
	@Override
	public Value mul(Value v) {
		return new IntValue(getIValue() * v.getIValue());
	}
	
	@Override
	public Value div(Value v) {
		return new IntValue(getIValue() / v.getIValue());
	}
	
	@Override
	public boolean eq(Value v) {
		return value == v.getIValue();
	}
	
	@Override
    public boolean gt(Value v) {
		return value > v.getIValue();
	}
	
	@Override
    public boolean lt(Value v) {
		return value < v.getIValue();
	}
	
	@Override
    public boolean ge(Value v) {
		return value >= v.getIValue();
	}
	
	@Override
    public boolean le(Value v) {
		return value <= v.getIValue();
	}
	
	@Override
    public boolean ne(Value v) {
		return value != v.getIValue();
	}
	
	@Override
	public Value negative() {
		return new IntValue(-this.getIValue());
	}

}
