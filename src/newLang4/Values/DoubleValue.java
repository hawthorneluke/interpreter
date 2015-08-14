package newLang4.Values;

import newLang4.Value;
import newLang4.ValueType;

public class DoubleValue implements Value {

	private Double value;
	private ValueType type = ValueType.DOUBLE;

	public DoubleValue(double i)
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
		return new DoubleValue(getDValue() + v.getDValue());
	}
	
	@Override
	public Value sub(Value v) {
		return new DoubleValue(getDValue() - v.getDValue());
	}
	
	@Override
	public Value mul(Value v) {
		return new DoubleValue(getDValue() * v.getDValue());
	}
	
	@Override
	public Value div(Value v) {
		return new DoubleValue(getDValue() / v.getDValue());
	}
	
	@Override
	public boolean eq(Value v) {
		return value == v.getDValue();
	}
	
	@Override
    public boolean gt(Value v) {
		return value > v.getDValue();
	}
	
	@Override
    public boolean lt(Value v) {
		return value < v.getDValue();
	}
	
	@Override
    public boolean ge(Value v) {
		return value >= v.getDValue();
	}
	
	@Override
    public boolean le(Value v) {
		return value <= v.getDValue();
	}
	
	@Override
    public boolean ne(Value v) {
		return value != v.getDValue();
	}
	
	@Override
	public Value negative() {
		return new DoubleValue(-this.getDValue());
	}
}
