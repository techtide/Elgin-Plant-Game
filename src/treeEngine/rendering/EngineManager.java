package treeEngine.rendering;

import treeEngine.models.RawModel;
import treeEngine.shaders.entities.EntityShader;

import java.util.ArrayList;
import java.util.List;

public class EngineManager {

    // Object inits essential for updates and others
    private static Loader loader;
    private static Renderer renderer;
    private static EntityShader entityShader;

    private static List<RawModel> models = new ArrayList<>();

    private static float[] vertices = {
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f
    };

    private static int[] indices = {
            0, 1, 3,
            3, 1, 2
    };

    //END of inits

    /**
     * Initiates the display, OpenGL, and any other non-loop lines
     * to run before loop
     */
    public static void init() {
        DisplayManager.createDisplay(); // Display
        loader = new Loader();
        renderer = new Renderer();
        entityShader = new EntityShader();

        models.add(loader.loadToVAO(vertices, indices));
    }

    /**
     * The running loop that updates all things that change.
     */
    public static void update() {
        //Game logic n stuff
        renderer.prepare();
        entityShader.start();

        renderer.render(models.get(0));

        entityShader.stop();
        DisplayManager.updateDisplay(); // Display
    }

    /**
     * Cleans up everything to make the exit a smooth one.
     */
    public static void stop() {
        entityShader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay(); // Display
    }

}
