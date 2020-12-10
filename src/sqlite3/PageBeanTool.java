package sqlite3;

import java.util.List;
//
//import myGson.DownloadAuthorityDataGson;

/**
 * 
 * @author yangdang
 *
 */
public class PageBeanTool {
//	private int startPage = 0;
//	private List<DownloadAuthorityDataGson> list = null;
//	private List<List<String>> listPhotoURl = null;
	public PageBean<PersonInfo> getPage(int currenPage){
        //类型转换 当前页数
//        Integer currenPage = Integer.valueOf(currPage);
        //实例化分页类
        PageBean<PersonInfo> pageBean = new PageBean<PersonInfo>();
        //数据库第几行开始查询
        int startPage=(currenPage-1)*pageBean.getPageCount();
        //查询多少行数据 分页类里默认39行
        int selectCount=pageBean.getPageCount();
        //查询数据库获取分页返回的数据 : select * from regional_info limit startPage,selectCount
        List<PersonInfo> list=DataBaseExecute.getInstance().listPersonInfo(selectCount, startPage);
        //获取总数
        int cityCount=DataBaseExecute.getInstance().getPersonInfoCount();
        //设置查询的数据
        pageBean.setPageData(list);
        //共多少行
        pageBean.setTotalCount(cityCount);
        //设置总页数
        pageBean.setTotalPage(cityCount/pageBean.getPageCount()+1);
        return pageBean;
    }
	/**
	 * 
	 * @param pageBean
	 * @param currenPage
	 * @param type type 0:含photo_url、 无feature  1 ：含photo_url、feature 、2:含PhotoBase64、 3：含PhotoBase64，feature
	 * @return
	 */
//	public PageBean<DownloadAuthorityDataGson> getDownloadAuthorityDataGson(PageBean<DownloadAuthorityDataGson> pageBean ,int currenPage , int type, String featureType, String macAddr) {
//		startPage = (currenPage-1)*pageBean.getPageCount();
//		list = DownLoadAuthority.listDownloadAuthorityDataGson(pageBean.getPageCount(), startPage, type, featureType, macAddr);
//		
//		pageBean.setPageData(list);
//		return pageBean;
//	}
	/**
	 * 
	 * @param pageBean
	 * @param currenPage
	 * @return list[0] =person_id  list[1] = photo_url
	 */
//	public PageBean<List<String>> getDownloadFeature(PageBean<List<String>> pageBean ,int currenPage ) {
//		startPage = (currenPage-1)*pageBean.getPageCount();
//		listPhotoURl = DownLoadAuthority.listDownloadFeature(pageBean.getPageCount(), startPage);
//		pageBean.setPageData(listPhotoURl);
//		return pageBean;
//	}
}