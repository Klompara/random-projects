package util;

public class Parser {
	private Reader reader = null;
	private Writer writer = null;

	public Parser(Reader reader, Writer writer) {
		this.reader = reader;
		this.writer = writer;
	}

	public void execute() throws Exception {
		int i;
		int index = 0;
		char ch;
		boolean openParanthes = false;
		while ((i = reader.readNextChar()) != -1) {
			ch = (char) i;
			System.out.println(ch);

			if(ch == '(' && index == 0) {
				openParanthes = true;
				writer.writeStringResult(String.valueOf(ch));
			}
			
			if (Character.isDigit(ch)) {
				writer.writeStringResult(String.valueOf(ch));
			} else {
				writer.writeStringError(String.valueOf(ch));
			}
			index++;
		}
		writer.close();
	}
}
