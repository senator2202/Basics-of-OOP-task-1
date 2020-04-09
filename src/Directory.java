
import java.io.FileFilter;
import java.util.ArrayList;

/**Класс директория, содержит стандартный набор полей для описания директории,
 * а также списки хранящихся в ней папок и файлов*/
public class Directory {
    Directory topDirectory;//родительская директория
    String directoryName;//имя директории
    String directoryPath;//полный путь директории
    ArrayList<String> folders;//список папок в дииректории
    ArrayList<String> files;//список файлов в директории

    public Directory(){
        folders=new ArrayList<String>();
        files=new ArrayList<String>();
    }

    public Directory(Directory topDirectory, String directoryName){
        this.topDirectory=topDirectory;
        this.directoryName=directoryName;
        folders=new ArrayList<String>();
        files=new ArrayList<String>();
        if(topDirectory!=null)
            this.directoryPath=topDirectory.getDirectoryPath()+"\\"+directoryName;
        else
            this.directoryPath=directoryName;
        init();
    }

    public Directory(String directoryPath, String directoryName){
        this.directoryPath=directoryPath;
        this.directoryName=directoryName;
        init();
        this.directoryPath+=directoryName;
        createDirectory();
    }//создание новой директории в существующей

    public Directory(String directoryPath){
        this.directoryPath=directoryPath;
        this.directoryName=directoryPath.split("\\\\")[directoryPath.split("\\\\").length-1];
        init();
    }//открытие существующей директории

    void init(){
        folders=new ArrayList<String>();
        files=new ArrayList<String>();
        initTopDirectory();
        initFolders();
        initFiles();
        int i=1;
    }

    public String getDirectoryPath(){
        return  directoryPath;
    }

    void initFolders(){
        java.io.File []dirs=new java.io.File(directoryPath).listFiles(new FileFilter() {
            @Override
            public boolean accept(java.io.File pathname) {
                return pathname.isDirectory();
            }
        });

        if (dirs!=null)
            for(int i=0;i<dirs.length;i++) {
                folders.add(dirs[i].getName());
        }
    }//инициализация папок в директории

    void initFiles(){
        java.io.File []dirs=new java.io.File(directoryPath).listFiles(new FileFilter() {
            @Override
            public boolean accept(java.io.File pathname) {
                return pathname.isFile();
            }
        });

        if (dirs!=null)
            for(int i=0;i<dirs.length;i++) {
                files.add(dirs[i].getName());
        }
    }//инициализация файлов в диектории

    void initTopDirectory(){
        String []ss=directoryPath.split("\\\\");
        Directory temp=new Directory((Directory)null,ss[0]);
        for(int i=1;i<ss.length-1;i++) {
            Directory cycleD=new Directory(temp,ss[i]);
            temp=cycleD;
        }
        this.topDirectory=temp;
    }//инициализируем родительскую директорию

    void createDirectory(){
        java.io.File f=new java.io.File(directoryPath);
        f.mkdir();
    }

    public ArrayList<String> getFolders(){
        return folders;
    }

    public ArrayList<String> getFiles(){
        return files;
    }

}
