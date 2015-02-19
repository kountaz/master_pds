package org.openid.hs.datagrid;

/**
 * Enumeration of data states.
 * @author Steven, Victor
 * @version R3
 */
public final class DataState {
	/** Cache state. 							**/
	public static final byte 	CACHED						= (byte) 1;
	/** Expiration state. 						**/
	public static final byte 	EXPIRED					 	= (byte) 0;
	/** Persist state. 							**/
	public static final byte 	PERSISTED					= (byte) 1;
	/** Unpersist state. 						**/
	public static final byte 	UNPERSISTED					= (byte) 0;
	/** Cache and persist state. 				**/
	public static final byte[] 	CACHED_PERSISTED 			= {CACHED,PERSISTED};
	/** Cache and persist state string. 		**/
	public static final String 	CACHED_PERSISTED_STRING 	= "cached and persisted";
	/** Cache and unpersist state. 				**/
	public static final byte[] 	CACHED_UNPERSISTED 			= {CACHED,UNPERSISTED};
	/** Cache and unpersist state string. 		**/
	public static final String 	CACHED_UNPERSISTED_STRING 	= "cached and unpersisted";
	/** Expiration and persist state. 			**/
	public static final byte[] 	EXPIRED_PERSISTED 		 	= {EXPIRED,PERSISTED};
	/** Expiration and persist state string. 	**/
	public static final String 	EXPIRED_PERSISTED_STRING 	= "expired and persisted";
	/** Expiration and unpersist state. 		**/
	public static final byte[] 	EXPIRED_UNPERSISTED 		= {EXPIRED,UNPERSISTED};
	/** Expiration and unpersist state string. 	**/
	public static final String 	EXPIRED_UNPERSISTED_STRING 	= "expired and unpersisted";
	/** Unknown state string. */
	public static final String 	UNKNOWN_STRING				= "unknow";
	/**
	 * Creates state from given cached and persist flags.
	 * @param pCached Cache flag.
	 * @param pPersisted Persist flag.
	 * @return State corresponding to the given flags.
	 */
	public static byte []create(boolean pCached, boolean pPersisted) {
		return new byte[]{(pCached?CACHED:EXPIRED),(pPersisted?PERSISTED:UNPERSISTED)};
	}
	/**
	 * Returns true if given states are the same.
	 * @param pState State to compare.
	 * @param pStateReference Reference state.
	 * @return True if given states are the same or false.
	 */
	public static boolean is(byte []pState, byte []pStateReference) {
		if (pState.length != pStateReference.length) {
			return false;
		}
		for (int i = 0; i < pState.length; ++i) {
			if (pState[i] != pStateReference[i]) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Returns true if the given state is cached and persisted.
	 * @param pState State tested.
	 * @return True if the given state is cached and persisted or false.
	 */
	public static boolean isCachedPersisted(byte []pState) {
		return is(pState, CACHED_PERSISTED);
	}
	/**
	 * Returns true if the given state is cached and unpersisted.
	 * @param pState State tested.
	 * @return True if the given state is cached and unpersisted or false.
	 */
	public static boolean isCachedUnpersisted(byte []pState) {
		return is(pState, CACHED_UNPERSISTED);
	}
	/**
	 * Returns true if the given state is expired and persisted.
	 * @param pState State tested.
	 * @return True if the given state is expired and persist or false.
	 */
	public static boolean isExpiredPersisted(byte []pState) {
		return is(pState, EXPIRED_PERSISTED);
	}
	/**
	 * Returns true if the given state is expired and unpersisted.
	 * @param pState State tested.
	 * @return True if the given state is expired and unpersisted or false.
	 */
	public static boolean isExpiredUnpersisted(byte []pState) {
		return is(pState, EXPIRED_UNPERSISTED);
	}
	/**
	 * Returns state string corresponding to given state.
	 * @param pState State used.
	 * @return State string.
	 */
	public static String toString(byte []pState) {
		if (isCachedPersisted(pState))
			{ return CACHED_PERSISTED_STRING; }
		if (isCachedUnpersisted(pState))
			{ return CACHED_UNPERSISTED_STRING; }
		if (isExpiredPersisted(pState))
			{ return EXPIRED_PERSISTED_STRING; }
		if (isExpiredUnpersisted(pState))
			{ return EXPIRED_UNPERSISTED_STRING; }
		return UNKNOWN_STRING;
	}
	private DataState() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}
