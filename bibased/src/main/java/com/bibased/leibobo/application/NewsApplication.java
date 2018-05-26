package com.bibased.leibobo.application;

import com.bibased.leibobo.domain.News;

import java.util.List;

/**
 * Created by boboLei on 2018/4/28.
 */
public interface NewsApplication {
	public void saveNews(News news);

	public List<News> getNoticeList(Long userId);

	public List<News> getLetterList(Long userId);
}
