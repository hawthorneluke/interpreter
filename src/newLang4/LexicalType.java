package newLang4;

public enum LexicalType {

	LITERAL, // ������萔�@�i��F�@�g������h�j
	INTVAL, // �����萔 �i��F�@�R�j
	DOUBLEVAL, // �����_�萔 �i��F�@�P�D�Q�j
	NAME, // �ϐ� �i��F�@i�j

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
	NL("\r\n"), // ���s
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

	// �\���̎n�܂�
	private static int startOfReserved = 4;

	// enum�̌�̃J�b�R�̒��̒l�����̃R���X�g���N�^��enum���̂Ɍ���
	private String[] str;

	LexicalType(String... str) {
		this.str = str;
	}

	// enum������������i�R���X�g���N�^�Őݒ�j���擾����
	String[] getStr() {
		return this.str;
	}

	// �\����enum�̎n�܂�̒l��Ԃ�
	public static int getStartOfReserved() {
		return startOfReserved;
	}

	// ���镶�����g����L���ł��邩�ǂ���
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
