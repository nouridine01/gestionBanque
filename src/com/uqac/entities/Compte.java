package com.uqac.entities;

public class Compte {
	private int code;
	private double solde;
	
	public void verser(double mt){
		 solde +=mt;
		
	}
	
	public void retirer(double mt){
		 solde-=mt;
	}

	public Compte(int code, double solde) {
		super();
		this.code = code;
		this.solde = solde;
	}

	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCode() {
		return code;
	}

	public double getSolde() {
		return solde;
	}
	
	public void setCode(int i){
		code =i;
	}

	public String toString() {
		return "Compte [code=" + code + ", solde=" + solde + "]";
	}

	
	
	

}
