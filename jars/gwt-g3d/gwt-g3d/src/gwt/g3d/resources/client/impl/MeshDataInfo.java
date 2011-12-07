/*
 * Copyright 2010 Hao Nguyen
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
package gwt.g3d.resources.client.impl;

import gwt.g3d.client.gl2.enums.DataType;

/**
 * Stores the data type and data size of each data array. 
 * 
 * @author hao1300@gmail.com
 */
public class MeshDataInfo {
	private DataType positionDataType = DataType.FLOAT;
	private int positionDataSize = 4;
	
	private DataType normalDataType = DataType.FLOAT;
	private int normalDataSize = 3;
	private boolean isNormalized;
	
	private DataType texCoordDataType = DataType.FLOAT;
	private int texCoordDataSize = 3;
	
	public void setPositionDataType(DataType positionDataType) {
		this.positionDataType = positionDataType;
	}
	
	public DataType getPositionDataType() {
		return positionDataType;
	}
	
	public void setPositionDataSize(int positionDataSize) {
		this.positionDataSize = positionDataSize;
	}
	
	public int getPositionDataSize() {
		return positionDataSize;
	}
	
	public void setNormalDataType(DataType normalDataType) {
		this.normalDataType = normalDataType;
	}
	
	public DataType getNormalDataType() {
		return normalDataType;
	}
	
	public void setNormalDataSize(int normalDataSize) {
		this.normalDataSize = normalDataSize;
	}
	
	public int getNormalDataSize() {
		return normalDataSize;
	}
	
	public void setNormalized(boolean isNormalized) {
		this.isNormalized = isNormalized;
	}
	
	public boolean isNormalized() {
		return isNormalized;
	}
	
	public void setTexCoordDataType(DataType texCoordDataType) {
		this.texCoordDataType = texCoordDataType;
	}
	
	public DataType getTexCoordDataType() {
		return texCoordDataType;
	}
	
	public void setTexCoordDataSize(int texCoordDataSize) {
		this.texCoordDataSize = texCoordDataSize;
	}
	
	public int getTexCoordDataSize() {
		return texCoordDataSize;
	}
}
