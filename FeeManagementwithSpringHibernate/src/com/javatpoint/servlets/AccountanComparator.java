package com.javatpoint.servlets;

import java.util.Comparator;

import com.javatpoint.beans.AccountantBean;

public  class AccountanComparator implements Comparator<AccountantBean>{

	@Override
	public int compare(AccountantBean o1, AccountantBean o2) {
		// TODO Auto-generated method stub
		return  o1.getName().compareTo(o2.getName());
		
	}

}
