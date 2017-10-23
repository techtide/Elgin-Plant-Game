package treeEngine.rendering;

import org.lwjgl.util.vector.Vector3f;
import treeEngine.entities.Camera;
import treeEngine.entities.Entity;
import treeEngine.entities.Light;
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
        Entity dragon = create.createEntity("dragon", "lowPolyTree", new Vector3f(10, 0, -10), 0, 0, 0, 1f);
        dragon.getModel().getTexture().setReflectivty(1);
        dragon.getModel().getTexture().setShineDamper(10);
        entities.add(dragon);

        List<Entity> tempBatch = create.createScatterEntity("fern", "fern", 0, 0, 0,
                0.9f, 400, 80, true);
        for(Entity e: tempBatch) {
            e.getModel().getTexture().setHasTransparency(true);
            e.getModel().getTexture().setUsesFakeLighting(true);
        }
        entities.addAll(tempBatch);

        sun = new Light(new Vector3f(2000, 2000, 2000), new Vector3f(1, 1, 1));

        Terrain terrain = create.createTerrain("grass", 0, -1);
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
