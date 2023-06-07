<template>
    <div>
        <el-button @click="evt => this.$refs.upload.open()">打开上传页面</el-button>
        <upload-index ref="upload" @success="data => $message.success(data.storageName)"/>
    </div>
</template>
<script>
import UploadIndex from "@/components/system/upload/upload-index.vue";

export default {
    name: "TestPage",
    components: {UploadIndex},
    data() {
        return {}
    },
}

</script>
<style scoped>

</style>