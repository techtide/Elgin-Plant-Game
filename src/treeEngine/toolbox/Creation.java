package treeEngine.toolbox;

import elgin.data.Reference;
import org.lwjgl.util.vector.Vector3f;
import treeEngine.entities.Entity;
import treeEngine.models.ModelTexture;
import treeEngine.models.RawModel;
import treeEngine.models.TexturedModel;
import treeEngine.objLoader.ModelData;
import treeEngine.objLoader.OBJFileLoader;
import treeEngine.rendering.Loader;
import treeEngine.terrains.Terrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Creation {

    private Loader loader;

    public Creation(Loader loader) {
        this.loader = loader;
    }

    public Terrain createTerrain(String texturePath, int xPos, int zPos) {
        ModelTexture modelTexture = new ModelTexture(loader.loadTexture(texturePath, Reference.LOADER_TEXTURES_FOLDER));

        return new Terrain(xPos, zPos, loader, modelTexture);
    }

    public Entity createEntity(String modelPath, String texturePath, Vector3f position, float dx, float dy, float dz, float scale) {
        ModelData modelData = OBJFileLoader.loadOBJ(modelPath);
        RawModel model = loader.loadToVAO(modelData.getVertices(), modelData.getTextureCoords(), modelData.getNormals(), modelData.getIndices());
        ModelTexture texture = new ModelTexture(loader.loadTexture(texturePath, Reference.LOADER_TEXTURES_FOLDER));
        TexturedModel texturedModel = new TexturedModel(model, texture);
        return new Entity(texturedModel, position, new Vector3f(dx, dy, dz), scale);
    }

    public List<Entity> createScatterEntity(String modelPath, String texturePath, float dx, float dy, float dz, float scale, float range, int freq, boolean ignoreYAxis) {
        List<Entity> entities = new ArrayList<>();
        Random r = new Random();
        for(int i = 0; i < freq; i++) {
            float xPos = r.nextFloat() * range;
            float zPos = r.nextFloat() * range;

            Vector3f position = new Vector3f(xPos, 0, zPos); // Will change 0 when adding custom terrains

            if(ignoreYAxis) {
                dx = r.nextFloat() * 360;
                dz = r.nextFloat() * 360;
            }

            entities.add(createEntity(modelPath, texturePath, position, dx, dy, dz, scale));
        }

        return entities;
    }

}
