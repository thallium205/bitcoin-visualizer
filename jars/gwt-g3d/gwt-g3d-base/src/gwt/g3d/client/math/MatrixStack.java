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
package gwt.g3d.client.math;

import java.util.ArrayList;
import java.util.EmptyStackException;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;

/**
 * A custom implementation of matrix stack to compensate for the lack of 
 * native matrix stack support in WebGL.
 * Initially, the matrix stack contains an identity matrix.
 * Keep in mind that matrices are stored in row-major order, so the 
 * transformation sequence may be the reverse of those in column-major order.
 */
public class MatrixStack {
	public static final MatrixStack MODELVIEW = new MatrixStack();
	public static final MatrixStack PROJECTION = new MatrixStack();
	
	/** We cache the matrices so we can reuse them in the stack without 
	 * reallocating. */
	private final ArrayList<Matrix4f> matrices = new ArrayList<Matrix4f>();
	private int currIndex;
	
	public MatrixStack() {
		matrices.add(new Matrix4f(StaticValues.IDENTITY));
	}
	
	/**
	 * Pushes a new matrix to the matrix stack.
	 * The new top matrix will be the same as the current transformation matrix.
	 */
	public void push() {
		push(get());
	}
	
	/**
	 * Pushes a new matrix to the matrix stack.
	 * The new top matrix will be the same as the given transformation matrix.
	 */
	public void push(Matrix4f matrix) {
		if (currIndex < matrices.size() - 1) {
			matrices.get(currIndex + 1).set(matrix);
		} else {
			matrices.add(new Matrix4f(matrix));
		}
		currIndex++;
	}
	
	/**
	 * Pushes a new matrix to the matrix stack.
	 * The new top matrix will be the identity matrix.
	 */
	public void pushIdentity() {
		push(StaticValues.IDENTITY);
	}
	
	/**
	 * Pops the top matrix from the matrix stack.
	 * 
	 * @throws EmptyStackException if no more matrix can be popped.
	 */
	public void pop() throws EmptyStackException {
		if (currIndex <= 0) {
			throw new EmptyStackException();
		}
		currIndex--;
	}
	
	/**
	 * Gets the matrix at the top of the transformation matrix stack.
	 */
	public Matrix4f get() {
		return matrices.get(currIndex);
	}
	
	/**
	 * Replaces the current matrix with the specified matrix.
	 * 
	 * @param matrix
	 */
	public void load(Matrix4f matrix) {
		get().set(matrix);
	}
	
	/**
	 * Replaces the current matrix with the identity matrix.
	 */
	public void loadIdentity() {
		get().set(StaticValues.IDENTITY);
	}
	
	/**
	 * Multiplies the current matrix with an orthographic matrix.
	 * 
	 * @param left Specify the coordinates for the left vertical clipping planes.
	 * @param right Specify the coordinates for the right vertical clipping 
	 * 				planes.
	 * @param bottom Specify the coordinates for the bottom horizontal clipping 
	 * 				planes.
	 * @param top Specify the coordinates for the top horizontal clipping planes.
	 * @param near Specify the distances to the nearer depth clipping planes. 
	 * 				This value is negative if the plane is to be behind the viewer.
	 * @param far Specify the distances to the farther depth clipping planes. 
	 * 				This value is negative if the plane is to be behind the viewer.
	 */
	public void ortho(float left, float right, float bottom, float top, 
			float near, float far) {
		MatrixUtils.ortho(left, right, bottom, top, near, far, 
				StaticValues.PROJECTION_MATRIX);
		mul(StaticValues.PROJECTION_MATRIX);
	}
	
	/**
	 * Multiply the current matrix by a perspective matrix.
	 * 
	 * @param left Specify the coordinates for the left vertical clipping planes.
	 * @param right Specify the coordinates for the right vertical clipping 
	 * 				planes.
	 * @param bottom Specify the coordinates for the bottom horizontal clipping 
	 * 				planes.
	 * @param top Specify the coordinates for the top horizontal clipping planes.
	 * @param near Specify the distances to the nearer depth clipping planes. 
	 * 				Must be positive.
	 * @param far Specify the distances to the farther depth clipping planes. 
	 * 				Must be positive
	 */
	public void frustum(float left, float right, float bottom, 
			float top, float near, float far) {
		MatrixUtils.frustum(left, right, bottom, top, near, far, 
				StaticValues.PROJECTION_MATRIX);
		mul(StaticValues.PROJECTION_MATRIX);
	}
	
