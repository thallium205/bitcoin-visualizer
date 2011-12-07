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

import static gwt.g3d.client.gl2.array.JsArrayUtils.toJsArrayNumber;
import gwt.g2d.client.math.Rectangle;

import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.SingularMatrixException;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

import com.google.gwt.core.client.JsArrayNumber;

/**
 * Helper class for manipulating matrix. The matrix libraries such as
 * {@link Matrix3f} and {@link Matrix4f} are stored in row-major order. However,
 * OpenGL expects column-major matrix by default. Thus, toFloatArray() will
 * return array representation of a matrix in row-major order. To obtain a
 * column-major array representation, using toFloatArrayColMajor().
 * 
 * @author hao1300@gmail.com
 * @author edwinfsmith@gmail.com
 */
public class MatrixUtils {

	/**
	 * Convert a {@link Matrix3f} matrix into a float array in row-major order.
	 */
	public static float[] toFloatArray(Matrix3f m) {
		return toFloatArrayRowMajor(m);
	}

	/**
	 * Convert a {@link Matrix3f} matrix into a float array in row-major order.
	 * 
	 * @param m
	 * @param outArray
	 *          an array of 9 elements.
	 */
	public static void toFloatArray(Matrix3f m, float[] outArray) {
		toFloatArrayRowMajor(m, outArray);
	}

	/**
	 * Convert a {@link Matrix3f} matrix into a {@link JsArrayNumber} in row-major
	 * order.
	 */
	public static JsArrayNumber toJsFloatArray(Matrix3f m) {
		return toJsFloatArrayRowMajor(m);
	}

	/**
	 * Convert a {@link Matrix3f} matrix into a float array
	 */
	public static float[] toFloatArrayRowMajor(Matrix3f m) {
		float[] outArray = new float[9];
		toFloatArrayRowMajor(m, outArray);
		return outArray;
	}

	/**
	 * Convert a {@link Matrix3f} matrix into a float array in row-major order.
	 * 
	 * @param m
	 * @param outArray
	 *          an array of 9 elements.
	 */
	public static void toFloatArrayRowMajor(Matrix3f m, float[] outArray) {
		outArray[0] = m.m00;
		outArray[1] = m.m01;
		outArray[2] = m.m02;

		outArray[3] = m.m10;
		outArray[4] = m.m11;
		outArray[5] = m.m12;

		outArray[6] = m.m20;
		outArray[7] = m.m21;
		outArray[8] = m.m22;
	}

	/**
	 * Convert a {@link Matrix3f} matrix into a {@link JsArrayNumber}.
	 */
	public static JsArrayNumber toJsFloatArrayRowMajor(Matrix3f m) {
		return toJsArrayNumber(m.m00, m.m01, m.m02, m.m10, m.m11, m.m12, m.m20,
				m.m21, m.m22);
	}

	/**
	 * Convert a {@link Matrix3f} matrix into a float array in column-major order.
	 */
	public static float[] toFloatArrayColMajor(Matrix3f m) {
		float[] outArray = new float[9];
		toFloatArrayColMajor(m, outArray);
		return outArray;
	}

	/**
	 * Convert a {@link Matrix3f} matrix into a float array in column-major order.
	 * 
	 * @param m
	 * @param outArray
	 *          an array of 9 elements.
	 */
	public static void toFloatArrayColMajor(Matrix3f m, float[] outArray) {
		outArray[0] = m.m00;
		outArray[1] = m.m10;
		outArray[2] = m.m20;

		outArray[3] = m.m01;
		outArray[4] = m.m11;
		outArray[5] = m.m21;

		outArray[6] = m.m02;
		outArray[7] = m.m12;
		outArray[8] = m.m22;
	}

	/**
	 * Convert a {@link Matrix3f} matrix into a {@link JsArrayNumber} in
	 * column-major order.
	 */
	public static JsArrayNumber toJsFloatArrayColMajor(Matrix3f m) {
		return toJsArrayNumber(m.m00, m.m10, m.m20, m.m01, m.m11, m.m21, m.m02,
				m.m12, m.m22);
	}

