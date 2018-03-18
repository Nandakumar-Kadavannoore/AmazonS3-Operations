/**
   Helper Methods for Amazon S3 Operations
   @author Nandakumar.K
**/


package com.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class S3Utils {
	
	private S3Utils() {
		
	}
	
	/**
	 * Helper method for getting file from server
	 * @param multipart
	 * @return file
	 * @throws IOException
	 */
	public static File multipartToFile(MultipartFile multipart) {
		try {
		String rootPath = System.getProperty("catalina.home");
		File dir = new File(rootPath + File.separator + "tmpFiles");
		if (!dir.exists())
			dir.mkdirs();
		// Create the file on server
		File serverFile = new File(dir.getAbsolutePath() + File.separator + multipart.getOriginalFilename());
		multipart.transferTo(serverFile);
		return serverFile;
		} catch (Exception ex) {
			/**
			 * Ignore
			 */
		}
		return null;
	}

}
