package willow.train.kuayue.systems.editable_panel.widget;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import willow.train.kuayue.mixins.mixin.client.AccessorEditBox;

public class TransparentEditBox extends EditBox {
    private float scaleX = 1.0f, scaleY = 1.0f;
    public TransparentEditBox(Font pFont, int pX, int pY, int pWidth, int pHeight, Component pMessage, String value, int color) {
        super(pFont, pX, pY, pWidth, pHeight, null, pMessage);
        setBordered(false);
        setValue(value);
        setTextColor(color);
    }

    public TransparentEditBox(Font pFont, int pX, int pY, int pWidth, int pHeight, float scaleX,
                              float scaleY, Component pMessage, String value, int color) {
        super(pFont, pX, pY, pWidth, pHeight, null, pMessage);
        setBordered(false);
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        setValue(value);
        setTextColor(color);
    }

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float scale) {
        this.scaleX = scale;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    public float getScaleY() {
        return scaleY;
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        if (this.visible) {
            float x = this.x - scaleX * this.x;
            float y = this.y - scaleY * this.y;
            pPoseStack.translate(x, y, 0);
            pPoseStack.scale(scaleX, scaleY, 1f);
            this.isHovered = pMouseX >= this.x && pMouseY >= this.y &&
                    pMouseX < this.x + this.width * scaleX && pMouseY < this.y + this.height * scaleY;
            this.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
            pPoseStack.scale(1 / scaleX, 1 / scaleY, 1f);
            pPoseStack.translate(- x, - y, 0);
        }
    }

    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        AccessorEditBox accessor = (AccessorEditBox) this;
        if (this.isVisible()) {
            if (accessor.getBordered()) {
                int i = this.isFocused() ? -1 : -6250336;
                fill(pPoseStack, this.x - 1, this.y - 1, this.x + this.width + 1, this.y + this.height + 1, i);
                fill(pPoseStack, this.x, this.y, this.x + this.width, this.y + this.height, -16777216);
            }

            int textColor = accessor.getIsEditable() ? accessor.getTextColor() : accessor.getTextColorUneditable();
            int headToCursor = accessor.getCursorPos() - accessor.getDisplayPos();
            int headToHighlight = accessor.getHighlightPos() - accessor.getDisplayPos();
            String displayedText = accessor.getFont().plainSubstrByWidth(accessor.getValue().substring(accessor.getDisplayPos()), this.getInnerWidth());
            boolean cursorInBox = headToCursor >= 0 && headToCursor <= displayedText.length();  // 游标是否在框内
            boolean shouldRenderCursor = this.isFocused() && accessor.getFrame() / 6 % 2 == 0 && cursorInBox;  // 是否应当渲染游标
            int startX = accessor.getBordered() ? this.x + 4 : this.x;
            int startY = accessor.getBordered() ? this.y + (this.height - 8) / 2 : this.y;
            int lengthBeforeCursor = startX;
            if (headToHighlight > displayedText.length()) {
                headToHighlight = displayedText.length();
            }
            String textBeforeCursor = cursorInBox ? displayedText.substring(0, headToCursor) : displayedText;
            if (!displayedText.isEmpty()) {
                lengthBeforeCursor = accessor.getFont().draw(pPoseStack, accessor.getFormatter().apply(textBeforeCursor, accessor.getDisplayPos()), (float) startX, (float) startY, textColor);
            }

            boolean cursorBeforeEnd = accessor.getCursorPos() < accessor.getValue().length() || accessor.getValue().length() >= accessor.getMaxLength();
            int positionAfterCursor = lengthBeforeCursor;
            if (!cursorInBox) {
                positionAfterCursor = headToCursor > 0 ? startX + this.width : startX;
            } else if (cursorBeforeEnd) {
                positionAfterCursor = lengthBeforeCursor - 1;
                --lengthBeforeCursor;
            }

            if (!displayedText.isEmpty() && cursorInBox && headToCursor < displayedText.length()) {
                accessor.getFont().draw(pPoseStack, accessor.getFormatter().apply(displayedText.substring(headToCursor), accessor.getCursorPos()), (float) lengthBeforeCursor, (float) startY, textColor);
            }

            if (!cursorBeforeEnd && accessor.getSuggestion() != null) {
                accessor.getFont().draw(pPoseStack, accessor.getSuggestion(), (float)(positionAfterCursor - 1), (float) startY, -8355712);
            }

            if (shouldRenderCursor) {
                if (cursorBeforeEnd) {
                    GuiComponent.fill(pPoseStack, positionAfterCursor, startY - 1, positionAfterCursor + 1, startY + 1 + 9, -3092272);
                } else {
                    accessor.getFont().draw(pPoseStack, "_", (float) positionAfterCursor, (float) startY, textColor);
                }
            }
            if (headToHighlight != headToCursor) {
                int lengthBeforeHighlight = startX + (int)(accessor.getFont().width(displayedText.substring(0, headToHighlight)) * scaleX);
                this.renderHighlight(startX + (int)(accessor.getFont().width(textBeforeCursor) * scaleX), startY - 1,
                        lengthBeforeHighlight, startY + (int)(height * scaleY));
            }
        }
    }

    private void renderHighlight(int pStartX, int pStartY, int pEndX, int pEndY) {
        if (pStartX < pEndX) {
            int i = pStartX;
            pStartX = pEndX;
            pEndX = i;
        }

        if (pStartY < pEndY) {
            int j = pStartY;
            pStartY = pEndY;
            pEndY = j;
        }

        if (pEndX > this.x + this.width * scaleX) {
            pEndX = this.x + (int)(this.width * scaleX);
        }

        if (pStartX > this.x + this.width * scaleX) {
            pStartX = this.x + (int)(this.width * scaleX);
        }

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(0.0F, 0.0F, 1.0F, 1.0F);
        RenderSystem.disableTexture();
        RenderSystem.enableColorLogicOp();
        RenderSystem.logicOp(GlStateManager.LogicOp.OR_REVERSE);
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);
        bufferbuilder.vertex((double)pStartX, (double)pEndY, 0.0D).endVertex();
        bufferbuilder.vertex((double)pEndX, (double)pEndY, 0.0D).endVertex();
        bufferbuilder.vertex((double)pEndX, (double)pStartY, 0.0D).endVertex();
        bufferbuilder.vertex((double)pStartX, (double)pStartY, 0.0D).endVertex();
        tesselator.end();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableColorLogicOp();
        RenderSystem.enableTexture();
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        AccessorEditBox accessor = (AccessorEditBox) this;
        if (!this.isVisible()) {
            return false;
        } else {
            boolean flag = pMouseX >= this.x && pMouseY >= this.y &&
                    pMouseX < this.x + this.width * scaleX &&
                    pMouseY < this.y + this.height * scaleY;
            if (accessor.getCanLoseFocus()) {
                this.setFocus(flag);
            }

            if (this.isFocused() && flag && pButton == 0) {
                int position = (int) Math.round((pMouseX - this.x) / scaleX);
                String textBeforePos = accessor.getFont().plainSubstrByWidth(accessor.getValue(), position);
                setCursorPosition(textBeforePos.length());
                return true;
            } else {
                return false;
            }
        }
    }
}
