<template>
    <div id="app">
        <el-card class="box-card">
            <div v-show="hiddent">
                <el-button @click="outForm" style="margin-bottom: 5px;" :circle="true" type="primary">导出表单</el-button>
                <fc-designer ref="designer" height="100vh" />
            </div>
            <div v-show="!hiddent">
                <form-create :rule="rule" :option="option" :value.sync="value"></form-create>
            </div>
        </el-card>
    </div>
</template>
<script>
export default {
    name: 'Form',
    data() {
        return {
            //表单的隐藏和显示
            hiddent: true,
            //表单的实例对象
            fApi: {},
            //表单的生成规则
            rule: [],
            //表单数据，
            value: {},
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
            this.hiddent = false,
                this.rule = JSON.parse(this.$refs.designer.getJson())
            console.log(this.$refs.designer.getJson());

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