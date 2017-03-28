package com.hifunki.funki.util.gpuimage;

import android.opengl.GLES20;

import jp.co.cyberagent.android.gpuimage.GPUImageFilter;

/*
 * Copy from follow filter:
 * 		GPUImageBrightnessFilter
 * 		GPUImageContrastFilter
 * 		GPUImageSaturationFilter
 * */
public class GPUImageBaseParamFilter extends GPUImageFilter {
    public static final String BASE_PARAM_FRAGMENT_SHADER = "" +
            "varying highp vec2 textureCoordinate;\n" +
            "\n" +
            "uniform sampler2D inputImageTexture;\n" +
            "uniform lowp float brightness;\n" +
            "uniform lowp float contrast;\n" + 
            "uniform lowp float saturation;\n" +
            "\n" +
            "// Values from \"Graphics Shaders: Theory and Practice\" by Bailey and Cunningham\n" +
            "const mediump vec3 luminanceWeighting = vec3(0.2125, 0.7154, 0.0721);\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "	lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n" +
            "	\n" +
            "	textureColor = vec4((textureColor.rgb + vec3(brightness)), textureColor.w);\n" +
            "	\n" +
            "	textureColor = vec4(((textureColor.rgb - vec3(0.5)) * contrast + vec3(0.5)), textureColor.w);\n" + 
            "	\n" +
            "	lowp float luminance = dot(textureColor.rgb, luminanceWeighting);\n" +
            "	lowp vec3 greyScaleColor = vec3(luminance);\n" +
            "	\n" +
            "	gl_FragColor = vec4(mix(greyScaleColor, textureColor.rgb, saturation), textureColor.w);\n" +
            "}\n";

    private int mBrightnessLocation;
    private int mContrastLocation;
    private int mSaturationLocation;
    private float mBrightness;
    private float mContrast;
    private float mSaturation;

    public GPUImageBaseParamFilter() {
        this(0.0f, 1.0f, 1.0f);
    }

    public GPUImageBaseParamFilter(float brightness, float contrast, float saturation) {
        super(NO_FILTER_VERTEX_SHADER, BASE_PARAM_FRAGMENT_SHADER);
        mBrightness = brightness;
        mContrast = contrast;
        mSaturation = saturation;
    }

    @Override
    public void onInit() {
        super.onInit();
        mBrightnessLocation = GLES20.glGetUniformLocation(getProgram(), "brightness");
        mContrastLocation = GLES20.glGetUniformLocation(getProgram(), "contrast");
        mSaturationLocation = GLES20.glGetUniformLocation(getProgram(), "saturation");
    }

    @Override
    public void onInitialized() {
        super.onInitialized();
        setBrightness(mBrightness);
        setContrast(mContrast);
        setSaturation(mSaturation);
    }

    public void setBrightness(final float brightness) {
        mBrightness = brightness;
        setFloat(mBrightnessLocation, mBrightness);
    }
    
    public void setContrast(final float contrast) {
        mContrast = contrast;
        setFloat(mContrastLocation, mContrast);
    }
    
    public void setSaturation(final float saturation) {
        mSaturation = saturation;
        setFloat(mSaturationLocation, mSaturation);
    }
}
