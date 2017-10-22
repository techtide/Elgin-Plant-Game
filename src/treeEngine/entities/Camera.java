package treeEngine.entities;

import elgin.data.Reference;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

/**
 * Class to hold data for camera, to view the world
 */
public class Camera {

    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch, yaw, roll;

    public Camera() { }

    public void move() {
        float shift = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? Reference.CAM_SHIFT : 1;
        if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
            position.z -= 0.02f * shift;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
            position.x += 0.02f * shift;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
            position.x -= 0.02f * shift;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
            position.z += 0.02f * shift;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
