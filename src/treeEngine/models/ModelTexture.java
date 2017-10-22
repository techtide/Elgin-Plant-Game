package treeEngine.models;

/**
 * Class to hold a texture, later attached to a model.
 */
public class ModelTexture {

    private int textureID;

    private float shineDamper = 1;
    private float reflectivty = 0;

    private boolean hasTransparency = false;
    private boolean usesFakeLighting = false;

    public ModelTexture(int id) {
        this.textureID = id;
    }

    public int getTextureID() {
        return textureID;
    }

    public boolean isUsesFakeLighting() {
        return usesFakeLighting;
    }

    public void setUsesFakeLighting(boolean usesFakeLighting) {
        this.usesFakeLighting = usesFakeLighting;
    }

    public boolean isHasTransparency() {
        return hasTransparency;
    }

    public void setHasTransparency(boolean hasTransparency) {
        this.hasTransparency = hasTransparency;
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
