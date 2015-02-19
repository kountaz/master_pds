package org.openid.hs.feature.controller;


public class Task implements Runnable{

	ScenarioOfTask s;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("je suis lancée");
		s=new ScenarioOfTask();
		s.run();
		// arreter le thread s au bout de 2h

		
	}
		
	
}
