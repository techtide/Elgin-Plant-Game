package treeEngine.rendering;

import elgin.data.Reference;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;
import treeEngine.entities.Entity;
import treeEngine.entities.Light;
import treeEngine.models.ModelTexture;
import treeEngine.models.RawModel;
import treeEngine.models.TexturedModel;
import treeEngine.shaders.UniformList;
import treeEngine.shaders.entities.EntityShader;
import treeEngine.toolbox.Maths;

import java.util.HashMap;
import java.util.List;

public class EntityRenderer {

    private Matrix4f projectionMatrix;
    private EntityShader entityShader;

    public EntityRenderer(EntityShader entityShader) {
        this.entityShader = entityShader;
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
        createProjectionMatrix();
        entityShader.start();
        UniformList.entityProjectionMatrix.loadData(projectionMatrix);
        entityShader.stop();
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
     * Renders a model.
     * @param entities - List of entities to render
     */
    public void render(HashMap<TexturedModel, List<Entity>> entities) {
        for(TexturedModel model:entities.keySet()) {
            prepareTexturedModel(model);
            List<Entity> batch = entities.get(model);
            for(Entity entity: batch) {
                prepareInstance(entity);
                GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
            }
            unbindTexturedModel();
        }
    }

    /**
     * Binds texture for shaders and rendering
     * @param model - textured model containing the texture.
     */
    private void prepareTexturedModel(TexturedModel model) {
        RawModel rawModel = model.getRawModel();
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);

        ModelTexture texture = model.getTexture();
        UniformList.entityShineDamper.loadData(texture.getShineDamper());
        UniformList.entityReflectivity.loadData(texture.getReflectivty());

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());
    }

    /**
     * Finished using texture, unbinding arrays
     */
    private void unbindTexturedModel() {
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);

        GL30.glBindVertexArray(0);
    }

    /**
     * Prepares per entity
     * @param entity - Entity to render
     */
    private void prepareInstance(Entity entity) {
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotation().x, entity.getRotation().y, entity.getRotation().z, entity.getScale());
        UniformList.entityTransformationMatrix.loadData(transformationMatrix);
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

}
