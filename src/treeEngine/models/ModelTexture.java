package treeEngine.models;

/**
 * Class to hold a texture, later attached to a model.
 */
public class ModelTexture {

    private int textureID;

    private float shineDamper = 1;
    private float reflectivty = 0;

    public ModelTexture(int id) {
        this.textureID = id;
    }

    public int getTextureID() {
        return textureID;
    }

    public float getShineDamper() {
        return shineDamper;
    }

    public void setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
    }

    public float getReflectivty() {
        return reflectivty;
    }

    public void setReflectivty(float reflectivty) {
        this.reflectivty = reflectivty;
    }
}
