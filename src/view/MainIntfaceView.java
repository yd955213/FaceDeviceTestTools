package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.testModel.TestParamInit;
import com.testModel.recognitionRateTest.RecognitonRateTest;

import comm.myLibraryLoad.jvt.DeviceInfo;
import comm.myLibraryLoad.jvt.FactoryTest;
import comm.myLibraryLoad.jvt.JvtDeviceDll;
import faceEngine.MyFaceEngine;
import faceEngine.MyPhoto;
import httpFrame.http.Http;
import httpFrame.http.dasApi.DownLoadApk;
import httpFrame.http.dasApi.DownLoadApkTest;
import myGson.das.ClearDeviceDataRequestGson;
import myGson.das.DevInfo;
import myGson.das.DeviceParameterGson;
import myGson.das.RequestGson;
import myGson.das.ResponseGson;
import myGson.das.ScreenSaverGson;
import myGson.das.ScreenSaverGson.ImageEx;
import myGson.das.SetLogoGson;
import myGson.das.UpgradeAppGson;
import myGson.tianmo350.DeviceConfig;
import myProgressBar.MyProgressBar;
import myProgressBar.PrimeNumbersTask;
import mySocketClient.MySocketChannel;
import mySocketClient.myHttpClient.api.ClearDeviceData;
import mySocketClient.myHttpClient.api.GetAllPerson;
import mySocketClient.myHttpClient.api.GetDeviceParams;
import mySocketClient.myHttpClient.api.GetScreenSaver;
import mySocketClient.myHttpClient.api.RemoteOpenDoor;
import mySocketClient.myHttpClient.api.RestartDevice;
import mySocketClient.myHttpClient.api.SetDeviceParams;
import mySocketClient.myHttpClient.api.SetLogo;
import mySocketClient.myHttpClient.api.SetScreenSaver;
import mySocketClient.myHttpClient.api.UpgradeApp;
import mySocketClient.udp.CommandSocket;
import sqlite3.DataBaseExecute;
import sqlite3.DownLoadAuthority;
import sqlite3.PageBean;
import sqlite3.PageBeanTool;
import sqlite3.PersonInfo;
import sqlite3.QueryTable;
import tools.LogsWriter;
import tools.MyFileUtil;
import tools.SystemTimes;
import tools.ViewChiosePamameter;
import tools.downLoadAuthority.DownLoadAuthorityStatu;
import view.update.DbServerTest;
import view.update.PersonInfoChange;
import view.update.UpdateTable;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
/**
 * Demo class
 * 
 * @author yangDang
 * @date 2020/5/15
 */
public class MainIntfaceView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static FactoryTest factoryTest = null;
	private JPanel contentPane;
	private static JLabel uiChiosePictureButton;
	private static JComboBox<String> InfoComboBox;
	private static JComboBox<String> infoGenderComboBox;
	private static JTable personInfoTable;
	private static JPanel panel_22, panel_35, panel_24;
	public static JComboBox<String> faceDevComboBox;
	public static JComboBox<String> faceDevchoiseComboBox;
	private static JComboBox<String> basicParameterDevTypeComboBox;
	private static JTextField appParameterAutoRestartTextField;
	private static JTextField accessDevMacTextField;
	private static JTextField accessDevDoorPasswordTextField;
	private static JTextField accessDevIpTextField;
	private static JTextField CameraBeautyScoreTextField;
	private static JTextField suppleLightOpenTimeTextField;
	private static JTextField accessUdpServerPortTextField;
	private static JTextField voiceTipAttendanceTimeTextField_1;
	private static JTextField voiceTipRecognizeSuccessTextField;
	private static JTextField voiceTipBeforeJobTextField;
	private static JTextField consumePayUrlTextField;
	private static JTextField consumeDevNoTextField;
	private static JTextField consumeDisplayTitleTextField;
	private static JTextField consumeSuccessInfoDurationTextField;
	private static JTextField consumeOnlineWalletId2TextField;
	private static JTextField voiceTipAfterJobTextField;
	private static JTextField voiceTipRecognizeErrorTextField;
	private static JTextField consumeOfflineMoneyLimitTextField;
	private static JTextField consumeOfflineNumberLimitTextField;
	private static JTextField consumeOfflineTimeLimitTextField;
	private static JTextField recognitionParameterLivingThresdhoidTextField;
	private static JTextField recognitionParameterTriggerActionIntervalTextField;
	private static JTextField recognitionParameterSimilityThresholdTextField;
	private static JTextField recognitionParameterQualityThresholdTextField;
	private static JTextField recognitionParameterMinFacePixelTextField;
	private static JTextField recognitionParameterMaxFacePixelTextField;
	private static JTextField devOutRelayTimeTextField;
	private static JTextField appParameterPasswordTextField;
	private static JTextField consumeQueryUrlTextField;
	private static JTextField consumeBreakfastTimeTextField;
	private static JTextField consumeLunchTimeTextField;
	private static JTextField consumeSupperTimeTextField;
	private static JTextField consumeDinnerTimeTextField;
	private static JTextField consumeOnlineWalletID1TextField;
	private static JTextField basicParameterDevMacTextField;
	private static JTextField basicParameterDevNameTextField;
	private static JTextField basicParameterDevIpTextField;
	private static JTextField basicParameterDevServerIpTextField;
	private static JTextField basicParameterDevServerPortTextField;
	private static JTextField basicParameterSystemNoTextField;
	private static JTextField basicParameterFeatureTypeTextField;
	private static JTextField basicParameterFeatureVersionTextField;
	private static JTextField basicParameterFeatureSdkTextField;
	private static JTextField basicParameterVersionTextField;
	private static JTextField basicParameterHeartBeatTextField;
	private static JTextField recognitionParameterPidTextField;
	private static JTextField uiTextField;
	private static JRadioButton appParameterDebugMode_Open_RadioButton;
	private static JRadioButton appParameterAutoRestart_Yes__RadioButton;
	private static JRadioButton appParameterAutoRestart_No__RadioButton;
	private static JRadioButton appParameterUploadPassImg_Yes_RadioButton;
	private static JRadioButton appParameterUploadPassImg_No_RadioButton;
	private static JRadioButton appParameterDebugMode_Close_RadioButton;
	private static JRadioButton devOutQrCode_No_RadioButton;
	private static JRadioButton devOutQrCode_Open_RadioButton;
	private static JRadioButton devOutQrCode_Close_RadioButton;
	private static JRadioButton devOutCard_No_RadioButton;
	private static JRadioButton devOutCard_Open_RadioButton;
	private static JRadioButton devOutCard_Close_RadioButton;
	private static JComboBox<String> devOutOpenDoorTypeComboBox;
	private static JComboBox<String> devOutWeigand_Out_ComboBox;
	private static JComboBox<String> devOutWeigand_In_ComboBox;
	private static JComboBox<String> uiTypeComboBox;
	private static JRadioButton uiScreanSave_No_RadioButton;
	private static JRadioButton uiScreanSave_Open_RadioButton;
	private static JRadioButton uiScreanSave_Close_RadioButton;
	private static JComboBox<String> accessDevDoorNoComboBox;
	private static JComboBox<String> recognitionParameterALiveVomboBox;
	private static JComboBox<String> CameraModeComboBox;
	private static JComboBox<String> suppleLightModelcomboBox;
	private static JRadioButton consumeOfflineMode_No_RadioButton;
	private static JRadioButton consumeOfflineMode_Yes_RadioButton;
	private static JTextField infoNameTextField;
	private static JTextField infoOpeTextField;
	private static JTextField InfoCardTextField;
	private static JTextField infoIdTextField;
	private static JTextField infoFaceStartTextField;
	private static JTextField infoFaceEndTextField;
	private static JTextField infoCardStartTextField;
	private static JTextField infoCardEndTextField;
	private static JLabel photoJLabel;
	private static File uiFile = null;
	private JTextField copyurlTextField_1;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	public static JTextArea logTextArea;
	public static CardLayout cardLayout, sorkectCardLayout;
	public static JLabel recordPersonInBaseDataButton_1;
	public static JLabel recordPersonInBaseDataButton_2;
	public static JLabel recordPersonInBaseDataButton_3;
	public static JLabel recordPersonInBaseDataButton_4;
	public static JLabel recordPersonInBaseDataButton_5;
	public static JLabel similarityScoreaLabel_1;
	public static JLabel similarityScoreaLabel_2;
	public static JLabel similarityScoreaLabel_3;
	public static JLabel similarityScoreaLabel_4;
	public static JLabel similarityScoreaLabel_5;
	public static JLabel capturePhotoButton_1, capturePhotoButton_2, capturePhotoButton_3, capturePhotoButton_4, capturePhotoButton_5;
	/**
	 * 数据库中无该人员信息照片
	 */
	private final static String noPersonInDbBase64 = "/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjI"
			+ "yMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCABpAGkDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIh"
			+ "MUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19"
			+ "jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2N"
			+ "zg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD26iiigAooooAK"
			+ "KKKACiiigAooooAKKKKACjFFFABRRXkmuWdzrnirxRpc17qfl3V7Y2FokV7KkUSNB5lx8gYIf3SucEHJxxzQB63RXJ+FLDTrmO11ewbWIrZ7aOWC3vL26cKHUnJDStG4KsuAAdpByc8LRbUJNei8KXd7FCWHiC5QKq/KPKS7RDgk8/"
			+ "Ipz68jFAdLnck4GTxiuc1XX3WQwWRGRwz9azNU1i+nHinTvKt7pYrqO2ghuDsQxvbxM6sQrZHzueh646dPMLmyit/F+nae3hHQi8kEilFf91lvmVnPkdcQSAcHqencA9L+3aiTu+2S59N3Fa+la9I0ggvSAT91+lcLeXF5oPhSaWDT"
			+ "9OspYXRYoIGMkIVnUE4Cx8/MxwPz5rCi+2vq+mGC3huZo21QQPPMV2kXO05O0kALwMZJzjAHNAHvYOelFYnh69mOhhtRuIWlgB82VUMaBR3wWOOOuTXJfCQzWun3ttfBY7rVJP7ciUMDmGfAGPUgpz6b19aA6XPSKKKKACiiigArmbf"
			+ "wdas1zdXs999uu5nmne01G4gU5J2rhHUNtXam4gEhc8dK6aigDmvC/hGHQdM0tJLi9kvbW1jikxqVw8BcIFbbGz7NvXA2jHGAMVdv9F8670V7RIIIbG+a6kQDbkNDMh2gDqWlBPTuevXYooA5fxREYgjWkEIlmlDyE/LvOApZiAckKA"
			+ "BnrtAyByOQTw6wvbe/kvXlvFuRPNI6cMBG8YjRQfkQeYxHU8nJJOa77xFAZLWOUdI2yfpXPA5oAwb/AEPUNRsbizl1OPyp7nzSTbEskYKlUU78Ajb97B69Kh/4Ria1uLS60/UWFxAs6s15EZRJ5snmMdqMiqd2eQOmAegrpKCeKAI9D"
			+ "0C91LRptJ1DVWltXLfbPLiMb3Cs5IjDbjsTBKnqSOhWui1nw6189hdaXeHTNQ087beZI98ZiON8Tx5AZCFHGQQVUgjHMvh6Ax2TysMeY3H4Vr0AFFFFABRRRQAUUUUAFFFFADJYlmiaNxlWGK5G+sXsJtp+4T8probzWLSzyHky4/hU"
			+ "c1z2oaqNTePYhVU5GaAK1W9OsGv7jb0RfvGqlWtN1RdMkl8xGZZMfd7UAdckaRRrHGMKowKdVKz1W1vcCKT5j/CRzV2gAooooAKKKKACiisnXdQa0thHF/rZOAfQetAEt/rNrY5Vm3yf3F61zl3q19fZXd5UX91aqLFk72OWPJNS9K"
			+ "QESwjOWJY+pOakCgdKWigQUhUN1paKAITDg7kZlPqpxWhZ65eWZAmHnR+/UVVoIzQB11lqdtfIDFIN2OVPUVcrgDGyOJI22sOhFdPoepvextDMP3sff1HrTGbFFFFABXO+JIyZbeTHABB/SuiqOa3huFCzRrIoOcMMigDie1Fdh/Zt"
			+ "j/z5w/8AfNH9m2H/AD5w/wDfNAHH0V2H9m2H/PnD/wB80f2bYf8APnD/AN80AcfRXYf2bYf8+cP/AHzR/Zth/wA+cP8A3zQBx9Fdh/Zth/z5w/8AfNH9m2H/AD5w/wDfNAHH1reG0P22eTtsx+ora/s2w/584f8AvmpobeG3VlhhS"
			+ "MMcnaMZoAkooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKAP/Z\r\n";
	/**
	 * 记录未上传抓拍照片
	 */
	private final static String noCapturePhotoBase64 = "/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgND"
			+ "RgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCABpAGkDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9A"
			+ "QIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8"
			+ "jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8Rc"
			+ "YGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEA"
			+ "PwD3+iimSSBFyTQA5mCjmqUt7ywjAO37zE4VfqarT3W/DNnY33EBwW9z6D+dZi39vLrS6XJKPtfk+eIwuFCZI47Z4PHXAJoAvPctMPvO7f7Pyr/if0pgR2H+qhHfOzcfzbNaMVmAORVlYFXtQBlAXeMLKwHoAAP5U0285JZo4XJ7t"
			+ "GM/nW2FA7UbR6UAYKwtGxOJI+ONjZGfoef1qZL64t03SqJIweWQHKj1I7fqPereq3kWl6XdX0sbyrBGX8uMDc5HRVz3JwB7mqei6lZ+ILFrm3HlywzPb3EDMC9vMhw8bbSRkH0OCMEZBBoA07e6juEDIwOanrHubCRHM9qds2dzL2"
			+ "k/wPv+frU+n6ilypUnEinaynqCOoNAGjRRRQAhOBmsm6uBIzMy7olO0Ln7zf4D/CrV/MyoI48eY52rWVCBcXHyA+WvCA+n/wBfrQBYghaVy7ncx5JNcLq8D2mu6xrRmdf7O1a1mDL/AAW8NtGZuO48u4uOPf6V6dBEI0FYHhPyNU0"
			+ "C+lmWO4iu9QvlkDAMsqC4kjUEdCNiKPpQNNJ6iah8QvCWlyrBPrto9w0nlCC1Y3Eu70KRhmH5VEvjc3F4bex8L+JbsDnzTY/Z0P0M7R11EcUcMaxxoqIgwqqMAD0Ap9Ajnx4h1QnA8Ha0P96ayx+lwaU+INUXr4P1hv8Acnsz/OcV"
			+ "v0UAcPrfiO8vfs2lP4X1+1lnu7UiV7dJYgouIy+54XcLhQT82OlOhuBpPjS0vntjFHr2+znkwBmeIF4CfqnnLn/YQdq7aud8cRR/8IleXzxiR9M26lEp7vbsJQPbOzb+JoHfSx0JAIrI1WzZD9vtgfOjHzqoz5i+n1Hb8vStO1uYb"
			+ "y0huraVZYJkWSORDlXUjIIPoQalIyKBFLT71Lu3SRTnIBq7XOEHStdZC37i6JeMeh/iH5kH8a3PtC/3qAMjUZ2MspTsoQH0Len4A/nVrTLfZGDis3f57lf+mzHP4D/69dBAoSICgCUMOQCDjiuT+GFmbH4Z6AjMWaW1FwxJ6mUmQ/8"
			+ "AodX7zyAbu+vLe0QQwlhc29yVmcKM4JAUgcdNxFS+F7VtP8G6LZzK0b2+nwROrEZUrGoOcfSgC/8A2lYi++xG8txd4z5BlXzMf7uc1aryPRPhnBo+o6t9l8TvNa6n8sqT2hMqgsT5iyBwvmg5Kybfl64NeuDpQNxa3QUVk3msva6/a"
			+ "aV5UHmXcbSws8+0sEZRKMbT8wV1ZQM7sNnbjJ1qBHLzfELw5F4jOgJdyT6iu7fFBCz4KglgCBh2GCNq7mypGMjFdGDFd2oJQmKZOUkQqSpHQqRkfQiuRvvDvhifxM2rnTpTqg3RfaorqWIrwQ23awC9SCQAck+prqrIwGxhW2GIFQ"
			+ "LGOuFHAFK5coSirtGB4PvYLXwpp9gPNlewZtLJijZ8vbsYctgEIDsDfNgYYV00bF4kdkaNmAJRsZU+hwSM/Q1xXhaOSy8S+KtNjmuisGpfahCgjCFZ41kz83zff8zoccV2cAxEBsdOTw7biOfXJ/nTIMfxRGo0xbvB320isCPQnaf"
			+ "w5z+FZH9rf7ddRqlu13pV3bqQGkhZQT2JFeP/ANp+9AHounrulJPaRv6V0iD5RWDp4C3E8f8AEJNx+hAx/wCgmtm5uY7S0eeWWGNVH3ppNiZ7ZbtzQBz3jWwsoPAfiGaOzt1kj0y5ZWES5BETEEcV0skSSxNG4yjAqR6ivIPGvjD"
			+ "WpbbU9PY6CLSfQb4yRwasZQSNihgxgBLgM2E4DZOSMCvUtF1JNV0yO6WWxkYkq/2K6+0RKwOCA+1c+/AoDYj/ALFj3ktISpYMRjk49/wrTHArk9I1u8k8X+MoLyeWWx0xrb7PDHCGMatAHfaEXe5JJ45PQAVb8aa5/YXhqaeK48i7"
			+ "uCLW0k27tkr8B9uDuCDLkY6IaVrbFSqSn8TNqWws55TLLawPIShLtGCSUYsnP+yxJHoScVYrjW8UQw6x9li1+wnjstM8yU3NzFGtzM7YjZnVflx5Um7aMDeDjgCqnjPxnLplos+lXayQXGi6hdQT20X2hfOjEXlvlQw2je2Sfl9aZ"
			+ "J2sllbyOXaP5jwSCRUyIsaBEACgYAFeV6j4g1keIpLe28SXa2k+oW9tbbJLAeWrFWlaQSRh1Uo+IuGLFQckSJnrvGev3Xh8aDNbEmO51WK2uEWFpWaJkfICqC2RgEY5yO4yKLDcm1qyxaQLb/EDVJcAG7021I/2jHJOGP5SJXQV5h"
			+ "D4mvE+KiG5tdUls7iOaCwjOnvGRGfseX+ZVO1X84sWOQBx1UH0+gQ2QgRsScACvnrDe9e6eIrr7H4ev5w+xhCwRvRjwv6kV5H9hPpQB6fNi01xCxwk67Pbd1H9R+Na13PPDZtJa2pup8gJEHCAknGSx6KM5JAJwDgMcA09bsvtNqS"
			+ "pIdeVI4IPrmptJvhfWSyMAsoJWRAfusP85+hoA8q8eeEJ1vNY1q6uLue7bQNQke9jVliiwYxHAqklQvlmQEHlsu3BwV9U0+71KTTll1HTFt7vzHRoILgTAAMQGDELkEAHGARkZGc1n+PAD8PPEv8A2Cro/wDkJq1HjiLuXmC7u2c"
			+ "Y+nNLYaVzndCsNSsfGfirUZ9OmW21CW2a3YSRncEhVGyA+RyO9L4ngn1Xwq97/Zd7Df8AyRraswd1UzJklY2ZCcIDkZIGemSK6Ly4CWxcYyuABJ096cI4w+4XBI24wXyPrSch8qOR1e21fT/E9xcWUN4sesy21s+o27LcGzjjB/5"
			+ "YmP5ASzDdlwC5ZsABTJ4r0ubVLtNNhtru5nm0S/tTeMQkSeYIgNxCYLsyjABGAGOCMY6tkjbgz8Y5+aohFEmz/SFyo/ibr9eaUZO2qCyOO83UX0a/1GTw0376K01prR3dZZJkjXMHGT5qGCMghQGyFKg5Y62t2R1vxJpEUjmGy0u"
			+ "Q30zMGXzJSjJEiNwONzs2CcYUfxZG6iwKFBmRtpyDu5/nSqluoYAhlJzgAHn8BTuHKjzzULeW2+Oui3K+ebaS0FsruzMpYpdOwBOeRsQke49a9OrkvEmz/hJfCDKr5/tSQEsD3tZ/Wupnmjt4JJpnVI41LMzHAAHUk00JnMeNrot"
			+ "BZ6WgybqTc/HRUwf57fyNZv8AY/sabpJl8Q+IZ9UkDeSTtgVh92MZx+fX8a7T7InoKYiy6hlINcxeLNomoG/t42kjcbZYlONw9R2yP8R3rqahngWZCrAEUAYvihk1X4f659kcSLcaZcKjLzkmNh/PtWqJdy7xcJsbkb07fmK5q6s"
			+ "bzSJZpLRfNtpQRLbMSFbIxn2PvitDwpqsF1oljZvODfwWsaTxtkNuCgEjPUZ7j1GaTQ07GtvQ9Xt2/SjEZ/5Z25/H/wCtVgordVB+opvkRf8APJP++RSsx8yIdqf88rf8/wD61GVXtAP+Bf8A1qm8iH/nkn/fIo8iIf8ALNP++RR"
			+ "ZhzIi80D/AJawL/n60eYSOLiI/Rc/1qcIo6KB+FVdQ1Ky0q2a4vbhIIh3Y9T6AdSfYUWYXMDXwX8SeE1MhbGoSyYK4GBbTD+tZevau/iS/Gk6eSbGN/38o5EpHQD2BHXv9OtDWrq58Y6taC0ilt7S1MgWTdgyhgFJxxgYyMdw3OK"
			+ "7DQtBh02BAEUEAdqpCZb0fTksbVFCgHAzxWnQBgUUCCiiigBkkSyLhgCK53UPC8UwV4Tslj5R14ZSOhB7V0tI3SgDi4LjxFouI2YX9uvQTH5wPTf1/PNTx+NzGcXuj3UR9YXWQD89p/St67+4a5q96mgDQHjjScfMl0p9DCarzeP"
			+ "bQHbbabfTt2O1VX8yc/pXPv8AeNWLX71AFqfxL4j1KLy7Gwisd2QZGbzWHoRwAD9Qajs/CV1eTrc6pdS3UgJIMzbtuTk49B7DitzT+31rdi+7QBTsNLitCdkYAAAHH5/59q0gMUUUAFFFFAH/2Q==";
	/**识别率测试说明*/
	private final static String EXPLAIN = "<html>测试方法：思路通过判断照片文件名是否相同来判断识别率<br>\r\n1、建立人脸测试库A（人数1W或者2W人），文件路径：.\\test\\facePhotoDataBase_A\\；A库中在B库中的人员：A1，"
			+ "以AB001开头，其他随意命名。<br>\r\n2，建立人脸抓拍对比库B（1千人），文件路径：.\\test\\contrastPhtotDataBase_B\\；其中B库中的抓拍照为500人在库A中的人员：B1,A1与B1库中对应人员不是同一张照片，"
			+ "但文件名相同；500人混淆人脸：B2，文件名以B002开头；<br>\r\n3、库A、库B的人脸组成按照肤色划分对比为黄种人：白种人：黑种人=5：3：2的比例组成；<br>\r\n4、将库A人脸导入人脸设备；<br>\r\n5、选中库B所在的"
			+ "文件夹后开始测试时；照片预览框会每5秒依次切换库B中的一张照片直到完成整个对比测试；<br>\r\n6、通过对比记录上报的结果来计算识别率、误识率（5s内设备没有上报记录，自己找设备原因）。<br>\r\n<br>\r\n注：<br>1、B库"
			+ "中的照片不能再A库中有相同的，否则测试无效；<br>\r\n\t2、当B库与A库中的照片名全部相同时，可进行下载成功率测试：测试下载成功的照片是否全部被识别；<br>\t3、库B1不以AB001开头时，误识率中无法加入本该识别成功却未上传"
			+ "记录的照片，导致误识率计算错误。；<br>\r\n\t</html>";
	
	
	public static Http http = null;
	/**
	 * 照片base64编码
	 */
	private static File files = null;
	private static String selectTablePersonID = null;
	public static String getNoPersonInDbBase64() {
		return noPersonInDbBase64;
	}

	public static String getNoCapturePhotoBase64() {
		return noCapturePhotoBase64;
	}


	public static JTextField serverPortTextField;
	private static JTextField setDevServerMacTextField;
	private static JTextField setDevServerDevIPTextField;
	private static JTextField setDevServerDevPortTextField;
	private static JTextField setDevServerServerIPTextField;
	private static JTextField setDevServerServerPortTextField;
	/**
	 * 接口：DeviceHeartBeat 新增设备信息
	 * 接口：GetDeviceParams 修改设备信息中的算法类型；当featureType为空时，移除设备信息
	 * @return HashMap<String, DevInfo>
	 */
	public static HashMap<String, DevInfo> devInfoMap = new HashMap<String, DevInfo>();
	private JTextField devPortTextField;
	private JComboBox<String> idTypeComboBox;
	private static JLabel capturePhuot_1, capturePhuot_2, capturePhuot_3, capturePhuot_4, capturePhuot_5, capturePhuot_6, capturePhuot_7, capturePhuot_8, capturePhuot_9, capturePhuot_10, capturePhuot_11, capturePhuot_12;
	private static JLabel captureTime_1, captureTime_2, captureTime_3, captureTime_4, captureTime_5, captureTime_6, captureTime_7, captureTime_8, captureTime_9, captureTime_10, captureTime_11, captureTime_12;
	private static JCheckBox downLoadcheckBox;
	private static JLabel downloadAuthorLabel_count;
	public static JLabel downloadAuthorLabel_success;
	public static JLabel downloadAuthorLabel_err;
	public static JProgressBar progressBar;
	public static JLabel pagePersonCount;
	public static JLabel pageCountLabel1;
	public static JComboBox<String> pageComboBox;
	private JTextField textField_1;
	private JTextField textField;
	private static JTextField serverIPJTextField;
	private static JTextField serverProtJTextField;
	private static JTextField devPortJTextField;
	private static JTextField devNoJTextField;
	private static JTextField systemIDJTextField;
	private static JTextField dbServerIPJTextField;
	private static JTextField dbUserNameJTextField;
	private static JTextField dbPasswordJTextField;
	private static JComboBox<String> dbNameComboBox;
	private static JRadioButton dbSQLRadioButton;
	private static JRadioButton dbOracleRadioButton;
	private static JRadioButton dbSQLite3RadioButton;
	private JButton button_19;
	private JTextField textField_2;
	private JCheckBox downLoadcheckBox_noFeature;
	private static JComboBox<String> serverIPComboBox;
	private static JLabel jvtSearchDeviceJlabel;
	private static JRadioButton rdbtnHttp;
	private static JRadioButton rdbtnMqtt;
	private static JLabel rrtLabel_total,rrtLabel_logs,rrtLabel_errorRate,rrtLabel_recognitionRate,rrtLabel_progress, rrtLabel_photo;
	private static JLabel CharacterLabel_count;
	private static JLabel CharacterLabel_success;
	private static JLabel CharacterLabel_fail;
	private static JLabel recordLabel_count;
	private static JLabel recordLabel_success;
	private static JLabel recordLabel_fail;
	private static JTextField mqttServerIPTextField;
	private static JTextField mqttServerPortTextField;
	private static JTable factoryTestTable;
	private static JTabbedPane testTabbedPane;
	private static JPanel testPhotoPanel, test343Panel, test361Panel;
	private static JLabel testphotoLabel, testLogsJlabel;
	private JTextField txtAdmin;
	private JTextField txtAdmin_1;
	private JTextField devMac350TextField;
	private JTextField devIp350TextField;
	private JTextField dev350SeverIpTextField;
	private JTextField dev350ServerPortTextField;
	private JTextField dev350AppVersionNameTextField;
	private JTextField dev350BuildTimeTextField;
	private JTextField dev350CameraVersionTextField;
	private JTextField dev350FimwareVersionTextField;
	private JTextField dev350HeatBeatTextField;
	private JTextField dev350AppVersionTextField;
	private JTextField dev350AlivingThresholdTextField;
	private JTextField dev350MaxPixelTextField;
	private JTextField dev350DiscernThresholdTextField;
	private JTextField dev350IdCardThresholdTextField;
	private JTextField dev350MinPixelTextField;
	private JTextField dev350DiscernIntervalTextField;
	private JTextField dev350detectThresholdTextField;
	private JTextField dev350AppTitleTextField;
	private JTextField dev350AppPassWordTextField;
	private JTextField dev350ScreanUrlTextField;
	private JTextField dev350KeepAlphaTextField;
	private JTextField dev350keepColorTextField;
	private JTextField dev350SpeakSpeedTextField;
	private JTextField dev350tempPointColorTextField;
	private JTextField dev350tempPointRectColorTextField;
	private JTextField dev350tempPointColorTextField_k;
	private JTextField dev350tempPointRectColorAlphaTextField;
	private JTextField dev350EnabletempTextField;
	private JTextField dev350TempAlarmTextField;
	private JTextField dev350tempFiterTextField;
	private JTextField dev350TimesStartTimeTextField_1;
	private JTextField dev350TimesEndTimeTextField_1;
	private JTextField dev350TimesCameraExposureTextField_1;
	private JTextField dev350IsAliveThresholdTextField_1;
	private JTextField dev350DetectThresholdTextfield_1;
	private JTextField dev350TimesStartTimeTextField_2;
	private JTextField dev350TimesEndTimeTextField_2;
	private JTextField dev350TimesCameraExposureTextField_2;
	private JTextField dev350IsAliveThresholdTextField_2;
	private JTextField dev350DetectThresholdTextfield_2;
	private JTextField dev350TimesStartTimeTextField_3;
	private JTextField dev350TimesEndTimeTextField_3;
	private JTextField dev350TimesCameraExposureTextField_3;
	private JTextField dev350IsAliveThresholdTextField_3;
	private JTextField dev350DetectThresholdTextfield_3;
	private JTextField dev350TimesStartTimeTextField_4;
	private JTextField dev350TimesEndTimeTextField_4;
	private JTextField dev350TimesCameraExposureTextField_4;
	private JTextField dev350IsAliveThresholdTextField_4;
	private JTextField dev350DetectThresholdTextfield_4;
	private JComboBox<String> dev350TempModeComboBox;
	private JComboBox<String> dev350IsAlivecomboBox;
	private JComboBox<String> dev350FaceRagecotionComboBox;
	private JComboBox<String> dev350CameraModelComboBox;
	private JComboBox<String> dev350RecotionFailCountComboBox;
	private JComboBox<String> dev350ScreanOnComboBox_5;
	private JComboBox<String> dev350ScreanTimeComboBox;
	private JComboBox<String> dev350ReportStangerComboBox;
	private JComboBox<String> dev350EnMakeupComboBox;
	private JComboBox<String> dev350IsTempFComboBox;
	private JComboBox<String> dev350LedOnComboBox;
	private JComboBox<String> dev350TimesCameraModeComboBox_1;
	private JComboBox<String> dev350IsAliveSensitivityComboBox_1;
	private JComboBox<String> dev350TimesCameraModeComboBox_2;
	private JComboBox<String> dev350IsAliveSensitivityComboBox_2;
	private JComboBox<String> dev350TimesCameraModeComboBox_3;
	private JComboBox<String> dev350IsAliveSensitivityComboBox_3;
	private JComboBox<String> dev350TimesCameraModeComboBox_4;
	private JComboBox<String> dev350IsAliveSensitivityComboBox_4;
	private JTextField d20IpTextField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainIntfaceView frame = new MainIntfaceView();
					frame.setVisible(true);
					
					new MyInitiatize().getInitialization();
					
					TestParamInit.setInitiantize(serverIPJTextField, serverProtJTextField, devPortJTextField, devNoJTextField, systemIDJTextField);
					DbServerTest.setIinitialization(dbSQLRadioButton, dbOracleRadioButton, dbSQLite3RadioButton, dbServerIPJTextField, dbUserNameJTextField, dbPasswordJTextField, dbNameComboBox);
					factoryTest = new FactoryTest(factoryTestTable);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 写日志， url 为null 则不写url
	 * @param url
	 * @param info
	 */
	public static void writeLogsTextArea(String url, String info) {
		if (logTextArea.getLineCount() > 500) {
			logTextArea.setText("");
		}
		info = LogsWriter.writeUrlInfo(LogsWriter.All_LOGS, url, info);
		
		if(info.length() < 10000) {
			logTextArea.append(info);
//			logTextArea.paintImmediately(logTextArea.getBounds()); 
			
		}else {
			logTextArea.append("[" + SystemTimes.getSysTime() + "]" + "  " + url + "\r\n");
		}
		logTextArea.setCaretPosition( logTextArea.getDocument().getLength());
	}
	
	
	public static void updateComboBox(JComboBox<String> box, List<String> macList) {
		DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) box.getModel();
		model.removeAllElements();
		for (String element : macList) {
			model.addElement(element);
		}
		box.setModel(model);
	}
	public static void updateComboBox(List<String> macList) {
		DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) faceDevchoiseComboBox.getModel();
		model.removeAllElements();
		for (String element : macList) {
			model.addElement(element);
		}
		faceDevchoiseComboBox.setModel(model);
		faceDevComboBox.setModel(model);
	}
	

	/**
	 * Create the frame.
	 */
	public MainIntfaceView() {
		setTitle("达实人脸测试工具");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 768);
		//居中显示
		Toolkit kit = Toolkit.getDefaultToolkit();
        int x = (kit.getScreenSize().width - getWidth()) / 2;
        int y = (kit.getScreenSize().height - getHeight()) / 2;
        setLocation(x, y);
        
        this.addWindowListener(new WindowAdapter() {
        	
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				JvtDeviceDll.cleanSDK();
				super.windowClosing(e);
			}
        	
		});
        
        
        
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.DARK_GRAY);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		tabbedPane.setFont(new Font("宋体", Font.PLAIN, 14));
		
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		tabbedPane.addTab("\u5B9E\u65F6\u901A\u884C\u4FE1\u606F", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(BorderFactory.createTitledBorder("未登记人员"));
		panel.add(panel_4, BorderLayout.WEST);
		panel_4.setLayout(new GridLayout(0, 3, 5, 5));
		
		JPanel panel_47 = new JPanel();
		panel_47.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(panel_47);
		panel_47.setPreferredSize(new Dimension(100,100));
		panel_47.setLayout(new BorderLayout(0, 0));
		
		capturePhuot_1 = new JLabel("");
		panel_47.add(capturePhuot_1);
		
		captureTime_1 = new JLabel("");
		captureTime_1.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_47.add(captureTime_1, BorderLayout.SOUTH);
		
		JPanel panel_48 = new JPanel();
		panel_48.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(panel_48);
		panel_48.setLayout(new BorderLayout(0, 0));
		
		capturePhuot_2 = new JLabel("");
		panel_48.add(capturePhuot_2, BorderLayout.NORTH);
		
		captureTime_2 = new JLabel("");
		captureTime_2.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_48.add(captureTime_2, BorderLayout.SOUTH);
		
		JPanel panel_49 = new JPanel();
		panel_49.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(panel_49);
		panel_49.setLayout(new BorderLayout(0, 0));
		
		capturePhuot_3 = new JLabel("");
		panel_49.add(capturePhuot_3, BorderLayout.NORTH);
		
		captureTime_3 = new JLabel("");
		captureTime_3.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_49.add(captureTime_3, BorderLayout.SOUTH);
		
		JPanel panel_50 = new JPanel();
		panel_50.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(panel_50);
		panel_50.setLayout(new BorderLayout(0, 0));
		
		capturePhuot_4 = new JLabel("");
		panel_50.add(capturePhuot_4, BorderLayout.NORTH);
		
		captureTime_4 = new JLabel("");
		captureTime_4.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_50.add(captureTime_4, BorderLayout.SOUTH);
		
		JPanel panel_51 = new JPanel();
		panel_51.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(panel_51);
		panel_51.setLayout(new BorderLayout(0, 0));
		
		capturePhuot_5 = new JLabel("");
		panel_51.add(capturePhuot_5, BorderLayout.NORTH);
		
		captureTime_5 = new JLabel("");
		captureTime_5.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_51.add(captureTime_5, BorderLayout.SOUTH);
		
		JPanel panel_52 = new JPanel();
		panel_52.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(panel_52);
		panel_52.setLayout(new BorderLayout(0, 0));
		
		capturePhuot_6 = new JLabel("");
		panel_52.add(capturePhuot_6, BorderLayout.NORTH);
		
		captureTime_6 = new JLabel("");
		captureTime_6.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_52.add(captureTime_6, BorderLayout.SOUTH);
		
		JPanel panel_53 = new JPanel();
		panel_53.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(panel_53);
		panel_53.setLayout(new BorderLayout(0, 0));
		
		capturePhuot_7 = new JLabel("");
		panel_53.add(capturePhuot_7, BorderLayout.NORTH);
		
		captureTime_7 = new JLabel("");
		captureTime_7.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_53.add(captureTime_7, BorderLayout.SOUTH);
		
		JPanel panel_54 = new JPanel();
		panel_54.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(panel_54);
		panel_54.setLayout(new BorderLayout(0, 0));
		
		capturePhuot_8 = new JLabel("");
		panel_54.add(capturePhuot_8, BorderLayout.NORTH);
		
		captureTime_8 = new JLabel("");
		captureTime_8.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_54.add(captureTime_8, BorderLayout.SOUTH);
		
		JPanel panel_55 = new JPanel();
		panel_55.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(panel_55);
		panel_55.setLayout(new BorderLayout(0, 0));
		
		capturePhuot_9 = new JLabel("");
		panel_55.add(capturePhuot_9, BorderLayout.NORTH);
		
		captureTime_9 = new JLabel("");
		captureTime_9.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_55.add(captureTime_9, BorderLayout.SOUTH);
		
		JPanel panel_56 = new JPanel();
		panel_56.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(panel_56);
		panel_56.setLayout(new BorderLayout(0, 0));
		
		capturePhuot_10 = new JLabel("");
		panel_56.add(capturePhuot_10, BorderLayout.NORTH);
		
		captureTime_10 = new JLabel("");
		captureTime_10.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_56.add(captureTime_10, BorderLayout.SOUTH);
		
		JPanel panel_57 = new JPanel();
		panel_57.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(panel_57);
		panel_57.setLayout(new BorderLayout(0, 0));
		
		capturePhuot_11 = new JLabel("");
		panel_57.add(capturePhuot_11, BorderLayout.NORTH);
		
		captureTime_11 = new JLabel("");
		captureTime_11.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_57.add(captureTime_11, BorderLayout.SOUTH);
		
		JPanel panel_58 = new JPanel();
		panel_58.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(panel_58);
		panel_58.setLayout(new BorderLayout(0, 0));
		
		capturePhuot_12 = new JLabel("");
		panel_58.add(capturePhuot_12, BorderLayout.NORTH);
		
		captureTime_12 = new JLabel("");
		captureTime_12.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_58.add(captureTime_12, BorderLayout.SOUTH);
		
//		JPanel panel_56 = new JPanel();
//		panel_4.add(panel_56);
//		panel_56.setLayout(new BorderLayout(0, 0));
//		
//		JButton button_22 = new JLabel("");
//		button_22.setEnabled(false);
//		button_22.setPreferredSize(new Dimension(100, 200));
//		button_22.setFont(new Font("宋体", Font.PLAIN, 14));
//		panel_56.add(button_22, BorderLayout.CENTER);
//		
//		JPanel panel_57 = new JPanel();
//		panel_4.add(panel_57);
//		panel_57.setLayout(new BorderLayout(0, 0));
//		
//		JButton button_23 = new JLabel("");
//		button_23.setEnabled(false);
//		button_23.setPreferredSize(new Dimension(100, 200));
//		button_23.setFont(new Font("宋体", Font.PLAIN, 14));
//		panel_57.add(button_23, BorderLayout.CENTER);
//		
//		JPanel panel_58 = new JPanel();
//		panel_4.add(panel_58);
//		panel_58.setLayout(new BorderLayout(0, 0));
//		
//		JButton button_24 = new JLabel("");
//		button_24.setEnabled(false);
//		button_24.setPreferredSize(new Dimension(100, 200));
//		button_24.setFont(new Font("宋体", Font.PLAIN, 14));
//		panel_58.add(button_24, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(BorderFactory.createTitledBorder("已注册人员"));
		panel.add(panel_5, BorderLayout.EAST);
		panel_5.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_9.setPreferredSize(new Dimension(300, 100));
		panel_5.add(panel_9);
		panel_9.setLayout(null);
		
		recordPersonInBaseDataButton_1 = new JLabel("");
		recordPersonInBaseDataButton_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		recordPersonInBaseDataButton_1.setFont(new Font("宋体", Font.PLAIN, 14));
		recordPersonInBaseDataButton_1.setBounds(1, 4, 120, 105);
		panel_9.add(recordPersonInBaseDataButton_1);
		
		similarityScoreaLabel_1 = new JLabel("");
		similarityScoreaLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		similarityScoreaLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		similarityScoreaLabel_1.setBounds(125, 4, 48, 110);
		panel_9.add(similarityScoreaLabel_1);
		
		capturePhotoButton_1 = new JLabel("");
		capturePhotoButton_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		capturePhotoButton_1.setFont(new Font("宋体", Font.PLAIN, 14));
		capturePhotoButton_1.setBounds(175, 4, 120, 105);
		panel_9.add(capturePhotoButton_1);
		
		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setPreferredSize(new Dimension(300, 100));
		panel_5.add(panel_10);
		
		recordPersonInBaseDataButton_2 = new JLabel("");
		recordPersonInBaseDataButton_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		recordPersonInBaseDataButton_2.setFont(new Font("宋体", Font.PLAIN, 14));
		recordPersonInBaseDataButton_2.setBounds(1, 4, 120, 105);
		panel_10.add(recordPersonInBaseDataButton_2);
		
		similarityScoreaLabel_2 = new JLabel("");
		similarityScoreaLabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
		similarityScoreaLabel_2.setBounds(125, 4, 48, 110);
		panel_10.add(similarityScoreaLabel_2);
		
		capturePhotoButton_2 = new JLabel("");
		capturePhotoButton_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		capturePhotoButton_2.setFont(new Font("宋体", Font.PLAIN, 14));
		capturePhotoButton_2.setBounds(175, 4, 120, 105);
		panel_10.add(capturePhotoButton_2);
		
		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setPreferredSize(new Dimension(300, 100));
		panel_5.add(panel_11);
		
		recordPersonInBaseDataButton_3 = new JLabel("");
		recordPersonInBaseDataButton_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		recordPersonInBaseDataButton_3.setFont(new Font("宋体", Font.PLAIN, 14));
		recordPersonInBaseDataButton_3.setBounds(1, 4, 120, 105);
		panel_11.add(recordPersonInBaseDataButton_3);
		
		similarityScoreaLabel_3 = new JLabel("");
		similarityScoreaLabel_3.setFont(new Font("宋体", Font.PLAIN, 14));
		similarityScoreaLabel_3.setBounds(125, 4, 48, 110);
		panel_11.add(similarityScoreaLabel_3);
		
		capturePhotoButton_3 = new JLabel("");
		capturePhotoButton_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		capturePhotoButton_3.setFont(new Font("宋体", Font.PLAIN, 14));
		capturePhotoButton_3.setBounds(175, 4, 120, 105);
		panel_11.add(capturePhotoButton_3);
		
		JPanel panel_12 = new JPanel();
		panel_12.setLayout(null);
		panel_12.setPreferredSize(new Dimension(300, 100));
		panel_5.add(panel_12);
		
		recordPersonInBaseDataButton_4 = new JLabel("");
		recordPersonInBaseDataButton_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		recordPersonInBaseDataButton_4.setFont(new Font("宋体", Font.PLAIN, 14));
		recordPersonInBaseDataButton_4.setBounds(1, 4, 120, 105);
		panel_12.add(recordPersonInBaseDataButton_4);
		
		similarityScoreaLabel_4 = new JLabel("");
		similarityScoreaLabel_4.setFont(new Font("宋体", Font.PLAIN, 14));
		similarityScoreaLabel_4.setBounds(125, 4, 48, 110);
		panel_12.add(similarityScoreaLabel_4);
		
		capturePhotoButton_4 = new JLabel("");
		capturePhotoButton_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		capturePhotoButton_4.setFont(new Font("宋体", Font.PLAIN, 14));
		capturePhotoButton_4.setBounds(175, 4, 120, 105);
		panel_12.add(capturePhotoButton_4);
		
		JPanel panel_13 = new JPanel();
		panel_13.setLayout(null);
		panel_13.setPreferredSize(new Dimension(300, 100));
		panel_5.add(panel_13);
		
		recordPersonInBaseDataButton_5 = new JLabel("");
		recordPersonInBaseDataButton_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		recordPersonInBaseDataButton_5.setFont(new Font("宋体", Font.PLAIN, 14));
		recordPersonInBaseDataButton_5.setBounds(1, 4, 120, 105);
		panel_13.add(recordPersonInBaseDataButton_5);
		
		similarityScoreaLabel_5 = new JLabel("");
		similarityScoreaLabel_5.setFont(new Font("宋体", Font.PLAIN, 14));
		similarityScoreaLabel_5.setBounds(125, 4, 48, 110);
		panel_13.add(similarityScoreaLabel_5);
		
		capturePhotoButton_5 = new JLabel("");
		capturePhotoButton_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		capturePhotoButton_5.setFont(new Font("宋体", Font.PLAIN, 14));
		capturePhotoButton_5.setBounds(175, 4, 120, 105);
		panel_13.add(capturePhotoButton_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(BorderFactory.createTitledBorder("信息统计"));
		panel.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.WEST);
		panel_7.setLayout(new GridLayout(2, 1, 0, 0));
		
		JButton btnNewButton = new JButton("1");
		btnNewButton.setEnabled(false);
		panel_7.add(btnNewButton);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(BorderFactory.createTitledBorder("实时视频流"));
		panel.add(panel_8, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("人员信息", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_15 = new JPanel();
		panel_15.setPreferredSize(new Dimension(300,150));
		panel_1.add(panel_15, BorderLayout.WEST);
		panel_15.setLayout(null);
		
		JLabel label = new JLabel("\u8BBE\u5907\u9009\u62E9\uFF1A");
		label.setFont(new Font("����", Font.PLAIN, 14));
		label.setBounds(10, 10, 73, 15);
		panel_15.add(label);
		
		faceDevComboBox = new JComboBox<String>();
		faceDevComboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		faceDevComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String st = faceDevComboBox.getSelectedItem().toString();
					faceDevComboBox.setSelectedItem(st);
					faceDevchoiseComboBox.setSelectedItem(st);
				}
			}
		});
		faceDevComboBox.setBounds(78, 7, 154, 21);
		panel_15.add(faceDevComboBox);
		
		JRadioButton faceDevRadioButton = new JRadioButton("\u5168\u9009");
		faceDevRadioButton.setEnabled(false);
		faceDevRadioButton.setFont(new Font("����", Font.PLAIN, 14));
		faceDevRadioButton.setBounds(238, 6, 62, 23);
		panel_15.add(faceDevRadioButton);
		
		JPanel panel_37 = new JPanel();
		panel_37.setBounds(3, 40, 294, 591);
		panel_37.setBorder(BorderFactory.createTitledBorder("单次注册"));
		panel_15.add(panel_37);
		panel_37.setLayout(null);
		
		JLabel label_19 = new JLabel("姓    名：");
		label_19.setFont(new Font("宋体", Font.PLAIN, 14));
		label_19.setBounds(10, 28, 104, 15);
		panel_37.add(label_19);
		
		infoNameTextField = new JTextField();
		infoNameTextField.setBounds(80, 25, 165, 21);
		panel_37.add(infoNameTextField);
		infoNameTextField.setColumns(10);
		
		JLabel label_63 = new JLabel("部    门：");
		label_63.setFont(new Font("宋体", Font.PLAIN, 14));
		label_63.setBounds(10, 58, 104, 15);
		panel_37.add(label_63);
		
		infoOpeTextField = new JTextField();
		infoOpeTextField.setColumns(10);
		infoOpeTextField.setBounds(80, 55, 206, 21);
		panel_37.add(infoOpeTextField);
		
		JLabel label_64 = new JLabel("人员类型：");
		label_64.setFont(new Font("宋体", Font.PLAIN, 14));
		label_64.setBounds(10, 88, 104, 15);
		panel_37.add(label_64);
		
		JLabel label_65 = new JLabel("性    别：");
		label_65.setFont(new Font("宋体", Font.PLAIN, 14));
		label_65.setBounds(10, 118, 80, 15);
		panel_37.add(label_65);
		
		JLabel label_66 = new JLabel("卡    号：");
		label_66.setFont(new Font("宋体", Font.PLAIN, 14));
		label_66.setBounds(10, 148, 104, 15);
		panel_37.add(label_66);
		
		InfoCardTextField = new JTextField();
		InfoCardTextField.setColumns(10);
		InfoCardTextField.setBounds(78, 145, 206, 21);
		panel_37.add(InfoCardTextField);
		
		JLabel label_67 = new JLabel("证件号码：");
		label_67.setFont(new Font("宋体", Font.PLAIN, 14));
		label_67.setBounds(10, 208, 104, 15);
		panel_37.add(label_67);
		
		infoIdTextField = new JTextField();
		infoIdTextField.setColumns(10);
		infoIdTextField.setBounds(78, 205, 206, 21);
		panel_37.add(infoIdTextField);
		
		JLabel label_68 = new JLabel("人脸启动日期：");
		label_68.setFont(new Font("宋体", Font.PLAIN, 14));
		label_68.setBounds(10, 238, 104, 15);
		panel_37.add(label_68);
		
		infoFaceStartTextField = new JTextField();
		infoFaceStartTextField.setColumns(10);
		infoFaceStartTextField.setBounds(109, 235, 175, 21);
		panel_37.add(infoFaceStartTextField);
		
		JLabel label_69 = new JLabel("人脸停止日期：");
		label_69.setFont(new Font("宋体", Font.PLAIN, 14));
		label_69.setBounds(10, 268, 104, 15);
		panel_37.add(label_69);
		
		infoFaceEndTextField = new JTextField();
		infoFaceEndTextField.setColumns(10);
		infoFaceEndTextField.setBounds(109, 265, 175, 21);
		panel_37.add(infoFaceEndTextField);
		
		JLabel label_70 = new JLabel("卡片启动日期：");
		label_70.setFont(new Font("宋体", Font.PLAIN, 14));
		label_70.setBounds(10, 298, 104, 15);
		panel_37.add(label_70);
		
		infoCardStartTextField = new JTextField();
		infoCardStartTextField.setColumns(10);
		infoCardStartTextField.setBounds(109, 295, 175, 21);
		panel_37.add(infoCardStartTextField);
		
		JLabel label_71 = new JLabel("卡片停止日期：");
		label_71.setFont(new Font("宋体", Font.PLAIN, 14));
		label_71.setBounds(10, 328, 104, 15);
		panel_37.add(label_71);
		
		infoCardEndTextField = new JTextField();
		infoCardEndTextField.setColumns(10);
		infoCardEndTextField.setBounds(109, 325, 175, 21);
		panel_37.add(infoCardEndTextField);
		
		JLabel label_72 = new JLabel("必填");
		label_72.setFont(new Font("宋体", Font.PLAIN, 14));
		label_72.setBounds(250, 28, 54, 15);
		panel_37.add(label_72);
		
		JPanel panel_38 = new JPanel();
		panel_38.setBounds(45, 348, 200, 200);
		panel_37.add(panel_38);
		panel_38.setBorder(BorderFactory.createTitledBorder("<html>请点击+号选择照片</html>"));
		panel_38.setLayout(new BorderLayout(0, 0));
		
		photoJLabel = new JLabel("+");
		photoJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		photoJLabel.setFont(new Font("宋体", Font.PLAIN, 82));
		photoJLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				files = new MyFileUtil().chooserFILES_AND_DIRECTORIES();
				if( null != files) {
					photoJLabel.setText("");
					photoJLabel.setIcon(MyPhoto.changeSize(180, 180, files.getPath()));
				}
	            
			}
		});
		panel_38.add(photoJLabel, BorderLayout.CENTER);
		
		JButton btnNewButton_6 = new JButton("新建人员并下载");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				if (null != devInfoMap.get(faceDevchoiseComboBox.getSelectedItem().toString())) {
					new PersonInfoChange(faceDevchoiseComboBox, InfoComboBox, infoGenderComboBox, idTypeComboBox, pageComboBox, infoNameTextField, infoOpeTextField, infoIdTextField, InfoCardTextField, infoFaceStartTextField, infoFaceEndTextField, infoCardStartTextField, infoCardEndTextField)
					.updatePersonInfoAndDownLoad(files, selectTablePersonID, devInfoMap);
				}else {
					JOptionPane.showMessageDialog(null, "无设备", "新建人员并下载", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnNewButton_6.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton_6.setBounds(154, 558, 134, 23);
		panel_37.add(btnNewButton_6);
		
		InfoComboBox = new JComboBox<String>();
		InfoComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"内部员工", "访客", "巨龙1", "巨龙2"}));
		InfoComboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		InfoComboBox.setBounds(80, 85, 206, 21);
		panel_37.add(InfoComboBox);
		
		infoGenderComboBox = new JComboBox<String>();
		infoGenderComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"保密", "男", "女"}));
		infoGenderComboBox.setSelectedIndex(1);
		infoGenderComboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		infoGenderComboBox.setBounds(78, 115, 206, 21);
		panel_37.add(infoGenderComboBox);
		
		JButton button_26 = new JButton("修改人员并下载");
		button_26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PersonInfoChange(faceDevchoiseComboBox, InfoComboBox, infoGenderComboBox, idTypeComboBox, pageComboBox, infoNameTextField, infoOpeTextField, infoIdTextField, InfoCardTextField, infoFaceStartTextField, infoFaceEndTextField, infoCardStartTextField, infoCardEndTextField)
				.alterPersonInfoAndDownLoad(files, selectTablePersonID, devInfoMap);
			}
		});
