package treeEngine.shaders.uniforms;

import org.lwjgl.opengl.GL20;

public abstract class Uniform {

    protected int uniformLocation;
    protected String uniformName;
    protected int programID;

    public Uniform(String uniformName, int programID) {
        this.uniformLocation = GL20.glGetUniformLocation(programID, uniformName);
        this.programID = programID;
        this.uniformName = uniformName;
    }

    public int getUniformLocation() {
        return uniformLocation;
    }

    public String getUniformName() {
        return uniformName;
    }

    public int getProgramID() {
        return programID;
    }
}
