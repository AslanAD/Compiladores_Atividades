package Atividade;

public enum TokenType {
    // Simbolos
    PLUS, MINUS, ASTERISK, SLASH,
    LT, GT, EQ, SEMICOLON,
    LPAREN, RPAREN, LBRACE, RBRACE,

    //"tipos"
    NUMBER, IDENT, STRING,

    //palavras-chave
    CLASS, CONSTRUCTOR, FUNCTION, METHOD,
    FIELD, STATIC, VAR, CHAR,
    BOOLEAN, VOID, TRUE, FALSE,
    NULL, THIS, LET, DO, IF,
    ELSE, RETURN, PRINT, WHILE,


    EOF
}

