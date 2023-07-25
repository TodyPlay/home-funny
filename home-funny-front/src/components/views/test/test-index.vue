<template>
    <div>
        <el-button @click="upload">点击上传</el-button>
    </div>
</template>
<script>

import axios from "axios";

export default {
    name: "TestPage",
    computed: {},
    data() {
        return {}
    },
    methods: {
        upload() {
            this.$message.success("ok")
        }
    },
}

</script>
<style scoped>

</style>