package treeEngine.rendering;

import elgin.data.Reference;
import treeEngine.models.ModelTexture;
import treeEngine.models.RawModel;
import treeEngine.models.TexturedModel;
import treeEngine.shaders.entities.EntityShader;

import java.util.ArrayList;
import java.util.List;

public class EngineManager {

    // Object inits essential for updates and others
    private static Loader loader;
    private static Renderer renderer;
    private static EntityShader entityShader;

    private static List<TexturedModel> models = new ArrayList<>();

    private static float[] vertices = {
            -0.3f, 0.5f, 0f,
            -0.3f, -0.5f, 0f,
            0.3f, -0.5f, 0f,
            0.3f, 0.5f, 0f
    };

    private static int[] indices = {
            0, 1, 3,
            3, 1, 2
    };

    private static float[] textureCoords = {
            0, 0,   0, 1,   1, 1,    1, 0
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

        RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture("monkeyFace", Reference.LOADER_TEXTURES_FOLDER));
        TexturedModel texturedModel = new TexturedModel(model, texture);
        models.add(texturedModel);
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
