package com.yunwei.library.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextPaint;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Package: com.yunwei.library.utils
 * @Description:Bitmap处理工具类
 * @author: hezhi
 * @date: 2016-05-30
 * @Time: 11:31
 * @version: V1.0
 */
public class BitmapUtils {

    private BitmapUtils() {/* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 计算图片的缩放值
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * 获取截图
     */
    public static Bitmap shot(View v) {
        v.setDrawingCacheEnabled(true);
        Bitmap bmp = v.getDrawingCache();
        return bmp;
    }

    /**
     * 获取截图 并保存
     *
     * @param context
     * @return
     * @author hefeng
     */
    public static Bitmap shot(View v, Context context) {
        Bitmap bmp = null;
        Bitmap bm = null;
        View vv = null;
        vv = v.getRootView();
        vv.setDrawingCacheEnabled(true);
        bmp = vv.getDrawingCache();
        saveBitmapToSDCard(bmp, getSDCardPath(), "screen.png");
        vv.setDrawingCacheEnabled(false);
        return bmp;
    }

    /**
     * 获取本地图片
     *
     * @param path
     * @return
     */
    public static Bitmap getBitmapByPath(String path) {
        Bitmap b = null;
        File f = new File(path);
        if (f.exists()) {
            b = BitmapFactory.decodeFile(path);
        }
        return b;
    }

    /**
     * 根据路径获得图片并压缩，返回bitmap用于显示
     *
     * @param filePath
     * @param width
     * @param heigh
     * @return
     * @author hefeng
     */
    public static Bitmap getSmallBitmap(String filePath, int width, int heigh) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, width, heigh);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * Bitmap缩放
     * 按原来大小的0.65倍缩放
     *
     * @return
     */
    public static Bitmap getScaleBitmap(Bitmap mBitmap, float scale) {
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
//        matrix.preScale(0.65f, 0.65f);//
//        matrix.setTranslate(0f,0f);
        Bitmap mScaleBitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, true);
        return mScaleBitmap;
    }


    /**
     * 将Bitmap转成Byte[],以PNG格式转换
     *
     * @param bmp
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bmp) {
        if (bmp != null) {
            ByteArrayOutputStream baosArrayOutputStream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, baosArrayOutputStream);
            return baosArrayOutputStream.toByteArray();
        }
        return null;
    }

    /**
     * 保存bitmap到sdcard
     *
     * @param bmp
     * @param path     图片路径
     * @param fileName 图片名
     */
    public static int saveBitmapToSDCard(Bitmap bmp, String path, String fileName) {
        File file = new File(path, fileName);
        try {
            //保存缩略图
            if (!file.exists()) {
                File filePath = file.getParentFile();
                filePath.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(file);
            if (bmp.compress(Bitmap.CompressFormat.PNG, 100, out)) {
                out.flush();
                out.close();
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 图片压缩
     *
     * @param filePath
     * @param cachePath
     * @return
     */
    public static boolean comprBitmap(String filePath, String cachePath) {
        OutputStream out = null;
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;  //设置为true，只读尺寸信息，不加载像素信息到内存
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, option);  //此时bitmap为空
        option.inJustDecodeBounds = false;
        int bWidth = option.outWidth;
        int bHeight = option.outHeight;
        int toWidth = 720;
        int toHeight = 1280;
        int be = 1;  //be = 1代表不缩放
        if (bWidth / toWidth > bHeight / toHeight && bWidth > toWidth) {
            be = (int) bWidth / toWidth;
        } else if (bWidth / toWidth < bHeight && bHeight > toHeight) {
            be = (int) bHeight / toHeight;
        }
        option.inSampleSize = be; //设置缩放比例
        try {
            bitmap = BitmapFactory.decodeFile(filePath, option);
            out = new FileOutputStream(new File(cachePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        bitmap.recycle();
        bitmap = null;
        System.gc();
        return isSuccess;
    }

    /**
     * 图片压缩
     *  @param filePath
     * @param cachePath
     * @param context
     */
    public static String compressBitmap(String filePath, String cachePath, Context context,String date) {
        String savePath = cachePath;
        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置true,只读取尺寸，不占用内存资源
        options.inJustDecodeBounds = true;
        /**
         * 位图位数越高代表其可以存储的颜色信息越多，图像也就越逼真。
         * ALPHA_8 代表8位Alpha位图
         ARGB_4444 代表16位ARGB位图
         ARGB_8888 代表32位ARGB位图
         RGB_565 代表8位RGB位图
         */
        options.inPreferredConfig = Config.RGB_565;
        //不会把图片读入内存，只会获取图片宽高等信息
        Bitmap bitmap;

        int heitht = options.outHeight;
        // 根据需要设置压缩比例
        int size = heitht / 800;
        if (size <= 0) {
            size = 2;
        }
        /**inSampleSize表示缩略图大小为原始图片大小的几分之一，
         即如果这个值为2，则取出的缩略图的宽和高都是原始图片的1/2，
         图片大小就为原始大小的1/4*/
        options.inSampleSize = size;
        /**
         * 当系统内存不够时候图片自动被回收
         */
        options.inPurgeable = true;
        options.inInputShareable = true;

        options.inJustDecodeBounds = false;

//        bitmap = BitmapFactory.decodeFile(filePath, options);
        //添加当前时间的水印
        Bitmap bitmap1 = BitmapFactory.decodeFile(filePath, options);
        float x=bitmap1.getWidth()-600;
        float y=bitmap1.getHeight()-100;
        bitmap=ImageUtils.addTextWatermark(bitmap1,
                date,64, 0xFFFFFFFF,x,y,false);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        /* options表示 如果不压缩是100，表示压缩率为0。如果是70，就表示压缩率是70，表示压缩30%; */
        int o = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        while (baos.toByteArray().length / 1024 > 200) {
            // 循环判断如果压缩后图片是否大于500kb继续压缩
            baos.reset();
            o -= 10;
            // 这里压缩options%，把压缩后的数据存放到baos中
            bitmap.compress(Bitmap.CompressFormat.JPEG, o, baos);
        }
        try {
            FileOutputStream out = new FileOutputStream(cachePath);
            out.write(baos.toByteArray());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            savePath = "";
        }
        return savePath;
    }

    /**
     * 图片压缩
     *  @param filePath
     * @param cachePath
     */
    public static String compressBitmap(String filePath, String cachePath) {
        String savePath = cachePath;
        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置true,只读取尺寸，不占用内存资源
        options.inJustDecodeBounds = true;
        /**
         * 位图位数越高代表其可以存储的颜色信息越多，图像也就越逼真。
         * ALPHA_8 代表8位Alpha位图
         ARGB_4444 代表16位ARGB位图
         ARGB_8888 代表32位ARGB位图
         RGB_565 代表8位RGB位图
         */
        options.inPreferredConfig = Config.RGB_565;
        //不会把图片读入内存，只会获取图片宽高等信息
        Bitmap bitmap;

        int heitht = options.outHeight;
        // 根据需要设置压缩比例
        int size = heitht / 800;
        if (size <= 0) {
            size = 2;
        }
        /**inSampleSize表示缩略图大小为原始图片大小的几分之一，
         即如果这个值为2，则取出的缩略图的宽和高都是原始图片的1/2，
         图片大小就为原始大小的1/4*/
        options.inSampleSize = size;
        /**
         * 当系统内存不够时候图片自动被回收
         */
        options.inPurgeable = true;
        options.inInputShareable = true;

        options.inJustDecodeBounds = false;

        bitmap = BitmapFactory.decodeFile(filePath, options);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        /* options表示 如果不压缩是100，表示压缩率为0。如果是70，就表示压缩率是70，表示压缩30%; */
        int o = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        while (baos.toByteArray().length / 1024 > 200) {
            // 循环判断如果压缩后图片是否大于500kb继续压缩
            baos.reset();
            o -= 10;
            // 这里压缩options%，把压缩后的数据存放到baos中
            bitmap.compress(Bitmap.CompressFormat.JPEG, o, baos);
        }
        try {
            FileOutputStream out = new FileOutputStream(cachePath);
            out.write(baos.toByteArray());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            savePath = "";
        }
        return savePath;
    }

    /**
     * 图片保存
     *
     * @param bitmap
     * @param filePath
     */
    public void saveBitmToSD(Bitmap bitmap, String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            outputStream.write(bitmap.getNinePatchChunk());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩图片
     *
     * @param bmp
     * @param maxSize 图片最大大小，kb
     * @return
     * @throws IOException
     */
    public static Bitmap comprBitmap(Bitmap bmp, int maxSize) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        //根据BitmapFactory.Options获取最佳缩放比
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是1280*720分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为1280f
        float ww = 480f;//这里设置宽度为720f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0) be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        if (isBm != null) isBm.close();
        if (baos != null) baos.close();
        return bitmap;
//	    return compressImage(bitmap, maxSize);
    }

    /**
     * BitMap质量压缩（注意内存中读取的Bitmap仍不会改变大小，可能导致内存溢出）
     *
     * @param image   原始图片
     * @param maxSize 大小限制
     * @return
     * @throws IOException
     */
    public static Bitmap compressImage(Bitmap image, int maxSize) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        if (maxSize <= 0) {
            maxSize = 100;
        }
        while (baos.toByteArray().length / 1024 > maxSize) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos  
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10  
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        isBm.close();
        baos.close();
        return bitmap;
    }

    /**
     * 将两张位图拼接成一张（上下拼接）
     *
     * @param bmpIcon 上面的图标
     * @param bmptxt  下面带文字的边框
     * @return 合成的bitmap
     */
    public static Bitmap add2Bitmap(Bitmap bmpIcon, Bitmap bmptxt) {
        int width = bmptxt.getWidth();//已知下面的文字比上面的图标长
        int height = bmpIcon.getHeight() + bmptxt.getHeight();
        Bitmap result = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        float left = (bmptxt.getWidth()) / 6;
        canvas.drawBitmap(bmpIcon, left, 0, null);
        canvas.drawBitmap(bmptxt, 0, bmpIcon.getHeight(), null);//////////////////需调整bmptxt位置
        return result;
    }

    /**
     * 在bitmap上面添加文字水印
     *
     * @param context
     * @param text
     * @param testSize
     * @param textColor
     * @param isTrim    是否裁剪文字
     * @param x         The x-coordinate of the origin of the text being drawn
     * @param y         x-coordinate of the origin of the text being drawn
     * @return
     */
    public static Bitmap text2Bitmap(Context context, String text, float testSize, int textColor, boolean isTrim, Bitmap bmpFrame, float x, float y) {
        String name = isTrim ? getNameTrim(text) : text;
//		Bitmap bmpFrame=BitmapFactory.decodeResource(context.getResources(), backDrawableid);//文字边框
        //首先创建一个和bmpframe一样尺寸的位图
        Bitmap bmpText = Bitmap.createBitmap(bmpFrame.getWidth(), bmpFrame.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpText);//创建画布
        canvas.drawBitmap(bmpFrame, 0, 0, null);//线在0,0坐标处载入图片
//		Typeface typeface=Typeface.createFromAsset(context.getAssets(), "fonts/CONSOLA.TTF");//从asset中获取字体
        TextPaint textpaint = new TextPaint();
        textpaint.setAntiAlias(true);//消除锯齿
        textpaint.setTextSize(testSize);//px单位
        textpaint.setColor(textColor);
        canvas.drawText(name, x, y, textpaint);//////////////////////////需调整文字位置
        canvas.save(Canvas.ALL_SAVE_FLAG);//保存
        canvas.restore();//
        return bmpText;
    }

    private static String getNameTrim(String name) {
        if (name.length() > 5) {
            name = name.substring(0, 4) + "...";
        }
        return name;
    }

    /**
     * 转换图片缩放成圆形 （检验可运行）
     *
     * @param bitmap     传入Bitmap对象
     * @param edgeLength 缩略图的边长
     * @return 经过缩放的圆形bitmap
     */
    public static Bitmap toRoundBitmap(Bitmap bitmap, int edgeLength) {
        bitmap = BitmapUtils.centerSquareScaleBitmap(bitmap, edgeLength);//得到正方形的缩略图
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }
        Bitmap output = Bitmap.createBitmap(width,
                height, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }

    /**
     * 图片维持宽高比缩放，截取正中间的正方形部分(可运行)
     *
     * @param bitmap
     * @param edgeLength
     * @return
     */
    public static Bitmap centerSquareScaleBitmap(Bitmap bitmap, int edgeLength) {
        if (bitmap == null || edgeLength <= 0) {
            return null;
        }
        Bitmap result = bitmap;
        int widthOrg = bitmap.getWidth();//图像宽度
        int heightOrg = bitmap.getHeight();//图像高度
        //当图片长宽都大于指定边长才进行裁剪，方法不够好
        if (widthOrg > edgeLength && heightOrg > edgeLength) {
            //压缩到一个最小长度的edelength的bitmap
            int longerEdge = (int) (edgeLength * Math.max(widthOrg, heightOrg) / Math.min(widthOrg, heightOrg));
            int scaledWidth = widthOrg > heightOrg ? longerEdge : edgeLength;
            int scaleHeight = widthOrg > heightOrg ? edgeLength : longerEdge;
            Bitmap scaledBitmap;
            try {
                scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaleHeight, true);
            } catch (Exception e) {
                return null;
            }
            //从图中截取正中间的正方形部分
            int xTopLeft = (scaledWidth - edgeLength) / 2;
            int yTopLeft = (scaleHeight - edgeLength) / 2;
            try {
                result = Bitmap.createBitmap(scaledBitmap, xTopLeft, yTopLeft, edgeLength, edgeLength);
                scaledBitmap.recycle();
            } catch (Exception e) {
                return null;
            }
        }
        return result;
    }

    /**
     * 获得视频的缩略图
     *
     * @param imgPath      视频绝对路径
     * @param getThumbnail 是否获取缩略图
     * @return Bitmap
     */
    public static Bitmap getBitmapOfVideo(String imgPath, boolean getThumbnail) {

        if (getAndroidSDKVersion() >= 8) {
            Bitmap bp = ThumbnailUtils.createVideoThumbnail(imgPath,
                    MediaStore.Video.Thumbnails.MINI_KIND);
            if (getThumbnail) {
                bp = centerSquareScaleBitmap(bp, 100);
            }
            return bp;
        } else {
            return null;
        }
    }

    /**
     * 图片变灰
     */
    public static Bitmap toGrayscale(Bitmap bmpOriginal) {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();
        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height,
                Config.RGB_565);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }

    /**
     * 将图片的四角圆化  (未检验)
     *
     * @param bitmap      原图
     * @param roundPixels 圆滑率
     * @param half        是否截取半截
     * @return
     */
    public static Bitmap getRoundCornerImage(Bitmap bitmap, int roundPixels, int half) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        //创建一个和原始图片一样大小位图
        Bitmap roundConcerImage = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        //创建带有位图roundConcerImage的画布
        Canvas canvas = new Canvas(roundConcerImage);
        //创建画笔
        Paint paint = new Paint();
        //创建一个和原始图片一样大小的矩形
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        // 去锯齿
        paint.setAntiAlias(true);

        //画一个和原始图片一样大小的圆角矩形
        canvas.drawRoundRect(rectF, roundPixels, roundPixels, paint);
        //设置相交模式
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        //把图片画到矩形去
        canvas.drawBitmap(bitmap, null, rect, paint);
        switch (half) {
            case LEFT:
                return Bitmap.createBitmap(roundConcerImage, 0, 0, width / 2, height);
            case RIGHT:
                return Bitmap.createBitmap(roundConcerImage, width / 2, 0, width / 2, height);
            case TOP:
                return Bitmap.createBitmap(roundConcerImage, 0, 0, width, height / 2);
            case BOTTOM:
                return Bitmap.createBitmap(roundConcerImage, 0, height / 2, width, height / 2);
            case NONE:
                return roundConcerImage;
            default:
                return roundConcerImage;
        }
    }

    public static final int NONE = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int TOP = 3;
    public static final int BOTTOM = 4;

    /**
     * 获取SDCard的目录路径功能
     */
    public static String getSDCardPath() {
        File sdcardDir = null;
        // 判断SDCard是否存在
        boolean sdcardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (sdcardExist) {
            sdcardDir = Environment.getExternalStorageDirectory();
        }
        return sdcardDir.toString();
    }

    /**
     * 当前系统的版本号
     *
     * @author liningning
     * @create 2011-11-14
     */
    @SuppressWarnings("deprecation")
    public static int getAndroidSDKVersion() {
        int version;
        try {
            version = Integer.valueOf(android.os.Build.VERSION.SDK);
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
            return -1;
        }
        return version;
    }

}
