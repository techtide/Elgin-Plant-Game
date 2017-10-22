package treeEngine.rendering;

import elgin.data.Reference;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import treeEngine.entities.Entity;
import treeEngine.models.ModelTexture;
import treeEngine.models.RawModel;
import treeEngine.models.TexturedModel;
import treeEngine.shaders.UniformList;
import treeEngine.shaders.terrains.TerrainShader;
import treeEngine.terrains.Terrain;
import treeEngine.toolbox.Maths;

import java.util.List;

public class TerrainRenderer {

    private TerrainShader terrainShader;

    public TerrainRenderer(TerrainShader terrainShader, Matrix4f projectionMatrix) {
        this.terrainShader = terrainShader;
        terrainShader.start();
        UniformList.terrainProjectionMatrix.loadData(projectionMatrix);
        UniformList.universalAmbientLight.loadData(Reference.UNIVERSAL_AMBIENT_LIGHT);
        terrainShader.stop();
    }

    public void render(List<Terrain> terrains) {
        for(Terrain t: terrains) {
            prepareTerrain(t);
            loadModelMatrix(t);
            GL11.glDrawElements(GL11.GL_TRIANGLES, t.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
            unbindTexturedModel();
        }
    }

    /**
     * Binds texture for shaders and rendering
     * @param terrain - terrain containing the texture.
     */
    private void prepareTerrain(Terrain terrain) {
        RawModel rawModel = terrain.getModel();
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);

        ModelTexture texture = terrain.getTexture();
        UniformList.terrainShineDamper.loadData(texture.getShineDamper());
        UniformList.terrainReflectivity.loadData(texture.getReflectivty());

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
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
     * Prepares per terrain
     * @param terrain - Terrain to render
     */
    private void loadModelMatrix(Terrain terrain) {
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(new Vector3f(terrain.getX(), 0, terrain.getZ()), 0, 0, 0, 1);
        UniformList.terrainTransformationMatrix.loadData(transformationMatrix);
    }


}
