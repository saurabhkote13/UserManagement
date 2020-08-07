package com.assimilate.user.services;

public class LoginService {
	public Boolean validateUser(String username,String password)
	{
		if(username!=null && password !=null && username.equals(username) && password.equals(password))
		{
			return true;
		}
		return false;
	}

}