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

    private EntityShader entityShader;

    public EntityRenderer(EntityShader entityShader, Matrix4f projectionMatrix) {
        this.entityShader = entityShader;
        entityShader.start();
        UniformList.entityProjectionMatrix.loadData(projectionMatrix);
        entityShader.stop();
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
        if(texture.isHasTransparency()) {
            MasterRenderer.disableCulling();
        }
        UniformList.entityUsesFakeLighting.loadData(texture.isUsesFakeLighting());
        UniformList.entityShineDamper.loadData(texture.getShineDamper());
        UniformList.entityReflectivity.loadData(texture.getReflectivty());

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());
    }

    /**
     * Finished using texture, unbinding arrays
     */
    private void unbindTexturedModel() {
        MasterRenderer.enableCulling();
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

}
