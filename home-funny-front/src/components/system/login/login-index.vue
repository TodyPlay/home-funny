<template>
    <div class="login-div">
        <el-form class="login-form" ref="loginForm" :rules="rules" :model="loginData" @submit="handleLogin"
                 @submit.native.prevent>
            <el-form-item label="用户名" prop="username">
                <el-input v-model="loginData.username"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="loginData.password"></el-input>
            </el-form-item>
            <el-button native-type="submit">点击登录</el-button>
        </el-form>
    </div>
</template>

<script>

import {restApi} from "@/api/restApi";

export default {
    name: "Login",
    data() {
        return {
            loginData: {
                username: "",
                password: "",
            },
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

            let data = await restApi.do_login(this.loginData.username, this.loginData.password);
            if (data.code === 200) {
                this.$message.success("登录成功");
                this.$cookies.set("Token", data.token);
                this.$router.push(this.$route.query.redirect || "/")
            } else {
                this.$message.error("登录失败：" + data.message)
            }


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
}

.login-form {
    border-radius: 6px;
    width: 400px;
    padding: 25px 25px 5px 25px;
}
</style>