package com.gsxy.core.service.impl;

import com.gsxy.core.mapper.FormMapper;
import com.gsxy.core.mapper.UserMapper;
import com.gsxy.core.pojo.Form;
import com.gsxy.core.pojo.FormNew;
import com.gsxy.core.pojo.FormSendUser;
import com.gsxy.core.pojo.FormUserData;
import com.gsxy.core.pojo.bo.*;
import com.gsxy.core.pojo.enums.MessageValues;
import com.gsxy.core.pojo.vo.FormSendUserVo;
import com.gsxy.core.pojo.vo.FormUserDaraVo;
import com.gsxy.core.pojo.vo.FormUserVo;
import com.gsxy.core.pojo.vo.ResponseVo;
import com.gsxy.core.service.FormService;
import com.gsxy.core.util.JwtUtil;
import com.gsxy.core.util.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.gsxy.core.pojo.enums.CodeValues.ERROR_CODE;
import static com.gsxy.core.pojo.enums.CodeValues.SUCCESS_CODE;
import static com.gsxy.core.pojo.enums.MessageValues.ERROR_MESSAGE;
import static com.gsxy.core.pojo.enums.MessageValues.SUCCESS_MESSAGE;

/**
 * @author luyiinn
 * @date 11/10/2024
 */
@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormMapper formMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 添加表单
     *
     * @param formAddBo
     * @return
     */
    @Override
    @Transactional
    public ResponseVo add(FormAddBo formAddBo) {

        //获取当前登录用户id
        Long userId = LoginUtils.getLoginUserId();

        FormNew formNew = FormNew.builder()
                .name(formAddBo.getFormName())
                .userId(userId)
                .build();

        formMapper.insertForm(formNew);

//        formAddBo.getConfig().forEach(item -> {
        formMapper.insert(
                Form.builder()
                        .config(formAddBo.getConfig())
                        .options(formAddBo.getOption())
                        .userId(userId)
                        .formId(formNew.getId())
                        .delFlag(0)
                        .build()
        );
//        });

        return ResponseVo.builder()
                .message(SUCCESS_MESSAGE)
                .code(SUCCESS_CODE)
                .data(null)
                .build();
    }

    /**
     * 根据id查询表单
     *
     * @param id
     * @return
     */
    @Override
    public ResponseVo getById(Long id) {

        Form form = formMapper.selectById(id);

        if (form == null) {
            return ResponseVo.builder()
                    .message(ERROR_MESSAGE)
                    .code(ERROR_CODE)
                    .data(null)
                    .build();
        }
        //已被删除则不被查询
        if (form.getDelFlag() == 1) {
            return ResponseVo.builder()
                    .message(ERROR_MESSAGE)
                    .code(ERROR_CODE)
                    .data(null)
                    .build();
        }

        return ResponseVo.builder()
                .message(SUCCESS_MESSAGE)
                .code(SUCCESS_CODE)
                .data(form)
                .build();
    }

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @Override
    public ResponseVo delete(Long id) {
        //逻辑删除
        Long aLong = formMapper.deleteById(id);
        //操作失败
        if (aLong == null) {
            return ResponseVo.builder()
                    .message(ERROR_MESSAGE)
                    .code(ERROR_CODE)
                    .data(null)
                    .build();
        }

        return ResponseVo.builder()
                .message(SUCCESS_MESSAGE)
                .code(SUCCESS_CODE)
                .data(null)
                .build();
    }

    /**
     * 修改表单
     *
     * @param formUpdateBo
     * @return
     */
    @Override
    public ResponseVo update(FormUpdateBo formUpdateBo) {

        Long aLong = formMapper.update(
                Form.builder()
                        .id(formUpdateBo.getId())
                        .config(formUpdateBo.getConfig())
                        .build()
        );

        if (aLong == null) {
            return ResponseVo.builder()
                    .message(ERROR_MESSAGE)
                    .code(ERROR_CODE)
                    .data(null)
                    .build();
        }

        return ResponseVo.builder()
                .message(SUCCESS_MESSAGE)
                .code(SUCCESS_CODE)
                .data(null)
                .build();
    }

    /**
     * 表单分页查询
     *
     * @param formPageBo
     * @return
     */
    @Override
    public ResponseVo page(@RequestBody FormPageBo formPageBo) {

        //起始索引计算 起始索引=(查询的页码 - 1) * 每页显示的记录数
        int index = (formPageBo.getPage() - 1) * formPageBo.getPageSize();

        List<Form> list = formMapper.page(
                FormPageSBo.builder()
                        .index(index)
                        .userId(LoginUtils.getLoginUserId())
                        .pageSize(formPageBo.getPageSize())
                        .build()

        );

        if (list == null && list.size() == 0) {
            return ResponseVo.builder()
                    .message(ERROR_MESSAGE)
                    .code(ERROR_CODE)
                    .data(null)
                    .build();
        }

        return ResponseVo.builder()
                .message(SUCCESS_MESSAGE)
                .code(SUCCESS_CODE)
                .data(list)
                .build();
    }

    /**
     * 查询所有表单
     *
     * @param formPageBo
     * @return
     */
    @Override
    public ResponseVo selectAll(FormPageBo formPageBo) {

        //起始索引计算 起始索引=(查询的页码 - 1) * 每页显示的记录数
        int index = (formPageBo.getPage() - 1) * formPageBo.getPageSize();

        List<Form> list = formMapper.selectAll(
                FormPageSBo.builder()
                        .index(index)
                        .userId(LoginUtils.getLoginUserId())
                        .pageSize(formPageBo.getPageSize())
                        .build()

        );

        if (list == null && list.size() == 0) {
            return ResponseVo.builder()
                    .message(ERROR_MESSAGE)
                    .code(ERROR_CODE)
                    .data(null)
                    .build();
        }

        return ResponseVo.builder()
                .message(SUCCESS_MESSAGE)
                .code(SUCCESS_CODE)
                .data(list)
                .build();
    }

    @Override
    public ResponseVo formByPageLike(FormNewPageBo formNewPageBo) {

        Long loginUserId = LoginUtils.getLoginUserId();
        List<FormNew> formList = formMapper.formByPageLike((formNewPageBo.getPage() - 1) * formNewPageBo.getLimit(),
                formNewPageBo.getLimit(),
                formNewPageBo.getName(),
                loginUserId
        );

        Long count = formMapper.formByPageLikeCount(
                formNewPageBo.getName(),
                LoginUtils.getLoginUserId(),
                loginUserId
        );

        return ResponseVo.builder()
                .message(SUCCESS_MESSAGE)
                .code(SUCCESS_CODE)
                .data(formList)
                .count(count)
                .build();
    }

    @Override
    public ResponseVo formDelete(Long id) {

        formMapper.formDelete(id);

        return ResponseVo.builder()
                .message(SUCCESS_MESSAGE)
                .code(SUCCESS_CODE)
                .build();
    }

    @Override
    public ResponseVo formUpdate(FormNewUpdateBo formNewUpdateBo) {

        formMapper.formUpdate(formNewUpdateBo);

        return ResponseVo.builder()
                .message(SUCCESS_MESSAGE)
                .code(SUCCESS_CODE)
                .build();
    }

    @Override
    public ResponseVo formQuery(Long id) {

        List<Object> list = formMapper.formQuery(id);

        return ResponseVo.builder()
                .message(SUCCESS_MESSAGE)
                .data(list)
                .code(SUCCESS_CODE)
                .build();
    }

    @Override
    public ResponseVo addFormInfo(FormInfoBo formInfoBo) {

        //获取当前登录用户id
        Long userId = LoginUtils.getLoginUserId();

        formMapper.addFormInfo(
                FormUserData.builder()
                        .createdBy(userId)
                        .formData(formInfoBo.getFormData())
                        .formId(formInfoBo.getFormId())
                        .build()
        );

        return ResponseVo.builder()
                .message(SUCCESS_MESSAGE)
                .code(SUCCESS_CODE)
                .build();
    }

    @Override
    public ResponseVo sendForm(SendFormBo sendFormBo) {

        Long userId = LoginUtils.getLoginUserId();

        sendFormBo.getUserId().forEach(item -> {
            formMapper.sendForm(
                    FormSendUser.builder()
                            .reserveId(item)
                            .createdTime(new Date())
                            .createdBy(userId)
                            .formId(sendFormBo.getFormId())
                            .build()
            );
        });

        return ResponseVo.builder()
                .message(SUCCESS_MESSAGE)
                .code(SUCCESS_CODE)
                .build();
    }

    @Override
    public ResponseVo viewForm(ViewFormBo viewFormBo) {

        Long userId = LoginUtils.getLoginUserId();
        viewFormBo.setPage((viewFormBo.getPage() - 1) * viewFormBo.getLimit());
        List<FormSendUserVo> formSendUserList = formMapper.viewForm(userId, viewFormBo.getPage(), viewFormBo.getLimit())
                .stream().map(item -> {
                    item.setName(formMapper.queryForm(item.getFormId()).getName());
                    Form form = formMapper.queryFormConfig(item.getFormId());
                    item.setOption(form.getOptions());
                    item.setConfig(form.getConfig());
                    return item;
                }).collect(Collectors.toList());

        Long count = formMapper.viewFormCount(userId);

        return ResponseVo.builder()
                .data(formSendUserList)
                .count(count)
                .message(SUCCESS_MESSAGE)
                .code(SUCCESS_CODE)
                .build();
    }

    @Override
    public ResponseVo viewFormUser(ViewFormUserBo viewFormUserBoi) {

        Long userId = LoginUtils.getLoginUserId();
        viewFormUserBoi.setPage((viewFormUserBoi.getPage() - 1) * viewFormUserBoi.getLimit());
        List<FormUserVo> formSendUserList = formMapper.viewFormUser(viewFormUserBoi.getFormId(), viewFormUserBoi.getPage(), viewFormUserBoi.getLimit())
                .stream().map(item -> {
                    item.setReserveIdtEXT(userMapper.selectById(item.getReserveId()).getUsername());
                    item.setWrite(formMapper.queryFormData(viewFormUserBoi.getFormId(), item.getReserveId()) > 0L ? true : false);
                    return item;
                }).collect(Collectors.toList());

        Long count = formMapper.viewFormUserCount(viewFormUserBoi.getFormId());

        return ResponseVo.builder()
                .data(formSendUserList)
                .count(count)
                .message(SUCCESS_MESSAGE)
                .code(SUCCESS_CODE)
                .build();
    }

    @Override
    public ResponseVo formQueryAll(Long id) {

        Long userId = LoginUtils.getLoginUserId();
        List<FormUserDaraVo> formSendUserList = formMapper.formQueryAll(id,userId);

        return ResponseVo.builder()
                .data(formSendUserList)
                .message(SUCCESS_MESSAGE)
                .code(SUCCESS_CODE)
                .build();
    }
}
