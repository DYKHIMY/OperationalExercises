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
        int opNum = (int) (Math.random() * 3) + 3;//���������(3,4,5)
        int[] nums = new int[opNum + 1];//��������
        int[] viewnum = new int[opNum + 1];//������ʾ
        for (int i = 0; i < opNum + 1; i++) {
            nums[i] = (int) (Math.random() * 100);
            viewnum[i] = nums[i];
        }//Ϊ������������ָ�ֵ

        char[] chars = {'+', '-', '*', '��'};
        int[] operator = new int[opNum];//��������
        char[] chars1 = new char[opNum];//������ʾ
        //�ȼ���Ӽ�
        //�½��������飬�ֱ������˳�������ֺ������
        int[] calnum = new int[opNum + 1];
        int[] calope = new int[opNum];
        int cn = 0;
        int co = 0;
        for (int i = 0; i < opNum; i++) {
            operator[i] = (int) (Math.random() * 4);//����������
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
                while ((nums[i + 1] == 0)||((nums[i] % nums[i + 1]) != 0)  ) {//���ż����Ƿ����(���������Ĵ����ܵߵ�)
                    nums[i + 1] = (int)(Math.random() * 100);
                }
                viewnum[i + 1] = nums[i + 1];//��������������Ϊ���ָ�ֵ����Ӧ����ʾ�õ�����ҲҪ����ֵ

                nums[i + 1] =nums[i]/nums[i + 1];
            }

            chars1[i] = chars[operator[i]];//Ϊ�ַ����鸳��Ӧ�ķ���ֵ
        }//Ϊ�������ֵ

        calnum[cn]=nums[opNum];//���һ����ֱ�ӷ����������calnum[]



        //���ļӼ�����
        int result=0;
        int flag2=0;//����û�мӼ����㣬cn=0
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
        //��������
        String ex=new String();
        for (int i=0;i<opNum;i++){
            ex+=String.valueOf(viewnum[i]);
            ex+=String.valueOf(chars1[i]);
        }
        ex+=String.valueOf(viewnum[opNum]);
        ex+="=";
        ex+=result;
        System.out.println(ex);
        ex+="\r\n";//����

        File file = new File("./result.txt");
        if (!file.exists())
            file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file,true);//����Ŀ������ļ���������
        byte[] bytes = ex.getBytes();
        fos.write(bytes);
    }
    public void setExercisesNum(int n) throws IOException {
        for (int i=0;i<n;i++){
            this.setExercise();
            if (this.flag==1){
                i--;
                this.flag=0;//��Ա����������0
            }
        }
    }
}
