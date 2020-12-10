package faceEngine;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import tools.WriteFiles;

public class CutPicture {
	private String srcpath;
    private String subpath;
    private String imageType;
    private int x;
    private int y;
    private int width;
    private int height;
    WriteFiles wf = new WriteFiles();

    private FileInputStream is = null;
    private ImageInputStream iis = null;
    
    
    public CutPicture() {
    }
     
    public CutPicture(String srcpath, int x, int y, int width, int height) {
     this.srcpath = srcpath;
     this.x = x;
     this.y = y;
     this.width = width;
     this.height = height;
    }
    
    public void cut(File outFile) throws IOException {
    	
        try {
        	is = new FileInputStream(srcpath);
        	this.setSrcpath(srcpath);
        	Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(this.imageType);
        	ImageReader reader = it.next();
        	iis = ImageIO.createImageInputStream(is);
        	reader.setInput(iis, true);
        	ImageReadParam param = reader.getDefaultReadParam();
        	Rectangle rect = new Rectangle(x, y, width, height);
        	param.setSourceRegion(rect);
        	BufferedImage bi = reader.read(0, param);
         
        	wf.createFile(outFile);
         
        	ImageIO.write(bi, this.imageType, outFile);
        } finally {
        	if (is != null)
        		is.close();
        	if (iis != null)
        		iis.close();
       }
    }
    
    
    public int getHeight() {
     return height;
    }
    public void setHeight(int height) {
     this.height = height;
    }
    public String getSrcpath() {
     return srcpath;
    }
    public void setSrcpath(String srcpath) {
     this.srcpath = srcpath;
     if(srcpath != null) {
      this.imageType = srcpath.substring(srcpath.indexOf(".")+1, srcpath.length());
     }
    }
    public String getSubpath() {
     return subpath;
    }
    public void setSubpath(String subpath) {
     this.subpath = subpath;
    }
    public int getWidth() {
     return width;
    }
    public void setWidth(int width) {
     this.width = width;
    }
    public int getX() {
     return x;
    }
    public void setX(int x) {
     this.x = x;
    }
    public int getY() {
     return y;
    }
    public void setY(int y) {
     this.y = y;
    }
     
    public String getImageType() {
     return imageType;
    }
    public void setImageType(String imageType) {
     this.imageType = imageType;
    }
    
}
