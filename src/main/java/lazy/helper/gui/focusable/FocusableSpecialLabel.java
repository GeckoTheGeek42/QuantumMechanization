package lazy.helper.gui.focusable;

import lazy.helper.gui.Widget;

/**
 * 
 * A FocusableLabel that is meant to display different text
 * than what it really means in the background.
 *
 */
public class FocusableSpecialLabel extends FocusableLabel {

	private String actualText;

	public FocusableSpecialLabel(String text, String actualText, Widget... tooltips) {
		super(text, tooltips);

		this.actualText = actualText;
	}

	public FocusableSpecialLabel(int x, int y, String text, String actualText, Widget... tooltips) {
		this(text, actualText, tooltips);

		setPosition(x,y);
	}

	public String getSpecialText() {
		return actualText;
	}

	public void setSpecialText(String str) {
		this.actualText = str;
	}

}
