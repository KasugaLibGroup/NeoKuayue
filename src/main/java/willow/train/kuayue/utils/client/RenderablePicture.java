package willow.train.kuayue.utils.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.renderer.GameRenderer;
import willow.train.kuayue.initial.ImageInit;

import javax.annotation.Nonnull;
import java.awt.*;

public class RenderablePicture {

    private final int uOffset, vOffset, uWidth, vHeight;
    private int color;
    private float fuOffset, fvOffset, fuWidth, fvHeight;
    private float zoom = 1.0f, alpha;
    public static final int COLOR_DEFAULT = 0xffffff;
    private final ImageInit.ImageRegex data;

    public RenderablePicture(@Nonnull ImageInit.ImageRegex imageData, int uOffset, int vOffset, int uWidth, int vHeight, int color, float alpha) {
        this.data = imageData;
        this.uOffset = uOffset;
        this.vOffset = vOffset;
        this.uWidth = uWidth;
        this.vHeight = vHeight;
        this.color = color;
        this.alpha = alpha;
        refreshImage();
    }

    public RenderablePicture(@Nonnull ImageInit.ImageRegex imageData, int uOffset, int vOffset, int uWidth, int vHeight, int color) {
        this(imageData, uOffset, vOffset, uWidth, vHeight, color, 1.0f);
    }

    public RenderablePicture(@Nonnull ImageInit.ImageRegex imageData, int uOffset, int vOffset, int uWidth, int vHeight) {
        this(imageData, uOffset, vOffset, uWidth, vHeight, COLOR_DEFAULT, 1.0f);
    }

    public RenderablePicture(@Nonnull ImageInit.ImageRegex imageData, int uWidth, int vHeight, int color, float alpha) {
        this(imageData, 0, 0, uWidth, vHeight, color, alpha);
    }

    public RenderablePicture(@Nonnull ImageInit.ImageRegex imageData, int uWidth, int vHeight, int color) {
        this(imageData, 0, 0, uWidth, vHeight, color, 1.0f);
    }

    public RenderablePicture(@Nonnull ImageInit.ImageRegex imageData, int uWidth, int vHeight) {
        this(imageData, 0, 0, uWidth, vHeight, COLOR_DEFAULT, 1.0f);
    }

    public RenderablePicture(@Nonnull ImageInit.ImageRegex imageData, int color, float alpha) {
        this(imageData, 0, 0, imageData.width, imageData.height, color, alpha);
    }

    public RenderablePicture(@Nonnull ImageInit.ImageRegex imageData, int color) {
        this(imageData, 0, 0, imageData.width, imageData.height, color, 1.0f);
    }

    public RenderablePicture(@Nonnull ImageInit.ImageRegex imageData, float alpha) {
        this(imageData, 0, 0, imageData.width, imageData.height, COLOR_DEFAULT, alpha);
    }

    public RenderablePicture(@Nonnull ImageInit.ImageRegex imageData) {
        this(imageData, 0, 0, imageData.width, imageData.height, COLOR_DEFAULT, 1.0f);
    }

    public RenderablePicture cut(int left, int up, int right, int down) {
        return new RenderablePicture(data, uOffset + left, vOffset + up, uWidth - right - left, vHeight - down - up, color, alpha);
    }

    public ImageInit.ImageRegex getData() {
        return data;
    }

    public double widthHeightRatio() {
        return data.aspectRatio();
    }

    public double heightWidthRatio() {
        return 1 / data.aspectRatio();
    }

    public int getFixedHeight(int fromWidth) {
        return (int) ((double)fromWidth / widthHeightRatio());
    }

    public int getFixedWidth(int fromHeight) {
        return (int) ((double) fromHeight * widthHeightRatio());
    }

    public int uOffset() {
        return uOffset;
    }

    public int vOffset() {
        return vOffset;
    }

    public float fuOffset() {
        return (float) uOffset / (float) data.width;
    }

    public float fvOffset() {
        return (float) vOffset / (float) data.height;
    }

    public float fuWidth() {
        return (float) uWidth / (float) data.width;
    }

    public float fvHeight() {
        return (float) vHeight / (float) data.height;
    }

    public float fuOffsetR(){
        return fuOffset() + fuWidth();
    }

    public float fvOffsetD() {
        return fvOffset() + fvHeight();
    }

    public int width() {
        return (int) (zoom * uWidth);
    }

    public int height() {
        return (int) (zoom * vHeight);
    }

    public void zoom(float zoom) {
        this.zoom = Math.min(Math.max(zoom, 0), 5);
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setColor(int r, int g, int b, float a) {
        this.color = getOctColor(r, g, b);
        this.alpha = a;
    }

    public void refreshImage() {
        fuOffset = ((float) uOffset)/((float) data.width);
        fvOffset = ((float) vOffset)/((float) data.height);
        fuWidth = ((float) uWidth)/((float) data.width);
        fvHeight = ((float) vHeight)/((float) data.height);
    }

    public void setColor(int r, int g, int b) {
        this.color = getOctColor(r, g, b);
    }

    public void render(int x, int y, int width, int height) {
        int[] colors = getRGBAColor(color);
        int r = colors[0], g = colors[1], b = colors[2], a = colors[3];
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, data.location);
        RenderSystem.setShaderColor(
                (float) r / 255.0f, (float) g / 255.0f, (float) b / 255.0f, 1.0f);
        RenderSystem.enableBlend();
        BufferBuilder buffer = Tesselator.getInstance().getBuilder();
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        buffer.vertex(x, y, 0.0).uv(fuOffset, fvOffset).endVertex();
        buffer.vertex(x, y + height, 0.0).uv(fuOffset, fvOffset + fvHeight).endVertex();
        buffer.vertex(x + width, y + height, 0.0).uv(fuOffset + fuWidth,fvOffset + fvHeight).endVertex();
        buffer.vertex(x + width, y, 0.0).uv(fuOffset + fuWidth, fvOffset).endVertex();
        BufferUploader.drawWithShader(buffer.end());
        RenderSystem.disableBlend();
    }

    public void renderScaled(int x, int y, int axis, boolean isWidth) {
        if(isWidth) render(x, y, axis, getFixedHeight(axis));
        else render(x, y, getFixedWidth(axis), axis);
    }

    public void renderLazy(int x, int y) {
        render(x, y, (int) (uWidth * zoom), (int) (vHeight * zoom));
    }

    private int[] getRGBAColor(int octColor) {
        int[] result = new int[4];
        Color color = new Color(octColor);
        result[0] = color.getRed();
        result[1] = color.getGreen();
        result[2] = color.getBlue();
        result[3] = color.getAlpha();
        return result;
    }

    private int getOctColor(int r, int g, int b) {
        Color color1 = new Color(r, g, b);
        return color1.getRGB();
    }
}
