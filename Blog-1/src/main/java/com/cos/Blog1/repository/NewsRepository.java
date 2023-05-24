package com.cos.Blog1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cos.Blog1.model.NewsArticle;

@Repository
public interface NewsRepository extends JpaRepository<NewsArticle, Long> {
    // 추가적인 쿼리 메소드가 필요한 경우 선언할 수 있습니다.
}
