package com.ced.filemodule.service;

import com.ced.filemodule.dto.FichierUpdateDto;
import com.ced.filemodule.model.Fichier;
import com.ced.filemodule.repository.FileRepo;
import com.ced.filemodule.utils.MediaTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {

    static String fileDir = System.getProperty("user.dir")+"/file-module/src/main/resources/static/documents";
    private UUID firstPath;
    @Autowired
    private FileRepo fileRepo;
    @Autowired
    private ServletContext servletContext;

    public Fichier getById(Long id){
        return  fileRepo.findById(id).get();
    }

    public Fichier save(MultipartFile file_binaire) throws IOException {
        String docFullName = completeNameAndSaveInFolder(file_binaire);
//        String docImage = createShot(file_binaire);
        Fichier file =new Fichier();
        file.setName(docFullName);
//        file.setImage_name(docImage);
        Fichier filePostsave = fileRepo.save(file);
        return filePostsave;
    }

    public String completeNameAndSaveInFolder(MultipartFile file) throws IOException {
        String docPath = null;
        docPath = file.getOriginalFilename();
        String docFullName= firstPath.randomUUID().toString()+"-"+docPath;
        Path docPathAndName = Paths.get(fileDir, docFullName);
        Files.write(docPathAndName, file.getBytes());
        return docFullName;
    }
/*
    private String createShot(MultipartFile file) throws IOException {
        File convFile = new File( file.getOriginalFilename() );
        FileOutputStream fos = new FileOutputStream( convFile );
        fos.write( file.getBytes() );
        fos.close();
        RandomAccessFile raf = new RandomAccessFile(convFile, "rwd");
        FileChannel channel = raf.getChannel();
        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());
        PDFFile pdf = new PDFFile(buf);
        PDFPage page = pdf.getPage(0);
        Rectangle rect = new Rectangle(0, 0, (int) page.getBBox().getWidth(),
                (int) page.getBBox().getHeight());
        BufferedImage bufferedImage = new BufferedImage(rect.width, rect.height,
                BufferedImage.TYPE_INT_RGB);

        Image image = page.getImage(rect.width, rect.height,    // width & height
                rect,                       // clip rect
                null,                       // null for the ImageObserver
                true,                       // fill background with white
                true                        // block until drawing is done
        );
        Graphics2D bufImageGraphics = bufferedImage.createGraphics();
        bufImageGraphics.drawImage(image, 0, 0, null);
        String imageName = firstPath.randomUUID().toString()+"-shot.jpg";
        ImageIO.write(bufferedImage, "JPG", new File( Paths.get(fileDir+"/images", imageName).toString() ));
        if(convFile.delete()){
            System.out.println("le fichier ets supprimé avec succes");
        }else{
            System.out.println("Echec le fichier n'ets pas supprimé avec succes");
        }
        return imageName;
    }
*/
    public ResponseEntity<InputStreamResource> get(String name) throws FileNotFoundException {
        File file = new File(fileDir, name);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, name);
        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }

    public int update(FichierUpdateDto dto) throws IOException {
        if(dto.getFile()!=null){
            File oldFile = new File(fileDir+"/"+dto.getOldName());
            if(oldFile.delete()){
                Fichier fichier = new Fichier(dto.getId(), dto.getOldName());
                String newName = completeNameAndSaveInFolder(dto.getFile());
                fichier.setName(newName);
                fileRepo.save(fichier);
                return 1;
            }
            return -1;
        }
        return 1;
    }

    public int delete(String name){
        Fichier fichier = fileRepo.findByName(name);
        File file = new File(fileDir+"/"+name);
        if(file.delete()){
            fileRepo.delete(fichier);
            return 1;
        }
        return -1;
    }

    public int deleteById(Long id) {
        if(!fileRepo.existsById(id)) return -1;
        Fichier fichier = fileRepo.getById(id);
        return delete(fichier.getName());
    }
}
