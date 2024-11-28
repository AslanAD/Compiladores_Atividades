package Atividade;

public class Main {
    public static void main(String[] args) {
        String input = """
              if (x < 0) {
                  // prints the sign
                  let sign = "negative";
              }
              """;

        Scanner scan = new Scanner(input.getBytes());
        System.out.println("<tokens>");
        for (Token tk = scan.nextToken(); tk.type != TokenType.EOF; tk = scan.nextToken()) {
            System.out.println(tk);
        }
        System.out.println("</tokens>");
    }
}
