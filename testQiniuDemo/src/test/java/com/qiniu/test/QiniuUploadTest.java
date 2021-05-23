package com.qiniu.test;
import java.io.IOException;
import java.util.UUID;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.junit.Test;

public class QiniuUploadTest {

    //设置好账号的ACCESS_KEY和SECRET_KEY;这两个登录七牛账号里面可以找到
    String ACCESS_KEY = "jw1jci6MOwBsRXRdqlngpGg8CKpR8toxIrAY-0dX";
    String SECRET_KEY = "nvq5juyQb7KmWNwZowHhTGCDtfKVjVTcUFJvSI-m";
    //要上传的空间;对应到七牛上（自己建文件夹 注意设置公开）
    String bucketname = "soframe";
    //上传到七牛后保存的文件名
    String key = UUID.randomUUID().toString().replace("-", "");
    //上传文件的路径 ;本地要上传文件路径
    String FilePath = "E:\\img\\1620915996217.png";
    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //创建上传对象
    UploadManager uploadManager = new UploadManager(new Configuration(Zone.zone2()));
    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken(){
        return auth.uploadToken(bucketname);
    }

    //普通上传
    @Test
    public void upload() throws IOException{
      try {
        //调用put方法上传
        //注意：这里只是测试上传，后面在对接到项目中时，需要使用设置字符数组的方法。
        Response res = uploadManager.put(FilePath, key, getUpToken());
        //打印返回的信息
        System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
        }
    }

}


