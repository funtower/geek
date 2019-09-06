package geekcompetition.topic2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * 数据：
 * DataBus [
 * 	  dataKeys=[
 * 		[e, f, g], 
 * 		[a, b, c], 
 * 		[a, b, d], 
 * 		[a, b, c, e, f, g]
 * 	  ], 
 * 	  data={
 * 		[e, f, g]=feg, 
 * 		[a, b, c]=abccopy, 
 * 		[a, b, d]=abd, 
 * 		[a, b, c, e, f, g]=abcfeg
 * 	  }
 * ]
 * 
 * ----删除----
 * remove("f","e","g");
 * 
 * 结果：
 * DataBus [
 * 	  dataKeys=[
 * 		[a, b, c], 
 * 		[a, b, d]
 * 	  ], 
 * 	  data={
 * 		[a, b, c]=abccopy, 
 * 		[a, b, d]=abd
 * 	  }
 * ]
 * @author huangtao
 *
 * @param <K>
 * @param <V>
 */
public class DataBusUnOrderable<K, V> implements DataBus<K, V> {

	private HashSet<HashSet<K>> dataKeys = new HashSet<>();
	private Map<HashSet<K>, V> data = new HashMap<>();
	
	public void put (Object... param) {
		Object[] rawKeys = Arrays.copyOf(param, param.length - 1);
		put((K[])rawKeys, (V)(param[param.length - 1]));
	}
	
	public void put (K[] inputKeys, V v) {
		HashSet<K> kys = setKeys(inputKeys);
		dataKeys.add(kys);
		data.put(kys, v);
	}
	
	public V get(K[] inputKeys) {
		HashSet<K> kys = setKeys(inputKeys);
		return data.get(kys);
	}

	public boolean containsKey (K... ks) {
		HashSet<K> kys = setKeys(ks);
		return containsKey(kys);
	}
	
	private boolean containsKey (HashSet<K> kys) {
		return dataKeys.contains(kys);
	}
	
	public boolean containsValue (V v) {
		return data.containsValue(v);
	}
	
	public void remove(K... ks) {
		HashSet<K> matchKeys = setKeys(ks);
		Iterator<Map.Entry<HashSet<K>, V>> it = data.entrySet().iterator();  
        while(it.hasNext()){  
        	Map.Entry<HashSet<K>, V> entry = it.next();  
            if(entry.getKey().containsAll(matchKeys)) {
            	it.remove();//使用迭代器的remove()方法删除元素  
            	dataKeys.remove(entry.getKey());
            }
            
        }  
	}
	
	private HashSet<K> setKeys(K[] inputKeys) {
		HashSet<K> kys = new HashSet<>();
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
		DataBusUnOrderable other = (DataBusUnOrderable) obj;
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
		return "DataBus [dataKeys=" + dataKeys + ", data=" + data + "]";
	}
	
}
