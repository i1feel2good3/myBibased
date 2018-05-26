package com.bibased.leibobo.application;

import com.bibased.leibobo.domain.News;

import java.util.List;

/**
 * Created by boboLei on 2018/4/28.
 */
public interface NewsApplication {
	public void saveNews(News news);

	public List<News> getNoticeList(Long userId);

	public List<News> getLetterReceivedList(Long userId);

	public List<News> getLetterSendList(Long userId);

	public News getLetterDeatil(Long newsId);

	public void updateLetterReadStatus(Long newsId);
}
