package com.gft.imobiliaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gft.imobiliaria.model.Imagem;
import com.gft.imobiliaria.model.Imovel;
import com.gft.imobiliaria.repository.FileSystemRepository;
import com.gft.imobiliaria.repository.ImagensRepository;

@Service
public class ImagemService {
	
	@Autowired
	FileSystemRepository files;
	
	@Autowired
	ImagensRepository imagens;
	
	public void save(MultipartFile image, Imovel imovel) throws Exception {
		String location = files.save(image.getBytes(), image.getOriginalFilename());
		
		imagens.save(new Imagem(image.getOriginalFilename(), location, imovel));
	}
}
