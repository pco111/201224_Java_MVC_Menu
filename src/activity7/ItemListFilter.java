package activity7;

import java.util.stream.Stream;


public interface ItemListFilter
{
	Stream<Item> filter(Stream<Item> list);
}
