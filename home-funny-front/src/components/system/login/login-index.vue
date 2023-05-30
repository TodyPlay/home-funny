<template>
    <div class="login">
        <el-button @click="handleLogin">点击登录</el-button>
    </div>
</template>

<script>

export default {
    name: "登录",
    data() {
        return {}
    },
    methods: {
        handleLogin() {
            this.$cookies.set("username", "jianchen");
            this.$cookies.set("password", "123456");
            this.$router.push(this.$route.query.redirect || "/")
        }
    },
}
</script>

<style scoped>

</style>