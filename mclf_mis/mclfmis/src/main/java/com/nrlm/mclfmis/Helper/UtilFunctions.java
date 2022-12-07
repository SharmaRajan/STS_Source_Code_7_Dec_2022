package com.nrlm.mclfmis.Helper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilFunctions {
	
	@Value("#{systemProperties['application.log.path']}")
	static String applicationLogDir;

	private static Logger logger = LoggerFactory.getLogger(UtilFunctions.class);

	public static String printJsonObject(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;

	}

	/**
	 * This method is used to print the request and response JSON for the Web
	 * Services defined for PAV.
	 * 
	 * @param object
	 * @param wsName
	 * @author Hemant_Sharma1
	 */
	public static void printJsonObject(Object object, String wsName) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonString = mapper.writeValueAsString(object);
			logger.info(wsName + " data is " + jsonString);
		} catch (JsonProcessingException e) {
			logger.error("Exception Occured",e);
			}

	}

	public static byte[] compressResponse(Object object) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		GZIPOutputStream gos = new GZIPOutputStream(os);
		ObjectOutputStream obj = new ObjectOutputStream(gos);
		obj.writeObject(object);
		obj.close();
		byte[] compressed = os.toByteArray();
		return compressed;
	}

	/*@SuppressWarnings("unchecked")
	public static List<BilFieldDetDTO> decompressResponse(byte[] bytes) throws IOException, ClassNotFoundException {
		ByteArrayInputStream input = new ByteArrayInputStream(bytes);
		GZIPInputStream inputStream = new GZIPInputStream(input);
		ObjectInputStream obj = new ObjectInputStream(inputStream);
		List<BilFieldDetDTO> response = (List<BilFieldDetDTO>) obj.readObject();
		obj.close();
		return response; 
	}*/
	
/*	public static String logExceptionDump(Exception exception) {

        java.util.Date date = new java.util.Date();
        StringBuilder result = new StringBuilder();
        try {
               FileUtils.writeStringToFile(new File(applicationLogDir + Constants.ERROR_LOG_FILE), new Timestamp(date.getTime()) + "|"
                            + exception.toString() + "\n", true);

               for (Throwable cause = exception; cause != null; cause = cause.getCause()) {
                     if (result.length() > 0)
                            result.append("Caused by: ");
                     result.append(cause.getClass().getName());
                     result.append(": ");
                     result.append(cause.getMessage());
                     result.append("\t");
                     for (StackTraceElement element : cause.getStackTrace()) {
                            result.append(" at ");
                            result.append(element.getMethodName());
                            result.append("(");
                            result.append(element.getFileName());
                            result.append(":");
                            result.append(element.getLineNumber());
                            result.append(")\t");
                     }
               }
               FileUtils.writeStringToFile(new File(applicationLogDir + Constants.ERROR_LOG_FILE), new Timestamp(date.getTime()) + "|"
                            + result.toString() + "\n", true);
        } catch (IOException e) {
               e.printStackTrace();
               
        }
        return result.toString();
 }*/
	public static void logSaveApproveReqResp(Object object,String process,String methodName){
		java.util.Date date= new java.util.Date();
		try {
			FileUtils.writeStringToFile(new File(applicationLogDir+process+".log"),new Timestamp(date.getTime())+"|"+methodName+"|"+UtilFunctions.printJsonObject(object)+"\n",true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static String replaceNull(String originalValue, String replaceValue) {
		return (originalValue == null ? replaceValue : originalValue);
	}


	/**
	 * @return the applicationLogDir
	 */
	public static String getApplicationLogDir() {
		return applicationLogDir;
	}

	/**
	 * @param applicationLogDir the applicationLogDir to set
	 */
	public static void setApplicationLogDir(String applicationLogDir) {
		UtilFunctions.applicationLogDir = applicationLogDir;
	}
}

	

