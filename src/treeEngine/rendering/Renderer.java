package treeEngine.rendering;

import elgin.data.Reference;
import treeEngine.models.RawModel;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

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
     * @param model - Model to be rendered.
     */
    public void render(RawModel model) {
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }

}