//		button_26.setEnabled(false);
		button_26.setFont(new Font("宋体", Font.PLAIN, 14));
		button_26.setBounds(10, 558, 134, 23);
		panel_37.add(button_26);
		
		JLabel label_79 = new JLabel("证件类型：");
		label_79.setFont(new Font("宋体", Font.PLAIN, 14));
		label_79.setBounds(10, 178, 104, 15);
		panel_37.add(label_79);
		
		idTypeComboBox = new JComboBox<String>();
		idTypeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"身份证 ", "港澳通行证 ", "驾驶证 ", "护照 ", "其他"}));
		idTypeComboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		idTypeComboBox.setBounds(78, 175, 206, 21);
		panel_37.add(idTypeComboBox);
		
		JPanel panel_16 = new JPanel();
		panel_1.add(panel_16, BorderLayout.CENTER);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_17 = new JPanel();
		panel_16.add(panel_17, BorderLayout.CENTER);
		panel_17.setLayout(new BorderLayout(0, 0));
		
		personInfoTable = new JTable();
		personInfoTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u59D3\u540D", "\u90E8\u95E8", "\u4EBA\u5458\u7C7B\u578B", "\u6027\u522B", "\u5361\u53F7", "\u8BC1\u4EF6\u7C7B\u578B", "\u8BC1\u4EF6\u53F7\u53F7", "\u4EBA\u8138\u542F\u52A8\u65E5\u671F", "\u4EBA\u8138\u505C\u6B62\u65E5\u671F", "\u5361\u7247\u542F\u52A8\u65E5\u671F", "\u5361\u7247\u505C\u6B62\u65E5\u671F", "id"
			}
		));
		personInfoTable.getColumnModel().getColumn(0).setPreferredWidth(60);
		personInfoTable.getColumnModel().getColumn(1).setPreferredWidth(70);
		personInfoTable.getColumnModel().getColumn(2).setPreferredWidth(65);
		personInfoTable.getColumnModel().getColumn(2).setMaxWidth(75);
		personInfoTable.getColumnModel().getColumn(3).setPreferredWidth(40);
		personInfoTable.getColumnModel().getColumn(3).setMaxWidth(40);
		personInfoTable.getColumnModel().getColumn(5).setPreferredWidth(65);
		personInfoTable.getColumnModel().getColumn(5).setMaxWidth(80);
		personInfoTable.getColumnModel().getColumn(11).setPreferredWidth(30);
		personInfoTable.getColumnModel().getColumn(11).setMinWidth(30);
		personInfoTable.getColumnModel().getColumn(11).setMaxWidth(45);
		personInfoTable.setFont(new Font("宋体", Font.PLAIN, 14));
		personInfoTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int selectRow = personInfoTable.getSelectedRow();
				DefaultTableModel tableModel = (DefaultTableModel)personInfoTable.getModel();
				@SuppressWarnings("unchecked")
				Vector<String> vt = (Vector<String>)tableModel.getDataVector().get(selectRow);
				for (int i =0 ; i < vt.size(); i++) {
					if(null == vt.get(i)) {
						vt.set(i, "");
					}
				}
				
				String selectValues = personInfoTable.getValueAt(selectRow, personInfoTable.getColumnCount()-1).toString();
//				System.out.println(selectValues);
				infoNameTextField.setText(vt.get(0).toString());
				infoOpeTextField.setText(vt.get(1).toString());
				InfoComboBox.setSelectedItem(vt.get(2).toString());
				infoGenderComboBox.setSelectedItem(vt.get(3).toString());
				InfoCardTextField.setText(vt.get(4).toString());
				idTypeComboBox.setSelectedItem(vt.get(5));
				infoIdTextField.setText(vt.get(6).toString());
				infoFaceStartTextField.setText(vt.get(7).toString());
				infoFaceEndTextField.setText(vt.get(8).toString());
				infoCardStartTextField.setText(vt.get(9).toString());
				infoCardEndTextField.setText(vt.get(10).toString());
				selectTablePersonID = vt.get(11);
//				ImageIcon icon = MyPhoto.getImageIconfromBASE64(DataBaseExecute.getInstance().listResult("face_photo", "person_id", selectValues, "PhotoBase64").get(0), 180, 180);
				
				List<String> list = DataBaseExecute.getInstance().listResult("face_photo", "person_id", selectValues, "photo_url");
				ImageIcon icon = null;
				if(!list.isEmpty()) {
					files= new File(list.get(0));
					if(files.exists()) {
						icon = MyPhoto.getImageIconfromBASE64(MyPhoto.getPhoteBASE64_Mime(files), 180, 180);
					}
				}else {
					icon = MyPhoto.getImageIconfromBASE64(noPersonInDbBase64, 180, 180);
				}
				photoJLabel.setText("");
				photoJLabel.setIcon(icon);
			}
		});
		panel_17.add(new JScrollPane(personInfoTable), BorderLayout.CENTER);
		
		JPanel panel_59 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_59.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_17.add(panel_59, BorderLayout.SOUTH);
		
		JButton button_30 = new JButton("第一页");
		button_30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageComboBox.setSelectedIndex(0);
			}
		});
		
		JButton button_2 = new JButton("刷新");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pageComboBox.getSelectedIndex() <= 1) {
					PageBean<PersonInfo> pageBean = new PageBeanTool().getPage(1);
					new UpdateTable().updateTableFromPersonInfoList(personInfoTable, pageBean.getPageData());
					pageCountLabel1.setText(Integer.toString(pageBean.getTotalPage()));
					pagePersonCount.setText(Integer.toString(pageBean.getTotalCount()));
				}else {
					pageComboBox.setSelectedIndex(pageComboBox.getSelectedIndex());
				}
			}
		});
		button_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		panel_59.add(button_2);
		button_30.setFont(new Font("Dialog", Font.PLAIN, 12));
		panel_59.add(button_30);
		
		JButton button_31 = new JButton("最后一页");
		button_31.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultComboBoxModel<String> boxModel = (DefaultComboBoxModel<String>)pageComboBox.getModel();
				
				int i = boxModel.getSize() -1;
				pageComboBox.setSelectedIndex(i);
			}
		});
		button_31.setFont(new Font("Dialog", Font.PLAIN, 12));
		panel_59.add(button_31);
		
		JLabel lblNewLabel_2 = new JLabel("当前页：");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_59.add(lblNewLabel_2);
		
		JButton btnNewButton_8 = new JButton("上一页");
		btnNewButton_8.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int page = pageComboBox.getSelectedIndex() - 1;
				if(page <= 0) {
					page = 0;
				}
				pageComboBox.setSelectedIndex(page);
//				PageBean<PersonInfo> pageBean = new PageBeanTool().getPage(page);
//				updateTable(pageBean.getPageData());
			}
		});
		panel_59.add(btnNewButton_8);
		
		pageComboBox = new JComboBox<String>();
		pageComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"1"}));
		pageComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED) {
//					DefaultTableModel tableModel = (DefaultTableModel) personInfoTable.getModel();
//					tableModel.setRowCount(0);
					
					int page = pageComboBox.getSelectedIndex() + 1;
//					System.out.println(page);
					PageBean<PersonInfo> pageBean = new PageBeanTool().getPage(page);
					new UpdateTable().updateTableFromPersonInfoList(personInfoTable, pageBean.getPageData());
					pageCountLabel1.setText(Integer.toString(pageBean.getTotalPage()));
					pagePersonCount.setText(Integer.toString(pageBean.getTotalCount()));
				}
			}
		});
		pageComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		panel_59.add(pageComboBox);
		
		JButton button_25 = new JButton("下一页");
		button_25.setFont(new Font("Dialog", Font.PLAIN, 12));
		button_25.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int page = pageComboBox.getSelectedIndex() + 1;
				int pageCount = Integer.parseInt(pageCountLabel1.getText());
				if(page >= pageCount) {
					page = pageCount - 1;
				}
				pageComboBox.setSelectedIndex(page);
				
			}
		});
		panel_59.add(button_25);
		
		JLabel pageCountLabel = new JLabel("  总页数：");
		pageCountLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_59.add(pageCountLabel);
		
		pageCountLabel1 = new JLabel("        ");
		pageCountLabel1.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_59.add(pageCountLabel1);
		
		JLabel lblNewLabel_5 = new JLabel("总人数：");
		lblNewLabel_5.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_59.add(lblNewLabel_5);
		
		pagePersonCount = new JLabel("    ");
		pagePersonCount.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_59.add(pagePersonCount);
		
		JPanel panel_18 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_18.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_17.add(panel_18, BorderLayout.NORTH);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"姓名", "卡号", "ID"}));
		panel_18.add(comboBox);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_18.add(textField);
		textField.setColumns(40);
		
		JButton btnNewButton_2 = new JButton("查询");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QueryTable.updateTableRowVector(personInfoTable,comboBox.getSelectedItem().toString(), textField.getText().replaceAll(" ", ""));
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_18.add(btnNewButton_2);
		
		JSplitPane splitPane = new JSplitPane();
		tabbedPane.addTab("设备参数设置", null, splitPane, null);
		
		JPanel panel_21 = new JPanel();
		splitPane.setLeftComponent(panel_21);
		panel_21.setLayout(new GridLayout(16, 1, 10, 10));
		
		JPanel panel_23 = new JPanel();
		panel_21.add(panel_23);
		panel_23.setLayout(new BorderLayout(0, 0));
		
		JLabel label_2 = new JLabel("设备选择：");
		panel_23.add(label_2, BorderLayout.WEST);
		label_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		faceDevchoiseComboBox = new JComboBox<String>();
		faceDevchoiseComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"---请选择设备---"}));
		faceDevchoiseComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String st = faceDevchoiseComboBox.getSelectedItem().toString();
					faceDevComboBox.setSelectedItem(st);
					faceDevchoiseComboBox.setSelectedItem(st);
					if (!devInfoMap.isEmpty() && null != devInfoMap.get(st)) {
						if (devInfoMap.get(st).getDevModel() == null) {
							cardLayout.show(panel_22, "devParameter");
						}else {
							switch (devInfoMap.get(st).getDevModel()) {
							case "350":
								cardLayout.show(panel_22, "350");
								break;
								
							default:
								cardLayout.show(panel_22, "devParameter");
								break;
							}
						}
					}else {
						cardLayout.show(panel_22, "devParameter");
					}
				}
			}
		});
		panel_23.add(faceDevchoiseComboBox, BorderLayout.CENTER);
		faceDevchoiseComboBox.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JRadioButton radioButton_1 = new JRadioButton("全选");
		radioButton_1.setEnabled(false);
		panel_23.add(radioButton_1, BorderLayout.EAST);
		radioButton_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JButton button = new JButton("读取设备参数");
		MyMouseListener(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (faceDevchoiseComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "无设备可读取！！！", "读取设备参数", JOptionPane.ERROR_MESSAGE);
				}else {
					new Thread(()->{
							RequestGson<String> requestGson2 = new RequestGson<String>();
							requestGson2.setDeviceUniqueCode(faceDevchoiseComboBox.getSelectedItem().toString());
							requestGson2.setData(null);
							new GetDeviceParams(new GsonBuilder().serializeNulls().create().toJson(requestGson2, RequestGson.class), faceDevchoiseComboBox.getSelectedItem().toString());
						})
					.start(); 
					
				}
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_21.add(button);
		
		JButton button_1 = new JButton("设置设备参数");
		MyMouseListener(button_1);
		button_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					DeviceParameterGson deviceParameterGson = getDeviceParamsFormView();
					
					String macAddr = faceDevchoiseComboBox.getSelectedItem().toString();
					RequestGson<DeviceParameterGson> requestGson = new RequestGson<DeviceParameterGson>();
					requestGson.setData(deviceParameterGson);
					requestGson.setDeviceUniqueCode(macAddr);
					requestGson.setTimeStamp(SystemTimes.getSysTime1());
					
					new Thread(()->{
							new SetDeviceParams(
									new Gson().toJson(
											requestGson, 
											new TypeToken<RequestGson<DeviceParameterGson>>() {}.getType()), 
									macAddr);
						})
					.start(); 
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "参数某些值不能为空", "设置设备参数", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_21.add(button_1);
		button_1.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JButton button_4 = new JButton("重启设备");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(()->{
					String macAddr = faceDevchoiseComboBox.getSelectedItem().toString();
					RequestGson<String> requestGson = new RequestGson<String>();
					requestGson.setData(null);
					requestGson.setDeviceUniqueCode(macAddr);
					requestGson.setTimeStamp(SystemTimes.getSysTime1());
					new RestartDevice(
							new GsonBuilder().serializeNulls().create()
							.toJson(
									requestGson, 
									new TypeToken<RequestGson<String>>() {}.getType()), 
							macAddr);
				})
				.start(); 
			}
		});
		MyMouseListener(button_4);
		button_4.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_21.add(button_4);
		
		JButton button_5 = new JButton("远程开门");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(()->{
					String macAddr = faceDevchoiseComboBox.getSelectedItem().toString();
					RequestGson<String> requestGson = new RequestGson<String>();
					requestGson.setData(null);
					requestGson.setDeviceUniqueCode(macAddr);
					requestGson.setTimeStamp(SystemTimes.getSysTime1());
					new RemoteOpenDoor(
							new GsonBuilder().serializeNulls().create()
							.toJson(
									requestGson, 
									new TypeToken<RequestGson<String>>() {}.getType()), 
							macAddr);
				})
				.start(); 
				
			}
		});
		MyMouseListener(button_5);
		button_5.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_21.add(button_5);
		
		JButton button_9 = new JButton("读取屏保文字和图片");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(()->{
					String macAddr = faceDevchoiseComboBox.getSelectedItem().toString();
					RequestGson<String> requestGson = new RequestGson<String>();
					requestGson.setData(null);
					requestGson.setDeviceUniqueCode(macAddr);
					requestGson.setTimeStamp(SystemTimes.getSysTime1());
					new GetScreenSaver(
							new GsonBuilder().serializeNulls().create()
							.toJson(
									requestGson, 
									new TypeToken<RequestGson<String>>() {}.getType()), 
							macAddr);
				})
				.start(); 
				
			}
		});
		MyMouseListener(button_9);
		button_9.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_21.add(button_9);
		
		JButton button_8 = new JButton("设置屏保文字和图片");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (uiFile != null) {
					new Thread(() ->{
						String uiString  = uiTextField.getText().replaceAll(" ", "");
						if( null == uiString) {
							uiString = "";
						}
						ScreenSaverGson screenSaverGson = new ScreenSaverGson();
						ImageEx imageEx = screenSaverGson.new ImageEx();
						imageEx.setImage(MyPhoto.getPhoteBASE64_Mime(uiFile));
						screenSaverGson.setDeviceHelloWord(uiString);
						screenSaverGson.setImageList(Arrays.asList(imageEx));
						uiFile = null;//回收
						String macAddr = faceDevchoiseComboBox.getSelectedItem().toString();
						RequestGson<ScreenSaverGson> requestGson = new RequestGson<ScreenSaverGson>();
						requestGson.setData(screenSaverGson);
						requestGson.setDeviceUniqueCode(macAddr);
						
						new SetScreenSaver(new Gson().toJson(requestGson, new TypeToken<ResponseGson<ScreenSaverGson>>() {}.getType()), macAddr);
					}) 
					.start();
					
				}
				
			}
		});
		button_8.setFont(new Font("宋体", Font.PLAIN, 14));
		MyMouseListener(button_8);
		panel_21.add(button_8);
		
		JButton button_7 = new JButton("在线升级");
		button_7.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
