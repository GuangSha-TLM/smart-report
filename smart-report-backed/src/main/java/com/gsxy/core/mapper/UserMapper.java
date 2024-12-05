package com.gsxy.core.mapper;

import com.gsxy.core.pojo.User;
import com.gsxy.core.pojo.bo.UserPageBo;
import com.gsxy.core.pojo.bo.UserRegBo;
import com.gsxy.core.pojo.bo.UserUpdateBo;
import com.gsxy.core.pojo.vo.UserVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    User selectByUsername(String username);

    @Insert("insert into user(username,password,created_time,school_id) " +
            "values (#{username},#{password},#{createdTime},#{schoolId})")
    void insert(User user);

    @Select("select * from user where id=#{id} and del_flag=0")
    UserVo selectById(Long id);

    @Update("update user set del_flag=1 where id=#{id}")
    void DeleteByid(Long id);

    List<UserVo> selectAll();

    void Update(UserUpdateBo userUpdateBo);

    List<UserVo> selectPageQuery(UserPageBo userPageBo);

    Long selectPageQueryCount(UserPageBo userPageBo);

    List<Long> selectUserIdsBySchoolId(Integer schoolId);


}
