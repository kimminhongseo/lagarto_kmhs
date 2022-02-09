package com.portfolio.lagarto;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Component
public class MyFileUtils {

    public void makeFolders(String path){
        File folder = new File(path);
        if(!folder.exists()){ //폴더가 존재하지 않는다면?
            folder.mkdirs();    //폴더를 만들어라
        }
    }

    //폴더 삭제 >>안씀
    public void delFolderFiles(String path,boolean isDelFolder){
        //isDirectory 객체가 폴더다? >> true   아니다?>>  false
        //isFile 객체가 파일이다? true   아니다?>>  false

        File file = new File(path);
        if(file.exists()&& file.isDirectory()){
            File[] fileArr = file.listFiles(); //폴더가아닌 파일이면 listFiles를 못줌.
            for(File f: fileArr){
                if(f.isDirectory()){ //재귀처리 //자기 자신을 부른다.
                    delFolderFiles(f.getPath(),true);
                }else {
                    f.delete();
                }
            }
        }
        if(isDelFolder){file.delete();}
    }

    //파일 존재하면 삭제 >> 안씀
    public void delFile(String path){
        File f = new File(path);
        if(f.exists()){
            f.delete();
        }
    }


    //랜덤파일명 만들기
    public String getRandomFileNm(){
        return UUID.randomUUID().toString();
    }

    //확장자까지 있는 파일 보내줄때
    public String getRandomFileNm(String fileNm){
        return getRandomFileNm()+getExt(fileNm);
    }

    //확장자 구하기
    public String getExt(String fileNm){
        int lastIdx = fileNm.lastIndexOf(".");
        return fileNm.substring(lastIdx);
    }

    //파일 저장 > 저장된 파일명 리턴
    public String saveFile(String path, MultipartFile mf){
        makeFolders(path); //폴더 만들고
        String randomFileNm = getRandomFileNm(mf.getOriginalFilename()); //랜덤파일 불러와

        File targetFile = new File(path, randomFileNm);
        try{
            mf.transferTo(targetFile); //폴더가 없으면 폴더를 만들어준다?????
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return randomFileNm;

    }





}
