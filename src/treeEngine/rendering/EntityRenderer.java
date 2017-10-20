package treeEngine.rendering;

import elgin.data.Reference;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;
import treeEngine.entities.Entity;
import treeEngine.models.RawModel;
import treeEngine.models.TexturedModel;
import treeEngine.shaders.entities.EntityShader;
import treeEngine.toolbox.Maths;

public class EntityRenderer {

    private Matrix4f projectionMatrix;

    public EntityRenderer(EntityShader entityShader) {
        createProjectionMatrix();
        entityShader.start();
        entityShader.projectionMatrix.loadData(projectionMatrix);
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
     * @param entity - Entity to render, with transformation and model
     * @param entityShader - The entity shader used to render the entity correctly.
     */
    public void render(Entity entity, EntityShader entityShader) {
        TexturedModel texturedModel = entity.getModel();
        RawModel model = texturedModel.getRawModel();
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotation().x, entity.getRotation().y, entity.getRotation().z, entity.getScale());
        entityShader.transformationMatrix.loadData(transformationMatrix);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getTextureID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }

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
