package gwt.g3d.resources.converter;

/**
 * An interface for parsing a raw model file into a {@link ParsedContent}.
 * 
 * @author hao1300@gmail.com
 */
public interface Parser {
	
	/**
	 * Parses the file into the a parsed content object.
	 * 
	 * @param fileContent the file content to parse.
	 * @return the parsed content
	 */
	ParsedContent parse(String fileContent) throws ParserException;
}
