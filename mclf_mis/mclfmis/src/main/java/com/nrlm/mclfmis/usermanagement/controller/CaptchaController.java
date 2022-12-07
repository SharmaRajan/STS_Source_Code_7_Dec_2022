package com.nrlm.mclfmis.usermanagement.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nrlm.mclfmis.Response.FormResponse;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.usermanagement.constant.MyConstant;
import com.nrlm.mclfmis.usermanagement.service.CaptchaService;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

import java.awt.Color;
	import java.awt.Font;
	import java.awt.Graphics2D;
	import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
	import java.io.OutputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
	import javax.imageio.ImageIO;
	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServletResponse;

	@RestController
	@RequestMapping("/mclf/captcha")
	public class CaptchaController {
		
		private  Bucket bucket ;
		
		public CaptchaController() {
	        Bandwidth limit = Bandwidth.classic(MyConstant.API_LIMIT_NO, Refill.greedy(MyConstant.API_LIMIT_NO, Duration.ofMinutes(MyConstant.API_TIME_LIMIT)));
	        this.bucket = Bucket4j.builder()
	                .addLimit(limit)
	                .build();
	    }


		@Autowired
		CaptchaService captchaService;
		
		
		@GetMapping
		public ResponseEntity<?> createCaptcha(@RequestParam("sessionId") String sessionId) throws ServletException, IOException {
			
			if (bucket.tryConsume(1)) {
				
				int iTotalChars = 6;
				int iHeight = 40;
				int iWidth = 150;
				int padding = 5;
				Font fntStyle1 = new Font("Arial", Font.ITALIC, 30);
				//Random randChars = new Random();
				//String sImageCode = (Long.toString(Math.abs(randChars.nextLong()), 36)).substring(0, iTotalChars);
				String sImageCode = generateCaptchaString();
				boolean flag = captchaService.saveCaptcha(sessionId,sImageCode);
				Map<String,String> map = new HashMap<String,String>();
				//map.put(key, value)
				if(flag) {
					//BufferedImage biImage = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_INT_RGB);
					BufferedImage biImage = new BufferedImage(iWidth+(padding*2), iHeight+(padding*2), BufferedImage.TYPE_INT_RGB);
					Graphics2D g2dImage = (Graphics2D) biImage.getGraphics();
					g2dImage.setColor(Color.WHITE);
					g2dImage.setBackground(Color.WHITE);
					//g2dImage.setClip(padding, padding, iWidth, iHeight);
					//int iCircle = 15;
//					for (int i = 0; i < iCircle; i++) {
//						g2dImage.setColor(new Color(randChars.nextInt(255), randChars.nextInt(255), randChars.nextInt(255)));
//					}
					g2dImage.setFont(fntStyle1);
					for (int i = 0; i < iTotalChars; i++) {
						
						//g2dImage.setColor(new Color(randChars.nextInt(255), randChars.nextInt(255), randChars.nextInt(255)));
						if (i % 2 == 0) {
							g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * i, 24);
						} else {
							g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * i, 35);
						}
					}
					//OutputStream osImage = response.getOutputStream();
					//ImageIO.write(biImage, "jpeg", osImage);
					g2dImage.dispose();	
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					  ImageIO.write(biImage, "jpeg", baos);
					  byte[] fileContent = baos.toByteArray();
					//File f = new 
					//byte[] fileContent = FileUtils.readFileToByteArray(biImage);
					String encodedString = Base64.getEncoder().encodeToString(fileContent);
					
					map.put("captcha", encodedString);
					return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
				}
				map.put("captcha","");
			    return new ResponseEntity<Map<String,String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			else {
				return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
			}
		
			
		}
		
		private String generateCaptchaString() {
			Random random = new Random();
			int length = 7 + (Math.abs(random.nextInt()) % 3);

			StringBuffer captchaStringBuffer = new StringBuffer();
			for (int i = 0; i < length; i++) {
				int baseCharNumber = Math.abs(random.nextInt()) % 62;
				int charNumber = 0;
				if (baseCharNumber < 26) {
					charNumber = 65 + baseCharNumber;
				}
				else if (baseCharNumber < 52){
					charNumber = 97 + (baseCharNumber - 26);
				}
				else {
					charNumber = 48 + (baseCharNumber - 52);
				}
				if(charNumber == 108 || charNumber == 73)
					charNumber = charNumber-1;
				
				captchaStringBuffer.append((char)charNumber);
					
			}
			return captchaStringBuffer.substring(0,6);
		}

	}

