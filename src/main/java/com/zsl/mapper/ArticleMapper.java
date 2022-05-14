package com.zsl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsl.entity.Article;
import org.apache.ibatis.annotations.Select;

/**
 * @Author zsl
 * @Date 2021/12/31 17:32
 */
public interface ArticleMapper extends BaseMapper<Article> {
    @Select("select * from t_article where user_id = #{id}")
    Article selectByUserId(long userId);
}
