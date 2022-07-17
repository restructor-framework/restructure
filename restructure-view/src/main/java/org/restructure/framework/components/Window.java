package org.restructure.framework.components;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.dialog.DialogVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.function.SerializableConsumer;

/**
 * @author João Henrique
 */
public class Window extends Dialog {

    private static final String SET_PROPERTY_IN_OVERLAY_JS = "this.$.overlay.$.overlay.style[$0]=$1";

    // private transient Position initialPosition;

    private String defaultWidth = "850px";

    private String title;

    private FlexLayout header;
    private FlexLayout content;
    private FlexLayout toolBar;

    private Button closeButton;

    private Text titleText;

    public Window() {
        // getPosition(x -> this.initialPosition = x);
        addThemeVariants(DialogVariant.LUMO_NO_PADDING);
        setWidth(defaultWidth);
        configHeader();
        configContent();
        configToolBar();
        setCloseOnOutsideClick(false);
        setCloseOnEsc(false);
        setDraggable(true);
        super.add(header, content, toolBar);
    }

    private void configHeader() {
        this.header = new FlexLayout();
        this.header.setHeight("45px");
        this.header.getStyle().set(
                "background-color",
                "var(--lumo-primary-color)");
        this.header.setJustifyContentMode(JustifyContentMode.BETWEEN);
        this.header.setAlignItems(Alignment.CENTER);
        this.header.addClassName("draggable");
        this.header.getStyle().set("padding", "0px 12px");
        this.header.getStyle().set("cursor", "move");
        this.header.getStyle().set("color", "#fff");
        this.titleText = new Text(title);
        configCloseButton();

        this.header.add(this.titleText, this.closeButton);
    }

    private void configContent() {
        this.content = new FlexLayout();
        this.content.getStyle().set("padding", "12px");
    }

    private void configCloseButton() {
        this.closeButton = new Button(VaadinIcon.CLOSE.create());
        this.closeButton.addThemeVariants(
                ButtonVariant.LUMO_SMALL,
                ButtonVariant.LUMO_ICON,
                ButtonVariant.LUMO_PRIMARY);
        this.closeButton.addClickListener(evt -> close());
    }

    private void configToolBar() {
        this.toolBar = new FlexLayout();
        this.toolBar.getStyle().set("border-top", "1px solid var(--lumo-contrast-10pct)");
        this.toolBar.getStyle().set("padding", "5px 12px");
        this.toolBar.getStyle().set("gap", "15px");
        this.toolBar.setJustifyContentMode(JustifyContentMode.END);
    }

    public void setTitle(String title) {
        this.titleText.setText(title);
    }

    public void addInToolbar(WindowButton... components) {
        this.toolBar.add(components);
    }

    @Override
    public void add(Component... components) {
        this.content.add(components);
    }

    public void setPosition(Position position) {
        enablePositioning(true);
        getElement().executeJs(SET_PROPERTY_IN_OVERLAY_JS, "left", position.getLeft());
        getElement().executeJs(SET_PROPERTY_IN_OVERLAY_JS, "top", position.getTop());
    }

    private void enablePositioning(boolean positioningEnabled) {
        getElement()
                .executeJs(SET_PROPERTY_IN_OVERLAY_JS, "align-self", positioningEnabled ? "flex-start" : "unset");
        getElement()
                .executeJs(SET_PROPERTY_IN_OVERLAY_JS, "position", positioningEnabled ? "absolute" : "relative");
    }

    public void getPosition(SerializableConsumer<Position> consumer) {
        getElement()
                .executeJs(
                        "return [" + "this.$.overlay.$.overlay.style['top'], this.$.overlay.$.overlay.style['left']"
                                + "]")
                .then(
                        String.class,
                        s -> {
                            String[] split = StringUtils.split(s, ',');
                            if (split.length == 2 && split[0] != null && split[1] != null) {
                                Position position = new Position(split[0], split[1]);
                                consumer.accept(position);
                            }
                        });
    }

    public static class Position {
        private String top;
        private String left;

        public Position(String top, String left) {
            this.top = top;
            this.left = left;
        }

        public String getTop() {
            return top;
        }

        public void setTop(String top) {
            this.top = top;
        }

        public String getLeft() {
            return left;
        }

        public void setLeft(String left) {
            this.left = left;
        }
    }

}
