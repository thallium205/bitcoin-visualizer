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
package gwt.g3d.client.camera;

import gwt.g3d.client.math.MatrixUtils;

import javax.vecmath.Matrix4f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;

/**
 * An abstract implementation of the {@link Camera} interface in 3D space.
 * 
 * @author hao1300@gmail.com
 */
public class AbstractCamera implements Camera {
	private final Vector3f eye = new Vector3f(0, 0, -5);
	private final Vector3f target = new Vector3f(); 
	private final Vector3f up = new Vector3f(0, 1, 0);
	private final Matrix4f projectionMatrix = new Matrix4f();
	private final Matrix4f viewMatrix = new Matrix4f();
	
	private float fov, aspect, near, far;
	private boolean viewChanged, projChanged;
	
	/**
	 * Gets the aspect ratio of the camera.
	 */
	public float getAspect() {
		return aspect;
	}

	/**
	 * Gets the vector to the eye position.
	 */
	public Vector3f getEye() {
		return new Vector3f(eye);
	}

	/**
	 * Gets the vector to the eye position.
	 *  
	 * @param outEye the tuple to store the eye position.
	 */
	public void getEye(Tuple3f outEye) {
		outEye.set(eye);
	}

	/**
	 * Gets the offset of the eye from the target.
	 */
	public Vector3f getEyeOffset() {
		Vector3f offset = new Vector3f();
		getEyeOffset(offset);
		return offset;
	}

	/**
	 * Gets the offset of the eye from the target.
	 *
	 * @param outEyeOffset the tuple to store the eye offset.
	 */
	public void getEyeOffset(Tuple3f outEyeOffset) {
		outEyeOffset.sub(eye, target);
	}
	
	/**
	 * Gets the far clipping plane distance.
	 */
	public float getFar() {
		return far;
	}
	
	/**
	 * Gets the field of view in degrees.
	 */
	public float getFov() {
		return fov;
	}

	/**
	 * Gets the field of view in radians.
	 */
	public float getFovRadian() {
		return (float) Math.toRadians(fov);
	}

	/**
	 * Gets the near clipping plane distance.
	 */
	public float getNear() {
		return near;
	}

	@Override
	public Matrix4f getProjectionMatrix() {
		Matrix4f projMatrix = new Matrix4f();
		getProjectionMatrix(projMatrix);
		return projMatrix;
	}

	@Override
	public void getProjectionMatrix(Matrix4f outMatrix) {
		if (projChanged) {
			MatrixUtils.perspective(fov, aspect, near, far, projectionMatrix);
			projChanged = false;
		}
		outMatrix.set(projectionMatrix);
	}

	/**
	 * Gets the camera's target.
	 */
	public Vector3f getTarget() {
		return new Vector3f(target);
	}

	/**
	 * Gets the camera's target.
	 * 
	 * @param outTarget the tuple to store the camera's target.
	 */
	public void getTarget(Tuple3f outTarget) {
		outTarget.set(target);
	}
	
	/**
	 * Gets the camera's up vector.
	 */
	public Vector3f getUp() {
		return new Vector3f(up);
	}

	/**
	 * Gets the camera's up vector.
	 * 
	 * @param outUp the tuple to store the up vector.
	 */
	public void getUp(Tuple3f outUp) {
		outUp.set(up);
	}

	/**
	 * Gets the viewing direction, that is, the direction from the eye to the
	 * target.
	 */
	public Vector3f getViewDir() {
		Vector3f viewDir = new Vector3f();
		getViewDir(viewDir);
		return viewDir;
	}

	/**
	 * Gets the viewing direction, that is, the direction from the eye to the
	 * target.
	 * 
	 * @param outViewDir the tuple to store the viewing direction.
	 */
	public void getViewDir(Tuple3f outViewDir) {
		outViewDir.sub(target, eye);
	}

