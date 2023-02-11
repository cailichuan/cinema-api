package create_data;

import org.bouncycastle.jcajce.provider.symmetric.util.ClassUtil;
import org.junit.Test;

import java.io.File;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        /**
         * 多线程读取excel写入到数据库===========================================================
         */
        URL resource = ClassUtil.class.getClassLoader().getResource("create_data/dao");

        //获取包下面的class文件
        String[] classNames = new File(resource.getFile()).list();
        int i=0;
        for (String className : classNames) {
            i++;

            new WriteMysqlThread(className,i).start();

        }

        /**
         * =================================================================================
         */

    }


}
