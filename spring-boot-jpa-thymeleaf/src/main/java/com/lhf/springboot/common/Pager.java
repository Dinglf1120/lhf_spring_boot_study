package com.lhf.springboot.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Pager
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/6/3 15:22
 */
public class Pager<T> implements Serializable {

    private static final long serialVersionUID = -473374229694061823L;

    //当前页
    private int currentPage = 1;

    //每页大小
    private int pageSize = 10;

    //总页数
    private int pageTotal;

    //总条数
    private int recordTotal = 0;

    //第一页
    private int firstPage = 1;

    //每页的内容
    private List<T> content;

    public Pager(List<T> content, int currentPage, int pageSize){
        super();
        this.content = content;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.otherAttr();
    }

    public Pager(List<T> content, int currentPage){
        super();
        this.content = content;
        this.currentPage = currentPage;
        this.otherAttr();
    }

    public Pager(List<T> content){
        super();
        this.content = content;
        this.otherAttr();
    }

    /**
     * pager获取分好页的数据记录
     * @return
     */
    public List<T> getPageContent(){
        if(this.content == null || this.content.size() < 1){
            return null;
        }

        List<T> pageContent = new ArrayList<>();
        //当前页的第一行为：(页码-1)x每页行数
        int firstLine = (this.currentPage - 1) * this.pageSize;
        //当前页的最后一行为： 页码 * 每页行数 - 1 （如果最后一页为最大行数）
        //如果当前页等于总页数，则等于总条数，否则等于当前页乘以每页条数
        int lastLine = this.currentPage == this.pageTotal ? this.recordTotal : this.currentPage * this.pageSize;
        for(int i = firstLine; i < lastLine; i++){
            pageContent.add(this.content.get(i));
        }
        return pageContent;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    //设置当前页
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    //设置每页大小，也可以不用赋值，默认大小为10条
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageTotal() {
        return pageTotal;
    }


    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getRecordTotal() {
        return recordTotal;
    }

    //设置总条数，默认为0
    public void setRecordTotal(int recordTotal) {
        this.recordTotal = recordTotal;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public List<T> getContent() {
        return content;
    }

    //设置分页内容
    public void setContent(List<T> content) {
        this.content = content;
    }

    /**
     * 设置其他参数
     */
    public void otherAttr(){
        if(this.content != null){
            //总条数
            this.recordTotal = this.content.size();
            //总页数
            this.pageTotal = this.recordTotal % this.pageSize > 0 ? this.recordTotal / this.pageSize + 1: this.recordTotal / this.pageSize;

            //设置并调整当前页
            if(this.currentPage < 1){
                this.currentPage = 1;
            }else if(this.currentPage > this.pageTotal){
                this.currentPage = this.pageTotal;
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Page: {currentPage = ");
        builder.append(currentPage);
        builder.append(", pageSize=");
        builder.append(pageSize);
        builder.append(", pageTotal=");
        builder.append(pageTotal);
        builder.append(", recordTotal=");
        builder.append(recordTotal);
        builder.append(", firstPage=");
        builder.append(firstPage);
        builder.append(", content=");
        builder.append(content);
        builder.append("}");
        return builder.toString();
    }


}
