package com.portfolio.lagarto.auction;

import com.portfolio.lagarto.MyFileUtils;
import com.portfolio.lagarto.model.AuctionDto;
import com.portfolio.lagarto.model.AuctionEntity;
import com.portfolio.lagarto.model.AuctionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileUploadController {
    public static String first_uploadDirectory = System.getProperty("user.dir") +"\\src\\main\\resources\\uploadfile";

    @Autowired
    private AuctionService service;

    @Autowired
    private MyFileUtils fileUtils;

    @GetMapping("/")
    public String UploadPage(Model model, AuctionEntity auctionEntity){
        return "uploadview";
    }

    @GetMapping("/upload")
    public void   upload(@ModelAttribute("auctionEntity") AuctionEntity auctionEntity){
        service.insAuctionList(auctionEntity);
        System.out.println(service.insAuctionList(auctionEntity));
    }


    @PostMapping("/upload")
    public String uploadProc(@ModelAttribute("auctionEntity") AuctionEntity auctionEntity,  Model model, @RequestParam("files") MultipartFile[] files){

        StringBuilder fileNames = new StringBuilder();
        //게시판번호 + 1 이 이번에 insert 되는것. uploadfile + iboard값 이라고 경로지정
        final String uploadDirectory = first_uploadDirectory + "/" + (service.insAuctionList(auctionEntity).getIboard()+1);


        for(MultipartFile file: files){
            Path fileNameAndPath = Paths.get(uploadDirectory,file.getOriginalFilename());
            //폴더없다면? 만들어라
            fileUtils.makeFolders(uploadDirectory);

            //오리지널 이름을 추가해라 "/" 로 구분 할거임
            fileNames.append(file.getOriginalFilename()+"/");
            try {
                Files.write(fileNameAndPath,file.getBytes());
            }catch (IOException e){
                e.printStackTrace();
            }
        }




/*
        String[] imageList = new String[5];
        imageList[0] ="0";
        imageList[1] ="0";
        imageList[2] ="0";
        imageList[3] ="0";
        imageList[4] ="0";



        String image1 = images[0];
        String image2 = images[1];
        String image3 = images[2];
        String image4 = images[3];
        String image5 = images[4];


        //index로 값찾아서 수정
        imageList[0] = image1;
        imageList[1] = image2;
        imageList[2] = image3;
        imageList[3] = image4;
        imageList[4] = image5;


*/


   /*
        //리스트로 만들어 0 >> 5개
        List<String> imageList = new ArrayList<String>();
        imageList.add("0");
        imageList.add("0");
        imageList.add("0");
        imageList.add("0");
        imageList.add("0");

        System.out.println(imageList);
*/

        //만약 images[?] 값이 없으면 ""
        //for문으로 images 이름들그대로 DB에 저장했음.

        //파일명 뽑아오기  "/" 기준으로 잘라오기
        String image = fileNames.toString();
        String images[] = image.split("/");
        List<String> imagesList = new ArrayList<String>();
        imagesList.add("0");
        imagesList.add("0");
        imagesList.add("0");
        imagesList.add("0");
        imagesList.add("0");


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


        /*
        auctionEntity.setImage1(images[0]);
        auctionEntity.setImage2(images[1]);
        auctionEntity.setImage3(images[2]);
        auctionEntity.setImage4(images[3]);
        auctionEntity.setImage5(images[4]);
        */


/*
         service.insAuction(auctionEntity); //insert.
        System.out.println(service.insAuctionList(auctionEntity));

 */
        model.addAttribute("msg","얘네들 업로드 성공" + fileNames.toString());

        return "redirect:/uploadstatusview";
    }

    @GetMapping("/uploadstatusview")
    public String status(AuctionEntity auctionEntity,Model model){


        model.addAttribute("ins1",service.insAuctionList(auctionEntity));
        System.out.println(service.insAuctionList(auctionEntity));



        return "/uploadstatusview";
    }



}
