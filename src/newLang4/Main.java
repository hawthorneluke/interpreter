package newLang4;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import newLang4.Nodes.*;

public class Main {

	public static void main(String[] args) {
	       FileInputStream fin = null;
	       LexicalAnalyzer lex;
	        Environment		env;
	        Node			prog;
	  
	        try {
	            fin = new FileInputStream("basic1.bas");
	        }
	        catch(Exception e) {
	            System.out.println("file not found");
	            System.exit(-1);
	        }
	        lex = new LexicalAnalyzerImpl(new InputStreamReader(fin));
	        env = new Environment(lex);
	        
	        prog = (Node) new Program(env);
	        if (prog != null && prog.parse()) {
	        	System.out.println(prog); //�\���؂��o�͂���
	        	System.out.println("\n------------\n");
	        	prog.getValue(); //���s����
	        }
	        else System.out.println("Syntax Error");
	}

}
