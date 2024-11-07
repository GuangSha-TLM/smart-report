package com.gsxy.core.service;

import com.gsxy.core.pojo.bo.*;
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

    ResponseVo formByPageLike(FormNewPageBo formNewPageBo);

    ResponseVo formDelete(Long id);

    ResponseVo formUpdate(FormNewUpdateBo formNewUpdateBo);

    ResponseVo formQuery(Long id);

    ResponseVo addFormInfo(FormInfoBo formInfoBo);

    ResponseVo sendForm(SendFormBo sendFormBo);

    ResponseVo viewForm(ViewFormBo viewFormBo);

    ResponseVo viewFormUser(ViewFormUserBo viewFormUserBoi);

    ResponseVo formQueryAll(Long id);
}
