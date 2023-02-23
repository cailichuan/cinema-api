package create_data;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import util.GetSnowId;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;



/**
 * 读取excel的文件写入mysql
 *
 */

@Slf4j
public class ReadExcel<T>  implements ReadListener<T> {

    //每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
    private static  final int BATCH_COUNT = 100;

    //缓存的数据
    private List<T> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    //sonwId的类
    GetSnowId getSnowId=null;

    //雪花算法的workId
    int workId;

    //marpper类的类名
    String mapperClassName;

    //参数类的类名
    String paramentClassName;


    //构造函数


    public ReadExcel(String mapperClassName,String paramentClassName,int workId){
        this.getSnowId=new GetSnowId();
        this.mapperClassName=mapperClassName;
        this.paramentClassName=paramentClassName;
        this.workId=workId;
    }

    //每一条数据解析都会调用这个函数
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
       // log.info("解析到一条数据:{}",t);


        //获取mapper的类型名，如User
        String[] classNameSplit = t.getClass().getName().split("\\.");
        String className = classNameSplit[classNameSplit.length - 1];




        //获取雪花id
        long id = getSnowId.setSnowIdType(workId).nextId();

        try {

            //执行setId方法
            Method setId = t.getClass().getDeclaredMethod("setId", Integer.class);
            setId.invoke(t,(int)id);


        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        System.out.println(t.toString());

        //将读取到的数据放入缓存中
        cachedDataList.add(t);

        //缓存达到这BATCH_COUNT条就写入数据库
        if (cachedDataList.size()>=BATCH_COUNT) {

            try {
                saveData();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }


            //清理缓存
            cachedDataList=ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }


    //所有数据解析完成就会调用这个函数
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
       //缓存数据为满100的情况处理
        if (cachedDataList.size()<BATCH_COUNT) {
            System.out.println("缓存数据未满，正在处理");
            try {
                saveData();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            //清理缓存
            cachedDataList=ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }

    }

    //数据存入数据库
    private void saveData() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // log.info("{}条数据，开始存储数据库！", cachedDataList.size());

        //存数据入数据库
        //写入数据库,Mybatis已经调为defaultExecutorType=BATCH（批处理模式）
        SqlSession sqlSession = MybatisUtils.getSqlSession();


        //取得mapper
        Object mapper = sqlSession.getMapper(Class.forName(mapperClassName));
        //System.out.println("mapper=:"+mapper.getClass().getName());
//        Method[] methods = mapper.getClass().getMethods();
//        for (Method method : methods) {
//            System.out.println(method.getName()+" =======");
//            Class<?>[] parameterTypes = method.getParameterTypes();
//            for (Class<?> parameterType : parameterTypes) {
//                System.out.println(parameterType.getName());
//            }
//
//        }

        //将缓存的数据插入数据库插入数据(调用insert方法)
        for (Object o : cachedDataList.stream().toArray()) {
            Method instert = mapper.getClass().getDeclaredMethod("insert", Class.forName(paramentClassName));
            instert.invoke(mapper, (T) o);
        }


        //提交事务
        sqlSession.commit();

        //关闭连接
        sqlSession.close();


    }
}