//				CardLayout card = (CardLayout) panel_22.getLayout();
//				card.show(panel_22, "devParameter");
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				cardLayout.show(panel_22, "downLoad");
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc=new JFileChooser();  
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
				jfc.setCurrentDirectory(new File("./APK"));
				jfc.showDialog(new JLabel(), "选择");  
				File file=jfc.getSelectedFile();  
				//System.out.println(file.getName());
				String url;
				if( null != file) {
//					try {
					DownLoadApk.setFileName(file.getName());
					DownLoadApk.setFilePath(file.getParent());
					
						url = "http://" + Http.getServerIP() + ":" + Http.getPort() + DownLoadApk.URL_NAME +"/"+ file.getName();
						String macAddr = faceDevchoiseComboBox.getSelectedItem().toString();
//						System.out.println(url);
						UpgradeAppGson upgradeAppGson = new UpgradeAppGson();
						upgradeAppGson.setAppUrl(url);
						
						RequestGson<UpgradeAppGson> requestGson = new RequestGson<UpgradeAppGson>();
						requestGson.setData(upgradeAppGson);
						requestGson.setDeviceUniqueCode(macAddr);
						copyurlTextField_1.setText(url);
						
						
						new Thread(() -> {
							new UpgradeApp(new Gson().toJson(requestGson, new TypeToken<RequestGson<UpgradeAppGson>>() {}.getType()), macAddr);
						}).start(); 
//					} catch (UnknownHostException e1) {
//						e1.printStackTrace();
//					}
				}
			}
		});
		
		JButton button_10 = new JButton("查询人员身份名单");
		button_10.setEnabled(false);
		button_10.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_21.add(button_10);
		
		JButton button_6 = new JButton("获取设备记录");
		button_6.setEnabled(false);
		button_6.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_21.add(button_6);
		button_7.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_21.add(button_7);
		
//		JButton btnip = new JButton("设置远程IP和端口");
//		btnip.setFont(new Font("宋体", Font.PLAIN, 14));
//		panel_21.add(btnip);
//		btnip.setEnabled(false);
//		btnip.addMouseListener(new MouseListener() {
//			
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				
//				
//			}
//			
//			@Override
//			public void mousePressed(MouseEvent e) {
//				
//				
//			}
//			
//			@Override
//			public void mouseExited(MouseEvent e) {
//				
////				CardLayout card = (CardLayout) panel_22.getLayout();
////				card.show(panel_22, "devParameter");
//			}
//			
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				
//				cardLayout.show(panel_22, "downLoad");
//			}
//			
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				
//				
//			}
//		});
//		btnip.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//				DeviceParameterGson dpg = new DeviceParameterGson();
//				BasicParams BasicParams = dpg.new BasicParams();
//				BasicParams.setDeviceName("Test");
//				BasicParams.setServerIP(setDevServerServerIPTextField.getText().replaceAll(" ", ""));
//				BasicParams.setServerPort(Integer.parseInt(setDevServerServerPortTextField.getText().replaceAll(" ", "")));
//				BasicParams.setIsUploadPassImg(1);
//				BasicParams.setTriggerActionInterval(3000);
//				BasicParams.setHeartBeatInterval(20000);
//				dpg.setBasicParams(BasicParams);
////				FeatureParams FeatureParams = dpg.new FeatureParams();
//				
//				RecognitionParams RecognitionParams = dpg.new RecognitionParams();
//				RecognitionParams.setSimilityThreshold("50");
//				RecognitionParams.setQualityThreshold("50");
//				RecognitionParams.setMinFacePixel(50);
//				RecognitionParams.setMaxFacePixel(800);
//				RecognitionParams.setIsAlive(1);
//				RecognitionParams.setLivingThreshold(50d);
//				dpg.setRecognitionParams(RecognitionParams);
//				
//				AppParams AppParams = dpg.new AppParams();
//				AppParams.setAppPassword("123456");
//				dpg.setAppParams(AppParams);
//				
////				AccessParams AccessParams = dpg.new AccessParams();
////				CameraParams CameraParams = dpg.new CameraParams();
////				HardWareParams HardWareParams = dpg.new HardWareParams();
//				
//				VoiceTipParams VoiceTipParams = dpg.new VoiceTipParams();
//				VoiceTipParams.setRecognizeSuccessTip("@识别成功");
//				VoiceTipParams.setRecognizeErrorTip("@识别成功");
//				dpg.setVoiceTipParams(VoiceTipParams);
//				
//				ConsumeParams ConsumeParams = dpg.new ConsumeParams();
//				ConsumeParams.setPayUrl("");
//				ConsumeParams.setQueryUrl("");
//				ConsumeParams.setDeviceNum("10000");
//				ConsumeParams.setOfflineMode(0);
//				dpg.setConsumeParams(ConsumeParams);
//				
//				Gson gson = new Gson();
////				System.out.println(gson.toJson(dpg));
//				JSONObject jsonObject = new JSONObject(gson.toJson(dpg));
//				JSONObject setDeviceParams = new JSONObject();
//				setDeviceParams.put("DeviceUniqueCode", setDevServerMacTextField.getText().replaceAll(" ", ""));
//				setDeviceParams.put("TimeStamp", SystemTimes.getSysTime1());
//				setDeviceParams.put("Data", jsonObject);
////				System.out.println("setDeviceParams = " + setDeviceParams.toString());
//				OkHttpClientUtil.setDevApiIP(setDevServerDevIPTextField.getText().replaceAll(" ", ""));
//				OkHttpClientUtil.setDevApiPort(Integer.parseInt(devPortTextField.getText().replaceAll(" ", "")));
//				
//				new Thread(new Runnable() {
//					@Override
//					public void run() {
//						
//						new SetDeviceParams(setDeviceParams);
//					}
//				}).start();
//				
//			}
//		});
		
		JButton button_20 = new JButton("恢复出厂设置");
		button_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Thread(() ->{
					
					ClearDeviceDataRequestGson clearDeviceDataGson = new ClearDeviceDataRequestGson();
					clearDeviceDataGson.setClearLog("Y");
					clearDeviceDataGson.setClearPassRecord("Y");
					clearDeviceDataGson.setClearPerson("Y");
					
					String macAddr = faceDevchoiseComboBox.getSelectedItem().toString();
					
					RequestGson<ClearDeviceDataRequestGson> requestGson = new RequestGson<ClearDeviceDataRequestGson>();
					requestGson.setDeviceUniqueCode(macAddr);
					requestGson.setData(clearDeviceDataGson);
					
					new ClearDeviceData(new Gson().toJson(requestGson, new TypeToken<RequestGson<ClearDeviceDataRequestGson>>() {}.getType()), macAddr);
				}).start();
			}
		});
		button_20.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_21.add(button_20);
		
		
		panel_22 = new JPanel();
//		panel_22.setPreferredSize(preferredSize);
		splitPane.setRightComponent(new JScrollPane(panel_22));
		cardLayout = new CardLayout();
		panel_22.setLayout(cardLayout);
		cardLayout.show(panel_22, "350");
		
		panel_24 = new JPanel();
		panel_24.setPreferredSize(new Dimension(800,780));
		panel_22.add(new JScrollPane(panel_24), "devParameter");
		panel_24.setLayout(null);
		
		JPanel panel_25 = new JPanel();
		panel_25.setBorder(BorderFactory.createTitledBorder("识别参数"));
		panel_25.setBounds(10, 215, 406, 142);
		panel_24.add(panel_25);
		panel_25.setLayout(null);
		
		JLabel label_12 = new JLabel("活体类型：");
		label_12.setFont(new Font("宋体", Font.PLAIN, 14));
		label_12.setBounds(10, 26, 77, 15);
		panel_25.add(label_12);
		
		JLabel label_15 = new JLabel("活体阈值：");
		label_15.setFont(new Font("宋体", Font.PLAIN, 14));
		label_15.setBounds(10, 56, 77, 15);
		panel_25.add(label_15);
		
		recognitionParameterLivingThresdhoidTextField = new JTextField();
		recognitionParameterLivingThresdhoidTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		recognitionParameterLivingThresdhoidTextField.setColumns(10);
		recognitionParameterLivingThresdhoidTextField.setBounds(82, 52, 105, 21);
		panel_25.add(recognitionParameterLivingThresdhoidTextField);
		
		JLabel label_23 = new JLabel("识别间隔：");
		label_23.setFont(new Font("宋体", Font.PLAIN, 14));
		label_23.setBounds(10, 86, 77, 15);
		panel_25.add(label_23);
		
		recognitionParameterTriggerActionIntervalTextField = new JTextField();
		recognitionParameterTriggerActionIntervalTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		recognitionParameterTriggerActionIntervalTextField.setColumns(10);
		recognitionParameterTriggerActionIntervalTextField.setBounds(82, 82, 105, 21);
		panel_25.add(recognitionParameterTriggerActionIntervalTextField);
		
		JLabel label_24 = new JLabel("相识度阈值：");
		label_24.setBounds(198, 26, 96, 15);
		panel_25.add(label_24);
		label_24.setFont(new Font("宋体", Font.PLAIN, 14));
		
		recognitionParameterSimilityThresholdTextField = new JTextField();
		recognitionParameterSimilityThresholdTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		recognitionParameterSimilityThresholdTextField.setColumns(10);
		recognitionParameterSimilityThresholdTextField.setBounds(279, 23, 117, 21);
		panel_25.add(recognitionParameterSimilityThresholdTextField);
		
		JLabel label_30 = new JLabel("质量分阈值：");
		label_30.setFont(new Font("宋体", Font.PLAIN, 14));
		label_30.setBounds(197, 56, 97, 15);
		panel_25.add(label_30);
		
		recognitionParameterQualityThresholdTextField = new JTextField();
		recognitionParameterQualityThresholdTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		recognitionParameterQualityThresholdTextField.setColumns(10);
		recognitionParameterQualityThresholdTextField.setBounds(279, 53, 117, 21);
		panel_25.add(recognitionParameterQualityThresholdTextField);
		
		JLabel label_25 = new JLabel("最小像素：");
		label_25.setFont(new Font("宋体", Font.PLAIN, 14));
		label_25.setBounds(10, 116, 97, 15);
		panel_25.add(label_25);
		
		recognitionParameterMinFacePixelTextField = new JTextField();
		recognitionParameterMinFacePixelTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		recognitionParameterMinFacePixelTextField.setColumns(10);
		recognitionParameterMinFacePixelTextField.setBounds(82, 113, 105, 21);
		panel_25.add(recognitionParameterMinFacePixelTextField);
		
		JLabel label_26 = new JLabel("最大  像素：");
		label_26.setFont(new Font("宋体", Font.PLAIN, 14));
		label_26.setBounds(197, 116, 105, 15);
		panel_25.add(label_26);
		
		recognitionParameterMaxFacePixelTextField = new JTextField();
		recognitionParameterMaxFacePixelTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		recognitionParameterMaxFacePixelTextField.setColumns(10);
		recognitionParameterMaxFacePixelTextField.setBounds(279, 113, 117, 21);
		panel_25.add(recognitionParameterMaxFacePixelTextField);
		
		JLabel label_60 = new JLabel("身份证阈值：");
		label_60.setEnabled(false);
		label_60.setFont(new Font("宋体", Font.PLAIN, 14));
		label_60.setBounds(197, 86, 97, 15);
		panel_25.add(label_60);
		
		recognitionParameterPidTextField = new JTextField();
		recognitionParameterPidTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		recognitionParameterPidTextField.setEnabled(false);
		recognitionParameterPidTextField.setColumns(10);
		recognitionParameterPidTextField.setBounds(279, 83, 117, 21);
		panel_25.add(recognitionParameterPidTextField);
		
		recognitionParameterALiveVomboBox = new JComboBox<String>();
		recognitionParameterALiveVomboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"0:不支持", "1:开启", "2:关闭"}));
		recognitionParameterALiveVomboBox.setFont(new Font("Dialog", Font.PLAIN, 14));
		recognitionParameterALiveVomboBox.setBounds(82, 20, 105, 26);
		panel_25.add(recognitionParameterALiveVomboBox);
		
		JPanel panel_26 = new JPanel();
		panel_26.setBorder(BorderFactory.createTitledBorder("输出设置"));
		panel_26.setBounds(452, 200, 209, 253);
		panel_24.add(panel_26);
		panel_26.setLayout(null);
		
		JLabel label_16 = new JLabel("维根输出：");
		label_16.setBounds(10, 26, 77, 15);
		panel_26.add(label_16);
		label_16.setFont(new Font("宋体", Font.PLAIN, 14));
		
		devOutWeigand_Out_ComboBox = new JComboBox<String>();
		devOutWeigand_Out_ComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"不输出维根", "正序维根26", "反序维根26", "正序维根34", "反序维根34"}));
		devOutWeigand_Out_ComboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		devOutWeigand_Out_ComboBox.setBounds(78, 20, 114, 26);
		panel_26.add(devOutWeigand_Out_ComboBox);
		
		JLabel label_1 = new JLabel("脉冲时长：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(10, 89, 77, 15);
		panel_26.add(label_1);
		
		devOutRelayTimeTextField = new JTextField();
		devOutRelayTimeTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		devOutRelayTimeTextField.setColumns(10);
		devOutRelayTimeTextField.setBounds(78, 86, 89, 21);
		panel_26.add(devOutRelayTimeTextField);
		
		JLabel label_5 = new JLabel("毫秒");
		label_5.setBounds(177, 89, 36, 15);
		panel_26.add(label_5);
		label_5.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JLabel label_14 = new JLabel("二维码识别：");
		label_14.setBounds(10, 149, 104, 15);
		panel_26.add(label_14);
		label_14.setFont(new Font("宋体", Font.PLAIN, 14));
		
		devOutQrCode_No_RadioButton = new JRadioButton("不支持");
		devOutQrCode_No_RadioButton.setFont(new Font("宋体", Font.PLAIN, 13));
		devOutQrCode_No_RadioButton.setBounds(20, 170, 70, 23);
		panel_26.add(devOutQrCode_No_RadioButton);
		
		devOutQrCode_Open_RadioButton = new JRadioButton("开启");
		devOutQrCode_Open_RadioButton.setFont(new Font("宋体", Font.PLAIN, 13));
		devOutQrCode_Open_RadioButton.setBounds(90, 170, 55, 23);
		panel_26.add(devOutQrCode_Open_RadioButton);
		
		devOutQrCode_Close_RadioButton = new JRadioButton("关闭");
		devOutQrCode_Close_RadioButton.setFont(new Font("宋体", Font.PLAIN, 13));
		devOutQrCode_Close_RadioButton.setBounds(145, 170, 55, 23);
		panel_26.add(devOutQrCode_Close_RadioButton);
				
		ButtonGroup QrCode = new ButtonGroup();
		QrCode.add(devOutQrCode_No_RadioButton);
		QrCode.add(devOutQrCode_Open_RadioButton);
		QrCode.add(devOutQrCode_Close_RadioButton);
		
		JLabel label_13 = new JLabel("刷卡模块：");
		label_13.setFont(new Font("宋体", Font.PLAIN, 14));
		label_13.setBounds(10, 202, 104, 15);
		panel_26.add(label_13);
		
		devOutCard_No_RadioButton = new JRadioButton("不支持");
		devOutCard_No_RadioButton.setFont(new Font("宋体", Font.PLAIN, 13));
		devOutCard_No_RadioButton.setBounds(20, 223, 70, 23);
		panel_26.add(devOutCard_No_RadioButton);
		
		devOutCard_Open_RadioButton = new JRadioButton("开启");
		devOutCard_Open_RadioButton.setFont(new Font("宋体", Font.PLAIN, 13));
		devOutCard_Open_RadioButton.setBounds(90, 223, 55, 23);
		panel_26.add(devOutCard_Open_RadioButton);
		
		devOutCard_Close_RadioButton = new JRadioButton("关闭");
		devOutCard_Close_RadioButton.setFont(new Font("宋体", Font.PLAIN, 13));
		devOutCard_Close_RadioButton.setBounds(145, 223, 55, 23);
		panel_26.add(devOutCard_Close_RadioButton);
		
		ButtonGroup card = new ButtonGroup();
		card.add(devOutCard_No_RadioButton);
		card.add(devOutCard_Open_RadioButton);
		card.add(devOutCard_Close_RadioButton);
		
		JLabel label_59 = new JLabel("开门方式：");
		label_59.setFont(new Font("宋体", Font.PLAIN, 14));
		label_59.setBounds(10, 119, 77, 15);
		panel_26.add(label_59);
		
		devOutOpenDoorTypeComboBox = new JComboBox<String>();
		devOutOpenDoorTypeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"纯人脸模式", "卡加人脸模式", "卡或人脸模式", "二维码或人脸模式", "人证对比模式", "人证或人脸模式"}));
		devOutOpenDoorTypeComboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		devOutOpenDoorTypeComboBox.setBounds(78, 114, 114, 26);
		panel_26.add(devOutOpenDoorTypeComboBox);
		
		JLabel label_62 = new JLabel("维根输入：");
		label_62.setFont(new Font("宋体", Font.PLAIN, 14));
		label_62.setBounds(10, 57, 77, 15);
		panel_26.add(label_62);
		
		devOutWeigand_In_ComboBox = new JComboBox<String>();
		devOutWeigand_In_ComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"不输入维根", "正序维根26", "反序维根26", "正序维根34", "反序维根34"}));
		devOutWeigand_In_ComboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		devOutWeigand_In_ComboBox.setBounds(78, 51, 114, 26);
		panel_26.add(devOutWeigand_In_ComboBox);
		
		JPanel panel_27 = new JPanel();
		panel_27.setBorder(BorderFactory.createTitledBorder("相机、补光灯设置"));
		panel_27.setBounds(416, 10, 245, 180);
		panel_24.add(panel_27);
		panel_27.setLayout(null);
		
		JLabel label_39 = new JLabel("相机模式：");
		label_39.setBounds(10, 31, 77, 15);
		panel_27.add(label_39);
		label_39.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JLabel label_40 = new JLabel("美颜效果：");
		label_40.setBounds(10, 60, 77, 15);
		panel_27.add(label_40);
		label_40.setFont(new Font("宋体", Font.PLAIN, 14));
		
		CameraBeautyScoreTextField = new JTextField();
		CameraBeautyScoreTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		CameraBeautyScoreTextField.setBounds(82, 57, 73, 21);
		panel_27.add(CameraBeautyScoreTextField);
		CameraBeautyScoreTextField.setColumns(10);
		
		JLabel label_41 = new JLabel("补光灯模式：");
		label_41.setBounds(10, 92, 118, 15);
		panel_27.add(label_41);
		label_41.setFont(new Font("宋体", Font.PLAIN, 14));
		
		suppleLightOpenTimeTextField = new JTextField();
		suppleLightOpenTimeTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		suppleLightOpenTimeTextField.setBounds(10, 147, 132, 21);
		panel_27.add(suppleLightOpenTimeTextField);
		suppleLightOpenTimeTextField.setColumns(10);
		
		JLabel label_42 = new JLabel("补光灯时段：");
		label_42.setBounds(10, 122, 118, 15);
		panel_27.add(label_42);
		label_42.setFont(new Font("宋体", Font.PLAIN, 14));
		
		CameraModeComboBox = new JComboBox<String>();
		CameraModeComboBox.setEnabled(false);
		CameraModeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"0:设备不支持", "1:室内", "2:室外", "3:智能"}));
		CameraModeComboBox.setFont(new Font("Dialog", Font.PLAIN, 14));
		CameraModeComboBox.setBounds(81, 20, 109, 26);
		panel_27.add(CameraModeComboBox);
		
		JLabel label_20 = new JLabel("\"18:00-07:00\"");
		label_20.setFont(new Font("宋体", Font.PLAIN, 12));
		label_20.setBounds(151, 151, 84, 15);
		panel_27.add(label_20);
		
		suppleLightModelcomboBox = new JComboBox<String>();
		suppleLightModelcomboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"0:不支持", "1:自动", "2:常开", "3:常关"}));
		suppleLightModelcomboBox.setFont(new Font("Dialog", Font.PLAIN, 14));
		suppleLightModelcomboBox.setBounds(92, 86, 98, 26);
		panel_27.add(suppleLightModelcomboBox);
		
		JPanel panel_28 = new JPanel();
		panel_28.setBorder(BorderFactory.createTitledBorder("语音"));
		panel_28.setBounds(671, 10, 257, 260);
		panel_24.add(panel_28);
		panel_28.setLayout(null);
		
		JLabel label_44 = new JLabel("上班工作时间：");
		label_44.setBounds(10, 23, 118, 15);
		panel_28.add(label_44);
		label_44.setFont(new Font("宋体", Font.PLAIN, 14));
		
		voiceTipAttendanceTimeTextField_1 = new JTextField();
		voiceTipAttendanceTimeTextField_1.setFont(new Font("宋体", Font.PLAIN, 14));
		voiceTipAttendanceTimeTextField_1.setBounds(102, 20, 142, 21);
		panel_28.add(voiceTipAttendanceTimeTextField_1);
		voiceTipAttendanceTimeTextField_1.setColumns(10);
		
		JLabel label_45 = new JLabel("上班前提示语：");
		label_45.setBounds(10, 55, 98, 15);
		panel_28.add(label_45);
		label_45.setFont(new Font("宋体", Font.PLAIN, 14));
		
		voiceTipRecognizeSuccessTextField = new JTextField();
		voiceTipRecognizeSuccessTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		voiceTipRecognizeSuccessTextField.setBounds(43, 180, 201, 21);
		panel_28.add(voiceTipRecognizeSuccessTextField);
		voiceTipRecognizeSuccessTextField.setColumns(10);
		
		JLabel label_46 = new JLabel("下班前提示语：");
		label_46.setBounds(10, 105, 98, 15);
		panel_28.add(label_46);
		label_46.setFont(new Font("宋体", Font.PLAIN, 14));
		
		voiceTipBeforeJobTextField = new JTextField();
		voiceTipBeforeJobTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		voiceTipBeforeJobTextField.setBounds(42, 80, 202, 21);
		panel_28.add(voiceTipBeforeJobTextField);
		voiceTipBeforeJobTextField.setColumns(10);
		
		JLabel label_22 = new JLabel("验证成功提示语：");
		label_22.setBounds(10, 155, 118, 15);
		panel_28.add(label_22);
		label_22.setFont(new Font("宋体", Font.PLAIN, 14));
		
		voiceTipAfterJobTextField = new JTextField();
		voiceTipAfterJobTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		voiceTipAfterJobTextField.setBounds(42, 130, 202, 21);
		panel_28.add(voiceTipAfterJobTextField);
		voiceTipAfterJobTextField.setColumns(10);
		
		JLabel label_37 = new JLabel("验证失败提示语：");
		label_37.setBounds(10, 205, 118, 15);
		panel_28.add(label_37);
		label_37.setFont(new Font("宋体", Font.PLAIN, 14));
		
		voiceTipRecognizeErrorTextField = new JTextField();
		voiceTipRecognizeErrorTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		voiceTipRecognizeErrorTextField.setBounds(43, 230, 201, 21);
		panel_28.add(voiceTipRecognizeErrorTextField);
		voiceTipRecognizeErrorTextField.setColumns(10);
		
		JPanel panel_29 = new JPanel();
		panel_29.setBorder(BorderFactory.createTitledBorder("参数设置"));
		panel_29.setBounds(221, 367, 219, 154);
		panel_24.add(panel_29);
		panel_29.setLayout(null);
		
		JLabel label_27 = new JLabel("设备密码：");
		label_27.setFont(new Font("宋体", Font.PLAIN, 14));
		label_27.setBounds(10, 26, 77, 15);
		panel_29.add(label_27);
		
		appParameterPasswordTextField = new JTextField();
		appParameterPasswordTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		appParameterPasswordTextField.setColumns(10);
		appParameterPasswordTextField.setBounds(79, 23, 129, 21);
		panel_29.add(appParameterPasswordTextField);
		
		JLabel label_9 = new JLabel("重启时间：");
		label_9.setBounds(10, 51, 105, 15);
		panel_29.add(label_9);
		label_9.setFont(new Font("宋体", Font.PLAIN, 14));
		
		appParameterAutoRestartTextField = new JTextField();
		appParameterAutoRestartTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		appParameterAutoRestartTextField.setBounds(79, 48, 129, 21);
		panel_29.add(appParameterAutoRestartTextField);
		appParameterAutoRestartTextField.setColumns(10);
		
		JLabel label_7 = new JLabel("支持自动重启：");
		label_7.setBounds(10, 76, 106, 15);
		panel_29.add(label_7);
		label_7.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JLabel lblAdb = new JLabel("ADB 调试模式：");
		lblAdb.setBounds(10, 126, 105, 15);
		panel_29.add(lblAdb);
		lblAdb.setFont(new Font("宋体", Font.PLAIN, 14));
		
		appParameterAutoRestart_Yes__RadioButton = new JRadioButton("是");
		appParameterAutoRestart_Yes__RadioButton.setFont(new Font("宋体", Font.PLAIN, 13));
		appParameterAutoRestart_Yes__RadioButton.setBounds(103, 72, 55, 23);
		panel_29.add(appParameterAutoRestart_Yes__RadioButton);
		
		appParameterAutoRestart_No__RadioButton = new JRadioButton("否");
		appParameterAutoRestart_No__RadioButton.setFont(new Font("宋体", Font.PLAIN, 13));
		appParameterAutoRestart_No__RadioButton.setBounds(158, 72, 55, 23);
		panel_29.add(appParameterAutoRestart_No__RadioButton);
		
		ButtonGroup AutoRestart = new ButtonGroup();
		AutoRestart.add(appParameterAutoRestart_Yes__RadioButton);
		AutoRestart.add(appParameterAutoRestart_No__RadioButton);
		
		appParameterUploadPassImg_Yes_RadioButton = new JRadioButton("是");
		appParameterUploadPassImg_Yes_RadioButton.setFont(new Font("宋体", Font.PLAIN, 13));
		appParameterUploadPassImg_Yes_RadioButton.setBounds(103, 97, 55, 23);
		panel_29.add(appParameterUploadPassImg_Yes_RadioButton);
		
		appParameterUploadPassImg_No_RadioButton = new JRadioButton("否");
		appParameterUploadPassImg_No_RadioButton.setFont(new Font("宋体", Font.PLAIN, 13));
		appParameterUploadPassImg_No_RadioButton.setBounds(158, 97, 55, 23);
		panel_29.add(appParameterUploadPassImg_No_RadioButton);
		
		ButtonGroup UploadPassImg = new ButtonGroup();
		UploadPassImg.add(appParameterUploadPassImg_Yes_RadioButton);
		UploadPassImg.add(appParameterUploadPassImg_No_RadioButton);
		
		appParameterDebugMode_Open_RadioButton = new JRadioButton("开启");
		appParameterDebugMode_Open_RadioButton.setFont(new Font("宋体", Font.PLAIN, 13));
		appParameterDebugMode_Open_RadioButton.setBounds(103, 122, 55, 23);
		panel_29.add(appParameterDebugMode_Open_RadioButton);
		
		appParameterDebugMode_Close_RadioButton = new JRadioButton("关闭");
		appParameterDebugMode_Close_RadioButton.setFont(new Font("宋体", Font.PLAIN, 13));
		appParameterDebugMode_Close_RadioButton.setBounds(158, 122, 55, 23);
		panel_29.add(appParameterDebugMode_Close_RadioButton);
		
		ButtonGroup debugModel = new ButtonGroup();
		debugModel.add(appParameterDebugMode_Open_RadioButton);
		debugModel.add(appParameterDebugMode_Close_RadioButton);
		
		JLabel label_10 = new JLabel("上传抓拍照片：");
		label_10.setBounds(10, 101, 115, 15);
		panel_29.add(label_10);
		label_10.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JPanel panel_30 = new JPanel();
		panel_30.setBorder(BorderFactory.createTitledBorder("消费功能参数"));
		panel_30.setBounds(10, 548, 948, 202);
		panel_24.add(panel_30);
		panel_30.setLayout(null);
		
		JLabel lblutl = new JLabel("支付服务UTL：");
		lblutl.setBounds(10, 26, 99, 15);
		panel_30.add(lblutl);
		lblutl.setFont(new Font("宋体", Font.PLAIN, 14));
		
		consumePayUrlTextField = new JTextField();
		consumePayUrlTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		consumePayUrlTextField.setBounds(97, 23, 335, 21);
		panel_30.add(consumePayUrlTextField);
		consumePayUrlTextField.setColumns(10);
		
		JLabel lblurl = new JLabel("查询服务URL：");
		lblurl.setBounds(10, 56, 91, 15);
		panel_30.add(lblurl);
		lblurl.setFont(new Font("宋体", Font.PLAIN, 14));
		
		consumeQueryUrlTextField = new JTextField();
		consumeQueryUrlTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		consumeQueryUrlTextField.setColumns(10);
		consumeQueryUrlTextField.setBounds(97, 51, 335, 21);
		panel_30.add(consumeQueryUrlTextField);
		
		JLabel label_28 = new JLabel("早餐时段：");
		label_28.setFont(new Font("宋体", Font.PLAIN, 14));
		label_28.setBounds(457, 29, 77, 15);
		panel_30.add(label_28);
		
		consumeBreakfastTimeTextField = new JTextField();
		consumeBreakfastTimeTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		consumeBreakfastTimeTextField.setColumns(10);
		consumeBreakfastTimeTextField.setBounds(529, 26, 105, 21);
		panel_30.add(consumeBreakfastTimeTextField);
		
		JLabel label_29 = new JLabel("午餐时段：");
		label_29.setFont(new Font("宋体", Font.PLAIN, 14));
		label_29.setBounds(457, 59, 77, 15);
		panel_30.add(label_29);
		
		consumeLunchTimeTextField = new JTextField();
		consumeLunchTimeTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		consumeLunchTimeTextField.setColumns(10);
		consumeLunchTimeTextField.setBounds(529, 56, 105, 21);
		panel_30.add(consumeLunchTimeTextField);
		
		JLabel label_31 = new JLabel("晚餐时段：");
		label_31.setFont(new Font("宋体", Font.PLAIN, 14));
		label_31.setBounds(457, 89, 77, 15);
		panel_30.add(label_31);
		
		consumeSupperTimeTextField = new JTextField();
		consumeSupperTimeTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		consumeSupperTimeTextField.setColumns(10);
		consumeSupperTimeTextField.setBounds(529, 86, 105, 21);
		panel_30.add(consumeSupperTimeTextField);
		
		JLabel label_32 = new JLabel("宵夜时段：");
		label_32.setFont(new Font("宋体", Font.PLAIN, 14));
		label_32.setBounds(457, 119, 77, 15);
		panel_30.add(label_32);
		
		consumeDinnerTimeTextField = new JTextField();
		consumeDinnerTimeTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		consumeDinnerTimeTextField.setColumns(10);
		consumeDinnerTimeTextField.setBounds(529, 116, 105, 21);
		panel_30.add(consumeDinnerTimeTextField);
		
		JLabel label_49 = new JLabel("设备编 号 ：");
		label_49.setBounds(228, 86, 115, 15);
		panel_30.add(label_49);
		label_49.setFont(new Font("宋体", Font.PLAIN, 14));
		
		consumeDevNoTextField = new JTextField();
		consumeDevNoTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		consumeDevNoTextField.setBounds(307, 82, 125, 21);
		panel_30.add(consumeDevNoTextField);
		consumeDevNoTextField.setColumns(10);
		
		JLabel label_56 = new JLabel("是否刷子卡 ：");
		label_56.setBounds(10, 86, 92, 15);
		panel_30.add(label_56);
		label_56.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JRadioButton consumeCardSystemTextField_Yes = new JRadioButton("是");
		consumeCardSystemTextField_Yes.setFont(new Font("宋体", Font.PLAIN, 13));
		consumeCardSystemTextField_Yes.setBounds(97, 78, 55, 23);
		panel_30.add(consumeCardSystemTextField_Yes);
		
		JRadioButton consumeCardSystemTextField_No = new JRadioButton("否");
		consumeCardSystemTextField_No.setFont(new Font("宋体", Font.PLAIN, 13));
		consumeCardSystemTextField_No.setBounds(152, 78, 55, 23);
		panel_30.add(consumeCardSystemTextField_No);
		
		JLabel label_57 = new JLabel("在线钱包号：");
		label_57.setBounds(10, 116, 91, 15);
		panel_30.add(label_57);
		label_57.setFont(new Font("宋体", Font.PLAIN, 14));
		
		consumeOnlineWalletID1TextField = new JTextField();
		consumeOnlineWalletID1TextField.setFont(new Font("宋体", Font.PLAIN, 14));
		consumeOnlineWalletID1TextField.setColumns(10);
		consumeOnlineWalletID1TextField.setBounds(97, 113, 125, 21);
		panel_30.add(consumeOnlineWalletID1TextField);
		
		JLabel label_58 = new JLabel("辅助钱包号：");
		label_58.setBounds(228, 116, 92, 15);
		panel_30.add(label_58);
		label_58.setFont(new Font("宋体", Font.PLAIN, 14));
		
		consumeOnlineWalletId2TextField = new JTextField();
		consumeOnlineWalletId2TextField.setFont(new Font("宋体", Font.PLAIN, 14));
		consumeOnlineWalletId2TextField.setBounds(307, 113, 125, 21);
		panel_30.add(consumeOnlineWalletId2TextField);
		consumeOnlineWalletId2TextField.setColumns(10);
		
		JLabel label_50 = new JLabel("消费成功提示显示时长：");
		label_50.setBounds(10, 175, 166, 15);
		panel_30.add(label_50);
		label_50.setFont(new Font("宋体", Font.PLAIN, 14));
		
		consumeDisplayTitleTextField = new JTextField();
		consumeDisplayTitleTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		consumeDisplayTitleTextField.setBounds(97, 141, 335, 21);
		panel_30.add(consumeDisplayTitleTextField);
		consumeDisplayTitleTextField.setColumns(10);
		
		JLabel label_51 = new JLabel("副屏提示语：");
		label_51.setBounds(10, 146, 115, 15);
		panel_30.add(label_51);
		label_51.setFont(new Font("宋体", Font.PLAIN, 14));
		
		consumeSuccessInfoDurationTextField = new JTextField();
		consumeSuccessInfoDurationTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		consumeSuccessInfoDurationTextField.setBounds(163, 172, 55, 21);
		panel_30.add(consumeSuccessInfoDurationTextField);
		consumeSuccessInfoDurationTextField.setColumns(10);
		
		JLabel label_38 = new JLabel("离线透支最大金额：");
		label_38.setBounds(655, 56, 134, 15);
		panel_30.add(label_38);
		label_38.setFont(new Font("宋体", Font.PLAIN, 14));
		
		consumeOfflineMoneyLimitTextField = new JTextField();
		consumeOfflineMoneyLimitTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		consumeOfflineMoneyLimitTextField.setBounds(778, 53, 91, 21);
		panel_30.add(consumeOfflineMoneyLimitTextField);
		consumeOfflineMoneyLimitTextField.setColumns(10);
		
		JLabel label_43 = new JLabel("离线透支消费次数：");
		label_43.setBounds(655, 86, 142, 15);
		panel_30.add(label_43);
		label_43.setFont(new Font("宋体", Font.PLAIN, 14));
		
		consumeOfflineNumberLimitTextField = new JTextField();
		consumeOfflineNumberLimitTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		consumeOfflineNumberLimitTextField.setBounds(778, 83, 91, 21);
		panel_30.add(consumeOfflineNumberLimitTextField);
		consumeOfflineNumberLimitTextField.setColumns(10);
		
		JLabel label_47 = new JLabel("允许离线最大时长：");
		label_47.setBounds(655, 116, 142, 15);
		panel_30.add(label_47);
		label_47.setFont(new Font("宋体", Font.PLAIN, 14));
		
		consumeOfflineTimeLimitTextField = new JTextField();
		consumeOfflineTimeLimitTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		consumeOfflineTimeLimitTextField.setBounds(778, 111, 91, 21);
		panel_30.add(consumeOfflineTimeLimitTextField);
		consumeOfflineTimeLimitTextField.setColumns(10);
		
		JLabel label_48 = new JLabel("离线模式：");
		label_48.setBounds(655, 26, 77, 15);
		panel_30.add(label_48);
		label_48.setFont(new Font("宋体", Font.PLAIN, 14));
		
		consumeOfflineMode_No_RadioButton = new JRadioButton("不支持");
		consumeOfflineMode_No_RadioButton.setFont(new Font("宋体", Font.PLAIN, 13));
		consumeOfflineMode_No_RadioButton.setBounds(722, 21, 70, 23);
		panel_30.add(consumeOfflineMode_No_RadioButton);
		
		consumeOfflineMode_Yes_RadioButton = new JRadioButton("支持");
		consumeOfflineMode_Yes_RadioButton.setFont(new Font("宋体", Font.PLAIN, 13));
		consumeOfflineMode_Yes_RadioButton.setBounds(792, 21, 55, 23);
		panel_30.add(consumeOfflineMode_Yes_RadioButton);
		
		ButtonGroup offlineModel = new ButtonGroup();
		offlineModel.add(consumeOfflineMode_No_RadioButton);
		offlineModel.add(consumeOfflineMode_Yes_RadioButton);
		
		JLabel label_52 = new JLabel("秒");
		label_52.setFont(new Font("宋体", Font.PLAIN, 14));
		label_52.setBounds(224, 175, 22, 15);
		panel_30.add(label_52);
		
		JPanel panel_31 = new JPanel();
		panel_31.setLayout(null);
		panel_31.setBorder(BorderFactory.createTitledBorder("远程开门控制"));
		panel_31.setBounds(10, 365, 201, 173);
		panel_24.add(panel_31);
		
		JLabel lblip_1 = new JLabel("控制器 IP：");
		lblip_1.setBounds(10, 26, 77, 15);
		panel_31.add(lblip_1);
		lblip_1.setFont(new Font("宋体", Font.PLAIN, 14));
		
		accessDevIpTextField = new JTextField();
		accessDevIpTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		accessDevIpTextField.setBounds(86, 23, 105, 21);
		panel_31.add(accessDevIpTextField);
		accessDevIpTextField.setColumns(10);
		
		JLabel lblmac = new JLabel("控制器MAC：");
		lblmac.setBounds(10, 56, 77, 15);
		panel_31.add(lblmac);
		lblmac.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JLabel lblUdp = new JLabel("UDP  端口：");
		lblUdp.setBounds(10, 86, 77, 15);
		panel_31.add(lblUdp);
		lblUdp.setFont(new Font("宋体", Font.PLAIN, 14));
		
		accessDevMacTextField = new JTextField();
		accessDevMacTextField.setText("FFFFFF");
		accessDevMacTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		accessDevMacTextField.setBounds(86, 53, 105, 21);
		panel_31.add(accessDevMacTextField);
		accessDevMacTextField.setColumns(10);
		
		accessUdpServerPortTextField = new JTextField();
		accessUdpServerPortTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		accessUdpServerPortTextField.setBounds(86, 83, 105, 21);
		panel_31.add(accessUdpServerPortTextField);
		accessUdpServerPortTextField.setColumns(10);
		
		JLabel label_36 = new JLabel("门     号：");
		label_36.setBounds(10, 116, 77, 15);
		panel_31.add(label_36);
		label_36.setFont(new Font("宋体", Font.PLAIN, 14));
		
		accessDevDoorNoComboBox = new JComboBox<String>();
		accessDevDoorNoComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"  0号门  ", "  1号门  ", "  2号门  ", "  3号门  "}));
		accessDevDoorNoComboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		accessDevDoorNoComboBox.setBounds(86, 111, 105, 26);
		panel_31.add(accessDevDoorNoComboBox);
		
		JLabel label_18 = new JLabel("开门密码：");
		label_18.setBounds(10, 146, 77, 15);
		panel_31.add(label_18);
		label_18.setFont(new Font("宋体", Font.PLAIN, 14));
		
		accessDevDoorPasswordTextField = new JTextField();
		accessDevDoorPasswordTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		accessDevDoorPasswordTextField.setBounds(86, 141, 105, 21);
		panel_31.add(accessDevDoorPasswordTextField);
		accessDevDoorPasswordTextField.setColumns(10);
		
		JPanel panel_32 = new JPanel();
		panel_32.setBorder(BorderFactory.createTitledBorder("UI设置"));
		panel_32.setBounds(671, 271, 257, 285);
		panel_24.add(panel_32);
		panel_32.setLayout(null);
		
		JLabel lblUi = new JLabel("UI  类型：");
		lblUi.setBounds(10, 26, 77, 15);
		panel_32.add(lblUi);
		lblUi.setFont(new Font("宋体", Font.PLAIN, 14));
		
		uiTypeComboBox = new JComboBox<String>();
		uiTypeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"----不支持----", " 无识别区域", "指定识别区域"}));
		uiTypeComboBox.setFont(new Font("Dialog", Font.PLAIN, 14));
		uiTypeComboBox.setBounds(81, 20, 146, 26);
		panel_32.add(uiTypeComboBox);
		
		JLabel label_33 = new JLabel("屏保开关：");
		label_33.setBounds(10, 59, 77, 15);
		panel_32.add(label_33);
		label_33.setFont(new Font("宋体", Font.PLAIN, 14));
		
		uiScreanSave_No_RadioButton = new JRadioButton("不支持");
		uiScreanSave_No_RadioButton.setBounds(81, 55, 65, 23);
		panel_32.add(uiScreanSave_No_RadioButton);
		uiScreanSave_No_RadioButton.setFont(new Font("宋体", Font.PLAIN, 13));
		
		uiScreanSave_Open_RadioButton = new JRadioButton("开启");
		uiScreanSave_Open_RadioButton.setBounds(148, 55, 55, 23);
		panel_32.add(uiScreanSave_Open_RadioButton);
		uiScreanSave_Open_RadioButton.setFont(new Font("宋体", Font.PLAIN, 13));
		
		uiScreanSave_Close_RadioButton = new JRadioButton("关闭");
		uiScreanSave_Close_RadioButton.setBounds(204, 55, 55, 23);
		panel_32.add(uiScreanSave_Close_RadioButton);
		uiScreanSave_Close_RadioButton.setFont(new Font("宋体", Font.PLAIN, 13));
		
		ButtonGroup screanSave = new ButtonGroup();
		screanSave.add(uiScreanSave_No_RadioButton);
		screanSave.add(uiScreanSave_Open_RadioButton);
		screanSave.add(uiScreanSave_Close_RadioButton);
		
		JPanel panel_33 = new JPanel();
		panel_33.setBounds(10, 80, 164, 149);
		panel_32.add(panel_33);
		
		uiChiosePictureButton = new JLabel("+");
		uiChiosePictureButton.setHorizontalAlignment(SwingConstants.CENTER);
		uiChiosePictureButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JFileChooser jfc=new JFileChooser();  
		        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
		        jfc.setCurrentDirectory(new File("."));
		        jfc.showDialog(new JLabel(), "选择"); 
