package myGson.das;

import java.util.List;

/**
 * 接口 GetScreenSaver 的response 使用
 * 接口SetScreenSaver 的request 使用
 * @author yangdang
 *
 */
public class ScreenSaverGson {
	/**设备欢迎语、最大40个字符*/
	private String DeviceHelloWord;
	private List<ImageEx> ImageList;

	/**设备欢迎语、最大40个字符*/
	public String getDeviceHelloWord() {
		return DeviceHelloWord;
	}
	
	/**设备欢迎语、最大40个字符*/
	public void setDeviceHelloWord(String deviceHelloWord) {
		DeviceHelloWord = deviceHelloWord;
	}
//	/**设备背景图片base64值*/
//	public String getImage() {
//		return Image;
//	}
//	
//	public void setImage(String image) {
//		Image = image;
//	}
	
	/**图片列表*/
	public List<ImageEx> getImageList() {
		return ImageList;
	}
	/**图片列表*/
	public void setImageList(List<ImageEx> imageList) {
		ImageList = imageList;
	}
	
	public class ImageEx{
		/**设备背景图片base64值*/
		private String Image;

		public String getImage() {
			return Image;
		}

		public void setImage(String image) {
			Image = image;
		}
		
	}
}
