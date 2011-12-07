/*
 * Copyright 2009 Hao Nguyen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package gwt.g3d.resources;

import gwt.g3d.client.gl2.GL2;
import gwt.g3d.client.shader.AbstractShader;
import gwt.g3d.client.shader.ShaderException;
import gwt.g3d.resources.client.ShaderResource;

import java.net.URL;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.dev.util.Util;
import com.google.gwt.resources.ext.AbstractResourceGenerator;
import com.google.gwt.resources.ext.ResourceContext;
import com.google.gwt.resources.ext.ResourceGeneratorUtil;
import com.google.gwt.user.rebind.SourceWriter;
import com.google.gwt.user.rebind.StringSourceWriter;

/**
 * Generator for {@link ShaderResource}.
 * 
 * @author hao1300@gmail.com
 */
public final class ShaderResourceGenerator extends AbstractResourceGenerator {

	@Override
	public String createAssignment(TreeLogger logger, ResourceContext context,
			JMethod method) throws UnableToCompleteException {
		URL[] resources = ResourceGeneratorUtil.findResources(logger, context,
        method);

    if (resources.length != 2) {
      logger.log(TreeLogger.ERROR, "Exactly two resources must be specified",
          null);
      throw new UnableToCompleteException();
    }
    
		SourceWriter sw = new StringSourceWriter();
    sw.println("new " + ShaderResource.class.getName() + "() {");
    sw.indent();
    
    sw.println("public String getVertexShaderSource() {");
    sw.indent();
    String vertexSource = Generator.escape(Util.readURLAsString(resources[0]));
    sw.println("return \"" + vertexSource + "\";"); 
    sw.outdent();
    sw.println("}");
    
    sw.println("public String getFragmentShaderSource() {");
    sw.indent();
    String fragmentSource = Generator.escape(Util.readURLAsString(resources[1]));
    sw.println("return \"" + fragmentSource + "\";"); 
    sw.outdent();
    sw.println("}");
    
    sw.println(String.format("public %s createShader(%s gl) throws %s {",
    		 AbstractShader.class.getName(), 
    		 GL2.class.getName(), 
    		 ShaderException.class.getName()));
    sw.indent();
    sw.println(String.format("%s shader = %s;", 
    		AbstractShader.class.getName(), 
    		createAbstractShader(logger, context, method)));
    sw.println("shader.init(gl);");
    sw.println("return shader;");
    sw.outdent();
    sw.println("}");

    sw.println("public String getName() {");
    sw.indent();
    sw.println("return \"" + method.getName() + "\";");
    sw.outdent();
    sw.println("}");

    sw.outdent();
    sw.println("}");

    return sw.toString();
	}
	
	/**
	 * Creates an assignment for {@link AbstractShader}.
	 */
	private String createAbstractShader(TreeLogger logger, 
			ResourceContext context, JMethod method) 
			throws UnableToCompleteException {
		SourceWriter sw = new StringSourceWriter();
		sw.println("new " + AbstractShader.class.getName() + "() {");
		sw.indent();

		sw.println(String.format("protected void initImpl() throws %s {",
    		ShaderException.class.getName()));
    sw.indent();
    sw.println("initProgram(getVertexShaderSource(), getFragmentShaderSource());"); 
    sw.outdent();
    sw.println("}");
    
    sw.outdent();
    sw.println("}");
    return sw.toString();
	}
}