//		        MyFileFilter myFileFilter = new MyFileFilter("JPEG", "JPEG图片文件");
//		        jfc.setFileFilter(myFileFilter);
		        uiFile=jfc.getSelectedFile();
		        if ( null != uiFile) {
			        Image image;
					try {
						image = ImageIO.read(uiFile);
						 if ( null != image) {
							 uiChiosePictureButton.setText("");
							 uiChiosePictureButton.setIcon(MyPhoto.changeSize(140, 150, uiFile.getPath()));
						 }else {
							 uiFile = null;
						 }
					} catch (IOException e1) {
	//					e1.printStackTrace();
					}
		        }
			}
		});
		panel_33.setLayout(new BorderLayout(0, 0));
		
		uiChiosePictureButton.setFont(new Font("宋体", Font.PLAIN, 99));
		panel_33.add(uiChiosePictureButton);
		
		JLabel label_61 = new JLabel("屏保内容显示：");
		label_61.setFont(new Font("宋体", Font.PLAIN, 14));
		label_61.setBounds(10, 235, 99, 15);
		panel_32.add(label_61);
		
		uiTextField = new JTextField();
		uiTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		uiTextField.setColumns(10);
		uiTextField.setBounds(10, 255, 237, 21);
		panel_32.add(uiTextField);
		
		JLabel lblstrmsgstrmsg = new JLabel("<html>点击+号选择照片</html>");
		lblstrmsgstrmsg.setFont(new Font("宋体", Font.PLAIN, 13));
		lblstrmsgstrmsg.setBounds(184, 96, 63, 60);
		panel_32.add(lblstrmsgstrmsg);
		
		JButton btnNewButton_4 = new JButton("<html>设置LOGO</html>");
		btnNewButton_4.addActionListener((e) -> {
			
			if (null != uiFile) {
				SetLogoGson setLogoGson = new SetLogoGson();
				String base64 = setLogoGson.setPhotoSize(uiFile.getPath());
				String macAddr = faceDevchoiseComboBox.getSelectedItem().toString();
				if (null != base64) {
					
					setLogoGson.setImage(base64);
					
					RequestGson<SetLogoGson> requestGson = new RequestGson<SetLogoGson>();
					requestGson.setDeviceUniqueCode(macAddr);
					requestGson.setData(setLogoGson);
					new Thread( () -> {
						new SetLogo(new Gson().toJson(requestGson, new TypeToken<RequestGson<SetLogoGson>>() {}.getType()), macAddr);
					}).start();
				}
			}else {
				JOptionPane.showMessageDialog(null, "未选择LOGO图标", "设置LOGO", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnNewButton_4.setToolTipText("图片后缀为jpg，图片宽高小于等于300*150像素。");
		btnNewButton_4.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton_4.setBounds(184, 166, 63, 63);
		panel_32.add(btnNewButton_4);
		
		JPanel panel_34 = new JPanel();
		panel_34.setBorder(BorderFactory.createTitledBorder("基本参数"));
		panel_34.setBounds(10, 7, 406, 208);
		panel_24.add(panel_34);
		panel_34.setLayout(null);
		
		JLabel label_3 = new JLabel("设备 MAC：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		label_3.setBounds(10, 26, 77, 15);
		panel_34.add(label_3);
		
		basicParameterDevMacTextField = new JTextField();
		basicParameterDevMacTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		basicParameterDevMacTextField.setColumns(10);
		basicParameterDevMacTextField.setBounds(82, 23, 110, 21);
		panel_34.add(basicParameterDevMacTextField);
		
		JLabel label_4 = new JLabel("设备  IP：");
		label_4.setFont(new Font("宋体", Font.PLAIN, 14));
		label_4.setBounds(10, 86, 77, 15);
		panel_34.add(label_4);
		
		basicParameterDevNameTextField = new JTextField();
		basicParameterDevNameTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		basicParameterDevNameTextField.setColumns(10);
		basicParameterDevNameTextField.setBounds(82, 53, 110, 21);
		panel_34.add(basicParameterDevNameTextField);
		
		JLabel label_6 = new JLabel("设备名称：");
		label_6.setFont(new Font("宋体", Font.PLAIN, 14));
		label_6.setBounds(10, 56, 77, 15);
		panel_34.add(label_6);
		
		basicParameterDevIpTextField = new JTextField();
		basicParameterDevIpTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		basicParameterDevIpTextField.setColumns(10);
		basicParameterDevIpTextField.setBounds(82, 83, 110, 21);
		panel_34.add(basicParameterDevIpTextField);
		
		JLabel label_8 = new JLabel("设备类型：");
		label_8.setFont(new Font("宋体", Font.PLAIN, 14));
		label_8.setBounds(197, 26, 77, 15);
		panel_34.add(label_8);
		
		JLabel label_11 = new JLabel("服务器IP：");
		label_11.setFont(new Font("宋体", Font.PLAIN, 14));
		label_11.setBounds(10, 116, 77, 15);
		panel_34.add(label_11);
		
		basicParameterDevServerIpTextField = new JTextField();
		basicParameterDevServerIpTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		basicParameterDevServerIpTextField.setColumns(10);
		basicParameterDevServerIpTextField.setBounds(82, 113, 110, 21);
		panel_34.add(basicParameterDevServerIpTextField);
		
		JLabel label_17 = new JLabel("服务端口：");
		label_17.setFont(new Font("宋体", Font.PLAIN, 14));
		label_17.setBounds(10, 146, 92, 15);
		panel_34.add(label_17);
		
		basicParameterDevServerPortTextField = new JTextField();
		basicParameterDevServerPortTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		basicParameterDevServerPortTextField.setColumns(10);
		basicParameterDevServerPortTextField.setBounds(82, 143, 110, 21);
		panel_34.add(basicParameterDevServerPortTextField);
		
		JLabel label_21 = new JLabel("系统单位编号：");
		label_21.setFont(new Font("宋体", Font.PLAIN, 14));
		label_21.setBounds(197, 56, 105, 15);
		panel_34.add(label_21);
		
		basicParameterSystemNoTextField = new JTextField();
		basicParameterSystemNoTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		basicParameterSystemNoTextField.setColumns(10);
		basicParameterSystemNoTextField.setBounds(288, 53, 105, 21);
		panel_34.add(basicParameterSystemNoTextField);
		
		basicParameterDevTypeComboBox = new JComboBox<String>();
		basicParameterDevTypeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"1:仅身份识别", "2:普通门禁", "3:高级门禁", "4:消费", "99:其他"}));
		basicParameterDevTypeComboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		basicParameterDevTypeComboBox.setBounds(263, 22, 131, 26);
		panel_34.add(basicParameterDevTypeComboBox);
		
		JLabel label_34 = new JLabel("人脸算法类别：");
		label_34.setFont(new Font("宋体", Font.PLAIN, 14));
		label_34.setBounds(197, 86, 105, 15);
		panel_34.add(label_34);
		
		basicParameterFeatureTypeTextField = new JTextField();
		basicParameterFeatureTypeTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		basicParameterFeatureTypeTextField.setEditable(false);
		basicParameterFeatureTypeTextField.setColumns(10);
		basicParameterFeatureTypeTextField.setBounds(288, 83, 105, 21);
		panel_34.add(basicParameterFeatureTypeTextField);
		
		JLabel label_35 = new JLabel("算法 版 本号：");
		label_35.setFont(new Font("宋体", Font.PLAIN, 14));
		label_35.setBounds(197, 116, 105, 15);
		panel_34.add(label_35);
		
		basicParameterFeatureVersionTextField = new JTextField();
		basicParameterFeatureVersionTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		basicParameterFeatureVersionTextField.setEditable(false);
		basicParameterFeatureVersionTextField.setColumns(10);
		basicParameterFeatureVersionTextField.setBounds(288, 113, 105, 21);
		panel_34.add(basicParameterFeatureVersionTextField);
		
		JLabel lblSdk = new JLabel("算法  SDK 值：");
		lblSdk.setFont(new Font("宋体", Font.PLAIN, 14));
		lblSdk.setBounds(197, 146, 105, 15);
		panel_34.add(lblSdk);
		
		basicParameterFeatureSdkTextField = new JTextField();
		basicParameterFeatureSdkTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		basicParameterFeatureSdkTextField.setEditable(false);
		basicParameterFeatureSdkTextField.setColumns(10);
		basicParameterFeatureSdkTextField.setBounds(288, 143, 105, 21);
		panel_34.add(basicParameterFeatureSdkTextField);
		
		JLabel label_53 = new JLabel("程序 版 本号：");
		label_53.setFont(new Font("宋体", Font.PLAIN, 14));
		label_53.setBounds(197, 176, 105, 15);
		panel_34.add(label_53);
		
		basicParameterVersionTextField = new JTextField();
		basicParameterVersionTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		basicParameterVersionTextField.setEditable(false);
		basicParameterVersionTextField.setColumns(10);
		basicParameterVersionTextField.setBounds(288, 173, 105, 21);
		panel_34.add(basicParameterVersionTextField);
		
		JLabel label_54 = new JLabel("心跳时间：");
		label_54.setFont(new Font("宋体", Font.PLAIN, 14));
		label_54.setBounds(10, 176, 77, 15);
		panel_34.add(label_54);
		
		basicParameterHeartBeatTextField = new JTextField();
		basicParameterHeartBeatTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		basicParameterHeartBeatTextField.setColumns(10);
		basicParameterHeartBeatTextField.setBounds(82, 173, 77, 21);
		panel_34.add(basicParameterHeartBeatTextField);
		
		JLabel label_55 = new JLabel("毫秒");
		label_55.setFont(new Font("宋体", Font.PLAIN, 14));
		label_55.setBounds(162, 176, 34, 15);
		panel_34.add(label_55);
		
		panel_35 = new JPanel();
		panel_22.add(panel_35, "downLoad");
		panel_35.setLayout(null);
		
		JPanel panel_36 = new JPanel();
		panel_36.setBounds(10, 10, 741, 56);
		panel_36.setBorder(BorderFactory.createTitledBorder("程序升级URL"));
		panel_35.add(panel_36);
		panel_36.setLayout(null);
		
		JLabel lblApkurl = new JLabel("APK_URL:");
		lblApkurl.setFont(new Font("宋体", Font.PLAIN, 14));
		lblApkurl.setBounds(10, 26, 78, 15);
		panel_36.add(lblApkurl);
		
		copyurlTextField_1 = new JTextField();
		copyurlTextField_1.setEditable(false);
		copyurlTextField_1.setFont(new Font("宋体", Font.PLAIN, 14));
		copyurlTextField_1.setBounds(69, 22, 554, 21);
		panel_36.add(copyurlTextField_1);
		copyurlTextField_1.setColumns(10);
		
		JButton button_13 = new JButton("复制");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.datatransfer.Clipboard clipboard=getToolkit().getSystemClipboard();
				StringSelection text1=new StringSelection(copyurlTextField_1.getText().replaceAll(" ", ""));
				clipboard.setContents(text1, null);
			}
		});
		button_13.setFont(new Font("宋体", Font.PLAIN, 14));
		button_13.setBounds(633, 22, 78, 23);
		panel_36.add(button_13);
		
		JPanel panel_41 = new JPanel();
		panel_41.setBorder(BorderFactory.createTitledBorder("设置远程IP和端口"));
		panel_41.setBounds(14, 73, 403, 176);
		panel_35.add(panel_41);
		panel_41.setLayout(null);
		
		JLabel lblIp = new JLabel("设备  IP：");
		lblIp.setFont(new Font("宋体", Font.PLAIN, 14));
		lblIp.setBounds(10, 60, 77, 15);
		panel_41.add(lblIp);
		
		setDevServerMacTextField = new JTextField();
		setDevServerMacTextField.setHorizontalAlignment(SwingConstants.CENTER);
		setDevServerMacTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		setDevServerMacTextField.setColumns(10);
		setDevServerMacTextField.setBounds(82, 26, 110, 21);
		panel_41.add(setDevServerMacTextField);
		
		setDevServerDevIPTextField = new JTextField();
		setDevServerDevIPTextField.setHorizontalAlignment(SwingConstants.CENTER);
		setDevServerDevIPTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		setDevServerDevIPTextField.setColumns(10);
		setDevServerDevIPTextField.setBounds(82, 56, 110, 21);
		panel_41.add(setDevServerDevIPTextField);
		
		JLabel lblMac = new JLabel("设备 MAC：");
		lblMac.setFont(new Font("宋体", Font.PLAIN, 14));
		lblMac.setBounds(10, 30, 77, 15);
		panel_41.add(lblMac);
		
		setDevServerDevPortTextField = new JTextField();
		setDevServerDevPortTextField.setHorizontalAlignment(SwingConstants.CENTER);
		setDevServerDevPortTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		setDevServerDevPortTextField.setColumns(10);
		setDevServerDevPortTextField.setBounds(82, 86, 110, 21);
		panel_41.add(setDevServerDevPortTextField);
		
		JLabel label_80 = new JLabel("设备端口：");
		label_80.setFont(new Font("宋体", Font.PLAIN, 14));
		label_80.setBounds(10, 90, 77, 15);
		panel_41.add(label_80);
		
		JLabel label_81 = new JLabel("服务器IP：");
		label_81.setFont(new Font("宋体", Font.PLAIN, 14));
		label_81.setBounds(10, 119, 77, 15);
		panel_41.add(label_81);
		
		setDevServerServerIPTextField = new JTextField();
		setDevServerServerIPTextField.setHorizontalAlignment(SwingConstants.CENTER);
		setDevServerServerIPTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		setDevServerServerIPTextField.setColumns(10);
		setDevServerServerIPTextField.setBounds(82, 116, 110, 21);
		panel_41.add(setDevServerServerIPTextField);
		
		JLabel label_82 = new JLabel("服务端口：");
		label_82.setFont(new Font("宋体", Font.PLAIN, 14));
		label_82.setBounds(10, 149, 92, 15);
		panel_41.add(label_82);
		
		setDevServerServerPortTextField = new JTextField();
		setDevServerServerPortTextField.setHorizontalAlignment(SwingConstants.CENTER);
		setDevServerServerPortTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		setDevServerServerPortTextField.setColumns(10);
		setDevServerServerPortTextField.setBounds(82, 146, 110, 21);
		panel_41.add(setDevServerServerPortTextField);
		
		JLabel label_77 = new JLabel("设备原始端口：");
		label_77.setFont(new Font("宋体", Font.PLAIN, 14));
		label_77.setBounds(202, 30, 110, 15);
		panel_41.add(label_77);
		
		devPortTextField = new JTextField();
		devPortTextField.setHorizontalAlignment(SwingConstants.CENTER);
		devPortTextField.setText("8090");
		devPortTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		devPortTextField.setColumns(10);
		devPortTextField.setBounds(298, 26, 95, 21);
		panel_41.add(devPortTextField);
		
		JLabel lblNewLabel = new JLabel("<html>1、该功能必须知道设备原始端口才能设置，否则设置失败<br>2、如果设备支持修改端口号，修改端口功能才能生效<br>3、本功能修改IP后，设备上线后必须读取设置参数、并重新设置参数</html>");
		lblNewLabel.setBounds(202, 48, 191, 116);
		panel_41.add(lblNewLabel);
		
		JPanel panel_84 = new JPanel();
		panel_84.setBorder(BorderFactory.createTitledBorder("D20程序更新测试"));
		panel_84.setBounds(10, 259, 446, 201);
		panel_35.add(panel_84);
		panel_84.setLayout(null);
		
		JLabel lblip_3 = new JLabel("设备IP地址：");
		lblip_3.setFont(new Font("宋体", Font.PLAIN, 14));
		lblip_3.setBounds(10, 26, 106, 15);
		panel_84.add(lblip_3);
		
		d20IpTextField = new JTextField();
		d20IpTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		d20IpTextField.setBounds(103, 23, 217, 21);
		panel_84.add(d20IpTextField);
		d20IpTextField.setColumns(10);
		
		JLabel d20LogLabel = new JLabel("");
		d20LogLabel.setVerticalAlignment(SwingConstants.TOP);
		d20LogLabel.setFont(new Font("宋体", Font.PLAIN, 13));
		d20LogLabel.setBounds(10, 84, 426, 112);
		panel_84.add(d20LogLabel);
		
		JButton d20UpdateButton = new JButton("升级程序");
		d20UpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pattern pattern = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
				String ip = d20IpTextField.getText().trim().replaceAll(" ", "");	
				Matcher matcher = pattern.matcher(ip); //验证IP地址有效性
				d20LogLabel.setText("");
