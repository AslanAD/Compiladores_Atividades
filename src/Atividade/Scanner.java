package Atividade;

import java.util.HashMap;
import java.util.Map;

public class Scanner {

    private static final Map<String, TokenType> keywords;

    static {
        keywords = new HashMap<>();
        keywords.put("let", TokenType.LET);
        keywords.put("print", TokenType.PRINT);
    }

    private byte[] input;
    private int current;

    public Scanner(byte[] input) {
        this.input = input;
        this.current = 0;
    }

    private char peek() {
        if (current < input.length)
            return (char) input[current];
        return '\0';
    }

    private void advance() {
        if (current < input.length)
            current++;
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
    }

    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || Character.isDigit(c);
    }

    private void skipWhitespace() {
        while (Character.isWhitespace(peek())) {
            advance();
        }
    }

    private Token identifier() {
        int start = current;
        while (isAlphaNumeric(peek())) {
            advance();
        }
        String id = new String(input, start, current - start);
        TokenType type = keywords.get(id); // Verifica se é uma palavra-chave
        if (type == null) {
            type = TokenType.IDENT; // Se não for palavra-chave, é um identificador
        }
        return new Token(type, id);
    }

    public Token nextToken() {
        skipWhitespace();

        char ch = peek();
        if (ch == '\0') {
            return new Token(TokenType.EOF, "EOF");
        }

        if (Character.isDigit(ch)) {
            int start = current;
            while (Character.isDigit(peek())) {
                advance();
            }
            String number = new String(input, start, current - start);
            return new Token(TokenType.NUMBER, number);
        }

        if (isAlpha(ch)) {
            return identifier();
        }

        switch (ch) {
            case '+':
                advance();
                return new Token(TokenType.PLUS, "+");
            case '-':
                advance();
                return new Token(TokenType.MINUS, "-");
            case '=':
                advance();
                return new Token(TokenType.EQ, "=");
            case ';':
                advance();
                return new Token(TokenType.SEMICOLON, ";");
            default:
                throw new Error("Erro léxico no caractere: " + ch);
        }
    }
}

