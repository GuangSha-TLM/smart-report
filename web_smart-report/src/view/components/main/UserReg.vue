<template>
    <div class="Register">
        <div>
            <Top></Top>
        </div>

        <!-- Main content -->
        <section>
            <div class="container">
                <!-- Title -->
                <!-- Section title -->
                <div class="row mb-5 justify-content-center text-center">
                    <div class="col-lg-6">

                        <h2 class=" mt-4">注册您的账户</h2>
                        <div class="mt-2">
                            <p class="lead lh-180">Register Your Account</p>
                        </div>

                        <div>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Account number</label>
                                <input v-model="userRegisterData.username" type="email" class="form-control"
                                    id="exampleInputEmail1" aria-describedby="emailHelp">
                                <small id="emailHelp" class="form-text text-muted">We'll never share your account
                                    information with anyone
                                    else.</small>
                            </div>

                            <div class="form-group">
                                <label for="exampleInputPassword1">Enter Password</label>
                                <input type="password" class="form-control" id="exampleInputPassword1"
                                    v-model="userRegisterData.password">
                            </div>

                            <div class="form-group">
                                <label for="exampleInputPassword1">choose a college</label>
                                <select name="请选择你的学院" id="exampleInputSchoolId"  class="form-control"  
                                        v-model="userRegisterData.schoolId"  >
                                           <option value=1>信息学院</option>
                                           <option value=2>财经学院</option>
                                           <option value=3>艺术与传媒学院</option>
                                           <option value=3>通识教育学院</option>
                                           <option value=3>马克思主义学院</option>
                                           <option value=3>创新创业学院</option>
                                 </select>
                            </div>


                            <!-- <div class="form-group">
                                <label for="exampleInputPassword1">Enter Password again</label>
                                <input type="password" class="form-control" id="exampleInputPassword1"
                                    v-model="user.againPassword">
                            </div> -->

                            <button class="btn btn-primary" style="width:100%" @click="submit()">Submit</button>

                        </div>
                    </div>
                </div>

            </div>
        </section>

        <footer class="position-relative" id="footer-main">
            <Foot></Foot>
        </footer>
    </div>
</template>

<script>
import Foot from '../frame/Foot.vue';
import Top from '../frame/LoginTop.vue';
//引入api的接口文件
import { userRegister } from "@/api/userinfo";
import { reg } from "@/api/user";

export default {
    name: 'Register',
    components: {
        Foot, Top
    },
    data() {
        return {
            userRegisterData: {
                username: "",
                password: "",
                schoolId: null
            },
        }
    },
    mounted() {

    },

    methods: {
        //提交登入
        // async submit() {
        //     // this.switchbutton = true;
        //     //密码的重复输入正确判断
        //     // if (this.userRegister.password !== this.user.againPassword) {
        //     //     alert('密码设置失败');
        //     //     return;
        //     // }

        //     let obj = await userRegister("user/userReg", this.userRegisterData);
        //     if (obj.code == '0x200') {
        //         console.log(obj, 'data');
        //         // this.$router.push("/user/login");
        //     }
        // },
        submit() {
            
            this.userRegisterData.schoolId=parseInt( this.userRegisterData.schoolId)
            reg(this.userRegisterData).then((obj) => {
              if (obj && obj.code === "0x200") {
                  console.log("注册成功");
                  alert(obj.message);
                  this.$router.push("/user/login");
                  this.switchbutton = false;
                  return obj
                  // 处理登录成功后的操作
              } else {
                console.error("登录失败");
                alert(obj.message);
                this.switchbutton = false;
                // return
              }
          })
          .catch((error) => {
              console.error("登录请求失败", error);
          });;;

            // this.$axios.post('/api/user/userReg', this.userRegisterData).then(res => {
            //     console.log(res);
            // })
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
    font-weight: normal;
}

ul {
    list-style-type: none;
    padding: 0;
}

li {
    display: inline-block;
    margin: 0 10px;
}

a {
    color: #42b983;
}
</style>