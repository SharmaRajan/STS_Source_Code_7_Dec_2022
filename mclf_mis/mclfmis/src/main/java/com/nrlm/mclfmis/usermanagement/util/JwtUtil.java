package com.nrlm.mclfmis.usermanagement.util;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.nrlm.mclfmis.usermanagement.constant.MyConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


@Component
public class JwtUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    //@Value("${azure.app.jwtSecret}")
    private String jwtSecret = MyConstant.JWT_SECRET_KEY;
    private Long jwtExpiration = MyConstant.JWT_EXPIRATION_SECONDS;
    
    
    
	public String generateToken(Long userId, String level) {
		Map<String, Object> claims = new HashMap<String, Object>();
		//claims.put("userLocations", locations);
		//claims.put("authorities", authorities);
		claims.put("userLevel", level);
		return createToken(claims,String.valueOf(userId));
	}
	private String createToken(Map<String,Object> claims,String subject) {
        return Jwts.builder()
        				.setClaims(claims)
		                .setSubject(subject)
		                .setIssuedAt(new Date(System.currentTimeMillis()))
		                .setExpiration(new Date(System.currentTimeMillis() + (jwtExpiration*1000)))
		                .signWith(SignatureAlgorithm.HS512, jwtSecret)
		                .compact();
    }
	
	public boolean validateToken(String token) {
		try {
			//return !isTokenExpired(token);
			return true;
			}

		catch (SignatureException e) {
           // logger.error("Invalid JWT signature -> Message: {} "+e.getMessage());
        } catch (MalformedJwtException e) {
          //  logger.error("Invalid JWT token -> Message: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
          //  logger.error("Expired JWT token -> Message: {}",e.getMessage());
        } catch (UnsupportedJwtException e) {
           // logger.error("Unsupported JWT token -> Message: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
           // logger.error("JWT claims string is empty -> Message: {}", e.getMessage());
        }
		return false;
	}
    
	public String validateApiTokenType(String token) {
		try {
			if(!isTokenExpired(token)) {
				return MyConstant.VALIDATED_JWT;
			}
		}
		catch (SignatureException e) {
            return MyConstant.SIGNATURE_EXCEPTION;
        } 
		catch (MalformedJwtException e) {
            return MyConstant.MALFORMED_JWT_EXCEPTION;
        } 
		catch (ExpiredJwtException e) {
            return MyConstant.EXPIRED_JWT_EXCEPTION;
        } 
		catch (UnsupportedJwtException e) {
            return MyConstant.UNSUPPORTED_JWT_EXCEPTION;
        } 
		catch (IllegalArgumentException e) {
            return MyConstant.ILLEGAL_ARGUMENT_EXCEPTION;
        }
		return MyConstant.JWT_EXCEPTION;
	}
	
	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}
	public String extractTokenUsername(String token) {
		String userName = null;
		try {
			userName = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token).getBody().getSubject();
		}
		catch(ExpiredJwtException ex) {
			userName = ex.getClaims().getSubject();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return userName;
	}
	public Claims extractAllClaims(String token) {
		return Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token).getBody();
		
	}
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		return 	extractAllClaims(token)
                .getExpiration();
	}
	

	/*public boolean validateToken(String token,UserDetails userDetails) {
		final String username = extractUsername(token);
		return (!isTokenExpired(token) && username.equals(userDetails.getUsername()) );
	}*/
   /* public boolean validateJwtToken(String authToken){
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken).getBody();
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} "+e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}",e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e.getMessage());
        }
        
        return false;
    }
    
    public String getEmpIdFromJwtToken(String token) {
        return Jwts.parser()
			                .setSigningKey(jwtSecret)
			                .parseClaimsJws(token)
			                .getBody().getSubject();
    }*/
}
