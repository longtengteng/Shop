package com.lnkj.privateshop.entity;

import java.io.Serializable;

/**
 * 图片对象
 *
 */
public class ImageItem implements Serializable
{
	private static final long serialVersionUID = -7188270558443739436L;
	public String imageId;
	public String thumbnailPath;
	public String sourcePath;
	public String imgUrl;
	public boolean isSelected = false;
	public boolean isup = false;


	/**
	 * community_image_id : 30
	 * user_id : 12
	 * community_image_url : /Uploads/Community/9/20170228_165249_14882719699273_8636.jpg
	 */

	private String community_image_id;
	private int user_id;
	private String community_image_url;

	public String getCommunity_image_id() {
		return community_image_id;
	}

	public void setCommunity_image_id(String community_image_id) {
		this.community_image_id = community_image_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getCommunity_image_url() {
		return community_image_url;
	}

	public void setCommunity_image_url(String community_image_url) {
		this.community_image_url = community_image_url;
	}
}
