package com.gsxy.core.mapper;

import com.gsxy.core.pojo.Form;
import com.gsxy.core.pojo.bo.FormPageSBo;
import org.apache.ibatis.annotations.Mapper;

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
}
