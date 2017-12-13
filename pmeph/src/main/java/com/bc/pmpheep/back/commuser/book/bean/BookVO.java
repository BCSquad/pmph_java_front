package com.bc.pmpheep.back.commuser.book.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("BookVO")
public class BookVO implements Serializable {

    /**
     * 主键
     */
    private Long    id;
    /**
     * 本版号
     */
    private String  vn;
    /**
     * 图书名称
     */
    private String  bookname;
    /**
     * ISBN号
     */
    private String  isbn;
    /**
     * 序号
     */
    private String  sn;
    /**
     * 作者
     */
    private String  author;
    /**
     * 出版社
     */
    private String  publisher;
    /**
     * 语言
     */
    private String  lang;
    /**
     * 版次
     */
    private Integer revision;
    /**
     * 类别
     */
    private Long    type;
    /**
     * 出版日期
     */
    private Date    publishDate;
    /**
     * 读者对象
     */
    private String  reader;
    /**
     * 价格
     */
    private Double  price;
    /**
     * 评分
     */
    private Double  score;
    /**
     * 购买地址
     */
    private String  buyUrl;
    /**
     * 图像地址
     */
    private String  imageUrl;
    /**
     * PDF地址
     */
    private String  pdfUrl;
    /**
     * 点击数
     */
    private Long    clicks;
    /**
     * 评论数
     */
    private Long    comments;
    /**
     * 点赞数
     */
    private Long    likes;
    /**
     * 收藏数
     */
    private Long    bookmarks;
    /**
     * 是否分类置顶
     */
    private Boolean isStick;
    /**
     * 分类显示顺序
     */
    private Integer sort;
    /**
     * 是否新书
     */
    private Boolean isNew;
    /**
     * 新书显示顺序
     */
    private Integer sortNew;
    /**
     * 是否推荐
     */
    private Boolean isPromote;
    /**
     * 销量
     */
    private Long    sales;
    /**
     * 图书是否上架
     */
    private Boolean isOnSale;
    /**
     * 图书详情
     */
    private String  detail;

    /**
     * 书籍类别根目录
     */
    private String  path;
    /**
     * 类别名称
     */
    private String  typeName;
    private String  name;
    /**
     * 排序1:综合，2：最新，3：最热
     */
    private Integer order;
    
    /**
     * 当前登录人id
     */
    private BigInteger logUserId;
    
    /**
     * 点赞关联表主键id 
     */
    private BigDecimal likeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Boolean getIsPromote() {
        return isPromote;
    }

    public void setIsPromote(Boolean isPromote) {
        this.isPromote = isPromote;
    }

    public Boolean getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(Boolean isOnSale) {
        this.isOnSale = isOnSale;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the vn
     */
    public String getVn() {
        return vn;
    }

    /**
     * @param vn the vn to set
     */
    public void setVn(String vn) {
        this.vn = vn;
    }

    /**
     * @return the sn
     */
    public String getSn() {
        return sn;
    }

    /**
     * @param sn the sn to set
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @param publisher the publisher to set
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * @return the lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * @param lang the lang to set
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * @return the revision
     */
    public Integer getRevision() {
        return revision;
    }

    /**
     * @param revision the revision to set
     */
    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    /**
     * @return the publishDate
     */
    public Date getPublishDate() {
        return publishDate;
    }

    /**
     * @param publishDate the publishDate to set
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * @return the reader
     */
    public String getReader() {
        return reader;
    }

    /**
     * @param reader the reader to set
     */
    public void setReader(String reader) {
        this.reader = reader;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the score
     */
    public Double getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(Double score) {
        this.score = score;
    }

    /**
     * @return the buyUrl
     */
    public String getBuyUrl() {
        return buyUrl;
    }

    /**
     * @param buyUrl the buyUrl to set
     */
    public void setBuyUrl(String buyUrl) {
        this.buyUrl = buyUrl;
    }

    /**
     * @return the imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl the imageUrl to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @return the pdfUrl
     */
    public String getPdfUrl() {
        return pdfUrl;
    }

    /**
     * @param pdfUrl the pdfUrl to set
     */
    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    /**
     * @return the clicks
     */
    public Long getClicks() {
        return clicks;
    }

    /**
     * @param clicks the clicks to set
     */
    public void setClicks(Long clicks) {
        this.clicks = clicks;
    }

    /**
     * @return the comments
     */
    public Long getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(Long comments) {
        this.comments = comments;
    }

    /**
     * @return the likes
     */
    public Long getLikes() {
        return likes;
    }

    /**
     * @param likes the likes to set
     */
    public void setLikes(Long likes) {
        this.likes = likes;
    }

    /**
     * @return the bookmarks
     */
    public Long getBookmarks() {
        return bookmarks;
    }

    /**
     * @param bookmarks the bookmarks to set
     */
    public void setBookmarks(Long bookmarks) {
        this.bookmarks = bookmarks;
    }

    /**
     * @return the isStick
     */
    public Boolean getIsStick() {
        return isStick;
    }

    /**
     * @param isStick the isStick to set
     */
    public void setIsStick(Boolean isStick) {
        this.isStick = isStick;
    }

    /**
     * @return the sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort the sort to set
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return the sortNew
     */
    public Integer getSortNew() {
        return sortNew;
    }

    /**
     * @param sortNew the sortNew to set
     */
    public void setSortNew(Integer sortNew) {
        this.sortNew = sortNew;
    }

    /**
     * @return the sales
     */
    public Long getSales() {
        return sales;
    }

    /**
     * @param sales the sales to set
     */
    public void setSales(Long sales) {
        this.sales = sales;
    }

    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the order
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

	public BigInteger getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(BigInteger logUserId) {
		this.logUserId = logUserId;
	}

	public BigDecimal getLikeId() {
		return likeId;
	}

	public void setLikeId(BigDecimal likeId) {
		this.likeId = likeId;
	}

}
