package geekcompetition.topic2;

public interface DataBus<K, V> {

	void put (Object... param);
	
	void put (K[] inputKeys, V v);
	
	V get(K[] inputKeys);

	boolean containsKey (K... ks);
	
	boolean containsValue (V v);
	
	void remove(K... ks);
}
