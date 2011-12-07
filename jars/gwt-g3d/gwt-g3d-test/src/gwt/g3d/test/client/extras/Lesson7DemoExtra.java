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
 * Extra widget for lesson 7 demo.
 */
public class Lesson7DemoExtra extends Composite {

  interface MyUiBinder extends UiBinder<Widget, Lesson7DemoExtra> {}
  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

  @UiField CheckBox chkLighting;
  @UiField TextBox txtDirectionX, txtDirectionY, txtDirectionZ;
  @UiField TextBox txtColorR, txtColorG, txtColorB;
  @UiField TextBox txtAmbientR, txtAmbientG, txtAmbientB;
  
  public Lesson7DemoExtra() {
    initWidget(uiBinder.createAndBindUi(this));
  }
  
  /**
   * Gets whether lighting is enabled.
   */
  public boolean isLighting() {
  	return chkLighting.getValue();
  }
  
  /**
   * Gets the light direction.
   */
  public Vector3f getDirection() {
  	return new Vector3f(parse(txtDirectionX), parse(txtDirectionY),
  			parse(txtDirectionZ));
  }
  
  /**
   * Gets the light color.
   */
  public Vector3f getColor() {
  	return new Vector3f(parse(txtColorR), parse(txtColorG), parse(txtColorB));
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