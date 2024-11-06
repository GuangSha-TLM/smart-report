<template>
    <div class="hello">
        <div>
            <Top></Top>
        </div>
        
        <!-- Main content -->
        <section >
            <div class="container">
                <!-- Title -->
                <!-- Section title -->
                <div class="row mb-5 justify-content-center text-center">
                    <div class="col-lg-6">
                       
                        <h2 class=" mt-4">登入您的账户</h2>
                        <div class="mt-2">
                            <p class="lead lh-180">Login Your Account</p>
                        </div>
                       
                        <div>
                                <div class="form-group">
                                  <label for="exampleInputEmail1">Username</label>
                                  <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" v-model="userLoginBo.username">
                                  <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                                </div>
                                <div class="form-group">
                                  <label for="exampleInputPassword1">Password</label>
                                  <input type="password" class="form-control" id="exampleInputPassword1" v-model="userLoginBo.passwd">
                                </div>
                                <div class="form-group form-check">
                                      
                                </div>
                                <button  class="btn btn-primary" style="width:100%" @click="submit()"   :disabled="switchbutton">Submit</button>
                           
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
  import Top  from '../frame/LoginTop.vue'
  import {synRequestPost,synRequestGet} from "../../../../static/request"
  import { login } from "@/api/user";
  import { setCookie } from "../../../../static/ZuiBlog/ZuiBlog";

  
  export default {
    name: 'HelloWorld',
    components: {
           Foot,Top
      },
    data () {
      return {
        userLoginBo: {
            username: "",
            passwd: "",
        },
        //按钮开关
        switchbutton: false,
      }
    },
    mounted(){
    
  },

  methods: {
    //提交登入
    async submit(){
      this.switchbutton = true;
         const object = login(this.userLoginBo)
         .then((obj) => {
              if (obj && obj.code === "0x200") {
                  console.log("登录成功", obj.data);
                  setCookie ("token",obj.data);
                  alert(obj.message);
                  this.$router.push("/form");
                  this.switchbutton = false;
                  return obj
                  // 处理登录成功后的操作
              } else {
                console.error("登录失败", obj.message);
                alert(obj.message);
                this.switchbutton = false;
                // return
              }
          })
          .catch((error) => {
              console.error("登录请求失败", error);
          });;
          
     },
}
  }
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
  h1, h2 {
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
  