<template>
  <div>

    <!-- 操作栏 -->
    <el-row type="flex" justify="end" style="margin-bottom: 20px; ">
      <el-col :span="2"  >
        <el-button type="primary" @click="sendIsSure = true" style="width: 100px;">发送</el-button>
      </el-col>
      <el-col :span="2" >
        <el-button type="primary" @click="sendIsSureAll = true" >发送学院</el-button>
      </el-col>
    </el-row>

    <!-- 表格展示数据 -->
    <el-table :data="tableData" style="width: 100%" border>
      <el-table-column label="ID">
            <template slot-scope="scope">{{ scope.$index + 1 }}</template>
      </el-table-column>
      <el-table-column label="名称" prop="name"></el-table-column>
      <!-- <el-table-column label="创建人" prop="createdText"></el-table-column> -->

      <!-- 操作栏（每行数据右侧的操作按钮） -->
      <el-table-column label="操作" width="300">
        <template slot-scope="scope">
          <el-button @click="handleView(scope.row)" size="small" type="primary">查看</el-button>
          <el-button @click="handleEdit(scope.row)" size="small" type="primary">修改</el-button>
          <el-button @click="handleDelete(scope.row)" size="small" type="danger" style="margin-left: 10px;">删除</el-button>
          <el-button @click="handleDetail(scope.row)" size="small" type="primary">详情</el-button>
        </template>
      </el-table-column>

    </el-table>

    <!-- 发送表单的弹窗 -->
    <el-dialog title="发送表单" :visible.sync="sendIsSure" width="30%" @close="resetFormName">
        <el-form>
            <el-form-item label="表单名称">
                <el-select v-model="SendFormBo.formId" placeholder="请选择表单名称">
                    <el-option
                        v-for="(item, index) in formNames"
                        :key="index"
                        :label="item.name"  
                        :value="item.id" 
                    ></el-option>
                </el-select>
            </el-form-item>
        </el-form>
        <el-form>
            <el-form-item label="选择用户">
                <el-checkbox-group v-model="SendFormBo.userId">
                    <el-checkbox
                        v-for="(user, index) in userDatas"
                        :key="user.id"
                        :label="user.id"
                    >{{ user.username }}</el-checkbox>
                </el-checkbox-group>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="sendIsSure = false">取消</el-button>
            <el-button type="primary" @click="submitSend">确定</el-button>
        </div>
    </el-dialog>
    

    <!-- 发送学院表单的弹窗 -->
    <el-dialog title="发送学院表单" :visible.sync="sendIsSureAll" width="30%" @close="resetFormName">
        <el-form>
            <el-form-item label="表单名称">
                <el-select v-model="SendFormBo.formId" placeholder="请选择表单名称">
                    <el-option
                        v-for="(item, index) in formNames"
                        :key="index"
                        :label="item.name"  
                        :value="item.id" 
                    ></el-option>
                </el-select>
            </el-form-item>
        </el-form>
        <el-form>
            <el-form-item label="选择学院：">
                <el-checkbox-group v-model="SendFormBo.schoolId">
                    <el-checkbox label=1>信息学院</el-checkbox>
                    <el-checkbox label=2>财经学院</el-checkbox>
                    <el-checkbox label=3>艺术与传媒学院</el-checkbox>
                    <el-checkbox label=4>通识教育学院</el-checkbox>
                    <el-checkbox label=5>马克思主义学院</el-checkbox>
                    <el-checkbox label=6>创新创业学院</el-checkbox>
                </el-checkbox-group>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="sendIsSure = false">取消</el-button>
            <el-button type="primary" @click="submitSend">确定</el-button>
        </div>
    </el-dialog>


    <!-- 导出表单的弹窗 -->
    <el-dialog title="请输入表单名称" :visible.sync="nameIsSure" width="30%" @close="resetFormName">
        <el-form>
            <el-form-item label="表单名称">
                <el-select placeholder="请选择表单名称">
                    <el-option
                        v-for="(name, index) in formNames"
                        :key="index"
                        :label="name"
                        :value="name"
                    ></el-option>
                </el-select>
            </el-form-item>
        </el-form>        
        <div slot="footer" class="dialog-footer">
            <el-button @click="nameIsSure = false">取消</el-button>
            <el-button type="primary" @click="submitUpdate">确定</el-button>
        </div>
    </el-dialog>

    <!-- 预览 -->
    <el-dialog title="预览" :visible.sync="viewIsSure" width="30%">
        <div>
            <!-- 这里是回显表单数据的部分 -->
            <form-create :rule="rule" :option="option" :value.sync="formData"></form-create>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button @click="viewIsSure = false">取消</el-button>
            <!-- <el-button type="primary">确定</el-button> -->
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
import { formByPageLike,formDelete,formUpdate,formQuery,sendFormUser } from "@/api/form";
import { getUserDatas } from "@/api/user";

