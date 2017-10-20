package treeEngine.entities;

import org.lwjgl.util.vector.Vector3f;
import treeEngine.models.TexturedModel;

/**
 * This class houses the textured model and other 3d data like pos, rot, and scale.
 */
public class Entity {

    private TexturedModel model;
    private Vector3f position;
    private Vector3f rotation;
    private float scale;

    public Entity(TexturedModel model, Vector3f position, Vector3f rotation, float scale) {
        this.model = model;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public void increaseRotation(Vector3f increase) {
        rotation.x += increase.x;
        rotation.y += increase.y;
        rotation.z += increase.z;
    }

    public void increasePosition(Vector3f increase) {
        position.x += increase.x;
        position.y += increase.y;
        position.z += increase.z;
    }

    public TexturedModel getModel() {
        return model;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
