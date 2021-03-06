package com.bibased.leibobo.application.impl;

import com.bibased.leibobo.application.NewsApplication;
import com.bibased.leibobo.domain.News;
import com.bibased.leibobo.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by boboLei on 2018/4/28.
 */
@Service
public class NewsApplicationImpl implements NewsApplication {

	@Autowired
	private NewsRepository newsRepository;

	@Override
	public void saveNews(News news) {
		newsRepository.save(news);
	}

	@Override
	public List<News> getNoticeList(Long userId) {
		return newsRepository.findAllNotice(userId);
	}

	@Override
	public List<News> getLetterReceivedList(Long userId) {
		return newsRepository.findAllReceivedLetter(userId);
	}

	@Override
	public List<News> getLetterSendList(Long userId) {
		return newsRepository.findAllSendLetter(userId);
	}

	@Override
	public News getLetterDeatil(Long newsId) {
		return newsRepository.findAllById(newsId);
	}

	@Override
	public void updateLetterReadStatus(Long newsId) {
		newsRepository.updateReadStatus(newsId);
	}
}
