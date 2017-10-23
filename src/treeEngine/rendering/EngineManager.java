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
import treeEngine.terrains.Terrain;
import treeEngine.toolbox.Creation;

import java.util.ArrayList;
import java.util.List;

public class EngineManager {

    // Object inits essential for updates and others
    private static Loader loader;
    private static Creation create;
    private static Camera camera;
    private static MasterRenderer renderer;

    private static List<Entity> entities = new ArrayList<>();
    private static List<Terrain> terrains = new ArrayList<>();
    private static Light sun;

    //END of inits

    //TODO- Write creation class to easily make entities, forest n such

    /**
     * Initiates the display, OpenGL, and any other non-loop lines
     * to run before loop
     */
    public static void init() {
        DisplayManager.createDisplay(); // Display
        loader = new Loader();
        create = new Creation(loader);
        renderer = new MasterRenderer();
        camera = new Camera();

        //dragon, lowPolyTree
        Entity dragon = create.createEntity("dragon", "lowPolyTree", new Vector3f(10, 10, 10), 0, 0, 0, 1f);
        dragon.getModel().getTexture().setReflectivty(1);
        dragon.getModel().getTexture().setShineDamper(10);
        entities.add(dragon);

        ModelData grassModelData = OBJFileLoader.loadOBJ("fern");
        RawModel grassModel = loader.loadToVAO(grassModelData.getVertices(), grassModelData.getTextureCoords(), grassModelData.getNormals(), grassModelData.getIndices());
        ModelTexture grassTexture = new ModelTexture(loader.loadTexture("fern", Reference.LOADER_TEXTURES_FOLDER));
        grassTexture.setHasTransparency(true);
        grassTexture.setUsesFakeLighting(true);
        TexturedModel grassTexturedModel = new TexturedModel(grassModel, grassTexture);
        entities.add(new Entity(grassTexturedModel, new Vector3f(5, 0, 5), new Vector3f(0, 0, 0), 1));

        sun = new Light(new Vector3f(2000, 2000, 2000), new Vector3f(1, 1, 1));

        Terrain terrain = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("grass", Reference.LOADER_TEXTURES_FOLDER)));
        terrains.add(terrain);
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

        for(Terrain t : terrains) {
            renderer.processTerrain(t);
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