	/**
	 * Multiplies the current transformation matrix by a perspective projection 
	 * matrix.
	 * 
	 * @param fovy Specifies the field of view angle, in degrees, in the y 
	 * 				direction.
	 * @param aspect Specifies the aspect ratio that determines the field of 
	 * 				view in the x direction. The aspect ratio is the ratio of x (width) 
	 * 				to y (height).
	 * @param zNear Specifies the distance from the viewer to the near clipping 
	 * 				plane (always positive).
	 * @param zFar Specifies the distance from the viewer to the far clipping 
	 * 				plane (always positive).
	 */
	public void perspective(float fovy, float aspect,
			float zNear, float zFar) {
		MatrixUtils.perspective(fovy, aspect, zNear, zFar, 
				StaticValues.PROJECTION_MATRIX);
		mul(StaticValues.PROJECTION_MATRIX);
	}
	
	/**
	 * Multiplies the current transformation by a matrix that defines a 
	 * viewing transformation.
	 * 
	 * @param eye Specifies the position of the eye point.
	 * @param center Specifies the position of the reference point.
	 * @param up Specifies the direction of the up vector.
	 */
	public void lookAt(Tuple3f eye, Tuple3f center, Tuple3f up) {
		lookAt(eye.x, eye.y, eye.z, center.x, center.y, center.z, up.x, up.y, up.z);
	}
	
	/**
	 * Multiplies the current transformation by a matrix that defines a 
	 * viewing transformation.
	 * 
	 * @param eyex Specifies the x position of the eye point.
	 * @param eyey Specifies the y position of the eye point.
	 * @param eyez Specifies the z position of the eye point.
	 * @param centerx Specifies the x position of the reference point.
	 * @param centery Specifies the y position of the reference point.
	 * @param centerz Specifies the z position of the reference point.
	 * @param upx Specifies the direction of the up vector.
	 * @param upy Specifies the direction of the up vector.
	 * @param upz Specifies the direction of the up vector.
	 */
	public void lookAt(float eyex, float eyey, float eyez,
			float centerx, float centery, float centerz, 
			float upx, float upy, float upz) {
		MatrixUtils.lookAt(eyex, eyey, eyez, centerx, centery, centerz, 
				upx, upy, upz, StaticValues.PROJECTION_MATRIX);
		mul(StaticValues.PROJECTION_MATRIX);
	}
	
	/**
	 * Gets the invert of the current transformation matrix.
	 * The current transformation matrix is not changed.
	 * 
	 * @param outMatrix the output matrix
	 */
	public void getInvert(Matrix4f outMatrix) {
		outMatrix.invert(get());
	}
	
	/**
	 * Gets the transpose of the current transformation matrix.
	 * The current transformation matrix is not changed.
	 * 
	 * @param outMatrix the output matrix
	 */
	public void getTranspose(Matrix4f outMatrix) {
		outMatrix.transpose(get());
	}
	
	/**
	 * Gets the transpose of the invert (usually for getting the normal matrix) 
	 * of the current transformation matrix.
	 * The current transformation matrix is not changed.
	 * 
	 * @param outMatrix the output matrix
	 */
	public void getInvertTranspose(Matrix4f outMatrix) {
		outMatrix.invert(get());
		outMatrix.transpose();
	}
	
	
	/**
	 * Gets the transpose of the invert (usually for getting the normal matrix) 
	 * of the current transformation matrix.
	 * The current transformation matrix is not changed.
	 * 
	 * @param outMatrix the output matrix
	 */
	public void getInvertTranspose(Matrix3f outMatrix) {
		get().get(outMatrix);
		outMatrix.invert();
		outMatrix.transpose();
	}
	/**
	 * Right multiply the current transformation matrix by the given matrix.
	 * 
	 * @param matrix
	 */
	public void mul(Matrix4f matrix) {
		get().mul(matrix);
	}
	
