package com.swfinal.user.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.swfinal.user.User;

@Mapper
public interface UserMapper {

    /**
     * 전체 상품 건수를 조회한다.
     * 검색어가 있으면 상품명에 대해 LIKE 조건을 적용한다.
     */
    @Select({"<script>",
            "SELECT COUNT(*)",
            "FROM tb_product",
            "<where>",
            "  <if test=\"searchuserName != null and searchuserName != ''\">",
            "    user_name LIKE CONCAT('%', #{searchuserName}, '%')",
            "  </if>",
            "  <if test=\"searchuserType != null and searchuserType != ''\">",
            "    AND user_type = #{searchuserType}",
            "  </if>",
            "</where>",
            "</script>"})
    int selectuserTotalCount(
            @Param("searchuserName") String searchuserName,
            @Param("searchuserType") String searchuserType
    );

    /**
     * 상품 목록을 조회한다.
     * 검색어가 있으면 상품명 LIKE, 유형이 지정되면 유형 조건을 적용하며,
     * 페이징을 위해 offset과 limit을 사용한다.
     */
    @Select({"<script>",
            "SELECT",
            "  user_seq AS userSeq,",
            "  user_type AS userType,",
            "  user_name AS userName,",
            "  user_price AS userPrice,",
            "  reg_dt AS regDt,",
            "  mod_dt AS modDt",
            "FROM tb_user",
            "<where>",
            "  <if test=\"searchuserName != null and searchuserName != ''\">",
            "    user_name LIKE CONCAT('%', #{searchuserName}, '%')",
            "  </if>",
            "  <if test=\"searchuserType != null and searchuserType != ''\">",
            "    AND user_type = #{searchuserType}",
            "  </if>",
            "</where>",
            "ORDER BY user_seq",
            "LIMIT #{offset}, #{limit}",
            "</script>"})
    List<User> selectuserList(
            @Param("searchuserName") String searchuserName,
            @Param("searchuserType") String searchuserType,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    /**
     * 단일 상품 상세 조회
     */
    @Select("SELECT user_seq, user_id, user_pw, user_nm, user_email, reg_dt, mod_dt\n"
    		+ "FROM book.tb_userst WHERE user_seq = #{seq}")
    Map<String, Object> selectuserDetail(@Param("seq") int seq);
}