//				String data = "1";
				if(matcher.matches()){
					new Thread(()->{
						d20LogLabel.setText("");
						
						
						JFileChooser jfc=new JFileChooser();  
						jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
						jfc.setCurrentDirectory(new File("./APK"));
						jfc.showDialog(new JLabel(), "选择");  
						File file=jfc.getSelectedFile();  
						//System.out.println(file.getName());
						String url = "";
						if( null != file) {
							DownLoadApkTest.setFileName(file.getName());
							DownLoadApkTest.setFilePath(file.getParent());
							DownLoadApkTest.setDownLocalFile(".APK");
							url = "http://" + Http.getServerIP() + ":" + Http.getPort() + DownLoadApkTest.URL_NAME +"/"+ file.getName();
							copyurlTextField_1.setText(url);
						}
						
						new CommandSocket().SendDatagram(ip, url);
						String logs = "<html>["+SystemTimes.getSysTime()+"] 向ip为（"+ip+"）发送数据："+ url +"<br>";
						if(CommandSocket.isNotReceiveData(3000)) {
							logs += "["+SystemTimes.getSysTime()+"] ip为（"+ip+"）返回数据："+CommandSocket.getReceiveData()+"<br>";
						}else {
							logs += "["+SystemTimes.getSysTime()+"] ip为（"+ip+"）3s内未返回数据<br></html>";
						}
						d20LogLabel.setText(logs);
					}).start(); 
				}else {
					d20LogLabel.setText("错误的IP请求参数!");
				}
			}
		});
		d20UpdateButton.setFont(new Font("宋体", Font.PLAIN, 14));
		d20UpdateButton.setBounds(10, 51, 93, 23);
		panel_84.add(d20UpdateButton);
		
		
		
		JPanel panel_350 = new JPanel();
		panel_22.add(panel_350, "350");
		panel_350.setLayout(null);
		
		JPanel panel_75 = new JPanel();
		panel_75.setBorder(BorderFactory.createTitledBorder("设备基础参数"));
		panel_75.setBounds(10, 10, 401, 172);
		panel_350.add(panel_75);
		panel_75.setLayout(null);
		
		JLabel label_110 = new JLabel("设备 MAC：");
		label_110.setFont(new Font("宋体", Font.PLAIN, 14));
		label_110.setBounds(10, 23, 77, 15);
		panel_75.add(label_110);
		
		devMac350TextField = new JTextField();
		devMac350TextField.setFont(new Font("宋体", Font.PLAIN, 14));
		devMac350TextField.setColumns(10);
		devMac350TextField.setBounds(82, 20, 110, 21);
		panel_75.add(devMac350TextField);
		
		JLabel label_111 = new JLabel("设备  IP：");
		label_111.setFont(new Font("宋体", Font.PLAIN, 14));
		label_111.setBounds(10, 51, 77, 15);
		panel_75.add(label_111);
		
		devIp350TextField = new JTextField();
		devIp350TextField.setFont(new Font("宋体", Font.PLAIN, 14));
		devIp350TextField.setColumns(10);
		devIp350TextField.setBounds(82, 48, 110, 21);
		panel_75.add(devIp350TextField);
		
		JLabel label_113 = new JLabel("内部软件版本：");
		label_113.setFont(new Font("宋体", Font.PLAIN, 14));
		label_113.setBounds(197, 23, 121, 15);
		panel_75.add(label_113);
		
		JLabel label_114 = new JLabel("服务器IP：");
		label_114.setFont(new Font("宋体", Font.PLAIN, 14));
		label_114.setBounds(10, 81, 77, 15);
		panel_75.add(label_114);
		
		dev350SeverIpTextField = new JTextField();
		dev350SeverIpTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350SeverIpTextField.setColumns(10);
		dev350SeverIpTextField.setBounds(82, 78, 110, 21);
		panel_75.add(dev350SeverIpTextField);
		
		JLabel label_115 = new JLabel("服务端口：");
		label_115.setFont(new Font("宋体", Font.PLAIN, 14));
		label_115.setBounds(10, 111, 92, 15);
		panel_75.add(label_115);
		
		dev350ServerPortTextField = new JTextField();
		dev350ServerPortTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350ServerPortTextField.setColumns(10);
		dev350ServerPortTextField.setBounds(82, 108, 110, 21);
		panel_75.add(dev350ServerPortTextField);
		
		JLabel label_116 = new JLabel("软件 版 本号：");
		label_116.setFont(new Font("宋体", Font.PLAIN, 14));
		label_116.setBounds(197, 53, 105, 15);
		panel_75.add(label_116);
		
		dev350AppVersionNameTextField = new JTextField();
		dev350AppVersionNameTextField.setEnabled(false);
		dev350AppVersionNameTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350AppVersionNameTextField.setColumns(10);
		dev350AppVersionNameTextField.setBounds(288, 50, 105, 21);
		panel_75.add(dev350AppVersionNameTextField);
		
		JLabel label_117 = new JLabel("软件编译时间：");
		label_117.setFont(new Font("宋体", Font.PLAIN, 14));
		label_117.setBounds(197, 83, 105, 15);
		panel_75.add(label_117);
		
		dev350BuildTimeTextField = new JTextField();
		dev350BuildTimeTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350BuildTimeTextField.setEditable(false);
		dev350BuildTimeTextField.setColumns(10);
		dev350BuildTimeTextField.setBounds(288, 80, 105, 21);
		panel_75.add(dev350BuildTimeTextField);
		
		JLabel label_118 = new JLabel("相机固件版本：");
		label_118.setFont(new Font("宋体", Font.PLAIN, 14));
		label_118.setBounds(197, 113, 105, 15);
		panel_75.add(label_118);
		
		dev350CameraVersionTextField = new JTextField();
		dev350CameraVersionTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350CameraVersionTextField.setEditable(false);
		dev350CameraVersionTextField.setColumns(10);
		dev350CameraVersionTextField.setBounds(288, 110, 105, 21);
		panel_75.add(dev350CameraVersionTextField);
		
		JLabel label_119 = new JLabel("系统固件版本：");
		label_119.setFont(new Font("宋体", Font.PLAIN, 14));
		label_119.setBounds(197, 143, 105, 15);
		panel_75.add(label_119);
		
		dev350FimwareVersionTextField = new JTextField();
		dev350FimwareVersionTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350FimwareVersionTextField.setEditable(false);
		dev350FimwareVersionTextField.setColumns(10);
		dev350FimwareVersionTextField.setBounds(288, 140, 105, 21);
		panel_75.add(dev350FimwareVersionTextField);
		
		JLabel label_120 = new JLabel("");
		label_120.setFont(new Font("宋体", Font.PLAIN, 14));
		label_120.setBounds(197, 173, 105, 15);
		panel_75.add(label_120);
		
		JLabel label_121 = new JLabel("心跳时间：");
		label_121.setFont(new Font("宋体", Font.PLAIN, 14));
		label_121.setBounds(10, 141, 77, 15);
		panel_75.add(label_121);
		
		dev350HeatBeatTextField = new JTextField();
		dev350HeatBeatTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350HeatBeatTextField.setColumns(10);
		dev350HeatBeatTextField.setBounds(82, 138, 77, 21);
		panel_75.add(dev350HeatBeatTextField);
		
		JLabel label_122 = new JLabel("毫秒");
		label_122.setFont(new Font("宋体", Font.PLAIN, 14));
		label_122.setBounds(162, 141, 34, 15);
		panel_75.add(label_122);
		
		dev350AppVersionTextField = new JTextField();
		dev350AppVersionTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350AppVersionTextField.setEditable(false);
		dev350AppVersionTextField.setColumns(10);
		dev350AppVersionTextField.setBounds(288, 20, 105, 21);
		panel_75.add(dev350AppVersionTextField);
		
		JPanel panel_76 = new JPanel();
		panel_76.setBorder(BorderFactory.createTitledBorder("识别参数"));
		panel_76.setBounds(10, 221, 401, 172);
		panel_350.add(panel_76);
		panel_76.setLayout(null);
		
		JLabel label_123 = new JLabel("活体类型：");
		label_123.setFont(new Font("宋体", Font.PLAIN, 14));
		label_123.setBounds(10, 26, 77, 15);
		panel_76.add(label_123);
		
		JLabel label_124 = new JLabel("活体阈值：");
		label_124.setFont(new Font("宋体", Font.PLAIN, 14));
		label_124.setBounds(10, 56, 77, 15);
		panel_76.add(label_124);
		
		dev350AlivingThresholdTextField = new JTextField();
		dev350AlivingThresholdTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350AlivingThresholdTextField.setColumns(10);
		dev350AlivingThresholdTextField.setBounds(82, 52, 105, 21);
		panel_76.add(dev350AlivingThresholdTextField);
		
		JLabel label_125 = new JLabel("识 别间 隔：");
		label_125.setFont(new Font("宋体", Font.PLAIN, 14));
		label_125.setBounds(198, 116, 110, 15);
		panel_76.add(label_125);
		
		dev350MaxPixelTextField = new JTextField();
		dev350MaxPixelTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350MaxPixelTextField.setColumns(10);
		dev350MaxPixelTextField.setBounds(82, 141, 105, 21);
		panel_76.add(dev350MaxPixelTextField);
		
		JLabel label_126 = new JLabel("相识度阈值：");
		label_126.setFont(new Font("宋体", Font.PLAIN, 14));
		label_126.setBounds(198, 26, 96, 15);
		panel_76.add(label_126);
		
		dev350DiscernThresholdTextField = new JTextField();
		dev350DiscernThresholdTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350DiscernThresholdTextField.setColumns(10);
		dev350DiscernThresholdTextField.setBounds(279, 23, 117, 21);
		panel_76.add(dev350DiscernThresholdTextField);
		
		JLabel label_127 = new JLabel("身份证阈值：");
		label_127.setFont(new Font("宋体", Font.PLAIN, 14));
		label_127.setBounds(197, 56, 97, 15);
		panel_76.add(label_127);
		
		dev350IdCardThresholdTextField = new JTextField();
		dev350IdCardThresholdTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350IdCardThresholdTextField.setColumns(10);
		dev350IdCardThresholdTextField.setBounds(279, 53, 117, 21);
		panel_76.add(dev350IdCardThresholdTextField);
		
		JLabel label_128 = new JLabel("最小像素：");
		label_128.setFont(new Font("宋体", Font.PLAIN, 14));
		label_128.setBounds(10, 116, 97, 15);
		panel_76.add(label_128);
		
		dev350MinPixelTextField = new JTextField();
		dev350MinPixelTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350MinPixelTextField.setColumns(10);
		dev350MinPixelTextField.setBounds(82, 112, 105, 21);
		panel_76.add(dev350MinPixelTextField);
		
		JLabel label_129 = new JLabel("最大像素：");
		label_129.setFont(new Font("宋体", Font.PLAIN, 14));
		label_129.setBounds(10, 146, 105, 15);
		panel_76.add(label_129);
		
		dev350DiscernIntervalTextField = new JTextField();
		dev350DiscernIntervalTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350DiscernIntervalTextField.setColumns(10);
		dev350DiscernIntervalTextField.setBounds(279, 113, 117, 21);
		panel_76.add(dev350DiscernIntervalTextField);
		
		JLabel label_130 = new JLabel("检 测阈 值：");
		label_130.setFont(new Font("宋体", Font.PLAIN, 14));
		label_130.setBounds(197, 86, 97, 15);
		panel_76.add(label_130);
		
		dev350detectThresholdTextField = new JTextField();
		dev350detectThresholdTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350detectThresholdTextField.setColumns(10);
		dev350detectThresholdTextField.setBounds(279, 83, 117, 21);
		panel_76.add(dev350detectThresholdTextField);
		
		dev350IsAlivecomboBox = new JComboBox<String>();
		dev350IsAlivecomboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"关闭", "慢", "适中", "快"}));
		dev350IsAlivecomboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350IsAlivecomboBox.setBounds(82, 20, 105, 26);
		panel_76.add(dev350IsAlivecomboBox);
		
		JLabel label_131 = new JLabel("识别模式：");
		label_131.setFont(new Font("宋体", Font.PLAIN, 14));
		label_131.setBounds(10, 86, 97, 15);
		panel_76.add(label_131);
		
		dev350FaceRagecotionComboBox = new JComboBox<String>();
		dev350FaceRagecotionComboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350FaceRagecotionComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"人脸验证", "人或认证", "人卡验证", "人证验证"}));
		dev350FaceRagecotionComboBox.setBounds(82, 81, 105, 21);
		panel_76.add(dev350FaceRagecotionComboBox);
		
		JLabel label_132 = new JLabel("相 机模 式：");
		label_132.setFont(new Font("宋体", Font.PLAIN, 14));
		label_132.setBounds(198, 145, 96, 15);
		panel_76.add(label_132);
		
		dev350CameraModelComboBox = new JComboBox<String>();
		dev350CameraModelComboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350CameraModelComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"室内模式", "室外模式", "智能模式"}));
		dev350CameraModelComboBox.setBounds(279, 142, 117, 21);
		panel_76.add(dev350CameraModelComboBox);
		
		JPanel panel_77 = new JPanel();
		panel_77.setBorder(BorderFactory.createTitledBorder("App参数"));
		panel_77.setBounds(412, 10, 278, 358);
		panel_350.add(panel_77);
		panel_77.setLayout(null);
		
		JLabel label_133 = new JLabel("界 面 标 题：");
		label_133.setFont(new Font("宋体", Font.PLAIN, 14));
		label_133.setBounds(10, 28, 92, 15);
		panel_77.add(label_133);
		
		dev350AppTitleTextField = new JTextField();
		dev350AppTitleTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350AppTitleTextField.setColumns(10);
		dev350AppTitleTextField.setBounds(101, 25, 167, 21);
		panel_77.add(dev350AppTitleTextField);
		
		dev350AppPassWordTextField = new JTextField();
		dev350AppPassWordTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350AppPassWordTextField.setColumns(10);
		dev350AppPassWordTextField.setBounds(101, 55, 167, 21);
		panel_77.add(dev350AppPassWordTextField);
		
		JLabel lblApp = new JLabel("App  密  码：");
		lblApp.setFont(new Font("宋体", Font.PLAIN, 14));
		lblApp.setBounds(10, 58, 105, 15);
		panel_77.add(lblApp);
		
		JLabel label_136 = new JLabel("识别失败次数：");
		label_136.setFont(new Font("宋体", Font.PLAIN, 14));
		label_136.setBounds(10, 86, 121, 15);
		panel_77.add(label_136);
		
		JLabel label_139 = new JLabel("屏保是否启用：");
		label_139.setFont(new Font("宋体", Font.PLAIN, 14));
		label_139.setBounds(10, 116, 105, 15);
		panel_77.add(label_139);
		
		JLabel label_140 = new JLabel("出现屏保时间：");
		label_140.setFont(new Font("宋体", Font.PLAIN, 14));
		label_140.setBounds(10, 146, 105, 15);
		panel_77.add(label_140);
		
		JLabel lblUrl = new JLabel("屏保 URL地址：");
		lblUrl.setFont(new Font("宋体", Font.PLAIN, 14));
		lblUrl.setBounds(10, 176, 105, 15);
		panel_77.add(lblUrl);
		
		dev350ScreanUrlTextField = new JTextField();
		dev350ScreanUrlTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350ScreanUrlTextField.setEditable(false);
		dev350ScreanUrlTextField.setColumns(10);
		dev350ScreanUrlTextField.setBounds(101, 173, 167, 21);
		panel_77.add(dev350ScreanUrlTextField);
		
		JLabel label_143 = new JLabel("屏保时钟透明度：");
		label_143.setFont(new Font("宋体", Font.PLAIN, 14));
		label_143.setBounds(10, 236, 121, 15);
		panel_77.add(label_143);
		
		dev350KeepAlphaTextField = new JTextField();
		dev350KeepAlphaTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350KeepAlphaTextField.setEditable(false);
		dev350KeepAlphaTextField.setColumns(10);
		dev350KeepAlphaTextField.setBounds(121, 233, 147, 21);
		panel_77.add(dev350KeepAlphaTextField);
		
		dev350RecotionFailCountComboBox = new JComboBox<String>();
		dev350RecotionFailCountComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		dev350RecotionFailCountComboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350RecotionFailCountComboBox.setBounds(101, 80, 167, 26);
		panel_77.add(dev350RecotionFailCountComboBox);
		
		dev350ScreanOnComboBox_5 = new JComboBox<String>();
		dev350ScreanOnComboBox_5.setModel(new DefaultComboBoxModel<String>(new String[] {"关闭", "开启"}));
		dev350ScreanOnComboBox_5.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350ScreanOnComboBox_5.setBounds(101, 110, 167, 26);
		panel_77.add(dev350ScreanOnComboBox_5);
		
		dev350ScreanTimeComboBox = new JComboBox<String>();
		dev350ScreanTimeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"}));
		dev350ScreanTimeComboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350ScreanTimeComboBox.setBounds(101, 141, 131, 26);
		panel_77.add(dev350ScreanTimeComboBox);
		
		JLabel label_135 = new JLabel("分钟");
		label_135.setFont(new Font("宋体", Font.PLAIN, 14));
		label_135.setBounds(234, 146, 34, 15);
		panel_77.add(label_135);
		
		JLabel label_141 = new JLabel("时钟 字体 颜色：");
		label_141.setFont(new Font("宋体", Font.PLAIN, 14));
		label_141.setBounds(10, 266, 121, 15);
		panel_77.add(label_141);
		
		dev350keepColorTextField = new JTextField();
		dev350keepColorTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350keepColorTextField.setEditable(false);
		dev350keepColorTextField.setColumns(10);
		dev350keepColorTextField.setBounds(121, 261, 147, 21);
		panel_77.add(dev350keepColorTextField);
		
		JLabel label_134 = new JLabel("上报陌生人记录：");
		label_134.setFont(new Font("宋体", Font.PLAIN, 14));
		label_134.setBounds(10, 296, 121, 15);
		panel_77.add(label_134);
		
		dev350ReportStangerComboBox = new JComboBox<String>();
		dev350ReportStangerComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"关闭", "开启"}));
		dev350ReportStangerComboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350ReportStangerComboBox.setBounds(120, 291, 148, 26);
		panel_77.add(dev350ReportStangerComboBox);
		
		JLabel label_137 = new JLabel("语音 播报 速度：");
		label_137.setFont(new Font("宋体", Font.PLAIN, 14));
		label_137.setBounds(10, 326, 121, 15);
		panel_77.add(label_137);
		
		dev350SpeakSpeedTextField = new JTextField();
		dev350SpeakSpeedTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350SpeakSpeedTextField.setEditable(false);
		dev350SpeakSpeedTextField.setColumns(10);
		dev350SpeakSpeedTextField.setBounds(121, 323, 147, 21);
		panel_77.add(dev350SpeakSpeedTextField);
		
		JPanel panel_78 = new JPanel();
		panel_78.setBorder(BorderFactory.createTitledBorder("测温模块参数"));
		panel_78.setBounds(693, 10, 239, 325);
		panel_350.add(panel_78);
		panel_78.setLayout(null);
		
		JLabel label_138 = new JLabel("测温模式：");
		label_138.setFont(new Font("宋体", Font.PLAIN, 14));
		label_138.setBounds(10, 26, 87, 15);
		panel_78.add(label_138);
		
		dev350TempModeComboBox = new JComboBox<String>();
		dev350TempModeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"关闭", "打开"}));
		dev350TempModeComboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350TempModeComboBox.setBounds(82, 20, 85, 26);
		panel_78.add(dev350TempModeComboBox);
		
		JLabel label_144 = new JLabel("测温对准点颜色：");
		label_144.setFont(new Font("宋体", Font.PLAIN, 14));
		label_144.setBounds(10, 206, 125, 15);
		panel_78.add(label_144);
		
		dev350tempPointColorTextField = new JTextField();
		dev350tempPointColorTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350tempPointColorTextField.setColumns(10);
		dev350tempPointColorTextField.setBounds(122, 202, 110, 21);
		panel_78.add(dev350tempPointColorTextField);
		
		JLabel label_145 = new JLabel("测 温 框 颜 色：");
		label_145.setFont(new Font("宋体", Font.PLAIN, 14));
		label_145.setBounds(10, 266, 125, 15);
		panel_78.add(label_145);
		
		dev350tempPointRectColorTextField = new JTextField();
		dev350tempPointRectColorTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350tempPointRectColorTextField.setColumns(10);
		dev350tempPointRectColorTextField.setBounds(122, 235, 110, 21);
		panel_78.add(dev350tempPointRectColorTextField);
		
		JLabel label_146 = new JLabel("测温额头区颜色：");
		label_146.setFont(new Font("宋体", Font.PLAIN, 14));
		label_146.setBounds(10, 236, 125, 15);
		panel_78.add(label_146);
		
		dev350tempPointColorTextField_k = new JTextField();
		dev350tempPointColorTextField_k.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350tempPointColorTextField_k.setColumns(10);
		dev350tempPointColorTextField_k.setBounds(122, 262, 110, 21);
		panel_78.add(dev350tempPointColorTextField_k);
		
		JLabel label_148 = new JLabel("测温 框透 明度：");
		label_148.setFont(new Font("宋体", Font.PLAIN, 14));
		label_148.setBounds(10, 296, 125, 15);
		panel_78.add(label_148);
		
		dev350tempPointRectColorAlphaTextField = new JTextField();
		dev350tempPointRectColorAlphaTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350tempPointRectColorAlphaTextField.setColumns(10);
		dev350tempPointRectColorAlphaTextField.setBounds(122, 292, 110, 21);
		panel_78.add(dev350tempPointRectColorAlphaTextField);
		
		JLabel label_149 = new JLabel("自适 环温 补偿：");
		label_149.setFont(new Font("宋体", Font.PLAIN, 14));
		label_149.setBounds(10, 176, 125, 15);
		panel_78.add(label_149);
		
		JLabel label_147 = new JLabel("告警 体温 数值：");
		label_147.setFont(new Font("宋体", Font.PLAIN, 14));
		label_147.setBounds(10, 56, 125, 15);
		panel_78.add(label_147);
		
		dev350EnabletempTextField = new JTextField();
		dev350EnabletempTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350EnabletempTextField.setColumns(10);
		dev350EnabletempTextField.setBounds(122, 142, 110, 21);
		panel_78.add(dev350EnabletempTextField);
		
		JLabel label_150 = new JLabel("测  温  版  本：");
		label_150.setFont(new Font("宋体", Font.PLAIN, 14));
		label_150.setBounds(10, 146, 125, 15);
		panel_78.add(label_150);
		
		dev350TempAlarmTextField = new JTextField();
		dev350TempAlarmTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350TempAlarmTextField.setColumns(10);
		dev350TempAlarmTextField.setBounds(122, 52, 110, 21);
		panel_78.add(dev350TempAlarmTextField);
		
		JLabel label_151 = new JLabel("最低测量体温值：");
		label_151.setFont(new Font("宋体", Font.PLAIN, 14));
		label_151.setBounds(10, 86, 125, 15);
		panel_78.add(label_151);
		
		dev350tempFiterTextField = new JTextField();
		dev350tempFiterTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350tempFiterTextField.setColumns(10);
		dev350tempFiterTextField.setBounds(122, 82, 110, 21);
		panel_78.add(dev350tempFiterTextField);
		
		JLabel label_152 = new JLabel("华 氏 度 转 换：");
		label_152.setFont(new Font("宋体", Font.PLAIN, 14));
		label_152.setBounds(10, 116, 125, 15);
		panel_78.add(label_152);
		
		dev350EnMakeupComboBox = new JComboBox<String>();
		dev350EnMakeupComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"关闭", "打开"}));
		dev350EnMakeupComboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350EnMakeupComboBox.setBounds(122, 171, 110, 26);
		panel_78.add(dev350EnMakeupComboBox);
		
		dev350IsTempFComboBox = new JComboBox<String>();
		dev350IsTempFComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"关闭", "打开"}));
		dev350IsTempFComboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350IsTempFComboBox.setBounds(122, 111, 110, 26);
		panel_78.add(dev350IsTempFComboBox);
		
		JPanel panel_79 = new JPanel();
		panel_79.setLayout(null);
		panel_79.setBorder(BorderFactory.createTitledBorder("补光灯模块参数"));
		panel_79.setBounds(10, 403, 294, 280);
		panel_350.add(panel_79);
		
		JLabel label_153 = new JLabel("智能补光灯模式：");
		label_153.setFont(new Font("宋体", Font.PLAIN, 14));
		label_153.setBounds(10, 27, 133, 15);
		panel_79.add(label_153);
		
		dev350LedOnComboBox = new JComboBox<String>();
		dev350LedOnComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"关闭", "开启"}));
		dev350LedOnComboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350LedOnComboBox.setBounds(123, 21, 85, 26);
		panel_79.add(dev350LedOnComboBox);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_1.setBounds(10, 52, 276, 218);
		panel_79.add(tabbedPane_1);
		
		JPanel panel_80 = new JPanel();
		tabbedPane_1.addTab("时段1", null, panel_80, null);
		panel_80.setLayout(null);
		
		JLabel label_154 = new JLabel("开 始时 间：");
		label_154.setFont(new Font("宋体", Font.PLAIN, 14));
		label_154.setBounds(10, 10, 96, 15);
		panel_80.add(label_154);
		
		dev350TimesStartTimeTextField_1 = new JTextField();
		dev350TimesStartTimeTextField_1.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350TimesStartTimeTextField_1.setColumns(10);
		dev350TimesStartTimeTextField_1.setBounds(91, 6, 117, 21);
		panel_80.add(dev350TimesStartTimeTextField_1);
		
		JLabel label_155 = new JLabel("截 止时 间：");
		label_155.setFont(new Font("宋体", Font.PLAIN, 14));
		label_155.setBounds(10, 38, 96, 15);
		panel_80.add(label_155);
		
		dev350TimesEndTimeTextField_1 = new JTextField();
		dev350TimesEndTimeTextField_1.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350TimesEndTimeTextField_1.setColumns(10);
		dev350TimesEndTimeTextField_1.setBounds(91, 38, 117, 21);
		panel_80.add(dev350TimesEndTimeTextField_1);
		
		JLabel label_156 = new JLabel("相 机模 式：");
		label_156.setFont(new Font("宋体", Font.PLAIN, 14));
		label_156.setBounds(10, 66, 96, 15);
		panel_80.add(label_156);
		
		dev350TimesCameraModeComboBox_1 = new JComboBox<String>();
		dev350TimesCameraModeComboBox_1.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350TimesCameraModeComboBox_1.setBounds(91, 63, 117, 21);
		panel_80.add(dev350TimesCameraModeComboBox_1);
		
		JLabel label_157 = new JLabel("相机曝光强度：");
		label_157.setFont(new Font("宋体", Font.PLAIN, 14));
		label_157.setBounds(10, 94, 105, 15);
		panel_80.add(label_157);
		
		dev350TimesCameraExposureTextField_1 = new JTextField();
		dev350TimesCameraExposureTextField_1.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350TimesCameraExposureTextField_1.setColumns(10);
		dev350TimesCameraExposureTextField_1.setBounds(91, 91, 117, 21);
		panel_80.add(dev350TimesCameraExposureTextField_1);
		
		JLabel label_158 = new JLabel("活 体类 型：");
		label_158.setFont(new Font("宋体", Font.PLAIN, 14));
		label_158.setBounds(10, 122, 96, 15);
		panel_80.add(label_158);
		
		dev350IsAliveSensitivityComboBox_1 = new JComboBox<String>();
		dev350IsAliveSensitivityComboBox_1.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350IsAliveSensitivityComboBox_1.setBounds(91, 116, 117, 26);
		panel_80.add(dev350IsAliveSensitivityComboBox_1);
		
		JLabel label_159 = new JLabel("活 体阈 值：");
		label_159.setFont(new Font("宋体", Font.PLAIN, 14));
		label_159.setBounds(9, 151, 97, 15);
		panel_80.add(label_159);
		
		dev350IsAliveThresholdTextField_1 = new JTextField();
		dev350IsAliveThresholdTextField_1.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350IsAliveThresholdTextField_1.setColumns(10);
		dev350IsAliveThresholdTextField_1.setBounds(91, 147, 117, 21);
		panel_80.add(dev350IsAliveThresholdTextField_1);
		
		JLabel label_160 = new JLabel("检 测阈 值：");
		label_160.setFont(new Font("宋体", Font.PLAIN, 14));
		label_160.setBounds(10, 176, 97, 15);
		panel_80.add(label_160);
		
		dev350DetectThresholdTextfield_1 = new JTextField();
		dev350DetectThresholdTextfield_1.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350DetectThresholdTextfield_1.setColumns(10);
		dev350DetectThresholdTextfield_1.setBounds(92, 173, 117, 21);
		panel_80.add(dev350DetectThresholdTextfield_1);
		
		JPanel panel_81 = new JPanel();
		tabbedPane_1.addTab("时段2", null, panel_81, null);
		panel_81.setLayout(null);
		
		JLabel label_161 = new JLabel("开 始时 间：");
		label_161.setFont(new Font("宋体", Font.PLAIN, 14));
		label_161.setBounds(11, 14, 96, 15);
		panel_81.add(label_161);
		
		dev350TimesStartTimeTextField_2 = new JTextField();
		dev350TimesStartTimeTextField_2.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350TimesStartTimeTextField_2.setColumns(10);
		dev350TimesStartTimeTextField_2.setBounds(92, 10, 117, 21);
		panel_81.add(dev350TimesStartTimeTextField_2);
		
		JLabel label_162 = new JLabel("截 止时 间：");
		label_162.setFont(new Font("宋体", Font.PLAIN, 14));
		label_162.setBounds(11, 42, 96, 15);
		panel_81.add(label_162);
		
		dev350TimesEndTimeTextField_2 = new JTextField();
		dev350TimesEndTimeTextField_2.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350TimesEndTimeTextField_2.setColumns(10);
		dev350TimesEndTimeTextField_2.setBounds(92, 42, 117, 21);
		panel_81.add(dev350TimesEndTimeTextField_2);
		
		JLabel label_163 = new JLabel("相 机模 式：");
		label_163.setFont(new Font("宋体", Font.PLAIN, 14));
		label_163.setBounds(11, 70, 96, 15);
		panel_81.add(label_163);
		
		dev350TimesCameraModeComboBox_2 = new JComboBox<String>();
		dev350TimesCameraModeComboBox_2.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350TimesCameraModeComboBox_2.setBounds(92, 67, 117, 21);
		panel_81.add(dev350TimesCameraModeComboBox_2);
		
		JLabel label_164 = new JLabel("相机曝光强度：");
		label_164.setFont(new Font("宋体", Font.PLAIN, 14));
		label_164.setBounds(11, 98, 96, 15);
		panel_81.add(label_164);
		
		dev350TimesCameraExposureTextField_2 = new JTextField();
		dev350TimesCameraExposureTextField_2.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350TimesCameraExposureTextField_2.setColumns(10);
		dev350TimesCameraExposureTextField_2.setBounds(92, 95, 117, 21);
		panel_81.add(dev350TimesCameraExposureTextField_2);
		
		JLabel label_165 = new JLabel("活 体类 型：");
		label_165.setFont(new Font("宋体", Font.PLAIN, 14));
		label_165.setBounds(11, 126, 96, 15);
		panel_81.add(label_165);
		
		dev350IsAliveSensitivityComboBox_2 = new JComboBox<String>();
		dev350IsAliveSensitivityComboBox_2.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350IsAliveSensitivityComboBox_2.setBounds(92, 120, 117, 26);
		panel_81.add(dev350IsAliveSensitivityComboBox_2);
		
		JLabel label_166 = new JLabel("活 体阈 值：");
		label_166.setFont(new Font("宋体", Font.PLAIN, 14));
		label_166.setBounds(10, 155, 97, 15);
		panel_81.add(label_166);
		
		dev350IsAliveThresholdTextField_2 = new JTextField();
		dev350IsAliveThresholdTextField_2.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350IsAliveThresholdTextField_2.setColumns(10);
		dev350IsAliveThresholdTextField_2.setBounds(92, 151, 117, 21);
		panel_81.add(dev350IsAliveThresholdTextField_2);
		
		JLabel label_167 = new JLabel("检 测阈 值：");
		label_167.setFont(new Font("宋体", Font.PLAIN, 14));
		label_167.setBounds(11, 180, 97, 15);
		panel_81.add(label_167);
		
		dev350DetectThresholdTextfield_2 = new JTextField();
		dev350DetectThresholdTextfield_2.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350DetectThresholdTextfield_2.setColumns(10);
		dev350DetectThresholdTextfield_2.setBounds(93, 177, 117, 21);
		panel_81.add(dev350DetectThresholdTextfield_2);
		
		JPanel panel_82 = new JPanel();
		tabbedPane_1.addTab("时段3", null, panel_82, null);
		panel_82.setLayout(null);
		
		JLabel label_168 = new JLabel("开 始时 间：");
		label_168.setFont(new Font("宋体", Font.PLAIN, 14));
		label_168.setBounds(11, 14, 96, 15);
		panel_82.add(label_168);
		
		dev350TimesStartTimeTextField_3 = new JTextField();
		dev350TimesStartTimeTextField_3.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350TimesStartTimeTextField_3.setColumns(10);
		dev350TimesStartTimeTextField_3.setBounds(92, 10, 117, 21);
		panel_82.add(dev350TimesStartTimeTextField_3);
		
		JLabel label_169 = new JLabel("截 止时 间：");
		label_169.setFont(new Font("宋体", Font.PLAIN, 14));
		label_169.setBounds(11, 42, 96, 15);
		panel_82.add(label_169);
		
		dev350TimesEndTimeTextField_3 = new JTextField();
		dev350TimesEndTimeTextField_3.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350TimesEndTimeTextField_3.setColumns(10);
		dev350TimesEndTimeTextField_3.setBounds(92, 42, 117, 21);
		panel_82.add(dev350TimesEndTimeTextField_3);
		
		JLabel label_170 = new JLabel("相 机模 式：");
		label_170.setFont(new Font("宋体", Font.PLAIN, 14));
		label_170.setBounds(11, 70, 96, 15);
		panel_82.add(label_170);
		
		dev350TimesCameraModeComboBox_3 = new JComboBox<String>();
		dev350TimesCameraModeComboBox_3.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350TimesCameraModeComboBox_3.setBounds(92, 67, 117, 21);
		panel_82.add(dev350TimesCameraModeComboBox_3);
		
		JLabel label_171 = new JLabel("相机曝光强度：");
		label_171.setFont(new Font("宋体", Font.PLAIN, 14));
		label_171.setBounds(11, 98, 96, 15);
		panel_82.add(label_171);
		
		dev350TimesCameraExposureTextField_3 = new JTextField();
		dev350TimesCameraExposureTextField_3.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350TimesCameraExposureTextField_3.setColumns(10);
		dev350TimesCameraExposureTextField_3.setBounds(92, 95, 117, 21);
		panel_82.add(dev350TimesCameraExposureTextField_3);
		
		JLabel label_172 = new JLabel("活 体类 型：");
		label_172.setFont(new Font("宋体", Font.PLAIN, 14));
		label_172.setBounds(11, 126, 96, 15);
		panel_82.add(label_172);
		
		dev350IsAliveSensitivityComboBox_3 = new JComboBox<String>();
		dev350IsAliveSensitivityComboBox_3.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350IsAliveSensitivityComboBox_3.setBounds(92, 120, 117, 26);
		panel_82.add(dev350IsAliveSensitivityComboBox_3);
		
		JLabel label_173 = new JLabel("活 体阈 值：");
		label_173.setFont(new Font("宋体", Font.PLAIN, 14));
		label_173.setBounds(10, 155, 97, 15);
		panel_82.add(label_173);
		
		dev350IsAliveThresholdTextField_3 = new JTextField();
		dev350IsAliveThresholdTextField_3.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350IsAliveThresholdTextField_3.setColumns(10);
		dev350IsAliveThresholdTextField_3.setBounds(92, 151, 117, 21);
		panel_82.add(dev350IsAliveThresholdTextField_3);
		
		JLabel label_174 = new JLabel("检 测阈 值：");
		label_174.setFont(new Font("宋体", Font.PLAIN, 14));
		label_174.setBounds(11, 180, 97, 15);
		panel_82.add(label_174);
		
		dev350DetectThresholdTextfield_3 = new JTextField();
		dev350DetectThresholdTextfield_3.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350DetectThresholdTextfield_3.setColumns(10);
		dev350DetectThresholdTextfield_3.setBounds(93, 177, 117, 21);
		panel_82.add(dev350DetectThresholdTextfield_3);
		
		JPanel panel_83 = new JPanel();
		tabbedPane_1.addTab("时段4", null, panel_83, null);
		panel_83.setLayout(null);
		
		JLabel label_175 = new JLabel("开 始时 间：");
		label_175.setFont(new Font("宋体", Font.PLAIN, 14));
		label_175.setBounds(11, 14, 96, 15);
		panel_83.add(label_175);
		
		dev350TimesStartTimeTextField_4 = new JTextField();
		dev350TimesStartTimeTextField_4.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350TimesStartTimeTextField_4.setColumns(10);
		dev350TimesStartTimeTextField_4.setBounds(92, 10, 117, 21);
		panel_83.add(dev350TimesStartTimeTextField_4);
		
		JLabel label_176 = new JLabel("截 止时 间：");
		label_176.setFont(new Font("宋体", Font.PLAIN, 14));
		label_176.setBounds(11, 42, 96, 15);
		panel_83.add(label_176);
		
		dev350TimesEndTimeTextField_4 = new JTextField();
		dev350TimesEndTimeTextField_4.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350TimesEndTimeTextField_4.setColumns(10);
		dev350TimesEndTimeTextField_4.setBounds(92, 42, 117, 21);
		panel_83.add(dev350TimesEndTimeTextField_4);
		
		JLabel label_177 = new JLabel("相 机模 式：");
		label_177.setFont(new Font("宋体", Font.PLAIN, 14));
		label_177.setBounds(11, 70, 96, 15);
		panel_83.add(label_177);
		
		dev350TimesCameraModeComboBox_4 = new JComboBox<String>();
		dev350TimesCameraModeComboBox_4.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350TimesCameraModeComboBox_4.setBounds(92, 67, 117, 21);
		panel_83.add(dev350TimesCameraModeComboBox_4);
		
		JLabel label_178 = new JLabel("相机曝光强度：");
		label_178.setFont(new Font("宋体", Font.PLAIN, 14));
		label_178.setBounds(11, 98, 124, 15);
		panel_83.add(label_178);
		
		dev350TimesCameraExposureTextField_4 = new JTextField();
		dev350TimesCameraExposureTextField_4.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350TimesCameraExposureTextField_4.setColumns(10);
		dev350TimesCameraExposureTextField_4.setBounds(92, 95, 117, 21);
		panel_83.add(dev350TimesCameraExposureTextField_4);
		
		JLabel label_179 = new JLabel("活 体类 型：");
		label_179.setFont(new Font("宋体", Font.PLAIN, 14));
		label_179.setBounds(11, 126, 96, 15);
		panel_83.add(label_179);
		
		dev350IsAliveSensitivityComboBox_4 = new JComboBox<String>();
		dev350IsAliveSensitivityComboBox_4.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350IsAliveSensitivityComboBox_4.setBounds(92, 120, 117, 26);
		panel_83.add(dev350IsAliveSensitivityComboBox_4);
		
		JLabel label_180 = new JLabel("活 体阈 值：");
		label_180.setFont(new Font("宋体", Font.PLAIN, 14));
		label_180.setBounds(10, 155, 97, 15);
		panel_83.add(label_180);
		
		dev350IsAliveThresholdTextField_4 = new JTextField();
		dev350IsAliveThresholdTextField_4.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350IsAliveThresholdTextField_4.setColumns(10);
		dev350IsAliveThresholdTextField_4.setBounds(92, 151, 117, 21);
		panel_83.add(dev350IsAliveThresholdTextField_4);
		
		JLabel label_181 = new JLabel("检 测阈 值：");
		label_181.setFont(new Font("宋体", Font.PLAIN, 14));
		label_181.setBounds(11, 180, 97, 15);
		panel_83.add(label_181);
		
		dev350DetectThresholdTextfield_4 = new JTextField();
		dev350DetectThresholdTextfield_4.setFont(new Font("宋体", Font.PLAIN, 14));
		dev350DetectThresholdTextfield_4.setColumns(10);
		dev350DetectThresholdTextfield_4.setBounds(93, 177, 117, 21);
		panel_83.add(dev350DetectThresholdTextfield_4);
		
		JPanel panel_14 = new JPanel();
		tabbedPane.addTab("测试模块", null, panel_14, null);
		panel_14.setLayout(null);
		
		JPanel panel_39 = new JPanel();
		panel_39.setBorder(BorderFactory.createTitledBorder("批量下载文件夹中所有照片测试"));
		panel_39.setBounds(10, 10, 284, 74);
		panel_14.add(panel_39);
		panel_39.setLayout(null);
		
		JButton button_12 = new JButton("批量导入测试");
		button_12.setEnabled(false);
		button_12.setBounds(10, 20, 132, 23);
		panel_39.add(button_12);
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							new DownLoadAuthorityStatu().DownLoadAuthority_file(faceDevchoiseComboBox.getSelectedItem().toString(), button_12);
						}
					}).start();
			}
		});
		button_12.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JLabel label_73 = new JLabel("备注：批量测试时为防止干扰，会清空数据库");
		label_73.setBounds(10, 53, 264, 15);
		panel_39.add(label_73);
		label_73.setFont(new Font("宋体", Font.PLAIN, 13));
		
		JPanel panel_60 = new JPanel();
		panel_60.setBounds(10, 94, 284, 241);
		panel_14.add(panel_60);
		panel_60.setBorder(BorderFactory.createTitledBorder("日志路径:logs\\权限下载错误日志***.txt"));
		panel_60.setLayout(null);
		
		JButton button_27 = new JButton("批量新增人员");
		button_27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				downloadAuthorLabel_count.setText("0");
				downloadAuthorLabel_err.setText("0");
				downloadAuthorLabel_success.setText("0");
				File file = new MyFileUtil().chooserDIRECTORIES();
				if ( null != file ) {
					int fileArrListlength = file.listFiles().length;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							new DownLoadAuthorityStatu().insertIntoPersonInfo(file);
						}
					}).start();
					button_27.setEnabled(false);
					downloadAuthorLabel_count.setText(Integer.toString(fileArrListlength));
					if (fileArrListlength > 0) {
						new PrimeNumbersTask(button_27, MainIntfaceView.progressBar, fileArrListlength, 0).execute();
						downloadAuthorLabel_count.setText(Integer.toString(fileArrListlength));
					}
					
				}
			}
		});
		
		button_27.setFont(new Font("宋体", Font.PLAIN, 14));
		button_27.setBounds(10, 26, 132, 23);
		panel_60.add(button_27);
		
		JButton button_28 = new JButton("批量提取特征值");
		button_28.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				downloadAuthorLabel_count.setText("0");
				downloadAuthorLabel_err.setText("0");
				downloadAuthorLabel_success.setText("0");
				int cityCount = DataBaseExecute.getInstance().getDownLoadFeatureCount(devInfoMap.get(faceDevComboBox.getSelectedItem()).getFeature_type());
				new Thread(new Runnable() {
					@Override
					public void run() {
						new DownLoadAuthorityStatu().dowLoadFeature(faceDevchoiseComboBox.getSelectedItem().toString());
					}
				}).start();
				button_28.setEnabled(false);
				if (cityCount > 0) {
					new PrimeNumbersTask(button_28, MainIntfaceView.progressBar, cityCount, 0).execute();
					downloadAuthorLabel_count.setText(Integer.toString(cityCount));
				}
				
			}
		});
		button_28.setFont(new Font("宋体", Font.PLAIN, 14));
		button_28.setBounds(10, 59, 132, 23);
		panel_60.add(button_28);
		
		JButton button_29 = new JButton("批量下载");
		button_29.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				downloadAuthorLabel_count.setText("0");
				downloadAuthorLabel_err.setText("0");
				downloadAuthorLabel_success.setText("0");
				String macAddr = faceDevchoiseComboBox.getSelectedItem().toString();
				if (!getDownLoadcheckBox().isSelected()) {
					//下载前自动更新下载标记为
					DataBaseExecute.getInstance().updateDB("face_dev_author_set", 
							Arrays.asList("dev_id"), Arrays.asList(MainIntfaceView.getDevInfo().get(macAddr).getDevID()), 
							Arrays.asList("down_loaded"), 
							Arrays.asList("0"));
				}
				int cityCount = new DownLoadAuthority().getFeatrueCount(downLoadcheckBox.isSelected(), macAddr);
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						/*
						 * 巨龙设备类型为4 ，不支持多线程，只能一张一张下载
						 * type 0:含photo_url、 无feature  1 ：含photo_url、feature 、2:含PhotoBase64、 3：含PhotoBase64，feature, 4:含photo_url、feature,down_loaded = 0;、5： is_legal = N 删除权限
						 */
						new DownLoadAuthorityStatu().downLoadAuthority(macAddr, MainIntfaceView.getDevInfo().get(macAddr).getDevID(), downLoadcheckBox_noFeature.isSelected());
					}
				}).start();
				button_29.setEnabled(false);
				if (cityCount > 0) {
					new PrimeNumbersTask(button_29, MainIntfaceView.progressBar, cityCount, 0).execute();
					downloadAuthorLabel_count.setText(Integer.toString(cityCount));
				}
				
			}
		});
		button_29.setFont(new Font("宋体", Font.PLAIN, 14));
		button_29.setBounds(10, 92, 132, 23);
		panel_60.add(button_29);
		
		progressBar = new MyProgressBar();
		progressBar.setBounds(10, 140, 264, 14);
		progressBar.setStringPainted(true);
		progressBar.setIndeterminate(false);//采用确定的进度条样式
		panel_60.add(progressBar);
		
		JLabel label_78 = new JLabel("总数量：");
		label_78.setFont(new Font("宋体", Font.PLAIN, 14));
		label_78.setBounds(10, 165, 84, 15);
		panel_60.add(label_78);
		
		downloadAuthorLabel_count = new JLabel("");
		downloadAuthorLabel_count.setHorizontalAlignment(SwingConstants.LEFT);
		downloadAuthorLabel_count.setFont(new Font("宋体", Font.PLAIN, 14));
		downloadAuthorLabel_count.setBounds(89, 165, 199, 15);
		panel_60.add(downloadAuthorLabel_count);
		
		JLabel label_83 = new JLabel("成功数：");
		label_83.setFont(new Font("宋体", Font.PLAIN, 14));
		label_83.setBounds(10, 190, 84, 15);
		panel_60.add(label_83);
		
		downloadAuthorLabel_success = new JLabel("");
		downloadAuthorLabel_success.setHorizontalAlignment(SwingConstants.LEFT);
		downloadAuthorLabel_success.setFont(new Font("宋体", Font.PLAIN, 14));
		downloadAuthorLabel_success.setBounds(89, 190, 195, 15);
		panel_60.add(downloadAuthorLabel_success);
		
		JLabel label_85 = new JLabel("失败数：");
		label_85.setFont(new Font("宋体", Font.PLAIN, 14));
		label_85.setBounds(10, 215, 84, 15);
		panel_60.add(label_85);
		
		downloadAuthorLabel_err = new JLabel("");
		downloadAuthorLabel_err.setHorizontalAlignment(SwingConstants.LEFT);
		downloadAuthorLabel_err.setFont(new Font("宋体", Font.PLAIN, 14));
		downloadAuthorLabel_err.setBounds(89, 215, 195, 15);
		panel_60.add(downloadAuthorLabel_err);
		
		downLoadcheckBox = new JCheckBox("过滤已下载");
		downLoadcheckBox.setSelected(true);
		downLoadcheckBox.setBounds(162, 59, 91, 23);
		panel_60.add(downLoadcheckBox);
		
		JButton button_3 = new JButton("中断下载");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DownLoadAuthorityStatu.setWantBreak(true);
			}
		});
		button_3.setFont(new Font("宋体", Font.PLAIN, 14));
		button_3.setBounds(152, 26, 122, 23);
		panel_60.add(button_3);
		
		downLoadcheckBox_noFeature = new JCheckBox("带特征值下载");
		downLoadcheckBox_noFeature.setSelected(true);
		downLoadcheckBox_noFeature.setBounds(162, 92, 112, 23);
		panel_60.add(downLoadcheckBox_noFeature);
		
		JPanel panel_40 = new JPanel();
		panel_40.setBorder(BorderFactory.createTitledBorder("定时重启测试"));
		panel_40.setBounds(10, 345, 284, 61);
		panel_14.add(panel_40);
		panel_40.setLayout(null);
		
		JLabel label_74 = new JLabel("定时重启时间：");
		label_74.setFont(new Font("宋体", Font.PLAIN, 14));
		label_74.setBounds(10, 26, 105, 15);
		panel_40.add(label_74);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_1.setBounds(104, 22, 73, 21);
		panel_40.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_75 = new JLabel("秒");
		label_75.setFont(new Font("宋体", Font.PLAIN, 14));
		label_75.setBounds(187, 26, 26, 15);
		panel_40.add(label_75);
		
		JCheckBox checkBox = new JCheckBox("启动");
		checkBox.setFont(new Font("宋体", Font.PLAIN, 14));
		
		checkBox.addActionListener(new ActionListener() {
			Timer timer = null;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String timeStr = textField_1.getText().replaceAll(" ", "");
//				System.out.println(timeStr);
				Long time = 0L;
				if("".equals(timeStr) || timeStr.length() < 0) {
					JOptionPane.showMessageDialog(null, "定时间隔输入为空！！！", "定时重启设置", JOptionPane.ERROR_MESSAGE);
					checkBox.setSelected(false);
				}else {
					try {
						time = Long.parseLong(timeStr) * 1000L;
						
						if(checkBox.isSelected()) {
							timer = new Timer("testTimer");
							
							String macAddr = faceDevchoiseComboBox.getSelectedItem().toString();
							RequestGson<String> requestGson = new RequestGson<String>();
							requestGson.setData(null);
							requestGson.setDeviceUniqueCode(macAddr);
							requestGson.setTimeStamp(SystemTimes.getSysTime1());
							String dataParametre = new GsonBuilder().serializeNulls().create()
									.toJson(
											requestGson, 
											new TypeToken<RequestGson<String>>() {}.getType());
						
							timer.schedule(new TimerTask() {
								@Override
								public void run() {
									new RestartDevice(dataParametre, macAddr);
								}
							}, 3000,time);
						}else {
							timer.cancel();
						}
						
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "请输入纯数字！！！", "定时重启设置", JOptionPane.ERROR_MESSAGE);
						checkBox.setSelected(false);
					}
				}
			}
		});
		checkBox.setBounds(219, 22, 56, 23);
		panel_40.add(checkBox);
		
		JPanel panel_61 = new JPanel();
		panel_61.setBorder(BorderFactory.createTitledBorder("照片旋转、裁剪"));
		panel_61.setBounds(10, 416, 284, 146);
		panel_14.add(panel_61);
		panel_61.setLayout(null);
		
		JButton button_14 = new JButton("人脸照片自动旋转、裁剪");
		button_14.setBounds(10, 26, 247, 23);
		button_14.setToolTipText("注意：算法不一致，检查成功的图片无法保证百分百可使用！！！");
		panel_61.add(button_14);
		button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						File file = new MyFileUtil().chooserDIRECTORIES();
						if ( null != file ) {
							File[] fileList = file.listFiles();
							//这里由于会新建3个文件夹：CutSuccess、CutFail、temp，所以这里-3
							int fileSize = fileList.length - 3;
							int success = 0 ;
							int fail = 0 ;
							PrimeNumbersTask pnk = new PrimeNumbersTask(button_14, MainIntfaceView.progressBar, fileSize, 0);
							pnk.execute();
							downloadAuthorLabel_count.setText(Integer.toString(fileSize));
							Image image = null;
							//加载人脸算法
							MyFaceEngine myFaceEngine = new MyFaceEngine();
							try {
								for (int i = 0 ; i < fileSize; i++) {
//									System.out.println("fileList["+i+"] = " + fileList[i]);
									if( fileList[i].isFile()) {
										image = ImageIO.read(fileList[i]);
										if ( null != image) { 
											if (myFaceEngine.getPhotoExecute(fileList[i])) {
												success += 1;
											}else {
												fail += 1; 
											}
											
										}else {
											fail += 1; 
										}
									}else {
										fail += 1; 
									}
									PrimeNumbersTask.setNumber(i + 1);
									downloadAuthorLabel_success.setText(Integer.toString(success));
									downloadAuthorLabel_err.setText(Integer.toString(fail));
								}
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}).start();
			}
		});
		button_14.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JLabel lblNewLabel_3 = new JLabel("<html>注：该功能只支持筛选JPG格式照片，检测到人脸，并抠图成功的照片存在CutSuccess文件夹下，失败则存放在CutFail文件夹；文件夹、非图片文件计算在失败数中。失败类型：1、未检测到人脸；2、照片旋转角度过大；</html>");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(10, 54, 266, 82);
		panel_61.add(lblNewLabel_3);
		
		JPanel panel_19 = new JPanel();
		panel_19.setBounds(307, 10, 212, 552);
		panel_19.setBorder(BorderFactory.createTitledBorder("测试服务器模块"));
		panel_14.add(panel_19);
		panel_19.setLayout(null);
		

		JLabel lblip = new JLabel("服务器IP：");
		lblip.setFont(new Font("宋体", Font.PLAIN, 14));
		lblip.setBounds(6, 29, 77, 15);
		panel_19.add(lblip);
		
		serverIPJTextField = new JTextField();
		serverIPJTextField.setText("172.168.121.249");
		serverIPJTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		serverIPJTextField.setColumns(10);
		serverIPJTextField.setBounds(72, 25, 130, 21);
		panel_19.add(serverIPJTextField);
		
		JLabel label_76 = new JLabel("服务端口：");
		label_76.setFont(new Font("宋体", Font.PLAIN, 14));
		label_76.setBounds(6, 59, 77, 15);
		panel_19.add(label_76);
		
		JLabel label_84 = new JLabel("设备端口：");
		label_84.setFont(new Font("宋体", Font.PLAIN, 14));
		label_84.setBounds(6, 89, 77, 15);
		panel_19.add(label_84);
		
		serverProtJTextField = new JTextField();
		serverProtJTextField.setText("8041");
		serverProtJTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		serverProtJTextField.setColumns(10);
		serverProtJTextField.setBounds(72, 56, 130, 21);
		panel_19.add(serverProtJTextField);
		
		devPortJTextField = new JTextField();
		devPortJTextField.setText("19003");
		devPortJTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		devPortJTextField.setColumns(10);
		devPortJTextField.setBounds(72, 86, 130, 21);
		panel_19.add(devPortJTextField);
		
		JPanel panel_20 = new JPanel();
		panel_20.setBounds(6, 179, 196, 53);
		panel_20.setBorder(BorderFactory.createTitledBorder("模拟设备上线"));
		panel_19.add(panel_20);
		panel_20.setLayout(null);
		JRadioButton devOnlineRadioButton_XF = new JRadioButton("消费");
		devOnlineRadioButton_XF.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				com.testModel.heartBeatTest.ResponseGetDeviceParams.setDeviceName(com.testModel.heartBeatTest.ResponseGetDeviceParams.DEVICE_NAME_XF);
				com.testModel.heartBeatTest.ResponseGetDeviceParams.setDeviceType("4");