	/**
	 * Sets the current transformation matrix to be the product of
	 * leftMatrix * rightMatrix.
	 * 
	 * @param leftMatrix
	 * @param rightMatrix
	 */
	public void mul(Matrix4f leftMatrix, Matrix4f rightMatrix) {
		get().mul(leftMatrix, rightMatrix);
	}
	
	/**
	 * Left multiply the current transformation matrix by the given matrix.
	 * 
	 * @param matrix
	 */
	public void mulLeft(Matrix4f matrix) {
		get().mul(matrix, get());
	}
	
	/**
	 * Transpose the current transformation matrix.
	 */
	public void transpose() {
		get().transpose();
	}
	
	/**
	 * Inverts the current transformation matrix.
	 */
	public void invert() {
		get().invert();
	}
	
	/**
	 * Multiplies the current matrix by a translation matrix.
	 * 
	 * @param translation
	 */
	public void translate(Vector3f translation) {
		StaticValues.TRANSLATION_MATRIX.setTranslation(translation);
		mul(StaticValues.TRANSLATION_MATRIX);
	}
	
	/**
	 * Multiplies the current matrix by a translation matrix.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public void translate(float x, float y, float z) {
		StaticValues.TRANSLATION.set(x, y, z);
		translate(StaticValues.TRANSLATION);
	}
	
	/**
	 * Multiplies the current matrix by a rotation matrix.
	 * 
	 * @param quat
	 */
	public void rotate(Quat4f quat) {
		StaticValues.ROTATION_MATRIX.set(quat);
		mul(StaticValues.ROTATION_MATRIX);
	}
	
	/**
	 * Multiplies the current matrix by a rotation matrix.
	 * 
	 * @param axisAngle
	 */
	public void rotate(AxisAngle4f axisAngle) {
		StaticValues.ROTATION_MATRIX.set(axisAngle);
		mul(StaticValues.ROTATION_MATRIX);
	}
	
	/**
	 * Multiplies the current matrix by a rotation matrix.
	 * 
	 * @param angle in radian
	 * @param x
	 * @param y
	 * @param z
	 */
	public void rotate(float angle, float x, float y, float z) {
		StaticValues.AXIS_ANGLE.set(x, y, z, angle);
		rotate(StaticValues.AXIS_ANGLE);
	}
	
	/**
	 * Multiplies the current matrix by a rotation matrix.
	 *  
	 * @param angle in radian
	 */
	public void rotateX(float angle) {
		StaticValues.ROTATION_MATRIX.rotX(angle);
		mul(StaticValues.ROTATION_MATRIX);
	}
	
	/**
	 * Multiplies the current matrix by a rotation matrix.
	 *  
	 * @param angle in radian
	 */
	public void rotateY(float angle) {
		StaticValues.ROTATION_MATRIX.rotY(angle);
		mul(StaticValues.ROTATION_MATRIX);
	}
	
	/**
	 * Multiplies the current matrix by a rotation matrix.
	 *  
	 * @param angle in radian
	 */
	public void rotateZ(float angle) {
		StaticValues.ROTATION_MATRIX.rotZ(angle);
		mul(StaticValues.ROTATION_MATRIX);
	}
	
	/**
	 * Multiplies the current matrix by a general scaling matrix.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public void scale(float x, float y, float z) {
		StaticValues.SCALE_MATRIX.m00 = x;
		StaticValues.SCALE_MATRIX.m11 = y;
		StaticValues.SCALE_MATRIX.m22 = z;
		mul(StaticValues.SCALE_MATRIX);
	}
	
	/** Promoted local variables to avoid reallocation. */
	private static final class StaticValues {
		private static final Matrix4f IDENTITY = new Matrix4f();
		private static final Matrix4f TRANSLATION_MATRIX = new Matrix4f();
		private static final Vector3f TRANSLATION = new Vector3f();
		private static final Matrix4f ROTATION_MATRIX = new Matrix4f();
		private static final AxisAngle4f AXIS_ANGLE = new AxisAngle4f();
		private static final Matrix4f SCALE_MATRIX = new Matrix4f();
		private static final Matrix4f PROJECTION_MATRIX = new Matrix4f();  
		
		static {
			IDENTITY.setIdentity();
			TRANSLATION_MATRIX.setIdentity();
			ROTATION_MATRIX.setIdentity();
			SCALE_MATRIX.setIdentity();
		}
	}
}
