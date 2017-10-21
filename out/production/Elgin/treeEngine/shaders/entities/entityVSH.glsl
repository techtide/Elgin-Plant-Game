#version 150 core

//Inputs
in vec3 position;
in vec2 textureCoords;

//Outputs
out vec2 passTextureCoords;

//Uniforms
uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

void main() {

  gl_Position =  projectionMatrix * viewMatrix * transformationMatrix * vec4(position, 1.0f);
  passTextureCoords = textureCoords;

}
