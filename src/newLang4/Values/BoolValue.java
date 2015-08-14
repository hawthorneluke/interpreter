package newLang4.Values;

import newLang4.Value;
import newLang4.ValueType;

public class BoolValue implements Value {

	private Boolean value;
	private ValueType type = ValueType.BOOL;

	public BoolValue(Boolean b)
	{
		this.value = b;
	}

	@Override
	public String getSValue() {
		return value.toString();
	}

	@Override
	public int getIValue() {
		return value == true ? 1 : 0;
	}

	@Override
	public double getDValue() {
		return value == true ? 1 : 0;
	}

	@Override
	public boolean getBValue() {
		return value;
	}

	@Override
	public ValueType getType() {
		return this.type;
	}
	
	@Override
	public Value add(Value v) {
		return null;
	}
	
	@Override
	public Value sub(Value v) {
		return null;
	}
	
	@Override
	public Value mul(Value v) {
		return null;
	}
	
	@Override
	public Value div(Value v) {
		return null;
	}
	
	@Override
	public boolean eq(Value v) {
		return value == v.getBValue();
	}
	
	@Override
    public boolean gt(Value v) {
		return value == true && v.getBValue() == false;
	}
	
	@Override
    public boolean lt(Value v) {
		return value == false && v.getBValue() == true;
	}
	
	@Override
    public boolean ge(Value v) {
		if (value == v.getBValue()) return true;
		else return value == true && v.getBValue() == false;
	}
	
	@Override
    public boolean le(Value v) {
		if (value == v.getBValue()) return true;
		else return value == false && v.getBValue() == true;
	}
	
	@Override
    public boolean ne(Value v) {
		return value != v.getBValue();
	}
	
	@Override
	public Value negative() {
		return null;
	}

}