//				TestParamInit.getInstance();
			}
		});
		devOnlineRadioButton_XF.setSelected(true);
		devOnlineRadioButton_XF.setFont(new Font("宋体", Font.PLAIN, 12));
		devOnlineRadioButton_XF.setBounds(89, 22, 49, 23);
		panel_20.add(devOnlineRadioButton_XF);
		
		JRadioButton devOnlineRadioButton_MJ = new JRadioButton("门禁");
		devOnlineRadioButton_MJ.addItemListener(new ItemListener() {
					
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				com.testModel.heartBeatTest.ResponseGetDeviceParams.setDeviceName(com.testModel.heartBeatTest.ResponseGetDeviceParams.DEVICE_NAME_MJ);
				com.testModel.heartBeatTest.ResponseGetDeviceParams.setDeviceType("1");
//				TestParamInit.getInstance();
			}
		});
		devOnlineRadioButton_MJ.setFont(new Font("宋体", Font.PLAIN, 12));
		devOnlineRadioButton_MJ.setBounds(138, 22, 49, 23);
		panel_20.add(devOnlineRadioButton_MJ);
		
		ButtonGroup devOnlineButtonGroup = new ButtonGroup();
		devOnlineButtonGroup.add(devOnlineRadioButton_XF);
		devOnlineButtonGroup.add(devOnlineRadioButton_MJ);
		
		JCheckBox devOnlineCheckBox_1 = new JCheckBox("定时启动");
		devOnlineCheckBox_1.setBounds(6, 22, 81, 23);
		devOnlineCheckBox_1.addActionListener(new ActionListener() {
			private Timer timer = null;
//			private List<String> macTestList = new ArrayList<String>();
//			private String url ;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				int times = 1;
				if(devOnlineCheckBox_1.isSelected()) {
					devOnlineRadioButton_MJ.setEnabled(false);
					devOnlineRadioButton_XF.setEnabled(false);
					timer = new Timer("testTimer");
//					TestParamInit testParamInit = new TestParamInit(serverIPJTextField, serverProtJTextField, devPortJTextField, devNoJTextField, systemIDJTextField);
					TestParamInit testParamInit = TestParamInit.getInstance();
					timer.schedule(new TimerTask() {
			            @Override
			            public void run() {
			            	testParamInit.doHeartBeat();
			            }
			        }, 1000,30000);
				}else {
					devOnlineRadioButton_MJ.setEnabled(true);
					devOnlineRadioButton_XF.setEnabled(true);
					if (null != timer)
						timer.cancel();
				}
			}
		});
		panel_20.add(devOnlineCheckBox_1);
		devOnlineCheckBox_1.setFont(new Font("宋体", Font.PLAIN, 14));
		
		
		
		JLabel label_86 = new JLabel("设备台数：");
		label_86.setFont(new Font("宋体", Font.PLAIN, 14));
		label_86.setBounds(6, 119, 77, 15);
		panel_19.add(label_86);
		
		devNoJTextField = new JTextField();
		devNoJTextField.setText("1");
		devNoJTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		devNoJTextField.setColumns(10);
		devNoJTextField.setBounds(72, 116, 130, 21);
		panel_19.add(devNoJTextField);
		
		JPanel panel_62 = new JPanel();
		panel_62.setLayout(null);
		panel_62.setBorder(BorderFactory.createTitledBorder("模拟人脸权限下载"));
		panel_62.setBounds(6, 242, 196, 124);
		panel_19.add(panel_62);
		
		JCheckBox checkBox_2 = new JCheckBox("启动");
		checkBox_2.setFont(new Font("宋体", Font.PLAIN, 14));
		checkBox_2.setBounds(6, 22, 88, 23);
		checkBox_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (checkBox_2.isSelected()) {
					cleanCharacterLabel();
//					new TestParamInit(serverIPJTextField, serverProtJTextField, devPortJTextField, devNoJTextField, systemIDJTextField).doCharacter();
					TestParamInit.getInstance().doCharacter();
					
				}else {
				}
			}
		});
		panel_62.add(checkBox_2);
		
		JLabel label_92 = new JLabel("累计总数：");
		label_92.setFont(new Font("宋体", Font.PLAIN, 14));
		label_92.setBounds(6, 51, 70, 15);
		panel_62.add(label_92);
		
		JLabel label_93 = new JLabel("成功总数：");
		label_93.setFont(new Font("宋体", Font.PLAIN, 14));
		label_93.setBounds(6, 76, 70, 15);
		panel_62.add(label_93);
		
		JLabel label_94 = new JLabel("失败总数：");
		label_94.setFont(new Font("宋体", Font.PLAIN, 14));
		label_94.setBounds(6, 101, 70, 15);
		panel_62.add(label_94);
		
		CharacterLabel_count = new JLabel("");
		CharacterLabel_count.setFont(new Font("宋体", Font.PLAIN, 14));
		CharacterLabel_count.setBounds(73, 51, 113, 15);
		panel_62.add(CharacterLabel_count);
		
		CharacterLabel_success = new JLabel("");
		CharacterLabel_success.setFont(new Font("宋体", Font.PLAIN, 14));
		CharacterLabel_success.setBounds(73, 76, 113, 15);
		panel_62.add(CharacterLabel_success);
		
		CharacterLabel_fail = new JLabel("");
		CharacterLabel_fail.setFont(new Font("宋体", Font.PLAIN, 14));
		CharacterLabel_fail.setBounds(73, 101, 113, 15);
		panel_62.add(CharacterLabel_fail);
		
		JLabel label_87 = new JLabel("系统编号：");
		label_87.setFont(new Font("宋体", Font.PLAIN, 14));
		label_87.setBounds(6, 149, 77, 15);
		panel_19.add(label_87);
		
		systemIDJTextField = new JTextField();
		systemIDJTextField.setText("0000034900");
		systemIDJTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		systemIDJTextField.setColumns(10);
		systemIDJTextField.setBounds(72, 146, 130, 21);
		panel_19.add(systemIDJTextField);
		
		JPanel panel_65 = new JPanel();
		panel_65.setLayout(null);
		panel_65.setBorder(BorderFactory.createTitledBorder("模拟通讯记录上传"));
		panel_65.setBounds(6, 376, 196, 146);
		panel_19.add(panel_65);
		
		JCheckBox checkBox_4 = new JCheckBox("单机模拟");
		JCheckBox checkBox_5 = new JCheckBox("连库模拟");
		checkBox_4.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				
//				ExecutorService pool = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10),Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
				TestParamInit testParamInit = TestParamInit.getInstance();
				if (checkBox_4.isSelected()) {
					cleanRecordLabel();
					testParamInit.setSetBreak(false);
					String uniqueCode = textField_2.getText().replaceAll(" ", "");
					DbServerTest dbServerTest = DbServerTest.getInstance();
					
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							testParamInit.doUploadRecord(0, uniqueCode, dbServerTest);
						}
					}).start();
					
				}else {
//					pool.shutdown();
					testParamInit.setSetBreak(true);
				}
			}
		});
		checkBox_4.setFont(new Font("宋体", Font.PLAIN, 14));
		checkBox_4.setBounds(6, 22, 88, 23);
		panel_65.add(checkBox_4);
		
		JLabel label_88 = new JLabel("UniqueCode：");
		label_88.setFont(new Font("宋体", Font.PLAIN, 14));
		label_88.setBounds(6, 50, 92, 15);
		panel_65.add(label_88);
		
		textField_2 = new JTextField();
		textField_2.setText("1048577");
		textField_2.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(87, 47, 95, 21);
		panel_65.add(textField_2);
		
//		JCheckBox checkBox_5 = new JCheckBox("连库模拟");
		checkBox_5.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
//				ExecutorService pool = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10),Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
				TestParamInit testParamInit = TestParamInit.getInstance();
				if (checkBox_5.isSelected()) {
					cleanRecordLabel();
					testParamInit.setSetBreak(false);
					DbServerTest dbServerTest = DbServerTest.getInstance();
//					dbServerTest.getConnect(dbSQLRadioButton, dbOracleRadioButton, dbSQLite3RadioButton, dbServerIPJTextField, dbUserNameJTextField, dbPasswordJTextField, dbNameComboBox);
					if (dbServerTest.getConn() == null) {
						checkBox_5.setSelected(false);
					}else {
						new Thread(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								testParamInit.doUploadRecord(1, "", dbServerTest);
							}
						}).start();
					}
					
					
				}else {
					System.out.println("未选中");
//					pool.shutdownNow();
					testParamInit.setSetBreak(true);
				}
			}
		});
		checkBox_5.setFont(new Font("宋体", Font.PLAIN, 14));
		checkBox_5.setBounds(96, 22, 88, 23);
		panel_65.add(checkBox_5);
		
		JLabel label_95 = new JLabel("累计总数：");
		label_95.setFont(new Font("宋体", Font.PLAIN, 14));
		label_95.setBounds(6, 74, 70, 15);
		panel_65.add(label_95);
		
		JLabel label_96 = new JLabel("成功总数：");
		label_96.setFont(new Font("宋体", Font.PLAIN, 14));
		label_96.setBounds(6, 99, 70, 15);
		panel_65.add(label_96);
		
		JLabel label_97 = new JLabel("失败总数：");
		label_97.setFont(new Font("宋体", Font.PLAIN, 14));
		label_97.setBounds(6, 124, 70, 15);
		panel_65.add(label_97);
		
		recordLabel_count = new JLabel("");
		recordLabel_count.setFont(new Font("宋体", Font.PLAIN, 14));
		recordLabel_count.setBounds(69, 74, 113, 15);
		panel_65.add(recordLabel_count);
		
		recordLabel_success = new JLabel("");
		recordLabel_success.setFont(new Font("宋体", Font.PLAIN, 14));
		recordLabel_success.setBounds(69, 99, 113, 15);
		panel_65.add(recordLabel_success);
		
		recordLabel_fail = new JLabel("");
		recordLabel_fail.setFont(new Font("宋体", Font.PLAIN, 14));
		recordLabel_fail.setBounds(69, 124, 113, 15);
		panel_65.add(recordLabel_fail);
		
		JLabel lbllogs = new JLabel("<html>失败信息日志在logs文件夹下</html>");
		lbllogs.setHorizontalAlignment(SwingConstants.LEFT);
		lbllogs.setFont(new Font("宋体", Font.PLAIN, 14));
		lbllogs.setBounds(6, 524, 199, 21);
		panel_19.add(lbllogs);
		
		
		
		JPanel panel_64 = new JPanel();
		panel_64.setBounds(524, 10, 227, 552);
		panel_14.add(panel_64);
		panel_64.setBorder(BorderFactory.createTitledBorder("连接后台测试"));
		panel_64.setLayout(null);
		
		JLabel lblip_2 = new JLabel("数据库IP：");
		lblip_2.setFont(new Font("宋体", Font.PLAIN, 14));
		lblip_2.setBounds(10, 60, 77, 15);
		panel_64.add(lblip_2);
		
		dbServerIPJTextField = new JTextField();
		dbServerIPJTextField.setText("172.168.120.224");
		dbServerIPJTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dbServerIPJTextField.setColumns(10);
		dbServerIPJTextField.setBounds(76, 56, 130, 21);
		panel_64.add(dbServerIPJTextField);
		
		JLabel label_89 = new JLabel("登 录 名：");
		label_89.setFont(new Font("宋体", Font.PLAIN, 14));
		label_89.setBounds(10, 90, 77, 15);
		panel_64.add(label_89);
		
		dbUserNameJTextField = new JTextField();
		dbUserNameJTextField.setText("sa");
		dbUserNameJTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dbUserNameJTextField.setColumns(10);
		dbUserNameJTextField.setBounds(76, 87, 130, 21);
		panel_64.add(dbUserNameJTextField);
		
		JLabel label_90 = new JLabel("密    码：");
		label_90.setFont(new Font("宋体", Font.PLAIN, 14));
		label_90.setBounds(10, 120, 77, 15);
		panel_64.add(label_90);
		
		dbPasswordJTextField = new JTextField();
		dbPasswordJTextField.setText("123");
		dbPasswordJTextField.setFont(new Font("宋体", Font.PLAIN, 14));
		dbPasswordJTextField.setColumns(10);
		dbPasswordJTextField.setBounds(76, 117, 130, 21);
		panel_64.add(dbPasswordJTextField);
		
		JLabel label_91 = new JLabel("数 据 库：");
		label_91.setFont(new Font("宋体", Font.PLAIN, 14));
		label_91.setBounds(10, 150, 77, 15);
		panel_64.add(label_91);
		
		JButton button_18 = new JButton("浏览账套");
		button_18.setFont(new Font("宋体", Font.PLAIN, 14));
		button_18.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DbServerTest dbServerTest = DbServerTest.getInstance();
				dbServerTest.getDBname();
				dbServerTest.updatecomboBox();
				button_19.setEnabled(true);
			}
		});
		button_18.setBounds(76, 178, 130, 23);
		panel_64.add(button_18);
		
		dbNameComboBox = new JComboBox<String>();
		dbNameComboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		dbNameComboBox.setBounds(76, 147, 130, 21);
		dbNameComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
//					DbServerTest dbServerTest = 
					DbServerTest.getInstance();
				}
			}
		});
		
		panel_64.add(dbNameComboBox);
		dbSQLRadioButton = new JRadioButton("SQL");
		dbSQLRadioButton.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (dbSQLRadioButton.isSelected()) {
					label_91.setVisible(true);
					dbNameComboBox.setVisible(true);
					button_18.setVisible(true);
					button_18.setText("浏览账套");
					lblip_2.setText("数据库IP：");
				}
			}
		});
		dbSQLRadioButton.setFont(new Font("宋体", Font.PLAIN, 14));
		dbSQLRadioButton.setBounds(10, 29, 47, 23);
		dbSQLRadioButton.setSelected(true);
		panel_64.add(dbSQLRadioButton);
		
		dbOracleRadioButton = new JRadioButton("Oracle");
		dbOracleRadioButton.setFont(new Font("宋体", Font.PLAIN, 14));
		dbOracleRadioButton.setBounds(67, 29, 67, 23);
		dbOracleRadioButton.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (dbOracleRadioButton.isSelected()) {
					label_91.setVisible(false);
					dbNameComboBox.setVisible(false);
					button_18.setText("链接测试");
					lblip_2.setText("数据库名：");
				}
			}
		});
		panel_64.add(dbOracleRadioButton);
		
		dbSQLite3RadioButton = new JRadioButton("SQLite3");
		dbSQLite3RadioButton.setFont(new Font("宋体", Font.PLAIN, 14));
		dbSQLite3RadioButton.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				
				if (dbSQLite3RadioButton.isSelected()) {
					label_91.setVisible(false);
					dbNameComboBox.setVisible(false);
					button_18.setVisible(false);
					dbServerIPJTextField.setEditable(false);
					dbUserNameJTextField.setEditable(false);
					dbPasswordJTextField.setEditable(false);
				}else {
					dbServerIPJTextField.setEditable(true);
					dbUserNameJTextField.setEditable(true);
					dbPasswordJTextField.setEditable(true);
				}
			}
		});
