package newLang4;

public interface LexicalAnalyzer {
    public LexicalUnit get();
    public boolean expect(LexicalType type);
    public void unget(LexicalUnit token);  
    public String syntaxError(String str);
}
