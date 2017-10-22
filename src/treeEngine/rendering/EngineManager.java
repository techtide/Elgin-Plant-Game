package treeEngine.rendering;

import elgin.data.Reference;
import org.lwjgl.util.vector.Vector3f;
import treeEngine.entities.Camera;
import treeEngine.entities.Entity;
import treeEngine.entities.Light;
import treeEngine.models.ModelTexture;
import treeEngine.models.RawModel;
import treeEngine.models.TexturedModel;
import treeEngine.objLoader.ModelData;
import treeEngine.objLoader.OBJFileLoader;
import treeEngine.shaders.UniformList;
import treeEngine.shaders.entities.EntityShader;

import java.util.ArrayList;
import java.util.List;

public class EngineManager {

    // Object inits essential for updates and others
    private static Loader loader;
    private static Camera camera;
    private static MasterRenderer renderer;

    private static List<Entity> entities = new ArrayList<>();
    private static Light sun;

    //END of inits

    /**
     * Initiates the display, OpenGL, and any other non-loop lines
     * to run before loop
     */
    public static void init() {
        DisplayManager.createDisplay(); // Display
        loader = new Loader();
        renderer = new MasterRenderer();
        camera = new Camera();

        ModelData modelData = OBJFileLoader.loadOBJ("dragon");
        RawModel model = loader.loadToVAO(modelData.getVertices(), modelData.getTextureCoords(), modelData.getNormals(), modelData.getIndices());
        ModelTexture texture = new ModelTexture(loader.loadTexture("lowPolyTree", Reference.LOADER_TEXTURES_FOLDER));
        texture.setShineDamper(10);
        texture.setReflectivty(0.5f);
        TexturedModel texturedModel = new TexturedModel(model, texture);
        entities.add(new Entity(texturedModel, new Vector3f(0, -0.75f, -3), new Vector3f(0, 0, 0), 0.175f));

        sun = new Light(new Vector3f(0, -5, 10), new Vector3f(1, 1, 1));
    }

    /**
     * The running loop that updates all things that change.
     */
    public static void update() {
        entities.get(0).increaseRotation(new Vector3f(0f, 0.1f, 0));
        camera.move();

        for(Entity e : entities) {
            renderer.processEntity(e);
        }

        //Game logic n stuff
        renderer.render(sun, camera);
        DisplayManager.updateDisplay(); // Display
    }

    /**
     * Cleans up everything to make the exit a smooth one.
     */
    public static void stop() {
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay(); // Display
    }

}
