package newLang4.Values;

import newLang4.Value;
import newLang4.ValueType;

public class VoidValue implements Value {

	private ValueType type = ValueType.VOID;

	public VoidValue()
	{
	}

	@Override
	public String getSValue() {
		return null;
	}

	@Override
	public int getIValue() {
		return 0;
	}

	@Override
	public double getDValue() {
		return 0;
	}

	@Override
	public boolean getBValue() {
		return false;
	}

	@Override
	public ValueType getType() {
		return this.type;
	}
	
	@Override
	public Value add(Value v) {
		return this;
	}
	
	@Override
	public Value sub(Value v) {
		return this;
	}
	
	@Override
	public Value mul(Value v) {
		return this;
	}
	
	@Override
	public Value div(Value v) {
		return this;
	}
	
	@Override
	public boolean eq(Value v) {
		return v.getType() == type; //‘Šè‚àvoid‚È‚Ì‚©H
	}
	
	@Override
    public boolean gt(Value v) {
		return false;
	}
	
	@Override
    public boolean lt(Value v) {
		return false;
	}
	
	@Override
    public boolean ge(Value v) {
		return false;
	}
	
	@Override
    public boolean le(Value v) {
		return false;
	}
	
	@Override
    public boolean ne(Value v) {
		return v.getType() != type; //‘Šè‚Ívoid‚¶‚á‚È‚¢‚Ì‚©H
	}
	
	@Override
	public Value negative() {
		return null;
	}

}
