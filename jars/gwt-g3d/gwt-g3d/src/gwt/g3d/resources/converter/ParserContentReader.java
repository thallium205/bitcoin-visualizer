package gwt.g3d.resources.converter;

/**
 * Buffered reader for aiding a parser to read a file.
 * 
 * @author hao1300@gmail.com
 */
public class ParserContentReader {
	private String fileContent;
	private int index;
	private StringBuilder buffer = new StringBuilder();
	private String line;
	private boolean isEofReached;
	
	public ParserContentReader(String fileContent) {
		this.fileContent = fileContent;
	}
	
	/**
	 * Read a line of text. A line is considered to be terminated by any one of a 
	 * line feed ('\n'), a carriage return ('\r'), or a carriage return followed 
	 * immediately by a linefeed.
	 */
	public final String readLine() {
		if (isEofReached) {
			line = null;
			return null;
		}
		buffer.setLength(0);
		while (true) {
			int nextByte = read();
			if (nextByte == '\n' || nextByte == '\r') {
				break;
			}
			if (nextByte == -1) {
				isEofReached = true;
				break;
			}
			buffer.append((char) nextByte);
		}
		line = buffer.toString();
		return line;
	}
	
	/**
	 * Reads the next token from the current line.
	 */
	public final String readToken() {
		if (line.isEmpty()) {
			return null;
		}
		int spaceIndex = line.indexOf(' ');
		String token;
		if (spaceIndex < 0) {
			token = line;
			line = "";
		} else {
			token = line.substring(0, spaceIndex);
			spaceIndex++;
			while (spaceIndex < line.length() && line.charAt(spaceIndex) == ' ') {
				spaceIndex++;
			}
			line = spaceIndex < line.length() ? line.substring(spaceIndex) : "";
		}
		return token;
	}
	
	/**
	 * Reads a character.
	 */
	private int read() {
		return index >= fileContent.length() ? -1 : fileContent.charAt(index++);
	}
}
