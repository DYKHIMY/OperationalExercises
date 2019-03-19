import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

// Created by IntelliJ IDEA.
//User: lhz
//Date: 2019/3/13 
//Time: 19:24
//To change this template use File | Settings | File Templates.
public class Exercises {
    int flag=0;
    public void setExercise() throws IOException {
        int opNum = (int) (Math.random() * 3) + 3;//运算符个数(3,4,5)
        int[] nums = new int[opNum + 1];//用来运算
        int[] viewnum = new int[opNum + 1];//用来显示
        for (int i = 0; i < opNum + 1; i++) {
            nums[i] = (int) (Math.random() * 100);
            viewnum[i] = nums[i];
        }//为参与运算的数字赋值

        char[] chars = {'+', '-', '*', '÷'};
        int[] operator = new int[opNum];//用来运算
        char[] chars1 = new char[opNum];//用来显示
        //先计算加减
        //新建两个数组，分别放算完乘除后的数字和运算符
        int[] calnum = new int[opNum + 1];
        int[] calope = new int[opNum];
        int cn = 0;
        int co = 0;
        for (int i = 0; i < opNum; i++) {
            operator[i] = (int) (Math.random() * 4);//随机出运算符
            if (operator[i] == 0 || operator[i] == 1) {
                calnum[cn] = nums[i];
                calope[co] = operator[i];
                cn++;
                co++;
            }
            if (operator[i] == 2) {
                nums[i + 1]= nums[i]*nums[i + 1];
            }
            if (operator[i] == 3) {
                while ((nums[i + 1] == 0)||((nums[i] % nums[i + 1]) != 0)  ) {//除号检验是否合理(两个条件的次序不能颠倒)
                    nums[i + 1] = (int)(Math.random() * 100);
                }
                viewnum[i + 1] = nums[i + 1];//若不合理需重新为数字赋值，对应的显示用的数组也要更新值

                nums[i + 1] =nums[i]/nums[i + 1];
            }

            chars1[i] = chars[operator[i]];//为字符数组赋对应的符号值
        }//为运算符赋值

        calnum[cn]=nums[opNum];//最后一个数直接放入计算数组calnum[]



        //最后的加减运算
        int result=0;
        int flag2=0;//假如没有加减运算，cn=0
        for (int i=0;i<cn;i++){
            flag2=1;
            if(i==0){
                if (calope[i]==0){
                    result=calnum[i]+calnum[i + 1];
                }
                if(calope[i]==1){
                    if (calnum[i]<calnum[i+1]){
                        flag=1;
                        return;
                    }else{
                        result=calnum[i]-calnum[i + 1];
                    }
                }
                continue;
            }
            if (calope[i]==0){
                result=result+calnum[i + 1];
            }
            if(calope[i]==1){
                if (result<calnum[i+1]){
                    flag=1;
                    return;
                }
                result=result-calnum[i + 1];
            }
        }
        if (flag2==0){
            result=calnum[0];
        }
        //输出最后结果
        String ex=new String();
        for (int i=0;i<opNum;i++){
            ex+=String.valueOf(viewnum[i]);
            ex+=String.valueOf(chars1[i]);
        }
        ex+=String.valueOf(viewnum[opNum]);
        ex+="=";
        ex+=result;
        System.out.println(ex);
        ex+="\r\n";//换行

        File file = new File("./result.txt");
        if (!file.exists())
            file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file,true);//将题目输出到文件，不覆盖
        byte[] bytes = ex.getBytes();
        fos.write(bytes);
    }
    public void setExercisesNum(int n) throws IOException {
        for (int i=0;i<n;i++){
            this.setExercise();
            if (this.flag==1){
                i--;
                this.flag=0;//成员变量还需变回0
            }
        }
    }
}
