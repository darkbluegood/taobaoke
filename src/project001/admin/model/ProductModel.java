package project001.admin.model;

import java.math.BigDecimal;

public class ProductModel {
	private long num_iid;
	private String title;
	private String pict_url;
	private BigDecimal reserve_price;
	private BigDecimal zk_final_price;
	private int user_type;
	private String provcity;
	private String item_url;
	private String nick;
	private long seller_id;
	private int volume;
	private String s_images;
	private String author;
	private String category;
	private int tag_f_id;
	private int tag_id;
	private ProductSmallImageModel small_images;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getS_images() {
		return s_images;
	}
	public void setS_images(String s_images) {
		this.s_images = s_images;
	}
	public long getNum_iid() {
		return num_iid;
	}
	public void setNum_iid(long num_iid) {
		this.num_iid = num_iid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPict_url() {
		return pict_url;
	}
	public void setPict_url(String pict_url) {
		this.pict_url = pict_url;
	}
	
	public BigDecimal getReserve_price() {
		return reserve_price;
	}
	public void setReserve_price(BigDecimal reserve_price) {
		this.reserve_price = reserve_price;
	}
	
	public BigDecimal getZk_final_price() {
		return zk_final_price;
	}
	public void setZk_final_price(BigDecimal zk_final_price) {
		this.zk_final_price = zk_final_price;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	public String getProvcity() {
		return provcity;
	}
	public void setProvcity(String provcity) {
		this.provcity = provcity;
	}
	public String getItem_url() {
		return item_url;
	}
	public void setItem_url(String item_url) {
		this.item_url = item_url;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public long getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(long seller_id) {
		this.seller_id = seller_id;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public int getTag_f_id() {
		return tag_f_id;
	}
	public void setTag_f_id(int tag_f_id) {
		this.tag_f_id = tag_f_id;
	}
	public int getTag_id() {
		return tag_id;
	}
	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public ProductSmallImageModel getSmall_images() {
		return small_images;
	}
	public void setSmall_images(ProductSmallImageModel name) {
		this.small_images = name;
	}
}
