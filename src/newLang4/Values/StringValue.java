package newLang4.Values;

import newLang4.Value;
import newLang4.ValueType;

public class StringValue implements Value {

	private String value;
	private ValueType type = ValueType.STRING;

	public StringValue(String s)
	{
		this.value = s;
	}

	@Override
	public String getSValue() {
		return value;
	}

	@Override
	public int getIValue() {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException ex)
		{
			System.out.println("StringValue: " + this.value + " をint型に変換出来ない。");
			return 0;
		}
	}

	@Override
	public double getDValue() {
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException ex)
		{
			System.out.println("StringValue: " + this.value + " をdouble型に変換出来ない。");
			return 0;
		}
	}

	@Override
	public boolean getBValue() {
		return Boolean.parseBoolean(value);
	}

	@Override
	public ValueType getType() {
		return this.type;
	}
	
	@Override
	public Value add(Value v) {
		return new StringValue(getSValue() + v.getSValue());
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
		return value.equals(v.getSValue());
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
		return value.equals(v.getSValue());
	}
	
	@Override
    public boolean le(Value v) {
		return value.equals(v.getSValue());
	}
	
	@Override
    public boolean ne(Value v) {
		return !value.equals(v.getSValue());
	}
	
	@Override
	public Value negative() {
		return new StringValue(new StringBuilder(value).reverse().toString()); //負の文字列？文字列を逆にしようか！
	}

}
