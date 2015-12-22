package SpaceRacing;

public class Vec {

	public float x;
	public float y;
	public float z;

	public Vec(float f, float g, float h) {
		x = f;
		y = g;
		z = h;
	}

	public static void normalize(Vec vec) {
		float length = length(vec.x, vec.y, vec.z);
		vec.x = vec.x / length;
		vec.y = vec.y / length;
		vec.z = vec.z / length;
	}

	public static void rotate(Vec vec, float theta) {
		float px = (float) (vec.x * Math.cos(theta) - vec.y * Math.sin(theta));
		float py = (float) (vec.x * Math.sin(theta) + vec.y * Math.cos(theta));
		vec.x = px;
		vec.y = py;
	}

	public static float length(float x, float y, float z) {
		return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
	}

	public static float angle(float ux, float uy, float uz, float vx, float vy, float vz) {
		float numerator = ux * vx + uy * vy + uz * vz;
		float denominator = length(ux, uy, uz) * length(vx, vy, vz);
		return (float) Math.acos(numerator / denominator);
	}

	public static float distance(float ax, float ay, float az, float bx, float by, float bz) {
		return (float) Math.sqrt(Math.pow((ax - bx), 2) + Math.pow((ay - by), 2) + Math.pow((az - bz), 2));
	}

}