	/**
	 * Convert a {@link Matrix4f} matrix into a float array in row-major order.
	 */
	public static float[] toFloatArray(Matrix4f m) {
		return toFloatArrayRowMajor(m);
	}

	/**
	 * Convert a {@link Matrix4f} matrix into a float array in row-major order.
	 * 
	 * @param m
	 * @param outArray
	 *          an array of 16 elements.
	 */
	public static void toFloatArray(Matrix4f m, float[] outArray) {
		toFloatArrayRowMajor(m, outArray);
	}

	/**
	 * Convert a {@link Matrix4f} matrix into a {@link JsArrayNumber} in row-major
	 * order.
	 */
	public static JsArrayNumber toJsFloatArray(Matrix4f m) {
		return toJsFloatArrayRowMajor(m);
	}

	/**
	 * Convert a {@link Matrix4f} matrix into a float array in row-major order.
	 */
	public static float[] toFloatArrayRowMajor(Matrix4f m) {
		float[] outArray = new float[16];
		toFloatArrayRowMajor(m, outArray);
		return outArray;
	}

	/**
	 * Convert a {@link Matrix4f} matrix into a float array in row-major order.
	 * 
	 * @param m
	 * @param outArray
	 *          an array of 16 elements.
	 */
	public static void toFloatArrayRowMajor(Matrix4f m, float[] outArray) {
		outArray[0] = m.m00;
		outArray[1] = m.m01;
		outArray[2] = m.m02;
		outArray[3] = m.m03;

		outArray[4] = m.m10;
		outArray[5] = m.m11;
		outArray[6] = m.m12;
		outArray[7] = m.m13;

		outArray[8] = m.m20;
		outArray[9] = m.m21;
		outArray[10] = m.m22;
		outArray[11] = m.m23;

		outArray[12] = m.m30;
		outArray[13] = m.m31;
		outArray[14] = m.m32;
		outArray[15] = m.m33;
	}

	/**
	 * Convert a {@link Matrix4f} matrix into a {@link JsArrayNumber} in row-major
	 * order.
	 */
	public static JsArrayNumber toJsFloatArrayRowMajor(Matrix4f m) {
		return toJsArrayNumber(m.m00, m.m01, m.m02, m.m03, m.m10, m.m11, m.m12,
				m.m13, m.m20, m.m21, m.m22, m.m23, m.m30, m.m31, m.m32, m.m33);
	}

	/**
	 * Convert a {@link Matrix4f} matrix into a float array in column-major order.
	 */
	public static float[] toFloatArrayColMajor(Matrix4f m) {
		float[] outArray = new float[16];
		toFloatArrayColMajor(m, outArray);
		return outArray;
	}

	/**
	 * Convert a {@link Matrix4f} matrix into a float array in column-major order.
	 * 
	 * @param m
	 * @param outArray
	 *          an array of 16 elements.
	 */
	public static void toFloatArrayColMajor(Matrix4f m, float[] outArray) {
		outArray[0] = m.m00;
		outArray[1] = m.m10;
		outArray[2] = m.m20;
		outArray[3] = m.m30;

		outArray[4] = m.m01;
		outArray[5] = m.m11;
		outArray[6] = m.m21;
		outArray[7] = m.m31;

		outArray[8] = m.m02;
		outArray[9] = m.m12;
		outArray[10] = m.m22;
		outArray[11] = m.m32;

		outArray[12] = m.m03;
		outArray[13] = m.m13;
		outArray[14] = m.m23;
		outArray[15] = m.m33;
	}

	/**
	 * Convert a {@link Matrix4f} matrix into a {@link JsArrayNumber} in
	 * column-major order.
	 */
	public static JsArrayNumber toJsFloatArrayColMajor(Matrix4f m) {
		return toJsArrayNumber(m.m00, m.m10, m.m20, m.m30, m.m01, m.m11, m.m21,
				m.m31, m.m02, m.m12, m.m22, m.m32, m.m03, m.m13, m.m23, m.m33);
	}

