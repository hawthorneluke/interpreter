package newLang4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import newLang4.Values.DoubleValue;
import newLang4.Values.IntValue;
import newLang4.Values.StringValue;

public class LexicalAnalyzerImpl implements LexicalAnalyzer {

	// 空白の宣言
	private static char space[] = { ' ', '\t' };

	// 読みすぎたときに戻すためのキュー
	private int byteQueue[] = new int[100];
	private int byteQueueLength = 0;
	private int byteQueuePointer = 0;
	private int currentByte = 0;

	private boolean startOfLine = true;
	
	private int line = 1;

	private List<LexicalUnit> unitList = new ArrayList<LexicalUnit>();

	private BufferedReader br;

	public LexicalAnalyzerImpl(InputStreamReader isr) {
		BufferedReader br = new BufferedReader(isr);
		this.br = br;
	}

	// 次のLexicalUnitを取得
	@Override
	public LexicalUnit get() {
		if (unitList.size() > 0) {
			LexicalUnit unit = unitList.remove(unitList.size() - 1);
			return unit;
		} else {
			int byt;
			boolean inComment = false;

			while (true) {
				byt = getByte();
				if (byt == -1)
					return new LexicalUnit(LexicalType.EOF); // EOF

				char ch = (char) byt;

				// ただの空白なら無視
				if (containsSpace(ch)) // IGNORE
				{
					continue;
				}
				else if (startOfLine) {
					if (ch == '\r' || ch == '\n') {
						inComment = false;
						continue;
					}
					else if (inComment == false && ch != ';') {
						startOfLine = false;
					}
				}

				if (inComment) // IN COMMENT
				{
					if (ch == '\n') {
						inComment = false; // COMMENT END
						if (!startOfLine) {
							startOfLine = true;
							return new LexicalUnit(LexicalType.NL);
						}
					}
					else
						continue;
				} else if (ch == ';') // COMMENT START
				{
					inComment = true;
					continue;
				}
				// 文字列
				else if (ch == '"') // LITERAL
				{
					return getLiteral();
				}
				// 数字
				else if (ch >= '0' && ch <= '9') // NUMBER
				{
					return getNumber(String.valueOf(ch));
				}
				// 変数名か予約語
				else if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')
						|| ch == ')')// NAME
				{
					return getName(String.valueOf(ch));
				}
				// 記号である予約語
				else if (LexicalType.charInSymbols(ch)) // 記号
				{
					return getSymbol(ch);
				} else {
					System.out.println("処理出来ない文字： " + ch + "(" + byt
							+ ")がありました。");
					return null;
				}
			}
		}
	}

	public LexicalUnit getLiteral() {
		LexicalType type = LexicalType.LITERAL;
		int byt;
		String str = "";
		boolean literalEscaping = false;

		while (true) {
			byt = getByte();
			if (byt == -1) // EOF
			{
				System.out.println("Literal文字列を読み込みながらファイルが終わった(EOF)。");
				ungetByte(-1);
				Value value = new StringValue(str);
				return new LexicalUnit(type, value);
			}

			char ch = (char) byt;

			if (ch == '"') // 文字列の終わり
			{
				if (literalEscaping) // エスケープしてるなら
				{
					str += ch;
					literalEscaping = false;
				} else {
					break;
				}

			} else if (ch == '\\') // エスケープの始まり
			{
				if (!literalEscaping) {
					literalEscaping = true;
				} else {
					str += ch;
					literalEscaping = false;
				}
			} else {
				if (literalEscaping) { // \?
					switch (ch) {
					case 'n':
						str += "\n";
						break;
					case 't':
						str += "\t";
						break;
					}
					literalEscaping = false;
				} else {
					str += ch;
				}
			}
		}

		Value value = new StringValue(str);

		return new LexicalUnit(type, value);
	}

	public LexicalUnit getNumber(String str) {
		LexicalType type = LexicalType.INTVAL; // doubleになる可能性はある
		int byt;

		while (true) {
			byt = getByte();
			if (byt == -1) // EOF
			{
				ungetByte(-1);
				break;
			}

			char ch = (char) byt;

			if (ch >= '0' && ch <= '9') {
				str += ch;
			} else if (ch == '.') // 小数点が現れたなら
			{
				if (type == LexicalType.INTVAL) {
					type = LexicalType.DOUBLEVAL;
					str += ch;
				} else {
					// .が2回でエラー
					System.out.println("数字を読み込みながら小数点が2個以上あった。");
				}
			} else {
				ungetByte(byt);
				break;
			}
		}

		Value value;
		if (type == LexicalType.INTVAL)
			value = new IntValue(Integer.parseInt(str));
		else
			value = new DoubleValue(Double.parseDouble(str));

		return new LexicalUnit(type, value);
	}

	public LexicalUnit getName(String str) {
		LexicalType type = LexicalType.NAME; // 予約語になる可能性はある
		int byt;

		while (true) {
			byt = getByte();
			if (byt == -1) // EOF
			{
				ungetByte(-1);
				break;
			}

			char ch = (char) byt;

			if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')
					|| (ch >= '0' && ch <= '9')) {
				str += ch;
			} else {
				ungetByte(byt);
				break;
			}
		}

		type = getNameType(str);

		Value value = new StringValue(str);

		return new LexicalUnit(type, value);
	}

	public LexicalUnit getSymbol(char ch) {
		LexicalType type = LexicalType.NAME;
		int byt = 0;
		String str = "";

		while (true) {

			if (LexicalType.charInSymbols(ch)) // 予約語である記号なら
			{
				str += ch;
				List<LexicalType> types = getNameTypes(str);
				if (types.size() == 1) {
					if (types.get(0) != LexicalType.NAME) {
						type = types.get(0);
						break;
					}
				}
			} else {
				ungetByte(byt);
				type = getNameType(str);
				break;
			}
			
			byt = getByte();
			if (byt == -1) // EOF
			{
				ungetByte(-1);
				break;
			}

			ch = (char) byt;
		}

		if (type == LexicalType.NL)
			startOfLine = true;

		Value value = new StringValue(str);

		return new LexicalUnit(type, value);
	}

	// 予約語のどれに当たるかを見つける
	LexicalType getNameType(String str) {
		LexicalType type = LexicalType.NAME;
		boolean found = false;
		for (int i = LexicalType.getStartOfReserved(); i < LexicalType.values().length; i++) {
			LexicalType t = LexicalType.values()[i];
			for (String s : t.getStr()) {
				if (str.toLowerCase().equals(s.toLowerCase())) {
					type = t;
					found = true;
					break;
				}
			}
			if (found)
				break;
		}

		return type;
	}

	List<LexicalType> getNameTypes(String str) {
		List<LexicalType> types = new ArrayList<LexicalType>();
		for (int i = LexicalType.getStartOfReserved(); i < LexicalType.values().length; i++) {
			LexicalType t = LexicalType.values()[i];
			for (String s : t.getStr()) {
				if (s.toLowerCase().contains(str.toLowerCase())) {
					types.add(t);
				}
			}
		}

		if (types.size() == 0)
			types.add(LexicalType.NAME);

		return types;
	}

	// ファイルかキューから次の文字を返す
	private int getByte() {
		if (byteQueueLength == 0) {
			try {
				currentByte = br.read();
				if (currentByte == (int)'\n') line++;
				return currentByte;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		} else {
			currentByte = byteQueue[byteQueuePointer];
			byteQueuePointer--;
			if (byteQueuePointer < 0)
				byteQueuePointer += byteQueue.length;
			byteQueueLength--;
			return currentByte;
		}
	}

	// 読み込んだ文字をまた次に読み込めるようにキューに戻す
	private void ungetByte(int b) {
		byteQueuePointer++;
		byteQueuePointer %= byteQueue.length;
		byteQueue[byteQueuePointer] = b;
		byteQueueLength++;
	}

	// 文字は空白であるかどうかを返す
	private boolean containsSpace(char ch) {
		for (char s : space) {
			if (ch == s)
				return true;
		}
		return false;
	}
	
	public int getLine() {
		return line;
	}
	
	//構文違いで最近ungetしたbyteを文字列として表示
	public String getCharQueue() {
		String str = "";
		for (int i = 0; i < byteQueueLength; i++) {
			str += byteQueue[i];
		}
		return str;
	}
	
	@Override
	public String syntaxError(String str) {
		return "Syntax Error on line " + getLine() + " near \"" + getCharQueue() + "\": " + str;
	}

	@Override
	public boolean expect(LexicalType type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unget(LexicalUnit token) {
		unitList.add(token);
	}

}
