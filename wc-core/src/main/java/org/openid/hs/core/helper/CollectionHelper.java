package org.openid.hs.core.helper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Utilities for collection.
 * @version R2
 * @author Steven
 *
 */
public final class CollectionHelper {
	/**
	 * Put a new entry at a given position in linked hash map.
	 * @param pMap Map which used to put in.
	 * @param pIndex Position to insert the new entry.
	 * @param pKey Key of the new entry.
	 * @param pValue Value of the new entry.
	 */
	public static <K, V> void putAt(LinkedHashMap<K, V> pMap, int pIndex, K pKey, V pValue) {
		if (pMap == null) {
			LoggerHelper.warn(String.format(
					"%s: putBefore failed! the given map is null", 
					CollectionHelper.class.getName()
				));
		} else if (pMap.containsKey(pKey)) {
			LoggerHelper.warn(String.format(
					"%s: putBefore failed! the map already has a key named '%s'", 
					CollectionHelper.class.getName(),
					pKey
				));
		} else if (pIndex < 0 || pIndex > pMap.size()) {
			LoggerHelper.warn(String.format(
					"%s: putBefore failed! the index %d is out of the range 0-%d", 
					CollectionHelper.class.getName(),
					pIndex,
					pMap.size()
				));
		} else {
			int i = 0;
			List<Entry<K, V>> rest = new ArrayList<Entry<K, V>>();
			for (Entry<K, V> entry : pMap.entrySet()) {
				if (i++ >= pIndex) {
					rest.add(entry);
				}
			}
			pMap.put(pKey, pValue);
			for (int j = 0; j < rest.size(); j++) {
				Entry<K, V> entry = rest.get(j);
				pMap.remove(entry.getKey());
				pMap.put(entry.getKey(), entry.getValue());
			}
		}
	}
	/**
	 * Put a new entry before an exists entry in linked hash map.
	 * @param pMap Map which used to put in.
	 * @param pReference Key of an exists entry.
	 * @param pKey Key of the new entry.
	 * @param pValue Value of the new entry.
	 */
	public static <K, V> void putBefore(LinkedHashMap<K, V> pMap, K pReference, K pKey, V pValue) {
		if (!pMap.containsKey(pReference)) {
			LoggerHelper.warn(String.format(
					"%s: putBefore failed! '%s' key wasn't found", 
					CollectionHelper.class.getName(),
					pKey
				));
		} else {
			int i = 0;
			for (Entry<K, V> entry : pMap.entrySet()) {
				if (entry.getKey().equals(pReference)) {
					break;
				}
				i++;
			}
			CollectionHelper.putAt(pMap, i, pKey, pValue);
		}
	}
	/**
	 * Put a new entry after an exists entry in linked hash map.
	 * @param pMap Map which used to put in.
	 * @param pReference Key of an exists entry.
	 * @param pKey Key of the new entry.
	 * @param pValue Value of the new entry.
	 */
	public static <K, V> void putAfter(LinkedHashMap<K, V> pMap, K pReference, K pKey, V pValue) {
		if (!pMap.containsKey(pReference)) {
			LoggerHelper.warn(String.format(
					"%s: putBefore failed! '%s' key wasn't found", 
					CollectionHelper.class.getName(),
					pKey
				));
		} else {
			int i = 0;
			for (Entry<K, V> entry : pMap.entrySet()) {
				i++;
				if (entry.getKey().equals(pReference)) {
					break;
				}
			}
			CollectionHelper.putAt(pMap, i, pKey, pValue);
		}
	}
	private CollectionHelper() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}