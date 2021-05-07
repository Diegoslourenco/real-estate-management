package com.gft.imobiliaria.repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Repository;

@Repository
public class FileSystemRepository {  

    public String save(byte[] content, String imageName) throws Exception {
    	
    	String resourcesDirectory = FileSystemRepository.class.getResource("/static/images/").getPath();
    	
    	if (resourcesDirectory.startsWith("/")) {
    		resourcesDirectory = resourcesDirectory.substring(1);
    	}
    	
        Path newFile = Paths.get(resourcesDirectory + imageName);
    	
        Files.createDirectories(newFile.getParent());
        Files.write(newFile, content);          
 
        return "/images/" + imageName;
    }
}
