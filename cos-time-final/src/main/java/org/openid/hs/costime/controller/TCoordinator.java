package org.openid.hs.costime.controller;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TCoordinator 
{
	private Lock lock;
	
	public TCoordinator()
	{
		lock = new ReentrantLock();
	}
	
	
	public void begin()
	{
		lock.lock();
	}
	
	
	public void rollback()
	{
		lock.unlock();
	}
	
	
	public void commit()
	{
		lock.unlock();
	}
}
