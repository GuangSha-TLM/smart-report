package com.gsxy.core.mapper;

import com.gsxy.core.pojo.Form;
import com.gsxy.core.pojo.FormNew;
import com.gsxy.core.pojo.FormSendUser;
import com.gsxy.core.pojo.FormUserData;
import com.gsxy.core.pojo.bo.FormNewUpdateBo;
import com.gsxy.core.pojo.bo.FormPageSBo;
import com.gsxy.core.pojo.vo.FormSendUserVo;
import com.gsxy.core.pojo.vo.FormUserDaraVo;
import com.gsxy.core.pojo.vo.FormUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.List;

/**
 * @author luyiinn
 * @date 11/10/2024
 */
@Mapper
public interface FormMapper {

    /**
     * 新建表单
     * @author luyiinn 11/10/2024
     * @param form
     * @return
     */
    Long insert(Form form);

    /**
     * 根据id查询表单
     * @author luyiinn 11/10/2024
     * @param id
     * @return
     */
    Form selectById(Long id);

    /**
     * 根据id逻辑删除表单
     * @author luyiinn 11/10/2024
     * @param id
     * @return
     */
    Long deleteById(Long id);

    /**
     * 修改表单
     * @author luyiinn 11/10/2024
     * @param build
     * @return
     */
    Long update(Form build);

    /**
     * 表单分页查询
     * @author luyiinn 11/10/2024
     * @param build
     * @return
     */
    List<Form> page(FormPageSBo build);

    /**
     * 查询全部表单
     * @author luyiinn 11/10/2024
     * @param build
     * @return
     */
    List<Form> selectAll(FormPageSBo build);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertForm(FormNew formNew);

    List<FormNew> formByPageLike(long page, Long limit, String name, Long userId);

    Long formByPageLikeCount(String name, Long loginUserId, Long userId);

    void formDelete(Long id);

    void formUpdate(FormNewUpdateBo formNewUpdateBo);

    @Select("select config from form_config where form_id = #{id}")
    List<Object> formQuery(Long id);

    void addFormInfo(FormUserData build);

    void sendForm(FormSendUser build);

    List<FormSendUserVo> viewForm(Long userId, Long page, Long limit);

    @Select("select * from form where id = #{formId} and del_flag = 0 limit 1")
    FormNew queryForm(Long formId);

    @Select("select * from form_config where form_id = #{formId} and del_flag = 0")
    Form queryFormConfig(Long formId);


    Long viewFormCount(Long userId);

    List<FormUserVo> viewFormUser(Long formId, Long page, Long limit);

    Long viewFormUserCount(Long formId);

    List<FormUserDaraVo> formQueryAll(Long id, Long userId);

    Long queryFormData(Long id,Long userId);
}