//		dbSQLite3RadioButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				if (dbSQLRadioButton.isSelected()) {
//					label_91.setVisible(false);
//					dbNameComboBox.setVisible(false);
//					button_18.setVisible(false);
//					dbServerIPJTextField.setEditable(false);
//					dbUserNameJTextField.setEditable(false);
//					dbPasswordJTextField.setEditable(false);
//				}else {
//					dbServerIPJTextField.setEditable(true);
//					dbUserNameJTextField.setEditable(true);
//					dbPasswordJTextField.setEditable(true);
//				}
//			}
//		});
		dbSQLite3RadioButton.setBounds(144, 29, 77, 23);
		panel_64.add(dbSQLite3RadioButton);
		
		ButtonGroup bg_db = new ButtonGroup();
		bg_db.add(dbSQLite3RadioButton);
		bg_db.add(dbOracleRadioButton);
		bg_db.add(dbSQLRadioButton);
		
		button_19 = new JButton("对比设备人脸权限");
		button_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取设备所有权限
				if(DbServerTest.getInstance().getConn() != null) {
					new Thread(() ->{
						String macAddr = faceDevchoiseComboBox.getSelectedItem().toString();
						RequestGson<String> requestGson = new RequestGson<String>();
						requestGson.setData(null);
						requestGson.setTimeStamp(SystemTimes.getSysTime1());
						requestGson.setDeviceUniqueCode(macAddr);
						
						new GetAllPerson(new GsonBuilder().serializeNulls().create()
								.toJson(requestGson, new TypeToken<RequestGson<String>>() {}.getType()), 
								macAddr);
					})
					.start();
				}
			}
		});
		button_19.setFont(new Font("宋体", Font.PLAIN, 14));
		button_19.setBounds(10, 211, 196, 23);
		panel_64.add(button_19);
		
		JPanel panel_63 = new JPanel();
		panel_63.setBounds(10, 244, 211, 177);
		panel_64.add(panel_63);
		panel_63.setLayout(null);
		panel_63.setBorder(BorderFactory.createTitledBorder("模拟生成在线消费数据"));
		
		JButton button_16 = new JButton("生成在线消费数据");
		button_16.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				new TestParamInit(serverIPJTextField, serverProtJTextField, devPortJTextField, devNoJTextField, systemIDJTextField)
				TestParamInit.getInstance()
				.doGetOnlinePayData();
			}
		});
		button_16.setEnabled(false);
		button_16.setFont(new Font("宋体", Font.PLAIN, 14));
		button_16.setBounds(10, 52, 176, 23);
		panel_63.add(button_16);
		
		JButton button_17 = new JButton("生成离线消费数据");
		button_17.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				new TestParamInit(serverIPJTextField, serverProtJTextField, devPortJTextField, devNoJTextField, systemIDJTextField)
				TestParamInit.getInstance()
				.doGetOffLinePayData();
			}
		});
		button_17.setEnabled(false);
		button_17.setFont(new Font("宋体", Font.PLAIN, 14));
		button_17.setBounds(10, 82, 176, 23);
		panel_63.add(button_17);
		
		
		JButton btnNewButton_3 = new JButton("获取密钥");
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton_3.setBounds(10, 22, 176, 23);
		btnNewButton_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				new TestParamInit(serverIPJTextField, serverProtJTextField, devPortJTextField, devNoJTextField, systemIDJTextField).doGetSecret();
				TestParamInit.getInstance().doGetSecret();
				if (TestParamInit.isSetButtonEnabled()) {

					button_16.setEnabled(true);
					button_17.setEnabled(true);
				}
			}
		});
		panel_63.add(btnNewButton_3);
		
		JLabel lblid_1 = new JLabel("<html>1、生成的压测数据至少2万条；2、只生成验证方式为人脸的数据；3、必须系统编号；否则报错</html>");
		lblid_1.setBounds(10, 115, 191, 53);
		panel_63.add(lblid_1);
		lblid_1.setFont(new Font("宋体", Font.PLAIN, 13));
		
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("识别率测试", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_43 = new JPanel();
		panel_2.add(panel_43, BorderLayout.CENTER);
		panel_43.setLayout(new BorderLayout(5, 5));
		
		JPanel panel_45 = new JPanel();
		panel_43.add(panel_45, BorderLayout.EAST);
		panel_45.setPreferredSize(new Dimension(150, 100));
		panel_45.setLayout(null);
		
		JCheckBox checkBox_3 = new JCheckBox("不新建人员");
		checkBox_3.setSelected(true);
		checkBox_3.setBounds(10, 69, 144, 26);
		checkBox_3.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_45.add(checkBox_3);
		
		JCheckBox checkBox_1 = new JCheckBox("不下载权限");
		checkBox_1.setSelected(true);
		checkBox_1.setBounds(10, 99, 144, 26);
		checkBox_1.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_45.add(checkBox_1);
		
		JLabel label_98 = new JLabel("总    数：");
		label_98.setFont(new Font("宋体", Font.PLAIN, 14));
		label_98.setBounds(10, 129, 70, 15);
		panel_45.add(label_98);
		
		JLabel label_99 = new JLabel("当前进度：");
		label_99.setFont(new Font("宋体", Font.PLAIN, 14));
		label_99.setBounds(10, 159, 70, 15);
		panel_45.add(label_99);
		
		JLabel label_100 = new JLabel("识 别 率：");
		label_100.setFont(new Font("宋体", Font.PLAIN, 14));
		label_100.setBounds(10, 189, 70, 15);
		panel_45.add(label_100);
		
		JLabel label_102 = new JLabel("误 识 率：");
		label_102.setFont(new Font("宋体", Font.PLAIN, 14));
		label_102.setBounds(10, 219, 75, 15);
		panel_45.add(label_102);
		
		rrtLabel_progress = new JLabel("");
		rrtLabel_progress.setFont(new Font("宋体", Font.PLAIN, 14));
		rrtLabel_progress.setBounds(74, 159, 66, 15);
		panel_45.add(rrtLabel_progress);
		
		rrtLabel_recognitionRate = new JLabel("");
		rrtLabel_recognitionRate.setFont(new Font("宋体", Font.PLAIN, 14));
		rrtLabel_recognitionRate.setBounds(74, 189, 66, 15);
		panel_45.add(rrtLabel_recognitionRate);
		
		rrtLabel_errorRate = new JLabel("");
		rrtLabel_errorRate.setFont(new Font("宋体", Font.PLAIN, 14));
		rrtLabel_errorRate.setBounds(74, 219, 66, 15);
		panel_45.add(rrtLabel_errorRate);
		
		rrtLabel_total = new JLabel("");
		rrtLabel_total.setFont(new Font("宋体", Font.PLAIN, 14));
		rrtLabel_total.setBounds(74, 129, 66, 15);
		panel_45.add(rrtLabel_total);
		
		rrtLabel_logs = new JLabel("");
		rrtLabel_logs.setFont(new Font("宋体", Font.PLAIN, 14));
		rrtLabel_logs.setBounds(10, 274, 66, 15);
		panel_45.add(rrtLabel_logs);
		
		JButton btnNewButton_1 = new JButton("开始比对");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				splitPane_1.setResizeWeight(0.1);
//				contentPane.repaint();
				if (RecognitonRateTest.getTimer() != null) {
					RecognitonRateTest.getTimer().cancel();
					rrtLabel_photo.setIcon(null);
					rrtLabel_photo.setText(EXPLAIN);
				}
				
				rrtLabel_photo.setText("");
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						new RecognitonRateTest(checkBox_3, checkBox_1, faceDevchoiseComboBox).doRecognitonRateTest();
					}
				}).start();
			}
		});
		btnNewButton_1.setBounds(10, 40, 118, 26);
		panel_45.add(btnNewButton_1);
		
		JButton button_21 = new JButton("中断对比");
		button_21.addActionListener((e)->{
			if (RecognitonRateTest.getTimer() != null) {
				RecognitonRateTest.getTimer().cancel();
				rrtLabel_photo.setIcon(null);
				rrtLabel_photo.setText(EXPLAIN);
			}
		});
		button_21.setFont(new Font("宋体", Font.PLAIN, 14));
		button_21.setBounds(10, 10, 118, 26);
		panel_45.add(button_21);
		
		JPanel panel_46 = new JPanel();
		panel_46.setBorder(BorderFactory.createTitledBorder("照片预览框"));
		panel_43.add(panel_46, BorderLayout.CENTER);
		panel_46.setLayout(new BorderLayout(0, 0));
		
		rrtLabel_photo = new JLabel(EXPLAIN);
		rrtLabel_photo.setToolTipText(EXPLAIN);
		rrtLabel_photo.setHorizontalAlignment(SwingConstants.CENTER);
		rrtLabel_photo.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_46.add(rrtLabel_photo, BorderLayout.CENTER);
		
		testTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("厂测模块", null, testTabbedPane, null);
		
		test343Panel = new JPanel();
		testTabbedPane.addTab("343、354设备", null, test343Panel, null);
		test343Panel.setLayout(null);
		
		JPanel panel_72 = new JPanel();
		panel_72.setBorder(BorderFactory.createTitledBorder("设备信息"));
		panel_72.setBounds(10, 42, 913, 134);
		test343Panel.add(panel_72);
		panel_72.setLayout(new BorderLayout(0, 0));
		
		factoryTestTable = new JTable();
		factoryTestTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BBE\u5907IP", "\u7269\u7406\u5730\u5740", "\u8BBE\u5907ID", "\u7528\u6237\u540D", "\u5BC6\u7801", "\u8F6F\u4EF6\u7248\u672C", "\u786C\u4EF6\u7248\u672C", "\u6700\u8FD1\u66F4\u65B0\u65E5\u671F", "Http\u7AEF\u53E3"
			}
		));
		factoryTestTable.setFont(new Font("宋体", Font.PLAIN, 13));
		panel_72.add(new JScrollPane(factoryTestTable), BorderLayout.CENTER);
		
		JPanel panel_44 = new JPanel();
		panel_44.setBorder(BorderFactory.createTitledBorder("厂测流程及测试项"));
		panel_44.setBounds(932, 10, 302, 592);
		test343Panel.add(panel_44);
		panel_44.setLayout(null);
		
		JLabel label_106 = new JLabel("<html>\r\n</t>（前期准备工作：选中5人照片（含检测人员）、门禁控制器；人脸设备维根输出接线在控制器读头上）<br><br>\r\n</t>1、检测设备外观是否有划痕，面板是否有贴膜，设备是否为二手或翻新；测试时电源不可复用，即必须使用包装盒内的电源<br><br>\r\n2、插上网线，自动搜索设备成功，则设备网络正常；<br><br>\r\n3、调用视频流，检测摄像头是否正常（开发中，现阶段可识别则摄像头正常！）；<br><br>\r\n4、调用LED接口：常亮5秒后关闭，检测LED灯是否正常（开发中，SDK不支持）；设备出厂状态上电补光灯为常亮状态，灯亮则正常<br><br>\r\n5、自动设置设备参数（已关闭活体），等待设备上线；<br><br>\r\n6、设备上线后，软件自动下载人脸权限、及门禁权限；<br><br>\r\n7、设备正对电脑屏幕，软件弹出识别图片连续识别5次，5条记录上传成功则失败功能、记录上传正常，控制器上报卡号于人脸设备一直则维根输出正常；<br><br>\r\n8、开启活体，将设备正对检查人员，进行识别，有记录上报则红外摄像头、活体功能正常；<br><br>\r\n9、测试完成，调用恢复出厂设置，清除测试数据：权限、通行记录、设备参数等；<br><br>\r\n</html>");
		label_106.setVerticalAlignment(SwingConstants.TOP);
		label_106.setFont(new Font("宋体", Font.PLAIN, 14));
		label_106.setBounds(10, 26, 282, 566);
		panel_44.add(label_106);
		
		JPanel panel_69 = new JPanel();
		panel_69.setBounds(10, 186, 913, 416);
		test343Panel.add(panel_69);
		panel_69.setLayout(new BorderLayout(0, 0));
		
		testLogsJlabel = new JLabel("");
		testLogsJlabel.setHorizontalAlignment(SwingConstants.CENTER);
		testLogsJlabel.setFont(new Font("宋体", Font.PLAIN, 25));
		panel_69.add(testLogsJlabel, BorderLayout.CENTER);
		
		JPanel panel_70 = new JPanel();
		panel_70.setBorder(BorderFactory.createTitledBorder("手动点击测试界面"));
		panel_69.add(panel_70, BorderLayout.WEST);
		panel_70.setLayout(new GridLayout(10, 1, 10, 15));
		
		JButton button_23 = new JButton("1、搜索设备");
		button_23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(()-> {
					factoryTest.searchDeviceAndUpdateTable();
				}).start();
			}
		});
		button_23.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_70.add(button_23);
		
		JButton btnNewButton_9 = new JButton("2、设置参数");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				factoryTest.getTestProcedure(FactoryTest.TestProcedure.SET_DEVICE_PARAM);
			}
		});
		btnNewButton_9.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_70.add(btnNewButton_9);
		
		JButton button_24 = new JButton("3、权限下载");
		button_24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				factoryTest.getTestProcedure(FactoryTest.TestProcedure.DOWNLOAD_AUTHORITY);
			}
		});
		button_24.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_70.add(button_24);
		
		JButton button_32 = new JButton("4、图片识别");
		button_32.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(()-> {
					if (null == FactoryTest.getFileArray()) {
						FactoryTest.setFileArray(new File(RecognitonRateTest.CONTRASTPHOTO_B).listFiles());
					}
					factoryTest.getTestProcedure(FactoryTest.TestProcedure.PTICTURE_RECONGNITION);
				}).start();
				
			}
		});
		button_32.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_70.add(button_32);
		
		JButton button_33 = new JButton("5、活体识别");
		button_33.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				factoryTest.getTestProcedure(FactoryTest.TestProcedure.SET_DEVICE_PARAM_TO_ALIVE);
				factoryTest.getTestProcedure(FactoryTest.TestProcedure.ALIVE_PECONGITION);
			}
		});
		button_33.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_70.add(button_33);
		
		JButton button_34 = new JButton("6、清除数据");
		button_34.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//清楚权限
				factoryTest.getTestProcedure(FactoryTest.TestProcedure.CLEAN_AUTHORITIY);
				//恢复出厂设置、注销登陆
				factoryTest.getTestProcedure(FactoryTest.TestProcedure.RETURN_TO_FACTORY);
			}
		});
		button_34.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_70.add(button_34);
		
		JPanel panel_74 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_74.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_69.add(panel_74, BorderLayout.SOUTH);
		
		
		
		JLabel label_108 = new JLabel("    用户名：");
		label_108.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_74.add(label_108);
		
		txtAdmin = new JTextField();
		txtAdmin.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtAdmin.setText("admin");
		panel_74.add(txtAdmin);
		txtAdmin.setColumns(10);
		
		JLabel label_109 = new JLabel("    登录密码:");
		label_109.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_74.add(label_109);
		
		txtAdmin_1 = new JTextField();
		txtAdmin_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtAdmin_1.setText("admin");
		panel_74.add(txtAdmin_1);
		txtAdmin_1.setColumns(10);
		JButton button_22 = new JButton("搜索设备入库");
		button_22.addActionListener(new ActionListener() {
			private List<DeviceInfo> list;

			public void actionPerformed(ActionEvent e) {
				new Thread(() -> {
					list = new FactoryTest().searchAndInsertIntoDb(txtAdmin.getText().trim().replaceAll(" ", ""), txtAdmin_1.getText().trim().replaceAll(" ", ""));
					if (list != null || !list.isEmpty()) {
						button_22.setVisible(true);
					}
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
					@Override
					public void run() {
						button_22.setVisible(true);
					}
					}, 1000,30000);
				}).start();
				button_22.setVisible(false);
			}
		});
		button_22.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_74.add(button_22);
		JPanel panel_73 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_73.getLayout();
		flowLayout_1.setHgap(15);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_73.setBounds(10, 0, 913, 49);
		test343Panel.add(panel_73);
		
		JButton factoryTestBeginButton = new JButton("开始测试");
		JButton factoryTestBreakButton = new JButton("中断测试");
		factoryTestBeginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FactoryTest.setSetBreak(false);
				factoryTestBeginButton.setVisible(false);
				factoryTestBreakButton.setVisible(true);
				new Thread(()-> {
					new FactoryTest(factoryTestTable).bigenTest343();
				}).start(); 
			}
		});
		
		panel_73.add(factoryTestBeginButton);
		factoryTestBeginButton.setFont(new Font("宋体", Font.PLAIN, 14));
		
		factoryTestBreakButton.setVisible(false);
		factoryTestBreakButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				factoryTestBeginButton.setVisible(true);
				factoryTestBreakButton.setVisible(false);
				FactoryTest.setSetBreak(true);
			}
		});
		panel_73.add(factoryTestBreakButton);
		factoryTestBreakButton.setFont(new Font("宋体", Font.PLAIN, 14));
		
		jvtSearchDeviceJlabel = new JLabel("<html>点击开始测试，会自动循环测试，有问题时，请手动测试;<br>注意：本模块禁止调试设备用，会自动将设备初始化</html>");
		panel_73.add(jvtSearchDeviceJlabel);
		jvtSearchDeviceJlabel.setFont(new Font("宋体", Font.PLAIN, 14));
		test361Panel = new JPanel();
		testTabbedPane.addTab("361", null, test361Panel, null);
		
		JPanel panel_71 = new JPanel();
		testTabbedPane.addTab("持续更新中***", null, panel_71, null);
		
		testPhotoPanel = new JPanel();
		testTabbedPane.addTab("照片", null, testPhotoPanel, null);
		testPhotoPanel.setLayout(new BorderLayout(0, 0));
		
		testphotoLabel = new JLabel("");
		testphotoLabel.setVerticalAlignment(SwingConstants.TOP);
		testphotoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		testPhotoPanel.add(testphotoLabel);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("\u7CFB\u7EDF\u8BBE\u7F6E", null, panel_3, null);
		panel_3.setLayout(null);
		
		JButton btnNewButton_5 = new JButton("一键清空数据库");
		btnNewButton_5.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataBaseExecute.getInstance().cleanTable("com_dev");
				DataBaseExecute.getInstance().cleanTable("pearson_info");
				DataBaseExecute.getInstance().cleanTable("face_dev_parameter");
				DataBaseExecute.getInstance().cleanTable("face_dev_record");
				DataBaseExecute.getInstance().cleanTable("face_photo");
				DataBaseExecute.getInstance().cleanTable("face_feature");
				DataBaseExecute.getInstance().cleanTable("face_dev_author_set");
				
				//face_emp_photo
				updateComboBox(faceDevComboBox, new ArrayList<String>());
				updateComboBox(faceDevchoiseComboBox, new ArrayList<String>()); 
				MainIntfaceView.getDevInfo().clear();
				DownLoadAuthorityStatu.getDownPersonInfo().clear();
				
				//清空图表
				new UpdateTable().cleanTable(personInfoTable);
				getDevInfo().clear();
			}
		});
		btnNewButton_5.setBounds(10, 10, 151, 23);
		panel_3.add(btnNewButton_5);
		
		JButton button_11 = new JButton("压缩数据库");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataBaseExecute.getInstance().getVacuum();
			}
		});
		button_11.setFont(new Font("宋体", Font.PLAIN, 14));
		button_11.setBounds(171, 10, 120, 23);
		panel_3.add(button_11);
		
		JPanel panel_66 = new JPanel();
		panel_66.setBorder(BorderFactory.createTitledBorder("通信参数设置"));
		panel_66.setBounds(10, 49, 250, 159);
		panel_3.add(panel_66);
		panel_66.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_67 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_67.getLayout();
		flowLayout_3.setVgap(0);
		flowLayout_3.setHgap(0);
		panel_66.add(panel_67, BorderLayout.NORTH);
		
		JPanel panel_68 = new JPanel();
		panel_66.add(panel_68, BorderLayout.CENTER);
		sorkectCardLayout = new CardLayout();
		panel_68.setLayout(sorkectCardLayout);
		
		JPanel httpPanel = new JPanel();
		httpPanel.setBorder(BorderFactory.createTitledBorder("修改http端口："));
		panel_68.add(httpPanel, "http");
		httpPanel.setLayout(null);
		
		JLabel lblip_4 = new JLabel("服务端口号：");
		lblip_4.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblip_4.setBounds(6, 24, 116, 15);
		httpPanel.add(lblip_4);
		serverPortTextField = new JTextField();
		serverPortTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
		serverPortTextField.setBounds(92, 20, 140, 21);
		httpPanel.add(serverPortTextField);
		serverPortTextField.setColumns(10);
		
		JButton button_15 = new JButton("修改");
		button_15.setBounds(156, 80, 76, 25);
		httpPanel.add(button_15);
		button_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = serverPortTextField.getText().replaceAll(" ", "");
				Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
		        if ( pattern.matcher(str).matches()) {
		        	Http.getHttpserver().shutdown();
					int port = Integer.parseInt(str);
//					if (port == 0) {
//						port = Http.getPort();
//					}
					
					port = (port == 0)? Http.getPort():port;
					
					DataBaseExecute.getInstance().updateDB("system_parameter", Arrays.asList("server_prot"), Arrays.asList(Integer.toString(port)), 
							Arrays.asList("server_prot", "gmt_modified"), Arrays.asList(str, SystemTimes.getSysTime()));
					Http.setPort(port);
					Http.setServerIP(serverIPComboBox.getSelectedItem().toString());
					http = new Http();
		        }else {
		        	JOptionPane.showMessageDialog(null, "端口号为纯数值");
		        	serverPortTextField.setText("");
		        }
			}
		});
		button_15.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JLabel label_107 = new JLabel("服 务 端  IP：");
		label_107.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_107.setBounds(6, 53, 116, 15);
		httpPanel.add(label_107);
		
		serverIPComboBox = new JComboBox<String>();
		serverIPComboBox.setBounds(92, 49, 140, 21);
		httpPanel.add(serverIPComboBox);
		
		JPanel mqttPanel = new JPanel();
		mqttPanel.setBorder(BorderFactory.createTitledBorder("MQTT连接参数："));
		panel_68.add(mqttPanel, "mqtt");
		mqttPanel.setLayout(null);
		
		JLabel label_104 = new JLabel("服务器地址：");
		label_104.setBounds(10, 20, 84, 16);
		label_104.setFont(new Font("宋体", Font.PLAIN, 14));
		mqttPanel.add(label_104);
		
		mqttServerIPTextField = new JTextField();
		mqttServerIPTextField.setBounds(88, 18, 105, 21);
		mqttPanel.add(mqttServerIPTextField);
		mqttServerIPTextField.setColumns(10);
		
		JLabel label_105 = new JLabel("端  口  号：");
		label_105.setFont(new Font("宋体", Font.PLAIN, 14));
		label_105.setBounds(10, 48, 84, 16);
		mqttPanel.add(label_105);
		
		mqttServerPortTextField = new JTextField();
		mqttServerPortTextField.setColumns(10);
		mqttServerPortTextField.setBounds(88, 46, 105, 21);
		mqttPanel.add(mqttServerPortTextField);
		
		JLabel label_101 = new JLabel("通信方式：");
		label_101.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_67.add(label_101);
		
		rdbtnHttp = new JRadioButton("HTTP");
		rdbtnHttp.addItemListener((e) -> {
			MySocketChannel.setSelectSocketType(MySocketChannel.HTTP);
			sorkectCardLayout.show(panel_68, "http");
		});
		rdbtnHttp.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_67.add(rdbtnHttp);
		
		rdbtnMqtt = new JRadioButton("MQTT");
		rdbtnMqtt.setEnabled(false);
		rdbtnMqtt.addItemListener((e) -> {
			MySocketChannel.setSelectSocketType(MySocketChannel.MQTT);
			sorkectCardLayout.show(panel_68, "mqtt");
		});
		rdbtnMqtt.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_67.add(rdbtnMqtt);
		
		ButtonGroup sorcketButton = new ButtonGroup();
		sorcketButton.add(rdbtnHttp);
		sorcketButton.add(rdbtnMqtt);
		
		JButton button_35 = new JButton("清空人员信息");
		button_35.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataBaseExecute.getInstance().cleanTable("pearson_info");
				DataBaseExecute.getInstance().cleanTable("face_photo");
				DataBaseExecute.getInstance().cleanTable("face_feature");
				//清空图表
				new UpdateTable().cleanTable(personInfoTable);
			}
		});
		button_35.setFont(new Font("宋体", Font.PLAIN, 14));
		button_35.setBounds(301, 10, 120, 23);
		panel_3.add(button_35);
		
		JButton button_36 = new JButton("清空设备表");
		button_36.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DataBaseExecute.getInstance().cleanTable("com_dev");
				DataBaseExecute.getInstance().cleanTable("face_dev_parameter");
				getDevInfo().clear();
			}
		});
		button_36.setFont(new Font("宋体", Font.PLAIN, 14));
		button_36.setBounds(431, 10, 120, 23);
		panel_3.add(button_36);
		
		JButton button_37 = new JButton("清空权限表");
		button_37.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataBaseExecute.getInstance().cleanTable("face_dev_author_set");
			}
		});
		button_37.setFont(new Font("宋体", Font.PLAIN, 14));
		button_37.setBounds(561, 10, 120, 23);
		panel_3.add(button_37);
		
		JPanel panel_42 = new JPanel();
		tabbedPane.addTab("日志", null, panel_42, null);
		panel_42.setLayout(new BorderLayout(0, 0));
		
		logTextArea = new JTextArea();
		logTextArea.setLineWrap(true);
		logTextArea.setEditable(false);
		logTextArea.setLineWrap(true);
//		longTextArea.setWrapStyleWord(true);
		panel_42.add(new JScrollPane(logTextArea), BorderLayout.CENTER);
	}
	
	public static void updateParameterVeiw(DeviceParameterGson dpg) {
		
		//基本参数
		
		basicParameterDevMacTextField.setText(dpg.getBasicParams().getDeviceUniqueCode());
		basicParameterDevNameTextField.setText(dpg.getBasicParams().getDeviceName());
		basicParameterDevIpTextField.setText(dpg.getBasicParams().getDeviceIP());
		basicParameterDevServerIpTextField.setText(dpg.getBasicParams().getServerIP());
		basicParameterDevServerPortTextField.setText(dpg.getBasicParams().getServerPort() + "");
		basicParameterHeartBeatTextField.setText(Integer.toString(dpg.getBasicParams().getHeartBeatInterval()));
		if (dpg.getBasicParams().getDeviceType() >= 5) {
			basicParameterDevTypeComboBox.setSelectedIndex(4);
		}else {
			basicParameterDevTypeComboBox.setSelectedIndex(dpg.getBasicParams().getDeviceType() -1 );
		}
		basicParameterSystemNoTextField.setText(dpg.getBasicParams().getSystemID());
		basicParameterFeatureTypeTextField.setText(Integer.toString(dpg.getFeatureParams().getFeatureType()));
		basicParameterFeatureSdkTextField.setText(dpg.getFeatureParams().getFeatureSDKValue());
		basicParameterFeatureVersionTextField.setText(dpg.getFeatureParams().getFeatureVersion());
		basicParameterVersionTextField.setText(dpg.getAppParams().getAppVersion());
			
			
			//输出设置
			devOutRelayTimeTextField.setText(Integer.toString(dpg.getBasicParams().getRelayTime()));
			
			if	(dpg.getBasicParams().getWiegandIn() == 0 || dpg.getBasicParams().getWiegandOut() == 0) {
				devOutWeigand_In_ComboBox.setSelectedIndex(0);
				devOutWeigand_Out_ComboBox.setSelectedIndex(0);
			}else {
				if (dpg.getBasicParams().getWiegandType() == 34) {
					if( dpg.getBasicParams().getWiegandIn() == 1) {
						devOutWeigand_In_ComboBox.setSelectedIndex(3);
					}else {
						devOutWeigand_In_ComboBox.setSelectedIndex(4);
					}
					
					if( dpg.getBasicParams().getWiegandOut() == 1) {
						devOutWeigand_Out_ComboBox.setSelectedIndex(3);
					}else {
						devOutWeigand_Out_ComboBox.setSelectedIndex(4);
					}
				}else {
					if( dpg.getBasicParams().getWiegandIn() == 1) {
						devOutWeigand_In_ComboBox.setSelectedIndex(1);
					}else {
						devOutWeigand_In_ComboBox.setSelectedIndex(2);
					}
					
					if( dpg.getBasicParams().getWiegandOut() == 1) {
						devOutWeigand_Out_ComboBox.setSelectedIndex(1);
					}else {
						devOutWeigand_Out_ComboBox.setSelectedIndex(2);
					}
				}
			}
			//二维码支持
			switch (dpg.getBasicParams().getQrCodeSwitch()) {
			case 0:
				devOutQrCode_No_RadioButton.setSelected(true);
				break;
			case 1:
				devOutQrCode_Open_RadioButton.setSelected(true);
				break;
			case 2:
				devOutQrCode_Close_RadioButton.setSelected(true);
				break;

			default:
				break;
			}
			
			switch (dpg.getBasicParams().getIsSupportCard()) {
			case 0:
				devOutCard_No_RadioButton.setSelected(true);
				break;
			case 1:
				devOutCard_Open_RadioButton.setSelected(true);
				break;
			case 2:
				devOutCard_Close_RadioButton.setSelected(true);
				break;

			default:
				break;
			}
			
			//设备参数设置
			appParameterPasswordTextField.setText(dpg.getAppParams().getAppPassword());
			appParameterAutoRestartTextField.setText(dpg.getBasicParams().getDailyRestartTime());
			if (dpg.getBasicParams().getIsAutoRestart() == 1) {
				appParameterAutoRestart_Yes__RadioButton.setSelected(true);
			}else {
				appParameterAutoRestart_No__RadioButton.setSelected(true);
			}
			if (dpg.getBasicParams().getIsUploadPassImg() == 1) {
				appParameterUploadPassImg_Yes_RadioButton.setSelected(true);
			}else {
				appParameterUploadPassImg_Yes_RadioButton.setSelected(true);
			}
//			appParameterAutoRestartTextField.setText(dpg.getBasicParams().getDailyRestartTime());
//			if (dpg.getBasicParams().getIsAutoRestart() == 1) {
//				appParameterAutoRestart_Yes__RadioButton.setSelected(true);
//			}else {
//				appParameterAutoRestart_No__RadioButton.setSelected(true);
//			}
//			if (dpg.getBasicParams().getIsUploadPassImg() == 1) {
//				appParameterUploadPassImg_Yes_RadioButton.setSelected(true);
//			}else {
//				appParameterUploadPassImg_Yes_RadioButton.setSelected(true);
//			}
			
			
			//UI设置
			uiTypeComboBox.setSelectedIndex(dpg.getBasicParams().getMainUIType());
			if (dpg.getBasicParams().getEnableScreenSaver() == 0) {
				uiScreanSave_No_RadioButton.setSelected(true);
			}else if (dpg.getBasicParams().getEnableScreenSaver()== 1) {
				uiScreanSave_Open_RadioButton.setSelected(true);
			}else {
				uiScreanSave_Close_RadioButton.setSelected(true);
			}
			//远程开门控制
			accessDevDoorPasswordTextField.setText(dpg.getBasicParams().getOpenDoorPassword());
		
			//设备参数设置
			if (dpg.getAppParams() == null) {
				appParameterPasswordTextField.setText("");
				basicParameterVersionTextField.setText("");
			}else {
				appParameterPasswordTextField.setText(dpg.getAppParams().getAppPassword());
				basicParameterVersionTextField.setText(dpg.getAppParams().getAppVersion());
			}
			
		
		
			/*
			 * 识别参数
			 */
			
			//识别间隔
			recognitionParameterTriggerActionIntervalTextField.setText(Integer.toString(dpg.getBasicParams().getTriggerActionInterval()));
			
			if (dpg.getRecognitionParams() == null ) {
				recognitionParameterALiveVomboBox.setSelectedItem("");
				recognitionParameterSimilityThresholdTextField.setText("");
				recognitionParameterQualityThresholdTextField.setText("");
				recognitionParameterLivingThresdhoidTextField.setText("");
				recognitionParameterMinFacePixelTextField.setText("");
				recognitionParameterMaxFacePixelTextField.setText("");
			}else {
				recognitionParameterALiveVomboBox.setSelectedIndex(dpg.getRecognitionParams().getIsAlive());
				recognitionParameterSimilityThresholdTextField.setText(dpg.getRecognitionParams().getSimilityThreshold());
				recognitionParameterQualityThresholdTextField.setText(dpg.getRecognitionParams().getQualityThreshold());
				if(null == dpg.getRecognitionParams().getLivingThreshold()) {
					recognitionParameterLivingThresdhoidTextField.setText("");
				}else {
					recognitionParameterLivingThresdhoidTextField.setText(Double.toString(dpg.getRecognitionParams().getLivingThreshold()));
				}
				recognitionParameterMinFacePixelTextField.setText(Integer.toString(dpg.getRecognitionParams().getMinFacePixel()));
				recognitionParameterMaxFacePixelTextField.setText(Integer.toString(dpg.getRecognitionParams().getMaxFacePixel()));
			}
			
			//补光灯设置
			if (dpg.getHardWareParams() == null) {
				suppleLightModelcomboBox.setSelectedItem("");
				suppleLightOpenTimeTextField.setText("");

				appParameterDebugMode_Open_RadioButton.setSelected(false);
				appParameterDebugMode_Close_RadioButton.setSelected(false);
			}else {
				if (dpg.getHardWareParams().getDebugModeSwitch() == 1) {
					appParameterDebugMode_Open_RadioButton.setSelected(true);
				}else {
					appParameterDebugMode_Close_RadioButton.setSelected(true);
				}
				suppleLightModelcomboBox.setSelectedIndex(dpg.getHardWareParams().getSuppleLightMode());
				suppleLightOpenTimeTextField.setText(dpg.getHardWareParams().getSuppleLightOpenTime());
			}
			
			//相机
			if (dpg.getCameraParams() == null) {
				CameraModeComboBox.setSelectedItem("");
				CameraBeautyScoreTextField.setText("");
			}else {
				
				CameraModeComboBox.setSelectedIndex(dpg.getCameraParams().getCameraMode());
				CameraBeautyScoreTextField.setText(Integer.toString(dpg.getCameraParams().getBeautyScore()));
			}
			
			//语音提示
			if (dpg.getVoiceTipParams() == null) {
				voiceTipAttendanceTimeTextField_1.setText("");
				voiceTipRecognizeSuccessTextField.setText("");
				voiceTipRecognizeErrorTextField.setText("");
				voiceTipBeforeJobTextField.setText("");
				voiceTipAfterJobTextField.setText("");
			}else {
				voiceTipAttendanceTimeTextField_1.setText(dpg.getVoiceTipParams().getAttendanceTime());
				voiceTipRecognizeSuccessTextField.setText(dpg.getVoiceTipParams().getRecognizeSuccessTip());
				voiceTipRecognizeErrorTextField.setText(dpg.getVoiceTipParams().getRecognizeErrorTip());
				voiceTipBeforeJobTextField.setText(dpg.getVoiceTipParams().getBeforeJobTip());
				voiceTipAfterJobTextField.setText(dpg.getVoiceTipParams().getAfterJob());
			}
			
			//远程开门控制
			if (dpg.getAccessParams() == null) {
				accessDevIpTextField.setText("");
				accessDevMacTextField.setText("");
				accessUdpServerPortTextField.setText("");
				accessDevDoorNoComboBox.setSelectedItem("");
			}else {
				accessDevIpTextField.setText(dpg.getAccessParams().getAccessDeviceIP());
				accessDevMacTextField.setText(dpg.getAccessParams().getAccessDeviceUniqueCode());
				accessUdpServerPortTextField.setText(Integer.toString(dpg.getAccessParams().getUDPPort()));
				accessDevDoorNoComboBox.setSelectedIndex(dpg.getAccessParams().getAccessDoorID());
			}
			
			//消费参数
			if (dpg.getConsumeParams() == null) {
				consumePayUrlTextField.setText("");
				consumeQueryUrlTextField.setText("");
				consumeDevNoTextField.setText("");
				consumeSuccessInfoDurationTextField.setText("");
				consumeDisplayTitleTextField.setText("");
				consumeBreakfastTimeTextField.setText("");
				consumeLunchTimeTextField.setText("");
				consumeDinnerTimeTextField.setText("");
				consumeSupperTimeTextField.setText("");
				consumeOfflineMode_No_RadioButton.setSelected(false);
				consumeOfflineMode_Yes_RadioButton.setSelected(true);
				consumeOfflineMoneyLimitTextField.setText("");
				consumeOfflineNumberLimitTextField.setText("");
				consumeOfflineTimeLimitTextField.setText("");
//				consumeParameter.getInt("");
				consumeOnlineWalletID1TextField.setText("");
				consumeOnlineWalletId2TextField.setText("");
			}else {
				consumePayUrlTextField.setText(dpg.getConsumeParams().getPayUrl());
				consumeQueryUrlTextField.setText(dpg.getConsumeParams().getQueryUrl());
				consumeDevNoTextField.setText(dpg.getConsumeParams().getDeviceNum());
				consumeSuccessInfoDurationTextField.setText(Integer.toString(dpg.getConsumeParams().getSuccessInfoDuration()));
				consumeDisplayTitleTextField.setText(dpg.getConsumeParams().getDisplayTitle());
				consumeBreakfastTimeTextField.setText(dpg.getConsumeParams().getBreakfastTime());
				consumeLunchTimeTextField.setText(dpg.getConsumeParams().getLunchTime());
				consumeDinnerTimeTextField.setText(dpg.getConsumeParams().getDinnerTime());
				consumeSupperTimeTextField.setText(dpg.getConsumeParams().getSupperTime());
				if (dpg.getConsumeParams().getOfflineMode() == 0) {
					consumeOfflineMode_No_RadioButton.setSelected(true);
				}else {
					consumeOfflineMode_Yes_RadioButton.setSelected(true);
				}
				consumeOfflineMoneyLimitTextField.setText(Integer.toString(dpg.getConsumeParams().getOfflineMoneyLimit()));;
				consumeOfflineNumberLimitTextField.setText(Integer.toString(dpg.getConsumeParams().getOfflineNumberLimit()));
				consumeOfflineTimeLimitTextField.setText(Integer.toString(dpg.getConsumeParams().getOfflineTimeLimit()));
//				consumeParameter.getInt("CardSystemID");
				consumeOnlineWalletID1TextField.setText(Integer.toString(dpg.getConsumeParams().getOnlineWalletID1()));
				consumeOnlineWalletId2TextField.setText(Integer.toString(dpg.getConsumeParams().getOnlineWalletID2()));
			}
	}
	
	
	
