package gwt.g3d.resources.converter;

import gwt.g3d.resources.client.impl.MeshDataInfo;

import java.io.IOException;
import java.io.Writer;

/**
 * An interface for a parsed content that can be exported in Json format.
 * 
 * @author hao1300@gmail.com
 */
public interface ParsedContent {
	
	/**
	 * Exports the parsed content as Json format.
	 * 
	 * @param meshDataInfo information about the data type and data size of
	 * 			  each data array.
	 * @param writer the writer to write to when exporting.
	 */
	void export(MeshDataInfo meshDataInfo, Writer writer) throws IOException;
}
