#version 150 core

//Inputs
in vec3 position;
in vec2 textureCoords;

//Outputs
out vec2 passTextureCoords;

void main() {

  gl_Position = vec4(position, 1.0f);
  passTextureCoords = textureCoords;

}
