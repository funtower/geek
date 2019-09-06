package geekcompetition.topic2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 数据：
 * DataBusOrderable [
 * 	dataKeys=[
 * 		[a, b, c], 
 * 		[a, b, d], 
 * 		[e, f, g], 
 * 		[f, e, g], 
 * 		[a, b, c, f, e, g]], 
 * 	data={
 * 		[a, b, c]=abccopy, 
 * 		[a, b, d]=abd, 
 * 		[e, f, g]=efg, 
 * 		[f, e, g]=feg, 
 * 		[a, b, c, f, e, g]=abcfeg
 * 	}
 * ]
 * 
 * ---删除---
 * remove("f","e","g")
 * 
 * 结果：
 * DataBusOrderable [
 * 	dataKeys=[
 * 		[a, b, c], 
 * 		[a, b, d], 
 * 		[e, f, g], 
 * 		[a, b, c, f, e, g]], 
 * 	data={
 * 		[a, b, c]=abccopy, 
 * 		[a, b, d]=abd, 
 * 		[e, f, g]=efg, 
 * 		[a, b, c, f, e, g]=abcfeg
 * 	}
 * ]
 * @author huangtao
 *
 * @param <K>
 * @param <V>
 */
public class DataBusOrderable<K, V> implements DataBus<K, V> {

	private HashSet<ArrayList<K>> dataKeys = new HashSet<ArrayList<K>>();
	private Map<ArrayList<K>, V> data = new HashMap<ArrayList<K>, V>();
	
	public void put (Object... param) {
		Object[] rawKeys = Arrays.copyOf(param, param.length - 1);
		put((K[])rawKeys, (V)(param[param.length - 1]));
	}
	
	public void put (K[] inputKeys, V v) {
		ArrayList<K> kys = setKeys(inputKeys);
		dataKeys.add(kys);
		data.put(kys, v);
	}
	
	public V get(K[] inputKeys) {
		ArrayList<K> kys = setKeys(inputKeys);
		return data.get(kys);
	}

	public boolean containsKey (K... ks) {
		ArrayList<K> kys = setKeys(ks);
		return containsKey(kys);
	}
	
	private boolean containsKey (ArrayList<K> kys) {
		return dataKeys.contains(kys);
	}
	
	public boolean containsValue (V v) {
		return data.containsValue(v);
	}
	
	public void remove(K... ks) {
		ArrayList<K> matchKeys = setKeys(ks);
		List<ArrayList<K>> listK = new ArrayList<>();
		for (ArrayList<K> arrayList : dataKeys) {
			List<K> subList = arrayList.subList(0, matchKeys.size());
			if (subList.equals(matchKeys)) {
				listK.add(arrayList);
			}
		}
		for (ArrayList<K> arrayList : listK) {
			data.remove(arrayList);
			dataKeys.remove(arrayList);
		}
	}
	
	private ArrayList<K> setKeys(K[] inputKeys) {
		ArrayList<K> kys = new ArrayList<>();
		for (K k : inputKeys) {
			kys.add(k);
		}
		return kys;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((dataKeys == null) ? 0 : dataKeys.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataBusOrderable other = (DataBusOrderable) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (dataKeys == null) {
			if (other.dataKeys != null)
				return false;
		} else if (!dataKeys.equals(other.dataKeys))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DataBusOrderable [dataKeys=" + dataKeys + ", data=" + data + "]";
	}
	
}
