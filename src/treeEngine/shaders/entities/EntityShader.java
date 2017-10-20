package treeEngine.shaders.entities;

import elgin.data.Reference;
import org.lwjgl.util.vector.Matrix4f;
import treeEngine.entities.Camera;
import treeEngine.shaders.ShaderProgram;
import treeEngine.shaders.uniforms.Uniform;
import treeEngine.shaders.uniforms.UniformMat4;
import treeEngine.toolbox.Maths;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Shader for all entities in world. (Non-normal mapped ones atleast)
 */
public class EntityShader extends ShaderProgram {

    public UniformMat4 transformationMatrix;
    public UniformMat4 projectionMatrix;
    public UniformMat4 viewMatrix;

    public EntityShader() {
        super(Reference.ENTITY_SHADER_VERTEX_FILE, Reference.ENTITY_SHADER_FRAGMENT_FILE);
    }

    /**
     * Binds attributes for entityVSH.glsl
     */
    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    /**
     * Inits uniform variables
     * Init a new uniform based on type along with the shader name, then stick
     * in the hashmap so you can call getUniformVariable(uniformName).loadData(value) to load values.
     */
    @Override
    protected void initUniforms() {
        transformationMatrix = new UniformMat4("transformationMatrix", this.getProgramID());
        projectionMatrix = new UniformMat4("projectionMatrix", this.getProgramID());
        viewMatrix = new UniformMat4("viewMatrix", this.getProgramID());
    }

    public void loadCameraViewMatrix(Camera camera) {
        Matrix4f matrix = Maths.createViewMatrix(camera);
        viewMatrix.loadData(matrix);
    }

}