export default {
  data() {
    return {
      currentPage: 1,  // 当前页数
      pageSize: 10,    // 每页显示多少条数据
      totalItems: 0,   // 数据总数
      tableData: [],   // 表格数据
      nameIsSure: false, // 修改按钮
      sendIsSure: false, // 发送按钮
      sendIsSureAll: false, // 学院发送按钮
      viewIsSure: false, // 预览按钮
      formId: '', // 表单id
       selectedUsers: [], //复选框 - 》 勾选中的用户id
       selectedSchools: [], //复选框 - 》 勾选中的用户id
      formNames:{},
      // 预览时表单数据
      formData: {},
      userDatas: [], //用户数据
      schoolDatas: [1,2,3], //学院数据
      // 表单配置项
      rule: [],  // 表单规则配置
      option: {},  // 表单其他选项配置
      FormNewPageBo : {
        page: 1,
        limit: 10,
        name: ''
        },
        FormNewPageBoNew: {
        page: 1,
        limit: 999999999,
        name: ''
      },
      FormUpdateBo:{
        name: '',
        id : ''
      },      
      SendFormBo:{
        formId: '',
        userId: [],
        schoolId :[]
      }
    };
  },
  created() {
    this.fetchData(); // 组件创建时加载数据
    this.formNamesData(); // 组件创建时加载数据
    this.userData(); // 组件创建时加载数据
  },
    methods: {
        async formNamesData() {
            this.formNames = await formByPageLike(this.FormNewPageBoNew)
                .then(obj => obj.data); // 获取数据
            console.log('formNames', this.formNames);
        },
        async submitSend() {
            try {
                const obj = await sendFormUser(this.SendFormBo); // 获取数据
                if (obj && obj.code === "0x200") {
                    console.log("成功", obj.data);
                    this.tableData = obj.data; // 设置表格数据
                    this.totalItems = obj.count; // 设置数据总数 (假设后端返回了 total 字段)
                    this.switchbutton = true; // 根据需要更新按钮状态
                    this.sendIsSure = false;
                    alert(obj.message);
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
        async userData() {
            this.userDatas = await getUserDatas()
                .then(obj => obj.data); // 获取数据
            console.log('userDatas', this.userDatas);
         },
    // 获取表单数据和配置
    async fetchFormData() {
      try {
        const response = await formQuery(this.formId);
console.log('config', response);

if (response && response.code === "0x200") {
  try {
    // 假设返回的数据包含表单规则和表单值
    //   this.rule = response.data.map(str => {
    //         console.log('str', str);
    //     return JSON.parse(str);
      //     });  // 表单规则
    this.rule = JSON.parse(response.data);
        //   this.formData = response.data || [];  // 表单回显数据
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
            const obj = await formByPageLike(this.FormNewPageBo); // 获取数据
            if (obj && obj.code === "0x200") {
                console.log("成功", obj.data);
                this.tableData = obj.data; // 设置表格数据
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
        async handleDetail(row) {
            // 路由跳转，假设 `row.id` 是你想传递的参数
            this.$router.push({ name: 'formDetail', params: { id: row.id } });
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
      handleView(row) {
          this.formId = row.id;
          this.viewIsSure = true;
          this.fetchFormData();
      }
  },
};
</script>

<style scoped>
/* 这里可以添加你的样式 */
</style>
