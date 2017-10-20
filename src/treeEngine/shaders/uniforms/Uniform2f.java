package treeEngine.shaders.uniforms;

import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector2f;

public class Uniform2f extends Uniform {

    public Uniform2f(String uniformName, int programID) {
        super(uniformName, programID);
    }

    public void loadData(Vector2f value) {
        GL20.glUniform2f(uniformLocation, value.x, value.y);
    }
}
