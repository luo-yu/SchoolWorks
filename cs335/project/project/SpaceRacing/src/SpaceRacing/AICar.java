package SpaceRacing;

public class AICar extends Car {
	
	public Vec[] track = new Vec[12];
	private Vec steering = new Vec(0f, 0f, 0f);
	public Vec currentTarget = new Vec(0f, 0f, 0f);
	public int targetIndex = 0;
	public AICar(Vec position, float mass, float maxSpeed, int carNumber) {
		super(position, mass);
		fillTrack(carNumber);
		currentTarget = track[0];
		this.maxSpeed = maxSpeed;
	}
	
	@Override
	public void update(float dt) {
		calculateSteering();
		velocity.x = velocity.x + steering.x * dt;
		velocity.y = velocity.y + steering.y * dt;
		velocity.z = velocity.z + steering.z * dt;
		
		position.x = position.x + velocity.x * dt;
		position.y = position.y + velocity.y * dt;
		position.z = position.z + velocity.z * dt;
		
		if(velocity.x != 0) {
			facing.x = (velocity.x / Vec.length(velocity.x, velocity.y, velocity.z));
		}
		if(velocity.y != 0) {
			facing.y = (velocity.y / Vec.length(velocity.x, velocity.y, velocity.z));
		}
		rotation = Vec.angle(facing.x, facing.y, facing.z, (new Vec(0, 1, 0)).x, (new Vec(0, 1, 0)).y, (new Vec(0, 1, 0)).z);
		if(facing.x > 0) rotation *= -1;
	}
	
	private void calculateSteering() {
		if(Vec.distance(position.x, position.y, position.z,  currentTarget.x, currentTarget.y, currentTarget.z) < 20) {
			targetIndex++;
			if(targetIndex > 11) targetIndex = 0;
			currentTarget = track[targetIndex];
		}
		Vec desiredVelocity = new Vec(0f, 0f, 0f);
		Vec tempVec = new Vec(currentTarget.x - position.x, currentTarget.y - position.y, currentTarget.z - position.z);
		Vec.normalize(tempVec);
		desiredVelocity.x = tempVec.x * maxSpeed;
		desiredVelocity.y = tempVec.y * maxSpeed;
		desiredVelocity.z = tempVec.z * maxSpeed;
		steering.x = desiredVelocity.x - velocity.x;
		steering.y = desiredVelocity.y - velocity.y;
		steering.z = desiredVelocity.z - velocity.z;
	}
	
	public void fillTrack(int carNumber) {
		
	}
	
}