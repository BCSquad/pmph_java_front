package com.bc.pmpheep.back.plugin;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.bc.pmpheep.back.util.Const;
/**
 *分页结果
 *@author Mryang
 *@createDate 2017年10月8日 下午10:14:44
 *
 */
@Alias("PageResult")
public class PageResult<T> {
	//当前页码
    private Integer pageNumber = 1;
    //页面大小
    private Integer pageSize   = Const.PAGE_SIZE;
    //数据总条数
    private Integer total      = 0;
    //总页数
    private Integer pageTotal  = 0;
    //是否是第一页
    private Boolean isFirst    = true;
    //是否是最后一页
    private Boolean isLast     = true;
    //查询开始页
    private Integer start      = 0; 
    //数据集
	private List<T> rows       = new ArrayList<T>(Const.PAGE_SIZE);
	
	
	public PageResult() {
		super();
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
		if(this.pageNumber==1){
			isFirst    = true;
		}else{
			isFirst    = false;
		}
		this.start=(this.pageNumber-1)*this.pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		this.start    = (this.pageNumber-1)*this.pageSize;
		rows          = new ArrayList<T>(this.pageSize);
	}

	public void setTotal(Integer total) {
		this.total = total;
		if(this.total%this.pageSize==0){
			this.pageTotal=this.total/this.pageSize;
		}else{
			this.pageTotal=(this.total/this.pageSize)+1;
		}
		if(this.pageTotal==this.pageNumber){
			isLast     = true;
		}else{
			isLast     = false;
		}
	}

	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}

	public void setFirst(Boolean isFirst) {
		this.isFirst = isFirst;
	}

	public void setLast(Boolean isLast) {
		this.isLast = isLast;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}


	public Integer getPageNumber() {
		return pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Integer getTotal() {
		return total;
	}

	public Integer getPageTotal() {
		return pageTotal;
	}

	public Boolean isFirst() {
		return isFirst;
	}

	public Boolean isLast() {
		return isLast;
	}

	public Integer getStart() {
		return start;
	}

	public List<T> getRows() {
		return rows;
	}

	

    @Override
    public String toString() {
        return "Page [pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", total=" + total
               + ", pageTotal=" + pageTotal + ", isFirst=" + isFirst + ", isLast=" + isLast
               + ", start=" + start + ", rows=" + rows.toString() + "]";
    }

	
	
	
}