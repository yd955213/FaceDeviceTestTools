package myGson.das;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import faceEngine.MyPhoto;

public class SetLogoGson {
	public String Image;

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}
	
	public String setPhotoSize(String imgPath) {
		String Image = null;
		try {
			//这里有问题 一张照片反复读取，以后再优化
			BufferedImage bufferedImage = ImageIO.read(new FileInputStream(imgPath));
			if (bufferedImage.getWidth() > 300 || bufferedImage.getHeight() >150) {
				MyPhoto.changeSizeToFile(300, 150, imgPath);
			}
		Image = MyPhoto.getPhoteBASE64_Mime(new File(imgPath));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "请选择LOGO图标", "设置LOGO", JOptionPane.ERROR_MESSAGE);
		}
		
		return Image;
	}
}
