package treeEngine.shaders.uniforms;

import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector2f;

public class Uniform1i extends Uniform {

    public Uniform1i(String uniformName, int programID) {
        super(uniformName, programID);
    }

    public void loadData(int value) {
        GL20.glUniform1i(uniformLocation, value);
    }

}
