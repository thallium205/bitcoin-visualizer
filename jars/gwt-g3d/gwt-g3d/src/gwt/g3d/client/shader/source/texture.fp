#ifdef GL_ES
precision highp float;
#endif
uniform sampler2D uTexture;
uniform vec4 uTintColor;

varying vec2 vTexCoord;

void main() {
  gl_FragColor = vec4((uTintColor * texture2D(uTexture, vec2(vTexCoord.s, 1.0 - vTexCoord.t))).rgb,
  	uTintColor.a);
}