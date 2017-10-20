package treeEngine.shaders.uniforms;

import org.lwjgl.opengl.GL20;

public class Uniform1f extends Uniform {

    public Uniform1f(String uniformName, int programID) {
        super(uniformName, programID);
    }

    public void loadData(float value) {
        GL20.glUniform1f(uniformLocation, value);
    }
}
