package com.project.duo.util;

public class Vector3 {
	
	private float x, y, z;
	
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Vector3(Vector3 vect) {
		x = vect.x;
		y = vect.y;
		z = vect.z;
	}
	
	
}
