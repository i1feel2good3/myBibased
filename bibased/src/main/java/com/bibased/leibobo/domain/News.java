package com.bibased.leibobo.domain;

import com.bibased.leibobo.domain.support.AbstractEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.assertj.core.util.Preconditions;

import javax.persistence.*;

/**
 * Created by boboLei on 2018/4/28.
 */
@Data
@Entity
@Table(name = "b_news")
public class News extends AbstractEntity{

	@Column(name = "news_type",nullable = false)
	@Setter(AccessLevel.PRIVATE)
	@Enumerated(EnumType.ORDINAL)
	private NewsType newsType;

	@Column(name = "from_user_id",nullable = false)
	@Setter(AccessLevel.PRIVATE)
	private Long fromUserId;

	@Column(name = "to_user_id",nullable = false)
	@Setter(AccessLevel.PRIVATE)
	private Long toUserId;

	@Column(name = "news_theme",nullable = false)
	@Setter(AccessLevel.PRIVATE)
	private String newsTheme;

	@Column(name = "news_content",nullable = false)
	@Setter(AccessLevel.PRIVATE)
	private String newsContent;

	@Column(name = "read_status",nullable = false)
	@Setter(AccessLevel.PRIVATE)
	private Boolean Read_status;

	public News(){
	}

	public News(Long fromUserId,Long toUserId,String newsTheme,String newsContent){
		Preconditions.checkArgument(fromUserId != null,"fromUserId is null");
		Preconditions.checkArgument(toUserId != null,"toUserId is null");
		Preconditions.checkArgument(newsTheme != null,"newsTheme is null");
		Preconditions.checkArgument(newsContent != null,"newsTheme is null");
		Preconditions.checkArgument(newsContent != null,"newsContent is null");
		init();
		setFromUserId(fromUserId);
		setToUserId(toUserId);
		setNewsTheme(newsTheme);
		setNewsContent(newsContent);
	}

	@Override
	public void init() {
		super.init();
		setNewsType(NewsType.NOTICE);
		setRead_status(false);
	}

	public void setNewsPrivateLetter(){
		setNewsType(NewsType.PERSONAL_LETTER);
	}

	public void setRead(){
		setRead_status(true);
	}

	public Boolean isRead(){
		if (this.getRead_status() == true){
			return true;
		}else {
			return false;
		}
	}
}
