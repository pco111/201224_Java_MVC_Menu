package activity7;

import java.util.Arrays;
import java.util.stream.Stream;

/*This class contain static methods to return anonymous objects that filter streams with a particular criteria.
 */

public class FilteringUtils
{
	
	
	/**
	 * @return all items below a price
	 */
	public static ItemListFilter filterBelowPrice(float pPrice) {
		return new ItemListFilter() {
			@Override
			public Stream<Item> filter(Stream<Item> list)
			{
				return list.filter(item -> item.price() < pPrice);
			}
		};
	}
	
	/**
	 * @return all items within a price range
	 */
	public static ItemListFilter filterPriceRange(Float pMinPrice, Float pMaxPrice) {
		return new ItemListFilter() {
			@Override
			public Stream<Item> filter(Stream<Item> list)
			{
					return	list.filter(item -> item.price() > pMinPrice && item.price() < pMaxPrice);
			}
		};
	}
	
	/**
	 * 
	 * @param pCategory
	 * @return all items of pCategory
	 */
	public static ItemListFilter filterCategory(Category pCategory){
		return new ItemListFilter() {
			@Override
			public Stream<Item> filter(Stream<Item> list)
			{
				return 	list.filter(item -> item.category() == pCategory);
			}
		};
	}
	
	/**
	 * 
	 * @param pCategories
	 * @return all items that belong to the all of the dietary categories in pCategories
	 */
	public static ItemListFilter filterDiets(Diet... pDiets){
		return new ItemListFilter() {
			@Override
			public Stream<Item> filter(Stream<Item> list)
			{
				return	list.filter(item -> item.diets().containsAll(Arrays.asList(pDiets)));
			}
		};
	}
	
	/**
	 * @return First x Items in a list
	 */
	public static ItemListFilter firstXItems(int size){
		return new ItemListFilter() {
			@Override
			public Stream<Item> filter(Stream<Item> list)
			{
				return list.limit(size);
			}
		};
	}

}
