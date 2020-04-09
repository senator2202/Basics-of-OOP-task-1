
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**Кдасс текстовый файл, с константный полем fileExtension, и чуть более расширенным функционалом,
 *  в отличие он класса предка File */
public class TextFile extends File {
    public final String fileExtension="txt";

    public TextFile(){
        super();
    }

    public TextFile(String fileName){
        super(fileName+".txt");
    }


    public void readFile(){
        try {
            List<String> list=Files.readAllLines(Paths.get(filePath));
            for (String s: list) {
                System.out.println(s);
            }
        }
        catch (IOException e){
            System.out.println(e);
        }
    }

    public void addText(String text){
        try {
            Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }


    /**Создать объект класса Текстовый файл, используя классы Файл, Директория. Методы: создать, переименовать,
     * вывести на консоль содержимое, дополнить, удалить.*/
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        String temp="";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TextFile tf;

        System.out.println("Введите имя текстового файла: ");
        try {
            temp=reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tf=new TextFile(temp);

        String menu="1-вывести на конслоль содержимое" +
                "\n2-дополнить" +
                "\n3-переименовать файл" +
                "\n4-удалить" +
                "\n0-выход";
        System.out.println(menu);
        int choice=s.nextInt();
        while (choice!=0) {
            switch (choice) {
                case 1:
                    tf.readFile();
                    break;
                case 2:
                    try {
                        System.out.println("Введите текст, который добавится в конец файла");
                        temp=reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    tf.addText("\n"+temp);
                    break;
                case 3:
                    System.out.println("Введите ново еимя файла: ");
                    try {
                        temp=reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    tf.renameFile(temp);
                    break;
                case 4:
                    tf.deleteFile();
                    break;
                case 0:
                    System.exit(0);
            }
            System.out.println("\n"+menu);
            choice=s.nextInt();
        }
    }

}
