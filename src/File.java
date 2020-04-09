
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**Класс файл с общими для файлов функционалом и характеристиками*/
public class File {
    String fileName;
    Directory directory;
    long  fileSize;
    String filePath;
    String fileExtension;

    public File(){

    }

    public File (Directory directory,String fileName){
        this.fileName=fileName.split("[.]")[0];
        this.directory=directory;
        this.fileExtension=fileName.split("[.]")[1];
        filePath=directory.directoryPath+"\\"+fileName;
    }

    public File (String filePath){
        this.filePath=filePath;
        if(Files.exists(Paths.get(filePath))==true){
            try {
                fileSize = Files.size(Paths.get(filePath));
            }
            catch (IOException e){
                System.out.println(e);
            }
        }
        else {
            try {
                Files.createFile(Paths.get(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String []ss=filePath.split("\\\\");
        fileName=ss[ss.length-1].split("[.]")[0];
        fileExtension=ss[ss.length-1].split("[.]")[1];
        String temp=new String();
        for(int i=0;i<ss.length-1;i++)
            temp=temp+ss[i]+"\\";
        directory=new Directory(temp);
    }

    public static File createFile(String filePath){
        File file=null;
        try{
            if(Files.exists(Paths.get(filePath))==false) {
                Files.createFile(Paths.get(filePath));
            }
            file = new File(filePath);

        }
        catch (IOException e){
            System.out.println(e);
        }
        return file;
    }

    public void renameFile(String newName){
        java.io.File f = new java.io.File(filePath);
        String newPath=directory.directoryPath+newName+"."+fileExtension;
        if(f.renameTo(new java.io.File(newPath))==true){
            fileName=newName;
            filePath=directory.directoryPath+"\\"+newName;
        }
    }

    public void deleteFile(){
        try {
            Files.deleteIfExists(Paths.get(filePath));
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }


}
