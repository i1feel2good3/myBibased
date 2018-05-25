package com.bibased.leibobo.application.impl;

import com.bibased.leibobo.application.NewsApplication;
import com.bibased.leibobo.domain.News;
import com.bibased.leibobo.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
