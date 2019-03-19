import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String studyID="2017011907\r\n";
        System.out.println("请输入题目数量：");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        try {
            //先输出学号到文件，覆盖之前的
            File file = new File("./result.txt");
            if (!file.exists())
                file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] bytes = studyID.getBytes();
            fos.write(bytes);
            Exercises exercises=new Exercises();
            exercises.setExercisesNum(n);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("出题完毕！");
    }
}
