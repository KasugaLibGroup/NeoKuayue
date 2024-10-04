package willow.train.kuayue.systems.editable_panel.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import kasuga.lib.core.client.render.SimpleColor;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.client.render.texture.Vec2f;
import kasuga.lib.core.util.LazyRecomputable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import willow.train.kuayue.initial.ClientInit;
import willow.train.kuayue.systems.editable_panel.ColorTemplate;
import willow.train.kuayue.systems.editable_panel.widget.*;

import java.io.IOException;
import java.util.function.Consumer;

public class GetShareTemplateScreen extends Screen {
    private int x, y;
    private final int bgWidth, bgHeight;
    private Vector3f bgPos;
    private ColorTemplate template;
    private DescriptionLabel title, rgb, document, owner;
    private ImageButton accept, cancel;
    private boolean editMode;
    private AbstractWidget chosen;
    private EditBar bar;
    private boolean visible = true;
    public static final LazyRecomputable<ImageMask> getShareBg =
            new LazyRecomputable<>(() -> {
                try {
                    ImageMask mask = ClientInit.recipeBook.getImage().get().getMask();
                    mask.rectangleUV(1 / 256f, 1/256f, 148f/256f, 167f/256f);
                    return mask;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

    private ColorScreen selector;

    public static final LazyRecomputable<ImageMask> cancelImage =
            new LazyRecomputable<>(() -> ColorTemplateScreen.buttons.get().copyWithOp((m) -> m.rectangleUV(0.5f, 0.125f, 0.625f, 0.25f)));

    public static final LazyRecomputable<ImageMask> acceptImage =
            new LazyRecomputable<>(() -> ColorTemplateScreen.buttons.get().copyWithOp(m -> m.rectangleUV(0.375f, 0.125f, 0.5f, 0.25f)));


    public GetShareTemplateScreen(Component pTitle, ColorTemplate template) {
        super(pTitle);
        this.template = template;
        x = y = 0;
        bgWidth = 147;
        bgHeight = 166;
        editMode = false;
        chosen = null;
    }

    @Override
    public void init() {
        super.init();
        if (template == null) return;
        this.clearWidgets();
        this.minecraft = Minecraft.getInstance();
        Screen screen = Minecraft.getInstance().screen;
        if (screen == null) return;
        int width = screen.width;
        int height = screen.height;
        bgPos = new Vector3f((width - 147f) / 2, (height - 166f) / 2, 0);
        this.x = (int) bgPos.x();
        this.y = (int) bgPos.y();

        SimpleColor textColor = SimpleColor.fromRGBInt(0xffffff);
        title = new DescriptionLabel(new Vec2f(this.x + 25, this.y + 15), 120, 8,
                Component.literal(template.getName()), textColor);
        title.setForceLeftBegin(true);

        SimpleColor templateColor = SimpleColor.fromRGBInt(template.getColor());
        String color = Integer.toHexString(template.getColor()).toUpperCase();
        if (color.length() < 6) color = "0".repeat(6 - color.length()) + color;

        rgb = new DescriptionLabel(new Vec2f(this.x + 20, this.y + 40), 80, 8,
                Component.literal("RGB: #" + color), templateColor);
        rgb.setForceLeftBegin(true);

        document = new DescriptionLabel(new Vec2f(this.x + 20, this.y + 50), 120, 64,
                Component.literal(template.getDocument()), textColor);

        owner = new DescriptionLabel(new Vec2f(this.x + 20, this.y + 125), 80, 8,
                Component.literal(template.getOwner()), textColor);
        owner.setForceLeftBegin(true);

        cancel = new ImageButton(cancelImage, this.x + bgWidth - 35, this.y + 113, 20, 20, Component.literal("cancel"), b -> {});
        accept = new ImageButton(acceptImage, this.x + bgWidth - 35, this.y + 135, 20, 20, Component.literal("accept"), b -> {});

        bar = new EditBar(0, 0, Component.empty(), "test");
        selector = new ColorScreen(32, 32, Component.translatable("tooltip.kuayue.color_screen.title"));

        addRenderableWidget(title);
        addRenderableWidget(rgb);
        addRenderableWidget(document);
        addRenderableWidget(owner);

        addRenderableWidget(cancel);
        addRenderableWidget(accept);
        addRenderableWidget(bar);
        addRenderableWidget(selector);

        selector.init();
        selector.setVisible(false);
        bar.visible = false;
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        if (!visible) return;
        if (!editMode || (editMode && chosen != rgb)) {
            ImageMask mask = getShareBg.get();
            mask.rectangle(bgPos, ImageMask.Axis.X, ImageMask.Axis.Y, true, true, bgWidth, bgHeight);
            mask.renderToGui();
        }

        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    public void onCancelClick(OnClick<ImageButton> clk) {
        if (cancel == null) return;
        cancel.setOnClick(clk);
    }

    public void onAcceptClick(OnClick<ImageButton> clk) {
        if (accept == null) return;
        accept.setOnClick(clk);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public void setTemplate(ColorTemplate template) {
        this.template = template;
        init();
    }

    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        if (!visible && (selector == null || !selector.getVisible())) return false;
        return super.mouseReleased(pMouseX, pMouseY, pButton);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (!visible && (selector == null || !selector.getVisible())) return false;
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        if (!visible && (selector == null || !selector.getVisible())) return false;
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        if (!visible && (selector == null || !selector.getVisible())) return false;
        return super.mouseScrolled(pMouseX, pMouseY, pDelta);
    }

    public void setEditMode(boolean editMode) {
        if (template == null) return;
        this.editMode = editMode;
        if (editMode) {
            OnClick<?> clk = (a, b, c) -> {
                if (!bar.visible) {
                    chosen = a;
                    if (a instanceof Label label)
                        bar.setText(label.getText().getString());
                    bar.setPosition(a.x + (a.getWidth() - bar.getWidth())/2, a.y + a.getHeight() + 2);
                    bar.visible = true;
                    bar.setFocused(true);
                }
            };
            this.title.setDescriptionOnClick((OnClick<DescriptionLabel>) clk);
            Consumer<Object> onClose = obj -> {
                title.visible = true;
                rgb.visible = true;
                document.visible = true;
                owner.visible = true;
                accept.visible = true;
                cancel.visible = true;
                selector.setVisible(false);
                this.chosen = null;
            };
            this.rgb.setDescriptionOnClick((a, b, c) -> {
                if (bar.visible) return;
                chosen = a;

                title.visible = false;
                rgb.visible = false;
                document.visible = false;
                owner.visible = false;
                accept.visible = false;
                cancel.visible = false;

                selector.setHex(this.rgb.getText().getString().replace("RGB: ", ""));
                selector.setSaveVisible(false);
                selector.setTemplateVisible(false);
                selector.onConfirmClick((widget, mx, my) -> {
                    this.rgb.setText(Component.literal("RGB: " + selector.hexCache));
                    rgb.setColor(0xff000000 + selector.getColor().getRGB());
                    this.rgb.setWidthAndHeight(80, 8);
                    this.rgb.setForceLeftBegin(true);
                    onClose.accept(null);
                });
                selector.onCancelClick(((widget, mx, my) -> {
                    onClose.accept(null);
                }));
                selector.setVisible(true);
            });
            this.document.setDescriptionOnClick((OnClick<DescriptionLabel>) clk);
            bar.onCancelClick(((widget, mouseX, mouseY) -> {
                bar.visible = false;
                chosen = null;
            }));
            bar.onAcceptClick((widget, mouseX, mouseY) -> {
                if (!(chosen instanceof DescriptionLabel label)) return;
                label.setText(Component.literal(bar.getText()));
                bar.visible = false;
                chosen = null;
            });
        } else {
            OnClick<?> clk = (a, b, c) -> {};
            this.title.setDescriptionOnClick((OnClick<DescriptionLabel>) clk);
            this.document.setDescriptionOnClick((OnClick<DescriptionLabel>) clk);
            this.rgb.setDescriptionOnClick((OnClick<DescriptionLabel>) clk);
            chosen = null;
        }
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        if (!visible && (selector == null || !selector.getVisible())) return false;
        if (bar.visible)
            return bar.charTyped(pCodePoint, pModifiers);
        return super.charTyped(pCodePoint, pModifiers);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (!visible && (selector == null || !selector.getVisible())) return false;
        if (bar.visible)
            return bar.keyPressed(pKeyCode, pScanCode, pModifiers);
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        if (!visible && (selector == null || !selector.getVisible())) return false;
        if (bar.visible)
            return bar.keyReleased(pKeyCode, pScanCode, pModifiers);
        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    public void fillDataToTemplate() {
        template.setDocument(this.document.getPlainText());
        template.setName(this.title.getPlainText());
        template.setColor(this.rgb.getColor().getRGB() - 0xff000000);
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public ColorTemplate getTemplate() {
        return template;
    }

    public boolean isEditMode() {
        return editMode;
    }
}
