package com.revature.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.InternalErrorServer;
import com.revature.exceptions.UserNotFound;
import com.revature.models.User;
import com.revature.repositories.IUserDao;

@Service
public class UserServiceImpl implements IUserService {
	
	IUserDao ud;
	
	@Autowired
	public UserServiceImpl(IUserDao ud) {
		this.ud = ud;
	}

	@Override
	public User login(String username, String password) throws NoSuchAlgorithmException{				
		try {
			byte[] salt = getSalt(username);
			String securePassword = makeSecurePassword(password,salt);
			User u  = ud.findUserbyUsernameAndPassword(username, securePassword);
			if(u!=null) {
				u.setCartOwners(null);
				u.setCartShopper(null);
				u.setPassword(null);
				return u;
			}
			else {
				throw new UserNotFound();
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new InternalErrorServer();
		}
		
		
	}
	@Override
	public User register(User u) throws NoSuchAlgorithmException {
		byte[] salt = getSalt(u.getUsername());
		String securePassword = makeSecurePassword(u.getPassword(),salt);
		u.setPassword(securePassword);
		try {
			User newUser = ud.save(u);
			if(newUser!=null) {
				newUser.setCartOwners(null);
				newUser.setCartShopper(null);
				newUser.setPassword(null);				
			}
			return newUser;
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			throw new InternalErrorServer();
		}
		
		
	}


	private static byte[] getSalt(String userName) throws NoSuchAlgorithmException
	{
	    byte[] salt = (userName).getBytes();
	    return salt;
	}
	
	private static String makeSecurePassword(String passwordToHash,byte[] salt)
	    {
	        String generatedPassword = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-512");
	            md.update(salt);
	            byte[] bytes = md.digest(passwordToHash.getBytes());
	            StringBuilder sb = new StringBuilder();
	            for(int i=0; i< bytes.length ;i++)
	            {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            generatedPassword = sb.toString();
	        } 
	        catch (NoSuchAlgorithmException e) 
	        {
	            e.printStackTrace();
	        }
	        return generatedPassword;
	    }

	@Override
	public User getUser(int userId) {
		return ud.findById(userId).get();
	}
	
}
