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

import gwt.g2d.client.math.MathHelper;

import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Tuple2f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

/**
 * Represents a basic 3D camera with basic mouse rotation support.
 * Adapted from the Camera class in by Computer Science Department, Cornell
 * University.
 * 
 * @author hao1300@gmail.com
 * @author Adam Arbree
 * @author Wenzel Jakob
 * @author Shuang Zhao
 */
public class BasicCamera extends AbstractCamera {
	/**
	 * The upper limit of the theta angle allowed when orbiting. Avoids gimbal
	 * lock
	 */
	private static final float THETA_UPPER_LIMIT = 89.0f * (float) Math.PI / 180.0f;
	private static final float THETA_LOWER_LIMIT = -THETA_UPPER_LIMIT;

	public BasicCamera() {
	}

	/**
	 * @param fov
	 *          field of view in degrees.
	 * @param aspect
	 * @param near
	 * @param far
	 */
	public BasicCamera(float fov, float aspect, float near, float far) {
		setFov(fov);
		setAspect(aspect);
		setNear(near);
		setFar(far);
	}

	/**
	 * Orbits the camera around the target.
	 * 
	 * @param lastMousePoint where x, y lie between [0, 1]
	 * @param currMousePoint where x, y lie between [0, 1]
	 */
	public void orbit(Tuple2f lastMousePoint, Tuple2f currMousePoint) {

		Vector2f mouseDelta = new Vector2f(currMousePoint);
		mouseDelta.sub(lastMousePoint);

		// Construct an arbitrary frame at the target with the z-axis the up
		// vector
		Vector3f w = new Vector3f(getUp());
		w.normalize();
		Vector3f u = nonParallelVector(w);
		Vector3f v = new Vector3f();
		v.cross(w, u);
		v.normalize();
		u.cross(v, w);
		Matrix3f basis = new Matrix3f();
		basis.setColumn(0, u);
		basis.setColumn(1, v);
		basis.setColumn(2, w);
		Matrix4f frame = new Matrix4f(basis, getTarget(), 1);
		Matrix4f frameInv = new Matrix4f();
		frameInv.invert(frame);

		// write eye in that frame
		Vector3f e = new Vector3f(getEye());
		frameInv.transform(e);

		// write e in spherical coordinates
		float r = e.length();
		float phi = (float) Math.atan2(e.y, e.x);
		float theta = (float) Math.asin(e.z / r);

		// increment phi and theta by mouse motion
		phi += -Math.PI / 2 * mouseDelta.x;
		theta += -Math.PI / 2 * mouseDelta.y;
		theta = (float) MathHelper.clamp(theta, THETA_LOWER_LIMIT, THETA_UPPER_LIMIT);

		// write e back in cartesian world coords
		e.set((float) (r * Math.cos(phi) * Math.cos(theta)), 
				(float) (r * Math.sin(phi) * Math.cos(theta)), 
				(float) (r * Math.sin(theta)));

		frame.transform(e);
		setEye(e);
	}

	/**
	 * Returns a vector that is not nearly parallel to v.
	 */
	private static Vector3f nonParallelVector(Vector3f v) {
		Vector3f u = new Vector3f();
		switch (argmin(Math.abs(v.x), Math.abs(v.y), Math.abs(v.z))) {
			case 0:
				u.x = 1;
				break;
			case 1:
				u.y = 1;
				break;
			case 2:
				u.z = 1;
				break;
		}
		return u;
	}

	/**
	 * Returns 0 if a is smallest, 1 if b is smallest, 2 if c is smallest.
	 */
	private static int argmin(double a, double b, double c) {
		return a < b ? (a < c ? 0 : 2) : (b < c ? 1 : 2);
	}
}
