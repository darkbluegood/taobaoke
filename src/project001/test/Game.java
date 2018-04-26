package project001.test;

import project001.admin.impl.HibernateConfig;

public class Game extends HibernateConfig{
	private String[] string;

	public String[] getString() {
		return string;
	}

	public void setString(String[] string) {
		this.string = string;
	}
	public static void main(String args[]){
		System.out.println("333223");
	}
}
