/*
 * Copyright 2010 Google Inc.
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
package gwt.g3d.resources.client;

import gwt.g3d.client.gl2.enums.DataType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies the data type and data size of the position array.
 * This annotation can be used for {@link ExternalMeshResource}.
 * 
 * @author hao1300@gmail.com
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PositionArrayInfo {
	
	/**
	 * Data type of the position array.
	 */
	DataType dataType() default DataType.FLOAT;
	
	/**
	 * Size of each element in the position array, for example, if the position
	 * array contain data for (x, y, z), then data size should be 3.
	 */
	int dataSize() default 3;
}
