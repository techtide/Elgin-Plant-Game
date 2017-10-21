package treeEngine.shaders.uniforms;

import org.lwjgl.opengl.GL20;

public class UniformBool extends Uniform {

    public UniformBool(String uniformName, int programID) {
        super(uniformName, programID);
    }

    public void loadData(boolean value) {
        GL20.glUniform1f(uniformLocation, value ? 1 : 0);
    }

}
