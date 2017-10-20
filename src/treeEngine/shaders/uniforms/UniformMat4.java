package treeEngine.shaders.uniforms;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;

import java.nio.FloatBuffer;

public class UniformMat4 extends Uniform {

    FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

    public UniformMat4(String uniformName, int programID) {
        super(uniformName, programID);
    }

    public void loadData(Matrix4f value) {
        value.store(matrixBuffer);
        matrixBuffer.flip();
        GL20.glUniformMatrix4(uniformLocation, false, matrixBuffer);
    }

}
