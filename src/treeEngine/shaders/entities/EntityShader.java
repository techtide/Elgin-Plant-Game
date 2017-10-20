package treeEngine.shaders.entities;

import elgin.data.Reference;
import treeEngine.shaders.ShaderProgram;
import treeEngine.shaders.uniforms.Uniform;
import treeEngine.shaders.uniforms.UniformMat4;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Shader for all entities in world. (Non-normal mapped ones atleast)
 */
public class EntityShader extends ShaderProgram {

    public EntityShader() {
        super(Reference.ENTITY_SHADER_VERTEX_FILE, Reference.ENTITY_SHADER_FRAGMENT_FILE);
    }

    private HashMap<String, Uniform> uniformIndex = new HashMap<>();

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
        UniformMat4 transformationMatrix = new UniformMat4("transformationMatrix", this.getProgramID());
        uniformIndex.put(transformationMatrix.getUniformName(), transformationMatrix);
    }

    /**
     * Gets the uniform so you can load values to the shader.
     * @param uniformName - Name of uniform you want
     * @return - the uniform
     */
    protected Uniform getUniformVariable(String uniformName) {
        Uniform uniform = uniformIndex.get(uniformName);

        return uniform;
    }

}
