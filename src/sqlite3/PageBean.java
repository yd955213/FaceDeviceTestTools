package sqlite3;

import java.util.List;

/**
 * 
 * @author yangdang
 *
 * @param <T>
 */
public class PageBean<T> {
    /**
     * 当前页， 默认显示第一页
     */
    private Integer currntPage = 1;
    /**
     * 查询返回的行数（每页显示的行数），默认每页显示XX行
     */
    private int pageCount = 34;
    /**
     * 总记录数
     */
    private int totalCount;
    /**
     * 总页数 = 总记录数/每页显示的行数（+1）
     */
    private int totalPage;
    /**
     * 分页查询的数据,运用泛型，可以重复利用
     */
    private List<T> pageData;
    /**
     * 总页数 = 总记录数/每页显示的行数（+1）
     */
    public int getTotalPage() {
        if (totalCount % pageCount == 0) {
            totalPage = totalCount / pageCount;
        } else {
            totalPage = totalCount / pageCount + 1;
        }
        return totalPage;
    }

    /**
     * 总页数 = 总记录数/每页显示的行数（+1）
     */
    public void setTotalPage(int totalPage) {
    	if(totalPage < 1) {
    		this.totalPage = 1;
    	}else if (totalPage > getTotalPage()) {
    		this.totalPage  = totalPage;
    	}else {
    		this.totalPage = totalPage;
    	}
    }

    public int getCurrntPage() {
        return currntPage;
    }

    public void setCurrntPage(int currntPage) {
    	
    	this.currntPage = currntPage;
    }

    /**
     * 查询返回的行数（每页显示的行数），默认每页显示XX行
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * 查询返回的行数（每页显示的行数），默认每页显示XX行
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * 总记录数
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 总记录数
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }


    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }
}