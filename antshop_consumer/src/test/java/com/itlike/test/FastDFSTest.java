package com.itlike.test;

/**
 * @author : 迟彪
 * @date : 2019/8/7
 */

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.FileNotFoundException;
import java.io.IOException;


public class FastDFSTest {
    public static void main(String[] args) throws FileNotFoundException, IOException, MyException {
//		初始化全局配置。加载一个配置文件。
        ClientGlobal.init("D:\\java\\IntelliJIDEA2018.2.7\\workspace\\SpringBoot_Dubbo_AntShop\\antshop_consumer\\src\\main\\resources\\client.conf");
//		3、创建一个TrackerClient对象。
        TrackerClient client = new TrackerClient();
//		4、创建一个TrackerServer对象。
        TrackerServer trackerServer = client.getConnection();
//		5、声明一个StorageServer对象，null。
        StorageServer storageServer = null;
//		6、获得StorageClient对象。
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
//		7、直接调用StorageClient对象方法上传文件即可。
        String[] arr = storageClient.upload_file("C:\\Users\\chibiao\\Pictures\\chen-java.png", "png", null);
        for (String string : arr) {
            System.out.println(string);
        }
    }
}
