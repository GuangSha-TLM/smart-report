package com.gsxy.core.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.gsxy.core.pojo.bo.*;
import com.gsxy.core.pojo.vo.ResponseVo;
import com.gsxy.core.service.FormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.gsxy.core.pojo.enums.CodeValues.PARAMETER_ERROR;
import static com.gsxy.core.pojo.enums.MessageValues.PARAMETER_MESSAGE;

/**
 * @author luyiinn
 * @date 11/10/2024
 */
@CrossOrigin
@Api(value = "表单板块接口",tags = {"表单板块接口"})
@RequestMapping("/form")
@RestController
public class FormController {

    @Autowired
    private FormService formService;

    /**
     * 添加表单
     * @author luyiinn 11/10/2024
     * @param formAddBo
     * @return
     */
    @ApiOperation("添加表单")
    @PostMapping("/add")
    public String add(@RequestBody FormAddBo formAddBo){
        if (formAddBo == null){
            return JSONArray.toJSONString(
                    ResponseVo.builder()
                            .message(PARAMETER_MESSAGE)
                            .code(PARAMETER_ERROR)
                            .data(null)
                            .build()
            );
        }

        return JSONArray.toJSONString(formService.add(formAddBo));
    }

    /**
     * 添加表单信息
     * @author hln 2024-11-7
     * @return
     */
    @ApiOperation("添加表单信息")
    @PostMapping("/addFormInfo")
    public String addFormInfo(@RequestBody FormInfoBo formInfoBo){
        return JSONArray.toJSONString(formService.addFormInfo(formInfoBo));
    }

    /**
     * 发送表单 -》 用户
     * @author hln 2024-11-7
     * @return
     */
    @ApiOperation("发送表单 -》 用户")
    @PostMapping("/sendForm")
    public String sendForm(@RequestBody SendFormBo sendFormBo){
        return JSONArray.toJSONString(formService.sendForm(sendFormBo));
    }

    /**
     * 查看表单 -》 用户
     * @author hln 2024-11-7
     * @return
     */
    @ApiOperation("查看表单 -》 用户")
    @PostMapping("/viewForm")
    public String viewForm(@RequestBody ViewFormBo viewFormBo){
        return JSONArray.toJSONString(formService.viewForm(viewFormBo));
    }

    /**
     * 查看该表单发给了哪些用户
     * @author hln 2024-11-7
     * @return
     */
    @ApiOperation("查看表单 -》 用户")
    @PostMapping("/viewFormUser")
    public String viewFormUser(@RequestBody ViewFormUserBo viewFormUserBoi){
        return JSONArray.toJSONString(formService.viewFormUser(viewFormUserBoi));
    }

    /**
     * 根据id查询表单
     * @author luyiinn 11/10/2024
     * @param id
     * @return
     */
    @ApiOperation("根据id查询表单")
    @GetMapping("/getById/{id}")
    public String getById(@PathVariable Long id){
        return JSON.toJSONString(formService.getById(id));
    }

    /**
     * 删除表单
     * @author luyiinn 11/10/2024
     * @param id
     * @return
     */
    @ApiOperation("删除表单")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        //要删除的表单不存在或已被删除
        if(formService.getById(id).getData() == null){
            return JSONArray.toJSONString(
                    ResponseVo.builder()
                            .message(PARAMETER_MESSAGE)
                            .code(PARAMETER_ERROR)
                            .data(null)
                            .build()
            );
        }

        return JSONArray.toJSONString(formService.delete(id));
    }

    /**
     * 修改表单
     * @author luyiinn 11/10/2024
     * @param formUpdateBo
     * @return
     */
    @ApiOperation("修改表单")
    @PostMapping("/update")
    public String update(@RequestBody FormUpdateBo formUpdateBo){

        //要修改的表单不存在或已删除
        if(formService.getById(formUpdateBo.getId()).getData() == null){
            return JSONArray.toJSONString(
                    ResponseVo.builder()
                            .message(PARAMETER_MESSAGE)
                            .code(PARAMETER_ERROR)
                            .data(null)
                            .build()
            );
        }

        return JSONArray.toJSONString(formService.update(formUpdateBo));
    }

    /**
     * 表单分页查询
     * @author hln 2024-11-7
     * @return
     */
    @ApiOperation("表单分页查询")
    @PostMapping("/formByPageLike")
    public String formByPageLike(@RequestBody FormNewPageBo formNewPageBo){
        return JSONArray.toJSONString(formService.formByPageLike(formNewPageBo));
    }

    /**
     * 表单删除
     * @author hln 2024-11-7
     * @return
     */
    @ApiOperation("表单删除")
    @GetMapping("/formDelete/{id}")
    public String formDelete(@PathVariable Long id){
        return JSONArray.toJSONString(formService.formDelete(id));
    }

    /**
     * 表单修改
     * @author hln 2024-11-7
     * @return
     */
    @ApiOperation("表单修改")
    @PostMapping("/formUpdate")
    public String formUpdate(@RequestBody FormNewUpdateBo formNewUpdateBo){
        return JSONArray.toJSONString(formService.formUpdate(formNewUpdateBo));
    }

    /**
     * 表单组件
     * @author hln 2024-11-7
     * @return
     */
    @ApiOperation("表单组件")
    @GetMapping("/formQuery/{id}")
    public String formQuery(@PathVariable Long id){
        return JSONArray.toJSONString(formService.formQuery(id));
    }

    /**
     * 表单组件
     * @author hln 2024-11-7
     * @return
     */
    @ApiOperation("表单组件")
    @GetMapping("/formQueryAll/{id}")
    public String formQueryAll(@PathVariable Long id){
        return JSONArray.toJSONString(formService.formQueryAll(id));
    }

    /**
     * 表单分页查询
     * @author luyiinn 11/10/2024
     * @param formPageBo
     * @return
     */
    @ApiOperation("表单分页查询")
    @PostMapping("/page")
    public String page(@RequestBody FormPageBo formPageBo){

        if(formPageBo == null){
            return JSONArray.toJSONString(
                    ResponseVo.builder()
                            .message(PARAMETER_MESSAGE)
                            .code(PARAMETER_ERROR)
                            .data(null)
                            .build()
            );
        }

        return JSONArray.toJSONString(formService.page(formPageBo));
    }

    /**
     * 查询所有表单
     * @author luyiinn 11/10/2024
     * @param formPageBo
     * @return
     */
    @ApiOperation("查询所有表单")
    @PostMapping("/selectAll")
    public String selectAll(@RequestBody FormPageBo formPageBo){

        if(formPageBo == null){
            return JSONArray.toJSONString(
                    ResponseVo.builder()
                            .message(PARAMETER_MESSAGE)
                            .code(PARAMETER_ERROR)
                            .data(null)
                            .build()
            );
        }

        return JSONArray.toJSONString(formService.selectAll(formPageBo));
    }

}
