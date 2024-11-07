<template>
    <div id="app">
        <el-card class="box-card">
            <div v-show="hiddent">
                <!-- <el-button @click="outForm"  style="margin-bottom: 5px;" :circle="true" type="primary">导出表单</el-button> -->
                <el-button @click="nameIsSure = true"  style="margin-bottom: 5px;" :circle="true" type="primary">导出表单</el-button>
                <fc-designer ref="designer" height="100vh" />
            </div>
            <div v-show="!hiddent">
                <form-create :rule="rule" :option="option" :value.sync="value"></form-create>
            </div>

            <!-- 导出表单的弹窗 -->
            <el-dialog title="请输入表单名称" :visible.sync="nameIsSure" width="30%" @close="resetFormName">
            <el-form>
                <el-form-item label="表单名称">
                <el-input v-model="FormAddBo.formName" placeholder="请输入表单名称"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="nameIsSure = false">取消</el-button>
                <el-button type="primary" @click="outForm">确定</el-button>
            </div>
            </el-dialog>

        </el-card>
    </div>
</template>
<script>
//引入api的接口文件
import { formCreate } from "@/api/form";

export default {
    name: 'Form',
    data() {
        return {
            //表单的隐藏和显示
            hiddent: true,
            nameIsSure: false,
            //表单的实例对象
            fApi: {},
            //表单的生成规则
            rule: [],
            //表单数据，
            value: {},
            FormAddBo: {
                config: '',
                formName: '',
                option: ''
            },
            //组件的参数配置
            option: {
                //表单的提交事件
                onSubmit: function (formData) {
                    console.log(formData)
                }
            }

        }
    },
    methods: {
        getApi() {
            return this.fApi
        },
        getRule() {
            return this.rule
        },
        outForm() {

            this.nameIsSure = false
            this.hiddent = false
            this.rule = JSON.parse(this.$refs.designer.getJson()) //表单实现数据 -> 后端接口
            // console.log('rule',this.$refs.designer.getJson());
            this.FormAddBo.config = JSON.stringify(this.rule);
            this.FormAddBo.option = JSON.stringify(this.option);
            const res = formCreate(this.FormAddBo);
            console.log('res = ', res);
            
            // if () { 

            // }
        },
        resetFormName() {
            this.FormAddBo.formName = ''; // 重置表单名称
        }
    },
    mounted() {
        //消除选项框的aria-hidden的错误使用
        this.$refs.designer.$children.forEach((item) => {
            item.$refs.designer.removeAttribute("aria-hidden");
        });
    }
}
</script>
<style>
.text {
    font-size: 14px;
}

.item {
    padding: 18px 0;
}

.box-card {
    width: 480px;
}
</style>