package tools;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class MyFileUtil {
	private JFileChooser chooser = null;
	
	public File chooserDIRECTORIES() {
		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setCurrentDirectory(new File("."));
		chooser.showDialog(new JLabel(), "请选择文件夹");
		File file = chooser.getSelectedFile();
//		System.out.println("file =" + file);
		return file;
	}
	public File chooserFILES_AND_DIRECTORIES() {
		chooser = new JFileChooser();  
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
		chooser.setCurrentDirectory(new File("."));
		chooser.showDialog(new JLabel(), "选择一张照片"); 
//        MyFileFilter myFileFilter = new MyFileFilter("JPEG", "JPEG图片文件");
//        jfc.setFileFilter(myFileFilter);
        File file=chooser.getSelectedFile();
        return file;
	}
}	