	/**
	 * Outputs a matrix that creates parallel projection.
	 * 
	 * @param left
	 *          Specify the coordinates for the left vertical clipping planes.
	 * @param right
	 *          Specify the coordinates for the right vertical clipping planes.
	 * @param bottom
	 *          Specify the coordinates for the bottom horizontal clipping planes.
	 * @param top
	 *          Specify the coordinates for the top horizontal clipping planes.
	 * @param near
	 *          Specify the distances to the nearer depth clipping planes. This
	 *          value is negative if the plane is to be behind the viewer.
	 * @param far
	 *          Specify the distances to the farther depth clipping planes. This
	 *          value is negative if the plane is to be behind the viewer.
	 */
	public static Matrix4f ortho(float left, float right, float bottom,
			float top, float near, float far) {
		Matrix4f outMatrix = new Matrix4f();
		ortho(left, right, bottom, top, near, far, outMatrix);
		return outMatrix;
	}

	/**
	 * Outputs a matrix that creates parallel projection.
	 * 
	 * @param left
	 *          Specify the coordinates for the left vertical clipping planes.
	 * @param right
	 *          Specify the coordinates for the right vertical clipping planes.
	 * @param bottom
	 *          Specify the coordinates for the bottom horizontal clipping planes.
	 * @param top
	 *          Specify the coordinates for the top horizontal clipping planes.
	 * @param near
	 *          Specify the distances to the nearer depth clipping planes. This
	 *          value is negative if the plane is to be behind the viewer.
	 * @param far
	 *          Specify the distances to the farther depth clipping planes. This
	 *          value is negative if the plane is to be behind the viewer.
	 * @param outMatrix
	 *          the matrix to store the parallel projection.
	 */
	public static void ortho(float left, float right, float bottom, float top,
			float near, float far, Matrix4f outMatrix) {
		// Reference: http://www.c3dl.org/
		float tx = (left + right) / (right - left);
		float ty = (top + bottom) / (top - bottom);
		float tz = (far + near) / (far - near);

		outMatrix.m00 = 2 / (right - left);
		outMatrix.m10 = 0;
		outMatrix.m20 = 0;
		outMatrix.m30 = 0;

		outMatrix.m01 = 0;
		outMatrix.m11 = 2 / (top - bottom);
		outMatrix.m21 = 0;
		outMatrix.m31 = 0;

		outMatrix.m02 = 0;
		outMatrix.m12 = 0;
		outMatrix.m22 = -2 / (far - near);
		outMatrix.m32 = 0;

		outMatrix.m03 = tx;
		outMatrix.m13 = ty;
		outMatrix.m23 = tz;
		outMatrix.m33 = 1;
	}

	/**
	 * Creates a perspective matrix that produces a perspective projection.
	 * 
	 * @param left
	 *          Specify the coordinates for the left vertical clipping planes.
	 * @param right
	 *          Specify the coordinates for the right vertical clipping planes.
	 * @param bottom
	 *          Specify the coordinates for the bottom horizontal clipping planes.
	 * @param top
	 *          Specify the coordinates for the top horizontal clipping planes.
	 * @param near
	 *          Specify the distances to the nearer depth clipping planes. Must be
	 *          positive.
	 * @param far
	 *          Specify the distances to the farther depth clipping planes. Must
	 *          be positive.
	 */
	public static Matrix4f frustum(float left, float right, float bottom,
			float top, float near, float far) {
		Matrix4f outMatrix = new Matrix4f();
		frustum(left, right, bottom, top, near, far);
		return outMatrix;
	}

	/**
	 * Creates a perspective matrix that produces a perspective projection.
	 * 
	 * @param left
	 *          Specify the coordinates for the left vertical clipping planes.
	 * @param right
	 *          Specify the coordinates for the right vertical clipping planes.
	 * @param bottom
	 *          Specify the coordinates for the bottom horizontal clipping planes.
	 * @param top
	 *          Specify the coordinates for the top horizontal clipping planes.
	 * @param near
	 *          Specify the distances to the nearer depth clipping planes. Must be
	 *          positive.
	 * @param far
	 *          Specify the distances to the farther depth clipping planes. Must
	 *          be positive.
	 * @param outMatrix
	 *          the matrix to store the perspective projection.
	 */
	public static void frustum(float left, float right, float bottom, float top,
			float near, float far, Matrix4f outMatrix) {
		// Reference: http://www.c3dl.org/
		float A = (right + left) / (right - left);
		float B = (top + bottom) / (top - bottom);
		float C = -(far + near) / (far - near);
		float D = -(2 * far * near) / (far - near);

		outMatrix.m00 = (2 * near) / (right - left);
		outMatrix.m10 = 0;
		outMatrix.m20 = 0;
		outMatrix.m30 = 0;

		outMatrix.m01 = 0;
		outMatrix.m11 = 2 * near / (top - bottom);
		outMatrix.m21 = 0;
		outMatrix.m31 = 0;

		outMatrix.m02 = A;
		outMatrix.m12 = B;
		outMatrix.m22 = C;
		outMatrix.m32 = -1;

		outMatrix.m03 = 0;
		outMatrix.m13 = 0;
		outMatrix.m23 = D;
		outMatrix.m33 = 0;
	}

