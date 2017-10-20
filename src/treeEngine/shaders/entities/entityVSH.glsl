#version 150 core

//Inputs
in vec3 position;
in vec2 textureCoords;

//Outputs
out vec2 passTextureCoords;

//Uniforms
uniform mat4 transformationMatrix;

void main() {

  gl_Position = transformationMatrix * vec4(position, 1.0f);
  passTextureCoords = textureCoords;

}
