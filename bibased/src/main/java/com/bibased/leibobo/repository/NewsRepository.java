package com.bibased.leibobo.repository;

import com.bibased.leibobo.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by boboLei on 2018/4/28.
 */
@Repository
public interface NewsRepository extends JpaRepository<News,Long> {

	@Query("select n from News as n where n.fromUserId = 0 and n.newsType = 1 and n.toUserId = ?1")
	public List<News> findAllNotice(Long toUserId);

	@Query("select n from News as n where n.newsType = 0 and n.fromUserId = ?1")
	public List<News> findAllSendLetter(Long fromUserId);

	@Query("select n from News as n where n.newsType = 0 and n.toUserId = ?1")
	public List<News> findAllReceivedLetter(Long toUserId);

	public News findAllById(Long newsId);

	@Transactional
	@Modifying
	@Query("update News as n set n.readStatus = true where n.id = ?1 ")
	public void updateReadStatus(Long id);
}
