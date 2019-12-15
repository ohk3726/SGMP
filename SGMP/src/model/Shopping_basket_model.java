package model;

public class Shopping_basket_model {
	private String prod_id;
	private String prod_name;
	private String prod_Price;
	private String sb_cnt;
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public String getProd_Price() {
		return prod_Price;
	}
	public void setProd_Price(String prod_Price) {
		this.prod_Price = prod_Price;
	}
	public String getSb_cnt() {
		return sb_cnt;
	}
	public void setSb_cnt(String sb_cnt) {
		this.sb_cnt = sb_cnt;
	}
}
