package treeEngine.models;

/**
 * Class to hold a texture, later attached to a model.
 */
public class ModelTexture {

    private int textureID;

    public ModelTexture(int id) {
        this.textureID = id;
    }

    public int getTextureID() {
        return textureID;
    }
}
