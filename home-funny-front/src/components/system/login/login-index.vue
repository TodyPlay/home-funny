<template>
    <div class="login-div">

        <el-form class="login-form" ref="loginForm" :rules="rules" :model="loginData" @submit="handleLogin"
                 @submit.native.prevent>
            <h3 style="text-align: center; margin: 0 auto 30px auto">Home Funny</h3>
            <el-form-item prop="username">
                <el-input v-model="loginData.username" size="large">
                    <template #prefix>
                        <el-icon>
                            <user/>
                        </el-icon>
                    </template>
                </el-input>
            </el-form-item>
            <div style="height: 10px">

            </div>
            <el-form-item prop="password">
                <el-input v-model="loginData.password" size="large" type="password">
                    <template #prefix>
                        <el-icon>
                            <lock/>
                        </el-icon>
                    </template>
                </el-input>
            </el-form-item>
            <div style="height: 50px">

            </div>
            <el-button :disabled="loading" native-type="submit" type="primary" style="width: 100%; height: 40px;">
                点击登录
            </el-button>
        </el-form>
    </div>
</template>

<script>

import {restApi} from "@/api/restApi";
import {Lock, User} from "@element-plus/icons-vue";

export default {
    name: "Login",
    components: {Lock, User},
    data() {
        return {
            loginData: {
                username: "",
                password: "",
            },
            loading: false,
            rules: {
                username: [{required: true, message: "请填写用户名"}],
                password: [{required: true, message: "请填写密码"}]
            }
        }
    },
    methods: {
        async handleLogin() {
            let validate = await this.$refs.loginForm.validate(() => {
            });
            if (!validate) {
                return
            }
            this.loading = true;
            await restApi.do_login(this.loginData.username, this.loginData.password).then(
                data => {
                    this.$message.success("登录成功");
                    this.$cookies.set("Token", data.data);
                    this.$router.push(this.$route.query.redirect || "/")
                },
                err => {
                    this.$message.error("登录失败：" + (err.response.data.message || "服务器错误"))
                }
            ).finally(() => {
                this.loading = false;
            })
        }
    },
}
</script>

<style scoped>
.login-div {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    background: url("@/assets/R-C.jfif");
    background-size: cover;
}

.login-form {
    border-radius: 6px;
    width: 400px;
    height: 320px;
    padding: 25px 25px 5px 25px;
    background: #ffffff;

}
</style>