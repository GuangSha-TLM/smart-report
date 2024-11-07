<template>
  <div>

    <!-- 操作栏 -->
    <el-row type="flex" justify="end" style="margin-bottom: 20px;">
      <el-col :span="3">
        <!-- <el-button type="primary" @click="handleAdd">新增</el-button> -->
      </el-col>
    </el-row>

    <!-- 表格展示数据 -->
    <el-table :data="userDatas" style="width: 100%" border>
      <el-table-column label="ID">
            <template slot-scope="scope">{{ scope.$index + 1 }}</template>
      </el-table-column>
      <el-table-column label="姓名" prop="reserveIdtEXT"></el-table-column>
      <el-table-column label="是否填写" prop="isWrite">
          <template v-slot:default="{ row }">
            {{ row.write ? '填写' : '未填写' }}
          </template>
      </el-table-column>
      <!-- <el-table-column label="创建人" prop="createdText"></el-table-column> -->

      <!-- 操作栏（每行数据右侧的操作按钮） -->
      <el-table-column label="操作" width="300">
        <template slot-scope="scope">
          <el-button @click="handleView(scope.row)" size="small" type="primary">详细</el-button>
        </template>
      </el-table-column>

    </el-table>

    <!-- 预览 -->
    <el-dialog title="预览" :visible.sync="viewIsSure" width="30%">
        <div>
            <!-- 这里是回显表单数据的部分 -->
            <form-create :rule="rule" :option="option" :value.sync="formData"></form-create>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button @click="viewIsSure = false">取消</el-button>
            <el-button type="primary" @click="submitInfo">确定</el-button>
        </div>
    </el-dialog>
    
    <!-- 分页组件 -->
     <el-pagination
    :current-page="currentPage"
    :page-size="pageSize"
    :total="totalItems"
    @current-change="handlePageChange"
    layout="total, prev, pager, next, jumper"
    />
  </div>
</template>

<script>
import { formByPageLike,formDelete,formUpdate,formQuery,viewFormUser,formQueryAll } from "@/api/form";
import { addFormInfo } from "@/api/user";

export default {
  data() {
    return {
      currentPage: 1,  // 当前页数
      pageSize: 10,    // 每页显示多少条数据
      totalItems: 0,   // 数据总数
      userDatas: [],   // 表格数据
      nameIsSure: false, // 修改按钮
      viewIsSure: false, // 预览按钮
      formId: '', // 表单id
      // 预览时表单数据
      formData: {},
      // 表单配置项
      rule: [],  // 表单规则配置
      option: {},  // 表单其他选项配置
      FormNewPageBo : {
        page: 1,
        limit: 10,
        formId: this.$route.params.id
        },
        AddFormInfo: {
            formId: '',
            formData: {}
        },
      FormUpdateBo:{
        name: '',
        id : ''
      }
    };
  },
  created() {
      this.formId = this.$route.params.id;
    console.log('formId', this.formId);
    this.fetchData(); // 组件创建时加载数据
  },
    methods: {
    // 获取表单数据和配置
    async fetchFormData() {
      try {
        const response = await formQueryAll(this.formId);
        //   response = JSON.parse(response)
    console.log('config122121', response.data);
    if (response && response.code === "0x200") {
    try {
        // 假设返回的数据包含表单规则和表单值
        //   this.rule = response.data.map(str => {
        //         console.log('str', str);
        //     return JSON.parse(str);
        //     });  // 表单规则
        this.rule = JSON.parse(response.data[0].config);
              this.formData = JSON.parse(response.data[0].formData) || [];  // 表单回显数据
            //   this.option = response.data || {};  // 表单其他配置（例如布局等）
        } catch (error) {
            console.error("解析数据失败:", error);
        }
        }
        } catch (error) {
        console.error("请求失败", error);
        }
        },
        async fetchData() {
        try {
            const obj = await viewFormUser(this.FormNewPageBo); // 获取数据
            console.log("成功", obj);
            if (obj && obj.code === "0x200") {
                console.log("成功", obj.data);
                this.userDatas = obj.data; // 设置表格数据
                this.totalItems = obj.count; // 设置数据总数 (假设后端返回了 total 字段)
                this.switchbutton = true; // 根据需要更新按钮状态
            } else {
                alert(obj.message);
                this.switchbutton = false;
            }
        } catch (error) {
            console.error("请求失败", error);
            this.switchbutton = false;
        }
    },
    // 页码改变时触发的事件
    handlePageChange(page) {
        this.FormNewPageBo.page = page; // 更新当前页码
        this.fetchData(); // 重新获取数据
      },
      async handleEdit(row) { 
        this.FormUpdateBo.id = row.id;
         this.nameIsSure = true;
      },
    async handleDelete(row){

        // 确认删除操作
        this.$confirm('你确定要删除这条数据吗?', '删除确认', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(async () => {
            // 在这里执行删除操作，例如向后端发送请求
            console.log("删除操作", row);
            try {
                const obj = await formDelete(row.id); // 获取数据
                if (obj && obj.code === "0x200") {
                    alert(obj.message);
                    this.switchbutton = true; // 根据需要更新按钮状态
                    this.fetchData();
                } else {
                    alert(obj.message);
                }
            } catch (error) {
                console.error("请求失败", error);
                this.switchbutton = false;
            }
        }).catch(() => {
            // 如果取消删除，什么也不做
            console.log("取消删除");
        });

      },
      async submitUpdate() { 
        this.nameIsSure = false;
        try {
            const obj = await formUpdate(this.FormUpdateBo); // 获取数据
            if (obj && obj.code === "0x200") {
                console.log("成功", obj.data);
                this.switchbutton = true; // 根据需要更新按钮状态
                this.fetchData();
            } else {
                alert(obj.message);
                this.switchbutton = false;
            }
        } catch (error) {
            console.error("请求失败", error);
            this.switchbutton = false;
        }
      },
        // 添加 resetFormName 方法
        resetFormName() {
        // 重置表单相关数据，如果需要可以重置 FormUpdateBo 或其他表单状态
        this.FormUpdateBo.name = '';
        this.FormUpdateBo.id = '';
        console.log("表单已重置");
        },
      handleView() {
          this.viewIsSure = true;
          this.fetchFormData();
        },
        async submitInfo() { 
            this.viewIsSure = false
            this.AddFormInfo.formId = this.formId
            this.AddFormInfo.formData = JSON.stringify(this.formData)
            try {
                const obj = await addFormInfo(this.AddFormInfo); // 获取数据
                if (obj && obj.code === "0x200") {
                    console.log("成功", obj.data);
                    alert(obj.message);
                    this.switchbutton = true; // 根据需要更新按钮状态
                    this.fetchData();
                } else {
                    alert(obj.message);
                    this.switchbutton = false;
                }
            } catch (error) {
                console.error("请求失败", error);
                this.switchbutton = false;
            }
            console.log("formData", this.formData);
        }
  },
};
</script>

<style scoped>
/* 这里可以添加你的样式 */
</style>
