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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

/**
 * Extra widget for ModelDemo.
 */
public class ModelDemoExtra extends Composite {
	public enum ModelType {
		FISH,
		PLANT,
		PTERANADON,
	}
	
  interface MyUiBinder extends UiBinder<Widget, ModelDemoExtra> {}
  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

  @UiField RadioButton radioFish, radioPlant, radioPteranadon;
  
  private List<HandlerRegistration> handlers = new ArrayList<HandlerRegistration>(3);
  
  public ModelDemoExtra() {
    initWidget(uiBinder.createAndBindUi(this));
  }
  
  public void addModelSelectedHandler(ClickHandler handler) {
  	handlers.add(radioFish.addClickHandler(handler));
  	handlers.add(radioPlant.addClickHandler(handler));
  	handlers.add(radioPteranadon.addClickHandler(handler));
  }
  
  public void removeAllModelSelectedHandler() {
  	for (HandlerRegistration handler : handlers) {
  		handler.removeHandler();
  	}
  	handlers.clear();
  }
  
  public ModelType getSelectedModel() {
  	if (radioFish.getValue()) {
  		return ModelType.FISH;
  	}
  	if (radioPlant.getValue()) {
  		return ModelType.PLANT;
  	}
  	return ModelType.PTERANADON;
  }
}