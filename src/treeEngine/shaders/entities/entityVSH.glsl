#version 150 core

//Inputs
in vec3 position;
in vec2 textureCoords;
in vec3 normal;

//Outputs
out vec2 passTextureCoords;
out vec3 surfaceNormal;
out vec3 toLightVector;
out vec3 toCameraVector;

//Uniforms
uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform float useFakeLighting;

uniform vec3 lightPosition;

void main() {

  vec4 worldPosition = transformationMatrix * vec4(position, 1.0f);
  gl_Position =  projectionMatrix * viewMatrix * worldPosition;
  passTextureCoords = textureCoords;

  vec3 actualNormal = normal;
  if(useFakeLighting > 0.5) {
    actualNormal = vec3(0, 1, 0);
  }

  surfaceNormal = (transformationMatrix * vec4(actualNormal, 0.0)).xyz;
  toLightVector = lightPosition - worldPosition.xyz;
  toCameraVector = (inverse(viewMatrix) * vec4(0.0, 0.0, 0.0, 1.0)).xyz - worldPosition.xyz;

}
