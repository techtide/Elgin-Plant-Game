package treeEngine.shaders;

import org.lwjgl.util.vector.Vector3f;
import treeEngine.shaders.uniforms.Uniform1f;
import treeEngine.shaders.uniforms.Uniform3f;
import treeEngine.shaders.uniforms.UniformMat4;

public class UniformList {

    /* Entity Shader */
    //Matricies
    public static UniformMat4 entityTransformationMatrix;
    public static UniformMat4 entityProjectionMatrix;
    public static UniformMat4 entityViewMatrix;

    //Lighting
    public static Uniform3f entityLightPosition;
    public static Uniform3f entityLightColor;
    public static Uniform1f entityShineDamper;
    public static Uniform1f entityReflectivity;
    public static Uniform1f universalAmbientLight;

    //

    /**
     * Initializes the entity shader uniform variables.
     * @param programID
     */
    public static void initEntityUniforms(int programID) {
        entityTransformationMatrix = new UniformMat4("transformationMatrix", programID);
        entityProjectionMatrix = new UniformMat4("projectionMatrix", programID);
        entityViewMatrix = new UniformMat4("viewMatrix", programID);

        entityLightPosition = new Uniform3f("lightPosition", programID);
        entityLightColor = new Uniform3f("lightColor", programID);
        entityShineDamper = new Uniform1f("shineDamper", programID);
        entityReflectivity = new Uniform1f("reflectivity", programID);

        universalAmbientLight = new Uniform1f("ambientLight", programID);
    }

}
