package treeEngine.shaders.uniforms;

import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

public class Uniform4f extends Uniform {

    public Uniform4f(String uniformName, int programID) {
        super(uniformName, programID);
    }

    public void loadData(Vector4f value) {
        GL20.glUniform4f(uniformLocation, value.x, value.y, value.z, value.w);
    }

}
