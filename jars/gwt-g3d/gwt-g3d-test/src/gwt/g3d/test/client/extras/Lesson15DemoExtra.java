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
package gwt.g3d.test.client.extras;

import javax.vecmath.Vector3f;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Extra widget for lesson 15 demo.
 */
public class Lesson15DemoExtra extends Composite {
  interface MyUiBinder extends UiBinder<Widget, Lesson15DemoExtra> {}
  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

  @UiField CheckBox chkLighting, chkSpecular, chkColor;
  @UiField TextBox txtPointX, txtPointY, txtPointZ;
  @UiField TextBox txtSpecularColorR, txtSpecularColorG, txtSpecularColorB;
  @UiField TextBox txtDiffuseColorR, txtDiffuseColorG, txtDiffuseColorB;
  @UiField TextBox txtAmbientR, txtAmbientG, txtAmbientB;
  
  public Lesson15DemoExtra() {
    initWidget(uiBinder.createAndBindUi(this));
  }
  
  /**
   * Gets whether lighting is enabled.
   */
  public boolean isLighting() {
  	return chkLighting.getValue();
  }
  
  /**
   * Gets whether to use specular highlight.
   */
  public boolean isUseSpecularMap() {
  	return chkSpecular.getValue();
  }
  
  /**
   * Gets whether to use the color map.
   */
  public boolean isUseColorMap() {
  	return chkColor.getValue();
  }
  
  /**
   * Gets the light position.
   */
  public Vector3f getPosition() {
  	return new Vector3f(parse(txtPointX), parse(txtPointY), parse(txtPointZ));
  }
  
  /**
   * Gets the light's specular color.
   */
  public Vector3f getSpecularColor() {
  	return new Vector3f(parse(txtSpecularColorR), parse(txtSpecularColorG), 
  			parse(txtSpecularColorB));
  }
  
  /**
   * Gets the light's diffuse color.
   */
  public Vector3f getDiffuseColor() {
  	return new Vector3f(parse(txtDiffuseColorR), parse(txtDiffuseColorG), 
  			parse(txtDiffuseColorB));
  }
  
  /**
   * Gets the ambient light color.
   */
  public Vector3f getAmbient() {
  	return new Vector3f(parse(txtAmbientR), parse(txtAmbientG), 
  			parse(txtAmbientB));
  }
  
  /**
   * Parse the value from the text box.
   * If fails, returns 0.
   * 
   * @param textBox
   */
  private static float parse(TextBox textBox) {
  	try {
  		return (float) Double.parseDouble(textBox.getValue());
  	} catch (NumberFormatException e) {
  		return 0;
  	}
  }
}