//	public static HashMap<String, String> getDownPersonInfo() {
//		return downPersonInfo;
//	}

	public static void updataforUI(String DeviceHelloWord, String ImageList) {
		uiTextField.setText(DeviceHelloWord);
		uiChiosePictureButton.setIcon(MyPhoto.getImageIconfromBASE64(ImageList));
	}
	
	/**
	 * 接口：DeviceHeartBeat 新增设备信息
	 * 接口：GetDeviceParams 修改设备信息中的算法类型；当featureType为空时，移除设备信息
	 * @return HashMap<String, DevInfo>
	 */
	public static HashMap<String, DevInfo> getDevInfo() {
		return devInfoMap;
	}

	public static void setDevInfo(HashMap<String, DevInfo> devInfoMap) {
		MainIntfaceView.devInfoMap = devInfoMap;
	}

	private static void MyMouseListener(JButton button) {
		button.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				cardLayout.show(panel_22, "devParameter");
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});
	}
	
	public static JCheckBox getDownLoadcheckBox () {
		return downLoadcheckBox;
	}
	
	public static JTextArea getLongTextArea() {
		return logTextArea;
	}

	public static JLabel getCapturePhotoButton_1() {
		return capturePhotoButton_1;
	}

	public static JLabel getCapturePhotoButton_2() {
		return capturePhotoButton_2;
	}

	public static JLabel getCapturePhotoButton_3() {
		return capturePhotoButton_3;
	}

	public static JLabel getCapturePhotoButton_4() {
		return capturePhotoButton_4;
	}

	public static JLabel getCapturePhotoButton_5() {
		return capturePhotoButton_5;
	}

	public static JLabel getCapturePhuot_1() {
		return capturePhuot_1;
	}

	public static JLabel getCapturePhuot_2() {
		return capturePhuot_2;
	}

	public static JLabel getCapturePhuot_3() {
		return capturePhuot_3;
	}

	public static JLabel getCapturePhuot_4() {
		return capturePhuot_4;
	}

	public static JLabel getCapturePhuot_5() {
		return capturePhuot_5;
	}

	public static JLabel getCapturePhuot_6() {
		return capturePhuot_6;
	}

	public static JLabel getCapturePhuot_7() {
		return capturePhuot_7;
	}

	public static JLabel getCapturePhuot_8() {
		return capturePhuot_8;
	}

	public static JLabel getCapturePhuot_9() {
		return capturePhuot_9;
	}

	public static JLabel getCapturePhuot_10() {
		return capturePhuot_10;
	}

	public static JLabel getCapturePhuot_11() {
		return capturePhuot_11;
	}

	public static JLabel getCapturePhuot_12() {
		return capturePhuot_12;
	}

	public static JLabel getCaptureTime_1() {
		return captureTime_1;
	}

	public static JLabel getCaptureTime_2() {
		return captureTime_2;
	}

	public static JLabel getCaptureTime_3() {
		return captureTime_3;
	}

	public static JLabel getCaptureTime_4() {
		return captureTime_4;
	}

	public static JLabel getCaptureTime_5() {
		return captureTime_5;
	}

	public static JLabel getCaptureTime_6() {
		return captureTime_6;
	}

	public static JLabel getCaptureTime_7() {
		return captureTime_7;
	}

	public static JLabel getCaptureTime_8() {
		return captureTime_8;
	}

	public static JLabel getCaptureTime_9() {
		return captureTime_9;
	}

	public static JLabel getCaptureTime_10() {
		return captureTime_10;
	}

	public static JLabel getCaptureTime_11() {
		return captureTime_11;
	}

	public static JLabel getCaptureTime_12() {
		return captureTime_12;
	}
	
	
	
	

	public static JLabel getRecordLabel_count() {
		return recordLabel_count;
	}

	public static JLabel getRecordLabel_success() {
		return recordLabel_success;
	}

	public static JLabel getRecordLabel_fail() {
		return recordLabel_fail;
	}

	public static JLabel getCharacterLabel_count() {
		return CharacterLabel_count;
	}

	public static JLabel getCharacterLabel_success() {
		return CharacterLabel_success;
	}

	public static JLabel getCharacterLabel_fail() {
		return CharacterLabel_fail;
	}

	public static JLabel getRrtLabel_total() {
		return rrtLabel_total;
	}

	public static JLabel getRrtLabel_logs() {
		return rrtLabel_logs;
	}

	public static JLabel getRrtLabel_errorRate() {
		return rrtLabel_errorRate;
	}



	public static JLabel getRrtLabel_recognitionRate() {
		return rrtLabel_recognitionRate;
	}

	public static JLabel getRrtLabel_progress() {
		return rrtLabel_progress;
	}
	
	public static JLabel getRrtLaben_photo() {
		return rrtLabel_photo;
	}
	
	
	public static JComboBox<String> getFaceDevchoiseComboBox() {
		return faceDevchoiseComboBox;
	}

	public static JComboBox<String> getFaceDevComboBox() {
		return faceDevComboBox;
	}
	
	public static JTextField getMqttServerIPTextField() {
		return mqttServerIPTextField;
	}

	public static JTextField getMqttServerPortTextField() {
		return mqttServerPortTextField;
	}

	public static JRadioButton getRdbtnHttp() {
		return rdbtnHttp;
	}

	public static JRadioButton getRdbtnMqtt() {
		return rdbtnMqtt;
	}
	/**识别率测试说明*/
	public static String getExplain() {
		return EXPLAIN;
	}

	public static JLabel getJvtSearchDeviceJlabel() {
		return jvtSearchDeviceJlabel;
	}

	/**
	 * 文本框自动加1
	 * @param jLabel
	 */
	public static void AddOneFromJLabel(JLabel jLabel) {
		String str = jLabel.getText();
		if ( str == null || str.length() <= 0) {
			str = "0";
		}
		jLabel.setText(Long.toString(Long.parseLong(str) + 1));
	}
	private static void cleanCharacterLabel() {
		CharacterLabel_count.setText("0");
		CharacterLabel_success.setText("0");
		CharacterLabel_fail.setText("0");
	}
	private static void cleanRecordLabel() {
		recordLabel_count.setText("0");
		recordLabel_success.setText("0");
		recordLabel_fail.setText("0");
	}
	
	/**
	 * 
	 * @return
	 */
	
	private DeviceParameterGson getDeviceParamsFormView() {
		DeviceParameterGson dpg = null;

		if (faceDevchoiseComboBox.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "无设备可设置！！！", "设置设备参数", JOptionPane.ERROR_MESSAGE);
		}else {
		
			/*
			 * "BasicParams":{//0:不支持，1：是、2：否
				"DeviceName":"测试设备",
				"ServerIP":"192.168.11.100",
				"ServerPort":8000,
				"SystemID":"00034900",
				"IsAutoRestart":0, 
				"DailyRestartTime":"02:00:00",
				"IsUploadPassImg":1,
				"RelayTime":1000,
				"TriggerActionInterval":3000,
				"QrCodeSwitch":0,
				"IsSupportCard":0,
				"WiegandType":34,
				"WiegandIn":0,
				"WiegandOut":0,
				"MainUIType":1,
				"EnableScreenSaver":0,
				"OpenDoorPassword":"123456",
				"HeartBeatInterval":3000
			}
			 */
			dpg = new DeviceParameterGson();
			DeviceParameterGson.BasicParams basicParams = dpg.new BasicParams();
			basicParams.setDeviceName(basicParameterDevNameTextField.getText().replaceAll(" ", ""));
			basicParams.setServerIP(basicParameterDevServerIpTextField.getText().replaceAll(" ", ""));
			basicParams.setServerPort(Integer.parseInt(basicParameterDevServerPortTextField.getText().replaceAll(" ", "")));
			basicParams.setSystemID(basicParameterSystemNoTextField.getText().replaceAll(" ", ""));
			int patameterSwitch = 0 ;
			if (appParameterAutoRestart_No__RadioButton.isSelected()) {
				patameterSwitch = 2;
			}else if ( appParameterAutoRestart_Yes__RadioButton.isSelected() ) {
				patameterSwitch = 1;
			}
			basicParams.setIsAutoRestart(patameterSwitch);
			basicParams.setDailyRestartTime(appParameterAutoRestartTextField.getText().replaceAll(" ", ""));
			if (appParameterUploadPassImg_No_RadioButton.isSelected()) {
				patameterSwitch = 2;
			}else if ( appParameterUploadPassImg_Yes_RadioButton.isSelected() ) {
				patameterSwitch = 1;
			}
			basicParams.setIsUploadPassImg(patameterSwitch);
			basicParams.setRelayTime(Integer.parseInt(devOutRelayTimeTextField.getText().replaceAll(" ", "")));
			basicParams.setTriggerActionInterval(Integer.parseInt(recognitionParameterTriggerActionIntervalTextField.getText().replaceAll(" ", "")));
			if (devOutQrCode_No_RadioButton.isSelected()) {
				patameterSwitch = 0;
			}else if (devOutQrCode_Open_RadioButton.isSelected()) {
				patameterSwitch = 1;
			}else if (devOutQrCode_Close_RadioButton.isSelected()) {
				patameterSwitch = 2;
			}
			basicParams.setQrCodeSwitch(patameterSwitch);
			if (devOutCard_No_RadioButton.isSelected()) {
				patameterSwitch = 0;
			}else if (devOutCard_Open_RadioButton.isSelected()) {
				patameterSwitch = 1;
			}else if (devOutCard_Close_RadioButton.isSelected()) {
				patameterSwitch = 2;
			}
			basicParams.setIsSupportCard(patameterSwitch);
			
			HashMap<String, Integer> weigand = ViewChiosePamameter.getWeigandComboBox(devOutWeigand_Out_ComboBox);
			basicParams.setWiegandType(weigand.get("weigandType"));
			basicParams.setWiegandOut(weigand.get("weigand"));
			weigand = ViewChiosePamameter.getWeigandComboBox(devOutWeigand_In_ComboBox);
			basicParams.setWiegandIn(weigand.get("weigand"));
			basicParams.setMainUIType(uiTypeComboBox.getSelectedIndex());
			if(uiScreanSave_No_RadioButton.isSelected()) {
				patameterSwitch = 0;
			}else if(uiScreanSave_Open_RadioButton.isSelected()) {
				patameterSwitch = 1;
			}else if(uiScreanSave_Close_RadioButton.isSelected()) {
				patameterSwitch = 2;
			}
			basicParams.setEnableScreenSaver(patameterSwitch);
			basicParams.setOpenDoorPassword(accessDevDoorPasswordTextField.getText().replaceAll(" ", ""));
			basicParams.setHeartBeatInterval(Integer.parseInt(basicParameterHeartBeatTextField.getText().replaceAll(" ", "")));
//				basicParams.put("DeviceName", basicParameterDevNameTextField.getText().replaceAll(" ", ""));
			
			/*
			 * "FeatureParams":{
				"FeatureType":"CW",
				"FeatureSDKValue":"NSDKBFJDKSFDSVNDSVBHDVCKJXZBWQWDTDKJBNCXDJKNCSAVFVYTFSCBAJKC",
				"FeatureVersion":"CW2.4.0"
			},
			 */
			DeviceParameterGson.FeatureParams featurePatameter = dpg.new FeatureParams();
			featurePatameter.setFeatureType(Integer.parseInt(basicParameterFeatureTypeTextField.getText().replaceAll(" ", "")));
			featurePatameter.setFeatureSDKValue(basicParameterFeatureSdkTextField.getText().replaceAll(" ", ""));
			featurePatameter.setFeatureVersion(basicParameterFeatureVersionTextField.getText().replaceAll(" ", ""));
			
			/*
			 * "RecognitionParams":{
				"SimilityThreshold":"75.00",
				"QualityThreshold":"80.00",
				"MinFacePixel":100,
				"MaxFacePixel":1000,
				"IsAlive":0,
				"LivingThreshold":"99.999"
			},
			*/
			DeviceParameterGson.RecognitionParams recognitionParams = dpg.new RecognitionParams();
			recognitionParams.setSimilityThreshold(recognitionParameterSimilityThresholdTextField.getText().replaceAll(" ", ""));
			recognitionParams.setQualityThreshold(recognitionParameterQualityThresholdTextField.getText().replaceAll(" ", ""));
			recognitionParams.setMinFacePixel(Integer.parseInt(recognitionParameterMinFacePixelTextField.getText().replaceAll(" ", "")));
			recognitionParams.setMaxFacePixel(Integer.parseInt(recognitionParameterMaxFacePixelTextField.getText().replaceAll(" ", "")));
			recognitionParams.setIsAlive(recognitionParameterALiveVomboBox.getSelectedIndex());
			
			recognitionParams.setLivingThreshold(Double.parseDouble(getTextFieldValuse(recognitionParameterLivingThresdhoidTextField, "0.0")));
			
			/*
			 * "AppParams":{
				"AppPassword":"999999"
			},
			*/
			DeviceParameterGson.AppParams appParams = dpg.new AppParams();
			appParams.setAppPassword(appParameterPasswordTextField.getText().replaceAll(" ", ""));
			/*
			 * "AccessParams":{
				"AccessDeviceIP":"192.168.11.201",
				"AccessDoorID":0,
				"AccessDeviceUniqueCode":"HENHJ0S"
			},
			*/
			DeviceParameterGson.AccessParams accessParams = dpg.new AccessParams();
			accessParams.setAccessDeviceIP(accessDevIpTextField.getText().replaceAll(" ", ""));
			accessParams.setAccessDoorID(accessDevDoorNoComboBox.getSelectedIndex());
			accessParams.setAccessDeviceUniqueCode(accessDevMacTextField.getText().replaceAll(" ", ""));
			/*
			 * "CameraParams":{
				"CameraMode":0,
				"BeautyScore":80
			},
			*/
			DeviceParameterGson.CameraParams cameraParams = dpg.new CameraParams();
			cameraParams.setCameraMode(CameraModeComboBox.getSelectedIndex());
			cameraParams.setBeautyScore(Integer.parseInt(CameraBeautyScoreTextField.getText().replaceAll(" ", "")));
			/*
			 * "HardWareParams":{
				"SuppleLightMode":1,
				"SuppleLightOpenTime":"18:00-07:00",
				"DebugModeSwitch":0
			},
			*/
			DeviceParameterGson.HardWareParams hardWareParams = dpg.new HardWareParams();
			hardWareParams.setSuppleLightMode(suppleLightModelcomboBox.getSelectedIndex());
			hardWareParams.setSuppleLightOpenTime(suppleLightOpenTimeTextField.getText().replaceAll(" ", ""));
			if(appParameterDebugMode_Open_RadioButton.isSelected()) {
				patameterSwitch = 1 ;
			}else if(appParameterDebugMode_Close_RadioButton.isSelected()) {
				patameterSwitch = 2 ;
			}
			hardWareParams.setDebugModeSwitch(patameterSwitch);
			/*
			 * "VoiceTipParams":{
				"AttendanceTime":"09:00-18:00",
				"BeforeJobTip":"@打卡成功",
				"AfterJobTip":"@上班一天辛苦了",
				"RecognizeSuccessTip ":"识别成功",
				"RecognizeErrorTip":"未登记"
			},
			*/
			DeviceParameterGson.VoiceTipParams voiceTipParams = dpg.new VoiceTipParams();
			voiceTipParams.setAttendanceTime(voiceTipAttendanceTimeTextField_1.getText().replaceAll(" ", ""));
			voiceTipParams.setBeforeJobTip(voiceTipBeforeJobTextField.getText().replaceAll(" ", ""));
			voiceTipParams.setAfterJob(voiceTipAfterJobTextField.getText().replaceAll(" ", ""));
			voiceTipParams.setRecognizeSuccessTip(voiceTipRecognizeSuccessTextField.getText().replaceAll(" ", ""));
			voiceTipParams.setRecognizeErrorTip(voiceTipRecognizeErrorTextField.getText().replaceAll(" ", ""));
			/*	
			*"ConsumeParams":{
					"PayUrl":"172.168.120.11:21000",
					"QueryUrl":"172.168.120.12:80",
					"DeviceNum":"18",
					"SuccessInfoDuration":1000,
					"DisplayTitle":"欢迎使用达实物联消费系统",
					"BreakfastTime":"09:00-10:00",
					"LunchTime":"11:00-13:00",
					"SupperTime":"23:00-2:00",
					"DinnerTime":"18:00-20:00",
					"CardSystemID":"00034900",
					"OnlineWalletID1":95,
					"OnlineWalletID2":96,
					"OfflineMoneyLimit":500,
					"OfflineNumberLimit":50,
					"OfflineTimeLimit":600,
					"OfflineMode":1
				}*/
			DeviceParameterGson.ConsumeParams consumeParameter = dpg.new ConsumeParams();
			consumeParameter.setPayUrl(consumePayUrlTextField.getText().replaceAll(" ", ""));
			consumeParameter.setQueryUrl(consumeQueryUrlTextField.getText().replaceAll(" ", ""));
			consumeParameter.setDeviceNum(consumeDevNoTextField.getText().replaceAll(" ", ""));
			consumeParameter.setSuccessInfoDuration(Integer.parseInt(consumeSuccessInfoDurationTextField.getText().replaceAll(" ", "")));
			consumeParameter.setDisplayTitle(consumeDisplayTitleTextField.getText().replaceAll(" ", ""));
			consumeParameter.setBreakfastTime(consumeBreakfastTimeTextField.getText().replaceAll(" ", ""));
			consumeParameter.setLunchTime(consumeLunchTimeTextField.getText().replaceAll(" ", ""));
			consumeParameter.setSupperTime(consumeSupperTimeTextField.getText().replaceAll(" ", ""));
			consumeParameter.setDinnerTime(consumeDinnerTimeTextField.getText().replaceAll(" ", ""));
			basicParams.setSystemID(basicParameterSystemNoTextField.getText().replaceAll(" ", ""));
			consumeParameter.setOnlineWalletID1(Integer.parseInt(consumeOnlineWalletID1TextField.getText().replaceAll(" ", "")));
			consumeParameter.setOnlineWalletID2(Integer.parseInt(consumeOnlineWalletId2TextField.getText().replaceAll(" ", "")));
			consumeParameter.setOfflineMoneyLimit(Integer.parseInt(consumeOfflineMoneyLimitTextField.getText().replaceAll(" ", "")));
			consumeParameter.setOfflineNumberLimit(Integer.parseInt(consumeOfflineNumberLimitTextField.getText().replaceAll(" ", "")));
			consumeParameter.setOfflineTimeLimit(Integer.parseInt(consumeOfflineTimeLimitTextField.getText().replaceAll(" ", "")));
			if (consumeOfflineMode_Yes_RadioButton.isSelected()) {
				patameterSwitch = 1;
			}else if (consumeOfflineMode_No_RadioButton.isSelected()) {
				patameterSwitch = 2;
			}
			consumeParameter.setOfflineMode(patameterSwitch);
			
			dpg.setBasicParams(basicParams);
			dpg.setFeatureParams(featurePatameter);
			dpg.setAccessParams(accessParams);
			dpg.setAppParams(appParams);
			dpg.setCameraParams(cameraParams);
			dpg.setConsumeParams(consumeParameter);
			dpg.setHardWareParams(hardWareParams);
			dpg.setRecognitionParams(recognitionParams);
			dpg.setVoiceTipParams(voiceTipParams);
			
		}
		return dpg;
	
	}
	
	public static JTabbedPane getTestTabbedPane() {
		return testTabbedPane;
	}

	public static JPanel getTestPhotoPanel() {
		return testPhotoPanel;
	}

	public static JPanel getTest343Panel() {
		return test343Panel;
	}

	public static JPanel getTest361Panel() {
		return test361Panel;
	}

	public static JLabel getTestphotoLabel() {
		return testphotoLabel;
	}
	public static JLabel getTestLogsJlabell() {
		return testLogsJlabel;
	}

	public static JComboBox<String> getServerIPComboBox() {
		return serverIPComboBox;
	}

	public static void setServerIPComboBox(JComboBox<String> serverIPComboBox) {
		MainIntfaceView.serverIPComboBox = serverIPComboBox;
	}
	
	private String getTextFieldValuse(JTextField textField, String defaultValuse) {
		if (null != textField && textField.getText().trim().length() > 0) {
			defaultValuse = textField.getText().trim().replaceAll(" ", "");
		}
		
		return defaultValuse;
		
	}
	
	private void updateDev350DeviceConfig(DeviceConfig deviceConfig) {

		setJtextFieldValues(devMac350TextField, deviceConfig.deviceCode, "");
//		setJtextFieldValues(devName350TextField, "", "");
		setJtextFieldValues(devIp350TextField, getDevInfo().get(deviceConfig.deviceCode).getDevIP(), "");
		setJtextFieldValues(dev350SeverIpTextField, deviceConfig.reqAddress, "");
		setJtextFieldValues(dev350ServerPortTextField, deviceConfig.reqPort, "");
		setJtextFieldValues(dev350HeatBeatTextField, "", "");
		setJtextFieldValues(dev350AlivingThresholdTextField, deviceConfig.livingThreshold, "");
		setJtextFieldValues(dev350MaxPixelTextField, "512", "");
		setJtextFieldValues(dev350DiscernThresholdTextField, deviceConfig.discernThreshold, "");
		setJtextFieldValues(dev350IdCardThresholdTextField, deviceConfig.idCardThreshold, "");
		setJtextFieldValues(dev350MinPixelTextField, deviceConfig.minPixel, "");
		setJtextFieldValues(dev350DiscernIntervalTextField, deviceConfig.discernInterval, "");
		setJtextFieldValues(dev350detectThresholdTextField, deviceConfig.detectThreshold, "");
		setJtextFieldValues(dev350AppTitleTextField, deviceConfig.appTitle, "");
		setJtextFieldValues(dev350AppPassWordTextField, deviceConfig.password, "");
		setJtextFieldValues(dev350ScreanUrlTextField, deviceConfig.screenUrl, "");
		setJtextFieldValues(dev350KeepAlphaTextField, deviceConfig.keepAlpha, "");
		setJtextFieldValues(dev350keepColorTextField, deviceConfig.keepColor, "");
		setJtextFieldValues(dev350SpeakSpeedTextField, deviceConfig.speakSpeed, "");
		setJtextFieldValues(dev350tempPointColorTextField, Integer.toString(deviceConfig.pointColor), "");
		setJtextFieldValues(dev350tempPointRectColorTextField, deviceConfig.pointRectColor, "");
		setJtextFieldValues(dev350tempPointColorTextField_k, deviceConfig.tempRectColor, "");
		setJtextFieldValues(dev350tempPointRectColorAlphaTextField, deviceConfig.tempRectColorAlpha, "");
		setJtextFieldValues(dev350EnabletempTextField, deviceConfig.enableTemp, "");
		setJtextFieldValues(dev350TempAlarmTextField, deviceConfig.tempAlarm, "");
		setJtextFieldValues(dev350tempFiterTextField, deviceConfig.tempFilter, "");
		if (null == deviceConfig.tempMode) {
			dev350TempModeComboBox.setSelectedIndex(0);
		}else {
			
		}
		
		if (null == deviceConfig) {
			dev350IsAlivecomboBox.setSelectedIndex(0);
		}else {
			
		}
		
		if (null == deviceConfig) {
			dev350FaceRagecotionComboBox.setSelectedIndex(0);
		}else {
			
		}
		
		if (null == deviceConfig) {
			dev350CameraModelComboBox.setSelectedIndex(0);
		}else {
			
		}
		
		if (null == deviceConfig) {
			dev350RecotionFailCountComboBox.setSelectedIndex(0);
		}else {
			
		}
		
		if (null == deviceConfig) {
			dev350ScreanOnComboBox_5.setSelectedIndex(0);
		}else {
			
		}
		
		if (null == deviceConfig) {
			dev350ScreanTimeComboBox.setSelectedIndex(0);
		}else {
			
		}
		
		if (null == deviceConfig) {
			dev350ReportStangerComboBox.setSelectedIndex(0);
		}else {
			
		}
		
		if (null == deviceConfig) {
			dev350EnMakeupComboBox.setSelectedIndex(0);
		}else {
			
		}
		
		if (null == deviceConfig) {
			dev350IsTempFComboBox.setSelectedIndex(0);
		}else {
		}
		
		if (null == deviceConfig) {
			dev350LedOnComboBox.setSelectedIndex(0);
		}else {
			
		}
	}

//	private void getDev350DeviceConfig() {
//		DeviceConfig deviceConfig = new DeviceConfig();
//		devMac350TextField;
//		devName350TextField;
//		devIp350TextField;
//		dev350SeverIpTextField;
//		dev350ServerPortTextField;
//		dev350HeatBeatTextField;
//		dev350AlivingThresholdTextField;
//		dev350MaxPixelTextField;
//		dev350DiscernThresholdTextField;
//		dev350IdCardThresholdTextField;
//		dev350MinPixelTextField;
//		dev350DiscernIntervalTextField;
//		dev350detectThresholdTextField;
//		dev350AppTitleTextField;
//		dev350AppPassWordTextField;
//		dev350ScreanUrlTextField;
//		dev350KeepAlphaTextField;
//		dev350keepColorTextField;
//		dev350SpeakSpeedTextField;
//		dev350tempPointColorTextField;
//		dev350tempPointRectColorTextField;
//		dev350tempPointColorTextField_k;
//		dev350tempPointRectColorAlphaTextField;
//		dev350EnabletempTextField;
//		dev350TempAlarmTextField;
//		dev350tempFiterTextField;
//		dev350TempModeComboBox;
//		dev350IsAlivecomboBox;
//		dev350FaceRagecotionComboBox;
//		dev350CameraModelComboBox;
//		dev350RecotionFailCountComboBox;
//		dev350ScreanOnComboBox_5;
//		dev350ScreanTimeComboBox;
//		dev350ReportStangerComboBox;
//		dev350EnMakeupComboBox;
//		dev350IsTempFComboBox;
//		dev350LedOnComboBox;
//	}
//	
//	private void updateDev350FrameSettings(FrameSettings frameSettings) {
//
//		setJtextFieldValues(dev350TimesStartTimeTextField_1;
//		setJtextFieldValues(dev350TimesEndTimeTextField_1;
//		setJtextFieldValues(dev350TimesCameraExposureTextField_1;
//		setJtextFieldValues(dev350IsAliveThresholdTextField_1;
//		setJtextFieldValues(dev350DetectThresholdTextfield_1;
//		setJtextFieldValues(dev350TimesCameraModeComboBox_1;
//		dev350IsAliveSensitivityComboBox_1;
//		
//		setJtextFieldValues(dev350TimesStartTimeTextField_2;
//		setJtextFieldValues(dev350TimesEndTimeTextField_2;
//		setJtextFieldValues(dev350TimesCameraExposureTextField_2;
//		setJtextFieldValues(dev350IsAliveThresholdTextField_2;
//		setJtextFieldValues(dev350DetectThresholdTextfield_2;
//		dev350TimesCameraModeComboBox_2;
//		dev350IsAliveSensitivityComboBox_2;
//		
//		setJtextFieldValues(dev350TimesStartTimeTextField_3;
//		setJtextFieldValues(dev350TimesEndTimeTextField_3;
//		setJtextFieldValues(dev350TimesCameraExposureTextField_3;
//		setJtextFieldValues(dev350IsAliveThresholdTextField_3;
//		setJtextFieldValues(dev350DetectThresholdTextfield_3;
//		dev350TimesCameraModeComboBox_3;
//		dev350IsAliveSensitivityComboBox_3;
//		
//		setJtextFieldValues(dev350TimesStartTimeTextField_4;
//		setJtextFieldValues(dev350TimesEndTimeTextField_4;
//		setJtextFieldValues(dev350TimesCameraExposureTextField_4;
//		setJtextFieldValues(dev350IsAliveThresholdTextField_4;
//		setJtextFieldValues(dev350DetectThresholdTextfield_4;
//		dev350TimesCameraModeComboBox_4;
//		dev350IsAliveSensitivityComboBox_4;
//	}
//
//	private void updateDev350Deviceinfo(DeviceInfo350 deviceInfo350) {
//
//		setJtextFieldValues(dev350AppVersionTextField;
//		setJtextFieldValues(dev350AppVersionNameTextField;
//		setJtextFieldValues(dev350BuildTimeTextField;
//		setJtextFieldValues(dev350CameraVersionTextField;
//		setJtextFieldValues(dev350FimwareVersionTextField;
//	}
	
	private void setJtextFieldValues(JTextField textField, String values, String defauleValues) {
		if (values == null || values.length() <= 0) {
			textField.setText(defauleValues);
		}else {
			textField.setText(values);
		}
	}
}
