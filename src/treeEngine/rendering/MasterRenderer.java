package treeEngine.rendering;

import com.sun.prism.ps.Shader;
import elgin.data.Reference;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import treeEngine.entities.Camera;
import treeEngine.entities.Entity;
import treeEngine.entities.Light;
import treeEngine.models.TexturedModel;
import treeEngine.shaders.UniformList;
import treeEngine.shaders.entities.EntityShader;
import treeEngine.shaders.terrains.TerrainShader;
import treeEngine.terrains.Terrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterRenderer {

    private EntityRenderer entityRenderer;
    private EntityShader entityShader = new EntityShader();

    private TerrainRenderer terrainRenderer;
    private TerrainShader terrainShader = new TerrainShader();

    private Matrix4f projectionMatrix;

    private HashMap<TexturedModel, List<Entity>> entities = new HashMap<>();
    private List<Terrain> terrains = new ArrayList<>();

    public MasterRenderer() {
        enableCulling();

        createProjectionMatrix();
        entityRenderer = new EntityRenderer(entityShader, projectionMatrix);
        terrainRenderer = new TerrainRenderer(terrainShader, projectionMatrix);
    }

    /**
     * Enables culling.
     */
    public static void enableCulling() {
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
    }

    /**
     * Disables culling.
     */
    public static void disableCulling() {
        GL11.glDisable(GL11.GL_CULL_FACE);
    }

    /**
     * Prepares the window for rendering
     */
    public void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(Reference.DEFAULT_BACKGROUND_COLOR.x, Reference.DEFAULT_BACKGROUND_COLOR.y, Reference.DEFAULT_BACKGROUND_COLOR.z, Reference.DEFAULT_BACKGROUND_COLOR.w); // sets default background to AQUA
    }

    /**
     * Renders scene, handles all rendering here.
     * @param sun - Light source in scene
     * @param camera - Camera that views scene
     */
    public void render(Light sun, Camera camera) {
        prepare();
        entityShader.start();

        UniformList.entityLightPosition.loadData(sun.getPosition());
        UniformList.entityLightColor.loadData(sun.getColor());
        entityShader.loadCameraViewMatrix(camera);
        entityRenderer.render(entities);

        entityShader.stop();

        terrainShader.start();

        UniformList.terrainLightPosition.loadData(sun.getPosition());
        UniformList.terrainLightColor.loadData(sun.getColor());
        terrainShader.loadCameraViewMatrix(camera);
        terrainRenderer.render(terrains);

        terrainShader.stop();

        entities.clear();
        terrains.clear();
    }

    /**
     * Adds a terrain to the batch
     * @param terrain - terrain to render
     */
    public void processTerrain(Terrain terrain) {
        terrains.add(terrain);
    }

    public void processEntity(Entity entity) {
        TexturedModel entityModel = entity.getModel();
        List<Entity> batch = entities.get(entityModel);
        if(batch != null) {
            batch.add(entity);
        } else {
            List<Entity> newBatch = new ArrayList<>();
            newBatch.add(entity);
            entities.put(entityModel, newBatch);
        }
    }

    /**
     * Creates matrix for perspective + camera
     */
    private void createProjectionMatrix() {
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(Reference.FOV / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = Reference.FAR_PLANE - Reference.NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((Reference.FAR_PLANE + Reference.NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * Reference.NEAR_PLANE * Reference.FAR_PLANE) / frustum_length);
        projectionMatrix.m33 = 0;
    }

    /**
     * Cleans up rendering.
     */
    public void cleanUp() {
        entityShader.cleanUp();
        terrainShader.cleanUp();
    }
}
