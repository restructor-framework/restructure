package org.restructure.framework.demo;

import com.vaadin.flow.component.icon.VaadinIcon;
import org.restructure.framework.components.Window;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.Route;
import org.restructure.framework.components.WindowButton;

@Route(value = "")
@JavaScript("./theme/shared-theme.js")
public class HelloPage extends FlexLayout {

    /**
     * Serial UID
     */
    private static final long serialVersionUID = 2165518358840889306L;

    public HelloPage() {
        setHeightFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        final var window = new Window();
        WindowButton cancel = new WindowButton("Cancelar", VaadinIcon.CLOSE_CIRCLE.create());
        cancel.setAction(window::close);


        window.setTitle("Teste");
        window.addInToolbar(cancel);
        final var btn = new Button("Click Me!");

        btn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        btn.addClickListener(e -> window.open());

        add(btn);
    }
}
