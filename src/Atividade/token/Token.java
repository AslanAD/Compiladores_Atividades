package Atividade;

public class Token {
    final TokenType type;
    final String lexeme;
    final int line;

    public Token(TokenType type, String lexeme, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.line = line;
    }

    private boolean isSymbol(String lexeme) {
        return "+-*/&|~<>=(){}[];,".contains(lexeme);
    }

    @Override
    public String toString() {
        String categoria = type.toString().toLowerCase();
        String valor = lexeme;

        if (isSymbol(lexeme)) {
            categoria = "symbol";
            switch (valor) {
                case ">" -> valor = "&gt;";
                case "<" -> valor = "&lt;";
                case "\"" -> valor = "&quot;";
                case "&" -> valor = "&amp;";
            }
        } else if (categoria.equals("number")) {
            categoria = "integerConstant";
        } else if (categoria.equals("ident")) {
            categoria = "identifier";
        } else if (categoria.equals("string")) {
            categoria = "stringConstant";
        } else {
            categoria = "keyword";
        }
        return "<" + categoria + "> " + valor + " </" + categoria + ">";
    }
}
