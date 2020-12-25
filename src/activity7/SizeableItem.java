package activity7;

import java.util.Map;

/**
 * 
 * Represents 3 decorated items with a size and adjust price
 *
 */
public class SizeableItem {
	private OneSize Small;
	private OneSize Medium;
	private OneSize Large;
	
	public SizeableItem(Item pItem) {
		Small = new OneSize(pItem, Size.SMALL);
		Medium = new OneSize(pItem, Size.MEDIUM);
		Large = new OneSize(pItem, Size.LARGE);
	}
	public Item small() {
		return Small;
	}
	public Item medium() {
		return Medium;
	}
	public Item large() {
		return Large;
	}
	
	private class OneSize extends AbstractDecorator{
		private final Map<Size, Double> standard = Map.of(Size.SMALL, 0.75, Size.MEDIUM, 1.0, Size.LARGE, 1.25);
		private final Size aSize;
		
		public OneSize(Item pItem, Size pSize) {
			super(pItem);
			aSize = pSize;
		}

		@Override
		public double price() {
			return super.price() * standard.get(aSize);
		}
		
		@Override
		public String description() {
			String info; 
			if (!super.diets().isEmpty()) {
				info = ", " + super.diets().toString();
			}
			else
				info = "";
			return String.format("%s(%s) %s, $%.2f", this.name(), aSize.name(), info, this.price());
		}
	}
	

}
