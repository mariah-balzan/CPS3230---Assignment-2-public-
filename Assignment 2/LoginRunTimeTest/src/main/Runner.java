package main;

import java.util.Random;

public class Runner{
	
	public static void main(String[] args) {
		final Runner m = new Runner();
		final Account account = m.new Account(false, false, 0);
		m.run(account);
	}
	
	public void invalidLogin() {
		System.out.println("Invalid login at: " + System.currentTimeMillis());
	}
	public void validLogin() {
		System.out.println("Valid login at: " + System.currentTimeMillis());
	}
	public void inAlertScreen() {
		System.out.println("In alert screen at: " + System.currentTimeMillis());
	}
	public void inLoginScreen() {
		System.out.println("In login screen at: " + System.currentTimeMillis());
	}
	
	public void run(final Account account) {
		final Random rand = new Random();
		
		while(true){
			final int randomNumber = rand.nextInt(10);
			
			if (randomNumber < 7){
				this.invalidLogin();
				account.setInvalidLogins(account.getInvalidLogins() + 1);
				if(account.getInvalidLogins() >= 3){
					account.setInLoginScreen(true);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					account.setInvalidLogins(0);
					account.setInLoginScreen(true);
				}
			} else {
				this.validLogin();
				account.setInvalidLogins(0);
				account.setInAlertScreen(true);
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public class Account{
		//stores number of invalidLogins
		private Integer invalidLogins;
		//boolean values of screens 
		private boolean alertScr;
		private boolean loginScr;
		
		public Account(final boolean alertScr, final boolean loginScr, final Integer invalidLogins) {
			super();
			this.alertScr = alertScr;
			this.loginScr = loginScr;
			this.invalidLogins = invalidLogins;
		}

		//Method for alert screens
		public boolean isInAlertScreen() {
			return alertScr;
		}

		public void setInAlertScreen(boolean alertScr) {
			if(alertScr){
				System.out.println("In Alert Screen");
			} else {
				System.out.println("Back to Login Screen!");
			}
			this.alertScr = alertScr;
		}
		
		//Method for login screens
		public boolean isInLoginScreen() {
			return loginScr;
		}

		public void setInLoginScreen(boolean loginScr) {
			if(loginScr){
				System.out.println("Back to Login Screen!");
			} else {
				System.out.println("In Alert Screen!");
			}
			this.loginScr = loginScr;
		}

		
		//Method for invalid log ins
		public Integer getInvalidLogins() {
			return invalidLogins;
		}

		public void setInvalidLogins(Integer invalidLogins) {
			this.invalidLogins = invalidLogins;
		}
	}
}