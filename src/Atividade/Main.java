package Atividade;

public class Main {
    public static void main(String[] args) {
        String input = """
            let a = 42 + 5 - 8;
            let b = 56 + 8;
            print a + b + 6;
        """;

        Parser parser = new Parser(input.getBytes());
        parser.parse();
    }
}
