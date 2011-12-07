package gwt.g3d.resources.converter.obj;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import gwt.g3d.resources.converter.ParsedContent;
import gwt.g3d.resources.converter.Parser;
import gwt.g3d.resources.converter.ParserContentReader;
import gwt.g3d.resources.converter.ParserException;

import java.util.HashMap;
import java.util.Map;

import javax.vecmath.Point3i;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector4d;

/**
 * Parser for an obj file.
 * 
 * @author hao1300@gmail.com
 */
public class ObjParser implements Parser {
	
	@Override
	public ParsedContent parse(String fileContent) throws ParserException {
		return new ObjParserImpl().parse(fileContent);
	}
	
	/**
	 * Implementation of obj file parser.
	 */
	private static class ObjParserImpl {
		private static final Map<String, Processor> PROCESSORS = 
				new HashMap<String, Processor>();
		
		/**
		 * Implementation of obj file parsing.
		 * 
		 * @param file
		 * @return
		 * @throws ParserException
		 */
		public ParsedContent parse(String fileContent) throws ParserException {
			ObjContent content = new ObjContent();
			try {
				ParserContentReader reader = new ParserContentReader(fileContent);
				while (reader.readLine() != null) {
					Processor processor = PROCESSORS.get(reader.readToken());
					if (processor != null) {
						processor.process(reader, content);
					}
				}
			} catch (IndexOutOfBoundsException e) {
				throw new ParserException("Error while reading a obj file " + e.getMessage());
			}
			return content;
		}

		static {
			PROCESSORS.put("v", new Processor() {
				@Override
				public void process(ParserContentReader reader, ObjContent content)
						throws ParserException {
					Vector4d vertex = new Vector4d(
							parseDouble(reader.readToken()),
							parseDouble(reader.readToken()),
							parseDouble(reader.readToken()), 
							1);
					String wToken = reader.readToken();
					if (wToken != null) {
						vertex.w = parseDouble(wToken);
					}
					content.addVertex(vertex);
				}
			});
			PROCESSORS.put("vn", new Processor() {
				@Override
				public void process(ParserContentReader reader, ObjContent content)
						throws ParserException {
					content.addNormal(new Vector3d(
							parseDouble(reader.readToken()),
							parseDouble(reader.readToken()),
							parseDouble(reader.readToken())));
				}
			});
			PROCESSORS.put("vt", new Processor() {
				@Override
				public void process(ParserContentReader reader, ObjContent content)
						throws ParserException {
					Vector3d texCoord = new Vector3d(
							parseDouble(reader.readToken()),
							parseDouble(reader.readToken()), 
							0);
					String wToken = reader.readToken();
					if (wToken != null) {
						texCoord.z = parseDouble(wToken);
					}
					content.addTextureCoord(texCoord);
				}
			});
			PROCESSORS.put("f", new Processor() {
				@Override
				public void process(ParserContentReader reader, ObjContent content)
						throws ParserException {
					String token;
					while ((token = reader.readToken()) != null) {
						String[] values = token.split("/");
						if (values.length < 1 || values.length > 3) {
							throw new ParserException("Incorrect face index");
						}
						Point3i faceIndex = new Point3i(parseInt(values[0]), -1, -1);
						if (values.length > 1 && !values[1].equals("")) { 
							faceIndex.y =	parseInt(values[1]);
						}
						if (values.length > 2 && !values[2].equals("")) { 
							faceIndex.z =	parseInt(values[2]);
						}
						content.addFace(faceIndex);
					}
				}
			});
		}
	}
	
	/**
	 * Interface for processing a line of an obj file in the reader.
	 */
	private static interface Processor {
		/**
		 * Process the current line in the reader and put the parsed content into
		 * content.
		 * 
		 * @param reader
		 * @param content
		 * @throws ParserException
		 */
		void process(ParserContentReader reader, ObjContent content) 
				throws ParserException;
	}
}
