package com.bibased.leibobo.repository;

import com.bibased.leibobo.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by boboLei on 2018/4/28.
 */
public interface NewsRepository extends JpaRepository<News,Long> {
}
