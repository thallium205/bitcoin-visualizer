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

import gwt.g2d.resources.ExternalImageResourceGenerator;
import gwt.g2d.resources.client.ImageElementResource;
import gwt.g3d.client.gl2.enums.TextureMagFilter;
import gwt.g3d.client.gl2.enums.TextureMinFilter;
import gwt.g3d.client.gl2.enums.TextureWrapMode;
import gwt.g3d.client.texture.Texture2D;
import gwt.g3d.resources.client.GenerateMipmap;
import gwt.g3d.resources.client.MagFilter;
import gwt.g3d.resources.client.MinFilter;
import gwt.g3d.resources.client.Texture2DResource;
import gwt.g3d.resources.client.WrapMode;
import gwt.g3d.resources.client.impl.AbstractExternalTexture2DResource;
import gwt.g3d.resources.client.impl.AbstractTexture2DResource;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.ext.AbstractResourceGenerator;
import com.google.gwt.resources.ext.ResourceContext;
import com.google.gwt.user.rebind.SourceWriter;
import com.google.gwt.user.rebind.StringSourceWriter;

/**
 * Generator for {@link AbstractExternalTexture2DResource}.
 * 
 * @author hao1300@gmail.com
 */
public final class ExternalTexture2DResourceGenerator 
		extends AbstractResourceGenerator {

	@Override
	public String createAssignment(TreeLogger logger, ResourceContext context,
			JMethod method) throws UnableToCompleteException {
		
		SourceWriter sw = new StringSourceWriter();
		sw.println(String.format("new %s() {",
				AbstractExternalTexture2DResource.class.getName()));
		sw.indent();
		
		// Creates method: void getTexture(ResourceCallback<Texture2DResource>);
		sw.println(String.format("public void getTexture(%s<%s> textureResource) {",
				ResourceCallback.class.getName(), 
				Texture2DResource.class.getName()));
		sw.indent();
		String externalImageResourceObjectSrc = new ExternalImageResourceGenerator()
				.createAssignment(logger, context, method);
		sw.println(String.format("getTexture(%s, textureResource);", 
				externalImageResourceObjectSrc));
		sw.outdent();
		sw.println("}");
		
		// Creates method: void onImageLoaded(ImageElementResource,
		//			ResourceCallback<Texture2DResource>);
		sw.println(String.format(
				"protected void onImageLoaded(%s imageResource, %s<%s> textureResource) {",
				ImageElementResource.class.getName(),
				ResourceCallback.class.getName(), 
				Texture2DResource.class.getName()));
		sw.indent();
		sw.println(String.format("textureResource.onSuccess(%s);", 
				createAbstractTexture2DImageResource(logger, context, method)));
		sw.outdent();
		sw.println("}");
		
		// Creates method: String getName();
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
	 * Creates an assignment for an {@link AbstractTexture2DResource} object.
	 */
	private String createAbstractTexture2DImageResource(TreeLogger logger, 
			ResourceContext context, JMethod method) 
			throws UnableToCompleteException {
		
		SourceWriter sw = new StringSourceWriter();
		sw.println(String.format("new %s(imageResource) {", 
				AbstractTexture2DResource.class.getName()));
		sw.indent();
		
		// Creates method: void setTextureParameters(Texture2D);
		String texture2DClassName = Texture2D.class.getName();
		sw.println(String.format(
				"protected void setTextureParameters(%s texture2d) {", 
				texture2DClassName));
		sw.indent();
		
		// Sets up the parameters as declared.
		{
			MagFilter magFilter = method.getAnnotation(MagFilter.class);
			TextureMagFilter textureMagFilter = magFilter != null ?
					magFilter.value() : TextureMagFilter.LINEAR;
			sw.println(String.format("texture2d.setMagFilter(%s.%s);",
					TextureMagFilter.class.getName(), textureMagFilter));
		}

		{
			MinFilter minFilter = method.getAnnotation(MinFilter.class);
			TextureMinFilter textureMinFilter = minFilter != null ?
					minFilter.value() : TextureMinFilter.LINEAR;
			sw.println(String.format("texture2d.setMinFilter(%s.%s);",
					TextureMinFilter.class.getName(), textureMinFilter));
		}
		
		{
			WrapMode wrapMode = method.getAnnotation(WrapMode.class);
			if (wrapMode != null) {
				sw.println(String.format("texture2d.setWrapMode(%s.%s);",
						TextureWrapMode.class.getName(), wrapMode.value()));
			}
		}
		
		if (method.getAnnotation(GenerateMipmap.class) != null) {
			sw.println("texture2d.generateMipmap();");
		}
		sw.outdent();
		sw.println("}");
		
		// Creates method: String getName();
		sw.println("public String getName() {");
		sw.indent();
		sw.println("return \"" + method.getName() + "\";");
		sw.outdent();
		sw.println("}");
		
		sw.outdent();
		sw.println("}");
		return sw.toString();
	}
}
