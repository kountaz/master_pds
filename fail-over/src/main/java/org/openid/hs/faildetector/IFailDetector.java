package org.openid.hs.faildetector;

import java.util.List;

import com.mongodb.DBObject;
/**
 * 
 * @author openid
 *
 */
public interface IFailDetector extends Runnable {
	/**
	 * Return the list of adresses the implement should watch in an List of MongoDB's DBObject
	 * @return list of adresses the implement should watch in an List of MongoDB's DBObject
	 */
	public List<DBObject> WhichWatch();
	/**
	 * Try to check if component return by WhichWatch is Alive
	 * If the first didn't answer, recursively call the next
	 * @param target_rank On which object on the list the watch should start
	 */
	public void WatchOverIt(int target_rank);
	/**
	 * Send to everybody a masterdeclaration
	 */
	public void masterDeclaration();
	/**
	 * Send to the specific address the new master reference
	 * @param addrutil adresses targeted
	 * @param portutil TCP port targeted
	 */
	public void callMaster(String addrutil, String portutil);
}
