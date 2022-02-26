package com.portfolio.lagarto;


import com.portfolio.lagarto.customer.files.AttachDTO;
import com.portfolio.lagarto.customer.files.AttachFileException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class MyFileUtils {

    public void makeFolders(String path){
        File folder = new File(path);
        if(!folder.exists()){ //폴더가 존재하지 않는다면?
            folder.mkdirs();    //폴더를 만들어라
        }
    }

    //폴더 삭제
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

    //파일 존재하면 삭제
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

// ----------------------------------------------------------------------------------------
    /** 업로드 경로 */
    private final String uploadPath1 = Paths.get("C:", "develop", "upload").toString();

    private final String uploadPath = System.getProperty("user.dir") + "/src/main/resources/static/uploadfile/customer/";

    /**
     * 서버에 생성할 파일명을 처리할 랜덤 문자열 반환
     * @return 랜덤 문자열
     */
    private final String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 서버에 첨부 파일을 생성하고, 업로드 파일 목록 반환
     * @param files    - 파일 Array
     * @param iboard - 게시글 번호
     * @return 업로드 파일 목록
     */
    public List<AttachDTO> uploadFiles(MultipartFile[] files, int iboard) {

        /* 업로드 파일 정보를 담을 비어있는 리스트 */
        List<AttachDTO> attachList = new ArrayList<>();

        /* uploadPath에 해당하는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉터리를 생성 */
        File dir = new File(uploadPath + "/" + iboard);
        if (dir.exists() == false) {
            dir.mkdirs();
        }

        /* 파일 개수만큼 forEach 실행 */
        for (MultipartFile file : files) {
            if(file.getSize() < 1) {
                continue;
            }
            try {
                /* 파일 확장자 */
                final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
                /* 서버에 저장할 파일명 (랜덤 문자열 + 확장자) */
                final String saveName = getRandomString() + "." + extension;

                /* 업로드 경로에 saveName과 동일한 이름을 가진 파일 생성 */
                File target = new File(uploadPath + "/" + iboard, saveName);
                file.transferTo(target);

                /* 파일 정보 저장 */
                AttachDTO attach = new AttachDTO();
                attach.setIboard(iboard);
                attach.setOriginal_name(file.getOriginalFilename());
                attach.setSave_name(saveName);
                attach.setSize((int) file.getSize());

                /* 파일 정보 추가 */
                attachList.add(attach);

            } catch (IOException e) {
                throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file...");

            } catch (Exception e) {
                throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file...");
            }
        } // end of for

        return attachList;
    }




}
