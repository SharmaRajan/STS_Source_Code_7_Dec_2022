package com.nic.nrlm_aajeevika.configuration;

import com.nic.nrlm_aajeevika.usermanagement.util.MD5Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;




public class CustomPasswordEncoder implements PasswordEncoder {

//    @Autowired
//    private MD5Encoder md5Encoder;


	@Override
	public String encode(CharSequence plainTextPassword) {
		return plainTextPassword.toString();
		
        //return BCrypt.hashpw(plainTextPassword.toString(),BCrypt.gensalt(10));
    }

    @Override
    public boolean matches(CharSequence encryptedPassword, String passwordInDatabase) {
    	 RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
    	    ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
    	    HttpServletRequest request = attributes.getRequest();
    	    HttpSession httpSession = request.getSession(true);
    	    		
    	    try {
    	    	
    	    	    if(encryptedPassword.toString().equalsIgnoreCase(passwordInDatabase)) {
    	    	    	return true;
    	    	    }
    	    	 return false;
    	    }
    	    catch(Exception e) {
    	    	return false;
    	    }
    }
    

}
