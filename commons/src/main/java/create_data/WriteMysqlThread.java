package create_data;

import com.alibaba.excel.EasyExcel;
import org.springframework.boot.system.ApplicationHome;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;


/**
 * 多线程从excel读数据写入mysql的类
 */
public class WriteMysqlThread extends Thread{

    //接口类名
    String mapperClassName;

    //接口类
    Class mapperClass = null;

    //excel文件名
    String excelName;

    //雪花算法的workid
    int snowIdWorkId;




    /**
     * 有参构造，传入mapper的类名
     * 例如：User.Class
     * @param className
     */
    public WriteMysqlThread(String className,int workerId) throws ClassNotFoundException {

        this.excelName=className.split("\\.")[0];
        this.mapperClassName="create_data.dao."+this.excelName;

        //获取接口类名
        this.mapperClass = Class.forName(this.mapperClassName);

        //获取workId
       this.snowIdWorkId = workerId;

    }





    @Override
    public void run() {

        //获取方法
        Method[] methods = this.mapperClass.getMethods();

        //同名插入方法的统计
        int methodSum =0;

        //insert方法
        Method insertMothod=null;

        for (Method method : methods) {

            String methodName = method.getName();



            //统计insert方法的个数
            if (methodName.equals("insert")) {

                insertMothod=method;
                methodSum++;
            }
        }

        //如果同一个接口中inser方法超过1个，则退出
        if (methodSum>1 || methodSum==0){

            return;
        }


        //获取方法参数
        Type[] genericParameterTypes = insertMothod.getGenericParameterTypes();

        //如果方法参数超过两个则退出
        if (genericParameterTypes.length>1 || genericParameterTypes.length==0) {
            return;
        }

        //获取方法参数类型
        String insertParameterClassName=genericParameterTypes[0].getTypeName();


        //反射方法参数的类
        Class<?> insertParameterClass =null;
        try {
            insertParameterClass=Class.forName(insertParameterClassName);



        }catch (Exception e){

            return;
        }





        //        获取jar包所在的目录
        System.out.println("获取中=============");
        ApplicationHome homePath = new ApplicationHome(getClass());

        //获取xlsx文件所在路径
        String fileName=homePath.toString() + "/data_excel/" +excelName + ".xlsx";


        //开启easyExcel读取
        EasyExcel.read(fileName,insertParameterClass,new ReadExcel(mapperClassName,insertParameterClassName,snowIdWorkId)).sheet().doRead();

















    }
}
