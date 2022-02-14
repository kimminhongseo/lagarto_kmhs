package com.portfolio.lagarto.auction;

import com.portfolio.lagarto.MyFileUtils;
import com.portfolio.lagarto.model.AuctionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileUploadController {
    public static String first_uploadDirectory = System.getProperty("user.dir") +"\\src\\main\\resources\\static\\uploadfile";

    @Autowired
    private AuctionService service;

    @Autowired
    private MyFileUtils fileUtils;

    @GetMapping("/")
    public String UploadPage(){
        return "main";
    }

    @GetMapping("/upload")
    public void   upload(@ModelAttribute("auctionEntity") AuctionEntity auctionEntity){
        service.insAuctionList(auctionEntity);
        System.out.println(service.insAuctionList(auctionEntity));
    }


    @PostMapping("/upload")
    public String uploadProc(@ModelAttribute("auctionEntity") AuctionEntity auctionEntity,  @RequestParam("files") MultipartFile[] files){

        StringBuilder fileNames = new StringBuilder();
        //게시판번호 + 1 이 이번에 insert 되는것. uploadfile + iboard값 이라고 경로지정
        // todo:ifnull(max(iboard),1) 사용하면 가능하려나???
        final String uploadDirectory = first_uploadDirectory + "/" + (service.insAuctionList(auctionEntity).getIboard()+1);


        for(MultipartFile file: files){
            Path fileNameAndPath = Paths.get(uploadDirectory,file.getOriginalFilename());
            //폴더없다면? 만들어라
            fileUtils.makeFolders(uploadDirectory);

            //오리지널 이름을 추가해라 "/" 로 구분 할거임
            fileNames.append(file.getOriginalFilename()+"/");
            try {
                //todo:게시판 번호로 하지말고 파일이름 같으면 파일추가하지 말고  다르면 파일을 추가해라.
                Files.write(fileNameAndPath,file.getBytes());
            }catch (IOException e){
                e.printStackTrace();
            }
        }


        //만약 images[?] 값이 없으면 ""
        //for문으로 images 이름들그대로 DB에 저장했음.

        //파일명 뽑아오기  "/" 기준으로 잘라오기
        String image = fileNames.toString();
        String images[] = image.split("/");
        List<String> imagesList = new ArrayList<String>();
        imagesList.add(null);
        imagesList.add(null);
        imagesList.add(null);
        imagesList.add(null);
        imagesList.add(null);
        try{
            for(int i=0; i< files.length; i++) {
                //값 찍기
                System.out.println("images[" + i + "] : " + images[i]);
                imagesList.set(0,images[0]);
                imagesList.set(1,images[1]);
                imagesList.set(2,images[2]);
                imagesList.set(3,images[3]);
                imagesList.set(4,images[4]);
            }
        }catch (IndexOutOfBoundsException e){
            //index관련 예외처리할때.
            System.out.println(e);

        }
            finally {
            auctionEntity.setImage1(imagesList.get(0));
            auctionEntity.setImage2(imagesList.get(1));
            auctionEntity.setImage3(imagesList.get(2));
            auctionEntity.setImage4(imagesList.get(3));
            auctionEntity.setImage5(imagesList.get(4));
            service.insAuction(auctionEntity);
            System.out.println(service.insAuctionList(auctionEntity));
            System.out.println("입력후 : "+imagesList);
        }


        return "redirect:/auction/uploadstatusview";
    }

    @GetMapping("/auction/uploadstatusview")
    public String status(AuctionEntity auctionEntity,Model model){


        model.addAttribute("ins1",service.insAuctionList(auctionEntity));
        System.out.println(service.insAuctionList(auctionEntity));


        return "/auction/uploadstatusview";
    }



}
