package treeEngine.rendering;

import elgin.data.Reference;
import org.lwjgl.util.vector.Vector3f;
import treeEngine.entities.Camera;
import treeEngine.entities.Entity;
import treeEngine.models.ModelTexture;
import treeEngine.models.RawModel;
import treeEngine.models.TexturedModel;
import treeEngine.shaders.entities.EntityShader;

import java.util.ArrayList;
import java.util.List;

public class EngineManager {

    // Object inits essential for updates and others
    private static Loader loader;
    private static EntityRenderer renderer;
    private static EntityShader entityShader;
    private static Camera camera;

    private static List<Entity> entities = new ArrayList<>();

    private static float[] vertices = {
            -0.5f,0.5f,-0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,0.5f,-0.5f,

            -0.5f,0.5f,0.5f,
            -0.5f,-0.5f,0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,0.5f,0.5f,

            0.5f,0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,0.5f,0.5f,

            -0.5f,0.5f,-0.5f,
            -0.5f,-0.5f,-0.5f,
            -0.5f,-0.5f,0.5f,
            -0.5f,0.5f,0.5f,

            -0.5f,0.5f,0.5f,
            -0.5f,0.5f,-0.5f,
            0.5f,0.5f,-0.5f,
            0.5f,0.5f,0.5f,

            -0.5f,-0.5f,0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f

    };

    private static float[] textureCoords = {

            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0


    };

    private static int[] indices = {
            0,1,3,
            3,1,2,
            4,5,7,
            7,5,6,
            8,9,11,
            11,9,10,
            12,13,15,
            15,13,14,
            16,17,19,
            19,17,18,
            20,21,23,
            23,21,22

    };

    //END of inits

    /**
     * Initiates the display, OpenGL, and any other non-loop lines
     * to run before loop
     */
    public static void init() {
        DisplayManager.createDisplay(); // Display
        loader = new Loader();
        entityShader = new EntityShader();
        renderer = new EntityRenderer(entityShader);
        camera = new Camera();

        RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture("hqdefault", Reference.LOADER_TEXTURES_FOLDER));
        TexturedModel texturedModel = new TexturedModel(model, texture);
        entities.add(new Entity(texturedModel, new Vector3f(0, 0, -5), new Vector3f(0, 0, 0), 1f));
    }

    /**
     * The running loop that updates all things that change.
     */
    public static void update() {
        entities.get(0).increaseRotation(new Vector3f(0.5f, 0.5f, 0));
        camera.move();

        //Game logic n stuff
        renderer.prepare();
        entityShader.start();
        entityShader.loadCameraViewMatrix(camera);

        renderer.render(entities.get(0), entityShader);

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
