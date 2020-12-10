package faceEngine;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.codec.binary.Base64;

import tools.WriteFiles;

public class MyPhoto {
	private static ImageIcon imageIcon = null;
	private static BufferedImage srcImage = null;
	private static Image image;
	private static long size;
	private static byte[] imageByte;

	private static FileInputStream fs = null;
	private static BufferedInputStream bis = null;
	private static String base64Photo = null;
	private static Base64 base;
	public static final String DAWNLOAD_AUTHORITY_PHOTO = "DAWNLOAD_AUTHORITY_PHOTO";
	/**
     * 改变图片的尺寸
     *
     * @param newWidth, newHeight, path
     * @return boolean
     */
    public static ImageIcon changeSize(int width, int height, String imgPath) {
    	
		try {
			srcImage =  ImageIO.read(new FileInputStream(imgPath));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        double sx = (double) width / srcImage.getWidth();
        double sy = (double) height / srcImage.getHeight();
        // 等比缩放
        sx = sx > sy? sx:sy;
        width = (int) (sx * srcImage.getWidth());
        height = (int) (sx * srcImage.getHeight());
        imageIcon = new ImageIcon(imgPath);
        image = imageIcon.getImage(); 
        image = image.getScaledInstance(width,height,java.awt.Image.SCALE_FAST);
        return imageIcon = new ImageIcon(image);
    }
	/**
     * 改变图片的尺寸
     *
     * @param newWidth, newHeight, path
     * @return boolean
     */
    public static File changeSizeToFile(int width, int height, String imgPath) {
    	
    	BufferedInputStream in = null;
        try {
            
				in = new BufferedInputStream(new FileInputStream(imgPath));

            //字节流转图片对象
            Image bi = ImageIO.read(in);
            //构建图片流
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            
            //绘制改变尺寸后的图
            tag.getGraphics().drawImage(bi, 0, 0, width, height, null);
            //输出流
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(imgPath));
            //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            //encoder.encode(tag);
            ImageIO.write(tag, "PNG", out);
            in.close();
            out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new File(imgPath);
    }
    public static String getImageIconToFile(ImageIcon imageIcon) {
    	try {
			base64Photo = Base64.encodeBase64String(imageIcon.toString().getBytes("utf-8"));
			base64Photo = base64Photo.replaceAll("[\\s*\t\n\r]", "");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return base64Photo;  
    }
    
    public synchronized static String getPhoteBASE64_Mime(File file){
        size = file.length();
        imageByte = new byte[(int)size];
        base64Photo = null;
        try {
            fs = new FileInputStream(file);
            bis = new BufferedInputStream(fs);
            bis.read(imageByte);
            base64Photo = Base64.encodeBase64String(imageByte);
            base64Photo = base64Photo.replaceAll("[\\s*\t\n\r]", "");
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        } finally{
            if(bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                }
            }
            if(fs != null){
                try {
                    fs.close();
                } catch (IOException e) {
                }
            }
        }
//        System.out.println("base64Photo = " + base64Photo);
        return base64Photo;    
    }
    
    public static ImageIcon getImageIconfromBASE64(String base64Photo, int width, int height) {
    	
        imageIcon = getImageIconfromBASE64(base64Photo);
        image = imageIcon.getImage(); 
        image = image.getScaledInstance(width,height,Image.SCALE_FAST);
    	return imageIcon = new ImageIcon(image);
    }
    
    public static ImageIcon getImageIconfromBASE64(String base64Photo) {
    	/*
    	 * 如果上传的base含有照片头文件，者取消头文件
    	 * data:image/jpg;base64
    	 */
//    	System.out.println("base64Photo.lenth ========" + base64Photo.length());
    	String temp = "base64,";
    	if (base64Photo.indexOf(temp) > 0) {
    		base64Photo = base64Photo.split(temp)[1];
    	}
    	if ( base64Photo == null) {
    		return null;
    	}
    	base = new Base64();
    	imageByte = base.decode(base64Photo.getBytes());
        for (int i = 0; i < imageByte.length; ++i) {
            if (imageByte[i] < 0) {// 调整异常数据
            	imageByte[i] += 256;
            }
        }
        InputStream inputStream = new ByteArrayInputStream(imageByte, 0 , imageByte.length);
        try {
			srcImage = ImageIO.read(inputStream);
//			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			imageIcon = new ImageIcon(srcImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	return imageIcon;
    }
//    public static File writePhotoFile(String path, Image image) {
//    	File file = new File(path);
//    	new WriteFiles().createFile(file);
//    	OutputStream os = null;
//    	BufferedImage bi = null;
//    	try {
//			os = Files.newOutputStream(Paths.get(path));
//			bi = new 
//			//ImageIO.write(new BufferedImage(image), "jpg", os);
//			bi.flush();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
//    
    /**
	 * 旋转图片
	 * 
	 * @param image
	 *            图片
	 * @param angel
	 *            旋转角度
	 * @return
	 */
	public static BufferedImage rotateImage(Image image, int angel) {
		if (image == null) {
			return null;
		}
		
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		Rectangle rectangle = calculatorRotatedSize(new Rectangle(new Dimension(imageWidth, imageHeight)), angel);
		BufferedImage newImage = null;
		newImage = new BufferedImage(rectangle.width, rectangle.height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = newImage.createGraphics();
		// transform
		graphics.translate((rectangle.width - imageWidth) / 2, (rectangle.height - imageHeight) / 2);
		graphics.rotate(Math.toRadians(angel), imageWidth / 2, imageHeight / 2);
		graphics.drawImage(image, null, null);
		return newImage;
	}
	/**
	 * 计算旋转后的尺寸
	 * 
	 * @param src
	 * @param angel
	 * @return
	 */
	private static Rectangle calculatorRotatedSize(Rectangle src, int angel) {
		if (angel >= 90) {
			if (angel / 90 % 2 == 1) {
				int temp = src.height;
				src.height = src.width;
				src.width = temp;
			}
			angel = angel % 90;
		}
		double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
		double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
		double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
		double angel_dalta_width = Math.atan((double) src.height / src.width);
		double angel_dalta_height = Math.atan((double) src.width / src.height);

		int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
		int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
		int des_width = src.width + len_dalta_width * 2;
		int des_height = src.height + len_dalta_height * 2;
		return new java.awt.Rectangle(new Dimension(des_width, des_height));
	}
	
	/**
	 * 照片写文件
	 * @param photoFilePath 照片原始路径
	 */
	public void writePhotoToFile(String photoFilePath) {
		String srcpath = getPhotoFilePath(DAWNLOAD_AUTHORITY_PHOTO, new File(photoFilePath).getName());
		File tempFile = new File(srcpath);
		new WriteFiles().createFile(tempFile);
		BufferedImage bi = null;
		OutputStream os = null;
		try {
			bi = ImageIO.read(Files.newInputStream(Paths.get(photoFilePath)));
			os = Files.newOutputStream(Paths.get(srcpath));
			bi.flush();
			ImageIO.write(bi, "jpg", os);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	private String getPhotoFilePath(String type, String photoFileName) {
		String srcpath = null;
		switch (type) {
		case DAWNLOAD_AUTHORITY_PHOTO:
			srcpath = "./logs/权限下载错误照片/" + photoFileName;
			break;

		default:
			break;
		}
		return srcpath;
	}
}
