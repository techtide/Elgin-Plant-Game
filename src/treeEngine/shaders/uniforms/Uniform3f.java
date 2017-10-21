package treeEngine.shaders.uniforms;

import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector3f;

public class Uniform3f extends Uniform {

    public Uniform3f(String uniformName, int programID) {
        super(uniformName, programID);
    }

    public void loadData(Vector3f value) {
        GL20.glUniform3f(uniformLocation, value.x, value.y, value.z);
    }

}
