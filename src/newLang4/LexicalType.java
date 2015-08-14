package newLang4;

public enum LexicalType {

	LITERAL, // 文字列定数　（例：　“文字列”）
	INTVAL, // 整数定数 （例：　３）
	DOUBLEVAL, // 小数点定数 （例：　１．２）
	NAME, // 変数 （例：　i）

	IF("if"), // IF
	THEN("then"), // THEN
	ELSE("else"), // ELSE
	ELSEIF("elseif"), // ELSEIF
	ENDIF("endif"), // ENDIF
	FOR("for"), // FOR
	FORALL("forall"), // FORALL
	NEXT("next"), // NEXT
	EQ("="), // =
	LT("<"), // <
	GT(">"), // >
	LE("<=", "=<"), // <=, =<
	GE(">=", "=>"), // >=, =>
	NE("<>"), // <>
	FUNC("sub"), // SUB
	DIM("dim"), // DIM
	AS("as"), // AS
	END("end"), // END
	NL("\r\n"), // 改行
	DOT("."), // .
	WHILE("while"), // WHILE
	DO("do"), // DO
	UNTIL("until"), // UNTIL
	ADD("+"), // +
	SUB("-"), // -
	MUL("*"), // *
	DIV("/"), // /
	LP("("), // (
	RP(")"), // )
	COMMA(","), // ,
	LOOP("loop"), // LOOP
	TO("to"), // TO
	WEND("wend"), // WEND
	EOF("end of file"), // end of file
	;

	// 予約後の始まり
	private static int startOfReserved = 4;

	// enumの後のカッコの中の値をこのコンストラクタでenum自体に結ぶ
	private String[] str;

	LexicalType(String... str) {
		this.str = str;
	}

	// enumを示す文字列（コンストラクタで設定）を取得する
	String[] getStr() {
		return this.str;
	}

	// 予約語のenumの始まりの値を返す
	public static int getStartOfReserved() {
		return startOfReserved;
	}

	// ある文字が使える記号であるかどうか
	public static boolean charInSymbols(char ch) {
		switch (ch) {
		case '=':
		case '>':
		case '<':
		case '+':
		case '-':
		case '*':
		case '/':
		case '(':
		case ')':
		case ',':
		case '\r':
		case '\n':
		case '.':
			return true;
		}

		return false;
	}
}