	/**
	 * Sets up a perspective projection matrix.
	 * 
	 * @param fovy
	 *          Specifies the field of view angle, in degrees, in the y direction.
	 * @param aspect
	 *          Specifies the aspect ratio that determines the field of view in
	 *          the x direction. The aspect ratio is the ratio of x (width) to y
	 *          (height).
	 * @param zNear
	 *          Specifies the distance from the viewer to the near clipping plane
	 *          (always positive).
	 * @param zFar
	 *          Specifies the distance from the viewer to the far clipping plane
	 *          (always positive).
	 */
	public static Matrix4f perspective(float fovy, float aspect, float zNear,
			float zFar) {
		Matrix4f outMatrix = new Matrix4f();
		perspective(fovy, aspect, zNear, zFar, outMatrix);
		return outMatrix;
	}

	/**
	 * Sets up a perspective projection matrix.
	 * 
	 * @param fovy
	 *          Specifies the field of view angle, in degrees, in the y direction.
	 * @param aspect
	 *          Specifies the aspect ratio that determines the field of view in
	 *          the x direction. The aspect ratio is the ratio of x (width) to y
	 *          (height).
	 * @param zNear
	 *          Specifies the distance from the viewer to the near clipping plane
	 *          (always positive).
	 * @param zFar
	 *          Specifies the distance from the viewer to the far clipping plane
	 *          (always positive).
	 * @param outMatrix
	 *          the matrix to store the perspective transformation.
	 */
	public static void perspective(float fovy, float aspect, float zNear,
			float zFar, Matrix4f outMatrix) {
		// Reference: http://www.c3dl.org/
		float top = (float) Math.tan(fovy * Math.PI / 360.0) * zNear;
		float bottom = -top;
		float left = aspect * bottom;
		float right = aspect * top;
		frustum(left, right, bottom, top, zNear, zFar, outMatrix);
	}

	/**
	 * Sets up a matrix that defines a viewing transformation.
	 * 
	 * @param eyex
	 *          Specifies the x position of the eye point.
	 * @param eyey
	 *          Specifies the y position of the eye point.
	 * @param eyez
	 *          Specifies the z position of the eye point.
	 * @param centerx
	 *          Specifies the x position of the reference point.
	 * @param centery
	 *          Specifies the y position of the reference point.
	 * @param centerz
	 *          Specifies the z position of the reference point.
	 * @param upx
	 *          Specifies the direction of the up vector.
	 * @param upy
	 *          Specifies the direction of the up vector.
	 * @param upz
	 *          Specifies the direction of the up vector.
	 */
	public static Matrix4f lookAt(float eyex, float eyey, float eyez,
			float centerx, float centery, float centerz, float upx, float upy,
			float upz) {
		Matrix4f outMatrix = new Matrix4f();
		lookAt(eyex, eyey, eyez, centerx, centery, centerz, upx, upy, upz,
				outMatrix);
		return outMatrix;
	}

