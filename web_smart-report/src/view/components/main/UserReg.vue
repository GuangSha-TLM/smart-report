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
                                <input type="email" class="form-control" id="exampleInputEmail1"
                                    aria-describedby="emailHelp">
                                <small id="emailHelp" class="form-text text-muted">We'll never share your account
                                    information with anyone
                                    else.</small>
                            </div>

                            <div class="form-group">
                                <label for="exampleInputPassword1">Enter Password</label>
                                <input type="password" class="form-control" id="exampleInputPassword1"
                                    v-model="user.password">
                            </div>

                            <div class="form-group">
                                <label for="exampleInputPassword1">Enter Password again</label>
                                <input type="password" class="form-control" id="exampleInputPassword1"
                                    v-model="user.againPassword">
                            </div>

                            <button class="btn btn-primary" style="width:100%" @click="submit()"
                                :disabled="switchbutton">Submit</button>

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
import { synRequestPost, synRequestGet } from "../../../../static/request"

export default {
    name: 'Register',
    components: {
        Foot, Top
    },
    data() {
        return {
            user: {
                username: "",
                password: "",
                againPassword: "",
            },
            input: "",
            //按钮开关
            switchbutton: false,
        }
    },
    mounted() {

    },

    methods: {
        //提交登入
        async submit() {
            this.switchbutton = true;
            //密码的重复输入正确判断
            if (this.user.password !== this.user.againPassword) {
                alert('密码设置失败');
                return;
            }

            let obj = await synRequestPost("/user/userReg", this.user);
            alert(obj.data);
            if (obj.code == '0x200') {
                this.$router.push("/user/login");
            }


            this.switchbutton = false;
        },
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
  