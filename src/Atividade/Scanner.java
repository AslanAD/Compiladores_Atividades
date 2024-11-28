package Atividade;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Scanner {
    private final byte[] input;
    private int current = 0;
    private int line = 1;
    private static final Map<String, TokenType> keywords; // Declaracao da variavel palavras chave

    // Inicializacao palavras chave
    static {
        keywords = new HashMap<>();
        keywords.put("while", TokenType.WHILE);
        keywords.put("class", TokenType.CLASS);
        keywords.put("constructor", TokenType.CONSTRUCTOR);
        keywords.put("function", TokenType.FUNCTION);
        keywords.put("method", TokenType.METHOD);
        keywords.put("field", TokenType.FIELD);
        keywords.put("static", TokenType.STATIC);
        keywords.put("var", TokenType.VAR);
        keywords.put("char", TokenType.CHAR);
        keywords.put("boolean", TokenType.BOOLEAN);
        keywords.put("void", TokenType.VOID);
        keywords.put("true", TokenType.TRUE);
        keywords.put("false", TokenType.FALSE);
        keywords.put("null", TokenType.NULL);
        keywords.put("this", TokenType.THIS);
        keywords.put("let", TokenType.LET);
        keywords.put("do", TokenType.DO);
        keywords.put("if", TokenType.IF);
        keywords.put("else", TokenType.ELSE);
        keywords.put("return", TokenType.RETURN);
        keywords.put("print", TokenType.PRINT); // Adicionando print
    }

    public Scanner(byte[] input) {
        this.input = input;
    }

    private char peek() {
        return current < input.length ? (char) input[current] : 0;
    }

    private void advance() {
        current++;
    }

    public Token nextToken() {
        skipWhitespace();
        char ch = peek();

        if (ch == 0) {
            return new Token(TokenType.EOF, "", line);
        }

        if (Character.isDigit(ch)) {
            return number();
        }

        if (Character.isLetter(ch)) {
            return identifier();
        }

        switch (ch) {
            case '+':
                advance();
                return new Token(TokenType.PLUS, "+", line);
            case '-':
                advance();
                return new Token(TokenType.MINUS, "-", line);
            case '*':
                advance();
                return new Token(TokenType.ASTERISK, "*", line);
            case '/':
                advance();
                return new Token(TokenType.SLASH, "/", line);
            case '"':
                return string();
            case '<':
                advance();
                return new Token(TokenType.LT, "<", line);
            case '>':
                advance();
                return new Token(TokenType.GT, ">", line);
            case '=':
                advance();
                return new Token(TokenType.EQ, "=", line);
            case ';':
                advance();
                return new Token(TokenType.SEMICOLON, ";", line);
            case '(':
                advance();
                return new Token(TokenType.LPAREN, "(", line);
            case ')':
                advance();
                return new Token(TokenType.RPAREN, ")", line);
            // Adicionar mais casos se preciso
            default:
                advance();
                return new Token(TokenType.IDENT, String.valueOf(ch), line);
        }
    }

    private void skipWhitespace() {
        char ch = peek();
        while (ch == ' ' || ch == '\r' || ch == '\t' || ch == '\n') {
            if (ch == '\n') {
                line++;
            }
            advance();
            ch = peek();
        }
    }

    private Token number() {
        int start = current;
        while (Character.isDigit(peek())) {
            advance();
        }
        String value = new String(input, start, current - start, StandardCharsets.UTF_8);
        return new Token(TokenType.NUMBER, value, line);
    }

    private Token identifier() {
        int start = current;
        while (Character.isLetterOrDigit(peek())) {
            advance();
        }
        String value = new String(input, start, current - start, StandardCharsets.UTF_8);
        TokenType type = keywords.getOrDefault(value, TokenType.IDENT);
        return new Token(type, value, line);
    }

    private Token string() {
        advance(); // Ignora o caractere de abertura
        int start = current;
        while (peek() != '"' && peek() != 0) {
            advance();
        }
        String value = new String(input, start, current - start, StandardCharsets.UTF_8);
        advance(); // Ignora o caractere de fechamento]
        return new Token(TokenType.STRING, value, line);
    }
}