	/**
	 * Sets up a matrix that defines a viewing transformation.
	 * 
	 * @param eyex
	 *          Specifies the x position of the eye point.
	 * @param eyey
	 *          Specifies the y position of the eye point.
	 * @param eyez
	 *          Specifies the z position of the eye point.
	 * @param centerx
	 *          Specifies the x position of the reference point.
	 * @param centery
	 *          Specifies the y position of the reference point.
	 * @param centerz
	 *          Specifies the z position of the reference point.
	 * @param upx
	 *          Specifies the direction of the up vector.
	 * @param upy
	 *          Specifies the direction of the up vector.
	 * @param upz
	 *          Specifies the direction of the up vector.
	 * @param outMatrix
	 *          the matrix to store the look at transformation.
	 */
	public static void lookAt(float eyex, float eyey, float eyez, float centerx,
			float centery, float centerz, float upx, float upy, float upz,
			Matrix4f outMatrix) {
		// Reference: glUtils.js from http://www.learningwebgl.com/
		LookAtTemp.EYE.set(eyex, eyey, eyez);
		LookAtTemp.CENTER.set(centerx, centery, centerz);
		LookAtTemp.UP.set(upx, upy, upz);
		LookAtTemp.Z_AXIS.sub(LookAtTemp.EYE, LookAtTemp.CENTER);
		LookAtTemp.Z_AXIS.normalize();

		LookAtTemp.X_AXIS.cross(LookAtTemp.UP, LookAtTemp.Z_AXIS);
		LookAtTemp.X_AXIS.normalize();
		LookAtTemp.Y_AXIS.cross(LookAtTemp.Z_AXIS, LookAtTemp.X_AXIS);

		outMatrix.m00 = LookAtTemp.X_AXIS.x;
		outMatrix.m01 = LookAtTemp.X_AXIS.y;
		outMatrix.m02 = LookAtTemp.X_AXIS.z;
		outMatrix.m03 = -LookAtTemp.X_AXIS.dot(LookAtTemp.EYE);

		outMatrix.m10 = LookAtTemp.Y_AXIS.x;
		outMatrix.m11 = LookAtTemp.Y_AXIS.y;
		outMatrix.m12 = LookAtTemp.Y_AXIS.z;
		outMatrix.m13 = -LookAtTemp.Y_AXIS.dot(LookAtTemp.EYE);

		outMatrix.m20 = LookAtTemp.Z_AXIS.x;
		outMatrix.m21 = LookAtTemp.Z_AXIS.y;
		outMatrix.m22 = LookAtTemp.Z_AXIS.z;
		outMatrix.m23 = -LookAtTemp.Z_AXIS.dot(LookAtTemp.EYE);

		outMatrix.m30 = 0;
		outMatrix.m31 = 0;
		outMatrix.m32 = 0;
		outMatrix.m33 = 1;
	}

	/**
	 * Transforms input point from screen space to world space.
	 * 
	 * @param winPos Specify the window coordinates to be mapped.
	 * @param model Specifies the modelview matrix
	 * @param proj Specifies the projection matrix 
	 * @param viewport Specifies the viewport
	 * @return Returns the computed object coordinates
	 */
	public static Vector3f unproject(Vector3f winPos,
			Matrix4f model, Matrix4f proj, Rectangle viewport) {
		// Normalizes the mouse coord so it lays between -1 and 1
		Vector4f vecIn = new Vector4f();
		vecIn.x = (float) ((winPos.x - viewport.getX()) * 2.0f / viewport.getWidth() - 1.0f);
		vecIn.y = (float) ((winPos.y - viewport.getY()) * 2.0f / viewport.getHeight() - 1.0f);
		vecIn.z = 2 * winPos.z - 1.0f;
		vecIn.w = 1.0f;

		// Calculates transformation inverse matrix
		Matrix4f m = new Matrix4f();
		m.mul(proj, model);
		try {
			m.invert();
		} catch (SingularMatrixException e) {
			return new Vector3f(0, 0, winPos.z);
		}

		// Multiplies input vector by inverted matrix
		m.transform(vecIn);

		Vector3f outVector = new Vector3f(vecIn.x, vecIn.y, vecIn.z);
		outVector.scale((vecIn.w == 0) ? 0 : (1f / vecIn.w));
		return outVector;
	}

	/** Temporary variables for lookAt() */
	private static final class LookAtTemp {
		static final Vector3f X_AXIS = new Vector3f(), Y_AXIS = new Vector3f(),
				Z_AXIS = new Vector3f(), EYE = new Vector3f(), CENTER = new Vector3f(),
				UP = new Vector3f();
	}
}
