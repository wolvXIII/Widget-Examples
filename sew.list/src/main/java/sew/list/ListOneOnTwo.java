/*
 * Sébastien Eon 2016 / CC0-1.0
 */
package sew.list;

import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.container.List;
import ej.container.Scroll;
import ej.microui.MicroUI;
import ej.microui.display.Colors;
import ej.style.Stylesheet;
import ej.style.background.PlainBackground;
import ej.style.font.FontProfile;
import ej.style.font.FontProfile.FontSize;
import ej.style.outline.EmptyOutline;
import ej.style.outline.SimpleOutline;
import ej.style.selector.ClassSelector;
import ej.style.selector.OddChildSelector;
import ej.style.selector.TypeSelector;
import ej.style.selector.combinator.AndCombinator;
import ej.style.util.EditableStyle;
import ej.widget.StyledDesktop;
import ej.widget.StyledPanel;
import ej.widget.basic.Label;

public class ListOneOnTwo {

	private static final String ITEM = "Item";

	public static void main(String[] args) {
		MicroUI.start();
		createStylesheet();
		createPage();
	}

	private static void createStylesheet() {
		Stylesheet stylesheet = ServiceLoaderFactory.getServiceLoader().getService(Stylesheet.class);

		// Remove white background from all elements.
		EditableStyle defaultStyle = new EditableStyle();
		defaultStyle.setBorder(EmptyOutline.EMPTY_OUTLINE);
		stylesheet.setDefaultStyle(defaultStyle);

		// Add a white background to the panel.
		// StyledPanel {
		EditableStyle panelStyle = new EditableStyle();
		// background-color: black;
		PlainBackground panelBackground = new PlainBackground(Colors.WHITE);
		panelStyle.setBorder(panelBackground);
		// }
		stylesheet.addRule(new TypeSelector(StyledPanel.class), panelStyle);

		// Create the list item style.
		// .Item {
		EditableStyle itemStyle = new EditableStyle();
		// font-size: medium;
		FontProfile itemFontProfile = new FontProfile();
		itemFontProfile.setSize(FontSize.MEDIUM);
		itemStyle.setFontProfile(itemFontProfile);
		// padding: 6px;
		SimpleOutline itemPadding = new SimpleOutline(6);
		itemStyle.setPadding(itemPadding);
		// }
		stylesheet.addRule(new ClassSelector(ITEM), itemStyle);

		// Colorize one item on two.
		// .Item:nthchild(odd) {
		EditableStyle oddItemStyle = new EditableStyle();
		// background-color: 0xeee;
		PlainBackground oddBorder = new PlainBackground(0xe0e0e0);
		oddItemStyle.setBorder(oddBorder);
		// }
		stylesheet.addRule(new AndCombinator(new ClassSelector(ITEM), new OddChildSelector()), oddItemStyle);

	}

	private static void createPage() {
		// Create top-level containers.
		StyledDesktop desktop = new StyledDesktop();
		StyledPanel panel = new StyledPanel();

		// Create the list that will scroll.
		List listComposite = new List(false);

		for (int i = 0; ++i <= 20;) {
			// Create the list items:
			// - the main item,
			Label item = new Label("Item " + i);
			item.addClassSelector(ITEM);
			// Add it to the scroll list.
			listComposite.add(item);
		}

		// Create the scroll composite containing the list…
		Scroll scrollComposite = new Scroll(false, listComposite, true);
		// … and add it to the panel.
		panel.setWidget(scrollComposite);

		// Show everything.
		panel.show(desktop, true);
		desktop.show();
	}

}
