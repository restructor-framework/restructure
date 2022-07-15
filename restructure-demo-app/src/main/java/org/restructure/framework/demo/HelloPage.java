package org.restructure.framework.demo;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.Route;

@Route(value = "")
public class HelloPage extends FlexLayout {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 2165518358840889306L;
	
	public HelloPage() {
		setHeightFull();
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);
		add(new Text("Hello Demo App"));
	}
}