	@Override
	public Matrix4f getViewMatrix() {
		Matrix4f viewMatrix = new Matrix4f();
		getViewMatrix(viewMatrix);
		return viewMatrix;
	}
	
	@Override
	public void getViewMatrix(Matrix4f outMatrix) {
		if (viewChanged) {
			MatrixUtils.lookAt(eye.x, eye.y, eye.z, target.x, target.y, target.z, 
					up.x, up.y, up.z, viewMatrix);
			viewChanged = false;
		}
		outMatrix.set(viewMatrix);
	}
	
	@Override
	public Matrix4f getViewProjectionMatrix() {
		Matrix4f viewProjMatrix = new Matrix4f();
		getViewProjectionMatrix(viewProjMatrix);
		return viewProjMatrix;
	}

	@Override
	public void getViewProjectionMatrix(Matrix4f outMatrix) {
		getProjectionMatrix(outMatrix);
		getViewMatrix(viewMatrix);
		outMatrix.mul(viewMatrix);
	}

	/**
	 * Sets the aspect ratio.
	 * 
	 * @param aspect
	 */
	public void setAspect(float aspect) {
		this.aspect = aspect;
		projChanged = true;
	}

	/**
	 * Sets the aspect ratio using a known width and height.
	 * 
	 * @param width
	 * @param height
	 */
	public void setAspect(float width, float height) {
		setAspect(width / height);
	}

	/**
	 * @see #setEye(Tuple3f)
	 */
	public void setEye(float eyex, float eyey, float eyez) {
		this.eye.set(eyex, eyey, eyez);
		viewChanged = true;
	}

	/**
	 * Sets the camera's eye position.
	 * 
	 * @param eye
	 */
	public void setEye(Tuple3f eye) {
		setEye(eye.x, eye.y, eye.z);
	}

	/**
	 * @see #setEyeOffset(Tuple3f)
	 */
	public void setEyeOffset(float offsetX, float offsetY, float offsetZ) {
		this.eye.x = this.target.x + offsetX;
		this.eye.y = this.target.y + offsetY;
		this.eye.z = this.target.z + offsetZ;
		viewChanged = true;
	}

	/**
	 * Sets the offset of the eye from the target. This will override the
	 * current eye position, and should only be called after target is set.
	 * 
	 * @param offset
	 */
	public void setEyeOffset(Tuple3f offset) {
		setEyeOffset(offset.x, offset.y, offset.z);
	}

	/**
	 * Sets the far clipping plane distance.
	 * 
	 * @param far
	 */
	public void setFar(float far) {
		this.far = far;
		projChanged = true;
	}

	/**
	 * Sets the field of view in degrees.
	 * 
	 * @param fov
	 */
	public void setFov(float fov) {
		this.fov = fov;
		projChanged = true;
	}

	/**
	 * Sets the field of view in radians.
	 * 
	 * @param fov
	 */
	public void setFovRadian(float fov) {
		setFov((float) Math.toDegrees(fov));
	}

	/**
	 * Sets the near clipping plane distance.
	 * 
	 * @param near
	 */
	public void setNear(float near) {
		this.near = near;
		projChanged = true;
	}

	/**
	 * @see #setTarget(Tuple3f)
	 */
	public void setTarget(float targetX, float targetY, float targetZ) {
		this.target.set(targetX, targetY, targetZ);
		viewChanged = true;
	}

	/**
	 * Sets the camera's target.
	 * 
	 * @param target
	 */
	public void setTarget(Tuple3f target) {
		setTarget(target.x, target.y, target.z);
	}

	/**
	 * @see #setUp(Tuple3f)
	 */
	public void setUp(float upX, float upY, float upZ) {
		this.up.set(upX, upY, upZ);
		viewChanged = true;
	}

	/**
	 * Sets the camera's up vector.
	 * 
	 * @param up
	 */
	public void setUp(Tuple3f up) {
		setUp(up.x, up.y, up.z);
	}
}
