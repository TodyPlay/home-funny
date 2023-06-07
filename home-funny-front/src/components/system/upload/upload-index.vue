<template>
    <div>
        <el-dialog v-model="show" title="文件上传" append-to-body
                   :close-on-click-modal="false"
        >
            <el-upload :action="'/api/v1' + restApi.storage" method="PUT"
                       :show-file-list="false"
                       :on-success="success"
                       :on-error="error => $message.error(error)"
                       :accept="accept"
                       :on-progress="process"
                       :before-upload="before"
            >
                <el-button :icon="UploadFilled" type="primary" :disabled="pending">点击上传</el-button>
            </el-upload>
            <el-progress v-if="pending" :percentage="percent"/>
        </el-dialog>
    </div>
</template>

<script>
import {constants} from "@/constant";
import {restApi} from "@/api/restApi";
import {UploadFilled} from "@element-plus/icons-vue";

export default {
    props: ['accept'],
    emits: ['success'],
    data() {
        return {
            show: false,
            percent: 0,
            pending: false,
        }
    },
    computed: {
        UploadFilled() {
            return UploadFilled
        },
        restApi() {
            return restApi
        },
        imageSuffix() {
            return constants.imageSuffix;
        }
    },
    methods: {
        success(data) {
            this.$message.success("上传完成")
            setTimeout(() => {
                this.show = false;
                this.$emit('success', data)
                this.percent = 0;
                this.pending = false;
            }, 1000)
        },
        process(process) {
            this.percent = process.percent;
        },
        open() {
            this.show = true;
        },
        before(f) {
            console.log(f);
            this.pending = true;
        }
    }
}
</script>

<style scoped>

</style>