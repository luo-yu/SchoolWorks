package SpaceRacing;


public class Car {
		public Vec position;
		public Vec velocity;
		public Vec acceleration;
		public Vec facing;
		public boolean b = false;
		float maxSpeed, speed;
		public float rotationAcceleration;
		public boolean reverse;
		public float rotation = 0;
		public int lapNum = 1;
		public int lapPhase = 0;
		
		public Car(Vec position, float mass) {
			reverse = false;
			velocity = new Vec(0f, 0f, 0f);
			facing = new Vec(0f, 1f, 0f);
			acceleration = new Vec(0f, 0f, 0f);
			this.position = position;
			maxSpeed = 35f;
			speed = 0f;
			rotationAcceleration = 0f;
		}
		
		public void update(float dt) {
			rotation = Vec.angle(facing.x, facing.y, facing.z, (new Vec(0, 1, 0)).x, (new Vec(0, 1, 0)).y, (new Vec(0, 1, 0)).z);
			if(facing.x > 0) rotation *= -1;
			// Keep rotational acceleration in a certain range
			if(rotationAcceleration > 3) rotationAcceleration = 3;
			if(rotationAcceleration < -3) rotationAcceleration = -3;
			
			// Apply acceleration to velocity every time step
			velocity.x += acceleration.x * dt;
			velocity.y += acceleration.y * dt;
			
			// Truncate velocity components to keep at or under max speed
			speed = Vec.length(velocity.x, velocity.y, velocity.z);
			if(speed > maxSpeed) {
				velocity.x = (velocity.x / speed) * maxSpeed;
				velocity.y = (velocity.y / speed) * maxSpeed;
			}
			// Apply rotational acceleration
			Vec.rotate(velocity, rotationAcceleration * dt);
			
			// Apply velocity to position every time step
			if(!reverse) {
				position.x += velocity.x * dt;
				position.y += velocity.y * dt;
			}
			// Apply negative velocity to position if gear is in reverse
			else {
				position.x -= velocity.x * dt;
				position.y -= velocity.y * dt;
			}
			
			if(velocity.x != 0) {
				facing.x = (velocity.x / Vec.length(velocity.x, velocity.y, velocity.z));
			}
			if(velocity.y != 0) {
				facing.y = (velocity.y / Vec.length(velocity.x, velocity.y, velocity.z));
			}
			Vec.normalize(facing);
			
			// Slow down over time (friction, letting go of gas)
			rotationAcceleration *= (1 - 5*dt);
			acceleration.x *= (1 - 10*dt);
			acceleration.y *= (1 - 10*dt);
			velocity.x *= (1 - dt);
			velocity.y *= (1 - dt);
		    if (lapPhase == 5) {
		    	lapPhase = 0;
		    	lapNum++;
		    }
			// Brakes
			if(b) {
				velocity.x *= (1 - dt);
				velocity.y *= (1 - dt);
			}
		}
	}
