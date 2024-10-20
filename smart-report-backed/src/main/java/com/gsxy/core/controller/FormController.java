package com.gsxy.core.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.gsxy.core.pojo.bo.FormAddBo;
import com.gsxy.core.pojo.bo.FormPageBo;
import com.gsxy.core.pojo.bo.FormUpdateBo;
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
