package activity7;

import java.util.List;

public class SpecialItem extends AbstractDecorator
{
	private int aSpecial=0;

	public SpecialItem(Item pItem, int pSpecial)
	{
		super(pItem);
		aSpecial = pSpecial;
	}

	@Override
	public double price()
	{
		return super.price() * (1.0 - aSpecial/100.0);
	}

	public Item setSpecial(int pSpecial)
	{
		assert pSpecial > 0;
		aSpecial = pSpecial;
		return this;
	}

	@Override
	public String description()
	{
		String info=""; 
		if (aSpecial > 0)
			info = String.format(" Special Discount: %d%% off!", aSpecial);
		else
			info = "";
		if (!super.diets().isEmpty()) {
			info += ", " + super.diets().toString();
		}
		return String.format("%s%s, $%.2f", name(), info, price());
	}
	
	
	public Item getItem() {
		return this;
	}

}
