package com.gsxy.core.service;

import com.gsxy.core.pojo.bo.FormAddBo;
import com.gsxy.core.pojo.bo.FormPageBo;
import com.gsxy.core.pojo.bo.FormUpdateBo;
import com.gsxy.core.pojo.vo.ResponseVo;

/**
 * @author luyiinn
 * @date 11/10/2024
 */
public interface FormService {

    /**
     * 添加表单
     * @author luyiinn 11/10/2024
     * @param formAddBo
     * @return
     */
    ResponseVo add(FormAddBo formAddBo);

    /**
     * 根据id查询表单
     * @author luyiinn 11/10/2024
     * @param id
     * @return
     */
    ResponseVo getById(Long id);

    /**
     * 根据id删除表单
     * @author luyiinn 11/10/2024
     * @param id
     * @return
     */
    ResponseVo delete(Long id);

    /**
     * 修改表单
     * @author luyiinn 11/10/2024
     * @param formUpdateBo
     * @return
     */
    ResponseVo update(FormUpdateBo formUpdateBo);

    /**
     * 表单分页查询
     * @author luyiinn 11/10/2024
     * @param formPageBo
     * @return
     */
    ResponseVo page(FormPageBo formPageBo);

    /**
     * 查询所有表单
     * @author luyiinn 11/10/2024
     * @param formPageBo
     * @return
     */
    ResponseVo selectAll(FormPageBo formPageBo);
}
