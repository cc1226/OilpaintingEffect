package data;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * User: XuJunjie(Xujj@ysion.com)
 * Date: 2015-11-18
 * Time: 10:26
 *����ͼƬ
 */
public class PictureSave {

    /**
     * Sava Picture in load
     * @param bitmap
     * @return null
     */
    public void SavePicture(Context context,Bitmap bitmap){
        //����ϵͳͼ�⡣
        //MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "title", "description");

        //����ͼƬ
        File appDir = new File( Environment.getExternalStorageDirectory(),"Oil Painting Effect"); //�洢·��
        if(!appDir.exists()){
            appDir.mkdir(); //����������
        }
       String fileName = System.currentTimeMillis()+".jpg";//�ļ���Ϊϵͳʱ��
        File file = new File(appDir,fileName); //���ļ�����appDir��

        try {
            FileOutputStream fos = new FileOutputStream(file); //�ļ�д��
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos); //д��fos��
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //���ļ�����ϵͳ���
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),file.getAbsolutePath(),fileName,null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //֪ͨͼ�����
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,	Uri.fromFile(new File(file.getPath()))));
    }

}
