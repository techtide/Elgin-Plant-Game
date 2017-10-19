package treeEngine.rendering;

import elgin.data.Reference;
import org.lwjgl.opengl.GL13;
import treeEngine.models.RawModel;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import treeEngine.models.TexturedModel;

public class Renderer {

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
     * @param texturedModel - Textured model to be rendered.
     */
    public void render(TexturedModel texturedModel) {
        RawModel model = texturedModel.getRawModel();
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getTextureID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }

}
