#ifdef GL_ES
precision highp float;
#endif
varying vec2 vTextureCoord;
 
uniform sampler2D uSampler;
 
uniform vec3 uColor;
 
void main(void) {
  vec4 textureColor = texture2D(uSampler, vec2(vTextureCoord.s, 1.0 - vTextureCoord.t));
  gl_FragColor = textureColor * vec4(uColor, 1.0);
}