/**
 * S3 Operations
 * @author Nandakumar.K
 */
package com.service;

import java.io.File;

public interface S3Service {
	
    void uploadFile(String bucketName, String keyName, File file);
    
    boolean deleteFromBucket(String bucketName, String keyName);


}
