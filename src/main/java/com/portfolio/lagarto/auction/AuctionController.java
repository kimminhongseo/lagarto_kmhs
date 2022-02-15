package com.portfolio.lagarto.auction;

import com.portfolio.lagarto.Const;
import com.portfolio.lagarto.MyFileUtils;
import com.portfolio.lagarto.model.AuctionDto;
import com.portfolio.lagarto.model.AuctionEntity;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/auction")
public class AuctionController {
    //저장할 경로 (게시판번호,파일명 제외한 상위 폴더
    public static String first_uploadDirectory = System.getProperty("user.dir") +"\\src\\main\\resources\\static\\uploadfile";


    @Autowired
    private AuctionService service;

    @Autowired
    private MyFileUtils fileUtils;

    //다중 파일 업로드를 위한 bean
    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }


    @GetMapping("/write")
    public String write(@ModelAttribute("auctionEntity") AuctionEntity auctionEntity) {
        System.out.println(auctionEntity.getIcategory());
        service.insAuctionList(auctionEntity);
        System.out.println(service.insAuctionList(auctionEntity));
        //여기선 찍힘
        return "/auction/write";
    }

    @PostMapping("/write")
    public String writeProc(@ModelAttribute("auctionEntity") AuctionEntity auctionEntity,@RequestParam("files") MultipartFile[] files){

        //문자배열 개념 값이 추가되면 쭉 이어짐.
        StringBuilder fileNames = new StringBuilder();
        //게시판번호 + 1 이 이번에 insert 되는것. uploadfile + iboard값 이라고 경로지정

            //todo: insAuctionList  null뜸 해결하기.
        String uploadDirectory = first_uploadDirectory + "/" + (service.insAuctionList(auctionEntity).getIboard()+1);
        if(service.insAuctionList(auctionEntity) == null){
        uploadDirectory = first_uploadDirectory + "/" + "1";
        }

        for(MultipartFile file: files){
            Path fileNameAndPath = Paths.get(uploadDirectory,file.getOriginalFilename());
            //폴더없다면? 만들어라
            fileUtils.makeFolders(uploadDirectory);

            //오리지널 이름을 추가해라 "/" 로 구분 할거임 >> UUID 랜덤파일명x
            fileNames.append(file.getOriginalFilename()+"/");
            try {
                Files.write(fileNameAndPath,file.getBytes());
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        //파일명 뽑아오기  "/" 기준으로 잘라오기
        String image = fileNames.toString();
        String images[] = image.split("/");
        //null을 5개줌
        List<String> imagesList = new ArrayList<String>();
        imagesList.add(null);
        imagesList.add(null);
        imagesList.add(null);
        imagesList.add(null);
        imagesList.add(null);
        try{
            for(int i=0; i< files.length; i++) {
                //값 찍기
                //값을 세팅해줌
                System.out.println("images[" + i + "] : " + images[i]);
                imagesList.set(0,images[0]);
                imagesList.set(1,images[1]);
                imagesList.set(2,images[2]);
                imagesList.set(3,images[3]);
                imagesList.set(4,images[4]);
            }
        }catch (IndexOutOfBoundsException e){
            //index관련 예외처리할때. 중요! 이거안쓰면 안먹혔음,,,ㅜㅜ
            System.out.println(e);

        }
        finally {
            //어차피 for문에서 files.length가 짧았다면 나머지 뒤는 null임
            auctionEntity.setImage1(imagesList.get(0));
            auctionEntity.setImage2(imagesList.get(1));
            auctionEntity.setImage3(imagesList.get(2));
            auctionEntity.setImage4(imagesList.get(3));
            auctionEntity.setImage5(imagesList.get(4));
            service.insAuction(auctionEntity);
            System.out.println(service.insAuctionList(auctionEntity));
            System.out.println("입력후 : "+imagesList);
        }

        //업로드한 상태 보여주는 창 >> 여기서 리스트로 가거나 자기가 쓴글로 가도록 만들자
        return "redirect:/auction/uploadstatusview"; //경매등록 눌렀을때 가는곳

    }


    @GetMapping("/list")
    public String list(AuctionDto dto, Model model) {

        model.addAttribute("List", service.selAuctionListAll(dto));
        System.out.println(service.selAuctionListAll(dto));
        return "auction/list";
    }


    @GetMapping("/list/{icategory}")
    public String list(@PathVariable int icategory, AuctionDto dto, Model model) {
        model.addAttribute("icategory");
        model.addAttribute("List", service.selAuctionList(dto));
        dto.setIcategory(icategory);
        return "auction/list";

    }

    @GetMapping("/detail")
    public String detail(AuctionDto dto, Model model) {
        model.addAttribute("Data", service.selAuctionDetail(dto));
        return "auction/detail";
    }

    @GetMapping("/mod")
    public String mod(AuctionDto dto,Model model){
        model.addAttribute(Const.DATA,service.selAuctionDetail(dto));

        return "auction/mod";
    }

    @PostMapping("/mod")
    public String modProc(@ModelAttribute("auctionEntity") AuctionEntity entity,@RequestParam("files") MultipartFile[] files){
        StringBuilder fileNames = new StringBuilder();

       String moduploadDirectory = first_uploadDirectory + "/" + entity.getIboard();
        //mod경로에 파일이나존재하면 삭제.
        File f = new File(moduploadDirectory);
//        if(f.isFile()){
//            fileUtils.delFile(moduploadDirectory);
//        }
        if(f.isDirectory()){
            fileUtils.delFolderFiles(moduploadDirectory,true);
        }


       for(MultipartFile file1: files){
            Path modfileNameAndPath = Paths.get(moduploadDirectory,file1.getOriginalFilename());
            //폴더 만들어준다
            fileUtils.makeFolders(moduploadDirectory);
            //파일 더한다.
            fileNames.append(file1.getOriginalFilename()+"/");
            try{
                Files.write(modfileNameAndPath,file1.getBytes());
            }catch (IOException e){
                e.printStackTrace();
            }
       }

        //파일명 뽑아오기  "/" 기준으로 잘라오기
        String image = fileNames.toString();
        String images[] = image.split("/");
        //null을 5개줌
        List<String> imagesList = new ArrayList<String>();
        imagesList.add(null);
        imagesList.add(null);
        imagesList.add(null);
        imagesList.add(null);
        imagesList.add(null);
        try{
            for(int i=0; i< files.length; i++) {
                //값 찍기
                //값을 세팅해줌
                System.out.println("images[" + i + "] : " + images[i]);
                imagesList.set(0,images[0]);
                imagesList.set(1,images[1]);
                imagesList.set(2,images[2]);
                imagesList.set(3,images[3]);
                imagesList.set(4,images[4]);
            }
        }catch (IndexOutOfBoundsException e){
            //index관련 예외처리할때. 중요! 이거안쓰면 안먹혔음,,,ㅜㅜ
            System.out.println(e);

        }
        finally {
            //어차피 for문에서 files.length가 짧았다면 나머지 뒤는 null임
            entity.setImage1(imagesList.get(0));
            entity.setImage2(imagesList.get(1));
            entity.setImage3(imagesList.get(2));
            entity.setImage4(imagesList.get(3));
            entity.setImage5(imagesList.get(4));
            service.updAuction(entity); //update
            System.out.println(service.updAuction(entity));
            System.out.println("입력후 : "+imagesList);
        }

        return "redirect:/auction/detail?iboard=" + entity.getIboard();
    }


}





