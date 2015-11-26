package domain;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Administrator on 2015/11/15.
 * ʹԴͼƬ����ĵõ�ѹ��
 */
public class BitmapUtils {
    /**
     * @description ����ͼƬ��ѹ������
     *
     * @param options ����
     * @param reqWidth Ŀ��Ŀ��
     * @param reqHeight Ŀ��ĸ߶�
     * @return
     */
    private static int calculateInSampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight){
        // ԴͼƬ�ĸ߶ȺͿ��
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * @description ͨ�������bitmap������ѹ�����õ����ϱ�׼��bitmap
     *
     * @param src
     * @param dstWidth
     * @param dstHeight
     * @return
     */
    private static Bitmap createScaleBitmap(Bitmap src, int dstWidth, int dstHeight, int inSampleSize) {
       // ����ǷŴ�ͼƬ��filter�����Ƿ�ƽ�����������СͼƬ��filter��Ӱ�죬������������СͼƬ������ֱ������Ϊfalse
        Bitmap dst = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, false);
        if (src != dst) { // ���û�����ţ���ô������
            src.recycle(); // �ͷ�Bitmap��native��������
        }
        return dst;
    }

    public static Bitmap BitmapReduce(Bitmap bitmap ,int reqWidth, int reqHeight) {

        // �������� inJustDecodeBounds=true ����ȡͼƬ�ߴ�
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.outHeight = bitmap.getHeight();
        options.outWidth = bitmap.getWidth();

        // ���� inSampleSize ��ֵ
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // ���ݼ������ inSampleSize ������ͼƬ����Bitmap
        options.inJustDecodeBounds = false;


        return createScaleBitmap(bitmap, reqWidth, reqHeight, options.inSampleSize); // ͨ���õ���bitmap����һ���õ�Ŀ���С������ͼ
    }

}
