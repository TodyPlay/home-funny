<template>
    <div>
        <el-button @click="upload">点击上传</el-button>
    </div>
</template>
<script>

export default {
    name: "TestPage",
    computed: {},
    data() {
        return {}
    },
    methods: {
        upload() {
            this.$uploader.uploadSingle().then(data => {
                console.log(data);
            }, err => {
                console.log(err);
            })
        }
    },
}

</script>
<style scoped>

</style>