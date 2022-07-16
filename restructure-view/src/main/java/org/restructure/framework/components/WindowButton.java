package org.restructure.framework.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;

/**
 * @author João Henrique
 */
public class WindowButton extends Button {

    public WindowButton() {
        this.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_ICON);
    }

    public WindowButton(String text) {
        this();
        this.setText(text);
    }

    public WindowButton(Icon icon) {
        this();
        this.setIcon(icon);
    }

    public WindowButton(String text, Icon icon) {
        this();
        this.setIcon(icon);
        this.setText(text);
    }


    public void setAction(ButtonAction action) {
        addClickListener(evt -> action.run());
    }
}
