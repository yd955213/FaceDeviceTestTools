﻿日标：
1、对接摄像头，实现人脸识别功能；
2、设备管理功能；
3、区域管理功能；
4、照片旋转、筛选功能；
5、简易门禁管理功能；
6、简易考勤统计；
7、简易消费系统；

软件使用说明：
1、该测试工具默认绑定http端口:19000,可在 系统设置-修改服务端口号处修改端口；
2、设置远程IP和端口功能，只有知道设备端口的情况下才能生效，并将该端口输入到：设备原始端口中；
3、外购设备可能存在其协议不符合达实协议的情况，请注意日志的报错信息；
4、人员管理界面，默认生成自增长的虚拟ID，与C3不对应，完成测试后请将设备信息清空后再发货；
5、工具长期使用，数据库文件会越来越大，若将工具发给其他人时，可在 系统设置-清空数据库 功能，将数据库数据清空；
6、达实协议人脸测试工具.exe 必须与jre_64、DB、bin、libs同一个目录，不可移动，若要移动，请右键-创建快捷方式；移动生产的快捷键图标。

修改日志：
2020-12-10：
1、优化所有sql语句；
2、优化权限下载流程；
3、新增自动下载权限功能；

2020-09-23
1、增加设置LOGO功能；
2、测试模块增加权限对比功能；

2020-09-22
1、修复代码重构后若干问题
1-1、343厂测模块问题；
1-2、日志显示混乱问题；
1-3、json序列化失败后未进行变量为空判断导致空指针问题；（low 、程序代码里面很多变量在使用前未着为空判断，有空在慢慢改吧）
1-4、批量导入、批量下载问题;调试将下标写为0，完成后忘记修改导只能下载第一张照片；
1-5、权限下载优化sql语句；
1-6、修复设置参数时未改数据库问题；
1-7、修复人员信息界面权限下载修改人员信息sql错误问题、修复权限下发代码被屏蔽问题；
1-8、修复人员信息界面页面为1时未刷新表问题；
1-9、接口UploadAuthorityDealResult新增为空判断；
1-10、修复清空设备数据功能调用接口写错，写成了读取参数接口；
1-11、NoticeOfDeviceParamsUpdate接口新增修改设备信息功能
1-12、修改在线升级功能增加自定义路径后的路径无/问题；
2、新增设置logo功能

2020-8-24
1、 优化代码重构后的若干问题；

2020-8-21
1、优化多线程并发导致死锁、并发写库导致违反约束条件问题；
2、优化识别率测试流程；
3、遗留问题：sqlite3当表中数据超过5W时，插入数据慢问题（表中数据46万条时，清空数据耗时28s）；思路：当主表数据满2万时，建分表并将数据导入到分表中，清空主表数据！

2020-08-19
1、修复批量下载无法中断问题；
2、修复过滤已下载功能未处理上次下载失败的问题，修复权限下载数量为0时，按钮无法再次弹起问题；
3、数据库导入照片加入多线程处理，减少等待时间；
4、修复主界面显示实时识别记录时，未判断库中照片url文件不存在时导致显示错误问题；
5、修复进度条问题，修复成功和失败数未置零问题；
6、系统设置新增MQTT协议选择（未做），表system_parameter新增communication_type、mqtt_server_ip字段；
7、新增类MySocketChannel，根据系统设置选择的HTTP、MQTT协议方式进行发送请求或者接收数据；
8、修复日志显示乱码问题；

2020-7-28
1、修改在线消费数据、离线消费数据功能生成的数据签名错误问题；

2020-7-27
1、修改SetDeviceParams接口字段中IsCardSystemInit的类型（String --> int）

2020-7-24
1、将Java源生httpURLConnection 更换为OkHttp3，解决源生框架未将请求头和正文一起发送的问题；
2、增加测试功能：请求密钥、生产在线消费数据、离线消费数据功能，并下载OnlinePay。txt、OfflinePay。txt文件下，该数据用于压测。
3、修改模拟设备权限下载功能下载功能featureType类型错误问题

2020-7-22
1、修复设备修改端口号后无法通讯问题；
2、修复httpURLConnection偶尔未收到数据问题；
3、增加模拟设备心跳上线；
4、增加模拟设备权限下载功能；

2020-7-15
1、新增照片旋转功能；
2、新增人脸检测功能，并裁剪合适的人脸区域（人脸算法：虹软）；
本工具只作为学习虹软人脸算法时的产物，请勿作为商业用途，产生任何经济等纠纷或其他时与作者无关

2020-7-10
1、修改设备IP变化后未修改数据库IP，导致无法通讯；
2、新增定时重启测试功能，测试批量下载时设备重启对下载的影响；

2020-7-2
1、修改批量下载界面显示不全问题；

2020-6-24
1、修改接口，使用多线程处理GetCharacter、UploadAuthorityDealResult接口；
（原因：查看日志文件发现，接口有返回数据，但未在表中找到该值，程序无任何报错）

2020-6-23
1、修改批量提取特征值时数据对应混乱问题；

2020-6-19
1、修改进度条无法显示100%问题；
2、新增批量下载时，已提取特征值的照片的人员无法继续 提取特征值；
3、单次下载新增修改信息功能并重新下载权限；

202006-17
1、修改分页查询时未找到照片路径问题；
2、新增显示照片时，路径不存在的判断；显示照片新增等比例缩放功能（下载的照片未调用该功能）；
3、批量下载sql增加判断条件；
4、批量下载权限时，增加进度条提示功能；

2020-6-16
1、修改内存溢出问题；
2、原下载流程：下人脸权限-->结果返回时判断库中是否有特征值->为空则提取特征值：大批量下载时设备易出现返回的特征值为空的现象；增加另一下载流程：（1）新建人员、（2）批量提特征值、（3）人脸权限下载；

2020-6-15
1、新增全日志写文件功能：目录为当前运行下的logs文件夹下，以日期命名；
2、修改触发器未按照目的运行BUG；
3、屏蔽代码：新增人员时写照片库、写特征库；

2020-6-12
1、删除表face_emp_photo,新增表：face_photo、face_feature；
2、修改下载流程：先写数据库，再发送下载协议数据，将下载返回状态写库；
3、新增类PersonInfo、FaceEmpPhoto 为中间层，修改人员信息时对应操作数据库；
3、表face_dev_parameter新增触发器：有新设备加入时，自动生产库中有照片的人员的权限表（face_dev_author_set）、特征表（face_feature）；
4、表pearson_info新增触发器：（1）新增人员未输入UniqueCode时，UniqueCode、cardNo去personId的值；（2）有新人员加入时，自动生成对应设备的权限表（face_dev_author_set）、特征表（face_feature）；
5、取消清除数据库时压缩数据库的功能（该功能导致清楚速度慢），新增按钮（系统设置界面）压缩数据库。

2020-6-10
1、修改设置远程IP和端口：设备端口被写死问题；新增其使用说明；
2、新增软件重新打开，系统设置界面显示服务号端口功能；
3、新增软件初始化后。人员管理界面表格显示已有照片和特征值人员；
4、新增分页查询人员信息；
5、新增判断照片旋转功能：测试时发现只能判断手机拍照后上传的照片，使用画图软件或者已被我旋转的照片无法判断，该功能暂时废弃；
6、修改多次开启软件端口占用BUG、增加提示功能功能。